package com.mygdx.game.desktop.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by codecadet on 07/07/16.
 */
public class Server {
    private int portNumber;
    private HashMap<String, ServerThread> playersList;
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

        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread playerThread = new Thread(new ServerThread(clientSocket, this));
        }

    }



    public void sendAll(){

    }




}
