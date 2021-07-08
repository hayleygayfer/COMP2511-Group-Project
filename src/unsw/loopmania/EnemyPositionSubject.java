package unsw.loopmania;

public interface EnemyPositionSubject {
    public void attachEnemyPosition(EnemyPositionObserver observer);

    public void detachEnemyPosition(EnemyPositionObserver observer);

    public void notifyEnemyPosition();
    
}
