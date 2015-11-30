package com.slyvronline.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Layer {

	private String name;
	private ArrayList<Ent> ents;
	
	public Layer(){
		ents = new ArrayList<Ent>();
	}

	public void render(SpriteBatch batch){
		for(Ent ent : ents){
			ent.render(batch);
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Ent> getEnts() {
		return ents;
	}

	public void setEnts(ArrayList<Ent> ents) {
		this.ents = ents;
	}
	
	
}
