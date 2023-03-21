package socket;
import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

import java.util.Scanner;

public class TCP_client {
    public static void main(String[] args) {
        //tao socket de client ket noi den server qua dia chi IP localhost va port 6789
        try (Socket clientSocket = new Socket("127.0.0.1", 6789)) {
            //tao inputStream ket noi voi socket
            BufferedReader inputFromServer =
                    new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));
            //tao outputStream ket noi voi socket
            PrintWriter outputToServer = new PrintWriter(clientSocket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String sentence_to_server;
            String sentence_from_server;

            do {
                //tao input string tu ban phim
                System.out.print("Input: ");
                //lay chuoi ky tu do nguoi dung nhap tu ban phim
                sentence_to_server = scanner.nextLine();

                //gui chuoi ky tu toi server thong qua outputStream da ket noi voi socket
                outputToServer.println(sentence_to_server);

                if (!sentence_to_server.equals("quit")) {
                    //doc loi nhan tu server thong qua inputStream sa ket noi voi socket
                    sentence_from_server = inputFromServer.readLine();

                    // in ket qua ra man hinh
                    System.out.println("From server: " + sentence_from_server);
                }
            } while (!sentence_to_server.equals("quit"));
            //___________________________________________

            //dong lien ket socket
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
