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
        //ExecutorService pool = Executors.newFixedThreadPool(MAX_PLAYERS);

        while (playersList.size() < MAX_PLAYERS) {
            System.out.println(playersList.size());
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                ServerThread playerThread = new ServerThread(clientSocket, this);
               // Thread t = new Thread(playerThread);
               // pool.submit(t);
                playersList.put(playersList.size() + 1, playerThread);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        start();
    }

    public void start() {
        //TODO send start message to players
        while (true) {
            ServerThread player = playersList.get(currentKey);
            player.getMovement();
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
            playersList.get(key).sendMessage(message + "the player is number " + currentKey);
        }
        updateCurrentKey();

    }

}


