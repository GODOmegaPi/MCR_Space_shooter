package com.mcr.spaceshooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mcr.spaceshooter.UI.Screen.GarageScreen;

/**
 * Class used to change the current displayed screen
 */
public class ScreenManager {

    private static ScreenManager instance;
    private Game game;
    private Screen currentScreen;
    private GarageScreen garageScreen;

    /**
     * Private constructor initialise a screen we want to reuse as is
     */
    private ScreenManager(){
        garageScreen = new GarageScreen();
    }

    /**
     * Get the garage screen
     * @return the garage screen
     */
    public Screen getGarageScreen(){
        return garageScreen;
    }

    /**
     * Set current displayed screen
     * @param screen the screen to display
     */
    public void setScreen(Screen screen){
        currentScreen = screen;
        game.setScreen(screen);
        screen.show();
    }

    /**
     * Set the current game
     * @param game the game to set
     */
    public void setGame(Game game){
        this.game = game;
    }

    /**
     * Render current screen (also serve as a form of update)
     */
    public void render () {
        currentScreen.render(Gdx.graphics.getDeltaTime());
    }

    /**
     * Get the singleton instance of the class
     * @return the current instance
     */
    public static ScreenManager getInstance(){
        if(instance == null){
            instance = new ScreenManager();
        }
        return instance;
    }

}
