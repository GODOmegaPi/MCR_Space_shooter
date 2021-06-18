package com.mcr.spaceshooter.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mcr.spaceshooter.Asset.Asset;
import com.mcr.spaceshooter.Entity.Equipements.Shield;
import com.mcr.spaceshooter.Entity.Equipements.Weapon;

public class Spaceship {
    private Rectangle bounds;
    private int speed;
    private Weapon weapon;
    private Shield shield;
    private int HP;
    private boolean unlimitedPower;

    public static final int SIZE = 50;
    private static final int MAX_HP = 100;

    public Spaceship(int x, int y, int speed) {
         bounds = new Rectangle(x, y, SIZE, SIZE);
         this.speed = speed;
         weapon = new Weapon(50, 50);
         shield = new Shield(50, 50);
         HP = MAX_HP;
         unlimitedPower = false;
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
        int shieldPV = shield.getHp();
        shieldPV -= damage;

        if(shieldPV < 0){
            HP += shieldPV;
        }

        shield.setHp(shieldPV);
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(Asset.getInstance().getCockpitsTexture(8), getX(), getY(), bounds.getWidth(), bounds.getHeight());
        spriteBatch.end();

        weapon.render(spriteBatch);
    }

    public float getX() {
        return bounds.getX();
    }

    public float getY() {
        return bounds.getY();
    }

    public int getHP() {
        return HP;
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

        weapon.update();
    }

    private void cheatCode() {
        if(
                Gdx.input.isKeyPressed(Keys.P)
                && Gdx.input.isKeyPressed(Keys.I)
                && Gdx.input.isKeyPressed(Keys.O)
                && Gdx.input.isKeyPressed(Keys.U)
        ) {
            unlimitedPower = true;
        }
    }
    }
        return fuselage;
    public Fuselage getFuselage() {
    }
        return weapon;
    public Weapon getWeapon() {
    public Spaceship(PlayableShipBuilder playableShipBuilder) {
        currentHp = playableShipBuilder.getCurrentHp();
        fuselage = playableShipBuilder.getFuselage();
        weapon = playableShipBuilder.getWeapon();
        shield = playableShipBuilder.getShield();
    }
    private Fuselage fuselage;
}
