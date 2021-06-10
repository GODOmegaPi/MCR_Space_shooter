package equipment;

abstract public class Equipement {
    private int hp;
    private int credit;

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public int getCredit() {
        return credit;
    }

    public Equipement(int hp, int credit){
        this.hp = hp;
        this.credit = credit;
    }
}
