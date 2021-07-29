package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Goals.Goal;
import unsw.loopmania.cards.TowerCard;
import unsw.loopmania.cards.TrapCard;
import unsw.loopmania.cards.VampireCastleCard;
import unsw.loopmania.cards.VillageCard;
import unsw.loopmania.cards.ZombiePitCard;
import unsw.loopmania.enemies.Slug;
import unsw.loopmania.enemies.Doggie;
import unsw.loopmania.enemies.ElanMuske;

/**
 * A backend world.
 *
 * A world can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 */
public class LoopManiaWorld implements CharacterPositionObserver {
    public static final int unequippedInventoryWidth = 4;
    public static final int unequippedInventoryHeight = 4;

    /**
     * width of the world in GridPane cells
     */
    private int width;

    /**
     * height of the world in GridPane cells
     */
    private int height;

    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;

    private Character character;

    /**
     * cycles - the current game cycle
     */
    private SimpleIntegerProperty gameCycle;

    // current tick
    private int tick;

    private List<BasicEnemy> enemies;

    private List<Card> cardEntities;

    private List<SpawnEnemyStrategy> spawnEnemyStrategies;

    private List<Building> buildingEntities;

    private List<BossEnemyType> defeatedBosses;


    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse them
     */
    private List<Pair<Integer, Integer>> orderedPath;

    private HerosCastleMenu shopMenu;

    // Current Battle object
    Battle currentBattle;

    /**
     * This gets read in from JSON 
     * Gets set in the loader 
     */
    private Goal gameGoal;

    /**
     * create the world (constructor)
     * 
     * @param width width of world in number of cells
     * @param height height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing position of path cells in world
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath) {
        this.width = width;
        this.height = height;
        nonSpecifiedEntities = new ArrayList<>();
        character = null;
        enemies = new ArrayList<>();
        defeatedBosses = new ArrayList<>();
        cardEntities = new ArrayList<>();
        this.orderedPath = orderedPath;
        buildingEntities = new ArrayList<>();
        spawnEnemyStrategies = new ArrayList<>();
        shopMenu = new HerosCastleMenu();
        gameCycle = new SimpleIntegerProperty(0);
        tick = 0;
    }

    /**
     * Excellent hero's castle method
     * @return The shop menu
     */
    public HerosCastleMenu getHerosCastleMenu() {
        return shopMenu;
    }

    /**
     * Width of board getter
     * @return int 
     */
    public int getWidth() {
        return width;
    }

    /**
     * Height of board getter
     * @return int 
     */
    public int getHeight() {
        return height;
    }

    /**
     * Goal of the game getter
     * @return Goal of the game
     */
    public Goal getGameGoal() {
        return gameGoal;
    }

    /**
     * Goal of the game setter
     * @param gameGoal
     */
    public void setGameGoal(Goal gameGoal) {
        this.gameGoal = gameGoal;
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity out of the file
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
        character.attach(this);
    }

    /**
     * Allows the controller to access stats from the character
     * @return character in the world
     * @pre the character has been set in the world
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * add a generic entity (without it's own dedicated method for adding to the world)
     * @param entity
     */
    public void addEntity(Entity entity) {
        // for adding non-specific entities (ones without another dedicated list)
        nonSpecifiedEntities.add(entity);
    }

    public BasicEnemy spawnBossEnemy() {
        Pair<Integer, Integer> pos = getRandomSpawnPosition();
        int indexInPath = orderedPath.indexOf(pos);

        if (gameCycle.get() == 20) {
            for (BasicEnemy enemy : enemies) {
                if (enemy instanceof Doggie) return null;
            }
            for (BossEnemyType boss : defeatedBosses) {
                if (boss instanceof Doggie) return null;
            }
            Doggie doggieBoss = new Doggie(new PathPosition(indexInPath, orderedPath));
            enemies.add(doggieBoss);
            return doggieBoss;
        } else if (gameCycle.get() == 40 && character.getXpProperty().get() >= 10000) {
            for (BasicEnemy enemy : enemies) {
                if (enemy instanceof ElanMuske) return null;
            }
            for (BossEnemyType boss : defeatedBosses) {
                if (boss instanceof ElanMuske) return null;
            }
            ElanMuske elanBoss = new ElanMuske(new PathPosition(indexInPath, orderedPath));
            enemies.add(elanBoss);
            return elanBoss;
        }
        return null;
    }

