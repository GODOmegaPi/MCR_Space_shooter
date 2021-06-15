package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mcr.spaceshooter.Entity.Equipements.Equipment;
import com.mcr.spaceshooter.Entity.Equipements.Fuselage;
import com.mcr.spaceshooter.Entity.Equipements.Shield;
import com.mcr.spaceshooter.Entity.Equipements.Weapon;
import com.mcr.spaceshooter.ScreenManager;
import com.sun.tools.javac.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class GarageScreen implements Screen {
    private Stage stage;
    private Skin skin;
    private List<Pair<Equipment, Texture>> fuselagesList;
    private List<Pair<Equipment, Texture>> weaponsList;
    private List<Pair<Equipment, Texture>> shieldsList;



    public GarageScreen(){
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));

        // TODO voir comment opti new LinkedList<>([p1,p2,p3])
        fuselagesList = new LinkedList<>();
        Pair p1 = new Pair<>(new Fuselage(10, 10), new Texture(Gdx.files.internal("ss_1.png")));
        Pair p2 = new Pair<>(new Fuselage(10, 10), new Texture(Gdx.files.internal("ss_2.png")));
        Pair p3 = new Pair<>(new Fuselage(10, 10), new Texture(Gdx.files.internal("ss_3.png")));

        fuselagesList.add(p1);
        fuselagesList.add(p3);
        fuselagesList.add(p2);

        weaponsList = new LinkedList<>();
        Pair pa = new Pair<>(new Weapon(10, 10), new Texture(Gdx.files.internal("badlogic.jpg")));
        Pair pb = new Pair<>(new Weapon(10, 10), new Texture(Gdx.files.internal("game.png")));
        Pair pc = new Pair<>(new Weapon(10, 10), new Texture(Gdx.files.internal("noEquipement.jpg")));
        weaponsList.add(pc);
        weaponsList.add(pb);
        weaponsList.add(pa);


        shieldsList = new LinkedList<>();
        Pair px = new Pair<>(new Shield(10, 10), new Texture(Gdx.files.internal("badlogic.jpg")));
        Pair py = new Pair<>(new Shield(10, 10), new Texture(Gdx.files.internal("game.png")));
        Pair pz = new Pair<>(new Shield(10, 10), new Texture(Gdx.files.internal("noEquipement.jpg")));
        shieldsList.add(pz);
        shieldsList.add(px);
        shieldsList.add(py);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("Garage", skin);
        titleLabel.setFontScale(1);
        TextButton playButton = new TextButton("Jouer", skin);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen screen = new GameScreen();
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


        table.row();
        table.add(titleLabel).colspan(2).center();
        table.row();

        new ConfigRow(table, fuselagesList);
        table.row();
        new ConfigRow(table, weaponsList);
        table.row();
        new ConfigRow(table, shieldsList);
        table.row();
        table.add(playButton).width(300).colspan(2);
        table.row();
        table.add(quitButton).width(300).colspan(2);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);


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
        stage.getViewport().update(width, height, true);
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
