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
import com.mcr.spaceshooter.Utils.Toast; // https://github.com/wentsa/Toast-LibGDX

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implémentation de l'écran garage, de sélection et construction de vaisseau
 *
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class
GarageScreen implements Screen {
    private final ShipBuilder builder;
    private final Stage stage;
    private final SpriteBatch spriteBatch;
    private final List<Toast> toasts;
    private final Toast.ToastFactory errorToastFactory;
    private final Label costValueLbl;

    /**
     * Constructeur
     */
    public GarageScreen() {
        builder = new PlayableShipBuilder();

        // Contient une hiérarchie d'actors, gère le viewport et distribue les évènements de saisie (input events)
        stage = new Stage(new ScreenViewport());
        // Définit l'InputProcessor qui va recevoir tous les input events. Sera appelé avant ApplicationListener.render() à chaque frame
        Gdx.input.setInputProcessor(stage);

        // SpriteBatch pour l'affichage de l'image de fond d'écran
        spriteBatch = new SpriteBatch();

        List<Equipment> fuselagesList = Loader.getInstance().getFuselageList();
        List<Equipment> weaponsList = Loader.getInstance().getWeaponList();
        List<Equipment> shieldsList = Loader.getInstance().getShieldList();

        // Initialisation du Toast Factory pour les messages d'erreur
        errorToastFactory = new Toast.ToastFactory.Builder()
                .font(Asset.getInstance().getFont())
                .backgroundColor(new Color(0.98f, 0.98f, 0.98f, 1f)) // default : new Color(0.5f, 0.5f, 0.5f, 1f)
                .fadingDuration(1.2f)
                .fontColor(new Color(0.86f, 0, 0, 1f)).build();
        toasts = new ArrayList<>();

        // Grilles pour l'alignement des éléments de l'interface
        Table table = new Table();
        Table firstColTable = new Table();
        Table secondColTable = new Table();

        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("Garage", Asset.getInstance().getSkin());
        titleLabel.setFontScale(1);

        // Bouton "Jouer" et click listener
        TextButton playButton = new TextButton("Jouer", Asset.getInstance().getSkin());
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    // Construit le vaisseau via le builder en effectuant les vérifications de bonne conception
                    Spaceship ship = builder.build();

                    Asset.getInstance().getGarageMusic().stop();

                    // Création du nouvel écran de jeu et basculement sur celui-ci
                    Screen screen = new GameScreen(ship);
                    ScreenManager.getInstance().setScreen(screen);
                } catch (ShipBuilderException sbe) {
                    // Affichage d'un message d'erreur si le builder n'a pas pu construire le vaisseau
                    displayToast(sbe.getMessage());
                }
            }
        });

        // Bouton "Quitter" et click listener
        TextButton quitButton = new TextButton("Quitter", Asset.getInstance().getSkin());
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // Labels pour le coût total de toutes les pièces du vaisseau
        Label costLbl = new Label("Cout total du vaisseau :", Asset.getInstance().getSkin());
        costValueLbl = new Label("0", Asset.getInstance().getSkin());
        Label maxCostLbl = new Label("/" + Constants.MAX_COST, Asset.getInstance().getSkin());

        table.add(titleLabel).colspan(2).expand();
        table.row();
        table.add(firstColTable).expand();
        table.add(secondColTable).expand();

        // Ajout des sélecteurs d'équipement
        firstColTable.add(new DefensiveEquipmentSelector(
                fuselagesList,
                Asset.getInstance().getSkin(),
                c -> builder.setFuselage((Fuselage) c),
                builder::clearFuselage,
                this)
        ).height(250).width(300).pad(10).colspan(3).center();
        firstColTable.row();
        firstColTable.add(new OffensiveEquipmentSelector(
                weaponsList,
                Asset.getInstance().getSkin(),
                c -> builder.setWeapon((Weapon) c),
                builder::clearWeapon,
                this)
        ).height(250).width(300).colspan(3).pad(10).center();
        firstColTable.row();
        firstColTable.add(new DefensiveEquipmentSelector(
                shieldsList,
                Asset.getInstance().getSkin(),
                c -> builder.setShield((Shield) c),
                builder::clearShield,
                this)
        ).height(250).width(300).colspan(3).pad(10).center();

        secondColTable.add(costLbl).colspan(2);
        secondColTable.row();
        secondColTable.add(costValueLbl).uniform().align(Align.right);
        secondColTable.add(maxCostLbl).colspan(1).align(Align.left);
        secondColTable.row();
        secondColTable.add(playButton).width(300).colspan(2);
        secondColTable.row();
        secondColTable.add(quitButton).width(300).colspan(2);
    }

    /**
     * Met à jour le coût du vaisseau en fonction des choix de pièces
     */
    public void updateCost() {
        if (builder.getTotalCost() > Constants.MAX_COST) {
            costValueLbl.setColor(Color.RED);
        } else {
            costValueLbl.setColor(Color.WHITE);
        }
        costValueLbl.setText(builder.getTotalCost());
    }

    /**
     * Affiche un toast court
     */
    public void displayToast(String text) {
        toasts.add(errorToastFactory.create(text, Toast.Length.SHORT));
    }

    /**
     * Affiche le stage
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Gère l'affichage des éléments dans le Screen
     * @param delta temps écoulé depuis le dernier appel à render
     */
    @Override
    public void render(float delta) {
        if (!Asset.getInstance().getGarageMusic().isPlaying()) {
            Asset.getInstance().getGarageMusic().play();
        }

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Affichage du fond d'écran
        spriteBatch.begin();
        spriteBatch.draw(Asset.getInstance().getBackgroundTexture(), 0, 0);
        spriteBatch.end();

        // Le stage effectue les actions et se dessine
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        // Affiche les toasts en cas d'erreur
        Iterator<Toast> it = toasts.iterator();
        while (it.hasNext()) {
            Toast t = it.next();
            if (!t.render(Gdx.graphics.getDeltaTime())) {
                it.remove(); // Toast fini
            } else {
                break; // Premier toast encore actif
            }
        }
    }

    /**
     * Méthode appeler lors d'un resize
     * @param width nouvelle largeur
     * @param height nouvelle hauteur
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * Méthode appeler lorsque la screen n'a pas le focus
     */
    @Override
    public void pause() {
    }

    /**
     * Méthode appeler lors du focus de la screen
     */
    @Override
    public void resume() {

    }

    /**
     * Méhtode appeler lorsque la screen est cachée. On set le input processor à null afin qu'il ne soit pas possible de
     * cliquer les boutons du garage lorsqu'on est dans l'écran de jeu.
     */
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Méhtode appeler lorsque screen doit libérer ses resources. On y libère le stage.
     */
    @Override
    public void dispose() {
        stage.dispose();
    }
}
