import StringApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import java.util.*;

public class StringClient{
    public static void main(String[] args){
        try{
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object obj_ref = orb.resolve_initial_references("NameService");
            NamingContextExt nc_ref = NamingContextExtHelper.narrow(obj_ref);

            StringOperation obj = StringOperationHelper.narrow(nc_ref.resolve_str("StringService"));

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter string : ");
            String s = sc.nextLine();

            System.out.println("Output : " + obj.process(s));
        }
        catch(Exception e){
            System.out.println("Exception Error");
        }
    }
}
