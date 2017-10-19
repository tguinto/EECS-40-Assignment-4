package jringal.lab4;



public class GameMap {
    //creates map array
    int[][] map = new int[200][16];

    public GameMap(){

        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 200; j++){
                map[j][i] = 0;
            }
        }

    }
}
