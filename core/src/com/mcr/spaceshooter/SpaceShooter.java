package com.mcr.spaceshooter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mcr.spaceshooter.Asset.Asset;
import com.mcr.spaceshooter.UI.GameScreen;
import com.mcr.spaceshooter.UI.GarageScreen;
import com.mcr.spaceshooter.Utils.Assets;
import com.mcr.spaceshooter.UI.Screen.GarageScreen;
import com.mcr.spaceshooter.Entity.Spaceship;

public class SpaceShooter extends Game {
	SpriteBatch batch;
	Screen s;
	
	private Spaceship ship;
	private Screen s;
	private Assets assets;
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		assets = Assets.getInstance();

		batch = new SpriteBatch();
		s = new GarageScreen();
		ScreenManager.getInstance().setGame(this);
		ScreenManager.getInstance().setScreen(s);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		ScreenManager.getInstance().render();
	}
	
	@Override
	public void dispose () {
        super.dispose();
        assets.dispose();
	}
}
