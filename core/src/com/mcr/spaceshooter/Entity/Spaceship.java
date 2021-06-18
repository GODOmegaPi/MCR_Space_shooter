package com.mcr.spaceshooter.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mcr.spaceshooter.Asset.Asset;
import com.mcr.spaceshooter.Builder.PlayableShipBuilder;
import com.mcr.spaceshooter.Entity.Equipments.Shield;
import com.mcr.spaceshooter.Entity.Equipments.Weapon;
import com.mcr.spaceshooter.Entity.Equipments.Fuselage;

// TODO remove : import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Spaceship {
    private Rectangle bounds;
    private int speed;
    private Weapon weapon;
    private Shield shield;
    private Fuselage fuselage;
    // private int hp;
    private boolean unlimitedPower;

    public static final int SIZE = 50;
    private static final int SPEED  = 5;

    /*
    public Spaceship(int x, int y, int speed) {
         bounds = new Rectangle(x, y, SIZE, SIZE);
         this.speed = speed;
         weapon = new Weapon("", 50, 50);
         shield = new Shield("", 50, 50);
         hp = MAX_HP;
         unlimitedPower = false;
    }
     */

    public Spaceship(PlayableShipBuilder playableShipBuilder) {
        // hp = playableShipBuilder.getHp();
        fuselage = playableShipBuilder.getFuselage();
        weapon = playableShipBuilder.getWeapon();
        shield = playableShipBuilder.getShield();
        bounds = new Rectangle(Gdx.graphics.getWidth() / 2, 50, SIZE, SIZE);
        unlimitedPower = false;
        speed = SPEED;
    }

    public boolean isColliding(Rectangle rect) {
        boolean hitBySpaceship = false;
        if(Intersector.overlaps(bounds, rect)){
            hit(Asteroid.DAMAGE);
            hitBySpaceship = true;
        }
        return weapon.isColliding(rect) || hitBySpaceship;
    }

    public void shoot() {
        if(!unlimitedPower) {
            weapon.shoot(getX(), getY());
        } else {
            weapon.shootMore(getX(), getY(), 4);
        }
    }

    public void hit(int damage){
        int shieldHP = shield.getHp();
        shieldHP -= damage;

        if(shieldHP <= 0){
            fuselage.setHp(fuselage.getHp() + shieldHP);
            shieldHP = 0;
        }

        shield.setHp(shieldHP);
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(fuselage.getTexture(), getX(), getY(), bounds.getWidth(), bounds.getHeight());
        spriteBatch.end();

        weapon.render(spriteBatch);
    }

    public float getX() {
        return bounds.getX();
    }

    public float getY() {
        return bounds.getY();
    }

    public Shield getShield() {
        return shield;
    }

    public void update() {
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
        if(Gdx.input.isKeyPressed(Keys.SPACE)){
            shoot();
        }

        cheatCode();

        if(bounds.getX() + bounds.getWidth() >= Gdx.graphics.getWidth()){
            bounds.setX(Gdx.graphics.getWidth() - bounds.getWidth());
        }else if(bounds.getX() <= 0){
            bounds.setX(0);
        }

        if(bounds.getY() >= Gdx.graphics.getHeight()){
            bounds.setY(Gdx.graphics.getHeight() - bounds.getHeight());
        }else if(bounds.getY() <= 0){
            bounds.setY(0);
        }

        System.out.println("==");
        System.out.println("(" + bounds.getX() + ", " + bounds.getY() + ")");
        System.out.println("(" + Gdx.graphics.getWidth() + ", " + Gdx.graphics.getHeight() + ")");
        System.out.println("==");

        weapon.update();
    }

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

    public Fuselage getFuselage() {
        return fuselage;
    }

    public Weapon getWeapon() {
        return weapon;
    }

}
