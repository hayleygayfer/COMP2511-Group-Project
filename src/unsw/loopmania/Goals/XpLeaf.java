package unsw.loopmania.Goals;

import unsw.loopmania.LoopManiaWorld;

public class XpLeaf implements Goal {
    private int goalXp;

    public XpLeaf(int goalXp) {
        this.goalXp = goalXp;
    }

    public void setGoalXp(int goalXp) {
        this.goalXp = goalXp;
    }
    
    @Override
    public Boolean metGoal(LoopManiaWorld world) { 
        if (world.getCharacter().getXpProperty().get() > goalXp) {
            return true;
        } 
        return false;
    }
    
}
