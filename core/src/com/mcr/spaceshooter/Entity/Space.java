package com.mcr.spaceshooter.Entity;

public class Space {

    private Spaceship spaceship;

    public Space() {
           spaceship = new Spaceship(50, 50, 5);
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }


}
