package unsw.loopmania.Goals;

import unsw.loopmania.LoopManiaWorld;

public class GoalAND implements Goal {
    private Goal goal1;
    private Goal goal2;

    public GoalAND(Goal goal1, Goal goal2) {
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    public void setGoal1(Goal goal1) {
        this.goal1 = goal1;
    }

    public void setGoal2(Goal goal2) {
        this.goal2 = goal2;
    }

    @Override
    public Boolean metGoal(LoopManiaWorld world) {
        return goal1.metGoal(world) && goal2.metGoal(world);
    }


    
    
}
