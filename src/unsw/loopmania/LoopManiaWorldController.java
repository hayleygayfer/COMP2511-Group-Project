package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

import org.codefx.libfx.listener.handle.ListenerHandle;
import org.codefx.libfx.listener.handle.ListenerHandles;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;
import unsw.loopmania.Goals.Goal;
import unsw.loopmania.itemTypes.ShieldType;
import unsw.loopmania.itemTypes.ArmourType;
import unsw.loopmania.itemTypes.WeaponType;
import unsw.loopmania.itemTypes.HelmetType;
import unsw.loopmania.itemTypes.AccessoryType;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;

import java.util.EnumMap;

import java.io.File;
import java.io.IOException;


/**
 * the draggable types.
 * If you add more draggable types, add an enum value here.
 * This is so we can see what type is being dragged.
 */
enum DRAGGABLE_TYPE{
    CARD,
    ITEM
}

/**
 * A JavaFX controller for the world.
 * 
 * All event handlers and the timeline in JavaFX run on the JavaFX application thread:
 *     https://examples.javacodegeeks.com/desktop-java/javafx/javafx-concurrency-example/
 *     Note in https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Application.html under heading "Threading", it specifies animation timelines are run in the application thread.
 * This means that the starter code does not need locks (mutexes) for resources shared between the timeline KeyFrame, and all of the  event handlers (including between different event handlers).
 * This will make the game easier for you to implement. However, if you add time-consuming processes to this, the game may lag or become choppy.
 * 
 * If you need to implement time-consuming processes, we recommend:
 *     using Task https://openjfx.io/javadoc/11/javafx.graphics/javafx/concurrent/Task.html by itself or within a Service https://openjfx.io/javadoc/11/javafx.graphics/javafx/concurrent/Service.html
 * 
 *     Tasks ensure that any changes to public properties, change notifications for errors or cancellation, event handlers, and states occur on the JavaFX Application thread,
 *         so is a better alternative to using a basic Java Thread: https://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
 *     The Service class is used for executing/reusing tasks. You can run tasks without Service, however, if you don't need to reuse it.
 *
 * If you implement time-consuming processes in a Task or thread, you may need to implement locks on resources shared with the application thread (i.e. Timeline KeyFrame and drag Event handlers).
 * You can check whether code is running on the JavaFX application thread by running the helper method printThreadingNotes in this class.
 * 
 * NOTE: http://tutorials.jenkov.com/javafx/concurrency.html and https://www.developer.com/design/multithreading-in-javafx/#:~:text=JavaFX%20has%20a%20unique%20set,in%20the%20JavaFX%20Application%20Thread.
 * 
 * If you need to delay some code but it is not long-running, consider using Platform.runLater https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Platform.html#runLater(java.lang.Runnable)
 *     This is run on the JavaFX application thread when it has enough time.
 */
public class LoopManiaWorldController {

    /**
     * container for all battle interactions
     */
    @FXML
    private VBox battle;

    @FXML
    private ImageView heroBattle;

    @FXML
    private ImageView enemyBattle;

    @FXML
    private Button finishBattleButton;

    @FXML
    private ProgressBar enemyHealth;

    @FXML
    private ProgressBar characterHealth;

    @FXML
    private Text enemiesLeft;

    @FXML
    private Text alliedSoldiersCount;

    @FXML
    private HBox gameOver;


    @FXML
    Button pauseButton;

    /**
     * container for all hero castle menu components
     */
    @FXML
    private ScrollPane heroCastle;

    /**
     * container for all game map components
     */
    @FXML 
    private VBox gameMap;

    /**
     * squares gridpane includes path images, enemies, character, empty grass, buildings
     */
    @FXML
    private GridPane squares;

    /**
     * cards gridpane includes cards and the ground underneath the cards
     */
    @FXML
    private GridPane cards;

    /**
     * shopItems gridPane includes shop items
     */
    @FXML
    private GridPane shopItems;

    /**
     * anchorPaneRoot is the "background". It is useful since anchorPaneRoot stretches over the entire game world,
     * so we can detect dragging of cards/items over this and accordingly update DragIcon coordinates
     */
    @FXML
    private AnchorPane anchorPaneRoot;

    /**
     * equippedItems gridpane is for equipped items (e.g. swords, shield, axe)
     */
    @FXML
    private GridPane equippedItems;

    @FXML
    private GridPane equippedArmour;

    @FXML
    private GridPane equippedShield; 

    @FXML
    private GridPane equippedWeapon;

    @FXML
    private GridPane equippedHelmet;

    @FXML
    private GridPane equippedAccessory;

    @FXML
    private GridPane unequippedInventory;

    @FXML
    private Text cycleDisplay;

    @FXML
    private Text healthDisplay;

    @FXML
    private Text baseHealthDisplay;

    @FXML
    private Text goldDisplay;

    @FXML
    private Text xpDisplay;

    // all image views including tiles, character, enemies, cards... even though cards in separate gridpane...
    private List<ImageView> entityImages;

    /**
     * when we drag a card/item, the picture for whatever we're dragging is set here and we actually drag this node
     */
    private DragIcon draggedEntity;

    private boolean isPaused;
    private LoopManiaWorld world;

    /**
     * runs the periodic game logic - second-by-second moving of character through maze, as well as enemies, and running of battles
     */
    private Timeline timeline;

