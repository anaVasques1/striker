package com.mygdx.game.Client;

import com.mygdx.game.Striker;
import com.mygdx.game.screens.PlayScreen;
import com.sun.org.apache.regexp.internal.RE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by codecadet on 08/07/16.
 */
public class Reader implements Runnable{
    private final int PORT = 8080;
    private final String SERVER_ADDRESS = "192.168.1.19";

    private Striker game;

    private Socket socket;
    private BufferedReader in;

    public Reader(Striker game) {
        this.game = game;
        try {
            socket = new Socket(SERVER_ADDRESS,PORT);
            Writer writer = new Writer(socket);
            game.setWriter(writer);
//            Thread t = new Thread(writer);
//            t.start();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    public void init(){
//        try {
//            System.out.println("esperando msg");
//            String line = in.readLine();
//            System.out.println("recebeu msg");
//            System.out.println(line);
//           if(line.equals("wait")) {
//               game.createWatchingScreen();
//           }else{
//               System.out.println("entrou no load screen");
//               game.createScreen();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        start();
//    }

    private void start() {
        try {

            while(true) {
                System.out.println("esperando a msg");
                String line = in.readLine();
                System.out.println("received msg from server " + line);
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

        game.setNextScreen("ShowPlay");
        game.move(Integer.parseInt(message[0]),Float.parseFloat(dir),Float.parseFloat(str));
    }

    @Override
    public void run() {
        try {
            System.out.println("esperando msg");
            String line = in.readLine();
            System.out.println("recebeu msg");
            System.out.println(line);
            if(line.equals("wait")) {
                game.setNextScreen("WatchingScreen");
            }else{
                System.out.println("entrou no load screen");
                game.setNextScreen("PlayScreen");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }
}
