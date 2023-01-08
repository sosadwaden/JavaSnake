import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    private Image SnakeBODY;

    List<Point> snakeCoord = new ArrayList<>(3);
    int[] snakeX = new int[100];
    int[] snakeY = new int[100];

    int dots;

    boolean up = false;
    boolean down = false;
    boolean right = true;
    boolean left = false;


    Snake()  {
        try {
            SnakeBODY = ImageIO.read(new File("resources/SnakeSprites", "SnakeBODY.png")).getScaledInstance(32, 32, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Snake(int x1, int y1, int x2, int y2, int x3, int y3) {
        snakeX[0] = x1;
        snakeX[1] = x2;
        snakeX[2] = x3;

        snakeY[0] = y1;
        snakeY[1] = y2;
        snakeY[2] = y3;
    }

    void move() {
        for (int i = dots; i > 0; i--) {
            snakeX[i]= snakeX[i-1];
            snakeY[i]= snakeY[i-1];
        }
        if (left) {
            snakeX[0] -= 32;
        }

        if (right) {
            snakeX[0] += 32;
        }

        if (up) {
            snakeY[0] -= 32;
        }

        if (down) {
            snakeY[0] += 32;
        }
    }

    Image getImageSnakeBODY() {
        return SnakeBODY;
    }

}
