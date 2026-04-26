import java.io.*;
import java.net.*;

public class TokenRingNode {

    private int myPort;
    private int nextPort;
    private boolean hasToken;

    public TokenRingNode(int myPort, int nextPort, boolean hasToken) {
        this.myPort = myPort;
        this.nextPort = nextPort;
        this.hasToken = hasToken;
    }

    public void start() throws Exception {

        ServerSocket serverSocket = new ServerSocket(myPort);

        // If first node has token, initiate
        if (hasToken) {
            System.out.println("Initial token holder. Passing token...");
            sendToken();
        }

        while (true) {
            Socket socket = serverSocket.accept();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String message = in.readLine();

            if ("TOKEN".equals(message)) {
                System.out.println("Received TOKEN");

                enterCriticalSection();

                sendToken();
            }

            socket.close();
        }
    }

    private void enterCriticalSection() throws InterruptedException {
        System.out.println("Entering Critical Section...");
        Thread.sleep(2000); // simulate work
        System.out.println("Exiting Critical Section...");
    }

    private void sendToken() {
        try {
            Thread.sleep(1000);

            Socket socket = new Socket("localhost", nextPort);

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            out.println("TOKEN");
            System.out.println("Token sent to port " + nextPort);

            socket.close();
        } catch (Exception e) {
            System.out.println("Failed to send token. Retrying...");
        }
    }

    public static void main(String[] args) throws Exception {

        if (args.length != 3) {
            System.out.println("Usage: java TokenRingNode <myPort> <nextPort> <hasToken>");
            return;
        }

        int myPort = Integer.parseInt(args[0]);
        int nextPort = Integer.parseInt(args[1]);
        boolean hasToken = Boolean.parseBoolean(args[2]);

        TokenRingNode node = new TokenRingNode(myPort, nextPort, hasToken);
        node.start();
    }
}


// javac *.java
// T1: java TokenRingNode 6000 6001 true
// T2: java TokenRingNode 6001 6002 false
// T3: java TokenRingNode 6002 6000 false
