package com.mcr.spaceshooter.UI.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mcr.spaceshooter.Utils.Asset;
import com.mcr.spaceshooter.ScreenManager;

import java.util.LinkedList;

/**
 * Classe représentant l'écran de fin de jeu
 *
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class GameOverScreen implements Screen {
    private Stage stage;
    private int score;

    /**
     * Constructeur
     *
     * @param score le score réalisé par le joueur
     */
    public GameOverScreen(int score) {
        this.score = score;

        Asset.getInstance().getGameoverMusic().play();

        // Contient une hiérarchie d'actors, gère le viewport et distribue les évènements de saisie (input events)
        stage = new Stage(new ScreenViewport());
        // Définit l'InputProcessor qui va recevoir tous les input events. Sera appelé avant ApplicationListener.render() à chaque frame
        Gdx.input.setInputProcessor(stage);

        // Grille pour l'alignement des éléments de l'interface
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);


        Label titleLabel = new Label("Game over", Asset.getInstance().getSkin());
        titleLabel.setFontScale(2);

        Label scoreLabel = new Label("Score : " + score, Asset.getInstance().getSkin());
        scoreLabel.setFontScale(1);

        // Bouton de retour au garage et click listener
        TextButton mainMenuButton = new TextButton("Retour au garage", Asset.getInstance().getSkin());
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Asset.getInstance().getGameoverMusic().stop();

                // Récupération de l'écran du garage et basculement sur celui-ci
                Screen screen = ScreenManager.getInstance().getGarageScreen();
                ScreenManager.getInstance().setScreen(screen);
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

        // Ajout des éléments sur la grille
        table.add(titleLabel).colspan(3);
        table.row();
        table.add(scoreLabel).colspan(3);
        table.row();
        table.add(mainMenuButton).colspan(3);
        table.row();
        table.add(quitButton).colspan(3);
    }

    /**
     * Méthode appeler lorsque la screen est affiché. Pour le moment, délègue la gestion des inputs au stage
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Affiche les composants de la screen. L'affichage du jeu est délégué à la classe renderer
     * @param delta le temps écoulé depuis le dernier appel à cette fonction
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Le stage effectue les actions et se dessine
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Méthode appeler lors d'un resize. On indique au renderer que la taille de la fenêtre à changer.
     * @param width nouvelle largeur
     * @param height nouvelle hauteur
     */
    @Override
    public void resize(int width, int height) {

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
     * Méhtode appeler lorsque la screen est cachée. On set le input processor à null afin de ne pas interférer avec les autres screens
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
