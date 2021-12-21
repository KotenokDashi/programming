package ru.cool.networking.increment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args)
    {
        reply();
    }

    private static void reply()
    {
        try {
            ServerSocket server = new ServerSocket(25565);
            System.out.println("Ожидание подключения клиента");
            try(Socket serverSocket = server.accept();
                DataOutputStream toClient = new DataOutputStream(serverSocket.getOutputStream());
                DataInputStream fromClient = new DataInputStream(serverSocket.getInputStream()))
            {
                int sendingNumber;
                do {
                    sendingNumber = fromClient.readInt();
                    System.out.println("Пришло с клиента: " + sendingNumber);
                    sendingNumber++;
                    toClient.writeInt(sendingNumber);
                    System.out.println("Отправлено на клиент: " + sendingNumber);
                }while(sendingNumber != 10);

            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }



}
