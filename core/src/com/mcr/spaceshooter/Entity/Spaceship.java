package com.mcr.spaceshooter.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mcr.spaceshooter.Builder.PlayableShipBuilder;
import com.mcr.spaceshooter.Entity.Equipments.Shield;
import com.mcr.spaceshooter.Entity.Equipments.Weapon;
import com.mcr.spaceshooter.Entity.Equipments.Fuselage;

/**
 * Classe représentant le vaisseau spatial contrôlé par le joueur.
 * Un vaisseau possède un fuselage et potentiellement un bouclier. En cas de collision avec un astéroïdes,
 * les dégats provoqués seront reportés sur le bouclier. Cependant, si le vaisseau ne possède pas de bouclier ou
 * que ce dernier ne possède plus de point de vie, les dégats seront occasionés sur le fuselage.
 * Le vaisseau possède également une arme qui tire des bullets.
 *
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 * */
public class Spaceship {
    // Hitbox et position du vaisseau.
    private Rectangle bounds;
    // Vitesse de déplacement du vaisseau.
    private int speed;
    // Arme du vaisseau.
    private Weapon weapon;
    // Bouclier optionnel du vaisseau.
    private Shield shield;
    // Fuselage du vaisseau
    private Fuselage fuselage;
    // Cheatcode. Appuyez simultanément sur "PIOU" en jeu.
    private boolean unlimitedPower;

    // Dimension de la hitbox du vaisseau. On considère sa hitbox comme un carré de côté SIZE.
    public static final int SIZE = 50;
    // Vitesse de déplacement du vaisseau.
    private static final int SPEED  = 5;

    /**
     * Constructeur du vaisseau. Il prend en paramètre le builder qui lui fournira les différents éléments du vaisseau
     * @param playableShipBuilder builder de vaisseau
     */
    public Spaceship(PlayableShipBuilder playableShipBuilder) {
        fuselage = new Fuselage(playableShipBuilder.getFuselage());
        weapon = new Weapon(playableShipBuilder.getWeapon());

        // Le vaisseau peut ne pas avoir de bouclier.
        if(playableShipBuilder.getShield() != null){
            shield = new Shield(playableShipBuilder.getShield());
        }

        bounds = new Rectangle(Gdx.graphics.getWidth() / 2, 50, SIZE, SIZE);
        unlimitedPower = false;
        speed = SPEED;
    }

    /**
     * Cette méthode permet de vérifier si un élement (asteroid) rentre en collision avec notre vaisseau ou si une des balles
     * touche un asteroid.
     * @param rect hitbox de l'élément qu'on essaie de vérifier.
     * @return vrai s'il y a collision.
     */
    public boolean isColliding(Rectangle rect) {
        boolean hitBySpaceship = false;
        if(Intersector.overlaps(bounds, rect)){
            hit(Asteroid.DAMAGE);
            hitBySpaceship = true;
        }
        return weapon.isColliding(rect) || hitBySpaceship;
    }

    /**
     * Méthode faisant tirer l'arme du vaisseau
     */
    public void shoot() {
        if(!unlimitedPower) {
            weapon.shoot(getX(), getY());
        } else {
        // cheatcode
            weapon.shootMore(getX(), getY(), 4);
        }
    }

    /**
     * Méthode appeler lorsqu'un vaisseau s'est fait touché et doit donc prendre des dégats
     * Soit sur son shield ou directement sur son fuselage (sa vie )
     * @param damage Dommage pris
     */
    public void hit(int damage){
        int shieldHP = getShieldHP();

        shieldHP -= damage;

        // S'il n'y a plus ou pas de shield on retire les dommages directement sur le vaisseau.
        if(shieldHP <= 0){
            fuselage.setHp(fuselage.getHp() + shieldHP);
            shieldHP = 0;
        }

        if(shield != null){
            shield.setHp(shieldHP);

        }
    }

    /**
     * Affiche le vaisseau via le spritebatch
     * @param spriteBatch qui sera utilisé pour dessiner le vaisseau
     */
    public void render(SpriteBatch spriteBatch) {
        weapon.render(spriteBatch);

        spriteBatch.begin();
        spriteBatch.draw(fuselage.getTexture(), getX(), getY(), bounds.getWidth(), bounds.getHeight());

        if(shield != null && shield.getHp() > 0) {
            spriteBatch.draw(shield.getTexture(), getX() - 10, getY() - 10, bounds.getWidth() + 20, bounds.getHeight() + 20);
        }
        spriteBatch.end();
    }

    /**
     * Retourne la position X du vaisseau(de la hitbox du vaisseau)
     * @return
     */
    public float getX() {
        return bounds.getX();
    }

    /**
     * Retour la position Y du vaisseau (de la hitbox du vaisseau)
     * @return
     */
    public float getY() {
        return bounds.getY();
    }

    /**
     * Retourne le nombre de point de vie de notre shield(s'il y en a un)
     * @return
     */
    public int getShieldHP() {
        if(shield != null){
            return shield.getHp();
        }
        return 0;
    }

    /**
     * Méthode permettant de mettre à jour le vaiseau en fonction des inteactions du joueur (des touches appuyées)
     *
     */
    public void update() {
        // Gestion des déplacement
        if(Gdx.input.isKeyPressed(Keys.A)){
            bounds.setX(getX() - speed);
        }
        if(Gdx.input.isKeyPressed(Keys.D)){
            bounds.setX(getX() + speed);
        }
        if(Gdx.input.isKeyPressed(Keys.W)){
            bounds.setY(getY() + speed);
        }
        if(Gdx.input.isKeyPressed(Keys.S)){
            bounds.setY(getY() - speed);
        }
        // Gestion du tir
        if(Gdx.input.isKeyPressed(Keys.SPACE)){
            shoot();
        }

        cheatCode();

        // Gestion du déplacement "torique" (eg: quand on sort à droite de l'écran on réapparait à gauche).
        if(bounds.getX() > Gdx.graphics.getWidth()){
            bounds.setX(-bounds.width);
        }else if(bounds.getX() < -bounds.getWidth()){
            bounds.setX(Gdx.graphics.getWidth());
        }

        if(bounds.getY() > Gdx.graphics.getHeight()){
            bounds.setY(-bounds.height);
        }else if(bounds.getY() < -bounds.getHeight()){
            bounds.setY(Gdx.graphics.getHeight());
        }

        weapon.update();
    }

    /**
     * Gestion de l'utilisation du cheatcode.
     */
    private void cheatCode() {
        if (
                Gdx.input.isKeyPressed(Keys.P)
                        && Gdx.input.isKeyPressed(Keys.I)
                        && Gdx.input.isKeyPressed(Keys.O)
                        && Gdx.input.isKeyPressed(Keys.U)
        ) {
            unlimitedPower = true;
        }
    }

    /**
     * Retourne le fuselage.
     * @return le fuselage actuellement équipé
     */
    public Fuselage getFuselage() {
        return fuselage;
    }

    /**
     * Retourne l'arme
     * @return l'arme actuellement équipé
     */
    public Weapon getWeapon() {
        return weapon;
    }

}
