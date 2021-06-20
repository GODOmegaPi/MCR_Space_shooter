package com.mcr.spaceshooter.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcr.spaceshooter.Utils.Asset;
import com.mcr.spaceshooter.Utils.Rand;

import com.badlogic.gdx.math.Rectangle;

/**
 * Classe représentant un astéroïde, ennemis du jeu. Ils se déplacent depuis le haut de l'écran avec une vitesse et une
 * direction aléatoire. Dès lors qu'ils sont touchés par un missile tiré par le vaisseau du joueur ou qu'ils sortent
 * de l'écran, ils sont désintégrés
 *
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class Asteroid {
    private final int speed;
    private boolean outOfBound;
    private boolean hit;
    private Rectangle bounds;   // Hitbox de l'astéroïde
    private final int asteroidTextureId;
    private int dLateral;   // Delta pour déplacement en diagonale

    public static final int DAMAGE = 10;

    /**
     * Constructeur
     */
    public Asteroid() {
        speed = Rand.generateRandom(2, 3);
        dLateral = Rand.generateRandom(-3, 3);

        outOfBound = false;
        hit = false;
        asteroidTextureId = Rand.generateRandom(
                Asset.getInstance().MIN_ASTEROIDS_TEXTURES,
                Asset.getInstance().MAX_ASTEROIDS_TEXTURES
        );

        // Fait apparaître un astéroïde aléatoirement en haut de l'écran
        int size = Rand.generateRandom(50, 75);
        int x = Rand.generateRandom(size, Gdx.graphics.getWidth() - size);
        int y = Rand.generateRandom(
                Gdx.graphics.getHeight(),
                (int) (Gdx.graphics.getHeight() + Gdx.graphics.getHeight() * 0.5)
        );

        bounds = new Rectangle(x, y, size, size);
    }

    /**
     * Définit la hitbox de l'astéroïde et dessine l'astéroide
     *
     * @param spriteBatch l'objet permettant d'afficher la texture de l'astéroïde
     */
    public void render(SpriteBatch spriteBatch) {
        bounds.setY(bounds.getY() - speed);
        bounds.setX(bounds.getX() + dLateral);
        spriteBatch.begin();
        spriteBatch.draw(
                Asset.getInstance().getAsteroidsTexture(asteroidTextureId),
                bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()
        );
        spriteBatch.end();
    }

    /**
     * Vérifie que l'astéroïde est encore dans l'écran de jeu
     *
     * @return true si l'astéroïde est encore dans l'écran de jeu, false sinon
     */
    public boolean isOutOfBound() {
        return outOfBound;
    }

    /**
     * L'astéroïde est touché
     */
    public void hit() {
        hit = true;
    }

    /**
     * Vérifie si l'astéroïde a été touché
     *
     * @return true si l'astéroïde a été touché, false sinon
     */
    public boolean isHit() {
        return hit;
    }

    /**
     * @return la hitbox de l'astéroïde
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Met à jour la position de l'astéroïde
     */
    public void update() {
        bounds.setY(bounds.getY() - speed);
        if (bounds.getY() < 0) {
            outOfBound = true;
        }
    }
}
