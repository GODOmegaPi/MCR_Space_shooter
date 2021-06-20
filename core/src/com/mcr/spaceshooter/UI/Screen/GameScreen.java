package com.mcr.spaceshooter.UI.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcr.spaceshooter.Utils.Asset;
import com.mcr.spaceshooter.Entity.Space;
import com.mcr.spaceshooter.Entity.Spaceship;
import com.mcr.spaceshooter.ScreenManager;
import com.mcr.spaceshooter.UI.SpaceRenderer;

/**
 * Classe représentant le screen de jeu. càd le spaceshooter à proprement parlé
 *
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class GameScreen implements Screen {
    private SpriteBatch spriteBatch;
    private Space space;
    private SpaceRenderer renderer;

    /**
     * Constructeur. On utilisera le vaisseau passé en paramètre dans le jeu
     * @param spaceship vaisseau à utiliser pour jouer
     */
    public GameScreen(Spaceship spaceship) {
        space = new Space(spaceship);
        spriteBatch = new SpriteBatch();
        renderer = new SpaceRenderer(space);
    }

    /**
     * Méthode appeler lorsque la screen est affiché. Pour le moment, on joue simplement une musique.
     */
    @Override
    public void show() {
        Asset.getInstance().getAmbianceMusic().play();
    }

    /**
     * Met à jour les enfants et vérifie si le jeu est terminé
     */
    private void update() {
        renderer.update();
        if (space.isGameOver()) {
            Asset.getInstance().getAmbianceMusic().stop();
            ScreenManager.getInstance().setScreen(new GameOverScreen(space.getScore()));
        }
    }

    /**
     * Affiche les composants de la screen. L'affichage du jeu est délégué à la classe renderer
     * @param delta le temps écoulé depuis le dernier appel à cette fonction
     */
    @Override
    public void render(float delta) {
        update();

        renderer.render(spriteBatch);
    }

    /**
     * Méthode appeler lors d'un resize. On indique au renderer que la taille de la fenêtre à changer.
     * @param width nouvelle largeur
     * @param height nouvelle hauteur
     */
    @Override
    public void resize(int width, int height) {
        renderer.setSize(width, height);
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
        spriteBatch.dispose();
    }
}
