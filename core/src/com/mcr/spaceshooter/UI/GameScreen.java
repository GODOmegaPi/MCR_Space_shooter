package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mcr.spaceshooter.Entity.Space;
import com.mcr.spaceshooter.ScreenManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Audio;


/**
 *
 * TODO:
 * - Game over
 * - Espace torique (à voir)
 * - Astéroïdes déplacement en diagonale
 * - Vitesse de tir à placer variable dans Weapon shootingSpeed
 */

public class GameScreen implements Screen {
    private Stage stage;
    private SpriteBatch spriteBatch;
    private Skin skin;
    private Space space;
    private SpaceRenderer renderer;
    private Music music;

    public GameScreen(){
        //stage = new Stage(new ScreenViewport());

        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/ambiance.mp3"));
        music.setLooping(true);
        space = new Space();
        spriteBatch = new SpriteBatch();

        renderer = new SpaceRenderer(space);


    }

    @Override
    public void show() {
        /*Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);*/

        /*
        TextButton quitButton = new TextButton("jean", skin);
        quitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.row();
        table.add(quitButton).width(300).colspan(2);
         */
    music.play();

    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(1, 1, 1, 1); //%color value (1 = 100%)
        //Gdx.gl.glClear();
        //stage.act();
        //stage.draw();

        renderer.update();
        if(space.isGameOver()) {
            ScreenManager.getInstance().setScreen(new GameOverScreen(space.getScore()));
            return;
        }
        renderer.draw(spriteBatch);

        /*
        renderer.renderBackground(spriteBatch);
        renderer.drawSpaceship(spriteBatch);

         */

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
        stage.dispose();
        music.dispose();
    }
}
