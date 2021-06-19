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


public class Spaceship {
    private Rectangle bounds;
    private int speed;
    private Weapon weapon;
    private Shield shield;
    private Fuselage fuselage;
    private boolean unlimitedPower;

    public static final int SIZE = 50;
    private static final int SPEED  = 5;

    public Spaceship(PlayableShipBuilder playableShipBuilder) {
        fuselage = new Fuselage(playableShipBuilder.getFuselage());
        weapon = new Weapon(playableShipBuilder.getWeapon());
        if(playableShipBuilder.getShield() != null){
            shield = new Shield(playableShipBuilder.getShield());
        }

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
        int shieldHP = getShieldHP();

        shieldHP -= damage;

        if(shieldHP <= 0){
            fuselage.setHp(fuselage.getHp() + shieldHP);
            shieldHP = 0;
        }

        if(shield != null){
            shield.setHp(shieldHP);

        }
    }

    public void render(SpriteBatch spriteBatch) {
        weapon.render(spriteBatch);

        spriteBatch.begin();
        spriteBatch.draw(fuselage.getTexture(), getX(), getY(), bounds.getWidth(), bounds.getHeight());

        if(shield != null && shield.getHp() > 0) {
            spriteBatch.draw(shield.getTexture(), getX() - 10, getY() - 10, bounds.getWidth() + 20, bounds.getHeight() + 20);
        }
        spriteBatch.end();
    }

    public float getX() {
        return bounds.getX();
    }

    public float getY() {
        return bounds.getY();
    }

    public int getShieldHP() {
        if(shield != null){
            return shield.getHp();
        }
        return 0;
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
