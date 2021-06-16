package com.mcr.spaceshooter.Entity.Equipements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    public void shoot(int x, int y) {
        bullets.add(new Bullet(x, y, this.attackSpeeder));
    }

    public void render(SpriteBatch spriteBatch) {
        bullets = bullets.stream()
                .filter(b -> b.isOutOfBound())
                .collect(Collectors.toList());

        for(Bullet bullet : bullets){
            bullet.render(spriteBatch);
            if(bullet.isOutOfBound()) {
                bullets.remove(bullet);
                
            }
        }
    }
}
