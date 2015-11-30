package com.slyvronline.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.slyvronline.game.load.LoadFonts;
import com.slyvronline.game.load.LoadImgs;
import com.slyvronline.game.load.LoadMenus;
import com.slyvronline.game.load.LoadMusic;
import com.slyvronline.game.load.LoadSounds;
import com.slyvronline.game.objects.Global;
import com.slyvronline.game.utils.GameConstants;
import com.slyvronline.game.utils.Renderer;
import com.slyvronline.game.utils.Updater;

public class Game extends ApplicationAdapter implements InputProcessor {
	public static final String TITLE = "DND";
	
	private static Global global;
	
	@Override
	public void create () {
		global = new Global();
		LoadImgs.load();
		LoadFonts.load();
		LoadSounds.load();
		LoadMusic.load();
		LoadMenus.load();
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		//Start game in fullscreen
		//Gdx.graphics.setDisplayMode(GameConstants.DEFAULT_WIDTH, GameConstants.DEFAULT_HEIGHT, true);
		
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Updater.update();
		
		//Render
		Renderer.render();
		
		//if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
	}
	
	public static Global getGlobal(){
		return global;
	}
	
	public void pause() {}
	public void resume() {}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if (amount > 0 && global.getCamera().zoom < 2.2f){
			global.getCamera().zoom += 0.05f;
		}
		else if (amount < 0 && global.getCamera().zoom > 0.05){
			global.getCamera().zoom -= 0.05f;
		}
		return false;
	}
}
