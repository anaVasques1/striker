package com.mygdx.game.Client;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by codecadet on 08/07/16.
 */
public class Writer implements Runnable{

    private PrintWriter out;
    private Socket socket;
    public Writer(Socket socket) {
       this.socket = socket;
    }


    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendMessage(String result) {
        out.write(result+"\n");
        out.flush();
    }
}
