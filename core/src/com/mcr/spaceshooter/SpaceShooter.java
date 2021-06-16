package com.mcr.spaceshooter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mcr.spaceshooter.UI.GameScreen;
import com.mcr.spaceshooter.UI.GarageScreen;

public class SpaceShooter extends Game {
	SpriteBatch batch;
	Texture img;
	Screen s;
	
	@Override
	public void create () {
		// TODO à enlever
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		s = new GameScreen();
		ScreenManager.getInstance().setGame(this);
		ScreenManager.getInstance().setScreen(s);
	}

	@Override
	public void render () {

		ScreenUtils.clear(1, 1, 1, 1);
		/*batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		 */
		ScreenManager.getInstance().render();
	}
	
	@Override
	public void dispose () {
		/*
		batch.dispose();
		img.dispose();
		 */
        super.dispose();
	}
}
