package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.mcr.spaceshooter.Builder.PlayableShipBuilder;
import com.mcr.spaceshooter.Builder.ShipBuilder;
import com.mcr.spaceshooter.Entity.Equipments.Equipment;
import com.mcr.spaceshooter.Entity.Equipments.Fuselage;
import com.mcr.spaceshooter.Entity.Equipments.Shield;
import com.mcr.spaceshooter.Entity.Equipments.Weapon;
import com.mcr.spaceshooter.ScreenManager;
import com.sun.tools.javac.util.Pair;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO:
 * L Faire la sous-structure Equipment (OffensiveEquipment, DefensiveEquipment) & Ajouter un nom aux équipements
 * - Rajouter les specs des équipements (+ coût etc)
 * - Afficher un coût du vaisseau à chaque appui sur équiper => màj du coût
 * - Afficher des erreurs  https://github.com/wentsa/Toast-LibGDX ou label ou dialog
 * - Transfert du vaisseau vers le jeu
 *      - Transfert des textures.
 * - Faire des données pour les équipements (HP, cost, etc.)
 */
public class GarageScreen implements Screen {
    private Stage stage;
    private Skin skin;
    private List<Pair<Equipment, Texture>> fuselagesList;
    private List<Pair<Equipment, Texture>> weaponsList;
    private List<Pair<Equipment, Texture>> shieldsList;
    private ShipBuilder builder;

    public GarageScreen() {
        builder = new PlayableShipBuilder();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));

        // TODO voir comment opti new LinkedList<>([p1,p2,p3])
        fuselagesList = new LinkedList<>();
        Pair p1 = new Pair<>(new Fuselage("Falcon 1", 10, 10), new Texture(Gdx.files.internal("ss_1.png")));
        Pair p2 = new Pair<>(new Fuselage("Falcon 9", 651, 25), new Texture(Gdx.files.internal("ss_2.png")));
        Pair p3 = new Pair<>(new Fuselage("Falcon Heavy", 10, 10), new Texture(Gdx.files.internal("ss_3.png")));

        fuselagesList.add(p1);
        fuselagesList.add(p3);
        fuselagesList.add(p2);

        weaponsList = new LinkedList<>();
        Pair pa = new Pair<>(new Weapon("SIG 550", 10, 10), new Texture(Gdx.files.internal("wp_1.png")));
        Pair pb = new Pair<>(new Weapon("Browning M2HB", 10, 10), new Texture(Gdx.files.internal("wp_2.png")));
        Pair pc = new Pair<>(new Weapon("Panzerfaust", 10, 10), new Texture(Gdx.files.internal("wp_3.png")));
        weaponsList.add(pc);
        weaponsList.add(pb);
        weaponsList.add(pa);


        shieldsList = new LinkedList<>();
        Pair px = new Pair<>(new Shield("Phantom Shield", 10, 10), new Texture(Gdx.files.internal("sh_1.png")));
        Pair py = new Pair<>(new Shield("Diamond Shield", 10, 10), new Texture(Gdx.files.internal("sh_2.png")));
        Pair pz = new Pair<>(new Shield("Green Plasma Shield", 10, 10), new Texture(Gdx.files.internal("sh_3.png")));

        shieldsList.add(pz);
        shieldsList.add(px);
        shieldsList.add(py);

        Table table = new Table();

        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("Garage", skin);
        titleLabel.setFontScale(1);
        TextButton playButton = new TextButton("Jouer", skin);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO si le builder est ok, récupérer ship
                Screen screen = new GameScreen();
                ScreenManager.getInstance().setScreen(screen);
            }
        });

        TextButton quitButton = new TextButton("Quitter", skin);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(titleLabel).colspan(3);
        table.row();
        table.add(new EquipementSelector(fuselagesList, skin, c -> builder.setFuselage((Fuselage) c), () -> builder.clearFuselage())).height(200).colspan(3).center();
        table.row();
        table.add(new EquipementSelector(weaponsList, skin, c -> builder.setWeapon((Weapon) c), () -> builder.clearWeapon())).height(200).colspan(3).center();
        table.row();
        table.add(new EquipementSelector(shieldsList, skin, c -> builder.setShield((Shield) c), () -> builder.clearShield())).height(200).colspan(3).center();
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
