package com.mcr.spaceshooter.Asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mcr.spaceshooter.Utils.Constants;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Asset {

    private static Asset asset = null;

    private Skin skin;

    private Music ambianceMusic;
    private Music gameoverMusic;
    private Music garageMusic;

    private Sound bulletSound;

    private BitmapFont font;

    private Texture leftArrowTexture;
    private Texture leftArrowPressedTexture;
    private Texture backgroundTexture;

    public final int MIN_ASTEROIDS_TEXTURES = 1;
    public final int MAX_ASTEROIDS_TEXTURES = 8;
    private List<Texture> asteroidsTexture;

    public final int MIN_FUSELAGES_TEXTURES = 1;
    public final int MAX_FUSELAGES_TEXTURES = 16;
    private List<Texture> fuselagesTexture;

    public final int MIN_SHIELDS_TEXTURES = 1;
    public final int MAX_SHIELDS_TEXTURES = 3;
    private List<Texture> shieldsTexture;

    public final int MIN_BULLETS_TEXTURES = 1;
    public final int MAX_BULLETS_TEXTURES = 16;
    private List<Texture> bulletsTexture;

    public final int MIN_WEAPONS_TEXTURES = 1;
    public final int MAX_WEAPONS_TEXTURES = 3;
    private List<Texture> weaponsTexture;

    private Asset() {
        load();
    }

    public static Asset getInstance() {
        if(asset == null) {
            asset = new Asset();
        }
        return asset;
    }

    private void load() {
        loadTextures();
        loadSounds();
        loadMusics();
        loadSkins();
    }

    private void loadSkins() {
        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
    }

    private void loadMusics() {
        ambianceMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/ambiance.mp3"));
        ambianceMusic.setVolume(Constants.AUDIO_LEVEL);
        ambianceMusic.setLooping(true);

        gameoverMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/gameover.ogg"));
        gameoverMusic.setVolume(Constants.AUDIO_LEVEL);

        garageMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/garage.ogg"));
        garageMusic.setVolume(Constants.AUDIO_LEVEL);
        garageMusic.setLooping(true);
    }

    private void loadSounds() {
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/7.wav"));
    }

    private void loadTextures() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("skin/Amble-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        font = generator.generateFont(parameter);
        generator.dispose();

        backgroundTexture = new Texture(Gdx.files.internal("bg.jpg"));
        leftArrowTexture = new Texture(Gdx.files.internal("leftArrow.png"));
        leftArrowPressedTexture = new Texture(Gdx.files.internal("leftArrow_pressed.png"));

        asteroidsTexture = new LinkedList<>();
        for (int i = MIN_ASTEROIDS_TEXTURES; i <= MAX_ASTEROIDS_TEXTURES; ++i) {
            asteroidsTexture.add(new Texture(Gdx.files.internal("asteroids/asteroid (" + i + ").png")));
        }

        fuselagesTexture = new LinkedList<>();
        for (int i = MIN_FUSELAGES_TEXTURES; i <= MAX_FUSELAGES_TEXTURES; ++i) {
            fuselagesTexture.add(new Texture(Gdx.files.internal("ships/fuselages/ship (" + i + ").png")));
        }

        shieldsTexture = new LinkedList<>();
        for (int i = MIN_SHIELDS_TEXTURES; i <= MAX_SHIELDS_TEXTURES; ++i) {
            shieldsTexture.add(new Texture(Gdx.files.internal("ships/shields/shield (" + i + ").png")));
        }

        weaponsTexture = new LinkedList<>();
        for (int i = MIN_WEAPONS_TEXTURES; i <= MAX_WEAPONS_TEXTURES; ++i) {
            weaponsTexture.add(new Texture(Gdx.files.internal("ships/weapons/weapon (" + i + ").png")));
        }

        bulletsTexture = new LinkedList<>();
        for (int i = MIN_BULLETS_TEXTURES; i <= MAX_BULLETS_TEXTURES; ++i) {
            bulletsTexture.add(new Texture(Gdx.files.internal("ships/bullets/bullet (" + i + ").png")));
        }
    }

    public Skin getSkin() {
        return skin;
    }

    public Music getAmbianceMusic() {
        return ambianceMusic;
    }

    public Music getGameoverMusic() {
        return gameoverMusic;
    }

    public Music getGarageMusic() {
        return garageMusic;
    }

    public Sound getBulletSound() {
        return bulletSound;
    }

    public BitmapFont getFont() {
        return font;
    }

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public  Texture getLeftArrowTexture() {
        return leftArrowTexture;
    }

    public  Texture getLeftArrowPressedTexture() {
        return leftArrowPressedTexture;
    }

    public Texture getAsteroidsTexture(int i) {
        inRange(MIN_ASTEROIDS_TEXTURES, MAX_ASTEROIDS_TEXTURES, i);
        return asteroidsTexture.get(i - 1);
    }

    public Texture getFuselagesTexture(int i) {
        inRange(MIN_FUSELAGES_TEXTURES, MAX_FUSELAGES_TEXTURES, i);
        return fuselagesTexture.get(i - 1);
    }

    public Texture getShieldsTexture(int i) {
        inRange(MIN_SHIELDS_TEXTURES, MAX_SHIELDS_TEXTURES, i);
        return shieldsTexture.get(i - 1);
    }

    public Texture getWeaponsTexture(int i) {
        inRange(MIN_WEAPONS_TEXTURES, MAX_WEAPONS_TEXTURES, i);
        return weaponsTexture.get(i - 1);
    }

    public Texture getBulletsTexture(int i) {
        inRange(MIN_BULLETS_TEXTURES, MAX_BULLETS_TEXTURES, i);
        return bulletsTexture.get(i - 1);
    }

    private void inRange(int lowerBound, int upperBound, int value) {
        if(value < lowerBound || value > upperBound) {
            throw new IllegalArgumentException(
                    String.format("Value %d not in range [%d; %d] !",
                            value, lowerBound, MAX_BULLETS_TEXTURES));
        }
    }
}
