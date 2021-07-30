package unsw.loopmania.generateItems;

import unsw.loopmania.Item;
import unsw.loopmania.GenerateItem;
import unsw.loopmania.RareItem;
import unsw.loopmania.items.DoggieCoin;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.jupiter.api.DisplayNameGenerator.Simple;

import java.util.Random;

public class DoggieCoinGenerateItem extends GenerateItem {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleIntegerProperty price;
    private int upperValue;
    private Random rand = new Random(System.currentTimeMillis());

    public DoggieCoinGenerateItem() {
        this.name = new SimpleStringProperty("DoggieCoin");
        this.description = new SimpleStringProperty("A revolutionary asset type, which randomly fluctuates in sellable price to an extraordinary extent. Can sell at shop.");
        this.upperValue = 10000;
    }

    public Item createItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Item newDoggieCoin = new DoggieCoin(x, y);
        return newDoggieCoin;
    }

    public void setUpperValue(int x) {
        upperValue = x;
    }

    private void setPrice(int x) {
        this.price.set(x);
    }

    public SimpleStringProperty description() {
        return description;
    }

    public SimpleStringProperty name() {
        return name;
    }

    public SimpleIntegerProperty price() {
        setPrice(rand.nextInt(upperValue));
        return price;
    }

    public Image getImage() {
        return new Image((new File("src/images/doggiecoin.png")).toURI().toString());
    }
}
