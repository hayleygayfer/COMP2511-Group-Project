package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

import unsw.loopmania.shopItems.SwordShopItem;
import unsw.loopmania.shopItems.StakeShopItem;
import unsw.loopmania.shopItems.StaffShopItem;
import unsw.loopmania.shopItems.ShieldShopItem;
import unsw.loopmania.shopItems.ArmourShopItem;
import unsw.loopmania.shopItems.HelmetShopItem;

import java.util.ArrayList;

public class HerosCastleMenu {
    private List<ShopItem> items;

    public HerosCastleMenu() {
        items = new ArrayList<ShopItem>();
        items.add(new SwordShopItem());
        items.add(new StaffShopItem());
        items.add(new StakeShopItem());
        items.add(new HelmetShopItem());
        items.add(new ArmourShopItem());
        items.add(new ShieldShopItem());
    }   

    /**
     * return a list of all items in the shop
     * @return
     */
    public List<ShopItem> getItems() {
        return items;
    }

    /**
     * Add a new item to the shop
     * @param newItem
     */
    public void addItem(ShopItem newItem) {
        items.add(newItem);
    }

    public boolean hasItem(ShopItem item) {
        return items.contains(item);
    }

    public Item purchaseItem(Character character, ShopItem item, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        if (character.getGold().get() >= item.price().get()) {
            Item newItem = item.createItem(x, y);
            character.addItemToInventory(newItem);
            character.deductGold(item.price().get());
            return newItem;
        }
        return null;
    }
}
