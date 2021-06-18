package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mcr.spaceshooter.Utils.Asset;
import com.mcr.spaceshooter.Entity.Space;

public class SpaceRenderer {

    private final Space space;

    private int width;
    private int height;

    public SpaceRenderer(Space space) {
        this.space = space;
    }

    public void renderBackground(SpriteBatch spriteBatch){
        spriteBatch.draw(Asset.getInstance().getBackgroundTexture(), 0, 0);
    }

    public void renderGUI(SpriteBatch spriteBatch){
        int shieldHP    = space.getSpaceship().getShield().getHp();
        int spaceshipHP = space.getSpaceship().getFuselage().getHp();
        int score       = space.getScore();

        Label hpLabel = new Label(String.format("PV       : %d", spaceshipHP), Asset.getInstance().getSkin());
        hpLabel.setPosition(width - 225, height - 50);

        Label shieldLabel = new Label(String.format("SHIELD : %d", shieldHP), Asset.getInstance().getSkin());
        shieldLabel.setPosition(width - 225, height - 100);

        Label scoreLabel = new Label(String.format("Score : %d", score), Asset.getInstance().getSkin());
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