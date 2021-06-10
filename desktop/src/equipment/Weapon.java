package equipment;

public class Weapon extends Equipement {
    private int damage;

    public Weapon(int hp, int credit, int damage) {
        super(hp, credit);
        this.damage = damage;
    }
}
