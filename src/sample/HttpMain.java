package sample;

import java.io.IOException;

public class HttpMain {

    public static void main(String[] args) throws IOException {

        int port = 8080;

        String address = "http://localhost";

        HttpSocket httpSocket = new HttpSocket(port);

        HttpConnection httpConnection = new HttpConnection(httpSocket.getServerSocket());
        System.out.println("통신 시작");

        HttpInputDataTransaction inputDataTransaction
                = new HttpInputDataTransaction(httpConnection.getSocket());
        System.out.println("출력 부분 동작 시작 ");

        HttpOutputDataTransaction outputDataTransaction
                = new HttpOutputDataTransaction(
                        httpConnection.getSocket(), inputDataTransaction.getRequestLine(),
                address, inputDataTransaction.getBody(), port);

        inputDataTransaction.finishInput(inputDataTransaction);

        outputDataTransaction.finishOutput(outputDataTransaction);

        httpConnection.closeConnection(httpConnection.getSocket());

        httpSocket.closeSocket(httpSocket.getServerSocket());
    }
}
