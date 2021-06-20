package com.mcr.spaceshooter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mcr.spaceshooter.SpaceShooter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// Dimensions de la fenêtre
		config.height = 1000;
		config.width = 800;
		config.fullscreen = false;
		// On interdit le redimensionnement de la fenêtre.
		config.resizable = false;
		new LwjglApplication(new SpaceShooter(), config);
	}
}
