package com.slyvronline.game.objects;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Ent.State;
import com.slyvronline.game.utils.TextInput;
import com.slyvronline.game.utils.Utils;

public class World {
	
	private Layer layer1;
	private Layer layer2;
	private Layer layer3;
	private Layer layer4;
	private int tileSize;
	private int width;
	private int height;
	
	private ArrayList<Ent> characters;
	
	private TextInput tileSizeInput;
	private TextInput worldWidthInput;
	private TextInput worldHeightInput;
	
	private boolean loadWorld;
	private boolean worldLoaded;
	
	private Ent select;
	
	public World(){
		characters = new ArrayList<Ent>();
		
		layer1 = new Layer();
		layer1.setName("Layer 1");
		
		layer2 = new Layer();
		layer2.setName("Layer 2");
		
		layer3 = new Layer();
		layer3.setName("Layer 3");
		
		layer4 = new Layer();
		layer4.setName("Layer 4");
		
		tileSizeInput = new TextInput();
		worldWidthInput = new TextInput();
		worldHeightInput = new TextInput();
		Gdx.input.getTextInput(tileSizeInput, "Enter tile size", "32","");
		Gdx.input.getTextInput(worldWidthInput, "Enter world width", "100","");
		Gdx.input.getTextInput(worldHeightInput, "Enter world height", "64","");
		
		loadWorld = false;
		worldLoaded = false;
		
		select = new Ent();
		select.setName("select");
		select.setImg(Game.getGlobal().getImgByName("select"));
		select.setPosBox(new Rectangle(0,0,0,0));
		select.setColor(Color.RED);
	}
	
	public void update(){
		if (tileSizeInput != null){
			if (tileSizeInput.getText() != null){
				tileSize = Integer.parseInt(tileSizeInput.getText());
				tileSizeInput = null;
			}
		}
		if (worldWidthInput != null){
			if (worldWidthInput.getText() != null){
				width = Integer.parseInt(worldWidthInput.getText());
				worldWidthInput = null;
			}
		}
		if (worldHeightInput != null){
			if (worldHeightInput.getText() != null){
				height = Integer.parseInt(worldHeightInput.getText());
				worldHeightInput = null;
			}
		}
		
		if (tileSize != 0 && width != 0 && height != 0){
			loadWorld = true;
		}
		
		if (loadWorld && !worldLoaded){
			loadWorld();
		}
		
		if (worldLoaded){
			gameUpdates();
		}
	}
	
	public void render(SpriteBatch batch){
		layer1.render(batch);
		layer2.render(batch);
		layer3.render(batch);
		layer4.render(batch);
		
		for(Ent character : characters){
			character.render(batch);
		}
		
		select.render(batch);
	}
	
