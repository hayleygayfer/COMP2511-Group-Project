package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;

public class Battle {
  private Character character;
  private List<BasicEnemy> enemies = new ArrayList<BasicEnemy>();
  private Timeline timeline;
  private int currentEnemyIndex;
  private BooleanProperty isFinished = new SimpleBooleanProperty(false);

  public Battle(Character character, List<BasicEnemy> enemies) {
    this.character = character;
    this.enemies = enemies;
    currentEnemyIndex = 0;
  }

  public void endBattle() {
    timeline.stop();
  }

  public void runBattle()  {
    // Initial set up for character
    for (EquippableItem item : character.getEquippedItems()) {
      item.affect(character);
      item.affect(enemies.get(currentEnemyIndex));
    }
    timeline = new Timeline(new KeyFrame(Duration.seconds(1.1), event -> {
      // if Character Turn
      character.attack(enemies.get(currentEnemyIndex));
      // if Enemy Turn
      enemies.get(currentEnemyIndex).attack(character);
      // Evaluate Result (both times) (set up new enemy)
      if (!enemies.get(currentEnemyIndex).isAlive() && (currentEnemyIndex + 1) < enemies.size()) {
        currentEnemyIndex = currentEnemyIndex + 1;
        for (EquippableItem item : character.getEquippedItems()) {
          item.affect(enemies.get(currentEnemyIndex));
        }
      } else if (!character.isAlive() || !enemies.get(currentEnemyIndex).isAlive()) {
        finishBattle();
        timeline.stop();
      }
    }));
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }

  private void finishBattle() {
    character.resetHealth();
    isFinished.set(true);
  }

  public BooleanProperty getIsFinished() {
    return isFinished;
  }

}
