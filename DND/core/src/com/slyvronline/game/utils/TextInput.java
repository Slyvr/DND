package com.slyvronline.game.utils;

import com.badlogic.gdx.Input.TextInputListener;

public class TextInput implements TextInputListener {

	private String inputType;
	private String text;
	
	@Override
	public void input(String text) {
		this.text = text;
	}

	@Override
	public void canceled() {
		
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
