package com.mcr.spaceshooter.Asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mcr.spaceshooter.Utils.Constants;

import java.util.LinkedList;
import java.util.List;

public class Asset {

    private static Asset asset = null;

    private Skin skin;

    private Music ambianceMusic;
    private Music gameoverMusic;
    private Music garageMusic;

    private Sound bulletSound;

    private Texture backgroundTexture;

    public final int MIN_ASTEROIDS_TEXTURES = 1;
    public final int MAX_ASTEROIDS_TEXTURES = 8;
    private List<Texture> asteroidsTexture;

    public final int MIN_COCKPITS_TEXTURES = 1;
    public final int MAX_COCKPITS_TEXTURES = 16;
    private List<Texture> cockpitsTexture;

    public final int MIN_WEAPONS_TEXTURES = 1;
    public final int MAX_WEAPONS_TEXTURES = 48;
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
        // bulletSound.setVolume(bulletSound.play(), Constants.AUDIO_LEVEL);
    }

    private void loadTextures() {
        backgroundTexture = new Texture(Gdx.files.internal("bg.jpg"));

        asteroidsTexture = new LinkedList<>();
        for (int i = MIN_ASTEROIDS_TEXTURES; i <= MAX_ASTEROIDS_TEXTURES; ++i) {
            asteroidsTexture.add(new Texture(Gdx.files.internal("asteroids/asteroid (" + i + ").png")));
        }

        cockpitsTexture = new LinkedList<>();
        for (int i = MIN_COCKPITS_TEXTURES; i <= MAX_COCKPITS_TEXTURES; ++i) {
            cockpitsTexture.add(new Texture(Gdx.files.internal("ships/cockpits/ship (" + i + ").png")));
        }

        weaponsTexture = new LinkedList<>();
        for (int i = MIN_WEAPONS_TEXTURES; i <= MAX_WEAPONS_TEXTURES; ++i) {
            weaponsTexture.add(new Texture(Gdx.files.internal("ships/weapons/weapon (" + i + ").png")));
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

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public Texture getAsteroidsTexture(int i) {
        inRange(MIN_ASTEROIDS_TEXTURES, MAX_ASTEROIDS_TEXTURES, i);
        return asteroidsTexture.get(i - 1);
    }

    public Texture getCockpitsTexture(int i) {
        inRange(MIN_COCKPITS_TEXTURES, MAX_COCKPITS_TEXTURES, i);
        return cockpitsTexture.get(i - 1);
    }

    public Texture getWeaponsTexture(int i) {
        inRange(MIN_WEAPONS_TEXTURES, MAX_WEAPONS_TEXTURES, i);
        return weaponsTexture.get(i - 1);
    }

    private void inRange(int lowerBound, int upperBound, int value) {
        if(value < lowerBound || value > MAX_WEAPONS_TEXTURES) {
            throw new IllegalArgumentException(
                    String.format("Value %d not in range [%d; %d] !",
                            value, lowerBound, MAX_WEAPONS_TEXTURES));
        }
    }
}
