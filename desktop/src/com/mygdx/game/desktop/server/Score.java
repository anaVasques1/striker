package com.mygdx.game.desktop.server;

import java.util.HashMap;

/**
 * Created by codecadet on 07/07/16.
 */
public class Score {
    private HashMap<Integer, Integer> scores = new HashMap<Integer, Integer>();
    public Score(int maxPlayers){
        for(int i = 1; i <= maxPlayers; i++) {
           scores.put(i,0);
        }
    }

    public void update(int player, int score){
        scores.put(player, scores.get(player)+score);
    }

    public void checkWinner(){

    }

}
