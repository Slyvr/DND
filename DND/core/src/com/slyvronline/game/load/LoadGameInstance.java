package com.slyvronline.game.load;

import com.slyvronline.game.Game;
import com.slyvronline.game.objects.GameInstance;

public class LoadGameInstance {

	public static void load(String gamemode){
		if (gamemode.equals("game")){
			GameInstance game = new GameInstance();
			game.setGamemode(gamemode);
			game.setGamemode("game");
			Game.getGlobal().setGame(game);
		}
	}
}
