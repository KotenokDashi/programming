package ru.cool.networking.increment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args)
    {
        send();
    }

    private static void send()
    {
        try(Socket clientSocket = new Socket("127.0.0.1", 25565);
            DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream fromServer = new DataInputStream(clientSocket.getInputStream()))
        {
            int counter = 0;
            while(counter < 10)
            {
                counter++;
                toServer.writeInt(counter);
                System.out.println("На сервер отправлено: " + counter);
                counter = fromServer.readInt();
                System.out.println("Пришло с сервера: " + counter);
            }
        }catch (IOException e)
        {
            System.err.println("Не удается подключиться к серверу!");
        }
    }



}
