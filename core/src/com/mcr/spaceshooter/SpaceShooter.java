package com.mcr.spaceshooter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mcr.spaceshooter.Entity.Spaceship;
import com.mcr.spaceshooter.UI.GarageScreen;
import com.mcr.spaceshooter.Utils.Assets;

public class SpaceShooter extends Game {
	private Screen s;
	private Spaceship ship;
	private Assets assets;

//  https://stackoverflow.com/questions/45198075/how-to-handle-assetmanager-in-multiple-screens

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		assets = Assets.getInstance();

		// TODO à enlever


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
