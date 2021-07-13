package unsw.loopmania;

import java.util.List;

public class HerosCastleMenu {
    private List<Item> items;

    public HerosCastleMenu() {
    }   

    /**
     * return a list of all items in the shop
     * @return
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Add a new item to the shop
     * @param newItem
     */
    public void addItem(Item newItem) {
        items.add(newItem);
    }

    public void purchaseItem(Character character, Item item) {
        if (items.contains(item) /* && character has enough gold */ ) {
            character.addItemToInventory(item);
            items.remove(item);
        }
    }
}
