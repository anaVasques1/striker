package com.mygdx.game.desktop.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by codecadet on 07/07/16.
 */
public class Server {
        private int portNumber;
        private final int MAX_PLAYERS = 2;
        private HashMap<Integer, ServerThread> playersList;
        private ServerSocket serverSocket = null;
        private final int TURNS = 2;
    private Score score;

    private Integer currentKey = 1;

        public Server() {
            portNumber = 8080;
            playersList = new HashMap<Integer, ServerThread>();
            try {
                serverSocket = new ServerSocket(portNumber);

            } catch (IOException e) {
                System.out.println("Could not open serverSocket");
            }

        }

    public void init() {

        while (playersList.size() < MAX_PLAYERS) {
            System.out.println(playersList.size());
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                ServerThread playerThread = new ServerThread(clientSocket, this);

                playersList.put(playersList.size() + 1, playerThread);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        playersList.get(1).sendMessage("play");
        playersList.get(2).sendMessage("wait");
        start();
    }

    public void start() {
        score = new Score(MAX_PLAYERS);
        int moves = 0;
        while (moves < TURNS * MAX_PLAYERS) {
            System.out.println("jodadas "+moves);
            ServerThread player = playersList.get(currentKey);
            player.getMovement();
            moves++;
        }
        playersList.get(1).sendMessage("end");
        playersList.get(2).sendMessage("end");

        scores();
    }


    private void scores() {
        int winner = score.checkWinner();

        if(winner == 1) {
            playersList.get(1).sendMessage("winner");
            playersList.get(2).sendMessage("looser");
        } else if(winner == 2){
            playersList.get(2).sendMessage("winner");
            playersList.get(1).sendMessage("looser");
        } else {
            playersList.get(2).sendMessage("tie");
            playersList.get(1).sendMessage("tie");
        }
    }

    public void updateCurrentKey(){
        if(currentKey == playersList.size()){
            System.out.println("changed to player 1");
            currentKey = 1;
        }else {
            System.out.println("changed to player 2");
            currentKey++;
        }
        playersList.get(currentKey).dischargeInvalidMessage();
    }


    public void sendAll(String message) {
        for (Integer key : playersList.keySet()) {
            if(key != currentKey) {
                playersList.get(key).sendMessage(message);
            }
        }
        updateCurrentKey();

    }

    public void updateScore(int score){
        this.score.update(currentKey,score);
    }


}


