import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class Client{
    public static void main(String[] args){
        try{
            Scanner sc = new Scanner(System.in);
            String url = "rmi://localhost/Server";
            ServerIntf obj = (ServerIntf)Naming.lookup(url);

            System.out.print("Enter str : ");
            String str = sc.nextLine();

            System.out.print("Vowel Count : " + obj.count(str));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
