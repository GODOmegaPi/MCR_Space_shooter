package com.mcr.spaceshooter.UI.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.mcr.spaceshooter.Builder.PlayableShipBuilder;
import com.mcr.spaceshooter.Builder.ShipBuilder;
import com.mcr.spaceshooter.Builder.ShipBuilderException;
import com.mcr.spaceshooter.Entity.Equipments.Equipment;
import com.mcr.spaceshooter.Entity.Equipments.Fuselage;
import com.mcr.spaceshooter.Entity.Equipments.Shield;
import com.mcr.spaceshooter.Entity.Equipments.Weapon;
import com.mcr.spaceshooter.Entity.Spaceship;
import com.mcr.spaceshooter.ScreenManager;
import com.mcr.spaceshooter.UI.EquipementSelector.DefensiveEquipmentSelector;
import com.mcr.spaceshooter.UI.EquipementSelector.OffensiveEquipmentSelector;
import com.mcr.spaceshooter.UI.GameScreen;
import com.mcr.spaceshooter.Utils.Assets;
import com.mcr.spaceshooter.Utils.Constants;
import com.mcr.spaceshooter.Utils.Toast;    // https://github.com/wentsa/Toast-LibGDX
import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * TODO:
 * - Transfert des textures.
 * - Transfert du vaisseau vers le jeu
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

    private Label costLbl;
    private Label costValueLbl;
    private Label maxCostLbl;

    public GarageScreen() {
        builder = new PlayableShipBuilder();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Assets assets = Assets.getInstance();
        //skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        skin = assets.get("skin/craftacular-ui.json", Skin.class);


        // Toasts pour les messages d'erreur
        //FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("skin/Amble-Regular.ttf"));
        //FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //BitmapFont font24 = generator.generateFont(parameter);
        errorToastFactory = new Toast.ToastFactory.Builder()
                .font(assets.get("skin/Amble-Regular.ttf", BitmapFont.class))
                .backgroundColor(new Color(0.98f, 0.98f, 0.98f, 1f)) // default : new Color(0.5f, 0.5f, 0.5f, 1f)
                .fadingDuration(1.2f)
                .fontColor(new Color(0.86f, 0, 0, 1f)).build();
        toasts = new ArrayList<>();

        fuselagesList = new LinkedList<>();

        Pair p1 = new Pair<>(new Fuselage("Falcon 1", 30, 75), assets.get("ss_1.png", Texture.class));
        Pair p2 = new Pair<>(new Fuselage("Falcon 9", 40, 90), assets.get("ss_1.png", Texture.class));
        Pair p3 = new Pair<>(new Fuselage("Falcon Heavy", 50, 100), assets.get("ss_1.png", Texture.class));

        fuselagesList.add(p1);
        fuselagesList.add(p2);
        fuselagesList.add(p3);

        weaponsList = new LinkedList<>();
        Pair pa = new Pair<>(new Weapon("SIG 550", 30, 10), assets.get("wp_1.png", Texture.class));
        Pair pb = new Pair<>(new Weapon("Browning M2HB", 40, 15), assets.get("wp_2.png", Texture.class));
        Pair pc = new Pair<>(new Weapon("Panzerfaust", 50, 20), assets.get("wp_3.png", Texture.class));
        weaponsList.add(pa);
        weaponsList.add(pb);
        weaponsList.add(pc);

        shieldsList = new LinkedList<>();
        Pair px = new Pair<>(new Shield("Phantom Shield", 30, 10), assets.get("sh_1.png", Texture.class));
        Pair py = new Pair<>(new Shield("Diamond Shield", 40, 20), assets.get("sh_2.png", Texture.class));
        Pair pz = new Pair<>(new Shield("Plasma Shield", 50, 30), assets.get("sh_3.png", Texture.class));

        shieldsList.add(px);
        shieldsList.add(py);
        shieldsList.add(pz);

        Table table = new Table();
        //table.setDebug(true);
        Table firstColTable = new Table();
        //firstColTable.setDebug(true);

        Table secondColTable = new Table();
        //secondColTable.setDebug(true);

        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("Garage", skin);
        titleLabel.setFontScale(1);
        TextButton playButton = new TextButton("Jouer", skin);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try{
                    Spaceship ship = builder.build();
                    // TODO si le builder est ok, récupérer ship
                    Screen screen = new GameScreen();
                    ScreenManager.getInstance().setScreen(screen);

                }catch(ShipBuilderException sbe){
                    toastLong(sbe.getMessage());
                }

            }
        });

        TextButton quitButton = new TextButton("Quitter", skin);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        costLbl = new Label("Cout total du vaisseau :", skin);
        costValueLbl = new Label("0", skin);
        maxCostLbl = new Label("/" + Constants.MAX_COST, skin);

        table.add(titleLabel).colspan(2).expand();
        table.row();
        table.add(firstColTable).expand();
        table.add(secondColTable).expand();

        firstColTable.add(new DefensiveEquipmentSelector(fuselagesList, skin, c -> builder.setFuselage((Fuselage) c), () -> builder.clearFuselage(), this)).height(250).width(300).pad(10).colspan(3).center();
        firstColTable.row();
        firstColTable.add(new OffensiveEquipmentSelector(weaponsList, skin, c -> builder.setWeapon((Weapon) c), () -> builder.clearWeapon(), this)).height(250).width(300).colspan(3).pad(10).center();
        firstColTable.row();
        firstColTable.add(new DefensiveEquipmentSelector(shieldsList, skin, c -> builder.setShield((Shield) c), () -> builder.clearShield(), this)).height(250).width(300).colspan(3).pad(10).center();

        secondColTable.add(costLbl).colspan(2);
        secondColTable.row();
        secondColTable.add(costValueLbl).uniform().align(Align.right);
        secondColTable.add(maxCostLbl).colspan(1).align(Align.left);
        secondColTable.row();
        secondColTable.add(playButton).width(300).colspan(2);
        secondColTable.row();
        secondColTable.add(quitButton).width(300).colspan(2);
        //stage.setDebugAll(true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    public void updateCost() {
        if (builder.getTotalCost() > Constants.MAX_COST) {
            costValueLbl.setColor(Color.RED);
        } else {
            costValueLbl.setColor(Color.WHITE);
        }
        costValueLbl.setText(builder.getTotalCost());
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
        while (it.hasNext()) {
            Toast t = it.next();
            if (!t.render(Gdx.graphics.getDeltaTime())) {
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
