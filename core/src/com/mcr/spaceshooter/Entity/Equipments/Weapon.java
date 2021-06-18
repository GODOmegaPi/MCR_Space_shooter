package com.mcr.spaceshooter.Entity.Equipments;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mcr.spaceshooter.Entity.Equipments.OffensiveEquipment;
import com.mcr.spaceshooter.Entity.Equipments.Bullet;
import com.mcr.spaceshooter.Entity.Spaceship;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Weapon extends OffensiveEquipment {

    private List<Bullet> bullets;
    private int attackSpeeder = 15;
    private long lastTimeShot;
    private final int shootSpeed;

    public Weapon(String name, Texture texture, int price, int shootSpeed) {
        super(name, texture, price);
        bullets = new LinkedList<>();
        lastTimeShot = 0;
        this.shootSpeed = shootSpeed;
    }

    public void shoot(float x, float y) {
        if(canShoot()) {
            bullets.add(new Bullet(x + Spaceship.SIZE / 2, y, this.attackSpeeder));
        }
    }

    public void shootMore(float x, float y, int number) {
        if(canShoot()) {
            for (int i = 1; i <= number; ++i) {
                bullets.add(new Bullet(x + (Spaceship.SIZE / number) * i, y, this.attackSpeeder));
            }
        }
    }

    private boolean canShoot() {
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastTimeShot >= shootSpeed) {
            lastTimeShot = currentTime;
            return true;
        }
        return false;
    }

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
    public int getShotFrequency(){
        return 1000/shootSpeed;
    }
}