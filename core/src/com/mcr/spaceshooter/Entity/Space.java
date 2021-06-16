package com.mcr.spaceshooter.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcr.spaceshooter.Entity.Equipements.Bullet;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Space {

    private Spaceship spaceship;
    private List<Asteroid> asteroids;
    private static int N_ASTEROIDS;

    public Space() {
           spaceship = new Spaceship(50, 50, 5);
           asteroids = new LinkedList<>();
           generateAsteroids(10);
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void update() {
        boolean ded = false;
        for(Asteroid asteroid: asteroids) {
            if(spaceship.isColliding(asteroid.getBounds())){
                ded = true;
            }
        }

    }

    private void generateAsteroids(int number) {
        for(int i = asteroids.size(); i < number; ++i) {
            asteroids.add(new Asteroid());
        }
    }

    public void render(SpriteBatch spriteBatch) {
        spaceship.render(spriteBatch);

        asteroids = asteroids.stream()
                .filter(a -> !a.isOutOfBound())
                .collect(Collectors.toList());

        generateAsteroids(20);

        for(Asteroid asteroid : asteroids){
            asteroid.render(spriteBatch);

        }
    }

}
