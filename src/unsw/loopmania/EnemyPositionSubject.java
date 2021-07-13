package unsw.loopmania;

public interface EnemyPositionSubject {
  public void attach(EnemyPositionSubject observer);
  public void detach(EnemyPositionSubject observer);
  public void updateObservers();
}
