import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class Client{
    public static void main(String[] args){
        try{
            Scanner sc = new Scanner(System.in);
            String url = "rmi://localhost/Server";
            ServerIntf serverintf = (ServerIntf)Naming.lookup(url);

            System.out.print("Enter Number : ");
            int a = sc.nextInt();

            System.out.print("Factorial : " + serverintf.fact(a));
        }
        catch(Exception e){
            System.out.println("Exception");
        }
    }
}
