package com.mcr.spaceshooter.UI.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.mcr.spaceshooter.Utils.Asset;
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
import com.mcr.spaceshooter.Utils.Constants;
import com.mcr.spaceshooter.Utils.Loader;
import com.mcr.spaceshooter.Utils.Toast;    // https://github.com/wentsa/Toast-LibGDX

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GarageScreen implements Screen {
    private final Stage stage;
    private final SpriteBatch spriteBatch;
    private final ShipBuilder builder;
    private final List<Toast> toasts;
    private final Toast.ToastFactory errorToastFactory;

    // TODO local variable (intellij suggest)
    private Label costLbl;
    private Label costValueLbl;
    private Label maxCostLbl;

    public GarageScreen() {
        builder = new PlayableShipBuilder();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        spriteBatch = new SpriteBatch();
        List<Equipment> fuselagesList = Loader.getInstance().getFuselageList();
        List<Equipment> weaponsList = Loader.getInstance().getWeaponList();
        List<Equipment> shieldsList = Loader.getInstance().getShieldList();

        // Toasts pour les messages d'erreur
        //FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("skin/Amble-Regular.ttf"));
        //FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //BitmapFont font24 = generator.generateFont(parameter);
        errorToastFactory = new Toast.ToastFactory.Builder()
                .font(Asset.getInstance().getFont()) // TODO
                .backgroundColor(new Color(0.98f, 0.98f, 0.98f, 1f)) // default : new Color(0.5f, 0.5f, 0.5f, 1f)
                .fadingDuration(1.2f)
                .fontColor(new Color(0.86f, 0, 0, 1f)).build();
        toasts = new ArrayList<>();

        Table table = new Table();
        Table firstColTable = new Table();

        Table secondColTable = new Table();

        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("Garage", Asset.getInstance().getSkin());
        titleLabel.setFontScale(1);
        TextButton playButton = new TextButton("Jouer", Asset.getInstance().getSkin());
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try{
                    Spaceship ship = builder.build();
                    Asset.getInstance().getGarageMusic().stop();
                    Screen screen = new GameScreen(ship);
                    ScreenManager.getInstance().setScreen(screen);

                }catch(ShipBuilderException sbe){
                    toastLong(sbe.getMessage());
                }
            }
        });

        TextButton quitButton = new TextButton("Quitter", Asset.getInstance().getSkin());
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        costLbl = new Label("Cout total du vaisseau :", Asset.getInstance().getSkin());
        costValueLbl = new Label("0", Asset.getInstance().getSkin());
        maxCostLbl = new Label("/" + Constants.MAX_COST, Asset.getInstance().getSkin());

        table.add(titleLabel).colspan(2).expand();
        table.row();
        table.add(firstColTable).expand();
        table.add(secondColTable).expand();

        firstColTable.add(new DefensiveEquipmentSelector(fuselagesList, Asset.getInstance().getSkin(), c -> builder.setFuselage((Fuselage) c), () -> builder.clearFuselage(), this)).height(250).width(300).pad(10).colspan(3).center();
        firstColTable.row();
        firstColTable.add(new OffensiveEquipmentSelector(weaponsList, Asset.getInstance().getSkin(), c -> builder.setWeapon((Weapon) c), () -> builder.clearWeapon(), this)).height(250).width(300).colspan(3).pad(10).center();
        firstColTable.row();
        firstColTable.add(new DefensiveEquipmentSelector(shieldsList, Asset.getInstance().getSkin(), c -> builder.setShield((Shield) c), () -> builder.clearShield(), this)).height(250).width(300).colspan(3).pad(10).center();

        secondColTable.add(costLbl).colspan(2);
        secondColTable.row();
        secondColTable.add(costValueLbl).uniform().align(Align.right);
        secondColTable.add(maxCostLbl).colspan(1).align(Align.left);
        secondColTable.row();
        secondColTable.add(playButton).width(300).colspan(2);
        secondColTable.row();
        secondColTable.add(quitButton).width(300).colspan(2);
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
        if(!Asset.getInstance().getGarageMusic().isPlaying()) {
            Asset.getInstance().getGarageMusic().play();
        }

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(Asset.getInstance().getBackgroundTexture(), 0, 0);
        spriteBatch.end();
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
        Gdx.input.setInputProcessor(null);

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
