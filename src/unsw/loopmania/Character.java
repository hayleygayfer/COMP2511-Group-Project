package unsw.loopmania;

import java.util.List;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity implements CharacterPositionSubject{
    private List<Item> inventory;
    private List<Item> equippedItems;


    // TODO = potentially implement relationships between this class and other classes
    public Character(PathPosition position) {
        super(position);
    }
    
}
