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

    public boolean purchaseItem(Character character, Item item) {
        if (items.contains(item)) {
            if (character.getGold().get() >= item.getPrice()) {
                character.addItemToInventory(item);
                character.deductGold(item.getPrice());
                items.remove(item);
                return true;
            }
        }
        return false;
    }
}