    /**
     * spawns enemies if the conditions warrant it, adds to world
     * @return list of the enemies to be displayed on screen
     */
    public List<BasicEnemy> possiblySpawnEnemies() {
        Pair<Integer, Integer> pos = possiblyGetBasicEnemySpawnPosition();
        List<BasicEnemy> spawningEnemies = new ArrayList<>();
        if (pos != null){
            int indexInPath = orderedPath.indexOf(pos);
            Slug enemy = new Slug(new PathPosition(indexInPath, orderedPath));
            enemies.add(enemy);
            spawningEnemies.add(enemy);
        }
        // spawn from buildings
        if (character.isAtHerosCastle()) {
            for (SpawnEnemyStrategy spawnEnemyStrategy : spawnEnemyStrategies) {
                BasicEnemy newEnemy = spawnEnemyStrategy.possiblySpawnEnemy(orderedPath, gameCycle.get());
                if (newEnemy != null) {
                    enemies.add(newEnemy);
                    spawningEnemies.add(newEnemy);
                }
            }
        }

        return spawningEnemies;
    }

    /**
     * potentially spawns gold at a random position
     * @return list of new gold pieces to be displayed on screen
     */
    public List<Gold> possiblySpawnGold() {
        List<Gold> spawningGold = new ArrayList<>();

        Random random = new Random();
        if (random.nextInt(100) < 10) {
            Pair<Integer, Integer> spawnPosition = orderedPath.get(random.nextInt(orderedPath.size()));
            Gold gold = new Gold(new SimpleIntegerProperty(spawnPosition.getValue0()), new SimpleIntegerProperty(spawnPosition.getValue1()));

            spawningGold.add(gold);
            addEntity(gold);
            character.attach(gold);
        }

        return spawningGold;
    }

    /**
     * Sells an item from the shop menu and adds to character
     * @param item an item to sell from the menu
     */
    public void sellItem(Item item) {
        shopMenu.sellItem(character, item);
    }

    /**
     * Removes the item from the inventory once it has been used
     * @param item to remove when used
     * @pre item is in inventory
     */
    public void removeItemWhenUsed(Item item) {
        item.destroy();
        character.removeItemFromInventory(item);
    }

    /**
     * Removes enemies that have died for other reasons
     * @return list of enemies that have died
     */
    public List<BasicEnemy> otherDefeatedEnemies() {
        List<BasicEnemy> killedEnemies = new ArrayList<BasicEnemy>();
        for (BasicEnemy enemy : enemies) {
            if (!enemy.isAlive()) {
                enemy.destroy();
                killedEnemies.add(enemy);
            }
        }

        for (BasicEnemy enemy : killedEnemies) {
            enemies.remove(enemy);
        }
        return killedEnemies;
    }

    /**
     * Goes through buildings and removes those that shouldn't exist
     */
    public void destroyBuildings() {
        buildingEntities.removeIf(b -> !b.shouldExist().get());
    }

    /**
     * kill an enemy
     * @param enemy enemy to be killed
     */
    private void killEnemy(BasicEnemy enemy){
        enemy.destroy();
        enemies.remove(enemy);
    }

