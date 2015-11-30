package com.slyvronline.game.load;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Img;

public class LoadImgs {

	private static ArrayList<Img> imgs;
	
	public static void load(){
		imgs = new ArrayList<Img>();
		
		FileHandle fh = Gdx.files.internal("data/imagesources.txt");
		
		loadImgsFromText(fh.readString());
		
		Game.getGlobal().setImgs(imgs);
	}
	
	public static void loadImgsFromText(String text){
		for(String path : text.split("\r\n")){
			String name = path.split(".png")[0];
			name = name.substring(name.lastIndexOf("/")+1, name.length());
			Texture tex = new Texture(Gdx.files.internal(path));
			imgs.add(new Img(tex,name));
		}
	}
}
