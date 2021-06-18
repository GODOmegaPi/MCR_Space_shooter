package com.mcr.spaceshooter.UI.EquipementSelector;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mcr.spaceshooter.Entity.Equipments.DefensiveEquipment;
import com.mcr.spaceshooter.Entity.Equipments.Equipment;
import com.mcr.spaceshooter.UI.Screen.GarageScreen;

import java.util.List;
import java.util.function.Consumer;

public class DefensiveEquipmentSelector extends EquipementSelector {
    private Label nameLbl;
    private Label priceLbl;
    private Label hpLbl;

    public DefensiveEquipmentSelector(List<Equipment> equipments, Skin skin, Consumer<Equipment> buildSetter, Runnable buildCleaner, GarageScreen garageScreen) {
        super(equipments, skin, buildSetter, buildCleaner, garageScreen);
        nameLbl = new Label(equipments.get(currentElementIdx).getName(), skin);
        priceLbl = new Label(String.valueOf(equipments.get(currentElementIdx).getPrice()), skin);
        hpLbl = new Label(String.valueOf(((DefensiveEquipment) equipments.get(currentElementIdx)).getHp()), skin);
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
        nameLbl.setText(equipments.get(currentElementIdx).getName());
        priceLbl.setText(String.valueOf(equipments.get(currentElementIdx).getPrice()));
        hpLbl.setText(String.valueOf(((DefensiveEquipment) equipments.get(currentElementIdx)).getHp()));
    }

}
