import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class Client{
    public static void main(String[] args){
        try{
            Scanner sc = new Scanner(System.in);
            String url = "rmi://localhost/Server";
            ServerIntf serverintf = (ServerIntf)Naming.lookup(url);


            System.out.print("Enter Str1: ");
            String s1 = sc.nextLine();

            System.out.print("Enter Str2: ");
            String s2 = sc.nextLine();

            System.out.print("Lexographically Largest String : " + serverintf.compare(s1, s2));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
