package com.mcr.spaceshooter.Entity.Equipements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Weapon extends Equipment {

    private List<Bullet> bullets;
    private int attackSpeeder = 5;

    public Weapon(int hp, int credit) {
        super(hp, credit);
        bullets = new LinkedList<>();

    }

    public void shoot(float x, float y) {
        bullets.add(new Bullet(x, y, Bullet.SIZE, this.attackSpeeder));
    }

    public boolean isColliding(Rectangle rect) {
        boolean colliding = false;
        for(Bullet bullet : bullets) {
            if(colliding) break;
            colliding = bullet.isColliding(rect);
            // colliding = !colliding ? bullet.isColliding(rect) : colliding;
        }
        return colliding;
    }
    

    public void render(SpriteBatch spriteBatch) {
        //TODO: Lamda probablement plus intelligent à faire

        bullets = bullets.stream()
                .filter(b -> !b.isOutOfBound())
                .filter(Bullet::isAlive)
                .collect(Collectors.toList());


        for(Bullet bullet : bullets){
            bullet.render(spriteBatch);
        }
    }
}
