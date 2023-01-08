import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Field extends JPanel implements ActionListener {

    private final Image field;
    private final Apple apple = new Apple();
    private final Snake snake = new Snake();

    private final int SCALE = 32;
    private boolean inGame = true;

    Field() {
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT && !snake.right) {
                    snake.left = true;
                    snake.up = false;
                    snake.down = false;
                }

                if (key == KeyEvent.VK_RIGHT && !snake.left) {
                    snake.right = true;
                    snake.up = false;
                    snake.down = false;
                }

                if (key == KeyEvent.VK_UP && !snake.down) {
                    snake.up = true;
                    snake.right = false;
                    snake.left = false;
                }

                if (key == KeyEvent.VK_DOWN && !snake.up) {
                    snake.down = true;
                    snake.right = false;
                    snake.left = false;
                }
            }
        });

        try {
            field = ImageIO.read(new File("resources", "grass01.png"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
        initGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawFieldTiles(g);

        if (inGame) {
            g.drawImage(apple.getImage(), apple.getX(), apple.getY(), this);
            for (int i = 0; i < snake.dots; i++) {
                g.drawImage(snake.getImageSnakeBODY(), snake.snakeX[i], snake.snakeY[i], this);
            }
        } else {
            String str = "Game Over";
            g.setColor(Color.white);
            g.drawString(str, 125, 320 / 2);
        }

    }

    private void initGame() {
        snake.dots = 3;
        for (int i = 0; i < snake.dots; i++) {
            snake.snakeX[i] = 32 - i * SCALE;
            snake.snakeY[i] = 32;
        }
        Timer timer = new Timer(200, this);
        timer.start();
        createApple();
    }

    private void drawFieldTiles(Graphics g) {

        Image newField = field.getScaledInstance(SCALE, SCALE, Image.SCALE_DEFAULT);

        for (int i = 0; i < 10; i += 1) {
            for (int j = 0; j < 10; j += 1) {
                g.drawImage(newField, i * SCALE, j * SCALE, this);
            }
        }

    }

    private void drawLines(Graphics g) {
        for (int x = 0; x < 16 * SCALE; x+= SCALE) {

            g.setColor(Color.BLACK);
            g.drawLine(x, 0, x, 16 * SCALE);

        }

        for (int y = 0; y < 16 * SCALE; y+= SCALE) {

            g.setColor(Color.BLACK);
            g.drawLine(0, y, 16 * SCALE, y);

        }
    }

    private void createApple() {
        apple.generateApple();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        checkApple();
        checkCollissions();
        repaint();
    }

    public void checkCollissions() {

        for (int i = snake.dots; i > 0; i--) {
            if (i > 4 && snake.snakeX[0] == snake.snakeX[i] && snake.snakeY[0] == snake.snakeY[i]) {
                inGame = false;
            }
        }

        if (snake.snakeX[0] > 336) {
            inGame = false;
        }

        if (snake.snakeX[0] < 0) {
            inGame = false;
        }

        if (snake.snakeY[0] > 336) {
            inGame = false;
        }

        if (snake.snakeY[0] < 0) {
            inGame = false;
        }
    }

    private void checkApple() {
        if (snake.snakeX[0] == apple.getX() && snake.snakeY[0] == apple.getY()) {
            snake.dots++;
            createApple();
        }
    }

}
