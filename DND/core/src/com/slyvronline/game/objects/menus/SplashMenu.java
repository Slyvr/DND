package com.slyvronline.game.objects.menus;

import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Menu;
import com.slyvronline.game.utils.GameConstants;

public class SplashMenu extends Menu {

	private long startMillis;
	
	public SplashMenu(){
		
	}
	
	public void update(){
		if (startMillis==0) startMillis = System.currentTimeMillis();
		else{
			if (startMillis+GameConstants.SPLASH_MENU_WAIT<=System.currentTimeMillis()){
				Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("main"));
			}
		}
	}
}
