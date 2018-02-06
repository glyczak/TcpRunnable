import java.io.*;
import java.net.*;

public class Server {
    public static void main(String argv[]) throws Exception {
        ServerSocket serverSock = new ServerSocket(6789);
        int i = 0;

        while (true) {
            Socket sock = serverSock.accept();
            ConnectionHandler c = new ConnectionHandler(sock, "Handler" + i);
            i++;
        }
    }
}

class ConnectionHandler implements Runnable {
    private Socket sock;
    private Thread thread;
    private String name;

    public ConnectionHandler(Socket sock, String name) {
        this.sock = sock;
        this.name = name;
        this.start();
    }

    public void start() {
        System.out.println(name + " starting.");
        if(thread == null) {
            thread = new Thread(this, name);
            thread.start();
        }
    }

    public void run() {
        try {
            String cName;
            BufferedReader request =
            new BufferedReader(new InputStreamReader(sock.getInputStream()));
            DataOutputStream response = new DataOutputStream(sock.getOutputStream());
            do {
                cName = request.readLine();
                System.out.println(name + " received: " + cName);
                response.writeBytes("Hello " + cName + ". \n");
            } while(true);
        } catch(SocketException e) {
            System.out.println(name + " socket exception: " + e.getLocalizedMessage());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
