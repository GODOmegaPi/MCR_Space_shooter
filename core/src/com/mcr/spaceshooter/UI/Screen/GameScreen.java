package com.mcr.spaceshooter.UI.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcr.spaceshooter.Utils.Asset;
import com.mcr.spaceshooter.Entity.Space;
import com.mcr.spaceshooter.Entity.Spaceship;
import com.mcr.spaceshooter.ScreenManager;
import com.mcr.spaceshooter.UI.SpaceRenderer;

/**
 * TODO GENERAUX
 * - Temps d'affichage des toasts
 */
public class GameScreen implements Screen {
    private SpriteBatch spriteBatch;
    private Space space;
    private SpaceRenderer renderer;

    public GameScreen(Spaceship spaceship) {
        space = new Space(spaceship);
        spriteBatch = new SpriteBatch();
        renderer = new SpaceRenderer(space);
    }

    @Override
    public void show() {
        Asset.getInstance().getAmbianceMusic().play();
    }

    private void update() {
        renderer.update();
        if (space.isGameOver()) {
            Asset.getInstance().getAmbianceMusic().stop();
            ScreenManager.getInstance().setScreen(new GameOverScreen(space.getScore()));
        }
    }

    @Override
    public void render(float delta) {
        update();

        renderer.render(spriteBatch);
    }

    @Override
    public void resize(int width, int height) {
        renderer.setSize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
