package com.mcr.spaceshooter.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Space {

    /**
     * TODO: Nombre croissant d'asteroids.
     */

    private Spaceship spaceship;
    private List<Asteroid> asteroids;
    private static int N_ASTEROIDS = 20;

    private int score;
    private int height;
    private int width;

    public Space(int width, int height) {
        spaceship = new Spaceship(50, 50, 5, width, height);
        asteroids = new LinkedList<>();
        generateAsteroids(N_ASTEROIDS / 2);
        score = 0;
        this.width = width;
        this.height = height;
        System.out.println("SPACE CREATED (" + width + ", "+ height + ")");
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public int getScore() {
        return score;
    }

    public void update() {
        for(Asteroid asteroid: asteroids) {
            if(spaceship.isColliding(asteroid.getBounds())){
                asteroid.hit();
                ++score;
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
                .filter(a -> !a.isHit())
                .collect(Collectors.toList());

        generateAsteroids(N_ASTEROIDS);

        for(Asteroid asteroid : asteroids){
            asteroid.render(spriteBatch);
        }
    }

    public boolean isGameOver() {
        return spaceship.getHP() == 0;
    }

}