    /**
     * the image currently being dragged, if there is one, otherwise null.
     * Holding the ImageView being dragged allows us to spawn it again in the drop location if appropriate.
     */
    // TODO = it would be a good idea for you to instead replace this with the building/item which should be dropped
    private ImageView currentlyDraggedImage;
    
    /**
     * null if nothing being dragged, or the type of item being dragged
     */
    private DRAGGABLE_TYPE currentlyDraggedType;

    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dropped over its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneSetOnDragDropped;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged over the background
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> anchorPaneRootSetOnDragOver;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dropped in the background
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> anchorPaneRootSetOnDragDropped;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged into the boundaries of its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneNodeSetOnDragEntered;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged outside of the boundaries of its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneNodeSetOnDragExited;

    /**
     * object handling switching to the main menu
     */
    private MenuSwitcher mainMenuSwitcher;

    /**
     * @param world world object loaded from file
     * @param initialEntities the initial JavaFX nodes (ImageViews) which should be loaded into the GUI
     */
    public LoopManiaWorldController(LoopManiaWorld world, List<ImageView> initialEntities) {
        this.world = world;
        entityImages = new ArrayList<>(initialEntities);
        currentlyDraggedImage = null;
        currentlyDraggedType = null;

        // initialize them all...
        gridPaneSetOnDragDropped = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        anchorPaneRootSetOnDragOver = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        anchorPaneRootSetOnDragDropped = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        gridPaneNodeSetOnDragEntered = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        gridPaneNodeSetOnDragExited = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
    }

    /**
     * Getter for the current world
     * @return LoopManiaWorld the current world
     */
    public LoopManiaWorld getWorld() {
        return world;
    }

    /**
     * Sets up the board and world 
     */
    @FXML
    public void initialize() {
        
        Image pathTilesImage = new Image((new File("src/images/32x32GrassAndDirtPath.png")).toURI().toString());
        Image inventorySlotImage = new Image((new File("src/images/empty_slot.png")).toURI().toString());
        Rectangle2D imagePart = new Rectangle2D(0, 0, 32, 32);

        // heroBattle = new ImageView(new Image((new File("src/images/human_new.png")).toURI().toString()));
        Image test = new Image((new File("src/images/basic_sword.png")).toURI().toString());
        enemyBattle.setImage(test);
        enemyBattle.setFitHeight(25);
        enemyBattle.setFitWidth(25);

        // Add the ground first so it is below all other entities (inculding all the twists and turns)
        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {
                ImageView groundView = new ImageView(pathTilesImage);
                groundView.setViewport(imagePart);
                squares.add(groundView, x, y);
            }
        }

        // load entities loaded from the file in the loader into the squares gridpane
        for (ImageView entity : entityImages){
            squares.getChildren().add(entity);
        }
        
        // add the ground underneath the cards
        for (int x=0; x<world.getWidth(); x++){
            ImageView groundView = new ImageView(pathTilesImage);
            groundView.setViewport(imagePart);
            cards.add(groundView, x, 0);
        }

        // add the empty slot images for the unequipped inventory
        for (int x=0; x<LoopManiaWorld.unequippedInventoryWidth; x++){
            for (int y=0; y<LoopManiaWorld.unequippedInventoryHeight; y++){
                ImageView emptySlotView = new ImageView(inventorySlotImage);
                unequippedInventory.add(emptySlotView, x, y);
            }
        }

        // add the starting heros castle shop items

        List<GenerateItem> itemMenu = world.getHerosCastleMenu().getItems();
        int row = 0;
        int col = 0;
        for (int i = 0; i < itemMenu.size(); i++) {
            VBox newTag = loadShopTag(itemMenu.get(i));
            if (col < 2) {
                shopItems.add(newTag, row, col);
                col++;
            } else {
                row += 1;
                col = 0;
                shopItems.add(newTag, row, col);
                col++;
            }
        }

        battle.prefWidthProperty().bind(anchorPaneRoot.widthProperty());
        battle.prefHeightProperty().bind(anchorPaneRoot.heightProperty());
        heroCastle.setPrefWidth(320);

        // bind the game states layout to their visibility
        heroCastle.managedProperty().bind(heroCastle.visibleProperty());
        gameMap.managedProperty().bind(gameMap.visibleProperty());
        battle.managedProperty().bind(battle.visibleProperty());
        gameOver.managedProperty().bind(gameOver.visibleProperty());

        // heros castle menu not visible in the beginning
        heroCastle.setVisible(false);
        battle.setVisible(false);
        gameOver.setVisible(false);

        // create the draggable icon
        draggedEntity = new DragIcon();
        draggedEntity.setVisible(false);
        draggedEntity.setOpacity(0.7);
        anchorPaneRoot.getChildren().add(draggedEntity);

        // make bindings for stats
        cycleDisplay.textProperty().bindBidirectional(world.getGameCycleProperty(), new NumberStringConverter());
        healthDisplay.textProperty().bindBidirectional(world.getCharacter().getCurrentHealthProperty(), new NumberStringConverter());
        baseHealthDisplay.textProperty().bindBidirectional(world.getCharacter().getBaseHealthProperty(), new NumberStringConverter());
        goldDisplay.textProperty().bindBidirectional(world.getCharacter().getGoldProperty(), new NumberStringConverter());
        xpDisplay.textProperty().bindBidirectional(world.getCharacter().getXpProperty(), new NumberStringConverter());

