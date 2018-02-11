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
                else if(zoneRow == 15 && zoneCol == 15){
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
                if(tileCol < 6 || tileRow < 6 || tileCol>23 || tileRow>23){
                    initWaterTile();
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
        System.out.println("WATER 0");
    }
    private static void initMountainTile(){
        System.out.println("MOUNTAIN 0");
    }
    private static void initTileWithHealth(int i){
        Random rand = new Random();
        System.out.println("GRASS 2");
        System.out.println("Effect HEALTHEFFECT " + (rand.nextInt(5)+1) + " " + (rand.nextInt(25)+1) + " " + i);
        System.out.println("Decal ArtAssets/healDecal.png");
    }
    private static void initTileWithDeath(int i){
        System.out.println("GRASS 2");
        System.out.println("Effect HEALTHEFFECT 0 -100000 " + i);
        System.out.println("Decal ArtAssets/deathDecal.png");
    }
    private static void initTileWithLevelUp(int i){
        System.out.println("GRASS 2");
        System.out.println("Effect LEVELUPEFFECT " + i);
        System.out.println("Decal ArtAssets/levelupDecal.png");
    }
    private static void initTileWithDamage(int i){
        Random rand = new Random();
        System.out.println("GRASS 2");
        System.out.println("Effect HEALTHEFFECT " + (rand.nextInt(10)+1) + " " + -(rand.nextInt(25)+1) + " " + i);
        System.out.println("Decal ArtAssets/damageDecal.png");
    }


}
