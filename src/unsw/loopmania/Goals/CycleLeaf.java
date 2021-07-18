package unsw.loopmania.Goals;

import unsw.loopmania.LoopManiaWorld;

public class CycleLeaf implements Goal{
    private int goalCycle; 

    public CycleLeaf(int goalCycle) {

        this.goalCycle = goalCycle;
    }

    

    @Override
    public boolean metGoal(LoopManiaWorld world) {
        if (world.getGameCycle() > goalCycle) {
            return true;
        } 
        return false;
        
    }
    
}
