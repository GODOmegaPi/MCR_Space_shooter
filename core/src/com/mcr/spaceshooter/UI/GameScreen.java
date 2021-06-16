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

public class GameScreen implements Screen {
    private Stage stage;
    private SpriteBatch spriteBatch;
    private Skin skin;
    private Space space;
    private SpaceRenderer renderer;

    public GameScreen(){
        //stage = new Stage(new ScreenViewport());

        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
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


    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(1, 1, 1, 1); //%color value (1 = 100%)
        //Gdx.gl.glClear();
        //stage.act();
        //stage.draw();

        renderer.update();
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
    }
}
