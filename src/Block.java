import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Block{
    int x,y;
    BufferedImage image;
    int health;
    MyPanel panel;
    public Block(MyPanel panel, int x, int y){
        setUp(x,y);
        this.panel = panel;
        health = 2;
        this.panel.blocks.add(this);
    }
    public void setUp(int x, int y){
        try {
            image = ImageIO.read(new File("png/Grid/brick.png"));
        } catch (IOException e) {
        }
        this.x = x*image.getWidth();
        this.y = y*image.getHeight();
    }
    public void getDamage(){
        health--;
        if(health <= 0) {
            panel.gameMap.grids[y/30][x/30] = 1;
            panel.trash.add(this);
        }
    }
    public void paint(Graphics2D g2d){
        g2d.drawImage(image,x,y,null);
    }
    public Rectangle2D getRectangle2D(){
        return new Rectangle2D.Double(x,y,image.getWidth(),image.getHeight());
    }
}
