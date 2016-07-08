package com.mygdx.game.Client;

import com.mygdx.game.Striker;
import com.sun.org.apache.regexp.internal.RE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by codecadet on 08/07/16.
 */
public class Reader {
    private final int PORT = 8080;
    private final String SERVER_ADDRESS = "127.0.0.1";

    private Striker game;

    private Socket socket;
    private BufferedReader in;

    public Reader(Striker game) {
        this.game = game;
        try {
            socket = new Socket(SERVER_ADDRESS,PORT);
            Writer writer = new Writer(socket);
            game.setWriter(writer);
            Thread t = new Thread(writer);
            t.start();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(){
        try {
            String line = in.readLine();
           if(line.equals("wait")) {
               //load watch screen
           }else{
               game.createScreen();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    private void start() {
        try {

            while(true) {
                String line = in.readLine();
                if(line != null) {
                    processLine(line);
                    //send to watch screen
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(socket != null)
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void processLine(String line) {

        String[] message = line.split(":");
        String dir = message[1];
        String str = message[2];

    }

}
