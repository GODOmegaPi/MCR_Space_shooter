package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;

public class SpaceRenderer {

    private Batch spriteBatch;
    private OrthographicCamera cam;

    private int width;
    private int height;
    private float ppuX;	// pixels per unit on the X axis
    private float ppuY;	// pixels per unit on the Y axi

    public SpaceRenderer(Batch batch) {
        spriteBatch = batch;
        //TODO Centrer.
        this.cam = new OrthographicCamera(0, 0);

        loadTextures();
    }

    private void loadTextures(){
        

    }

    public void renderBackground(){
        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        //TODO draw shit
        spriteBatch.end();
    }


    public void setSize (int w, int h) {
        //TODO. Pour l'instant on s'en fout des resizes.
    }
}
