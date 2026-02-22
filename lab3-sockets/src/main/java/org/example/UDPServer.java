package org.example;

import java.io.*;
import java.net.*;

public class UDPServer {
    static boolean endFlag = false;

    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket(4160)) {
            System.out.println("Server is running ...");

            while (true) {
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Received from client (" + clientAddress + ":" + clientPort + "): " + message);

                String response = "Hello, client! You said: " + message;
                byte[] sendBuffer = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
