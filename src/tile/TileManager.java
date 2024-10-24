package tile;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    private GamePanel gamePanel;
    private Tile[] tile;
    private int mapTileNum[][];
    private UtilityTool utilityTool = new UtilityTool();

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        tile = new Tile[50];

        mapTileNum = new int[gamePanel.maxWorldRow][gamePanel.maxScreenCol];

        getTileImage();

        loadMap("/res/map/worldV2.txt");
    }

    public void loadMap(String filePath){

    }

    public void getTileImage(){
        //PLACEHOLDER
        tile[0] = utilityTool.setup("grass00",gamePanel.tileSize, gamePanel.tileSize, false);
        tile[1] = utilityTool.setup("grass00", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[2] = utilityTool.setup("grass00", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[3] = utilityTool.setup("grass00", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[4] = utilityTool.setup("grass00", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[5] = utilityTool.setup("grass00", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[6] = utilityTool.setup("grass00", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[7] = utilityTool.setup("grass00", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[8] = utilityTool.setup("grass00", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[9] = utilityTool.setup("grass00", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[10] = utilityTool.setup("grass00", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[11] = utilityTool.setup("grass01", gamePanel.tileSize, gamePanel.tileSize, false);
        //PLACEHOLDER

        tile[12] = utilityTool.setup("water00", gamePanel.tileSize, gamePanel.tileSize, true);
        tile[13] = utilityTool.setup("water01", gamePanel.tileSize, gamePanel.tileSize, true);
        tile[14] = utilityTool.setup("water02", gamePanel.tileSize, gamePanel.tileSize, true);
        tile[15] = utilityTool.setup("water03", gamePanel.tileSize, gamePanel.tileSize, true);
        tile[16] = utilityTool.setup("water04", gamePanel.tileSize, gamePanel.tileSize, true);
        tile[17] = utilityTool.setup("water05", gamePanel.tileSize, gamePanel.tileSize, true);
        tile[18] = utilityTool.setup("water06", gamePanel.tileSize, gamePanel.tileSize, true);
        tile[19] = utilityTool.setup("water07", gamePanel.tileSize, gamePanel.tileSize, true);
        tile[20] = utilityTool.setup("water08", gamePanel.tileSize, gamePanel.tileSize, true);
        tile[21] = utilityTool.setup("water09", gamePanel.tileSize, gamePanel.tileSize, true);
        tile[22] = utilityTool.setup("water10", gamePanel.tileSize, gamePanel.tileSize, true);
        tile[23] = utilityTool.setup("water11", gamePanel.tileSize, gamePanel.tileSize, true);
        tile[24] = utilityTool.setup("water12", gamePanel.tileSize, gamePanel.tileSize, true);
        tile[25] = utilityTool.setup("water13", gamePanel.tileSize, gamePanel.tileSize, true);

        tile[26] = utilityTool.setup("road00", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[27] = utilityTool.setup("road01", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[28] = utilityTool.setup("road02", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[29] = utilityTool.setup("road03", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[30] = utilityTool.setup("road04", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[31] = utilityTool.setup("road05", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[32] = utilityTool.setup("road06", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[33] = utilityTool.setup("road07", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[34] = utilityTool.setup("road08", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[35] = utilityTool.setup("road09", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[36] = utilityTool.setup("road10", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[37] = utilityTool.setup("road11", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[38] = utilityTool.setup("road12", gamePanel.tileSize, gamePanel.tileSize, false);

        tile[39] = utilityTool.setup("earth", gamePanel.tileSize, gamePanel.tileSize, false);
        tile[40] = utilityTool.setup("wall", gamePanel.tileSize, gamePanel.tileSize, true);
        tile[41] = utilityTool.setup("tree", gamePanel.tileSize, gamePanel.tileSize, true);
    }

}
