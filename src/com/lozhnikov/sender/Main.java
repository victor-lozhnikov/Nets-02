package com.lozhnikov.sender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;

public class Main {

    public static void main(String[] args) {
        String fileName = args[0];
        String address = args[1];
        int port = Integer.parseInt(args[2]);

        try {
            InetAddress inetAddress = InetAddress.getByName(address);
            File file = new File(fileName);
            if (file.exists()) {
                Thread sender_thread = new Thread(new Sender(inetAddress, port, file));
                sender_thread.start();
            }
            else throw new FileNotFoundException();
        }
        catch (IOException ex) {
            System.out.println("Can't connect to server");
        }
    }
}
