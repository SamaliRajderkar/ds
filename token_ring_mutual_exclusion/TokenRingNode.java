import java.io.*;
import java.net.*;

public class TokenRingNode {

    public static void main(String[] args) throws Exception {

        int myPort = Integer.parseInt(args[0]);
        int nextPort = Integer.parseInt(args[1]);
        boolean hasToken = Boolean.parseBoolean(args[2]);

        ServerSocket server = new ServerSocket(myPort);

        // If this node starts with token
        if (hasToken) {
            System.out.println("Starting with TOKEN");
            sendToken(nextPort);
        }

        while (true) {
            Socket socket = server.accept();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String msg = in.readLine();

            if ("TOKEN".equals(msg)) {
                System.out.println("Received TOKEN");

                // Critical Section
                System.out.println("Entering Critical Section...");
                Thread.sleep(2000);
                System.out.println("Exiting Critical Section...");

                // Pass token
                sendToken(nextPort);
            }

            socket.close();
        }
    }

    static void sendToken(int port) throws Exception {
        Thread.sleep(1000);

        Socket socket = new Socket("localhost", port);

        PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);

        out.println("TOKEN");

        System.out.println("Token sent to " + port);

        socket.close();
    }
}

// javac *.java
// T1 : java TokenRingNode 6000 6001 true
// T2 : java TokenRingNode 6001 6000 false
