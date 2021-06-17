package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mcr.spaceshooter.Entity.Space;

public class SpaceRenderer {

    private final Space space;

    private int width;
    private int height;

    private BitmapFont font;
    private Texture backgroundTexture;
    private Skin skin;

    public SpaceRenderer(Space space, Skin skin) {
        this.space = space;
        this.skin = skin;
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

    public void renderGUI(SpriteBatch spriteBatch){
        int spaceshipHP = space.getSpaceship().getHP();
        int shieldHP    = space.getSpaceship().getShield().getHp();
        int score       = space.getScore();

        Label hpLabel = new Label(String.format("PV       : %d", spaceshipHP), skin);
        hpLabel.setPosition(width - 225, height - 50);

        Label shieldLabel = new Label(String.format("SHIELD : %d", shieldHP), skin);
        shieldLabel.setPosition(width - 225, height - 100);

        Label scoreLabel = new Label(String.format("Score : %d", score), skin);
        scoreLabel.setPosition(20, height - 50);

        hpLabel.draw(spriteBatch, 1);
        shieldLabel.draw(spriteBatch, 1);
        scoreLabel.draw(spriteBatch, 1);
    }

    public void setSize (int w, int h) {
        width = w;
        height = h;
    }

    public void update() {
        space.update();
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        renderBackground(spriteBatch);
        spriteBatch.end();

        space.render(spriteBatch);

        spriteBatch.begin();
        renderGUI(spriteBatch);
        spriteBatch.end();
    }
}