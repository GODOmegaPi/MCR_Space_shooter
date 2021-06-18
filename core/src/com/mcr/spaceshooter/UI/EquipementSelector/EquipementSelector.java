package com.mcr.spaceshooter.UI.EquipementSelector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.mcr.spaceshooter.UI.Screen.GarageScreen;
import com.mcr.spaceshooter.Utils.Asset;

import java.util.List;
import java.util.function.Consumer;


abstract public class EquipementSelector extends Group {
    protected Table table;
    protected List<Equipment> equipments;
    protected int currentElementIdx;
    protected Skin skin;
    private Image imgEquipement;
    private ImageButton leftArrowBtn;
    private ImageButton rightArrowBtn;
    private TextButton equipBtn;
    private Boolean isEquiped = false;
    private static final String EQUIPER_TEXT = "Equiper";
    private static final String DESEQUIPER_TEXT = "Desequiper";

    Consumer<Equipment> buildSetter;
    Runnable buildCleaner;
    GarageScreen garageScreen;


    public EquipementSelector(List<Equipment> equipments, Skin skin, Consumer<Equipment> buildSetter, Runnable buildCleaner, GarageScreen garageScreen) {
        // On le set l'index de l'élément courant à une valeur impossible car au commencement
        // Aucun élément n'est sélectionné
        this.currentElementIdx = 0;
        this.equipments = equipments;
        this.skin = skin;
        this.table = new Table();
        table.setFillParent(true);
        addActor(table);

        this.buildSetter = buildSetter;
        this.buildCleaner = buildCleaner;
        this.garageScreen = garageScreen;
    }

    private void changeEquipment(Equipment equipement) {
        imgEquipement.setDrawable(new SpriteDrawable(new Sprite(equipement.getTexture())));
    }
    private void equipe(){
        buildSetter.accept(equipments.get(currentElementIdx));
        isEquiped = true;
        equipBtn.setText(DESEQUIPER_TEXT);
        leftArrowBtn.setDisabled(true);
        rightArrowBtn.setDisabled(true);
        garageScreen.updateCost();
    }
    private void desequipe(){
        buildCleaner.run();
        isEquiped = false;
        equipBtn.setText(EQUIPER_TEXT);
        leftArrowBtn.setDisabled(false);
        rightArrowBtn.setDisabled(false);
        garageScreen.updateCost();
    }
    protected void init() {
        equipBtn = new TextButton(EQUIPER_TEXT, skin);
        equipBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try{
                    if (isEquiped) {
                        desequipe();
                    } else {
                       equipe();
                    }
                }catch(ShipBuilderException sbe){
                    garageScreen.toastLong("Erreur de construction: " + sbe.getMessage());
                }
            }
        });


        Texture btnTex = Asset.getInstance().getLeftArrowTexture();
        Texture pressedBtnTex = Asset.getInstance().getLeftArrowPressedTexture();
        leftArrowBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnTex)),new TextureRegionDrawable(new TextureRegion(pressedBtnTex)));
        leftArrowBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (leftArrowBtn.isDisabled())
                    return;
                if (--currentElementIdx < 0) {
                    // On sette l'itérateur comment étant le dernières élements.
                    currentElementIdx = equipments.size() - 1;
                }
                changeEquipment(equipments.get(currentElementIdx));
                updateLabels();
            }
        });
        rightArrowBtn =  new ImageButton(new TextureRegionDrawable(new TextureRegion(btnTex)),new TextureRegionDrawable(new TextureRegion(pressedBtnTex)));
        rightArrowBtn.addListener(new ClickListener() {
            @Override

            public void clicked(InputEvent event, float x, float y) {
                if (rightArrowBtn.isDisabled())
                    return;
                if (++currentElementIdx >= equipments.size()) {
                    currentElementIdx = 0;
                }
                changeEquipment(equipments.get(currentElementIdx));
                updateLabels();
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
        addSpecs();
        table.row();
        table.add(equipBtn).width(250).height(40).colspan(3).center();
        table.row();
    }
    abstract void addSpecs();
    abstract void updateLabels();

}
