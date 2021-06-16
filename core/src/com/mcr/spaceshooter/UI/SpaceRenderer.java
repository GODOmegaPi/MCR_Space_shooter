package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mcr.spaceshooter.Entity.Asteroid;
import com.mcr.spaceshooter.Entity.Space;
import com.mcr.spaceshooter.Entity.Spaceship;

public class SpaceRenderer {

    private Space space;

    private int width;
    private int height;


    private Texture backgroundTexture;

    public SpaceRenderer(Space space) {
        this.space = space;
        //TODO Centrer.
       // this.cam = new OrthographicCamera(0, 0);

        loadTextures();
    }

    private void loadTextures() {
        backgroundTexture = new Texture(Gdx.files.internal("bg.jpg"));
    }

    public void renderBackground(SpriteBatch spriteBatch){
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0);
        spriteBatch.end();
    }


    public void setSize (int w, int h) {
        //TODO. Pour l'instant on s'en fout des resizes.

    }

    public void update() {
        space.update();
    }

    public void draw(SpriteBatch spriteBatch) {
        renderBackground(spriteBatch);
        space.render(spriteBatch);
    }
}
