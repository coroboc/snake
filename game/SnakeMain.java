package game;

import objects.Apple;
import objects.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class SnakeMain extends JPanel implements ActionListener {
    public static JFrame jFrame;
    public static final int SCALE = 32;//размер клетки в пикселяз
    public static final int WIDTH = 20; //щирина в клетках
    public static final int HEIGHT = 20; //высота в клетках
    Snake snake = new Snake(5, 6, 5, 5);
    Apple apple = new Apple(
            (int) (Math.random() * SnakeMain.HEIGHT - 1),
            (int) (Math.random() * SnakeMain.WIDTH - 1)
    );

    //скорость отрисовки
    public static int speed = 5;
    Timer timer = new Timer(1000 / speed, this);

    //запуск отрисовки
    public SnakeMain() {
        timer.start();
        //конструтор

        //джава принимает сигналы с клавиатуры и понимает что они предназчены именно для нашего приложения
        addKeyListener((new KeyBoard()));
        setFocusable(true);
    }

    //метод для отрисовки графики
    public void paint(Graphics graphics) {
        // отрисовка поля
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        //горизонтальной линии
        for (int x = 0; x < WIDTH * SCALE; x = x + SCALE) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(x, 0, x, HEIGHT * SCALE);
//отрисовка вертикальной линии
        }
        for (int y = 0; y < HEIGHT * SCALE; y = y + SCALE) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(0, y, WIDTH * SCALE, y);
//отрисовка змеи
            for (int i = 0; i < snake.length; i++) {
                graphics.setColor(Color.GREEN);
                graphics.fillRect(
                        snake.snakeX[i] * SCALE + 2,
                        snake.snakeY[i] * SCALE + 2,
                        SCALE - 5,
                        SCALE - 5);
                //красим голову
                graphics.setColor(Color.magenta);
                graphics.fillOval(snake.snakeX[0] * SCALE + 2,
                        snake.snakeY[0] * SCALE + 2,
                        SCALE - 5,
                        SCALE - 5);

            }
        }


        //отрисовка яблока
        graphics.setColor(Color.blue);
        graphics.fillOval(apple.posX * SCALE + 2, apple.posY * SCALE + 2, SCALE - 4, SCALE - 4);
    }

    public static void main(String[] args) {
        jFrame = new JFrame("Snake Game");
        jFrame.setSize(WIDTH * SCALE + 31, HEIGHT * SCALE + 37);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(new SnakeMain());
        jFrame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        repaint();

        if (snake.snakeX[0] == apple.posX && snake.snakeY[0] == apple.posY) {
            apple.randomPosition();
            snake.length++;
        }
        for (int i = 1; i < snake.length; i++) {
            if (snake.snakeX[i] == apple.posX && snake.snakeY[i] == apple.posY) {
                apple.randomPosition();
            }


//game over
            if (snake.snakeX[0]==snake.snakeX[i]&& snake.snakeY[0]==snake.snakeY[i]){
                timer.stop();
                JOptionPane.showMessageDialog(null,"Game over!Start again?");
                jFrame.setVisible(false);
                snake.length=2;
                snake.direction=0;
                apple.randomPosition();
                jFrame.setVisible(true);
                timer.start();
            }
        }
        }

    public class KeyBoard extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            int key = event.getKeyCode();

            if (key == KeyEvent.VK_UP && snake.direction != 2) {
                snake.direction = 0;
            }
            if (key == KeyEvent.VK_DOWN && snake.direction != 0) {
                snake.direction = 2;
            }
            if (key == KeyEvent.VK_RIGHT && snake.direction != 3) {
                snake.direction = 1;
            }
            if (key == KeyEvent.VK_LEFT && snake.direction != 1) {
                snake.direction = 3;
            }
        }
    }
}
