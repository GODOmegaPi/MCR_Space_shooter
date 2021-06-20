package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mcr.spaceshooter.Entity.Spaceship;
import com.mcr.spaceshooter.Utils.Asset;
import com.mcr.spaceshooter.Entity.Space;

/**
 * Classe utilisée pour créer les éléments GUI (statistiques de partie) et les afficher sur le jeu
 *
 * @authors Ilias, Guillaume, Ludovic, Vitor, Eric
 */
public class SpaceRenderer {
    private final Space space;

    private int width;
    private int height;

    /**
     * Constructeur de copie
     *
     * @param spaceship l'entité spaceship représentant le spaceShip
     */
    public SpaceRenderer(Spaceship spaceship) {
        space = new Space(spaceship);
    }

    public Space getSpace() {
        return space;
    }

    /**
     * Affiche le fond d'écran
     * @param spriteBatch l'objet sur lequel la texture du fond d'écran va être affichée
     */
    public void renderBackground(SpriteBatch spriteBatch) {
        spriteBatch.draw(Asset.getInstance().getBackgroundTexture(), 0, 0);
    }

    /**
     * Affiche la GUI (points de vie du vaisseau, points de vie du bouclier et score)
     * @param spriteBatch l'objet sur lequel les différents éléments GUI vont être affichés
     */
    public void renderGUI(SpriteBatch spriteBatch) {
        int shieldHP = space.getSpaceship().getShieldHP();
        int spaceshipHP = space.getSpaceship().getFuselage().getHp();
        int score = space.getScore();

        // Formatage des labels
        Label hpLabel = new Label(String.format("PV       : %d", spaceshipHP), Asset.getInstance().getSkin());
        hpLabel.setPosition(width - 225, height - 50);

        Label shieldLabel = new Label(String.format("SHIELD : %d", shieldHP), Asset.getInstance().getSkin());
        shieldLabel.setPosition(width - 225, height - 100);

        Label scoreLabel = new Label(String.format("Score : %d", score), Asset.getInstance().getSkin());
        scoreLabel.setPosition(20, height - 50);

        hpLabel.draw(spriteBatch, 1);
        shieldLabel.draw(spriteBatch, 1);
        scoreLabel.draw(spriteBatch, 1);
    }

    /**
     * Définit la taille de l'écran de jeu
     * @param w largeur
     * @param h hauteur
     */
    public void setSize(int w, int h) {
        width = w;
        height = h;
    }

    /**
     * Met à jour le SpaceRenderer
     */
    public void update() {
        space.update();
    }

    /**
     * Affiche les éléments sur le SpriteBatch
     * @param spriteBatch l'objet sur lequel vont s'afficher les différentes textures des éléments
     */
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        renderBackground(spriteBatch);
        spriteBatch.end();

        space.render(spriteBatch);

        spriteBatch.begin();
        renderGUI(spriteBatch);
        spriteBatch.end();
    }
}