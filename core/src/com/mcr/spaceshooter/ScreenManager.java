package com.mcr.spaceshooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

public class ScreenManager {

    private static ScreenManager instance;
    private Game game;
    private Screen currentScreen;

    // TODO singleton

    private ScreenManager(){

    }
    public void setScreen(Screen screen){
        currentScreen = screen;
        game.setScreen(screen);
        screen.show();
    }

    public void setGame(Game game){
        this.game = game;
    }

    public void render () {
        currentScreen.render(0);
    }

    public static ScreenManager getInstance(){
        if(instance == null){
            instance = new ScreenManager();
        }
        return instance;
    }

}
