package com.mcr.spaceshooter.Entity.Equipements;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mcr.spaceshooter.Entity.Spaceship;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Weapon extends Equipment {

    private List<Bullet> bullets;
    private int attackSpeeder = 15;

    public Weapon(int hp, int credit) {
        super(hp, credit);
        bullets = new LinkedList<>();
    }

    public void shoot(float x, float y) {
        bullets.add(new Bullet(x + Spaceship.SIZE / 2, y, this.attackSpeeder));
    }

    // TODO possibility for a weapon to shoot multiple bullets (maybe make it that it's reload time is longer)
    /*public void shoot(float x, float y) {
        bullets.add(new Bullet(x + Spaceship.SIZE / 4, y, this.attackSpeeder));
        bullets.add(new Bullet(x + (Spaceship.SIZE / 4) * 3, y, this.attackSpeeder));
    }*/

    public boolean isColliding(Rectangle rect) {
        boolean colliding = false;
        for(Bullet bullet : bullets) {
            if(colliding) break;
            colliding = bullet.isColliding(rect);
        }
        return colliding;
    }
    

    public void render(SpriteBatch spriteBatch) {
        for(Bullet bullet : bullets){
            bullet.render(spriteBatch);
        }
    }

    public void update() {
        bullets = bullets.stream()
                .filter(b -> !b.isOutOfBound())
                .filter(Bullet::isAlive)
                .collect(Collectors.toList());

        for(Bullet bullet : bullets){
            bullet.update();
        }
    }
}
