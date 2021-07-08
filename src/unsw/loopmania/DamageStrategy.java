package unsw.loopmania;

import org.junit.jupiter.api.DisplayNameGenerator.Simple;

import javafx.beans.property.SimpleIntegerProperty;

public interface DamageStrategy {
    public SimpleIntegerProperty getModifiedDamage(SimpleIntegerProperty baseDamage);
}
