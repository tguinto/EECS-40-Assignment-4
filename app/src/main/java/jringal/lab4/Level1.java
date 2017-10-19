package jringal.lab4;

/**
 * Created by Tristan on 6/7/2017.
 */

public class Level1 extends GameMap {


    public Level1(){
        for(int i = 0; i < 200; i++){
            for(int j = 0; j < 10; j++){
                map[j][i] = 1;
            }
        }
    }
}
