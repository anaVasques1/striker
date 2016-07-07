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
    private HashMap<Integer, ServerThread> playersList;
    private ServerSocket serverSocket = null;

    public Server(){
        portNumber = 8080;
        try {
            serverSocket = new ServerSocket(portNumber);

        } catch (IOException e) {
            System.out.println("Could not open serverSocket");
        }

    }

    public void init(){
        ExecutorService pool = Executors.newFixedThreadPool(2);
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
                ServerThread playerThread = new ServerThread(clientSocket, this);
                Thread t = new Thread(playerThread);
                pool.submit(t);
                playersList.put(playersList.size()+1,playerThread);
                

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }



    public void sendAll(){

    }



}
