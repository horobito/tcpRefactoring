package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class HttpSocket {

    private ServerSocket serverSocket = null;


    public HttpSocket( int port) throws IOException {
        this.serverSocket = new ServerSocket(port); // bind
        System.out.println("ServerSocket 생성");
    }

    public ServerSocket getServerSocket() {
        System.out.println("ServerSocket 반환");
        return serverSocket;
    }

    public void closeSocket(ServerSocket serverSocket) throws IOException {
        serverSocket.close();
        System.out.println("ServerSocket 종료");
    }


}
