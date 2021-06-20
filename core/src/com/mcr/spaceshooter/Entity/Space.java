package com.mcr.spaceshooter.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Modélise le monde dans lequel évolue notre vaisseau (Spaceship). Ce dernier tire des balles (Bullet),
 * afin de détruire des atrédoids (Asteroid) lors de collisions.
 * Ces astréoïds apparaissent aléatoirement dans le haut de l'écran et descendent vers le bas, avec un déplacement
 * latéral également aléatoire.
 * À noter que le nombre d'astréroïds aparaissant est croissant.
 * Chaque astroïds entrant en collision avec le vaisseau provoque des dommages à ce dernier.
 * Une partie est terminée lors-ce que le vaisseau n'a plus de point de vie (hp), qui lui sont procurés par
 * son fuselage (Fuselage) et, s'il en a un, son bouclier (Shield).
 *
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class Space {
    // Vaisseau, contrôlé par le joueur évoluant dans l'espace.
    private Spaceship spaceship;
    // Liste d'astréoids se déplaçant dans l'espace, pouvant endommager le vaisseau.
    private List<Asteroid> asteroids;
    // Nombre d'astéroïds présents en début de partie.
    private final int BASE_NB_ASTEROIDS = 20;
    // Nombre d'astéroids supplémentaires par palier de difficulté.
    private final int ADD_DIFFICULTY = 5;
    // Palier de difficulté. Augmente progressivement (en fonction du temps joué).
    private int difficulty = 0;
    private long lastDifficultyIncrease;
    // On augmente de palier chaque 5 secondes.
    private final long INCREASE_DIFFICULTY_TIME_MS = 5000;

    // Score du joueur. Le score correspond au nombre d'astéroïds détruits.
    private int score;

    /**
     * Constructeur de l'espace de jeu.
     * @param spaceship vaisseau contrôlé par le joueur
     */
    public Space(Spaceship spaceship) {
        this.spaceship = spaceship;
        asteroids = new LinkedList<>();
        generateAsteroids(BASE_NB_ASTEROIDS);
        score = 0;
        lastDifficultyIncrease = System.currentTimeMillis();
    }

    /**
     * @return retourne le vaisseau contrôlé par le joueur
     */
    public Spaceship getSpaceship() {
        return spaceship;
    }

    /**
     * @return retourn le score, càd le nombre d'astéroïds tués
     */
    public int getScore() {
        return score;
    }

    /**
     * Met à jour les position du vaisseau, des astéroids et des potentiels balles tirés.
     */
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

    /**
     * Maintient le nombre d'astéroids présents dans l'espace.
     * @param number
     */
    private void generateAsteroids(int number) {
        for(int i = asteroids.size(); i < number + difficulty * ADD_DIFFICULTY; ++i) {
            asteroids.add(new Asteroid());
        }
    }

    /**
     * Affiche tout ce qui est présent dans l'espace.
     * @param spriteBatch
     */
    public void render(SpriteBatch spriteBatch) {
        spaceship.render(spriteBatch);

        for(Asteroid asteroid : asteroids){
            asteroid.render(spriteBatch);
        }
    }

    /**
     * @return Vrai si game over. Un game over est provoqué quand le fuselage et le bouclier (s'il en a un)
     * d'un vaisseau valent 0.
     */
    public boolean isGameOver() {
        return spaceship.getFuselage().getHp() <= 0 && spaceship.getShieldHP() <=0;

    }

}