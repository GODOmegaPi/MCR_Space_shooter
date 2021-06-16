package com.mcr.spaceshooter.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcr.spaceshooter.Entity.Equipements.Shield;
import com.mcr.spaceshooter.Entity.Equipements.Weapon;

public class Spaceship {
    //TODO: MON TROU DU CUL EN FORME DE VECTEUR
    int x, y;
    int speed;
    Texture texture;
    Weapon weapon;
    Shield shield;


    public Spaceship(int x, int y, int speed) {
         this.x = x;
         this.y = y;
         this.speed = speed;
         texture = new Texture(Gdx.files.internal("ss_3.png"));
         weapon = new Weapon(50, 50);
         shield = new Shield(50, 50);
    }

    public void move(int amountX, int amountY) {
        this.x += (amountX * speed);
        this.y += (amountY * speed);
    }

    public void shoot() {
        System.out.println("piou piou!!!");
    }

    public void render(SpriteBatch spriteBatch) {
        if(Gdx.input.isKeyPressed(Keys.A)){
            x -= speed;
        }
        if(Gdx.input.isKeyPressed(Keys.D)){
            x += speed;
        }
        if(Gdx.input.isKeyPressed(Keys.W)){
            y += speed;
        }
        if(Gdx.input.isKeyPressed(Keys.S)){
            y -= speed;
        }
        if(Gdx.input.isKeyPressed(Keys.SPACE)){
            shoot();
        }

        spriteBatch.begin();
        spriteBatch.draw(texture, x, y, 80, 80);
        spriteBatch.end();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
