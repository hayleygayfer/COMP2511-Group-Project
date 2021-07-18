package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import org.javatuples.Triplet;

public class Battle {
  private Character character;
  private List<BasicEnemy> enemies = new ArrayList<BasicEnemy>();

  public Battle(Character character, List<BasicEnemy> enemies) {
    this.character = character;
    this.enemies = enemies;
  }

  public List<Triplet<Integer, Integer, BasicEnemy>> runBattle() {
    List<Triplet<Integer, Integer, BasicEnemy>> frames = new ArrayList<Triplet<Integer, Integer, BasicEnemy>>();
    // Initial set up for character
    for (EquippableItem item : character.getEquippedItems()) {
      item.affect(character);
    }
    frames.add(Triplet.with(character.getModifiedHealth(), enemies.get(0).getHealth(), enemies.get(0)));
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
        frames.add(Triplet.with(character.getModifiedHealth(), enemyHealth, enemy));
      }
    }
    return frames;
  }

  public void resetCharacter() {
    character.resetHealth();
  }

  public boolean wonBattle() {
    return character.isAlive();
  }
}
