package objects;

import game.SnakeMain;

public class Apple {
    public int posX;
    public int posY;
    public Apple (int x ,int y){
        this.posX=x;
        this.posY=y;
    }
    //радномное размещение яблока в пределах нашего поля
    public void randomPosition(){
        //по высоте рандомно
        posX=(int)(Math.random()* SnakeMain.HEIGHT-1);
        //по ширине рандомно
        posY=(int)(Math.random()* SnakeMain.WIDTH-1);
    }

}
