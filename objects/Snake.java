package objects;

import game.SnakeMain;

public class Snake {
    //длина змейки на старте игры равна 2
    public int length = 2;
    //вверз = 0 вниз =2 вправо = 1 влево = 3
    public int direction = 0;
    public int[] snakeX = new int[300];
    public int[] snakeY = new int[300];

    public Snake(int x1, int y1, int x2, int y2) {
        snakeX[0] = x1; //координаті голові по оси Х
        snakeX[1] = x2;//координаті хвоста по оси х
        snakeY[0] = y1;//координаті голові по оси y
        snakeY[1] = y2;//координаті хвоста по оси y
    }

    public void move() {

        for (int i = length; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }


        //up
        if (direction == 0) {
            snakeY[0]--;
        }
        //down
        if (direction == 2) {
            snakeY[0]++;
        }
        //right
        if (direction == 1) {
            snakeX[0]++;
        }
        //left
        if (direction == 3) {
            snakeX[0]--;
        }
        //для направления 0
        if (snakeY[0] > SnakeMain.HEIGHT - 1) {
            snakeY[0]=0;

        }
        //для направления 2
        if (snakeY[0]<0){
            snakeY[0]=SnakeMain.HEIGHT-1;

        }
        //для напраления 3
    if (snakeX[0]>SnakeMain.WIDTH-1){
        snakeX[0]=0;
    }
//для направления 4
        if(snakeX[0]<0){
            snakeX[0]= SnakeMain.WIDTH-1;
        }
        }
    }




