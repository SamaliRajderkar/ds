import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

public class Client{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try{

            String url = "rmi://localhost/Server";
            ServerIntf serverintf = (ServerIntf)Naming.lookup(url);

            System.out.print("Enter name : ");
            String name = sc.nextLine();

            System.out.println(" Output : "+ serverintf.hello(name));
        }
        catch(Exception e){
            System.out.println("Exception");
        }
    }
}
