/*
Problem Statement :
Perform Leader Election using Token Ring ALgorithm.
Also print the messages exchanged between the processes.
*/


import java.util.*;

public class token_ring{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of leaders :");
        int n= sc.nextInt();

        System.out.println("Ring formed :");
        for(int i=0;i<n;i++){
            System.out.print(i+ " ");
        }
        System.out.println("0");

        System.out.print("Enter Leader who wants to send message :");
        int sender = sc.nextInt();

        System.out.print("Enter Leader who will be receiver :");
        int receiver = sc.nextInt();

        System.out.print("Enter message :");
        sc.nextLine();
        String message = sc.nextLine();

        System.out.print(" ");
        System.out.println("Token Ring Algorithm :");
        System.out.print(" ");

        int token = 0;

        System.out.print("Token passing: ");
        int j = token;
        while (j != sender) {
            System.out.print(j + " -> ");
            j = (j + 1) % n;
        }
        System.out.println(sender);

        System.out.println("Leader " + sender + " sent the message : " + message);

        for(int i=sender; i!=receiver; i =(i+1) % n){
            System.out.println("Message " + message + " forwarded by Leader "+ i);
        }

        System.out.println("Leader " + receiver + " received the message : " + message);

        token = sender ;

        System.out.println("Updated token : "+ token);
    }
}