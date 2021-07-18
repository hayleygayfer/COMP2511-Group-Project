package unsw.loopmania.Goals;

import unsw.loopmania.LoopManiaWorld;

public class GoldLeaf implements Goal {
    private int goalGold; 

    public GoldLeaf(int goal) {
        this.goalGold = goal;
    }

    public void setGoalGold(int goalGold) {
        this.goalGold = goalGold;
    }

    @Override
    public boolean metGoal(LoopManiaWorld world) {
        if (world.getCharacter().getGold() > goalGold) {
            return true;
        } 
        return false;
        
    }
    
}
