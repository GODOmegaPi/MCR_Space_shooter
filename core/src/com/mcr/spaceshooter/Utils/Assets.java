package com.mcr.spaceshooter.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;


public class Assets implements Disposable {
    private static Assets instance;
    private AssetManager assetManager;

    public static Assets getInstance() {
        if (instance == null) {
            Gdx.app.debug(Assets.class.getName(), "Instancing...");
            instance = new Assets();
        }
        return instance;
    }

    private Assets() {
        assetManager = new AssetManager();
        load();

        assetManager.finishLoading();
    }

    private void load() {
        Gdx.app.debug(this.getClass().getName(), "Loading assets...");
        // Police de message d'erreur
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter parms = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms.fontFileName = "skin/Amble-Regular.ttf";    // path of .ttf file where that exist
        //parms.fontParameters.size = 10;
        assetManager.load("skin/Amble-Regular.ttf", BitmapFont.class, parms);
        //assetManager.load("skin/Amble-Regular.ttf", BitmapFont.class, parameter);

        // Skin
        assetManager.load("skin/craftacular-ui.json", Skin.class);

        // Fuselages
        assetManager.load("ss_1.png", Texture.class);
        assetManager.load("ss_2.png", Texture.class);
        assetManager.load("ss_3.png", Texture.class);

        // Weapons
        assetManager.load("wp_1.png", Texture.class);
        assetManager.load("wp_2.png", Texture.class);
        assetManager.load("wp_3.png", Texture.class);

        // Shields
        assetManager.load("sh_1.png", Texture.class);
        assetManager.load("sh_2.png", Texture.class);
        assetManager.load("sh_3.png", Texture.class);

        // Arrow Btn
        assetManager.load("leftArrow.png", Texture.class);
        assetManager.load("leftArrow_pressed.png", Texture.class);

    }

    public <T> T get(String name, Class<T> type) {
        Gdx.app.debug(this.getClass().getName(), "Accessing asset...");
        return assetManager.get(name, type);
    }

    @Override
    public void dispose() {
        Gdx.app.debug(this.getClass().getName(), "Assets freed");
        assetManager.dispose();
    }
}
