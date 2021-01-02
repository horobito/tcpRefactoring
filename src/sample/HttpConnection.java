package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpConnection {

    Socket socket = null;


    public HttpConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("통신 준비 완료");
        this.socket = serverSocket.accept();

    }

    public Socket getSocket() {
        return socket;
    }

    public void closeConnection(Socket socket) throws IOException {
        this.socket.close();

    }
}
