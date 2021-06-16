package com.mcr.spaceshooter.Entity.Equipements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {

    private int x;
    private int y;
    private int speed;
    Texture texture;
    boolean outOfBound;

    public Bullet(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        texture = new Texture(Gdx.files.internal("bullet1.png"));
    }

    public void render(SpriteBatch spriteBatch) {
        y += speed;
        spriteBatch.begin();
        spriteBatch.draw(texture, x, y, 50, 50);
        spriteBatch.end();
        if(y > Gdx.graphics.getHeight()) {
            outOfBound = true;
        }
    }

    boolean isOutOfBound(){
        return outOfBound;
    }
}
