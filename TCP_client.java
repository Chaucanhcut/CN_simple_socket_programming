package socket;
import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

public class TCP_client {
    public static void main(String argv[]) throws Exception
    {
        String sentence_to_server;
        String sentence_from_server;

        //tao input string tu ban phim
        System.out.print("Input: ");
        BufferedReader inputFromClient =
                new BufferedReader(new InputStreamReader(System.in));
        //lay chuoi ky tu do nguoi dung nhap tu ban phim
        sentence_to_server = inputFromClient.readLine();

        //tao socket de client ket noi den server qua dia chi IP va port 3456
        Socket clientSocket = new Socket("127.0.0.1", 6789);

        //tao outputStream ket noi voi socket
        DataOutputStream outputToServer =
                new DataOutputStream(clientSocket.getOutputStream());

        //tao inputStream ket noi voi socket
        BufferedReader inputFromServer =
                new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

        //gui chuoi ky tu toi server thong qua outputStream da ket noi voi socket
        outputToServer.writeBytes(sentence_to_server + '\n');

        //doc loi nhan tu server thong qua inputStream sa ket noi voi socket
        sentence_from_server = inputFromServer.readLine();

        // in ket qua ra man hinh
        System.out.println("From server: " + sentence_from_server);

        //dong lien ket socket
        clientSocket.close();
    }
}
