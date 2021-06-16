package com.mcr.spaceshooter.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mcr.spaceshooter.Entity.Equipements.Shield;
import com.mcr.spaceshooter.Entity.Equipements.Weapon;

public class Spaceship {
    private Rectangle bounds;
    private int speed;
    private Texture texture;
    private Weapon weapon;
    private Shield shield;
    private static final int SIZE = 50;

    public Spaceship(int x, int y, int speed) {
         bounds = new Rectangle(x, y, SIZE, SIZE);
         this.speed = speed;
         texture = new Texture(Gdx.files.internal("ss_4.png"));
         weapon = new Weapon(50, 50);
         shield = new Shield(50, 50);
    }

    public void move(int amountX, int amountY) {
        this.bounds.setX( getX() + (amountX * speed));
        this.bounds.setY( getY() + (amountY * speed));
    }

    public boolean isColliding(Rectangle rect) {
        if(Intersector.overlaps(bounds, rect)) {
            //alive = false;
            return true;
        }
        return weapon.isColliding(rect);
    }

    public void shoot() {
        weapon.shoot(getX(), getY());
    }

    public void render(SpriteBatch spriteBatch) {
        if(Gdx.input.isKeyPressed(Keys.A)){
            bounds.setX(getX() - speed);
        }
        if(Gdx.input.isKeyPressed(Keys.D)){
            bounds.setX(getX() + speed);
        }
        if(Gdx.input.isKeyPressed(Keys.W)){
            bounds.setY(getY() + speed);
        }
        if(Gdx.input.isKeyPressed(Keys.S)){
            bounds.setY(getY() - speed);
        }
        if(Gdx.input.isKeyPressed(Keys.SPACE)){
            shoot();
        }

        spriteBatch.begin();
        spriteBatch.draw(texture, getX(), getY(), 80, 80);
        spriteBatch.end();

        weapon.render(spriteBatch);
    }

    public float getX() {
        return bounds.getX();
    }

    public float getY() {
        return bounds.getY();
    }
}
