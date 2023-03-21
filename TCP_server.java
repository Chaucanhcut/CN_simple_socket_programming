package socket;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.lang.String;
import java.lang.StringBuilder;


public class TCP_server {
    public static void  main(String[] args) {
        //tao socket server, cho tai cong 6789
        try(ServerSocket welcomeSocket = new ServerSocket(6789)) {
            // cho yeu cau tu client tai welcomeSocket
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Client connected!");

            //tao input stream va ket noi toi socket
            BufferedReader inputFromClient =
                    new BufferedReader(
                            new InputStreamReader(connectionSocket.getInputStream()));
            //tao output stream va ket noi toi socket
            PrintWriter outputToClient = new PrintWriter(connectionSocket.getOutputStream(), true);

            while (true) {
                //doc thong tin tu socket
                String sentence_from_client = inputFromClient.readLine();
                System.out.println("Input from client: " + sentence_from_client);
                //quit the loop when input = quit
                if (sentence_from_client.equals("quit")) {
                    break;
                }
                
                //ghi du lieu da chuan hoa ra socket
                outputToClient.println("Normalized sentence: " + sentence_from_client);
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
