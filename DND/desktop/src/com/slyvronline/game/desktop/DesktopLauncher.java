package com.slyvronline.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.slyvronline.game.Game;
import com.slyvronline.game.utils.GameConstants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Game.TITLE;
		config.width = GameConstants.DEFAULT_WIDTH;
		config.height = GameConstants.DEFAULT_HEIGHT;
		
		new LwjglApplication(new Game(), config);
	}
}