        onLoad(world.loadCard());
        onLoad(world.loadCard());
        onLoad(world.loadCard());
        onLoad(world.loadCard());
        onLoad(world.loadCard());

    }

    /**
     * create and run the timer
     */
    public void startTimer(){
        System.out.println("starting timer");
        gameOver.setVisible(false);
        heroCastle.setVisible(false);
        gameMap.setVisible(true);
        isPaused = false;
        // trigger adding code to process main game logic to queue. JavaFX will target framerate of 0.3 seconds
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), event -> {
            world.runTickMoves();

            if (evaluate(world.getGameGoal())) {
                terminate();
            }
            // check if character is at heros castle
            if (this.world.characterAtHerosCastle() && this.world.getGameCycle() != 0) {
                heroCastle.setVisible(true);
                gameMap.setVisible(false);
                pauseButton.setText("Start");
                pause();
            }
            
            // check if character is in a battle
            if (this.world.getCurrentBattle() != null) {
                battle.setVisible(true);
                gameMap.setVisible(false);
                finishBattleButton.setVisible(false);
                pause();
                SequentialTransition battleSequence = new SequentialTransition();
                List<Quintet<Double, Double, BasicEnemy, Integer, Integer>> frames = world.getCurrentBattle().runBattle();
                enemyBattle.setImage(frames.get(0).getValue2().render());
                enemiesLeft.setText(frames.get(0).getValue3() + " Enemies Left");
                alliedSoldiersCount.setText("You have " + frames.get(0).getValue4() + " allied soldiers.");
                characterHealth.setProgress(frames.get(0).getValue0());
                enemyHealth.setProgress(frames.get(0).getValue1());
                for (int i = 1; i < (frames.size()); i++) {
                    battleSequence.getChildren().add(animateBattleFrame(frames.get(i)));
                }
                battleSequence.setCycleCount(1);
                battleSequence.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        characterHealth.setProgress(frames.get(frames.size() - 1).getValue0());
                        enemyHealth.setProgress(frames.get(frames.size() - 1).getValue1());
                        world.getCurrentBattle().resetCharacter();
                        if (!world.getCurrentBattle().wonBattle()) {
                            terminate();

                        } else {
                            enemiesLeft.setText("You Won!");
                            enemiesLeft.setText("0 Enemies Left");
                            alliedSoldiersCount.setText("You have " + world.getCharacter().getNumOfAlliedSoldiers() + " allied soldiers.");            
                            finishBattleButton.setVisible(true);
                        }
                    }
                });
                battleSequence.play();
            }

            List<BasicEnemy> defeatedEnemies = world.runBattles();
            for (BasicEnemy e: defeatedEnemies){
                reactToEnemyDefeat(e);
            }
            List<BasicEnemy> otherDefeatedEnemies = world.otherDefeatedEnemies();
            for (BasicEnemy e: otherDefeatedEnemies) {
                reactToEnemyDefeat(e);
            }
            world.destroyBuildings();
            List<BasicEnemy> newEnemies = world.possiblySpawnEnemies();
            for (BasicEnemy newEnemy: newEnemies){
                onLoad(newEnemy);
            }
            // spawn boss if applicable
            BasicEnemy spawningBoss = world.spawnBossEnemy();
            if (spawningBoss != null) {
                onLoad(spawningBoss);
            }
            List<Gold> newGold = world.possiblySpawnGold();
            for (Gold gold: newGold) {
                onLoad(gold);
            }
            printThreadingNotes("HANDLED TIMER");
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * pause the execution of the game loop
     * the human player can still drag and drop items during the game pause
     */
    public void pause(){
        isPaused = true;
        System.out.println("pausing");
        timeline.stop();
    }

    public void endGame() {
        timeline.stop();
        world.resetGame();
    }

    /**
     * End the game due to success criteria / loss
     */
    public void terminate() {
        gameOver.setVisible(true);
        gameMap.setVisible(false);
        battle.setVisible(false);
        endGame();
        world.setCurrentBattle(null);
    }

    /**
     * Restart game after shop has been open
     */
    public void resumeGameFromShop() {
        heroCastle.setVisible(false);
        gameMap.setVisible(true);
    }

    /**
     * Checks if the current stats meet the goal
     * @param expression the game current goal
     * @return boolean
     */
    public boolean evaluate(Goal expression) {
        // Return the expression evaluated
        return expression.metGoal(this.getWorld());
    }

    /**
     * Go through and get an item from heros castle menu
     * @param item An item to get from the shop
     */
    public void purchaseItemFromShop(GenerateItem item) {
        Pair<Integer, Integer> coords = world.getFirstAvailableSlotForItem();
        Item purchasedItem = world.getHerosCastleMenu().purchaseItem(world.getCharacter(), item, new SimpleIntegerProperty(coords.getValue0()), new SimpleIntegerProperty(coords.getValue1()));
        if (!purchasedItem.equals(null)) {
            onLoad(purchasedItem);
        }
    }

    public Popup loadPopupInfo(Item item) {
        Popup itemDetailsPopup = new Popup();

        VBox itemInfo = new VBox();
        itemInfo.setStyle("-fx-padding: 8;" + 
        "-fx-border-style: solid inside;" + 
        "-fx-border-width: 1;" +
        "-fx-border-insets: 3;" + 
        "-fx-border-color: grey;" +
        "-fx-background-color: white;");

        Button hide = new Button("X");
        hide.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                itemDetailsPopup.hide();
                try {
                    pauseGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }); 

        HBox closeButtonRow = new HBox();
        closeButtonRow.getChildren().add(hide);

        itemInfo.getChildren().add(closeButtonRow);

        HBox imgRow = new HBox();
        imgRow.setPadding(new Insets(3));
        imgRow.setPrefHeight(110);

        VBox nameDescription = new VBox();

        HBox priceRow = new HBox();
        priceRow.setPadding(new Insets(3));
        // add name of item
        Label name = new Label(item.getItemDetails().name().get());
        name.setStyle("-fx-font-weight: bold");
        nameDescription.getChildren().add(name);
        // add item description
        Label description = new Label(item.getItemDetails().description().get());
        description.setWrapText(true);
        description.setPrefWidth(100);
        nameDescription.getChildren().add(description);
        // add item image
        ImageView itemView = new ImageView(item.getItemDetails().getImage());

        // create image row
        imgRow.getChildren().add(nameDescription);
        imgRow.getChildren().add(itemView);

        itemInfo.getChildren().add(imgRow);

        // add item price
        Label price = new Label("$" + item.getItemDetails().price().get());
        price.setPrefWidth(95);
        priceRow.getChildren().add(price);

        itemInfo.getChildren().add(priceRow);

        itemDetailsPopup.getContent().add(itemInfo);

        return itemDetailsPopup;
    }

    /**
     * Creates the shop and all the items in it 
     * @param item The item from shop
     * @return VBox
     */
    public VBox loadShopTag(GenerateItem item) {
        VBox GenerateItem = new VBox();
        GenerateItem.setStyle("-fx-padding: 8;" + 
        "-fx-border-style: solid inside;" + 
        "-fx-border-width: 1;" +
        "-fx-border-insets: 3;" + 
        "-fx-border-color: grey;" +
        "-fx-background-color: white;");

        HBox imgRow = new HBox();
        imgRow.setPadding(new Insets(3));
        imgRow.setPrefHeight(110);

        VBox nameDescription = new VBox();

        HBox priceRow = new HBox();
        priceRow.setPadding(new Insets(3));
        // add name of item
        Label name = new Label(item.name().get());
        name.setStyle("-fx-font-weight: bold");
        nameDescription.getChildren().add(name);
        // add item description
        Label description = new Label(item.description().get());
        description.setWrapText(true);
        description.setPrefWidth(100);
        nameDescription.getChildren().add(description);
        // add item image
        ImageView itemView = new ImageView(item.getImage());

        // create image row
        imgRow.getChildren().add(nameDescription);
        imgRow.getChildren().add(itemView);

        GenerateItem.getChildren().add(imgRow);

        // add item price
        Label price = new Label("$" + item.price().get());
        price.setPrefWidth(95);
        priceRow.getChildren().add(price);
        // add buy button
        Button buyItem = new Button("Buy");
        buyItem.setOnAction(e -> { 
            purchaseItemFromShop(item); 
        });
        buyItem.disableProperty().bind(world.getCharacter().getGoldProperty().lessThan(item.price()));
        priceRow.getChildren().add(buyItem);

        GenerateItem.getChildren().add(priceRow);

        return GenerateItem;
    }

    /**
     * pair the entity an view so that the view copies the movements of the entity.
     * add view to list of entity images
     * @param entity backend entity to be paired with view
     * @param view frontend imageview to be paired with backend entity
     */
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entityImages.add(view);
    }

    /**
     * run GUI events after an enemy is defeated, such as spawning items/experience/gold
     * @param enemy defeated enemy for which we should react to the death of
     */
    private void reactToEnemyDefeat(BasicEnemy enemy){
        // react to character defeating an enemy
        // in starter code, spawning extra card/weapon...
        List<Item> itemsToLoad = world.defeatedEnemyItemDrops(enemy);
        for (int i = 0; i < itemsToLoad.size(); i++) {
            onLoad(itemsToLoad.get(i));
        }
        List<Card> cardsToLoad = world.defeatedEnemyCardDrops(enemy);
        for (int i = 0; i < cardsToLoad.size(); i++) {
            onLoad(cardsToLoad.get(i));
        }
        world.getGoldAndXpDrops(enemy);
    }

    /**
     * load a card into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the cards GridPane.
     * @param Card to load to GUI 
     */
    private void onLoad(Card card) {
        ImageView view = new ImageView(card.render());

        // FROM https://stackoverflow.com/questions/41088095/javafx-drag-and-drop-to-gridpane
        // note target setOnDragOver and setOnDragEntered defined in initialize method
        addDragEventHandlers(view, DRAGGABLE_TYPE.CARD, cards, squares);

        addEntity(card, view);
        cards.getChildren().add(view);
    }

    /**
     * load an item into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the unequippedInventory GridPane.
     * @param item an item to load to GUI
     */
    private void onLoad(Item item) {
        ImageView view = new ImageView(item.render());
        if (item instanceof ArmourType) {
            addDragEventHandlers(view, DRAGGABLE_TYPE.ITEM, unequippedInventory, equippedArmour);
        } else if (item instanceof WeaponType) {
            addDragEventHandlers(view, DRAGGABLE_TYPE.ITEM, unequippedInventory, equippedWeapon);
        } else if (item instanceof ShieldType) {
            addDragEventHandlers(view, DRAGGABLE_TYPE.ITEM, unequippedInventory, equippedShield);
        } else if (item instanceof HelmetType) {
            addDragEventHandlers(view, DRAGGABLE_TYPE.ITEM, unequippedInventory, equippedHelmet);
        } else if (item instanceof AccessoryType) {
            addDragEventHandlers(view, DRAGGABLE_TYPE.ITEM, unequippedInventory, equippedAccessory);
        }   

        if (item instanceof UsableItem) {
            UsableItem usableItem = (UsableItem) item;
            view.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    usableItem.affect(world.getCharacter());
                    world.removeItemWhenUsed(item);
                    view.setVisible(false);
                    view.setManaged(false);
                    event.consume();
                }
            });
        } else  {
            Popup itemDetails = loadPopupInfo(item);
            view.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (heroCastle.isVisible()) {
                        world.sellItem(item);
                        view.setVisible(false);
                        view.setManaged(false);
                        event.consume();
                    } else {
                        itemDetails.show(anchorPaneRoot.getScene().getWindow());
                        try {
                            pauseGame();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        addEntity(item, view);
        unequippedInventory.getChildren().add(view);
    }

    /**
     * load an enemy into the GUI
     * @param enemy and enemy to load to GUI
     */
    private void onLoad(BasicEnemy enemy) {
        // Determine which image to load in.
        ImageView view = new ImageView(enemy.render());
        addEntity(enemy, view);
        squares.getChildren().add(view);
    }

    /**
     * load a building into the GUI
     * @param building the building to load to GUI
     */
    private void onLoad(Building building) {
        ImageView view = new ImageView(building.render());
        addEntity(building, view);
        squares.getChildren().add(view);
    }

    /**
     * load a gold card into the GUI
     * @param gold The gold to load to GUI
     */
    private void onLoad(Gold gold) {
        ImageView view = new ImageView(gold.render());
        addEntity(gold, view);
        squares.getChildren().add(view);
    }

    /**
     * add drag event handlers for dropping into gridpanes, dragging over the background, dropping over the background.
     * These are not attached to invidual items such as swords/cards.
     * @param draggableType the type being dragged - card or item
     * @param sourceGridPane the gridpane being dragged from
     * @param targetGridPane the gridpane the human player should be dragging to (but we of course cannot guarantee they will do so)
     */
    private void buildNonEntityDragHandlers(DRAGGABLE_TYPE draggableType, GridPane sourceGridPane, GridPane targetGridPane){
        // TODO = be more selective about where something can be dropped
        // for example, in the specification, villages can only be dropped on path, whilst vampire castles cannot go on the path

        gridPaneSetOnDragDropped.put(draggableType, new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                // TODO = for being more selective about where something can be dropped, consider applying additional if-statement logic
                /*
                 *you might want to design the application so dropping at an invalid location drops at the most recent valid location hovered over,
                 * or simply allow the card/item to return to its slot (the latter is easier, as you won't have to store the last valid drop location!)
                 */
                boolean validDrop = false;
                if (currentlyDraggedType == draggableType){
                    // problem = event is drop completed is false when should be true...
                    // https://bugs.openjdk.java.net/browse/JDK-8117019
                    // putting drop completed at start not making complete on VLAB...

                    //Data dropped
                    //If there is an image on the dragboard, read it and use it
                    Dragboard db = event.getDragboard();
                    Node node = event.getPickResult().getIntersectedNode();
                    if(node != targetGridPane && db.hasImage()){

                        Integer cIndex = GridPane.getColumnIndex(node);
                        Integer rIndex = GridPane.getRowIndex(node);
                        int x = cIndex == null ? 0 : cIndex;
                        int y = rIndex == null ? 0 : rIndex;
                        //Places at 0,0 - will need to take coordinates once that is implemented
                        ImageView image = new ImageView(db.getImage());

                        int nodeX = GridPane.getColumnIndex(currentlyDraggedImage);
                        int nodeY = GridPane.getRowIndex(currentlyDraggedImage);
                        switch (draggableType){
                            case CARD:
                                removeDraggableDragEventHandlers(draggableType, targetGridPane);
                                if (canBuildByCoordinates(nodeX, nodeY, x, y)) {
                                    Building newBuilding = convertCardToBuildingByCoordinates(nodeX, nodeY, x, y);
                                    onLoad(newBuilding);
                                    validDrop = true;
                                }
                               break;
                            case ITEM:
                                removeDraggableDragEventHandlers(draggableType, targetGridPane);
                                // TODO = spawn an item in the new location. The above code for spawning a building will help, it is very similar
                                equipItemByCoordinates(nodeX, nodeY);
                                removeItemByCoordinates(nodeX, nodeY);
                                targetGridPane.add(image, x, y, 1, 1);
                                validDrop = true;
                                break;
                            default:
                                break;
                        }
                        if (!validDrop) {
                            currentlyDraggedImage.setVisible(true);
                        }

                        draggedEntity.setVisible(false);
                        draggedEntity.setMouseTransparent(false);
                        // remove drag event handlers before setting currently dragged image to null
                        removeDraggableDragEventHandlers(draggableType, targetGridPane);
                        currentlyDraggedImage = null;
                        currentlyDraggedType = null;
                        printThreadingNotes("DRAG DROPPED ON GRIDPANE HANDLED");
                       
                    }
                }
                event.setDropCompleted(true);
                // consuming prevents the propagation of the event to the anchorPaneRoot (as a sub-node of anchorPaneRoot, GridPane is prioritized)
                // https://openjfx.io/javadoc/11/javafx.base/javafx/event/Event.html#consume()
                // to understand this in full detail, ask your tutor or read https://docs.oracle.com/javase/8/javafx/events-tutorial/processing.htm
                event.consume();
            }
        });

        // this doesn't fire when we drag over GridPane because in the event handler for dragging over GridPanes, we consume the event
        anchorPaneRootSetOnDragOver.put(draggableType, new EventHandler<DragEvent>(){
            // https://github.com/joelgraff/java_fx_node_link_demo/blob/master/Draggable_Node/DraggableNodeDemo/src/application/RootLayout.java#L110
            @Override
            public void handle(DragEvent event) {
                if (currentlyDraggedType == draggableType){
                    if(event.getGestureSource() != anchorPaneRoot && event.getDragboard().hasImage()){
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                }
                if (currentlyDraggedType != null){
                    draggedEntity.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                }
                event.consume();
            }
        });

        // this doesn't fire when we drop over GridPane because in the event handler for dropping over GridPanes, we consume the event
        anchorPaneRootSetOnDragDropped.put(draggableType, new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (currentlyDraggedType == draggableType){
                    //Data dropped
                    //If there is an image on the dragboard, read it and use it
                    Dragboard db = event.getDragboard();
                    Node node = event.getPickResult().getIntersectedNode();
                    if(node != anchorPaneRoot && db.hasImage()){
                        //Places at 0,0 - will need to take coordinates once that is implemented
                        currentlyDraggedImage.setVisible(true);
                        draggedEntity.setVisible(false);
                        draggedEntity.setMouseTransparent(false);
                        // remove drag event handlers before setting currently dragged image to null
                        removeDraggableDragEventHandlers(draggableType, targetGridPane);
                        
                        currentlyDraggedImage = null;
                        currentlyDraggedType = null;
                    }
                }
                //let the source know whether the image was successfully transferred and used
                event.setDropCompleted(true);
                event.consume();
            }
        });
    }

    /**
     * remove the card from the world, and spawn and return a building instead where the card was dropped
     * @param cardNodeX the x coordinate of the card which was dragged, from 0 to width-1
     * @param cardNodeY the y coordinate of the card which was dragged (in starter code this is 0 as only 1 row of cards)
     * @param buildingNodeX the x coordinate of the drop location for the card, where the building will spawn, from 0 to width-1
     * @param buildingNodeY the y coordinate of the drop location for the card, where the building will spawn, from 0 to height-1
     * @return building entity returned from the world
     */
    private Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        return world.convertCardToBuildingByCoordinates(cardNodeX, cardNodeY, buildingNodeX, buildingNodeY);
    }

    /**
     * Checks if based on the co0rdinates can build 
     * @param cardNodeX the x coordinate of the card which was dragged, from 0 to width-1
     * @param cardNodeY the y coordinate of the card which was dragged (in starter code this is 0 as only 1 row of cards)
     * @param buildingNodeX the x coordinate of the drop location for the card, where the building will spawn, from 0 to width-1
     * @param buildingNodeY the y coordinate of the drop location for the card, where the building will spawn, from 0 to height-1
     * @return boolean if can build based on the coordinates
     */
    private boolean canBuildByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        return world.canBuildByCoordinates(cardNodeX, cardNodeY, buildingNodeX, buildingNodeY);
    }

    /**
     * Equips items based on the coordinates
     * @param itemNodeX the x coordiate for the item
     * @param itemNodeY the y coordinate for the item
     */
    private void equipItemByCoordinates(int itemNodeX, int itemNodeY) {
        world.equipInventoryItemByCoordinates(itemNodeX, itemNodeY);
    }

    /**
     * remove an item from the unequipped inventory by its x and y coordinates in the unequipped inventory gridpane
     * @param nodeX x coordinate from 0 to unequippedInventoryWidth-1
     * @param nodeY y coordinate from 0 to unequippedInventoryHeight-1
     */
    private void removeItemByCoordinates(int nodeX, int nodeY) {
        world.removeUnequippedInventoryItemByCoordinates(nodeX, nodeY);
    }

    /**
     * add drag event handlers to an ImageView
     * @param view the view to attach drag event handlers to
     * @param draggableType the type of item being dragged - card or item
     * @param sourceGridPane the relevant gridpane from which the entity would be dragged
     * @param targetGridPane the relevant gridpane to which the entity would be dragged to
     */
    private void addDragEventHandlers(ImageView view, DRAGGABLE_TYPE draggableType, GridPane sourceGridPane, GridPane targetGridPane){
        view.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                currentlyDraggedImage = view; // set image currently being dragged, so squares setOnDragEntered can detect it...
                currentlyDraggedType = draggableType;
                //Drag was detected, start drap-and-drop gesture
                //Allow any transfer node
                Dragboard db = view.startDragAndDrop(TransferMode.MOVE);
    
                //Put ImageView on dragboard
                ClipboardContent cbContent = new ClipboardContent();
                cbContent.putImage(view.getImage());
                db.setContent(cbContent);
                view.setVisible(false);

                buildNonEntityDragHandlers(draggableType, sourceGridPane, targetGridPane);

                draggedEntity.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                switch (draggableType){
                    case CARD:
                        draggedEntity.setImage(view.getImage());
                        break;
                    case ITEM:
                        draggedEntity.setImage(view.getImage());
                        break;
                    default:
                        break;
                }
                
                draggedEntity.setVisible(true);
                draggedEntity.setMouseTransparent(true);
                draggedEntity.toFront();

                // IMPORTANT!!!
                // to be able to remove event handlers, need to use addEventHandler
                // https://stackoverflow.com/a/67283792
                targetGridPane.addEventHandler(DragEvent.DRAG_DROPPED, gridPaneSetOnDragDropped.get(draggableType));
                anchorPaneRoot.addEventHandler(DragEvent.DRAG_OVER, anchorPaneRootSetOnDragOver.get(draggableType));
                anchorPaneRoot.addEventHandler(DragEvent.DRAG_DROPPED, anchorPaneRootSetOnDragDropped.get(draggableType));

                for (Node n: targetGridPane.getChildren()){
                    // events for entering and exiting are attached to squares children because that impacts opacity change
                    // these do not affect visibility of original image...
                    // https://stackoverflow.com/questions/41088095/javafx-drag-and-drop-to-gridpane
                    
                    gridPaneNodeSetOnDragEntered.put(draggableType, new EventHandler<DragEvent>() {
                        Integer cIndex = GridPane.getColumnIndex(n);
                        Integer rIndex = GridPane.getRowIndex(n);
                        int x = cIndex == null ? 0 : cIndex;
                        int y = rIndex == null ? 0 : rIndex;
                        //Places at 0,0 - will need to take coordinates once that is implemented

                        int nodeX = GridPane.getColumnIndex(currentlyDraggedImage);
                        int nodeY = GridPane.getRowIndex(currentlyDraggedImage);

                        public void handle(DragEvent event) {
                            if (currentlyDraggedType == draggableType) {
                            //The drag-and-drop gesture entered the target
                            //show the user that it is an actual gesture target
                                if(event.getGestureSource() != n && event.getDragboard().hasImage() && canBuildByCoordinates(nodeX, nodeY, x, y)){
                                    n.setOpacity(0.7);
                                }
                            }
                            event.consume();
                        }
                    });
                    gridPaneNodeSetOnDragExited.put(draggableType, new EventHandler<DragEvent>() {
                        public void handle(DragEvent event) {
                            if (currentlyDraggedType == draggableType) {
                                n.setOpacity(1);
                            }
                
                            event.consume();
                        }
                    });
                    n.addEventHandler(DragEvent.DRAG_ENTERED, gridPaneNodeSetOnDragEntered.get(draggableType));
                    n.addEventHandler(DragEvent.DRAG_EXITED, gridPaneNodeSetOnDragExited.get(draggableType));
                }
                event.consume();
            }
            
        });
    }

    /**
     * remove drag event handlers so that we don't process redundant events
     * this is particularly important for slower machines such as over VLAB.
     * @param draggableType either cards, or items in unequipped inventory
     * @param targetGridPane the gridpane to remove the drag event handlers from
     */
    private void removeDraggableDragEventHandlers(DRAGGABLE_TYPE draggableType, GridPane targetGridPane){
        // remove event handlers from nodes in children squares, from anchorPaneRoot, and squares
        targetGridPane.removeEventHandler(DragEvent.DRAG_DROPPED, gridPaneSetOnDragDropped.get(draggableType));

        anchorPaneRoot.removeEventHandler(DragEvent.DRAG_OVER, anchorPaneRootSetOnDragOver.get(draggableType));
        anchorPaneRoot.removeEventHandler(DragEvent.DRAG_DROPPED, anchorPaneRootSetOnDragDropped.get(draggableType));

        for (Node n: targetGridPane.getChildren()){
            n.removeEventHandler(DragEvent.DRAG_ENTERED, gridPaneNodeSetOnDragEntered.get(draggableType));
            n.removeEventHandler(DragEvent.DRAG_EXITED, gridPaneNodeSetOnDragExited.get(draggableType));
        }
    }

    /**
     * handle the pressing of keyboard keys.
     * Specifically, we should pause when pressing SPACE
     * @param event some keyboard key press
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        // TODO = handle additional key presses, e.g. for consuming a health potion
        switch (event.getCode()) {
        case SPACE:
            if (isPaused){
                startTimer();
                pauseButton.setText("Pause");
                if (heroCastle.isVisible()) {
                    resumeGameFromShop();
                }
            }
            else{
                pause();
                pauseButton.setText("Start");
            }
            break;
        default:
            break;
        }
    }

    /**
     * If pressed then takes user to main menu
     * @param mainMenuSwitcher the button to switch to main menu
     */
    public void setMainMenuSwitcher(MenuSwitcher mainMenuSwitcher){
        // TODO = possibly set other menu switchers
        this.mainMenuSwitcher = mainMenuSwitcher;
    }

    /**
     * this method is triggered when click button to go to main menu in FXML
     * @throws IOException
     */
    @FXML
    private void switchToMainMenu() throws IOException {
        // TODO = possibly set other menu switchers
        endGame();
        mainMenuSwitcher.switchMenu();
    }


    /**
     * Sets up visibaily of heros castle
     * Stops and starts the timer 
     * @throws IOException 
     */
    @FXML
    private void pauseGame() throws IOException {
        if (isPaused) {
            pauseButton.setText("Pause");
            if (heroCastle.isVisible()) {
                resumeGameFromShop();
            }
            startTimer();
        } else {
            pauseButton.setText("Start");
            pause();
        }
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the world.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * 
     * note that this is put in the controller rather than the loader because we need to track positions of spawned entities such as enemy
     * or items which might need to be removed should be tracked here
     * 
     * NOTE teardown functions setup here also remove nodes from their GridPane. So it is vital this is handled in this Controller class
     * @param entity 
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        // TODO = tweak this slightly to remove items from the equipped inventory?
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());

        ChangeListener<Number> xListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        };
        ChangeListener<Number> yListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        };

        // if need to remove items from the equipped inventory, add code to remove from equipped inventory gridpane in the .onDetach part
        ListenerHandle handleX = ListenerHandles.createFor(entity.x(), node)
                                               .onAttach((o, l) -> o.addListener(xListener))
                                               .onDetach((o, l) -> {
                                                    o.removeListener(xListener);
                                                    entityImages.remove(node);
                                                    squares.getChildren().remove(node);
                                                    cards.getChildren().remove(node);
                                                    unequippedInventory.getChildren().remove(node);
                                                })
                                               .buildAttached();
        ListenerHandle handleY = ListenerHandles.createFor(entity.y(), node)
                                               .onAttach((o, l) -> o.addListener(yListener))
                                               .onDetach((o, l) -> {
                                                   o.removeListener(yListener);
                                                   entityImages.remove(node);
                                                   squares.getChildren().remove(node);
                                                   cards.getChildren().remove(node);
                                                   unequippedInventory.getChildren().remove(node);
                                                })
                                               .buildAttached();
        handleX.attach();
        handleY.attach();

        // this means that if we change boolean property in an entity tracked from here, position will stop being tracked
        // this wont work on character/path entities loaded from loader classes
        entity.shouldExist().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> obervable, Boolean oldValue, Boolean newValue) {
                handleX.detach();
                handleY.detach();
            }
        });
    }

    /**
     * we added this method to help with debugging so you could check your code is running on the application thread.
     * By running everything on the application thread, you will not need to worry about implementing locks, which is outside the scope of the course.
     * Always writing code running on the application thread will make the project easier, as long as you are not running time-consuming tasks.
     * We recommend only running code on the application thread, by using Timelines when you want to run multiple processes at once.
     * EventHandlers will run on the application thread.
     * @param currentMethodLabel The method label to help with debugging
     */
    private void printThreadingNotes(String currentMethodLabel){
        System.out.println("\n###########################################");
        System.out.println("current method = "+currentMethodLabel);
        System.out.println("In application thread? = "+Platform.isFxApplicationThread());
        System.out.println("Current system time = "+java.time.LocalDateTime.now().toString().replace('T', ' '));
    }

    public ParallelTransition animateBattleFrame(Quintet<Double, Double, BasicEnemy, Integer, Integer> frame) {
        ParallelTransition ptr = new ParallelTransition();
        //Duration = 2.5 seconds
        Duration duration = Duration.millis(250);
        //Create new translate transition
        TranslateTransition transitionHero = new TranslateTransition(duration, heroBattle);
        transitionHero.setByX(50);
        transitionHero.setAutoReverse(true);
        transitionHero.setCycleCount(2);

        SequentialTransition heroSequence = new SequentialTransition(transitionHero, new PauseTransition(Duration.millis(350)));
        heroSequence.setCycleCount(1);

        TranslateTransition transitionEnemy = new TranslateTransition(duration, enemyBattle);
        transitionEnemy.setByX(-50);
        transitionEnemy.setAutoReverse(true);
        transitionEnemy.setCycleCount(2);

        SequentialTransition enemySequence = new SequentialTransition(new PauseTransition(Duration.millis(350)), transitionEnemy);
        enemySequence.setCycleCount(1);

        ptr.getChildren().addAll(heroSequence, enemySequence);
        ptr.setCycleCount(1);
        ptr.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enemiesLeft.setText(frame.getValue3() + " Enemies Left");
                alliedSoldiersCount.setText("You have " + frame.getValue4() + " allied soldiers.");        
                characterHealth.setProgress(Math.max(frame.getValue0(), 0));
                enemyHealth.setProgress(Math.max(frame.getValue1(), 0));
                enemyBattle.setImage(frame.getValue2().render());
            }
        });
        return ptr;
    }

    /**
     * Setting up the screen at the end of the battle
     * @param event
     */
    @FXML
    private void onFinishBattleButton(ActionEvent event) {
        battle.setVisible(false);
        gameMap.setVisible(true);
        world.setCurrentBattle(null);
        startTimer();
    }
}
