package com.slyvronline.game.objects.menus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.Ent.State;
import com.slyvronline.game.objects.Menu;
import com.slyvronline.game.utils.Utils;

public class GameMenu extends Menu {

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
}
