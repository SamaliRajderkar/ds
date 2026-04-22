import StringApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

public class StringServer{
    public static void main(String[] args){
        try{
            ORB orb = ORB.init(args, null);

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            StringOpImpl obj = new StringOpImpl();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(obj);
            StringOperation href = StringOperationHelper.narrow(ref);

            org.omg.CORBA.Object obj_ref = orb.resolve_initial_references("NameService");
            NamingContextExt nc_ref = NamingContextExtHelper.narrow(obj_ref);

            nc_ref.rebind(nc_ref.to_name("StringService"),href);
            System.out.println("Server Started/Ready....");

            orb.run();
        }
        catch(Exception e){
            System.out.println("Exception Error");
        }
    }
}

// Run :

// export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)
// export PATH=$JAVA_HOME/bin:$PATH

// Idlj -fall StringOp.idl

// javac *.java  StringApp/*.java

// Terminal 1 : orbd -ORBInitialPort 1050

// Terminal 2 : java StringServer -ORBInitialPort 1050 -ORBInitialHost localhost

// Terminal 3 : java StringClient -ORBInitialPort 1050 -ORBInitialHost localhost
