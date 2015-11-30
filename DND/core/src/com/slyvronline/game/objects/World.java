package com.slyvronline.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
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
	
	private TextInput tileSizeInput;
	private TextInput worldWidthInput;
	private TextInput worldHeightInput;
	
	private boolean loadWorld;
	private boolean worldLoaded;
	
	private Ent select;
	
	public World(){
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
		
		select.render(batch);
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
}
