package unsw.loopmania;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

/**
 * controller for the main menu.
 * TODO = you could extend this, for example with a settings menu, or a menu to load particular maps.
 */
public class MainMenuController {
    /**
     * facilitates switching to main game
     */
    private MenuSwitcher gameSwitcher;

    @FXML 
    private RadioButton standardMode;
    @FXML 
    private RadioButton survivalMode;
    @FXML 
    private RadioButton berserkerMode;
    @FXML 
    private RadioButton confusingMode;

    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToGame() throws IOException {
        gameSwitcher.switchMenu();
    }

    public int getSelectedGameMode() {
        if (standardMode.isSelected()) return 0;
        if (survivalMode.isSelected()) return 1;
        if (berserkerMode.isSelected()) return 2;
        if (confusingMode.isSelected()) return 3;
        else return 0;
    }
}
