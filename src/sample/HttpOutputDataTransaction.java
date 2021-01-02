package sample;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.security.spec.ECField;
import java.util.HashMap;


public class HttpOutputDataTransaction {

    BufferedWriter bw = null;

    String method = "";
    String protocol = "";
    String address = "";
    int port = 0;
    String url = "";
    String body = "";
    HashMap<String, Integer> query = new HashMap<>();
    String file = "";
    String queries = "";

    public HttpOutputDataTransaction(Socket socket,  String requestLine, String address, String body, int port) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String[] parsedLine = requestLine.split(" ");
        this.method = parsedLine[0];
        this.url = parsedLine[1];
        this.protocol = parsedLine[2];
        this.address = address;
        this.port = port;
        this.body = body;
        processOutput(method, url, body);
        bw.close();
    }

    public void processOutput(String method, String url, String body) throws IOException {
        URL tempUrl = new URL(address + port + url);
        file = tempUrl.getPath();
        System.out.println(file);
        queries = tempUrl.getQuery();


        try {

            if (method.equals("POST")){
                processPOST(file, body);
            }

            if(method.equals("GET")){
                if (url.length()>1){
                    processGET(file, queries);
                }else {
                    bw.write("HTTP/1.1 200 OK\r\n" +
                            "Content-Type: text/html\r\n\r\n" +
                            "black coffee!\r\n\r\n");
                    bw.flush();
                }

            }

        }catch (Exception e){}
    }

    public void processPOST(String file, String body ) throws IOException {
        String[] temp = body.split("&");
        int answer = 0;
        if (file.equals("/calculator")){
            for (String a : temp){
                int value = Integer.parseInt(a.split("=")[1]);
                answer+=value;

            }
            bw.write("HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n\r\n" +

                    body + "의 답은 " + answer + " 입니다."+ "\r\n\r\n");
            bw.flush();
        }


    }

    public void processGET(String file, String queries) throws IOException {

        String answer = "";

        if (file.equals("/profile")){
            String[] temp = queries.split("&");
            for (String a : temp){
                answer = a.split("=")[1];
            }
            bw.write("HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n\r\n" +

                     answer + "님 안녕하세요"+ "\r\n\r\n");
            bw.flush();
        }
    }

    public void finishOutput(HttpOutputDataTransaction httpOutputDataTransaction) throws IOException {
        httpOutputDataTransaction.bw.close();
    }


}



