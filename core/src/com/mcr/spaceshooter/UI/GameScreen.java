package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mcr.spaceshooter.Entity.Space;
import com.mcr.spaceshooter.ScreenManager;
import com.badlogic.gdx.audio.Music;


/**
 * TODO:
 * - Espace torique (à voir)
 * - Astéroïdes déplacement en diagonale
 * - Vitesse de tir à placer variable dans Weapon shootingSpeed
 * - Séparer render et update - GUILLAUME
 */

public class GameScreen implements Screen {
    private SpriteBatch spriteBatch;
    private Skin skin;
    private Space space;
    private SpaceRenderer renderer;
    private Music music;

    public GameScreen() {
        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/ambiance.mp3"));
        music.setLooping(true);
        space = new Space(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch = new SpriteBatch();

        renderer = new SpaceRenderer(space);
    }

    @Override
    public void show() {
        music.play();
        music.setVolume(0.01F);
    }

    private void update() {
        renderer.update();
        if (space.isGameOver()) {
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

    }

    @Override
    public void dispose() {
        music.dispose();
        spriteBatch.dispose();
        skin.dispose();
    }
}
