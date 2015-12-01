package com.slyvronline.game.load;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.Menu;
import com.slyvronline.game.objects.Ent.State;
import com.slyvronline.game.objects.menus.CreateCharacterMenu;
import com.slyvronline.game.objects.menus.GameMenu;
import com.slyvronline.game.objects.menus.MainMenu;
import com.slyvronline.game.objects.menus.MusicMenu;
import com.slyvronline.game.objects.menus.PauseMenu;
import com.slyvronline.game.objects.menus.SplashMenu;

public class LoadMenus {

	public static void load(){
		ArrayList<Menu> menus = new ArrayList<Menu>();
		
		menus.add(loadSplashMenu());
		menus.add(loadMainMenu());
		menus.add(loadGameMenu());
		menus.add(loadMusicMenu());
		
		
		Game.getGlobal().setMenus(menus);
		Game.getGlobal().setCurrentMenu(menus.get(0));
		Game.getGlobal().setMusicMenu(Game.getGlobal().getMenuByName("music"));
	}
	
	public static Menu loadSplashMenu(){
		SplashMenu menu = new SplashMenu();
		
		menu.setName("splash");
		
		ArrayList<Ent> ents = new ArrayList<Ent>();

		Ent logo = new Ent();
		logo.setName("logo");
		logo.setImg(Game.getGlobal().getImgByName("Logo2_white"));
		logo.setPosBox(new Rectangle((Gdx.graphics.getWidth()/2)-(logo.getImg().getTex().getWidth()/2),
				(Gdx.graphics.getHeight()/2)-(logo.getImg().getTex().getHeight()/2),
				logo.getImg().getTex().getWidth(),
				logo.getImg().getTex().getHeight()));
		ents.add(logo);
		
		menu.setEnts(ents);
		
		return menu;
	}
	
	public static Menu loadMainMenu(){
		MainMenu menu = new MainMenu();
		menu.setName("main");
		
		ArrayList<Ent> ents = new ArrayList<Ent>();
		
		Ent logo = new Ent();
		logo.setName("logo");
		logo.setImg(Game.getGlobal().getImgByName("dnd"));
		logo.setPosBox(new Rectangle((Gdx.graphics.getWidth()/2)-(logo.getImg().getTex().getWidth()/2),
				Gdx.graphics.getHeight()-logo.getImg().getTex().getHeight()-100,
				logo.getImg().getTex().getWidth(),
				logo.getImg().getTex().getHeight()));
		ents.add(logo);
		
		Ent btnPlay = new Ent();
		btnPlay.setName("btnPlay");
		btnPlay.setImg(Game.getGlobal().getImgByName("btnPlay"));
		btnPlay.setPosBox(new Rectangle((Gdx.graphics.getWidth()/2)-(btnPlay.getImg().getTex().getWidth()/2),
				300 - btnPlay.getImg().getTex().getHeight() - 5,
				btnPlay.getImg().getTex().getWidth(),
				btnPlay.getImg().getTex().getHeight()));
		ents.add(btnPlay);
		
		Ent btnExit = new Ent();
		btnExit.setName("btnExit");
		btnExit.setImg(Game.getGlobal().getImgByName("btnExit"));
		btnExit.setPosBox(new Rectangle(btnPlay.getPosBox().getX(),
				btnPlay.getPosBox().getY() - btnExit.getImg().getTex().getHeight() - 5,
				btnExit.getImg().getTex().getWidth(),
				btnExit.getImg().getTex().getHeight()));
		ents.add(btnExit);
		
		menu.setEnts(ents);
		
		//Setup sub menus
		ArrayList<Menu> subMenus = new ArrayList<Menu>();
		menu.setSubMenus(subMenus);
		
		return menu;
	}
	
