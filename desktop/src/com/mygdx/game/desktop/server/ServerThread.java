package com.mygdx.game.desktop.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by codecadet on 07/07/16.
 */
public class ServerThread{

    private Server server;
    private Socket socket;

    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMovement() {

        try {
            String line = "";
                line = in.readLine();
                if(line != null) {
                    //System.out.println(line);
                    server.sendAll(line);
                    //processLine(line);
                    //processar line
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dischargeInvalidMessage(){
        String line = " ";
        while(line != null) {
            try {
                if (in.ready()) {

                    in.readLine();

                }else {

                    line = null;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void processLine(String line) {

        //TODO  separar score e guardar no score

        //TODO mandar aos restantes clientes
    }

    public void sendMessage(String message) {

        System.out.println(message);
//        out.write(message + "\n");
//        out.flush();
    }
}
