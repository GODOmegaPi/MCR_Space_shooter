package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mcr.spaceshooter.ScreenManager;

public class GameOverScreen implements Screen {
    private Stage stage;
    private Skin skin;
    private int score;

    public GameOverScreen(int score) {
        this.score = score;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));


        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);


        Label titleLabel = new Label("Game over puto", skin);
        titleLabel.setFontScale(2);

        Label scoreLabel = new Label("Score : " + score, skin);
        scoreLabel.setFontScale(1);

        TextButton mainMenuButton = new TextButton("Retour au menu", skin);
        mainMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen screen = new GarageScreen();
                ScreenManager.getInstance().setScreen(screen);
            }
        });

        TextButton quitButton = new TextButton("Quitter", skin);
        quitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(titleLabel).colspan(3);
        table.row();
        table.add(scoreLabel).colspan(3);
        table.row();
        table.add(mainMenuButton).colspan(3);
        table.row();
        table.add(quitButton).colspan(3);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    }
}
