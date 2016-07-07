package com.mygdx.game.desktop.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by codecadet on 07/07/16.
 */
public class ServerThread implements Runnable{

    private Server server;
    private Socket socket;

    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }


    @Override
    public void run() {


        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            String line = "";


                while (true) {
                    if (server.getTurn().equals(this)) {
                        System.out.println("teste A");
                        line = in.readLine();
                        System.out.println("teste B");
                        if (line != null) {
                            server.sendAll(line);
                            //processLine(line);
                            //processar line
                        }

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
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
