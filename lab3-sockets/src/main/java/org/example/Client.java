package org.example;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket s = null;

        try {
            s = new Socket("localhost", 4999);

            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            Scanner scanner = new Scanner(System.in);

            String response = (String) in.readObject();
            System.out.println("server: " + response);

            response = (String) in.readObject();
            System.out.println("server: " + response);

            String username = scanner.nextLine();
            out.writeObject(username);
            out.flush();

            response = (String) in.readObject();
            System.out.println("server: " + response);

            //int number = scanner.nextInt();
            int number = (int) Math.floor(10*Math.random());
            //scanner.nextLine();
            out.writeObject(number);
            out.flush();

            response = (String) in.readObject();
            System.out.println("server: " + response);

            for(int i = 0; i < number; i++){
                //System.out.println("Enter message content: ");
                //String content = scanner.nextLine();
                String content = String.valueOf((int) Math.floor(100*Math.random()));
                Message m = new Message(i, content, username);
                out.writeObject(m);
                out.flush();
            }

            response = (String) in.readObject();
            System.out.println("server: " + response);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (s != null)
                s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
