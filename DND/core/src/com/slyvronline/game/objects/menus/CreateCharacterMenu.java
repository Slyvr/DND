package com.slyvronline.game.objects.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.Menu;
import com.slyvronline.game.objects.Ent.State;
import com.slyvronline.game.utils.Utils;

public class CreateCharacterMenu extends Menu {

	public CreateCharacterMenu(){
		
	}
	
	public void update(){
		super.updateButtonHover();
		updateCloseMenu();
		updateCreateCharacter();
	}
	
	public void updateCloseMenu(){
		if (Gdx.input.justTouched()){
			Rectangle mousePos = Utils.getMenuMousePos();
			Ent btnReturn = this.getEntByName("btnReturn");
			if (mousePos.overlaps(btnReturn.getPosBox())){
				Game.getGlobal().getCurrentMenu().setCurrentSubMenu(null);
			}
		}
	}
	
	public void updateCreateCharacter(){
		if (Gdx.input.justTouched()){
			Rectangle mousePos = Utils.getMenuMousePos();
			for(Ent e : this.getEnts()){
				if (!e.getName().contains("btn") && e.getImg() != null && !e.getImg().getName().equals("whitecover")){
					if (mousePos.overlaps(e.getPosBox())){
						int tileSize = Game.getGlobal().getGame().getWorld().getTileSize();
						Ent character = new Ent();
						character.setName(e.getImg().getName());
						character.setImg(e.getImg());
						character.setPosBox(new Rectangle(mousePos.getX(),mousePos.getY(),tileSize,tileSize));
						character.setState(State.SELECTED);
						Game.getGlobal().getGame().getWorld().getCharacters().add(character);
						Game.getGlobal().getCurrentMenu().setCurrentSubMenu(null);
						Game.getGlobal().getGame().getWorld().setSubMenuJustLeft(true);
						break;
					}
				}
			}
		}
	}
}
