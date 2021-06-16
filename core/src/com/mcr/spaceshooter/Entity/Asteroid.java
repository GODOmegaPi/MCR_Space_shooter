package com.mcr.spaceshooter.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcr.spaceshooter.Utils.Rand;

import com.badlogic.gdx.math.Rectangle;

public class Asteroid {
    private Texture texture;
    // private int x, y;
    private int size;
    private int speed;
    private boolean outOfBound;
    private boolean alive;
    private Rectangle bounds;

    public Asteroid() {
        size = Rand.generateRandom(50, 75);
        speed = Rand.generateRandom(2, 5);
        texture = new Texture(Gdx.files.internal("asteroid1.png"));
        outOfBound = false;
        alive = true;

        //TODO remove variables tampons ouloulou
        int x = Rand.generateRandom(0, Gdx.graphics.getWidth());
        int y = Rand.generateRandom(Gdx.graphics.getHeight(), Gdx.graphics.getHeight() + 20);
        bounds = new Rectangle(x, y, size, size);
    }

    public void render(SpriteBatch spriteBatch) {

        bounds.setY(bounds.getY() - speed);
        spriteBatch.begin();
        spriteBatch.draw(texture, bounds.getX(), bounds.getY(), size, size);
        spriteBatch.end();
        if(bounds.getY() < 0) {
            outOfBound = true;
        }
    }

    boolean isOutOfBound(){
        return outOfBound;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
