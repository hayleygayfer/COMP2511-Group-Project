package unsw.loopmania;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
public abstract class Building extends StaticEntity {
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
} 
