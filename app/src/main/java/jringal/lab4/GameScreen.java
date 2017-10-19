package jringal.lab4;

/**
 * Created by Tristan on 6/8/2017.
 */

public class GameScreen {
    //public GameMap gameMap;
    int[][] screen = new int[19][16];
    public GameScreen(GameMap gameMap){
        for (int i = 0; i <= 15  ; ++i){
            for(int j = 0; j <= 18; ++j){
                screen[j][i] = 0;
            }
        }

        for (int i = 0; i <= 15  ; ++i){
            for(int j = 18; j >= 0; --j){
                screen[j][i] = gameMap.map[199-j][i];
            }
        }

    }
    public void PrintGameSCreen(){
        for (int i = 0; i <= 15  ; ++i){
            for(int j = 0; j <= 18; ++j){
                System.out.print(" " + screen[j][i] + " ");
            }
            System.out.print("\n");
        }
    }
}
