package edu.escuelaing.arep.HttpServer;

import edu.escuelaing.arep.connection.HttpConnection;
import edu.escuelaing.arep.writer.WriterServer;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        //NUEVO CODIGO -> LOOP PARA QUE SIEMPRE ESTE RECIBIENDO PETICIONES
        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            WriterServer writer = null;
            String firstLine = "";
            try {
                writer = new WriterServer();
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, path = "";
            //NUEVO CODIGO -> PONEMOS UN BOOLEANO PARA REVISAR LA PRIMERA LINEA
            //CUANDO LA CONSEGUIMOS LE HACEMOS SPLIT PARA CONSEGUIR EL RECURSO
            // EJEMPLO -> GET / index.html / 200 OK
            //RETORNA /index.html. ESTE RECURSO SE MANDA AL WRITER
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibí: " + inputLine);
                if(inputLine.contains("GET")){
                    firstLine = inputLine;
                    reviewFirstLine(firstLine,clientSocket,writer);
                }
                if (!in.ready()) {
                    break;
                }
            }
            if(firstLine.split(" ")[1].equals("/")){
                writer.getForm(clientSocket);
            }
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    public static void reviewFirstLine(String firstLine, Socket clientSocket,WriterServer writer) throws IOException {
        HttpConnection connection = new HttpConnection();
        System.out.println("ESTA ES LA PRIMER LINEA "+firstLine);
        String resource = firstLine.split(" ")[1];
        if(resource.contains("/consulta")){
            StringBuffer json_proof = connection.getData(resource.split("=")[1]);
            System.out.println("ESTA ES LA CIUDAD "+resource.split("=")[1]);
            System.out.println("STRINGBUFFER "+json_proof);
            writer.writeJSON(clientSocket,json_proof);

        }
    }
}
