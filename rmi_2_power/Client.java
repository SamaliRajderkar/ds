import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class Client{
   
        

        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            try{
                String url = "rmi://localhost/Server";
                ServerIntf serverintf = (ServerIntf)Naming.lookup(url);

                System.out.println("Enter power : ");
                int a = sc.nextInt();

                
                System.out.println("Power : " + serverintf.power( a ));
         
            }
            catch(Exception e){
                System.out.println("Exception");
            }
        }
    
}
