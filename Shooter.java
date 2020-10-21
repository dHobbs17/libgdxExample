package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.MainMenuScreen;

public class Shooter extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public AssetManager assets;
	//public OrthographicCamera camera;

	public static float	SCREEN_WIDTH;
	public static float	SCREEN_HEIGHT;

	public void create() {
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();

		batch = new SpriteBatch();
		font = new BitmapFont();
		assets = new AssetManager();
		//camera = new OrthographicCamera();

		this.setScreen(new MainMenuScreen(this));
	}
	public void render() {
		super.render(); //important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}

