package socket;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.lang.String;
import java.lang.StringBuilder;


public class TCP_server {
    public void normalizeString(String str)
    {
    }

    public static void main(String argv[]) throws Exception
    {
        String sentence_from_client;
        StringBuilder sentence_to_client;
        String normalized_sentence;

        //tao socket server, cho tai cong 3456
        ServerSocket welcomeSocket = new ServerSocket(6789);

        //main loop
        while (true) {
            // cho yeu cau tu client tai welcomeSocket
            Socket connectionSocket = welcomeSocket.accept();

            //tao input stream va ket noi toi socket
            BufferedReader inputFromClient =
                    new BufferedReader(
                            new InputStreamReader(connectionSocket.getInputStream()));

            //tao output stream va ket noi toi socket
            DataOutputStream outputToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            //doc thong tin tu socket
            sentence_from_client = inputFromClient.readLine();

            //normalization____________
            normalized_sentence = sentence_from_client.toLowerCase() + '\n';
            String firstLetter = normalized_sentence.substring(0, 1);
            String remainingLetters = normalized_sentence.substring(1, normalized_sentence.length());

            // change the first letter to uppercase
            firstLetter = firstLetter.toUpperCase();

            // join the two substrings
            normalized_sentence = firstLetter + remainingLetters;
            //__________________________

            //chuoi ket qua
            sentence_to_client = new StringBuilder(
                    sentence_from_client + " (Server accepted!). Normalized: " + normalized_sentence);

            //ghi du lieu da chuan hoa ra socket
            outputToClient.writeBytes(sentence_to_client.toString());
            return;
        }
    }
}
