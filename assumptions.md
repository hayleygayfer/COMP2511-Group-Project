# Assumptions
### Starting state
- A player starts with 50 health and can deal 2 damage

### Movement
- When the game is in play, the character moves at a rate of one tile per second

### Defining radius
- Radius is given as the line distance calculated using coordinate positions

### Buying and equipping items
- The Human Player will not be allowed to buy any items the character can’t afford
- The character can only equip one item of each type at a time
- The character can have multiple of each item type in their inventory

### Battle behaviour
- Order of attack: character → allied soldiers in order of age → enemy → enemy support troops in order of distance
- Our character and our allied soldiers will inflict damage on the same enemy until it dies, and then move on to the next
- Enemy characters inflict damage on allied soldiers first, and will only move onto the character after the allied soldiers die
- An allied soldier has 20 health and can do 8 damage

### Buildings and cards
- The player can have a maximum of 6 building cards
- A tower has a shooting radius of 2, and deals 1 point of damage on an enemy character for every second that it is within this shooting radius
- Buildings that are on path tiles are activated when the character/enemy steps on the same tile
- A campfire has a battle radius of 2

### Enemy stats
- Enemies move in an anti-clockwise direction
- Speed is given relative to the character’s speed

| Type | Health | Damage | Battle radius | Support radius | Speed |
|------|--------|--------|---------------|----------------|-------|
| Slug | 10 | 3 | 1 | 1 | 1 |
| Zombie | 10 | 6,  For each attack on an allied soldier there is a 10% chance of a critical bite | 2 | 2 | 0.5 |
| Vampire | 20 | Standard: 10, Critical: 20, for each attack there is a 20% chance of a critical attack occurring | 3 | 4 | 1 |

### Attack item stats
| Type | Damage | Cost (gold) |
|------|--------|-------------|
| Sword | 10 | 10 |
| Stake | Normal: 6, Vampires: 20 | 10 |
| Staff | Normal: 3, Trance lasts 5 attack turns | 10 |


### Defence item stats
| Type | Defence | Cost (gold) |
|------|---------|-------------|
| Armour | Enemy attacks are halved | 10 |
| Shield | Vampire critical attack chance decreases by 60% | 10 |
| Helmet | Enemy attacks are reduced by 2, Damage to enemies is reduced by 2 | 10 |


