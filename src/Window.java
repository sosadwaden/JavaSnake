import javax.swing.*;
import java.io.IOException;

public class Window extends JFrame {

    // Разрешение окна для тайлов по 16 пикселя
    private final int WIDTH_16BIT = 336;
    private final int HEIGHT_16BIT = 359;

    Window() {
        setSize(WIDTH_16BIT, HEIGHT_16BIT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Field());
        setVisible(true);
    }

}
