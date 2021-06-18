package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mcr.spaceshooter.Asset.Asset;
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
    private Music music;

    public GarageScreen(){
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        music = Asset.getInstance().getGarageMusic();
        music.setLooping(true);
        music.play();

        // TODO voir comment opti new LinkedList<>([p1,p2,p3])
        // TODO clean code
        // TODO: ajouter aide commandes
        fuselagesList = new LinkedList<>();
        for (int i = 1; i <= 16; i++) {
            Pair p = new Pair<>(new Fuselage(10, 10), new Texture(Gdx.files.internal("ships/cockpits/ship (" + i + ").png")));
            fuselagesList.add(p);
        }
        /*Pair p1 = new Pair<>(new Fuselage(10, 10), new Texture(Gdx.files.internal("ss_1.png")));
        Pair p2 = new Pair<>(new Fuselage(10, 10), new Texture(Gdx.files.internal("ss_2.png")));
        Pair p3 = new Pair<>(new Fuselage(10, 10), new Texture(Gdx.files.internal("ss_3.png")));

        fuselagesList.add(p1);
        fuselagesList.add(p3);
        fuselagesList.add(p2);*/

        weaponsList = new LinkedList<>();
        for (int i = 1; i <= 48; i++) {
            Pair p = new Pair<>(new Weapon(10, 10), new Texture(Gdx.files.internal("ships/weapons/weapon (" + i + ").png")));
            weaponsList.add(p);
        }
        /*Pair pa = new Pair<>(new Weapon(10, 10), new Texture(Gdx.files.internal("badlogic.jpg")));
        Pair pb = new Pair<>(new Weapon(10, 10), new Texture(Gdx.files.internal("game.png")));
        Pair pc = new Pair<>(new Weapon(10, 10), new Texture(Gdx.files.internal("noEquipement.jpg")));
        weaponsList.add(pc);
        weaponsList.add(pb);
        weaponsList.add(pa);*/


        shieldsList = new LinkedList<>();
        for (int i = 1; i <= 3; i++) {
            Pair p = new Pair<>(new Shield(10, 10), new Texture(Gdx.files.internal("ships/shields/shield (" + i + ").png")));
            shieldsList.add(p);
        }
        /*Pair px = new Pair<>(new Shield(10, 10), new Texture(Gdx.files.internal("badlogic.jpg")));
        Pair py = new Pair<>(new Shield(10, 10), new Texture(Gdx.files.internal("game.png")));
        Pair pz = new Pair<>(new Shield(10, 10), new Texture(Gdx.files.internal("noEquipement.jpg")));
        shieldsList.add(pz);
        shieldsList.add(px);
        shieldsList.add(py);*/

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("Garage", skin);
        titleLabel.setFontScale(3);

        Label cockpitLabel = new Label("Cockpit", skin);
        cockpitLabel.setFontScale(1);

        Label weaponLabel = new Label("Weapon", skin);
        cockpitLabel.setFontScale(1);

        Label shieldLabel = new Label("Shield", skin);
        cockpitLabel.setFontScale(1);

        TextButton playButton = new TextButton("Jouer", skin);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.stop();
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


        table.add(titleLabel).colspan(3);
        table.row();
        table.add(cockpitLabel).colspan(3);
        table.row();
        table.add(new ConfigRow(fuselagesList, skin)).height(200).colspan(3).center();
        table.row();
        table.add(weaponLabel).colspan(3);
        table.row();
        table.add(new ConfigRow(weaponsList, skin)).height(200).colspan(3).center();
        table.row();
        table.add(shieldLabel).colspan(3);
        table.row();
        table.add(new ConfigRow(shieldsList, skin)).height(200).colspan(3).center();
        table.row();
        table.add(playButton).width(300).colspan(3);
        table.row();
        table.add(quitButton).width(300).colspan(3);

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
