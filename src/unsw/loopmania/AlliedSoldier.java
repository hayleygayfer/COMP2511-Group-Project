package unsw.loopmania;


import javafx.beans.property.SimpleIntegerProperty;

public class AlliedSoldier {
    // TODO: write allied solder
    private SimpleIntegerProperty health;
    private SimpleIntegerProperty baseDamage;
    private Character character;
    private boolean tranced;


    public AlliedSoldier(Character character, boolean isTranced) {
        health = new SimpleIntegerProperty(10);
        baseDamage = new SimpleIntegerProperty(1);
        this.character = character;
        this.tranced = isTranced;
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

    /**
     * Returns whether or not the allied soldier is tranced (and therefore dies at the end of a battle).
     * @return
     */
    public boolean isTranced() {
        return tranced;
    }
}
