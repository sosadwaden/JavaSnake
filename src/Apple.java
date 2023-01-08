import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Apple {

    private Image apple;
    private final Random rnd = new Random();
    private int x;
    private int y;

    Apple() {

        x = 0;
        y = 0;

        try {
            apple = ImageIO.read(new File("resources", "apple.png")).getScaledInstance(32, 32, Image.SCALE_DEFAULT);;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void generateApple() {
        x = (new Random().nextInt(10)) * 32;
        y = (new Random().nextInt(10)) * 32;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Image getImage() {
        return apple;
    }

}
