package builder;

// TODO : interface utile  ?
public interface ShipBuilder {
    public void reset();
    public ShipBuilder fuselage(int hp, int credit);
    public ShipBuilder shield(int hp, int credit);
    public ShipBuilder weapon(int hp, int credit, int damage);
    public Ship build();
}
