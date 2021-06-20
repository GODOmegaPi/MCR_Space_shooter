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
     * @return récupère l'instance de la classe
     */
    public static Asset getInstance() {
        if(asset == null) {
            asset = new Asset();
        }
        return asset;
    }

    /**
     * Charge chaque asset par catégorie
     */
    private void load() {
        loadTextures();
        loadSounds();
        loadMusics();
        loadSkins();
    }

    /**
     * Libère chaque asset par catégorie
     */
    public void unload() {
        unloadTextures();
        unloadSounds();
        unloadMusics();
        unloadSkins();
    }

    /**
     * Charge le skin pour les objets du style labels, boutons, ...
     */
    private void loadSkins() {
        skin = new Skin(getFile(Constants.SKIN_PATH));
    }

    /**
     * Libère le skin
     */
    private void unloadSkins() {
        skin.dispose();
    }

    /**
     * Charge les musiques
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
     * Libère les musiques
     */
    private void unloadMusics() {
        ambianceMusic.dispose();
        gameoverMusic.dispose();
        garageMusic.dispose();
    }

    /**
     * Charge les sons
     */
    private void loadSounds() {
        bulletSound = Gdx.audio.newSound(getFile(Constants.BULLET_SOUND_PATH));
    }

    /**
     * Libère les sons
     */
    private void unloadSounds() {
        bulletSound.dispose();
    }

    /**
     * Charge les textures
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
     * Libère les textures
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
     * Charge le fichier spécifié par path
     * @param path le chemin du fichier à charger (depuis le dossier core/assets/)
     * @return le fichier chargé
     */
    public static FileHandle getFile(String path) {
        return Gdx.files.internal(path);
    }

    /**
     * Charge le fichier spécifié par path
     * @param path le chemin du fichier à charger (depuis le dossier core/assets/)
     * @param value la valeur à remplacer dans la variable path pour charger le fichier voulus
     * @return le fichier chargé
     */
    public static FileHandle getFile(String path, int value) {
        return Gdx.files.internal(String.format(path, value));
    }

    /**
     * Récupère le skin
     * @return le skin
     */
    public Skin getSkin() {
        return skin;
    }

    /**
     * Récupère la musique d'ambiance
     * @return la musique d'ambiance
     */
    public Music getAmbianceMusic() {
        return ambianceMusic;
    }

    /**
     * Récupère la musique du gameover
     * @return la musique du gameover
     */
    public Music getGameoverMusic() {
        return gameoverMusic;
    }

    /**
     * Récupère la musique du garage
     * @return la musique du garage
     */
    public Music getGarageMusic() {
        return garageMusic;
    }

    /**
     * Récupère le son que fait une balle
     * @return le son que fait une balle
     */
    public Sound getBulletSound() {
        return bulletSound;
    }

    /**
     * Récupère la police d'écriture
     * @return la police d'écriture
     */
    public BitmapFont getFont() {
        return font;
    }

    /**
     * Récupère l'image de l'arrière plan
     * @return l'image de l'arrière plan
     */
    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    /**
     * Récupère l'image de la flèche pointant la gauche
     * @return l'image de la flèche pointant la gauche
     */
    public  Texture getLeftArrowTexture() {
        return leftArrowTexture;
    }

    /**
     * Récupère l'image de la flèche, lorsqu'appuyée, pointant la gauche
     * @return l'image de la flèche, lorsqu'appuyée, pointant la gauche
     */
    public  Texture getLeftArrowPressedTexture() {
        return leftArrowPressedTexture;
    }

    /**
     * Récupère l'image d'un asteroide avec un index donné
     * @param i l'index de l'image
     * @return l'image de l'asteroide sélectionné
     * @throws IllegalArgumentException si l'index n'est pas dans la liste
     */
    public Texture getAsteroidsTexture(int i) {
        inRange(MIN_ASTEROIDS_TEXTURES, MAX_ASTEROIDS_TEXTURES, i);
        return asteroidsTexture.get(i - 1);
    }

    /**
     * Récupère l'image d'un fuselage avec un index donné
     * @param i l'index de l'image
     * @return l'image du fuselage sélectionné
     * @throws IllegalArgumentException si l'index n'est pas dans la liste
     */
    public Texture getFuselagesTexture(int i) {
        inRange(MIN_FUSELAGES_TEXTURES, MAX_FUSELAGES_TEXTURES, i);
        return fuselagesTexture.get(i - 1);
    }

    /**
     * Récupère l'image d'un bouclier avec un index donné
     * @param i l'index de l'image
     * @return l'image du bouclier sélectionné
     * @throws IllegalArgumentException si l'index n'est pas dans la liste
     */
    public Texture getShieldsTexture(int i) {
        inRange(MIN_SHIELDS_TEXTURES, MAX_SHIELDS_TEXTURES, i);
        return shieldsTexture.get(i - 1);
    }

    /**
     * Récupère l'image d'une arme avec un index donné
     * @param i l'index de l'image
     * @return l'image de l'arme sélectionné
     * @throws IllegalArgumentException si l'index n'est pas dans la liste
     */
    public Texture getWeaponsTexture(int i) {
        inRange(MIN_WEAPONS_TEXTURES, MAX_WEAPONS_TEXTURES, i);
        return weaponsTexture.get(i - 1);
    }

    /**
     * Récupère l'image d'une balle avec un index donné
     * @param i l'index de l'image
     * @return l'image de la balle sélectionné
     * @throws IllegalArgumentException si l'index n'est pas dans la liste
     */
    public Texture getBulletsTexture(int i) {
        inRange(MIN_BULLETS_TEXTURES, MAX_BULLETS_TEXTURES, i);
        return bulletsTexture.get(i - 1);
    }

    /**
     * Vérifie si une valeur est dans un interval donné
     * @param lowerBound la borne inférieur comprise
     * @param upperBound la borne supérieur comprise
     * @param value la valeur à vérifier
     * @throws IllegalArgumentException si la valeur n'est pas dans l'interval
     */
    private void inRange(int lowerBound, int upperBound, int value) {
        if(value < lowerBound || value > upperBound) {
            throw new IllegalArgumentException(
                    String.format("Value %d not in range [%d; %d] !",
                            value, lowerBound, MAX_BULLETS_TEXTURES));
        }
    }
}
