package org.example;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server {
    static boolean endFlag = false;
    public static void main(String[] args){
        ServerSocket ss = null;

        try {
            ss = new ServerSocket(4999);
            System.out.println("Server is running ...");

            CommandLine commandLine = new CommandLine();
            commandLine.start();
            while(!endFlag){
                Socket s = ss.accept();
                System.out.println("Client connected");

                new ServerThread(s).start();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try{
            if(ss != null)
                ss.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    private static class CommandLine extends Thread{
        public void run(){
            Scanner scanner = new Scanner(System.in);
            String c = scanner.next();
            while(!c.equals("exit"))
            {
                c = scanner.next();
            }
            endFlag = true;
        }
    }

    private static class ServerThread extends Thread{
        private final Socket s;

        public ServerThread(Socket s){
            this.s = s;
        }

        public void run(){
            try(
                    ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                    ){
                out.writeObject("connected");
                out.flush();

                out.writeObject("Enter your username: ");
                out.flush();
                String username = (String) in.readObject();
                out.writeObject("Hello, " + username + ". Enter number of messages to send: ");
                out.flush();
                int number = (int) in.readObject();

                out.writeObject("ready for messages");
                out.flush();
                for(int i = 0;i<number;i++){
                    Message message = (Message) in.readObject();
                    System.out.println(username + ": " + message.getContent());
                }

                out.writeObject("finished");
                out.flush();
            }
            catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
            try{
                if(s != null)
                    s.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
