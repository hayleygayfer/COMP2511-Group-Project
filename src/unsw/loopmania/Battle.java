package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Quartet;
import org.javatuples.Triplet;

public class Battle {
  private Character character;
  private List<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
  private int initialCharacterHealth;
  private double baseBattleCharacterHealth;
  private int initialCharacterDamage;
  private double baseEnemyBattleHealth;

  public Battle(Character character, List<BasicEnemy> enemies) {
    this.character = character;
    this.enemies = enemies;
    this.initialCharacterHealth = character.getCurrentHealth();
    this.initialCharacterDamage = character.getDamage();
  }

  /**
   * Creates the frames to run the battle
   * Sets up both the character and enemy
   * Updates both enemy and character stats during battle until one is dead
   * @return List<Triplet<Integer, Integer, BasicEnemy>>
   */
  public List<Quartet<Double, Double, BasicEnemy, Integer>> runBattle() {
    List<Quartet<Double, Double, BasicEnemy, Integer>> frames = new ArrayList<Quartet<Double, Double, BasicEnemy, Integer>>();
    // Initial set up for character
    for (EquippableItem item : character.getEquippedItems()) {
      item.affect(character);
    }
    this.baseBattleCharacterHealth = character.getCurrentHealth() + (character.getBaseHealth() - initialCharacterHealth);
    // Add initial frame
    int index = 0;
    frames.add(Quartet.with(character.getCurrentHealth() / baseBattleCharacterHealth, 1.0, enemies.get(0), enemies.size() - index));
    for (BasicEnemy enemy : enemies) {
      // Initial set up for each enemy
      for (EquippableItem item : character.getEquippedItems()) {
        item.affect(enemy);
      }
      this.baseEnemyBattleHealth = enemy.getHealth();
      while (character.isAlive() && enemy.isAlive()) {
        enemy.attack(character);
        character.attack(enemy);
        int enemyHealth = enemy.getHealth();
        if (enemyHealth < 0) enemyHealth = 0;
        frames.add(Quartet.with(((double) character.getCurrentHealth() / baseBattleCharacterHealth), enemyHealth / baseEnemyBattleHealth, enemy, enemies.size() - index));
      }
      index++;
    }
    return frames;
  }

  /**
   * Resets the characters health 
   */
  public void resetCharacter() {
    if (initialCharacterHealth < character.getCurrentHealth()) {
      character.setCurrentHealth(initialCharacterHealth);
      character.setDamage(initialCharacterDamage);
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
