package com.slyvronline.game.load;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.Menu;
import com.slyvronline.game.objects.Ent.State;
import com.slyvronline.game.objects.Img;
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
		
		//LAYER 1------------------------------------------------------------------------------------------------
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
		
		Ent btnBars = new Ent();
		btnBars.setName("btnBarsTile");
		btnBars.setImg(Game.getGlobal().getImgByName("bars"));
		btnBars.setPosBox(new Rectangle(btnWater.getPosBox().getX() + 32,
				0,
				btnBars.getImg().getTex().getWidth(),
				btnBars.getImg().getTex().getHeight()));
		btnBars.setType("layer1");
		ents.add(btnBars);
		
		Ent btnCarpet = new Ent();
		btnCarpet.setName("btnCarpetTile");
		btnCarpet.setImg(Game.getGlobal().getImgByName("carpet"));
		btnCarpet.setPosBox(new Rectangle(btnBars.getPosBox().getX() + 32,
				0,
				btnCarpet.getImg().getTex().getWidth(),
				btnCarpet.getImg().getTex().getHeight()));
		btnCarpet.setType("layer1");
		ents.add(btnCarpet);
		
		Ent btnGrass2 = new Ent();
		btnGrass2.setName("btnGrass2Tile");
		btnGrass2.setImg(Game.getGlobal().getImgByName("grass"));
		btnGrass2.setPosBox(new Rectangle(btnCarpet.getPosBox().getX() + 32,
				0,
				btnGrass2.getImg().getTex().getWidth(),
				btnGrass2.getImg().getTex().getHeight()));
		btnGrass2.setType("layer1");
		ents.add(btnGrass2);
		
		Ent btnStairs = new Ent();
		btnStairs.setName("btnStairsTile");
		btnStairs.setImg(Game.getGlobal().getImgByName("stairs"));
		btnStairs.setPosBox(new Rectangle(btnGrass2.getPosBox().getX() + 32,
				0,
				btnStairs.getImg().getTex().getWidth(),
				btnStairs.getImg().getTex().getHeight()));
		btnStairs.setType("layer1");
		ents.add(btnStairs);
		
		Ent btnStone2 = new Ent();
		btnStone2.setName("btnStone2Tile");
		btnStone2.setImg(Game.getGlobal().getImgByName("stone"));
		btnStone2.setPosBox(new Rectangle(btnStairs.getPosBox().getX() + 32,
				0,
				btnStone2.getImg().getTex().getWidth(),
				btnStone2.getImg().getTex().getHeight()));
		btnStone2.setType("layer1");
		ents.add(btnStone2);
		
		Ent btnWoodPlank = new Ent();
		btnWoodPlank.setName("btnWoodPlankTile");
		btnWoodPlank.setImg(Game.getGlobal().getImgByName("woodplank"));
		btnWoodPlank.setPosBox(new Rectangle(btnStone2.getPosBox().getX() + 32,
				0,
				btnWoodPlank.getImg().getTex().getWidth(),
				btnWoodPlank.getImg().getTex().getHeight()));
		btnWoodPlank.setType("layer1");
		ents.add(btnWoodPlank);
		
		//LAYER 2------------------------------------------------------------------------------------------------------
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
		
		Ent btnBarrel = new Ent();
		btnBarrel.setName("btnBarrelTile");
		btnBarrel.setImg(Game.getGlobal().getImgByName("barrel"));
		btnBarrel.setPosBox(new Rectangle(btnTree.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnBarrel.getImg().getTex().getWidth(),
				btnBarrel.getImg().getTex().getHeight()));
		btnBarrel.setType("layer2");
		ents.add(btnBarrel);
		
		Ent btnBed = new Ent();
		btnBed.setName("btnBedTile");
		btnBed.setImg(Game.getGlobal().getImgByName("bed"));
		btnBed.setPosBox(new Rectangle(btnBarrel.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnBed.getImg().getTex().getWidth(),
				btnBed.getImg().getTex().getHeight()));
		btnBed.setType("layer2");
		ents.add(btnBed);
		
		Ent btnBox = new Ent();
		btnBox.setName("btnBoxTile");
		btnBox.setImg(Game.getGlobal().getImgByName("box"));
		btnBox.setPosBox(new Rectangle(btnBed.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnBox.getImg().getTex().getWidth(),
				btnBox.getImg().getTex().getHeight()));
		btnBox.setType("layer2");
		ents.add(btnBox);
		
		Ent btnChairLeft = new Ent();
		btnChairLeft.setName("btnChairLeftTile");
		btnChairLeft.setImg(Game.getGlobal().getImgByName("chairleft"));
		btnChairLeft.setPosBox(new Rectangle(btnBox.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnChairLeft.getImg().getTex().getWidth(),
				btnChairLeft.getImg().getTex().getHeight()));
		btnChairLeft.setType("layer2");
		ents.add(btnChairLeft);
		
		Ent btnChairRight = new Ent();
		btnChairRight.setName("btnChairRightTile");
		btnChairRight.setImg(Game.getGlobal().getImgByName("chairright"));
		btnChairRight.setPosBox(new Rectangle(btnChairLeft.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnChairRight.getImg().getTex().getWidth(),
				btnChairRight.getImg().getTex().getHeight()));
		btnChairRight.setType("layer2");
		ents.add(btnChairRight);
		
		Ent btnCity = new Ent();
		btnCity.setName("btnCityTile");
		btnCity.setImg(Game.getGlobal().getImgByName("city"));
		btnCity.setPosBox(new Rectangle(btnChairRight.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnCity.getImg().getTex().getWidth(),
				btnCity.getImg().getTex().getHeight()));
		btnCity.setType("layer2");
		ents.add(btnCity);
		
		Ent btnDoor = new Ent();
		btnDoor.setName("btnDoorTile");
		btnDoor.setImg(Game.getGlobal().getImgByName("door"));
		btnDoor.setPosBox(new Rectangle(btnCity.getPosBox().getX() + 64,
				btnDelete2.getPosBox().getY(),
				btnDoor.getImg().getTex().getWidth(),
				btnDoor.getImg().getTex().getHeight()));
		btnDoor.setType("layer2");
		ents.add(btnDoor);
		
		Ent btnDoor2 = new Ent();
		btnDoor2.setName("btnDoor2Tile");
		btnDoor2.setImg(Game.getGlobal().getImgByName("door2"));
		btnDoor2.setPosBox(new Rectangle(btnDoor.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnDoor2.getImg().getTex().getWidth(),
				btnDoor2.getImg().getTex().getHeight()));
		btnDoor2.setType("layer2");
		ents.add(btnDoor2);
		
		Ent btnFire = new Ent();
		btnFire.setName("btnFireTile");
		btnFire.setImg(Game.getGlobal().getImgByName("fire"));
		btnFire.setPosBox(new Rectangle(btnDoor2.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnFire.getImg().getTex().getWidth(),
				btnFire.getImg().getTex().getHeight()));
		btnFire.setType("layer2");
		ents.add(btnFire);
		
		Ent btnFlower2 = new Ent();
		btnFlower2.setName("btnFlower2Tile");
		btnFlower2.setImg(Game.getGlobal().getImgByName("flower2"));
		btnFlower2.setPosBox(new Rectangle(btnFire.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnFlower2.getImg().getTex().getWidth(),
				btnFlower2.getImg().getTex().getHeight()));
		btnFlower2.setType("layer2");
		ents.add(btnFlower2);
		
		Ent btnMountain1 = new Ent();
		btnMountain1.setName("btnMountain1Tile");
		btnMountain1.setImg(Game.getGlobal().getImgByName("mountain1"));
		btnMountain1.setPosBox(new Rectangle(btnFlower2.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnMountain1.getImg().getTex().getWidth(),
				btnMountain1.getImg().getTex().getHeight()));
		btnMountain1.setType("layer2");
		ents.add(btnMountain1);
		
		Ent btnMountain2 = new Ent();
		btnMountain2.setName("btnMountain2Tile");
		btnMountain2.setImg(Game.getGlobal().getImgByName("mountain2"));
		btnMountain2.setPosBox(new Rectangle(btnMountain1.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnMountain2.getImg().getTex().getWidth(),
				btnMountain2.getImg().getTex().getHeight()));
		btnMountain2.setType("layer2");
		ents.add(btnMountain2);
		
		Ent btnMountain3 = new Ent();
		btnMountain3.setName("btnMountain3Tile");
		btnMountain3.setImg(Game.getGlobal().getImgByName("mountain3"));
		btnMountain3.setPosBox(new Rectangle(btnMountain2.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnMountain3.getImg().getTex().getWidth(),
				btnMountain3.getImg().getTex().getHeight()));
		btnMountain3.setType("layer2");
		ents.add(btnMountain3);
		
		Ent btnLadder = new Ent();
		btnLadder.setName("btnLadderTile");
		btnLadder.setImg(Game.getGlobal().getImgByName("ladder"));
		btnLadder.setPosBox(new Rectangle(btnMountain3.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnLadder.getImg().getTex().getWidth(),
				btnLadder.getImg().getTex().getHeight()));
		btnLadder.setType("layer2");
		ents.add(btnLadder);
		
		Ent btnRock2 = new Ent();
		btnRock2.setName("btnRock2Tile");
		btnRock2.setImg(Game.getGlobal().getImgByName("rock2"));
		btnRock2.setPosBox(new Rectangle(btnLadder.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnRock2.getImg().getTex().getWidth(),
				btnRock2.getImg().getTex().getHeight()));
		btnRock2.setType("layer2");
		ents.add(btnRock2);
		
		Ent btnStairsDown = new Ent();
		btnStairsDown.setName("btnStairsDownTile");
		btnStairsDown.setImg(Game.getGlobal().getImgByName("stairsdown"));
		btnStairsDown.setPosBox(new Rectangle(btnRock2.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnStairsDown.getImg().getTex().getWidth(),
				btnStairsDown.getImg().getTex().getHeight()));
		btnStairsDown.setType("layer2");
		ents.add(btnStairsDown);
		
		Ent btnStairsUp = new Ent();
		btnStairsUp.setName("btnStairsUpTile");
		btnStairsUp.setImg(Game.getGlobal().getImgByName("stairsup"));
		btnStairsUp.setPosBox(new Rectangle(btnStairsDown.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnStairsUp.getImg().getTex().getWidth(),
				btnStairsUp.getImg().getTex().getHeight()));
		btnStairsUp.setType("layer2");
		ents.add(btnStairsUp);
		
		Ent btnTable = new Ent();
		btnTable.setName("btnTableTile");
		btnTable.setImg(Game.getGlobal().getImgByName("table"));
		btnTable.setPosBox(new Rectangle(btnStairsUp.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnTable.getImg().getTex().getWidth(),
				btnTable.getImg().getTex().getHeight()));
		btnTable.setType("layer2");
		ents.add(btnTable);
		
		Ent btnTown = new Ent();
		btnTown.setName("btnTownTile");
		btnTown.setImg(Game.getGlobal().getImgByName("town"));
		btnTown.setPosBox(new Rectangle(btnTable.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnTown.getImg().getTex().getWidth(),
				btnTown.getImg().getTex().getHeight()));
		btnTown.setType("layer2");
		ents.add(btnTown);
		
		Ent btnWell = new Ent();
		btnWell.setName("btnWellTile");
		btnWell.setImg(Game.getGlobal().getImgByName("well"));
		btnWell.setPosBox(new Rectangle(btnTown.getPosBox().getX() + 32,
				btnDelete2.getPosBox().getY(),
				btnWell.getImg().getTex().getWidth(),
				btnWell.getImg().getTex().getHeight()));
		btnWell.setType("layer2");
		ents.add(btnWell);
		
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
		
		//Load character lists
		float x=bg.getPosBox().getX();
		float y=bg.getPosBox().getY();
		for(Img img : Game.getGlobal().getImgs()){
			if (img.getPath().contains("character")){
				Ent character = new Ent();
				character.setName(img.getName());
				character.setImg(img);
				character.setPosBox(new Rectangle(x,
						y,
						character.getImg().getTex().getWidth(),
						character.getImg().getTex().getHeight()));
				subEnts.add(character);
				
				x += character.getImg().getTex().getWidth();
				if (x >= bg.getPosBox().getX()+bg.getPosBox().getWidth()){
					y += character.getImg().getTex().getHeight();
					x = bg.getPosBox().getX();
				}
			}
		}
		
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
