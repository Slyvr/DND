package com.slyvronline.game.objects.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.load.LoadGameInstance;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.Menu;

public class MainMenu extends Menu {

	public MainMenu(){
		
	}
	
	public void render(SpriteBatch batch){
		for(Ent e : this.getEnts()){
			e.render(batch);
		}
		
		if (this.getCurrentSubMenu() != null){
			getCurrentSubMenu().render(batch);
		}
	}
	
	public void update(){
		if (getCurrentSubMenu() != null){
			getCurrentSubMenu().update();
		}
		else{
			super.updateButtonHover();
			updateButtonClick();
		}
	}
	
	public void updateButtonClick(){
		if (Gdx.input.justTouched()){
			Rectangle mousePos = new Rectangle(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY(),1,1);
			
			for(Ent e : getEnts()){
				if (e.getName().contains("btn") && mousePos.overlaps(e.getPosBox())){
					Game.getGlobal().getSoundByName("click").getSound().play();
					if (e.getName().equals("btnExit")){
						Gdx.app.exit();
					}
					else if (e.getName().equals("btnPlay")){
						Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("game"));
						LoadGameInstance.load("game");
					}
				}
			}
		}
	}
}
