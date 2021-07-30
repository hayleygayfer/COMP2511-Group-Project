package test.generateItems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.Item;
import unsw.loopmania.ItemType;
import unsw.loopmania.generateItems.DoggieCoinGenerateItem;
import unsw.loopmania.items.Armour;

public class DoggieCoinGenerateItemTest {
    @Test
    public void testConstructor() {
        GenerateItem generateItem = new DoggieCoinGenerateItem();

        assertTrue(generateItem instanceof DoggieCoinGenerateItem);
        assertTrue(generateItem instanceof GenerateItem);
    }

    @Test
    public void testName() {
        DoggieCoinGenerateItem generateItem = new DoggieCoinGenerateItem(); 

        assertEquals("DoggieCoin", generateItem.name().get());
    }

    @Test
    public void testDescription() {
        DoggieCoinGenerateItem generateItem = new DoggieCoinGenerateItem();

        assertEquals("A revolutionary asset type, which randomly fluctuates in sellable price to an extraordinary extent. Can sell at shop.", generateItem.description().get());
    }

    @Test
    public void testPrice() {
        DoggieCoinGenerateItem generateItem = new DoggieCoinGenerateItem(); 

        assertEquals(50, generateItem.price().get());
    }

    @Test
    public void testType() {
        DoggieCoinGenerateItem generateItem = new DoggieCoinGenerateItem();

        assertEquals(ItemType.NOT_EQUIPPABLE, generateItem.getType());
    }

}
