import org.omg.CORBA.*;
import StringApp.*;
import java.util.*;
import org.omg.CosNaming.*;

public class StringClient{
    public static void main(String[] args){
        try{
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object obj_ref = orb.resolve_initial_references("NameService");
            NamingContextExt nc_ref = NamingContextExtHelper.narrow(obj_ref);

            StringOperation obj = StringOperationHelper.narrow(nc_ref.resolve_str("StringServer"));

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter String : ");
            String s = sc.nextLine();

            System.out.println("Output : "+ obj.process(s));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}