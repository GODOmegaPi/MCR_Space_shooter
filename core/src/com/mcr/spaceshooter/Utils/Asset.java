package com.mcr.spaceshooter.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

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

    public void unload() {
        unloadTextures();
        unloadSounds();
        unloadMusics();
        unloadSkins();
    }

    private void loadSkins() {
        skin = new Skin(getFile(Constants.SKIN_PATH));
    }

    private void unloadTextures() {
        skin.dispose();
    }

    private void loadMusics() {
        ambianceMusic = Gdx.audio.newMusic(getFile(Constants.AMBIANCE_MUSIC_PATH));
        ambianceMusic.setVolume(Constants.AUDIO_LEVEL);
        ambianceMusic.setLooping(true);

        gameoverMusic = Gdx.audio.newMusic(getFile(Constants.GAMEOVER_MUSIC_PATH));
        gameoverMusic.setVolume(Constants.AUDIO_LEVEL);

        garageMusic = Gdx.audio.newMusic(getFile(Constants.GARAGE_MUSIC_PATH));
        garageMusic.setVolume(Constants.AUDIO_LEVEL);
        garageMusic.setLooping(true);
    }

    private void unloadMusics() {
        ambianceMusic.dispose();
        gameoverMusic.dispose();
        garageMusic.dispose();
    }

    private void loadSounds() {
        bulletSound = Gdx.audio.newSound(getFile(Constants.BULLET_SOUND_PATH));
    }

    private void unloadSounds() {
        bulletSound.dispose();
    }

    private void loadTextures() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(getFile(Constants.FONT_PATH));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
        font = generator.generateFont(parameter);
        generator.dispose();

        backgroundTexture = new Texture(getFile(Constants.BACKGROUND_TEXTURE_PATH));
        leftArrowTexture = new Texture(getFile(Constants.LEFT_ARROW_TEXTURE_PATH));
        leftArrowPressedTexture = new Texture(getFile(Constants.LEFT_ARROW_PRESSED_TEXTURE_PATH));

        asteroidsTexture = new LinkedList<>();
        for (int i = MIN_ASTEROIDS_TEXTURES; i <= MAX_ASTEROIDS_TEXTURES; ++i) {
            asteroidsTexture.add(new Texture(getFile(Constants.ASTEROIDS_TEXTURE_PATH, i)));
        }

        fuselagesTexture = new LinkedList<>();
        for (int i = MIN_FUSELAGES_TEXTURES; i <= MAX_FUSELAGES_TEXTURES; ++i) {
            fuselagesTexture.add(new Texture(getFile(Constants.FUSELAGES_TEXTURE_PATH, i)));
        }

        shieldsTexture = new LinkedList<>();
        for (int i = MIN_SHIELDS_TEXTURES; i <= MAX_SHIELDS_TEXTURES; ++i) {
            shieldsTexture.add(new Texture(getFile(Constants.SHIELDS_TEXTURE_PATH, i)));
        }

        weaponsTexture = new LinkedList<>();
        for (int i = MIN_WEAPONS_TEXTURES; i <= MAX_WEAPONS_TEXTURES; ++i) {
            weaponsTexture.add(new Texture(getFile(Constants.WEAPONS_TEXTURE_PATH, i)));
        }

        bulletsTexture = new LinkedList<>();
        for (int i = MIN_BULLETS_TEXTURES; i <= MAX_BULLETS_TEXTURES; ++i) {
            bulletsTexture.add(new Texture(getFile(Constants.BULLETS_TEXTURE_PATH, i)));
        }
    }

    private void unloadSkins() {
        font.dispose();
        backgroundTexture.dispose();
        leftArrowTexture.dispose();
        leftArrowPressedTexture.dispose();

        asteroidsTexture.forEach(Texture::dispose);
        fuselagesTexture.forEach(Texture::dispose);
        shieldsTexture.forEach(Texture::dispose);
        weaponsTexture.forEach(Texture::dispose);
        bulletsTexture.forEach(Texture::dispose);
    }

    public static FileHandle getFile(String path) {
        return Gdx.files.internal(path);
    }

    public static FileHandle getFile(String path, int value) {
        return Gdx.files.internal(String.format(path, value));
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
