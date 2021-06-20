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

/**
 * Classe utilisée pour charger les assets dans la mémoire et offrir un moyen de les récupérer
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
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

    /**
     * Constructeur privé qui charge tous les assets
     */
    private Asset() {
        load();
    }

    /**
     * Récupère le singleton de la classe
     * @return the current instance
     */
    public static Asset getInstance() {
        if(asset == null) {
            asset = new Asset();
        }
        return asset;
    }

    /**
     * Load each assets by category
     */
    private void load() {
        loadTextures();
        loadSounds();
        loadMusics();
        loadSkins();
    }

    /**
     * Unload each assets by category
     */
    public void unload() {
        unloadTextures();
        unloadSounds();
        unloadMusics();
        unloadSkins();
    }

    /**
     * Load the skin used to format objects like labels, buttons, ...
     */
    private void loadSkins() {
        skin = new Skin(getFile(Constants.SKIN_PATH));
    }

    /**
     * Unload the skin used to format objects like labels, buttons, ...
     */
    private void unloadSkins() {
        skin.dispose();
    }

    /**
     * Load the musics
     */
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

    /**
     * Unload the musics
     */
    private void unloadMusics() {
        ambianceMusic.dispose();
        gameoverMusic.dispose();
        garageMusic.dispose();
    }

    /**
     * Load the sounds
     */
    private void loadSounds() {
        bulletSound = Gdx.audio.newSound(getFile(Constants.BULLET_SOUND_PATH));
    }

    /**
     * Unload the sounds
     */
    private void unloadSounds() {
        bulletSound.dispose();
    }

    /**
     * Load the textures
     */
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

    /**
     * Unload the textures
     */
    private void unloadTextures() {
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

    /**
     * Get the file with given path
     * @param path the path to the file from assets folder
     * @return the loaded file
     */
    public static FileHandle getFile(String path) {
        return Gdx.files.internal(path);
    }

    /**
     * Get the file with given path
     * @param path the path to the file from assets folder
     * @param value a value needed to be replaced in the given path
     * @return the loaded file
     */
    public static FileHandle getFile(String path, int value) {
        return Gdx.files.internal(String.format(path, value));
    }

    /**
     * Get the skin
     * @return the skin
     */
    public Skin getSkin() {
        return skin;
    }

    /**
     * Get the ambiance music
     * @return the ambiance music
     */
    public Music getAmbianceMusic() {
        return ambianceMusic;
    }

    /**
     * Get the gameover music
     * @return the gameover music
     */
    public Music getGameoverMusic() {
        return gameoverMusic;
    }

    /**
     * Get the garage music
     * @return the garage music
     */
    public Music getGarageMusic() {
        return garageMusic;
    }

    /**
     * Get the bullet sound
     * @return the bullet sound
     */
    public Sound getBulletSound() {
        return bulletSound;
    }

    /**
     * Get the font
     * @return the font
     */
    public BitmapFont getFont() {
        return font;
    }

    /**
     * Get the background texture
     * @return the background texture
     */
    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    /**
     * Get the left arrow texture
     * @return the left texture arrow
     */
    public  Texture getLeftArrowTexture() {
        return leftArrowTexture;
    }

    /**
     * Get the left arrow pressed texture
     * @return the left arrow pressed texture
     */
    public  Texture getLeftArrowPressedTexture() {
        return leftArrowPressedTexture;
    }

    /**
     * Get an asteroid texture at given index
     * @param i the index of the texture
     * @return the asteroid at the given index
     * @throws IllegalArgumentException if the index is out of the list
     */
    public Texture getAsteroidsTexture(int i) {
        inRange(MIN_ASTEROIDS_TEXTURES, MAX_ASTEROIDS_TEXTURES, i);
        return asteroidsTexture.get(i - 1);
    }

    /**
     * Get a fuselage texture at given index
     * @param i the index of the texture
     * @return the fuselage at the given index
     * @throws IllegalArgumentException if the index is out of the list
     */
    public Texture getFuselagesTexture(int i) {
        inRange(MIN_FUSELAGES_TEXTURES, MAX_FUSELAGES_TEXTURES, i);
        return fuselagesTexture.get(i - 1);
    }

    /**
     * Get a shield texture at given index
     * @param i the index of the texture
     * @return the shield at the given index
     * @throws IllegalArgumentException if the index is out of the list
     */
    public Texture getShieldsTexture(int i) {
        inRange(MIN_SHIELDS_TEXTURES, MAX_SHIELDS_TEXTURES, i);
        return shieldsTexture.get(i - 1);
    }

    /**
     * Get a weapon texture at given index
     * @param i the index of the texture
     * @return the weapon at the given index
     * @throws IllegalArgumentException if the index is out of the list
     */
    public Texture getWeaponsTexture(int i) {
        inRange(MIN_WEAPONS_TEXTURES, MAX_WEAPONS_TEXTURES, i);
        return weaponsTexture.get(i - 1);
    }

    /**
     * Get a bullet texture at given index
     * @param i the index of the texture
     * @return the bullet at the given index
     * @throws IllegalArgumentException if the index is out of the list
     */
    public Texture getBulletsTexture(int i) {
        inRange(MIN_BULLETS_TEXTURES, MAX_BULLETS_TEXTURES, i);
        return bulletsTexture.get(i - 1);
    }

    /**
     * Check if a given value is in range
     * @param lowerBound the lower bound of the range
     * @param upperBound the upper bound of the range
     * @param value the value to check
     * @throws IllegalArgumentException if the index is out of the list
     */
    private void inRange(int lowerBound, int upperBound, int value) {
        if(value < lowerBound || value > upperBound) {
            throw new IllegalArgumentException(
                    String.format("Value %d not in range [%d; %d] !",
                            value, lowerBound, MAX_BULLETS_TEXTURES));
        }
    }
}
