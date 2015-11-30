package com.slyvronline.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.slyvronline.game.Game;

public class Updater {

	public static void update(){
		updateInputs();
		
		Game.getGlobal().getCurrentMenu().update();
		if (Game.getGlobal().getGame() != null && Game.getGlobal().getCurrentMenu().getCurrentSubMenu() == null)
			Game.getGlobal().getGame().update();
		
		Game.getGlobal().getMusicMenu().update();
		
		if (Game.getGlobal().getGame() != null && Game.getGlobal().getCurrentMenu().getName().equals("game")){
			if (Game.getGlobal().getBackMenu()!=null)
				Game.getGlobal().getBackMenu().update();
			
			if (Game.getGlobal().getOverlapMenu()!=null)
				Game.getGlobal().getOverlapMenu().update();
		}
		
		Game.getGlobal().getCamera().update();
		Game.getGlobal().getHudCam().update();
		Game.getGlobal().getBackCam().update();
	}
	
	public static void updateInputs(){
		int speed = 5;
		
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)){
			speed = 10;
		}
		
		if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)){
			Game.getGlobal().getCamera().position.x = Game.getGlobal().getCamera().position.x - speed;
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)){
			Game.getGlobal().getCamera().position.x = Game.getGlobal().getCamera().position.x + speed;
		}
		if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)){
			Game.getGlobal().getCamera().position.y = Game.getGlobal().getCamera().position.y + speed;
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)){
			Game.getGlobal().getCamera().position.y = Game.getGlobal().getCamera().position.y - speed;
		}
	}
}
