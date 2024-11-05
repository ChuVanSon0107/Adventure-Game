package enemy;

import java.awt.Graphics2D;
import java.util.Random;

import main.GamePanel;

public class MONSTER_GreenSlime extends Monster {


    public MONSTER_GreenSlime(GamePanel gamePanel) {
        super(gamePanel);

        name = "Green Slime";
        
        getImage();
    }

    @Override
    public void getImage() {
        up1 = setup("/res/monster/greenslime_down_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/res/monster/greenslime_down_2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/res/monster/greenslime_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/res/monster/greenslime_down_2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/res/monster/greenslime_down_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/res/monster/greenslime_down_2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/res/monster/greenslime_down_1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/res/monster/greenslime_down_2", gamePanel.tileSize, gamePanel.tileSize);

    }

    @Override
    public void update() {
        setAction();

        collisionOn = false;
        checkTile();
        checkObject();
        checkMonster();
        
        boolean contactPlayer = checkPlayer();

        


    }

    @Override
    public void draw(Graphics2D graphics2d) {
        
    }

    @Override
    public void setAction() {
        Random random = new Random();
        int i = random.nextInt(100) + 1;

        if(i <= 25){
            direction = "up";
        }
        else if(25 < i && i <= 50){
            direction = "down";
        }
        else if(50 < i && i <= 75){
            direction = "left";
        }
        else{
            direction = "right";
        }
    }

    @Override
    public void attack() {
        
    }


}