	public static Menu loadGameMenu(){
		GameMenu menu = new GameMenu();
		menu.setName("game");
		
		ArrayList<Ent> ents = new ArrayList<Ent>();
		
		Ent debugTooltip = new Ent();
		debugTooltip.setName("debugTooltip");
		debugTooltip.setText("FPS: ");
		debugTooltip.setFont(Game.getGlobal().getFontByName("Calibri16"));
		debugTooltip.setPosBox(new Rectangle(
				Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight(),0,0));
		ents.add(debugTooltip);
		
		Ent btnSave = new Ent();
		btnSave.setName("btnSave");
		btnSave.setImg(Game.getGlobal().getImgByName("btnSave"));
		btnSave.setPosBox(new Rectangle(32,
				Gdx.graphics.getHeight() - btnSave.getImg().getTex().getHeight(),
				btnSave.getImg().getTex().getWidth(),
				btnSave.getImg().getTex().getHeight()));
		ents.add(btnSave);
		
		Ent btnLoad = new Ent();
		btnLoad.setName("btnLoad");
		btnLoad.setImg(Game.getGlobal().getImgByName("btnLoad"));
		btnLoad.setPosBox(new Rectangle(32 + btnSave.getImg().getTex().getWidth() + 10,
				Gdx.graphics.getHeight() - btnLoad.getImg().getTex().getHeight(),
				btnLoad.getImg().getTex().getWidth(),
				btnLoad.getImg().getTex().getHeight()));
		ents.add(btnLoad);
		
		Ent btnAddCharacter = new Ent();
		btnAddCharacter.setName("btnAddCharacter");
		btnAddCharacter.setImg(Game.getGlobal().getImgByName("btnAdd"));
		btnAddCharacter.setPosBox(new Rectangle(32,
				Gdx.graphics.getHeight() - btnAddCharacter.getImg().getTex().getHeight()*3,
				btnAddCharacter.getImg().getTex().getWidth(),
				btnAddCharacter.getImg().getTex().getHeight()));
		ents.add(btnAddCharacter);
		
		//LAYER 1
		Ent btnDelete = new Ent();
		btnDelete.setName("btnDeleteTile");
		btnDelete.setImg(Game.getGlobal().getImgByName("delete"));
		btnDelete.setPosBox(new Rectangle(32,
				0,
				btnDelete.getImg().getTex().getWidth(),
				btnDelete.getImg().getTex().getHeight()));
		btnDelete.setType("layer1");
		btnDelete.setState(State.SELECTED);
		ents.add(btnDelete);
		
		Ent btnDirt = new Ent();
		btnDirt.setName("btnDirtTile");
		btnDirt.setImg(Game.getGlobal().getImgByName("dirt"));
		btnDirt.setPosBox(new Rectangle(btnDelete.getPosBox().getX() + 32,
				0,
				btnDirt.getImg().getTex().getWidth(),
				btnDirt.getImg().getTex().getHeight()));
		btnDirt.setType("layer1");
		ents.add(btnDirt);
		
		Ent btnStone = new Ent();
		btnStone.setName("btnStoneTile");
		btnStone.setImg(Game.getGlobal().getImgByName("grassystone"));
		btnStone.setPosBox(new Rectangle(btnDirt.getPosBox().getX() + 32,
				0,
				btnStone.getImg().getTex().getWidth(),
				btnStone.getImg().getTex().getHeight()));
		btnStone.setType("layer1");
		ents.add(btnStone);
		
		Ent btnGrass = new Ent();
		btnGrass.setName("btnGrassTile");
		btnGrass.setImg(Game.getGlobal().getImgByName("tallgrass"));
		btnGrass.setPosBox(new Rectangle(btnStone.getPosBox().getX() + 32,
				0,
				btnGrass.getImg().getTex().getWidth(),
				btnGrass.getImg().getTex().getHeight()));
		btnGrass.setType("layer1");
		ents.add(btnGrass);
		
		Ent btnWater = new Ent();
		btnWater.setName("btnWaterTile");
		btnWater.setImg(Game.getGlobal().getImgByName("water"));
		btnWater.setPosBox(new Rectangle(btnGrass.getPosBox().getX() + 32,
				0,
				btnWater.getImg().getTex().getWidth(),
				btnWater.getImg().getTex().getHeight()));
		btnWater.setType("layer1");
		ents.add(btnWater);
		
		//LAYER 2
		Ent btnDelete2 = new Ent();
		btnDelete2.setName("btnDelete2Tile");
		btnDelete2.setImg(Game.getGlobal().getImgByName("delete"));
		btnDelete2.setPosBox(new Rectangle(32,
				btnDelete2.getPosBox().getY() + btnDelete2.getImg().getTex().getHeight(),
				btnDelete2.getImg().getTex().getWidth(),
				btnDelete2.getImg().getTex().getHeight()));
		btnDelete2.setType("layer2");
		btnDelete2.setState(State.SELECTED);
		ents.add(btnDelete2);
		
		Ent btnTreeStump = new Ent();
		btnTreeStump.setName("btnTreeStumpTile");
		btnTreeStump.setImg(Game.getGlobal().getImgByName("treestump"));
		btnTreeStump.setPosBox(new Rectangle(btnDelete2.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnTreeStump.getImg().getTex().getWidth(),
				btnTreeStump.getImg().getTex().getHeight()));
		btnTreeStump.setType("layer2");
		ents.add(btnTreeStump);
		
		Ent btnFern = new Ent();
		btnFern.setName("btnFernTile");
		btnFern.setImg(Game.getGlobal().getImgByName("fern"));
		btnFern.setPosBox(new Rectangle(btnTreeStump.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnFern.getImg().getTex().getWidth(),
				btnFern.getImg().getTex().getHeight()));
		btnFern.setType("layer2");
		ents.add(btnFern);
		
		Ent btnBush = new Ent();
		btnBush.setName("btnBushTile");
		btnBush.setImg(Game.getGlobal().getImgByName("bush"));
		btnBush.setPosBox(new Rectangle(btnFern.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnBush.getImg().getTex().getWidth(),
				btnBush.getImg().getTex().getHeight()));
		btnBush.setType("layer2");
		ents.add(btnBush);
		
		Ent btnFlower = new Ent();
		btnFlower.setName("btnFlowerTile");
		btnFlower.setImg(Game.getGlobal().getImgByName("flower"));
		btnFlower.setPosBox(new Rectangle(btnBush.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnFlower.getImg().getTex().getWidth(),
				btnFlower.getImg().getTex().getHeight()));
		btnFlower.setType("layer2");
		ents.add(btnFlower);
		
		Ent btnPineTree = new Ent();
		btnPineTree.setName("btnPineTreeTile");
		btnPineTree.setImg(Game.getGlobal().getImgByName("pinetree"));
		btnPineTree.setPosBox(new Rectangle(btnFlower.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnPineTree.getImg().getTex().getWidth(),
				btnPineTree.getImg().getTex().getHeight()));
		btnPineTree.setType("layer2");
		ents.add(btnPineTree);
		
		Ent btnRock = new Ent();
		btnRock.setName("btnRockTile");
		btnRock.setImg(Game.getGlobal().getImgByName("rock"));
		btnRock.setPosBox(new Rectangle(btnPineTree.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnRock.getImg().getTex().getWidth(),
				btnRock.getImg().getTex().getHeight()));
		btnRock.setType("layer2");
		ents.add(btnRock);
		
		Ent btnTree = new Ent();
		btnTree.setName("btnTreeTile");
		btnTree.setImg(Game.getGlobal().getImgByName("tree"));
		btnTree.setPosBox(new Rectangle(btnRock.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnTree.getImg().getTex().getWidth(),
				btnTree.getImg().getTex().getHeight()));
		btnTree.setType("layer2");
		ents.add(btnTree);
		
		Ent btnSelect = new Ent();
		btnSelect.setName("btnSelect");
		btnSelect.setImg(Game.getGlobal().getImgByName("select"));
		btnSelect.setPosBox(new Rectangle(btnDelete.getPosBox().getX(),
				btnDelete.getPosBox().getY(),
				btnSelect.getImg().getTex().getWidth(),
				btnSelect.getImg().getTex().getHeight()));
		btnSelect.setColor(Color.BLUE);
		ents.add(btnSelect);
		
		menu.setEnts(ents);
		
		ArrayList<Menu> subMenus = new ArrayList<Menu>();
		
		subMenus.add(loadPauseMenu());
		subMenus.add(loadCreateCharacterMenu());
		
		menu.setSubMenus(subMenus);
		
		return menu;
	}
	
