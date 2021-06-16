package com.mcr.spaceshooter.Entity.Equipements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {

    private Rectangle bounds;
    private int speed;
    private Texture texture;
    private boolean outOfBound;
    private boolean alive;

    public static int SIZE = 50;
    

    public Bullet(float x, float y, int size, int speed) {

        this.speed = speed;
        texture = new Texture(Gdx.files.internal("bullet1.png"));
        outOfBound = false;
        alive = true;
        bounds = new Rectangle(x, y, size, size);
        
    }

    public void render(SpriteBatch spriteBatch) {
        bounds.setY(bounds.getY() + speed);
        spriteBatch.begin();
        spriteBatch.draw(texture, bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        spriteBatch.end();
        if(bounds.getY() > Gdx.graphics.getHeight()) {
            outOfBound = true;
        }
    }

    public boolean isColliding(Rectangle rect) {
        if(Intersector.overlaps(bounds, rect)) {
            alive = false;
            return true;
        }
        return false;
    }

    boolean isOutOfBound(){
        return outOfBound;
    }

    boolean isAlive(){
        return alive;
    }
}
