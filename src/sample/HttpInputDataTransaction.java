package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.spec.ECField;

public class HttpInputDataTransaction {
    BufferedReader br = null;

    private String requestLine = "";
    private String header = "";
    private String body = "";
    private String line = "";




    public HttpInputDataTransaction(Socket socket) throws IOException {
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        requestLine = br.readLine();

        String method = requestLine.split(" ")[0];
        System.out.println(requestLine);
        System.out.println("리퀘스트 라인 분리 성공");

//        for (int i =0; i<100; i++){
//            System.out.println(br.readLine());
//        }


        try {
            seperateHeader();
            if (method.equals("POST")){
                seperateBody();
            }
            System.out.println("입력 작업 완료");
        }catch (Exception e){

        }
    }

    public void seperateHeader() throws IOException {
        System.out.println("헤더 작업 시작");
        while (!(line=br.readLine()).isBlank()){
            System.out.println(line);
            this.header += line + " ";
        }
        System.out.println("헤더 작업 성공");
    }


    public void seperateBody() throws IOException {
        System.out.println("본문 작업 시작");

//        while (!(line=br.readLine()).isBlank()){
            this.body += br.readLine();
//        }

        System.out.println("본문 작업 완료");
    }

    public void finishInput(HttpInputDataTransaction inputDataTransaction) throws IOException {
        inputDataTransaction.br.close();
    }

    public String getRequestLine() {
        return requestLine;
    }

    public String getBody(){
        return body;
    }
}
