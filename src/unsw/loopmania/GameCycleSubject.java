package unsw.loopmania;

import java.util.List;

// Not currently being used
public interface GameCycleSubject {
    public void attach(Entity entity);
    public void detach(Entity entity);
    public void updateObservers();
}
