package unsw.loopmania;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity implements CharacterPositionSubject{
    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
    }
    
}
