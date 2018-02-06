import java.io.*;
import java.net.*;

class Client {
    public static void main(String argv[]) {
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            Socket sock = new Socket("localhost", 6789);
            DataOutputStream request = new DataOutputStream(sock.getOutputStream());
            BufferedReader response = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            do {
                System.out.println("What is your name?");
                request.writeBytes(userInput.readLine() + '\n');
                String message = response.readLine();
                System.out.println(message);
            } while(true);
        } catch(SocketException e) {
            System.out.println("Socket exception: " + e.getLocalizedMessage());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
