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

/**
 * Classe représentant l'écran d'accueil
 *
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class WelcomeScreen implements Screen {

    private Stage stage;

    /**
     * Constructeur qui crée l'écran d'accueil
     */
    public WelcomeScreen(){
        stage = new Stage(new ScreenViewport());

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("Welcome", Asset.getInstance().getSkin());
        titleLabel.setFontScale(2);

        TextButton mainMenuButton = new TextButton("Aller au garage", Asset.getInstance().getSkin());
        mainMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen screen = ScreenManager.getInstance().getGarageScreen();
                ScreenManager.getInstance().setScreen(screen);
            }
        });

        TextButton quitButton = new TextButton("Quitter", Asset.getInstance().getSkin());
        quitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });


        table.add(titleLabel).colspan(3);
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

        // tell our stage to do actions and draw itself
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