    // GET ITEM DROPS FROM ENEMY
    /**
     * Goes through and gets all of the items which a defeated enemy will drop
     * @param enemy which hsa been defeated
     * @return List<Item> all the items that the enemy drops when it has been defeated drops
     */
    public List<Item> defeatedEnemyItemDrops(BasicEnemy enemy) {
        List<GenerateItem> itemDrops = enemy.getItemDrops();
        List<Item> itemInstances = new ArrayList<Item>();

        for (int i = 0; i < itemDrops.size(); i++) {
            Pair<Integer, Integer> coords = getFirstAvailableSlotForItem();
            int x = coords.getValue0() + i;
            int y = coords.getValue1();
            if (x > 3) {
                x = 0;
                y += 1;
            }
            
            if (!(x > 3 && y == 3)) {
                Item newDrop = itemDrops.get(i).createItem(new SimpleIntegerProperty(x), new SimpleIntegerProperty(y));
                itemInstances.add(newDrop);
            }
        }
        for (Item item : itemInstances) {
            character.addItemToInventory(item);
        }
        return itemInstances;
    }

    /**
     * Updates gold and xp once the enemy has been defeated
     * @param enemy That drops gold and xp once defeated
     */
    public void getGoldAndXpDrops(BasicEnemy enemy) {
        enemy.getXPAndGold(character);
    }

    // GET CARD DROPS FROM ENEMY
    /**
     * Goes through and gets all of the cards which a defeated enemy will drop
     * @param enemy which hsa been defeated
     * @return List<Card> all the cards that the enemy drops when it  has been defeated drops
     */
    public List<Card> defeatedEnemyCardDrops(BasicEnemy enemy) {
        List<GenerateCard> cardDrops = enemy.getCardDrops();
        List<Card> cardInstances = new ArrayList<Card>();

        for (int i = 0; i < cardDrops.size(); i++) {
            // if adding more cards than have, remove the first card...
            if (cardEntities.size() >= getWidth()){
                character.addGold(10);
                character.addXp(10);
                removeCard(0);
            }
            SimpleIntegerProperty x = new SimpleIntegerProperty(cardEntities.size());
            SimpleIntegerProperty y = new SimpleIntegerProperty(0);
            Card newCard = cardDrops.get(i).createCard(x, y);
            cardEntities.add(newCard);
            cardInstances.add(newCard);
        }

        return cardInstances;
    }

     /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public Card loadCard () {
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            character.addGold(5);
            character.addXp(5);
            removeCard(0);
        }
        SimpleIntegerProperty posX = new SimpleIntegerProperty(cardEntities.size());
        SimpleIntegerProperty posY = new SimpleIntegerProperty(0);
        // pick a random card
        List<Card> potentialCards = new ArrayList<>();
        potentialCards.add(new VampireCastleCard(posX, posY));
        potentialCards.add(new ZombiePitCard(posX, posY));
        potentialCards.add(new VillageCard(posX, posY));
        potentialCards.add(new TrapCard(posX, posY));
        potentialCards.add(new TowerCard(posX, posY));

        Random random = new Random();
        Card newCard = potentialCards.get(random.nextInt(potentialCards.size()));