	public static Menu loadMusicMenu(){
		MusicMenu menu = new MusicMenu();
		menu.setName("music");
		
		ArrayList<Ent> ents = new ArrayList<Ent>();
		
		Ent musicbg = new Ent();
		musicbg.setName("bg");
		musicbg.setImg(Game.getGlobal().getImgByName("music_bg"));
		musicbg.setPosBox(new Rectangle(Gdx.graphics.getWidth() - musicbg.getImg().getTex().getWidth(),
				0,
				musicbg.getImg().getTex().getWidth(),
				musicbg.getImg().getTex().getHeight()));
		ents.add(musicbg);
		
		float commonY=14;
		float commonXDiff = 44;
		
		Ent musicprev = new Ent();
		musicprev.setName("musicprev");
		musicprev.setImg(Game.getGlobal().getImgByName("music_prev"));
		musicprev.setPosBox(new Rectangle(musicbg.getPosBox().getX() + commonXDiff,
				commonY,
				musicprev.getImg().getTex().getWidth(),
				musicprev.getImg().getTex().getHeight()));
		ents.add(musicprev);
		
		Ent musicplay = new Ent();
		musicplay.setName("musicplay");
		musicplay.setImg(Game.getGlobal().getImgByName("music_pause"));
		musicplay.setPosBox(new Rectangle(musicprev.getPosBox().getX() + commonXDiff,
				commonY,
				musicplay.getImg().getTex().getWidth(),
				musicplay.getImg().getTex().getHeight()));
		ents.add(musicplay);
		
		Ent musicnext = new Ent();
		musicnext.setName("musicnext");
		musicnext.setImg(Game.getGlobal().getImgByName("music_next"));
		musicnext.setPosBox(new Rectangle(musicplay.getPosBox().getX() + commonXDiff,
				commonY,
				musicnext.getImg().getTex().getWidth(),
				musicnext.getImg().getTex().getHeight()));
		ents.add(musicnext);
		
		Ent musicinfo = new Ent();
		musicinfo.setName("musicinfo");
		musicinfo.setImg(Game.getGlobal().getImgByName("music_info"));
		musicinfo.setPosBox(new Rectangle(musicnext.getPosBox().getX() + commonXDiff,
				commonY,
				musicinfo.getImg().getTex().getWidth(),
				musicinfo.getImg().getTex().getHeight()));
		ents.add(musicinfo);
		
		menu.setEnts(ents);
		
		return menu;
	}
	
