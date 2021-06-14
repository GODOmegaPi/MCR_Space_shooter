package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Logger;
import com.mcr.spaceshooter.Entity.Equipements.Equipment;
import com.mcr.spaceshooter.ScreenManager;
import com.sun.tools.javac.util.Pair;
import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ConfigRow {
    // TODO voir ce qui peut etre static  eg: btnTex restera toujours le même (comme defaultEuiqpementTex)
    private Table table;
    private Image imgEquipement;
    private ImageButton leftArrowBtn;
    private ImageButton rightArrowBtn;
    private Texture btnTex;
    private List<Pair<Equipment, Texture>> equipments;
    private ListIterator currentPosition;
    private Pair<Equipment, Texture> currentEquipement;
    private static Texture defaultEquipementTex = new Texture(Gdx.files.internal("noEquipement.jpg"));

    public ConfigRow(Table table, List<Pair<Equipment, Texture>> equipments) {

        this.equipments = equipments;
        this.table = table;
        this.init();
    }
    private void changeEquipment(Pair<Equipment, Texture> equipement){
        imgEquipement.setDrawable(new TextureRegionDrawable(equipement.snd));
    }


    private void init() {
        btnTex = new Texture(Gdx.files.internal("leftArrow.png"));
        leftArrowBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnTex)));
        leftArrowBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Si la position courrante n'a pas encore été setté (autrement dit, au avant tout click sur les boutons, on affiche l'image par défaut (/))
                // ou si il n'y a pas de prochain élement on retourne au début
                if (currentPosition == null || !currentPosition.hasPrevious()) {
                    // On sette l'itérateur comment étant le dernières élements.
                    currentPosition = equipments.listIterator(equipments.size());
                    Gdx.app.debug(this.getClass().getName(),"null iterator ou n'a pas de prochain élement");
                }
                Pair<Equipment, Texture> old = currentEquipement;
                currentEquipement = (Pair<Equipment, Texture>) currentPosition.previous();
                if(currentEquipement == old){
                    currentEquipement = (Pair<Equipment, Texture>) currentPosition.previous();
                }
                // TODO voir s'il y a mieux que caster.
                changeEquipment(currentEquipement);

            }
        });
        rightArrowBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnTex)));
        rightArrowBtn.addListener(new ClickListener() {
            @Override

            // TODO Utiliser autre chose que les itérateurs car trop chiant...
            public void clicked(InputEvent event, float x, float y) {
                // Si la position courrante n'a pas encore été setté (autrement dit, au avant tout click sur les boutons, on affiche l'image par défaut (/))
                // ou si il n'y a pas de prochain élement on retourne au début
                if (currentPosition == null || !currentPosition.hasNext()) {
                    // On sette l'itérateur comment étant le dernières élements.
                    currentPosition = equipments.listIterator();
                    Gdx.app.debug(this.getClass().getName(),"null iterator ou n'a pas de prochain élement");
                }
                Pair<Equipment, Texture> old = currentEquipement;
                currentEquipement = (Pair<Equipment, Texture>) currentPosition.next();
                if(currentEquipement == old){
                    currentEquipement = (Pair<Equipment, Texture>) currentPosition.next();
                }
                // TODO voir s'il y a mieux que caster.
                changeEquipment(currentEquipement);


            }
        });

        rightArrowBtn.setOrigin(20, 20);
        rightArrowBtn.setTransform(true);
        rightArrowBtn.rotateBy(180);
        imgEquipement = new Image(defaultEquipementTex);

        table.add(leftArrowBtn).width(40).height(40);
        table.add(imgEquipement).width(100).height(100);
        table.add(rightArrowBtn).width(40).height(40);
    }

}