	public String save(String gameName){
		String xmlOutput = "";
		
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try {
            icBuilder = icFactory.newDocumentBuilder();
            Document doc = icBuilder.newDocument();
            Element mainRootElement = doc.createElementNS("", gameName);
            doc.appendChild(mainRootElement);
 
            // append child elements to root element
            //mainRootElement.appendChild(getCompany(doc, "1", "Paypal", "Payment", "1000"));
            mainRootElement.setAttribute("tileSize", ""+tileSize);
            mainRootElement.setAttribute("width", ""+width);
            mainRootElement.setAttribute("height", ""+height);
            
            Element layer1Elem = doc.createElement("layer1");
            Element layer2Elem = doc.createElement("layer2");
            Element layer3Elem = doc.createElement("layer3");
            Element layer4Elem = doc.createElement("layer4");
            
            for(Ent e : layer1.getEnts()){
            	if (e.getImg() != null){
	            	Element ent = doc.createElement("tile");
	            	ent.setAttribute("Img", e.getImg().getName());
	            	ent.setAttribute("X", ""+e.getPosBox().getX());
	            	ent.setAttribute("Y", ""+e.getPosBox().getY());
	            	layer1Elem.appendChild(ent);
            	}
            }
            
            for(Ent e : layer2.getEnts()){
            	if (e.getImg() != null){
	            	Element ent = doc.createElement("tile");
	            	ent.setAttribute("Img", e.getImg().getName());
	            	ent.setAttribute("X", ""+e.getPosBox().getX());
	            	ent.setAttribute("Y", ""+e.getPosBox().getY());
	            	layer2Elem.appendChild(ent);
            	}
            }
            
            for(Ent e : layer3.getEnts()){
            	if (e.getImg() != null){
	            	Element ent = doc.createElement("tile");
	            	ent.setAttribute("Img", e.getImg().getName());
	            	ent.setAttribute("X", ""+e.getPosBox().getX());
	            	ent.setAttribute("Y", ""+e.getPosBox().getY());
	            	layer3Elem.appendChild(ent);
            	}
            }
            
            for(Ent e : layer4.getEnts()){
            	if (e.getImg() != null){
	            	Element ent = doc.createElement("tile");
	            	ent.setAttribute("Img", e.getImg().getName());
	            	ent.setAttribute("X", ""+e.getPosBox().getX());
	            	ent.setAttribute("Y", ""+e.getPosBox().getY());
	            	layer4Elem.appendChild(ent);
            	}
            }
            
            mainRootElement.appendChild(layer1Elem);
            mainRootElement.appendChild(layer2Elem);
            mainRootElement.appendChild(layer3Elem);
            mainRootElement.appendChild(layer4Elem);
            
            xmlOutput = Utils.docToString(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return xmlOutput;
	}
	
	public void load(String gameName, String xmlData) throws Exception{
		Document doc = Utils.loadXMLFromString(xmlData);
		doc.normalize();
		tileSize = Integer.parseInt(doc.getDocumentElement().getAttribute("tileSize"));
		width = Integer.parseInt(doc.getDocumentElement().getAttribute("width"));
		height = Integer.parseInt(doc.getDocumentElement().getAttribute("height"));
		
		NodeList layer1Node = doc.getElementsByTagName("layer1");
		NodeList layer2Node = doc.getElementsByTagName("layer2");
		NodeList layer3Node = doc.getElementsByTagName("layer3");
		NodeList layer4Node = doc.getElementsByTagName("layer4");
		
		loadWorld();
		
		loadXmlTiles(layer1Node, layer1);
		loadXmlTiles(layer2Node, layer2);
		loadXmlTiles(layer3Node, layer3);
		loadXmlTiles(layer4Node, layer4);
	}
	
	public void loadXmlTiles(NodeList list, Layer layer){
		for(int i=0; i<list.getLength(); i++){
			Node node = list.item(i);
			NodeList tileNodes = node.getChildNodes();
			for(int x=0; x<tileNodes.getLength(); x++){
				Node tileNode = tileNodes.item(x);
				NamedNodeMap attr = tileNode.getAttributes();
				Ent e = new Ent();
				e.setPosBox(new Rectangle(0,0,tileSize,tileSize));
				for(int a=0; a<attr.getLength(); a++){
					Node attribute = attr.item(a);
					String name = attribute.getNodeName();
					String value = attribute.getNodeValue();
					if (name.equals("Img")){
						e.setImg(Game.getGlobal().getImgByName(value));
					}
					else if (name.equals("X")){
						e.getPosBox().setX(Float.parseFloat(value));
					}
					else if (name.equals("Y")){
						e.getPosBox().setY(Float.parseFloat(value));
					}
				}
				//Find associated tile and set image
				for(Ent tile : layer.getEnts()){
					if (tile.getPosBox().overlaps(e.getPosBox())){
						tile.setImg(e.getImg());
						break;
					}
				}
			}
		}
	}
	
	public void loadWorld(){
		worldLoaded = true;
		for(int y=0; y<height; y++){
			for(int x=0; x<width; x++){
				Ent tile = new Ent();
				tile.setName("tile");
				tile.setImg(Game.getGlobal().getImgByName("select"));
				tile.setPosBox(new Rectangle(
						x*tileSize,
						y*tileSize,
						tileSize,
						tileSize));
				tile.setImg(null);
				
				layer1.getEnts().add(new Ent(tile));
				layer2.getEnts().add(new Ent(tile));
				layer3.getEnts().add(new Ent(tile));
				layer4.getEnts().add(new Ent(tile));
			}
		}
	}
	
	public void gameUpdates(){
		checkCharacterSelect();
		checkTileClick();
	}
	
	public void checkTileClick(){
		Rectangle mousePos = Utils.getGameMousePos();
		Rectangle menuMousePos = Utils.getMenuMousePos();
		boolean selectedSomething = false;
		
		//Check if selecting from menuitems to prevent clicking game objects when not meeting to
		boolean selectingMenuItem = false;
		for(Ent e : Game.getGlobal().getCurrentMenu().getEnts()){
			if (e.getPosBox().overlaps(menuMousePos)){
				selectingMenuItem = true;
			}
		}
		for(Ent e : Game.getGlobal().getMusicMenu().getEnts()){
			if (e.getPosBox().overlaps(menuMousePos)){
				selectingMenuItem = true;
			}
		}
		
		Ent placeableTile = null;
		for(Ent e : Game.getGlobal().getCurrentMenu().getEnts()){
			if (e.getState() == State.SELECTED){
				placeableTile = e;
			}
		}
		
		//Check hovers
		if (!selectingMenuItem){
			Layer layer = layer1;
			if (placeableTile != null){
				if (placeableTile.getType().equals("layer1")) layer = layer1;
				else if (placeableTile.getType().equals("layer2")) layer = layer2;
				else if (placeableTile.getType().equals("layer3")) layer = layer3;
				else if (placeableTile.getType().equals("layer4")) layer = layer4;
			}
				
			for(Ent e : layer.getEnts()){
				if (mousePos.overlaps(e.getPosBox())){
					select.setPosBox(e.getPosBox());
					e.setState(State.SELECTED);
					selectedSomething = true;
					//Clicked, place a tile here
					if (Gdx.input.isTouched()){
						placeTile(e, placeableTile);
					}
				}
				else{
					e.setState(State.NORMAL);
				}
			}
		}
		
		if (!selectedSomething){
			select.setPosBox(new Rectangle(0,0,0,0));
		}
	}
	
	public void placeTile(Ent tile, Ent placeableTile){
		if (placeableTile != null){
			if (placeableTile.getName().contains("Delete")){
				tile.setImg(null);
			}
			else{
				tile.setImg(placeableTile.getImg());
			}
		}
	}
	
	private boolean subMenuJustLeft;
	
	public void checkCharacterSelect(){
		boolean characterIsSelected = false;
		Rectangle mousePos = Utils.getGameMousePos();
		for(int i=characters.size()-1; i>=0; i--){
			Ent c = characters.get(i);
			if (c.getState() == State.SELECTED){
				characterIsSelected = true;
				//Check if placing character
				if (Gdx.input.justTouched() && !subMenuJustLeft){
					if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
						characters.remove(c);
					}
					for(Ent tile : layer4.getEnts()){
						if (mousePos.overlaps(tile.getPosBox())){
							c.getPosBox().setX(tile.getPosBox().getX());
							c.getPosBox().setY(tile.getPosBox().getY());
							c.setState(State.NORMAL);
						}
					}
				}
				else{
					c.getPosBox().setX(mousePos.getX());
					c.getPosBox().setY(mousePos.getY());
				}
			}
		}
		
		//Check new character select
		if (!characterIsSelected){
			if (Gdx.input.justTouched()){
				for(Ent c : characters){
					if (mousePos.overlaps(c.getPosBox())){
						c.setState(State.SELECTED);
						Ent btnSelect = Game.getGlobal().getCurrentMenu().getEntByName("btnSelect");
						for(Ent btn : Game.getGlobal().getCurrentMenu().getEnts()){
							if (btn.getName().contains("Tile") && btn.getName().contains("btn")){
								if (btn.getName().equals("btnDeleteTile")){
									btnSelect.setPosBox(new Rectangle(btn.getPosBox()));
								}
								btn.setState(State.NORMAL);
							}
						}
						break;
					}
				}
			}
		}
	}
	
	public void setSubMenuJustLeft(boolean subMenuJustLeft){
		this.subMenuJustLeft=subMenuJustLeft;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Layer getLayer1() {
		return layer1;
	}

	public void setLayer1(Layer layer1) {
		this.layer1 = layer1;
	}

	public Layer getLayer2() {
		return layer2;
	}

	public void setLayer2(Layer layer2) {
		this.layer2 = layer2;
	}

	public Layer getLayer3() {
		return layer3;
	}

	public void setLayer3(Layer layer3) {
		this.layer3 = layer3;
	}

	public Layer getLayer4() {
		return layer4;
	}

	public void setLayer4(Layer layer4) {
		this.layer4 = layer4;
	}

	public ArrayList<Ent> getCharacters() {
		return characters;
	}

	public void setCharacters(ArrayList<Ent> characters) {
		this.characters = characters;
	}

	public TextInput getTileSizeInput() {
		return tileSizeInput;
	}

	public void setTileSizeInput(TextInput tileSizeInput) {
		this.tileSizeInput = tileSizeInput;
	}

	public TextInput getWorldWidthInput() {
		return worldWidthInput;
	}

	public void setWorldWidthInput(TextInput worldWidthInput) {
		this.worldWidthInput = worldWidthInput;
	}

	public TextInput getWorldHeightInput() {
		return worldHeightInput;
	}

	public void setWorldHeightInput(TextInput worldHeightInput) {
		this.worldHeightInput = worldHeightInput;
	}

	public boolean isLoadWorld() {
		return loadWorld;
	}

	public void setLoadWorld(boolean loadWorld) {
		this.loadWorld = loadWorld;
	}

	public boolean isWorldLoaded() {
		return worldLoaded;
	}

	public void setWorldLoaded(boolean worldLoaded) {
		this.worldLoaded = worldLoaded;
	}

	public Ent getSelect() {
		return select;
	}

	public void setSelect(Ent select) {
		this.select = select;
	}
	
	
}
