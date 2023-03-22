package socket;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.lang.*;


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

                 String normalized_sentence;
                 //normalization____________
                 normalized_sentence = sentence_from_client.toLowerCase() + '\n';

                 String firstLetter = normalized_sentence.substring(0, 1);
                 String remainingLetters = normalized_sentence.substring(1, normalized_sentence.length());
                 // change the first letter to uppercase
                 firstLetter = firstLetter.toUpperCase();
                 // join the two substrings
                 normalized_sentence = firstLetter + remainingLetters;
                 //__________________________

                //ghi du lieu da chuan hoa ra socket
                //replaceAll("\\s+", " ") : loai bo dau cach thua
                outputToClient.println("Normalized sentence: " + normalized_sentence.replaceAll("\\s+", " "));
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
