package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mcr.spaceshooter.Entity.Asteroid;
import com.mcr.spaceshooter.Entity.Space;
import com.mcr.spaceshooter.Entity.Spaceship;

import java.awt.Font;

public class SpaceRenderer {

    private final Space space;

    private int width;
    private int height;

    private BitmapFont font;
    private Texture backgroundTexture;

    public SpaceRenderer(Space space) {
        this.space = space;
        //TODO Centrer.
       // this.cam = new OrthographicCamera(0, 0);
        font = new BitmapFont();

        loadTextures();
    }

    private void loadTextures() {
        backgroundTexture = new Texture(Gdx.files.internal("bg.jpg"));
    }

    public void renderBackground(SpriteBatch spriteBatch){
        spriteBatch.draw(backgroundTexture, 0, 0);
    }

    public void renderText(SpriteBatch spriteBatch){
        int spaceshipHP = space.getSpaceship().getHP();
        int shieldHP    = space.getSpaceship().getShield().getHp();
        int score       = space.getScore();
        
        font.draw(spriteBatch, "PV : " + spaceshipHP + ", SHIELD : " + shieldHP, width - 200, height - 20);
        font.draw(spriteBatch, "SCORE : " + score, 20, height - 20);
    }


    public void setSize (int w, int h) {
        width = w;
        height = h;
    }

    public void update() {
        space.update();
    }


    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        renderBackground(spriteBatch);
        renderText(spriteBatch);
        spriteBatch.end();
        space.render(spriteBatch);

    }
}