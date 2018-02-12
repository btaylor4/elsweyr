# elsweyr
This is our first iteration. Story and theme to come.

## How To Play:
### Main Menu:
* Load Game: if you have a saved game
* New Game: where player picks name and avatar
* Quit: player exits the game

### Loading/ Creating/ Saving:
Require player to select one of three slots to load/ create/ save into

### In Game Menu:
The in game menu can be shown by clicking the in game menu button inside of the global view or the local view and will show the following options:

* Save Game: player can choose to save his current location
* Resume Game: player chooses to go back into the game
* Quit to Main: player goes back to main menu

### Movement:
The following controls below are what can be pressed to move on either the global map or global map

#### Using the num pad:
* 1: down-left
* 2: down
* 3: down-right
* 4: left
* 6: right
* 7: up-left
* 8: up
* 9: up-right

#### Using arrows:
* left: left
* up: up
* right: right
* down: down

### Effects:
* Positive Health over time: green cross on map
* Negative Health over time: red cross on map
* Instant Death: skull on map
* Level Up: yellow up arrow
  
### Decals:
* Green Cross: +health
* Red Cross: -health
* Skull: instant death
* Yellow Arrow: level up
  
### Items:
* Takeable:
  * Key - unlocks door (when equipped): key on map
  * Food - allows you to pet an animal (when equipped): food item on map
  * Sword - just for looks (is equippable): sword on map
* Interactive:
  * Door - unlocked by key (prevents movement to tile until unlocked): door on map
  * Animal - pettable when carrying food (prevents movement to tile until fed): animal by map
* OneShot:
  * Health Pot - gives player health and is removed from map: potion bottle on map
  * Banana Peel - damages player when walked on, then removed from map: banana peel on map
  * Book - gives player experience points and is removed: book on map
* Obstacle:
  * Spikes - prevent player from entering tile: spikes on map
  * Wall - prevent player from entering tile: wall on map
    
### Terrain:
* Grass - player can walk on: green
* Water - player cannot walk on: blue
* Mountain - player cannot walk on: grey
  
### Inventory:
The player can open from local map to equip, unequip, and drop items
