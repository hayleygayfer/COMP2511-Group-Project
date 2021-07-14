package unsw.loopmania;

import java.util.List;
import java.util.ArrayList;

public class HerosCastleMenu {
    private List<PurchaseItem> items;

    public HerosCastleMenu() {
        items = new ArrayList<PurchaseItem>();
    }   

    /**
     * return a list of all items in the shop
     * @return
     */
    public List<PurchaseItem> getItems() {
        return items;
    }

    /**
     * Add a new item to the shop
     * @param newItem
     */
    public void addItem(PurchaseItem newItem) {
        items.add(newItem);
    }

    public boolean hasItem(PurchaseItem item) {
        return items.contains(item);
    }

    public boolean purchaseItem(Character character, PurchaseItem item) {
        if (items.contains(item)) {
            if (character.getGold().get() >= item.getPrice().get()) {
                character.addItemToInventory((Item) item);
                character.deductGold(item.getPrice().get());
                items.remove(item);
                return true;
            }
        }
        return false;
    }
}
