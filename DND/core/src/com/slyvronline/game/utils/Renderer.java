package com.slyvronline.game.utils;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.slyvronline.game.Game;

public class Renderer {

	public static void render(){
		SpriteBatch batch = Game.getGlobal().getBatch();
		
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		
		Game.getGlobal().setStateTime(Game.getGlobal().getStateTime() + Gdx.graphics.getDeltaTime());
		
		batch.begin();
		
		if (Game.getGlobal().getBackMenu()!=null){
			batch.setProjectionMatrix(Game.getGlobal().getBackCam().combined);
			Game.getGlobal().getBackMenu().render(batch);
		}
		
		batch.setProjectionMatrix(Game.getGlobal().getCamera().combined);
		
		if (Game.getGlobal().getGame() != null){
			Game.getGlobal().getGame().render(batch);
		}
		
		if (Game.getGlobal().getOverlapMenu()!=null){
			batch.setProjectionMatrix(Game.getGlobal().getBackCam().combined);
			Game.getGlobal().getOverlapMenu().render(batch);
		}
		
		batch.setProjectionMatrix(Game.getGlobal().getHudCam().combined);
		
		Game.getGlobal().getCurrentMenu().render(batch);
		
		if (!Game.getGlobal().getCurrentMenu().getName().equals("splash"))
			Game.getGlobal().getMusicMenu().render(batch);
		
		
		batch.end();
	}
}
