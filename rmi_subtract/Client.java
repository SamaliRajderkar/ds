import java.rmi.*;
import java.util.*;

public class Client{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try{
            String url="rmi://localhost/Server";
            ServerIntf serverintf = (ServerIntf)Naming.lookup(url);

            System.out.print("Enter num1 : ");
            int a = sc.nextInt();

            System.out.print("Enter num2 : ");
            int b = sc.nextInt();

            System.out.print("Subtraction : " + serverintf.sub(a,b));

        }
        catch (Exception e)
        {
            System.out.println("Exception");
        }
    }
}