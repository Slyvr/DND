package com.slyvronline.game.objects.menus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.Ent.State;
import com.slyvronline.game.objects.Menu;
import com.slyvronline.game.utils.TextInput;
import com.slyvronline.game.utils.Utils;

public class GameMenu extends Menu {

	TextInput saveGameName;
	TextInput loadGameName;
	
	private boolean saving;
	private boolean loading;
	
	public GameMenu(){
		
	}
	
	public void render(SpriteBatch batch){
		for(Ent e : this.getEnts()){
			e.render(batch);
		}
		
		if (this.getCurrentSubMenu() != null){
			this.getCurrentSubMenu().render(batch);
		}
	}
	
	public void update(){
		if (getCurrentSubMenu() != null){
			getCurrentSubMenu().update();
		}
		else{
			if (Game.getGlobal().getGame().isPaused()){
				if (Gdx.input.isKeyJustPressed(Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Keys.P)){
					Game.getGlobal().getGame().setPaused(false);
				}
			}
			else{
				if (Gdx.input.isKeyJustPressed(Keys.P) || Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
					Game.getGlobal().getGame().setPaused(true);
					this.setCurrentSubMenu(this.getSubMenuByName("pause"));
				}
				
				selectTile();
				updateDebugTooltip();
				checkSave();
				checkLoad();
				saveGame();
				loadGame();
				super.updateButtonHover();
				updateCreateCharacterButton();
			}
		}
	}
	
	public void updateDebugTooltip(){
		if (Game.getGlobal().getCurrentMenu().getName().equals("game")){
			Ent debugTooltip = Game.getGlobal().getCurrentMenu().getEntByName("debugTooltip");
			String debugText = "";
			debugText += "\nFPS: "+Gdx.graphics.getFramesPerSecond();
			debugText += "\nCamX: "+Game.getGlobal().getCamera().position.x;
			debugText += "\nCamY: "+Game.getGlobal().getCamera().position.y;
			debugText += "\nTileSize: "+Game.getGlobal().getGame().getWorld().getTileSize();
			debugText += "\nWorldSize: "+Game.getGlobal().getGame().getWorld().getWidth()+","+Game.getGlobal().getGame().getWorld().getHeight();
			debugText += "\nWorldZoom: "+Game.getGlobal().getCamera().zoom;
			debugTooltip.setText(debugText);
		}
	}
	
	public void selectTile(){
		if (Gdx.input.justTouched()){
			Rectangle mousePos = Utils.getMenuMousePos();
			Ent btnSelect = this.getEntByName("btnSelect");
			Ent selectedTile = null;
			for(Ent e : this.getEnts()){
				if (e.getName().contains("btn") && e.getName().contains("Tile")){
					if (mousePos.overlaps(e.getPosBox())){
						e.setState(State.SELECTED);
						btnSelect.setPosBox(new Rectangle(e.getPosBox()));
						selectedTile = e;
					}
				}
			}
			if (selectedTile != null){
				for(Ent e : this.getEnts()){
					if (e.getName() != selectedTile.getName()){
						e.setState(State.NORMAL);
					}
				}
			}
		}
	}
	
	public void saveGame(){
		if(Gdx.input.justTouched()){
			Rectangle mousePos = Utils.getMenuMousePos();
			Ent btnSave = this.getEntByName("btnSave");
			if (btnSave.getPosBox().overlaps(mousePos)){
				saveGameName = new TextInput();
				saving = true;
				Gdx.input.getTextInput(saveGameName, "Enter save game file name", "world1","");
			}
		}
	}
	
	public void checkSave(){
		if (saving && saveGameName != null && saveGameName.getText() != null && saveGameName.getText() != ""){
			saving = false;
			String gameName = saveGameName.getText();
			saveGameName = null;
			String xmlOutput = Game.getGlobal().getGame().getWorld().save(gameName);
			FileHandle saveFile = Gdx.files.external("DND/saveData/"+gameName+".xml");
			if (xmlOutput==null)xmlOutput = "";
			saveFile.writeString(xmlOutput, false);
		}
	}
	
	public void loadGame(){
		if(Gdx.input.justTouched()){
			Rectangle mousePos = Utils.getMenuMousePos();
			Ent btnLoad = this.getEntByName("btnLoad");
			if (btnLoad.getPosBox().overlaps(mousePos)){
				loadGameName = new TextInput();
				loading = true;
				Gdx.input.getTextInput(loadGameName, "Enter load game file name", "world1","");
			}
		}
	}
	
	public void checkLoad(){
		if (loading && loadGameName != null && loadGameName.getText() != null && loadGameName.getText() != ""){
			loading = false;
			String gameName = loadGameName.getText();
			loadGameName = null;
			FileHandle loadFile = Gdx.files.external("DND/saveData/"+gameName+".xml");
			try{
				String xmlData = loadFile.readString();
				xmlData = xmlData.replace("\n", "");
				xmlData = xmlData.replace("\r", "");
				Game.getGlobal().getGame().getWorld().load(gameName, xmlData);
			}
			catch(Exception ex){
				
			}
		}
	}
	
	public void updateCreateCharacterButton(){
		if (Gdx.input.justTouched()){
			Rectangle mousePos = Utils.getMenuMousePos();
			Ent btnAdd = this.getEntByName("btnAddCharacter");
			if (mousePos.overlaps(btnAdd.getPosBox())){
				this.setCurrentSubMenu(this.getSubMenuByName("createcharacter"));
				Ent btnSelect = this.getEntByName("btnSelect");
				for(Ent btn : this.getEnts()){
					if (btn.getName().contains("Tile") && btn.getName().contains("btn")){
						if (btn.getName().equals("btnDeleteTile")){
							btnSelect.setPosBox(new Rectangle(btn.getPosBox()));
						}
						btn.setState(State.NORMAL);
					}
				}
			}
		}
	}
}
