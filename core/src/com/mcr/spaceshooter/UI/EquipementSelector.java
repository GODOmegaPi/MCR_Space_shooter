package com.mcr.spaceshooter.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mcr.spaceshooter.Builder.ShipBuilderException;
import com.mcr.spaceshooter.Entity.Equipments.Equipment;
import com.sun.tools.javac.util.Pair;

import java.util.List;
import java.util.function.Consumer;


public class EquipementSelector extends Group {
    // TODO voir ce qui peut etre static  eg: btnTex restera toujours le même (comme defaultEuiqpementTex)
    private Table table;
    private Image imgEquipement;
    private ImageButton leftArrowBtn;
    private ImageButton rightArrowBtn;
    private Texture btnTex; //
    private int currentElementIdx;
    private List<Pair<Equipment, Texture>> equipments;
    private Skin skin;
    private TextButton equipBtn;
    private Boolean isEquiped = false;
    Consumer<Equipment> buildSetter;
    Runnable buildCleaner;
    GarageScreen garageScreen;


    public EquipementSelector(List<Pair<Equipment, Texture>> equipments, Skin skin, Consumer<Equipment> buildSetter, Runnable buildCleaner, GarageScreen garageScreen) {
        // On le set l'index de l'élément courant à une valeur impossible car au commencement
        // Aucun élément n'est sélectionné
        this.currentElementIdx = 0;
        this.equipments = equipments;
        //this.table = table;
        this.skin = skin;
        this.table = new Table();
        table.setFillParent(true);
        addActor(table);
        this.init();
        this.buildSetter = buildSetter;
        this.buildCleaner = buildCleaner;
        this.garageScreen = garageScreen;
    }

    private void changeEquipment(Pair<Equipment, Texture> equipement) {
        imgEquipement.setDrawable(new SpriteDrawable(new Sprite(equipement.snd)));
    }


    private void init() {
        equipBtn = new TextButton("Equiper", skin);
        equipBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try{
                    // TODO UTILISER LE BUILDER
                    if (isEquiped) {
                        Gdx.app.debug(this.getClass().getName(), "PAR ici"  );
                        buildCleaner.run();
                        isEquiped = false;
                        equipBtn.setText("Equiper");
                        leftArrowBtn.setDisabled(false);
                        rightArrowBtn.setDisabled(false);
                        Gdx.app.debug(this.getClass().getName(), "PAR ici en bas"  );
                    } else {
                        Gdx.app.debug(this.getClass().getName(), "PAR LA"  );
                        buildSetter.accept(equipments.get(currentElementIdx).fst);
                        isEquiped = true;
                        equipBtn.setText("Desequiper");
                        leftArrowBtn.setDisabled(true);
                        rightArrowBtn.setDisabled(true);
                        Gdx.app.debug(this.getClass().getName(), "PAR La en bas"  );
                    }
                }catch(ShipBuilderException sbe){
                    garageScreen.toastLong("Erreur de construction: " + sbe.getMessage());
                    Gdx.app.debug(this.getClass().getName(), "Erreur de construction: " + sbe.getMessage()  );
                }

            }
        });


        btnTex = new Texture(Gdx.files.internal("leftArrow.png"));
        leftArrowBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnTex)));
        leftArrowBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (leftArrowBtn.isDisabled())
                    return; // TODO vraiment nécessaire ?: Il faut vraiement faire ça soit même ? -_-'
                if (--currentElementIdx < 0) {
                    // On sette l'itérateur comment étant le dernières élements.
                    currentElementIdx = equipments.size() - 1;
                }
                Gdx.app.debug(this.getClass().getName(), String.format("is disabled : %b", leftArrowBtn.isDisabled()));
                changeEquipment(equipments.get(currentElementIdx));
            }
        });
        rightArrowBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnTex)));
        rightArrowBtn.addListener(new ClickListener() {
            @Override

            public void clicked(InputEvent event, float x, float y) {
                if (rightArrowBtn.isDisabled())
                    return; // TODO vraiment nécessaire ? : Il faut vraiement faire ça soit même ? -_-'
                if (++currentElementIdx >= equipments.size()) {
                    currentElementIdx = 0;
                }
                Gdx.app.debug(this.getClass().getName(), String.format("index : %d", currentElementIdx));
                changeEquipment(equipments.get(currentElementIdx));

            }
        });

        rightArrowBtn.setOrigin(20, 20);
        rightArrowBtn.setTransform(true);
        rightArrowBtn.rotateBy(180);
        imgEquipement = new Image();
        changeEquipment(equipments.get(currentElementIdx));
        table.add(leftArrowBtn).width(40).height(40).uniform();
        table.add(imgEquipement).width(100).height(100).colspan(1);
        table.add(rightArrowBtn).width(40).height(40).colspan(1);
        table.row();
        table.add(equipBtn).width(250).height(40).colspan(3).center();
        table.row();
    }

}
