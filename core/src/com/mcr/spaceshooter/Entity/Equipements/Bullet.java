package com.mcr.spaceshooter.Entity.Equipements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mcr.spaceshooter.Asset.Asset;

public class Bullet {

    private Rectangle bounds;
    private final int speed;
    private boolean outOfBound;
    private boolean alive;
    private Sound sound;

    public static int WIDTH = 7;
    public static int HEIGHT = 19;
    

    public Bullet(float x, float y, int speed) {
        this.speed = speed;
        sound = Asset.getInstance().getBulletSound();
        sound.play();
        outOfBound = false;
        alive = true;
        bounds = new Rectangle(x - WIDTH / 2, y, WIDTH, HEIGHT);
        
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(
                Asset.getInstance().getWeaponsTexture(38),
                bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()
        );
        spriteBatch.end();
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

    public void update() {
        if(bounds.getY() + bounds.getHeight() > Gdx.graphics.getHeight()) {
            outOfBound = true;
        }

        bounds.setY(bounds.getY() + speed);
    }
}
