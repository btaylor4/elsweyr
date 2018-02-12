import java.util.Random;

public class MapGenerator {

    public static void main(String[] args){
        Random rand = new Random();

        System.out.println("40 40");//global level size
        System.out.println("0");//game time
        for(int zoneCol = 0; zoneCol < 40; zoneCol++){
            for(int zoneRow = 0; zoneRow < 40; zoneRow++) {
                System.out.println("0 0");//Local Level Size
                System.out.println("0 0");//End tile
                System.out.println("0 0");//Start tile
                int pathNum = rand.nextInt(2) + 1;
                if(zoneCol < 6 || zoneRow < 6 || zoneCol>33 || zoneRow>33){
                    System.out.println("ArtAssets/Lava.png");
                    System.out.println("0");
                }
                else if((zoneRow == 15 && zoneCol == 15)){
                    System.out.println("ArtAssets/Path" + pathNum + ".png");
                    System.out.println("1");
                    initForest();
                }
                else if(zoneRow == 15 && zoneCol == 20){
                    System.out.println("ArtAssets/Path" + pathNum + ".png");
                    System.out.println("1");
                    initTutorial();
                }
                else if((zoneRow == 20 && zoneCol == 15)){
                    System.out.println("ArtAssets/Path" + pathNum + ".png");
                    System.out.println("1");
                    initForest();
                }
                else if(zoneRow == 20 && zoneCol == 20){
                    System.out.println("ArtAssets/Path" + pathNum + ".png");
                    System.out.println("1");
                    initForest();
                }
                else{
                    System.out.println("ArtAssets/Path" + pathNum + ".png");
                    System.out.println("1");
                }
            }
        }
    }

    private static void initForest(){
        Random rand = new Random();
        System.out.println("30 30");//forest size
        System.out.println("10 10");//end tile
        System.out.println("20 20");//start tile
        System.out.println("ArtAssets/Forest.png");
        System.out.println("1");
        for(int tileCol = 0; tileCol < 30; tileCol++){
            for(int tileRow = 0; tileRow < 30; tileRow++){
                if((tileCol < 6 || tileRow < 6 || tileCol>23 || tileRow>23)){
                    initWaterTile();
                }
                else{
                    if((tileCol == 10 && tileRow == 10)){
                        //initGrassTile(30*tileRow + tileCol);
                        System.out.println("GRASS 1");
                        System.out.println("Item INTERACTIVE Door");
                    }
                    else if((tileCol == 10 && tileRow == 11)){
                        //initGrassTile(30*tileRow + tileCol);
                        System.out.println("GRASS 1");
                        System.out.println("Item TAKEABLE Key");
                    }
                    else{
                        int tileTerrain = rand.nextInt(100);
                        if(tileTerrain < 10){
                            initWaterTile();
                        }
                        else if(tileTerrain >=10 && tileTerrain < 20){
                            initMountainTile();
                        }
                        else{
                            initGrassTile(30*tileRow + tileCol);
                        }
                    }
                }
            }
        }
    }

    private static void initTutorial(){
        Random rand = new Random();
        System.out.println("20 20");//forest size
        System.out.println("7 7");//end tile
        System.out.println("14 14");//start tile
        System.out.println("ArtAssets/Town.png");
        System.out.println("1");
        for(int tileCol = 0; tileCol < 20; tileCol++){
            for(int tileRow = 0; tileRow < 20; tileRow++){
                if(tileCol < 4 || tileRow < 4 || tileCol>16 || tileRow>16){
                    initWaterTile();
                }
                else if((tileCol == 10 && tileRow == 11)){
                    //initGrassTile(30*tileRow + tileCol);
                    System.out.println("GRASS 1");
                    System.out.println("Item TAKEABLE Key");
                }
                else{
                    if((tileCol == 7 && tileRow == 7)){
                        //initGrassTile(30*tileRow + tileCol);
                        System.out.println("GRASS 1");
                        System.out.println("Item INTERACTIVE Door");
                    }
                    else{
                        int tileTerrain = rand.nextInt(100);
                        if(tileTerrain < 7){
                            initWaterTile();
                        }
                        else if(tileTerrain >=7 && tileTerrain < 16){
                            initMountainTile();
                        }
                        else{
                            initGrassTile(20*tileRow + tileCol);
                        }
                    }
                }
            }
        }
    }


    private static void initGrassTile(int i){
        Random rand = new Random();
        int effect = rand.nextInt(100);
        if(effect<5){//levelup
            initTileWithLevelUp(i);
        }
        else if(effect>=5 && effect <10 ){//death
            initTileWithDeath(i);
        }
        else if(effect>=10 && effect < 20){//-health
            initTileWithDamage(i);
        }
        else if(effect >= 20 && effect < 35){//+health
            initTileWithHealth(i);
        }
        else{//no effect
            System.out.println("GRASS 0");
        }
    }
    private static void initWaterTile(){
        System.out.println("WATER 1");
        System.out.println("Item NONE");
    }
    private static void initMountainTile(){
        System.out.println("MOUNTAIN 1");
        System.out.println("Item NONE");
    }
    private static void initTileWithHealth(int i){
        Random rand = new Random();
        System.out.println("GRASS 3");
        System.out.println("Effect HEALTHEFFECT " + (rand.nextInt(5)+1) + " " + (rand.nextInt(25)+1) + " " + i);
        System.out.println("Decal ArtAssets/healDecal.png");
        initTileWithItem();
    }
    private static void initTileWithDeath(int i){
        System.out.println("GRASS 2");
        System.out.println("Effect HEALTHEFFECT 0 -100000 " + i);
        System.out.println("Decal ArtAssets/deathDecal.png");
        //initTileWithItem();
    }
    private static void initTileWithLevelUp(int i){
        System.out.println("GRASS 3");
        System.out.println("Effect LEVELUPEFFECT " + 0);    //Changed
        System.out.println("Decal ArtAssets/levelupDecal.png");
        initTileWithItem();
    }
    private static void initTileWithDamage(int i){
        Random rand = new Random();
        System.out.println("GRASS 2");
        System.out.println("Effect HEALTHEFFECT " + (rand.nextInt(10)+1) + " " + -(rand.nextInt(25)+1) + " " + i);
        System.out.println("Decal ArtAssets/damageDecal.png");
        //initTileWithItem();
    }

    private static void initTileWithItem(){
        Random rand = new Random();
        int itemTypeInt = rand.nextInt(10);
        if(itemTypeInt == 0){
            System.out.println("Item OBSTACLE Bed of Spikes");
        }
        else if(itemTypeInt == 1){
            //Wall
            System.out.println("Item OBSTACLE Spike");
        }
        else if(itemTypeInt == 2){
            //Door
            System.out.println("Item INTERACTIVE Door");
        }
        else if(itemTypeInt == 3){
            //animal
            System.out.println("Item INTERACTIVE Animal");
        }
        else if(itemTypeInt == 4){
            //key
            System.out.println("Item TAKEABLE Key");
        }
        else if(itemTypeInt == 5){
            //food
            System.out.println("Item TAKEABLE Food");
        }
        else if(itemTypeInt == 6){
            //sword
            System.out.println("Item TAKEABLE Sword");
        }
        else if(itemTypeInt == 7){
            //health pot
            System.out.println("Item ONESHOT Health Pot");
        }
        else if(itemTypeInt == 8){
            //banana peel
            System.out.println("Item ONESHOT Banana peel");
        }
        else if(itemTypeInt == 9){
            //book
            System.out.println("Item ONESHOT Book");
        }
        else{   //returns a none type
            System.out.println("Item NONE");
        }
    }
}

