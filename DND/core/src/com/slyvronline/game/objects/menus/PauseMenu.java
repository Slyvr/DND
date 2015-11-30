package com.slyvronline.game.objects.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.Menu;

public class PauseMenu extends Menu {

	public PauseMenu(){
		
	}
	
	public void update(){
		super.updateButtonHover();
		updateButtonClick();
	}
	
	public void updateButtonClick(){
		if (Gdx.input.justTouched()){
			Rectangle mousePos = new Rectangle(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY(),1,1);
			
			for(Ent e : getEnts()){
				if (e.getName().contains("btn") && mousePos.overlaps(e.getPosBox())){
					Game.getGlobal().getSoundByName("click").getSound().play();
					if (e.getName().equals("btnMainMenu")){
						Game.getGlobal().getCurrentMenu().setCurrentSubMenu(null);
						Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("main"));
						Game.getGlobal().setBackMenu(null);
						Game.getGlobal().setGame(null);
					}
					if (e.getName().equals("btnReturn")){
						Game.getGlobal().getCurrentMenu().setCurrentSubMenu(null);
						Game.getGlobal().getGame().setPaused(false);
					}
					if (e.getName().equals("btnOptions")){
						Game.getGlobal().getCurrentMenu().setCurrentSubMenu(Game.getGlobal().getCurrentMenu().getSubMenuByName("options"));
					}
				}
			}
		}
	}
}
