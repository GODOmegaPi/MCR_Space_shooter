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
    private final long INCREASE_DIFFICULTY_TIME_MS = 5000;

    private int score;

    public Space(Spaceship spaceship) {
        this.spaceship = spaceship;
        asteroids = new LinkedList<>();
        generateAsteroids(BASE_NB_ASTEROIDS);
        score = 0;
        lastDifficultyIncrease = System.currentTimeMillis();
    }

    // TODO remove
/*    public void setSpaceship(Spaceship spaceship)
    {
        this.spaceship = spaceship;
    }*/

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public int getScore() {
        return score;
    }

    public void update() {
        for(Asteroid asteroid: asteroids) {
            asteroid.update();
            if(spaceship.isColliding(asteroid.getBounds())){
                asteroid.hit();
                ++score;
            }
        }

        spaceship.update();

        long currentTime = System.currentTimeMillis();
        if(currentTime - lastDifficultyIncrease >= INCREASE_DIFFICULTY_TIME_MS) {
            difficulty++;
            lastDifficultyIncrease = currentTime;
        }

        asteroids = asteroids.stream()
                .filter(a -> !a.isOutOfBound())
                .filter(a -> !a.isHit())
                .collect(Collectors.toList());

        generateAsteroids(BASE_NB_ASTEROIDS);
    }

    private void generateAsteroids(int number) {
        for(int i = asteroids.size(); i < number + difficulty * ADD_DIFFICULTY; ++i) {
            asteroids.add(new Asteroid());
        }
    }

    public void render(SpriteBatch spriteBatch) {
        spaceship.render(spriteBatch);

        for(Asteroid asteroid : asteroids){
            asteroid.render(spriteBatch);
        }
    }

    public boolean isGameOver() {
        return spaceship.getFuselage().getHp() <= 0 && spaceship.getShieldHP() <=0;

    }

}