package unsw.loopmania;

import java.util.List;

import org.javatuples.Quintet;

public interface BattleBehaviourContext {
    public List<Frame>  battleBehaviour(List<BasicEnemy> enemies, List<CharacterEffect> buildings, BasicEnemy boss, Character character);
}
