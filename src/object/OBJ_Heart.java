package object;

import main.GamePanel;

public class OBJ_Heart extends Object {
    public OBJ_Heart(GamePanel gamePanel){
        super(gamePanel);

        name = "Heart";
        image1 = setup("/res/object/heart_full", gamePanel.tileSize, gamePanel.tileSize);
        image2 = setup("/res/object/heart_half", gamePanel.tileSize, gamePanel.tileSize);
        image3 = setup("/res/object/heart_blank", gamePanel.tileSize, gamePanel.tileSize);
    }
}