        cardEntities.add(newCard);
        return newCard;
    }

    /**
     * run the expected battles in the world, based on current world state
     * @return list of enemies which have been killed
     */
    public List<BasicEnemy> runBattles() {
        if (characterAtHerosCastle()) { return enemies; }
        List<BasicEnemy> defeatedEnemies = new ArrayList<BasicEnemy>();
        for (BasicEnemy e: enemies){
            // Pythagoras: a^2+b^2 < radius^2 to see if within radius
            // TODO = you should implement different RHS on this inequality, based on influence radii and battle radii
            if (Math.pow((character.getX()-e.getX()), 2) +  Math.pow((character.getY()-e.getY()), 2) < Math.pow(e.getBattleRadius(), 2)){
                // Loop through enemies again, to see who is in the influence radius of the enemy, and add them to the battle.
                List<BasicEnemy> enemiesEncountered = new ArrayList<BasicEnemy>();
                enemiesEncountered.add(e);
                for (BasicEnemy support : enemies) {
                    if (Math.pow((e.getX()-support.getX()), 2) +  Math.pow((e.getY()-support.getY()), 2) < Math.pow(support.getSupportRadius(), 2)) {
                        enemiesEncountered.add(support);
                    }
                }
                setCurrentBattle(new Battle(character, enemiesEncountered));
                if (character.isAlive()) {
                    defeatedEnemies.add(e);
                    // if defeated boss add to list
                    if (e instanceof BossEnemyType) defeatedBosses.add((BossEnemyType) e);
                } else {
                    // Finish Game
                }
            }
        }
        for (BasicEnemy e: defeatedEnemies){
            // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from the enemies list
            // if we killEnemy in prior loop, we get java.util.ConcurrentModificationException
            // due to mutating list we're iterating over
            killEnemy(e);
        }
        return defeatedEnemies;
    }

    /**
     * Sets current battle
     * @param battle the current battle happening
     */
    public void setCurrentBattle(Battle battle) {
        this.currentBattle = battle;
    }

    /**
     * remove card at a particular index of cards (position in gridpane of unplayed cards)
     * @param index the index of the card, from 0 to length-1
     */
    private void removeCard(int index){
        Card c = cardEntities.get(index);
        int x = c.getX();
        c.destroy();
        cardEntities.remove(index);
        shiftCardsDownFromXCoordinate(x);
    }


    /**
     * remove an item by x,y coordinates
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y){
        Item item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        character.removeItemFromInventory(item);
    }

    /**
     * Adds in items by cordinates to the inventory
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void equipInventoryItemByCoordinates(int x, int y){
        Item item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        if (item instanceof EquippableItem) {
            character.equipItem((EquippableItem) item);
        }
    }

    /**
     * run moves which occur with every tick without needing to spawn anything immediately
     */
    public void runTickMoves(){
        // move characters
        character.moveDownPath();
        character.updateObservers();

        // move enemies
        for (BasicEnemy e : enemies) {
            e.move(tick);
            e.updateObservers();
        }
        tick++;
    }


    /**
     * return an unequipped inventory item by x and y coordinates
     * assumes that no 2 unequipped inventory items share x and y coordinates
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    private Item getUnequippedInventoryItemEntityByCoordinates(int x, int y){
        for (Item e: character.getInventory()) {
            if ((e.getX() == x) && (e.getY() == y)){
                return e;
            }
        }
        return null;
    }

    /**
     * Gets the current battle object
     * @return currentBattle
     */
    public Battle getCurrentBattle() {
      return currentBattle;
    }

    /**
     * get the first pair of x,y coordinates which don't have any items in it in the unequipped inventory
     * @return  Pair<Integer, Integer>  x,y coordinate pair
     */
    public Pair<Integer, Integer> getFirstAvailableSlotForItem() {
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available slot defined by looking row by row
        for (int y=0; y<unequippedInventoryHeight; y++){
            for (int x=0; x<unequippedInventoryWidth; x++){
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null){
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    /**
     * shift card coordinates down starting from x coordinate
     * @param x x coordinate which can range from 0 to width-1
     */
    private void shiftCardsDownFromXCoordinate(int x) {
        for (Card c: cardEntities){
            if (c.getX() >= x){
                c.x().set(c.getX()-1);
            }
        }
    }

    /**
     * Observe character movements
     * @param Character the character to observe
     */
    public void encounter(Character character) {
        if (character.isAtHerosCastle()) {
            iterateGamecycle();
        }
    }

    /**
     * Checks if the character is at heros castle
     * @return boolean
     */
    public boolean characterAtHerosCastle() {
        return character.isAtHerosCastle();
    }

    /**
     * get a randomly generated position which could be used to spawn an enemy
     * @return null if random choice is that wont be spawning an enemy or it isn't possible, or random coordinate pair if should go ahead
     */
    private Pair<Integer, Integer> possiblyGetBasicEnemySpawnPosition() {
        
        // has a chance spawning a basic enemy on a tile the character isn't on or immediately before or after (currently space required = 2)...
        Random rand = new Random();
        int choice = rand.nextInt(2); // TODO = change based on spec... currently low value for dev purposes...
        // TODO = change based on spec
        if ((choice == 0) && (enemies.size() < 2)){
            return getRandomSpawnPosition();
        }
        return null;
    }

    private Pair<Integer, Integer> getRandomSpawnPosition() {
        Random rand = new Random(System.currentTimeMillis());
        List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
        int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
        // inclusive start and exclusive end of range of positions not allowed
        int startNotAllowed = (indexPosition - 2 + orderedPath.size())%orderedPath.size();
        int endNotAllowed = (indexPosition + 3)%orderedPath.size();
        // note terminating condition has to be != rather than < since wrap around...
        for (int i=endNotAllowed; i!=startNotAllowed; i=(i+1)%orderedPath.size()){
            orderedPathSpawnCandidates.add(orderedPath.get(i));
        }

        // choose random choice
        Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates.get(rand.nextInt(orderedPathSpawnCandidates.size()));

        return spawnPosition;
    }

    /**
     * remove a card by its x, y coordinates
     * @param cardNodeX x index from 0 to width-1 of card to be removed
     * @param cardNodeY y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     * @pre the position of the building to be added is a valid position
     * @return A new building
     */
    public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        // start by getting card
        Card card = null;
        for (Card c: cardEntities){
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)) { // Check placeable
                card = c;
                break;
            }
        }
        
        // Check for character position, enemy positon, spawn
        // now spawn building
        Building newBuilding = card.generateBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
        buildingEntities.add(newBuilding);
        if (newBuilding instanceof SpawnEnemyStrategy) {
            spawnEnemyStrategies.add((SpawnEnemyStrategy) newBuilding);
        }

        if (newBuilding instanceof CharacterPositionObserver) {
            character.attach((CharacterPositionObserver) newBuilding);
        }

        if (newBuilding instanceof EnemyPositionObserver) {
            for (BasicEnemy enemy : enemies) {
                enemy.attach((EnemyPositionObserver) newBuilding);
            }
        }

        // destroy the card
        card.destroy();
        cardEntities.remove(card);
        shiftCardsDownFromXCoordinate(cardNodeX);

        return newBuilding;
    }

    /**
     * Checks if a card is placemable and thus can build
     * @param cardNodeX x index from 0 to width-1 of card to be removed
     * @param cardNodeY y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     * @return boolean if the build can happen
     */
    public boolean canBuildByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        Card card = null;
        for (Card c: cardEntities){
            if ((c.getX() == cardNodeX) && (c.getY() == cardNodeY)) { // Check placeable
                card = c;
                break;
            }
        } 

        if (card != null) {
            return card.isValidPosition(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY), orderedPath);
        }
        return false;
    }

    /**
     * iterates cycle
     */
    public void iterateGamecycle() {
        this.gameCycle.set(this.gameCycle.get() + 1);
    }

    /**
     * Gets game cycle as an integer
     * @return cycle
     */
    public int getGameCycle() {
        return this.gameCycle.get();
    }

    /**
     * Gets game cycle as a SimpleIntegerProperty
     * @return cycle
     */
    public SimpleIntegerProperty getGameCycleProperty() {
        return this.gameCycle;
    }

    /**
     * Resets game to initial state
     * @return void
     */
    public void resetGame() {
        nonSpecifiedEntities = new ArrayList<>();
        character.reset();
        for (BasicEnemy enemy : enemies) {
            enemy.destroy();
        }
        enemies.clear();
        for (Card card : cardEntities) {
            card.destroy();
        }
        cardEntities.clear();
        for (Building building : buildingEntities) {
            building.destroy();
        }
        buildingEntities.clear();
        spawnEnemyStrategies.clear();
        gameCycle.set(0);
    }

}
