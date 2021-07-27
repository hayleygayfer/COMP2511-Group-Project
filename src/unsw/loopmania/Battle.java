package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import org.javatuples.Triplet;

public class Battle {
    private Character character;
    private List<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
    private List<Building> buildings;
    private int initialHealth;
    private int initialDamage;

    public Battle(Character character, List<BasicEnemy> enemies, List<Building> buildings) {
        this.character = character;
        this.enemies = enemies;
        this.buildings = buildings;
        this.initialHealth = character.getCurrentHealth();
        this.initialDamage = character.getDamage();
    }

    /**
     * Creates the frames to run the battle
     * Sets up both the character and enemy
     * Updates both enemy and character stats during battle until one is dead
     * @return List<Triplet<Integer, Integer, BasicEnemy>>
     */
    public List<Triplet<Integer, Integer, BasicEnemy>> runBattle() {
        List<Triplet<Integer, Integer, BasicEnemy>> frames = new ArrayList<Triplet<Integer, Integer, BasicEnemy>>();
        // Initial set up for character
        for (EquippableItem item : character.getEquippedItems()) {
            item.affect(character);
        }
        
        frames.add(Triplet.with(character.getCurrentHealth(), enemies.get(0).getHealth(), enemies.get(0)));
        for (BasicEnemy enemy : enemies) {
            // Initial set up for each enemy
            for (EquippableItem item : character.getEquippedItems()) {
                item.affect(enemy);
            }
            while (character.isAlive() && enemy.isAlive()) {
                enemy.attack(character);
                character.attack(enemy);
                int enemyHealth = enemy.getHealth();
                if (enemyHealth < 0) enemyHealth = 0;
                frames.add(Triplet.with(character.getCurrentHealth(), enemyHealth, enemy));
            }
        }
        return frames;
    }

    /**
     * Resets the characters health 
     */
    public void resetCharacter() {
        if (initialHealth < character.getCurrentHealth()) {
        character.setCurrentHealth(initialHealth);
        character.setDamage(initialDamage);
        }
    }

    /**
     * Check if the character is still alive at the end of the battle
     * @return boolean
     */
    public boolean wonBattle() {
        return character.isAlive();
    }
}
