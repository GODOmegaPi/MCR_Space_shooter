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

/**
 * Modélise une arme, attachée à un vaisseau. Une arme tire des bullets, qui détruisent des astéroïds
 * une fois entré en collision avec.
 * Deux armes se différencient par leurs vitesse de tirs.
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class Weapon extends OffensiveEquipment {

    // Bullets (balles) tirés par l'arme.
    private List<Bullet> bullets;

    private long lastTimeShot;

    // Vitesse de tir de l'arme.
    private final int shootSpeed;


    /**
     * Constructeur d'une arme.
     * @param name nom de l'arme.
     * @param texture texture de l'arme.
     * @param price prix de l'arme.
     * @param shootSpeed vitesse de tir de l'arme.
     */
    public Weapon(String name, Texture texture, int price, int shootSpeed) {
        super(name, texture, price);
        bullets = new LinkedList<>();
        lastTimeShot = 0;
        this.shootSpeed = shootSpeed;
    }

    /**
     * Constructeur copiant les attributs d'une autre arme.
     * @param weapon arme à copier.
     */
    public Weapon(Weapon weapon){
        super(weapon);
        bullets = new LinkedList<>();
        lastTimeShot = weapon.lastTimeShot;
        shootSpeed = weapon.shootSpeed;
    }

    /**
     * L'arme effectue un tir.
     * @param x position sur l'axe des abscisse depuis laquelle l'arme tire
     * @param y position sur l'axe des ordonnées depuis laquelle l'arme tire.
     */
    public void shoot(float x, float y) {
        if(canShoot()) {
            bullets.add(new Bullet(x + Spaceship.SIZE / 2, y, Bullet.BULLET_SPEED));
        }
    }

    /**
     * L'arme effectue pluseurs tirs simultanés
     * @param x position sur l'axe des abscisse depuis laquelle l'arme tire
     * @param y position sur l'axe des ordonnées depuis laquelle l'arme tire.
     * @param number nombre de bullets tirés simultanément.
     */
    public void shootMore(float x, float y, int number) {
        for(int i = 1; i <= number; ++i){
            shoot(x + (Spaceship.SIZE / number) * i, y);
        }

    }

    /**
     * @return vrai si l'arme peut tirer une nouvelle bullet.
     * Faux sinon.
     */
    private boolean canShoot() {
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastTimeShot >= shootSpeed) {
            lastTimeShot = currentTime;
            return true;
        }
        return false;
    }

    /**
     * @param rect rectangle potentiellement en collision avec l'une des bullets tirée par notre arme.
     * @return vrai si l'une des balle est en collision avec le rectangle donné.
     */
    public boolean isColliding(Rectangle rect) {
        boolean colliding = false;
        for(Bullet bullet : bullets) {
            if(colliding) break;
            colliding = bullet.isColliding(rect);
        }
        return colliding;
    }


    /**
     * Affiche les bullets tirés par l'arme.
     * @param spriteBatch objet premettant l'affichage de textures
     */
    public void render(SpriteBatch spriteBatch) {
        for(Bullet bullet : bullets){
            bullet.render(spriteBatch);
        }
    }

    /**
     * Met à jour les position de toutes la balles actives présentes dans l'espace de jeu.
     */
    public void update() {
        bullets = bullets.stream()
                .filter(b -> !b.isOutOfBound())
                .filter(Bullet::isAlive)
                .collect(Collectors.toList());

        for(Bullet bullet : bullets){
            bullet.update();
        }
    }

    /**
     * @return retourne la fréquence de tir de l'arme.
     */
    public int getShotFrequency(){
        return 1000 / shootSpeed;
    }
}