	public static Menu loadCreateCharacterMenu(){
		CreateCharacterMenu menu = new CreateCharacterMenu();
		menu.setName("createcharacter");
		
		ArrayList<Ent> subEnts = new ArrayList<Ent>();
		
		Ent whitecover = new Ent();
		whitecover.setName("whitecover");
		whitecover.setImg(Game.getGlobal().getImgByName("whitecover"));
		whitecover.setPosBox(new Rectangle(0,0,
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()));
		subEnts.add(whitecover);
		
		Ent btnReturn = new Ent();
		btnReturn.setName("btnReturn");
		btnReturn.setImg(Game.getGlobal().getImgByName("btnReturn"));
		btnReturn.setPosBox(new Rectangle((Gdx.graphics.getWidth()/2)-(btnReturn.getImg().getTex().getWidth()/2),
				32,
				btnReturn.getImg().getTex().getWidth(),
				btnReturn.getImg().getTex().getHeight()));
		subEnts.add(btnReturn);
		
		Ent bg = new Ent();
		bg.setName("bg");
		bg.setImg(Game.getGlobal().getImgByName("whitecover"));
		bg.setPosBox(new Rectangle((Gdx.graphics.getWidth()/2)-(512/2),
				(Gdx.graphics.getHeight()/2)-(512/2),
				512,
				512));
		bg.setColor(Color.BLUE);
		subEnts.add(bg);
		
		Ent bandit1 = new Ent();
		bandit1.setName("bandit1");
		bandit1.setImg(Game.getGlobal().getImgByName("bandit1"));
		bandit1.setPosBox(new Rectangle(bg.getPosBox().getX(),
				bg.getPosBox().getY(),
				bandit1.getImg().getTex().getWidth(),
				bandit1.getImg().getTex().getHeight()));
		subEnts.add(bandit1);
		
		Ent dragon1 = new Ent();
		dragon1.setName("dragon1");
		dragon1.setImg(Game.getGlobal().getImgByName("dragon1"));
		dragon1.setPosBox(new Rectangle(bg.getPosBox().getX()+dragon1.getImg().getTex().getWidth(),
				bg.getPosBox().getY(),
				dragon1.getImg().getTex().getWidth(),
				dragon1.getImg().getTex().getHeight()));
		subEnts.add(dragon1);
		
		Ent dwarf1 = new Ent();
		dwarf1.setName("dwarf1");
		dwarf1.setImg(Game.getGlobal().getImgByName("dwarf1"));
		dwarf1.setPosBox(new Rectangle(dragon1.getPosBox().getX()+dwarf1.getImg().getTex().getWidth(),
				bg.getPosBox().getY(),
				dwarf1.getImg().getTex().getWidth(),
				dwarf1.getImg().getTex().getHeight()));
		subEnts.add(dwarf1);
		
		Ent dwarf2 = new Ent();
		dwarf2.setName("dwarf2");
		dwarf2.setImg(Game.getGlobal().getImgByName("dwarf2"));
		dwarf2.setPosBox(new Rectangle(dwarf1.getPosBox().getX()+dwarf2.getImg().getTex().getWidth(),
				bg.getPosBox().getY(),
				dwarf2.getImg().getTex().getWidth(),
				dwarf2.getImg().getTex().getHeight()));
		subEnts.add(dwarf2);
		
		Ent elf1 = new Ent();
		elf1.setName("elf1");
		elf1.setImg(Game.getGlobal().getImgByName("elf1"));
		elf1.setPosBox(new Rectangle(dwarf2.getPosBox().getX()+elf1.getImg().getTex().getWidth(),
				bg.getPosBox().getY(),
				elf1.getImg().getTex().getWidth(),
				elf1.getImg().getTex().getHeight()));
		subEnts.add(elf1);
		
		Ent goblin1 = new Ent();
		goblin1.setName("goblin1");
		goblin1.setImg(Game.getGlobal().getImgByName("goblin1"));
		goblin1.setPosBox(new Rectangle(elf1.getPosBox().getX()+goblin1.getImg().getTex().getWidth(),
				bg.getPosBox().getY(),
				goblin1.getImg().getTex().getWidth(),
				goblin1.getImg().getTex().getHeight()));
		subEnts.add(goblin1);
		
		Ent halfling1 = new Ent();
		halfling1.setName("halfling1");
		halfling1.setImg(Game.getGlobal().getImgByName("halfling1"));
		halfling1.setPosBox(new Rectangle(goblin1.getPosBox().getX()+halfling1.getImg().getTex().getWidth(),
				bg.getPosBox().getY(),
				halfling1.getImg().getTex().getWidth(),
				halfling1.getImg().getTex().getHeight()));
		subEnts.add(halfling1);
		
		Ent halfling2 = new Ent();
		halfling2.setName("halfling2");
		halfling2.setImg(Game.getGlobal().getImgByName("halfling2"));
		halfling2.setPosBox(new Rectangle(halfling1.getPosBox().getX()+halfling2.getImg().getTex().getWidth(),
				bg.getPosBox().getY(),
				halfling2.getImg().getTex().getWidth(),
				halfling2.getImg().getTex().getHeight()));
		subEnts.add(halfling2);
		
		Ent knight1 = new Ent();
		knight1.setName("knight1");
		knight1.setImg(Game.getGlobal().getImgByName("knight1"));
		knight1.setPosBox(new Rectangle(bg.getPosBox().getX(),
				bg.getPosBox().getY()+knight1.getImg().getTex().getHeight(),
				knight1.getImg().getTex().getWidth(),
				knight1.getImg().getTex().getHeight()));
		subEnts.add(knight1);
		
		Ent ogre1 = new Ent();
		ogre1.setName("ogre1");
		ogre1.setImg(Game.getGlobal().getImgByName("ogre1"));
		ogre1.setPosBox(new Rectangle(knight1.getPosBox().getX()+ogre1.getImg().getTex().getWidth(),
				bg.getPosBox().getY()+knight1.getImg().getTex().getHeight(),
				ogre1.getImg().getTex().getWidth(),
				ogre1.getImg().getTex().getHeight()));
		subEnts.add(ogre1);
		
		Ent skeleton1 = new Ent();
		skeleton1.setName("skeleton1");
		skeleton1.setImg(Game.getGlobal().getImgByName("skeleton1"));
		skeleton1.setPosBox(new Rectangle(ogre1.getPosBox().getX()+skeleton1.getImg().getTex().getWidth(),
				bg.getPosBox().getY()+knight1.getImg().getTex().getHeight(),
				skeleton1.getImg().getTex().getWidth(),
				skeleton1.getImg().getTex().getHeight()));
		subEnts.add(skeleton1);
		
		menu.setEnts(subEnts);
		
		return menu;
	}
	
