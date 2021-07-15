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
    // for (EquippableItem item : character.getEquippedItems()) {
    //   item.modify(character);
    //   for (BasicEnemy enemy : enemies) {
    //     item.modify(enemy;)
    //   }
    // }
    while (character.isAlive() && !enemies.isEmpty()) {
      // Character attack
      // All enemies attac;
    }
  }
}
