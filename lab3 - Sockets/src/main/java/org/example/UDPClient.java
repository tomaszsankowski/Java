package org.example;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket()) {

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 4160;

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter your message: ");
            String message = scanner.nextLine();
            byte[] sendBuffer = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            socket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
