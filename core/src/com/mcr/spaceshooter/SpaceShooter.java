package com.mcr.spaceshooter;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mcr.spaceshooter.Utils.Asset;
import com.mcr.spaceshooter.UI.Screen.WelcomeScreen;

public class SpaceShooter extends Game {

	@Override
	public void create () {
		// TODO remove before production
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		Screen screen = new WelcomeScreen();
		ScreenManager.getInstance().setGame(this);
		ScreenManager.getInstance().setScreen(screen);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		ScreenManager.getInstance().render();
	}
	
	@Override
	public void dispose () {
        super.dispose();
		Asset.getInstance().unload();
	}
}
