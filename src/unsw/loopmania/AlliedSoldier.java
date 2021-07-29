package unsw.loopmania;


import javafx.beans.property.SimpleIntegerProperty;

public class AlliedSoldier {
    // TODO: write allied solder
    private SimpleIntegerProperty health;
    private SimpleIntegerProperty baseDamage;
    private Character character;

    public AlliedSoldier(Character character) {
        health = new SimpleIntegerProperty(10);
        baseDamage = new SimpleIntegerProperty(1);
        this.character = character;
    }

    /**
     * Health getter
     * @return int
     */
    public int getHealth() {
        return health.get();
    }

    /**
     * Reduces the health 
     * subtracts the damage from the existing health
     * @param damage
     */
    public void loseHealth(double damage) {
        this.health.set((int) (getHealth() - damage));
        System.out.println(getHealth());
        if (!isAlive()) {
            character.loseSoldier(this);
        }
    }

    /**
     * Checks if the allied soldier is alive based on its health
     * @return boolean
     */
    public boolean isAlive() {
        return getHealth() > 0;
    }

    /**
     * Damage getter
     * @return int 
     */
    public int getDamage() {
        return baseDamage.get();
    }

    /**
     * Attacks enemy
     * @param enemy
     */
    public void attack(BasicEnemy enemy) {
        enemy.deductHealth(getDamage());
    }
}
