package com.mcr.spaceshooter.UI.EquipementSelector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mcr.spaceshooter.Entity.Equipments.Equipment;
import com.mcr.spaceshooter.Entity.Equipments.OffensiveEquipment;
import com.mcr.spaceshooter.UI.Screen.GarageScreen;

import java.util.List;
import java.util.function.Consumer;

public class OffensiveEquipmentSelector extends EquipementSelector {
    private Label damageLbl;
    private Label nameLbl;
    private Label priceLbl;

    public OffensiveEquipmentSelector(List<Equipment> equipments, Skin skin, Consumer<Equipment> buildSetter, Runnable buildCleaner, GarageScreen garageScreen) {
        super(equipments, skin, buildSetter, buildCleaner, garageScreen);

        nameLbl = new Label(equipments.get(currentElementIdx).getName(), skin);
        priceLbl = new Label(String.valueOf(equipments.get(currentElementIdx).getPrice()), skin);
        damageLbl = new Label(String.valueOf(((OffensiveEquipment) equipments.get(currentElementIdx)).getDamage()), skin);
        init();
    }

    @Override
    void addSpecs() {
        table.add(nameLbl).width(240).colspan(3);
        table.row();

        table.add(new Label("Degats", skin)).width(120).colspan(2);
        table.add(damageLbl);
        table.row();

        table.add(new Label("Prix", skin)).width(120).colspan(2);
        table.add(priceLbl);
        table.row();
    }

    @Override
    void updateLabels() {
        Gdx.app.debug(this.getClass().getName(), "LABEL");
        nameLbl.setText(equipments.get(currentElementIdx).getName());
        priceLbl.setText(String.valueOf(equipments.get(currentElementIdx).getPrice()));
        damageLbl.setText(String.valueOf(((OffensiveEquipment) equipments.get(currentElementIdx)).getDamage()));
    }
}
