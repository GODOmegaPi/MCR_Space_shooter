package com.mcr.spaceshooter.Entity.Equipements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {

    private Rectangle bounds;
    private final int speed;
    private final Texture texture;
    private boolean outOfBound;
    private boolean alive;
    private Sound sound;

    public static int WIDTH = 7;
    public static int HEIGHT = 19;
    

    public Bullet(float x, float y, int speed) {
        this.speed = speed;
        texture = new Texture(Gdx.files.internal("ships/weapons/weapon (38).png"));
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/7.wav"));
        sound.play();
        outOfBound = false;
        alive = true;
        bounds = new Rectangle(x - WIDTH / 2, y, WIDTH, HEIGHT);
        
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(texture, bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
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
