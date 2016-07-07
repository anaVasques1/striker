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
        ExecutorService pool = Executors.newFixedThreadPool(MAX_PLAYERS);

        while (playersList.size() < MAX_PLAYERS) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                ServerThread playerThread = new ServerThread(clientSocket, this);

                System.out.println("list size is " + playersList.size());
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
            playersList.get(currentKey).run();

        }
    }

    public ServerThread getTurn(){
        return playersList.get(currentKey);
    }

    public void sendAll(String message) {
        for (Integer key : playersList.keySet()) {
            playersList.get(key).sendMessage(message);
        }


        if(currentKey == playersList.size()){
            currentKey = 1;
        }else{
            currentKey++;
        }
    }

}


