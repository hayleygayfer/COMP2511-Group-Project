package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

public class Battle {
  private Character character;
  private List<BasicEnemy> enemies = new ArrayList<BasicEnemy>();

  public Battle(Character character, List<BasicEnemy> enemies) {
    this.character = character;
    this.enemies = enemies;
  }

  public void runBattle() {
    // Initial set up for character
    for (EquippableItem item : character.getEquippedItems()) {
      item.affect(character);
    }
    while (character.isAlive()) {
      for (BasicEnemy enemy : enemies) {
        // Initial set up for each enemy
        for (EquippableItem item : character.getEquippedItems()) {
          item.affect(enemy);
        }
        while (character.isAlive() && enemy.isAlive()) {
          character.attack(enemy);
          enemy.attack(character);
        }
      }
      character.resetHealth();
      break;
    }
  }
}
