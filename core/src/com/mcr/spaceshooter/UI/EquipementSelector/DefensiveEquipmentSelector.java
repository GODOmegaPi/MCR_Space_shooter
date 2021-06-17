package com.mcr.spaceshooter.UI.EquipementSelector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mcr.spaceshooter.Entity.Equipments.DefensiveEquipment;
import com.mcr.spaceshooter.Entity.Equipments.Equipment;
import com.mcr.spaceshooter.UI.Screen.GarageScreen;
import com.sun.tools.javac.util.Pair;

import java.util.List;
import java.util.function.Consumer;

public class DefensiveEquipmentSelector extends EquipementSelector {
    private Label nameLbl = new Label("test", skin);
    private Label priceLbl;
    private Label hpLbl;

    public DefensiveEquipmentSelector(List<Pair<Equipment, Texture>> equipments, Skin skin, Consumer<Equipment> buildSetter, Runnable buildCleaner, GarageScreen garageScreen) {
        super(equipments, skin, buildSetter, buildCleaner, garageScreen);
        nameLbl = new Label(equipments.get(currentElementIdx).fst.getName(), skin);
        priceLbl = new Label(String.valueOf(equipments.get(currentElementIdx).fst.getPrice()), skin);
        hpLbl = new Label(String.valueOf(((DefensiveEquipment) equipments.get(currentElementIdx).fst).getHp()), skin);
        init();
    }


    @Override
    protected void addSpecs() {
        table.add(nameLbl).width(240).colspan(3);
        table.row();

        table.add(new Label("HP", skin)).width(120).colspan(2);
        table.add(hpLbl);
        table.row();

        table.add(new Label("Prix", skin)).width(120).colspan(2);
        table.add(priceLbl);
        table.row();

    }

    @Override
    void updateLabels() {
        Gdx.app.debug(this.getClass().getName(), String.format("name %s| price %s| hp %s", equipments.get(currentElementIdx).fst.getName(), String.valueOf(equipments.get(currentElementIdx).fst.getPrice()), String.valueOf(((DefensiveEquipment) equipments.get(currentElementIdx).fst).getHp())));
        nameLbl.setText(equipments.get(currentElementIdx).fst.getName());
        priceLbl.setText(String.valueOf(equipments.get(currentElementIdx).fst.getPrice()));
        hpLbl.setText(String.valueOf(((DefensiveEquipment) equipments.get(currentElementIdx).fst).getHp()));
    }

}
