package com.lozhnikov.receiver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Receiver implements Runnable {

    ServerSocket socket;
    List<Socket> clientsSockets;

    public Receiver(int port) throws IOException {
        socket = new ServerSocket(port, 0, InetAddress.getLocalHost());
        clientsSockets = new ArrayList<>();
        System.out.println("Server started. Address: " + socket.getInetAddress().getHostAddress());
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Socket newSenderSocket = socket.accept();
                clientsSockets.add(newSenderSocket);
                Thread newSenderThread = new Thread(new SenderThread(newSenderSocket));
                newSenderThread.start();
                System.out.println("New sender. Address: " + newSenderSocket.getInetAddress().getHostAddress());
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
