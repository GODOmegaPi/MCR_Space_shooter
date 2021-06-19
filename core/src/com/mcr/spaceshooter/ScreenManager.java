package com.mcr.spaceshooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mcr.spaceshooter.UI.Screen.GarageScreen;

public class ScreenManager {

    private static ScreenManager instance;
    private Game game;
    private Screen currentScreen;
    private GarageScreen garageScreen;

    private ScreenManager(){
        garageScreen = new GarageScreen();

    }
    public Screen getGarageScreen(){
        return garageScreen;
    }


    public void setScreen(Screen screen){
        //Screen old = screen;
        currentScreen = screen;
        /*
        if(old != null){
            old.pause();
        }
        
         */

        game.setScreen(screen);
        screen.show();
    }

    public void setGame(Game game){
        this.game = game;
    }

    public void render () {
        currentScreen.render(Gdx.graphics.getDeltaTime());
    }

    public static ScreenManager getInstance(){
        if(instance == null){
            instance = new ScreenManager();
        }
        return instance;
    }

}
