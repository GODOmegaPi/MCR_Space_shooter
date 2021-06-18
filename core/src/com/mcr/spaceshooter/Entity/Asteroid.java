package com.mcr.spaceshooter.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcr.spaceshooter.Asset.Asset;
import com.mcr.spaceshooter.Utils.Rand;

import com.badlogic.gdx.math.Rectangle;

public class Asteroid {
    private final int speed;
    private boolean outOfBound;
    private boolean hit;
    private Rectangle bounds;
    private final int asteroidTextureId;

    public static int DAMAGE = 10;

    public Asteroid() {
        speed = Rand.generateRandom(2, 5);
        outOfBound = false;
        hit = false;
        asteroidTextureId = Rand.generateRandom(
                Asset.getInstance().MIN_ASTEROIDS_TEXTURES,
                Asset.getInstance().MAX_ASTEROIDS_TEXTURES
        );

        //TODO remove variables tampons ouloulou
        int size = Rand.generateRandom(50, 75);
        int x = Rand.generateRandom(size, Gdx.graphics.getWidth() - size);
        int y = Rand.generateRandom(
                Gdx.graphics.getHeight(),
                (int) (Gdx.graphics.getHeight() + Gdx.graphics.getHeight() * 0.5)
        );
        bounds = new Rectangle(x, y, size, size);
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(
                Asset.getInstance().getAsteroidsTexture(asteroidTextureId),
                bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()
        );
        spriteBatch.end();
    }

    public boolean isOutOfBound(){
        return outOfBound;
    }

    public void hit(){
        hit = true;
    }

    public boolean isHit(){
        return hit;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void update() {
        bounds.setY(bounds.getY() - speed);
        if(bounds.getY() < 0) {
            outOfBound = true;
        }
    }
}
