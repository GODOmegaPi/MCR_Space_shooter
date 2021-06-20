package com.mcr.spaceshooter.Entity.Equipments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mcr.spaceshooter.Utils.Asset;
import com.mcr.spaceshooter.Utils.Constants;

/**
 * Implémentation d'une balle, tirée par une arme (Weapon).
 * Celle-ci a pour but de tuer un astéroide.
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class Bullet {

    // Position et dimension de la balle.
    private Rectangle bounds;

    private final int speed;
    private boolean outOfBound;

    private boolean alive;

    // Largeur de la hitbox.
    public static int WIDTH = 7;
    // Hauteur de la hitbox.
    public static int HEIGHT = 19;

    /**
     * Constructeur d'une balle.
     * @param x position initiale, sur l'axe des absicsse.
     * @param y position initale, sur l'axe des ordonnées.
     * @param speed vitesse de déplacement de la balle.
     */
    public Bullet(float x, float y, int speed) {
        this.speed = speed;
        Asset.getInstance().getBulletSound().play(Constants.AUDIO_LEVEL);

        outOfBound = false;
        alive = true;
        bounds = new Rectangle(x - WIDTH / 2, y, WIDTH, HEIGHT);
    }

    /**
     * Affiche la sprite de la balle.
     * @param spriteBatch
     */
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(
                Asset.getInstance().getBulletsTexture(1),
                bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()
        );
        spriteBatch.end();
    }

    public boolean isColliding(Rectangle rect) {
        if(Intersector.overlaps(bounds, rect)) {
            alive = false;
            return true;
        }
        return false;
    }

    boolean isOutOfBound(){
        return outOfBound;
    }

    boolean isAlive(){
        return alive;
    }

    public void update() {
        if(bounds.getY() + bounds.getHeight() > Gdx.graphics.getHeight()) {
            outOfBound = true;
        }

        bounds.setY(bounds.getY() + speed);
    }

}
