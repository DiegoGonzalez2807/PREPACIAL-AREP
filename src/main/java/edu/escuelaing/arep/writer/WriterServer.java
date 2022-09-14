package edu.escuelaing.arep.writer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriterServer {

    public static String HTTP_HEADER = "HTTP/1.1 200 OK\r\n"
            + "Content-Type: text/html\r\n"
            + "\r\n";

    public static String JSON_HEADER = "HTTP/1.1 200 OK\r\n"
            + "Content-Type: application/json\r\n"
            + "\r\n";

    public static String ERROR_HEADER = "HTTP/1.1 404 Bad/Request\r\n"
            + "Content-Type: text/html\r\n"
            + "\r\n";

    public static void getForm(Socket clientSocket) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String output = HTTP_HEADER + new String(Files.readAllBytes(Paths.get("resources/public/index.html")));
        out.println(output);
        out.close();
    }

    public static void writeJSON(Socket clientSocket, StringBuffer buffer) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String output = HTTP_HEADER +buffer ;
        out.println(output);
        out.close();
    }
}
