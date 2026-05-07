import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TokenRingNode {

    public static void main(String[] args) throws Exception {
        int myPort   = Integer.parseInt(args[0]);
        int nextPort = Integer.parseInt(args[1]);
        boolean hasToken = Boolean.parseBoolean(args[2]);

        ServerSocket server = new ServerSocket(myPort);
        if (hasToken) sendToken(nextPort);

        while (true) {
            Socket s = server.accept();
            String msg = new Scanner(s.getInputStream()).nextLine(); // ✅
            s.close();

            if ("TOKEN".equals(msg)) {
                System.out.println("Got token → doing work...");
                Thread.sleep(2000);
                System.out.println("Done. Passing token.");
                sendToken(nextPort);
            }
        }
    }

    static void sendToken(int port) throws Exception {
        Thread.sleep(1000);
        Socket s = new Socket("localhost", port);
        s.getOutputStream().write("TOKEN\n".getBytes()); // ✅
        s.close();
        System.out.println("Token sent to port " + port);
    }
}

// javac *.java
// T1 : java TokenRingNode 6000 6001 true
// T2 : java TokenRingNode 6001 6000 false
