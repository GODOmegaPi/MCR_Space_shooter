package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
import com.mcr.spaceshooter.Utils.Toast;    // https://github.com/wentsa/Toast-LibGDX
import com.sun.tools.javac.util.Pair;
import org.graalvm.compiler.lir.LIRInstruction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO:
 * L Faire la sous-structure Equipment (OffensiveEquipment, DefensiveEquipment) & Ajouter un nom aux équipements
 * - Rajouter les specs des équipements (+ coût etc)
 * - Afficher un coût du vaisseau à chaque appui sur équiper => màj du coût
 * L Afficher des erreurs https://github.com/wentsa/Toast-LibGDX ou label ou dialog
 * - Transfert du vaisseau vers le jeu
 * - Transfert des textures.
 * - Faire des données pour les équipements (HP, cost, etc.)
 */
public class GarageScreen implements Screen {
    private Stage stage;
    private Skin skin;
    private List<Pair<Equipment, Texture>> fuselagesList;
    private List<Pair<Equipment, Texture>> weaponsList;
    private List<Pair<Equipment, Texture>> shieldsList;
    private ShipBuilder builder;
    private List<Toast> toasts;
    private final Toast.ToastFactory errorToastFactory;

    public GarageScreen() {
        builder = new PlayableShipBuilder();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));

        // Toasts pour les messages d'erreur
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("skin/Amble-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont font24 = generator.generateFont(parameter);
        errorToastFactory = new Toast.ToastFactory.Builder()
            .font(font24)
            .backgroundColor(Color.WHITE) // default : new Color(0.5f, 0.5f, 0.5f, 1f)
            .fadingDuration(1f)
            .fontColor(Color.RED).build();
        toasts = new ArrayList<>();

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
        Table firstColTable = new Table();
        Table secondColTable = new Table();

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

        table.add(titleLabel).colspan(2).expand();
        table.row();
        table.add(firstColTable).expand();
        table.add(secondColTable).expand();

        firstColTable.add(new DefensiveEquipmentSelector(fuselagesList, skin, c -> builder.setFuselage((Fuselage) c), () -> builder.clearFuselage(), this)).height(250).width(300).pad(10).colspan(3).center();
        firstColTable.row();
        firstColTable.add(new OffensiveEquipmentSelector(weaponsList, skin, c -> builder.setWeapon((Weapon) c), () -> builder.clearWeapon(), this)).height(250).width(300).colspan(3).pad(10).center();
        firstColTable.row();
        firstColTable.add(new DefensiveEquipmentSelector(shieldsList, skin, c -> builder.setShield((Shield) c), () -> builder.clearShield(), this)).height(250).width(300).colspan(3).pad(10).center();
        secondColTable.add(playButton).width(300);
        secondColTable.row();
        secondColTable.add(quitButton).width(300);
        stage.setDebugAll(true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Displays long toast
     */
    public void toastLong(String text) {
        toasts.add(errorToastFactory.create(text, Toast.Length.LONG));
    }

    /**
     * Displays short toast
     */
    public void toastShort(String text) {
        toasts.add(errorToastFactory.create(text, Toast.Length.SHORT));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        // Affiche les toasts
        Iterator<Toast> it = toasts.iterator();
        while(it.hasNext()) {
            Toast t = it.next();
            if(!t.render(Gdx.graphics.getDeltaTime())) {
                it.remove(); // toast finished -> remove
            } else {
                break; // first toast still active, break the loop
            }
        }
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