	public static Menu loadPauseMenu(){
		PauseMenu pause = new PauseMenu();
		pause.setName("pause");
		
		ArrayList<Ent> subEnts = new ArrayList<Ent>();
		
		Ent whitecover = new Ent();
		whitecover.setName("whitecover");
		whitecover.setImg(Game.getGlobal().getImgByName("whitecover"));
		whitecover.setPosBox(new Rectangle(0,0,
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()));
		subEnts.add(whitecover);
		
		Ent btnReturn = new Ent();
		btnReturn.setName("btnReturn");
		btnReturn.setImg(Game.getGlobal().getImgByName("btnReturn"));
		btnReturn.setPosBox(new Rectangle((Gdx.graphics.getWidth()/2)-(btnReturn.getImg().getTex().getWidth()/2),
				(Gdx.graphics.getHeight()/2)-(btnReturn.getImg().getTex().getHeight()/2),
				btnReturn.getImg().getTex().getWidth(),
				btnReturn.getImg().getTex().getHeight()));
		subEnts.add(btnReturn);
		
		Ent btnOptions = new Ent();
		btnOptions.setName("btnOptions");
		btnOptions.setImg(Game.getGlobal().getImgByName("btnOptions"));
		btnOptions.setPosBox(new Rectangle((Gdx.graphics.getWidth()/2)-(btnOptions.getImg().getTex().getWidth()/2),
				btnReturn.getPosBox().getY() - btnOptions.getImg().getTex().getHeight() - 10,
				btnOptions.getImg().getTex().getWidth(),
				btnOptions.getImg().getTex().getHeight()));
		subEnts.add(btnOptions);
		
		Ent btnMainMenu = new Ent();
		btnMainMenu.setName("btnMainMenu");
		btnMainMenu.setImg(Game.getGlobal().getImgByName("btnMainMenu"));
		btnMainMenu.setPosBox(new Rectangle((Gdx.graphics.getWidth()/2)-(btnMainMenu.getImg().getTex().getWidth()/2),
				btnOptions.getPosBox().getY() - btnMainMenu.getImg().getTex().getHeight() - 10,
				btnMainMenu.getImg().getTex().getWidth(),
				btnMainMenu.getImg().getTex().getHeight()));
		subEnts.add(btnMainMenu);
		
		pause.setEnts(subEnts);
		
		return pause;
	}
}
