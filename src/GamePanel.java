import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private static final int TEXTURE_NUM = 3;
    private static final int TILE_SIZE = 100;

    private char[][] grid;
    private ArrayList<Image> textures = new ArrayList<>();
    GamePanel() throws IOException {
        setBackground(Color.black);
        loadTextures();
    }

    public void redraw(char[][] grid){
        this.grid = grid;
        repaint();
    }
    private void loadTextures() throws IOException {
        for(int i = 0;i < TEXTURE_NUM;i++){
            textures.add(ImageIO.read(new File("src/textures/texture" + i + ".png")));
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(grid != null) {
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid.length; y++) {
                    if (grid[y][x] == ShipBattleGame.EMPTY_CELL || grid[y][x] == ShipBattleGame.SHIP_CELL) {
                        g2.drawImage(textures.get(0), TILE_SIZE * x, TILE_SIZE * y, TILE_SIZE,TILE_SIZE,this);
                    }
                    else if(grid[y][x] == ShipBattleGame.HIT_CELL){
                        g2.drawImage(textures.get(1), TILE_SIZE * x, TILE_SIZE * y, TILE_SIZE,TILE_SIZE,this);
                    }
                    else if(grid[y][x] == ShipBattleGame.MISS_CELL){
                        g2.drawImage(textures.get(2), TILE_SIZE * x, TILE_SIZE * y, TILE_SIZE,TILE_SIZE,this);
                    }
                }
            }
        }
        g2.dispose();
    }

}
