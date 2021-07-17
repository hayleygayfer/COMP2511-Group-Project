package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.generateItems.ArmourGenerateItem;
import unsw.loopmania.generateItems.HelmetGenerateItem;
import unsw.loopmania.generateItems.ShieldGenerateItem;
import unsw.loopmania.generateItems.StaffGenerateItem;
import unsw.loopmania.generateItems.StakeGenerateItem;
import unsw.loopmania.generateItems.SwordGenerateItem;
import unsw.loopmania.generateItems.HealthPotionGenerateItem;

import java.util.ArrayList;

public class HerosCastleMenu {
    private List<GenerateItem> items;

    public HerosCastleMenu() {
        items = new ArrayList<GenerateItem>();
        items.add(new SwordGenerateItem());
        items.add(new StaffGenerateItem());
        items.add(new StakeGenerateItem());
        items.add(new HelmetGenerateItem());
        items.add(new ArmourGenerateItem());
        items.add(new ShieldGenerateItem());
        items.add(new HealthPotionGenerateItem());
    }   

    /**
     * return a list of all items in the shop
     * @return
     */
    public List<GenerateItem> getItems() {
        return items;
    }

    /**
     * Add a new item to the shop
     * @param newItem
     */
    public void addItem(GenerateItem newItem) {
        items.add(newItem);
    }

    public boolean hasItem(GenerateItem item) {
        return items.contains(item);
    }

    public Item purchaseItem(Character character, GenerateItem item, SimpleIntegerProperty x, SimpleIntegerProperty y) {
        if (character.getGold().get() >= item.price().get()) {
            Item newItem = item.createItem(x, y);
            character.addItemToInventory(newItem);
            character.deductGold(item.price().get());
            return newItem;
        }
        return null;
    }

    public void sellItem(Character character, Item item) {
        character.removeItemFromInventory(item);
        character.addGold(item.getSellPrice().get());
        item.destroy();
    }
}
