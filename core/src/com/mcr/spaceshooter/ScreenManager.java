package com.mcr.spaceshooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mcr.spaceshooter.UI.Screen.GarageScreen;

/**
 * Classe utilisée pour charger et afficher les différents écrans utilisés pour le jeu
 *
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class ScreenManager {

    private static ScreenManager instance;
    private Game game;
    private Screen currentScreen;
    private GarageScreen garageScreen;

    /**
     * Constructeur privé initialisant l'écran de construction de vaisseau qui seras réutilisé
     */
    private ScreenManager() {
        garageScreen = new GarageScreen();
    }

    /**
     * Récupère l'écran de construction de vaisseau
     *
     * @return l'écran de construction de vaisseau
     */
    public Screen getGarageScreen() {
        return garageScreen;
    }

    /**
     * Définit quel écran doit être affiché
     *
     * @param screen l'écran à afficher
     */
    public void setScreen(Screen screen) {
        currentScreen = screen;
        game.setScreen(screen);
        screen.show();
    }

    /**
     * Définit quelle partie doit être utilisée
     *
     * @param game la partie à utiliser
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Affiche et met à jour l'écran courant
     */
    public void render() {
        currentScreen.render(Gdx.graphics.getDeltaTime());
    }

    /**
     * Récupère l'instance de la classe courante
     *
     * @return l'instance de la classe courante
     */
    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

}
