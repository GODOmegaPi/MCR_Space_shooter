package com.mcr.spaceshooter.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Space {
    private Spaceship spaceship;
    private List<Asteroid> asteroids;
    private final int BASE_NB_ASTEROIDS = 20;
    private final int ADD_DIFFICULTY = 5;
    private int difficulty = 0;
    private long lastDifficultyIncrease;
    private final long INCREASE_DIFFICULTY_TIME_MS = 10000;

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
           lastDifficultyIncrease = System.currentTimeMillis();
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

        long currentTime = System.currentTimeMillis();
        if(currentTime - lastDifficultyIncrease >= INCREASE_DIFFICULTY_TIME_MS) {
            difficulty++;
            lastDifficultyIncrease = currentTime;
        }
    }

    private void generateAsteroids(int number) {
        for(int i = asteroids.size(); i < number + difficulty * ADD_DIFFICULTY; ++i) {
            asteroids.add(new Asteroid());
        }
    }

    public void render(SpriteBatch spriteBatch) {
        spaceship.render(spriteBatch);

        asteroids = asteroids.stream()
                .filter(a -> !a.isOutOfBound())
                .filter(a -> !a.isHit())
                .collect(Collectors.toList());

        generateAsteroids(BASE_NB_ASTEROIDS);

        for(Asteroid asteroid : asteroids){
            asteroid.render(spriteBatch);
        }
    }

    public boolean isGameOver() {
        return spaceship.getHP() == 0;
    }

}