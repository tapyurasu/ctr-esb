package main;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import ESB.CTR;
import ESB.CTRHelper;
import main.impl.CTRImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        try{
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);
			
			// get reference to rootpoa and activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			
			// create servant and register it with the ORB
			CTRImpl helloImpl = new CTRImpl();
			helloImpl.setORB(orb); 
			
			// get object reference from the servant
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
			CTR href = CTRHelper.narrow(ref);
			    
			// get the root naming context
			org.omg.CORBA.Object objRef =
			    orb.resolve_initial_references("NameService");
			// Use NamingContextExt which is part of the Interoperable
			// Naming Service (INS) specification.
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			// bind the Object Reference in Naming
			String name = "Hello";
			NameComponent path[] = ncRef.to_name( name );
			ncRef.rebind(path, href);
			
			System.out.println("HelloServer ready and waiting ...");
			
			// wait for invocations from clients
			orb.run();
          } catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
          }
        System.out.println("HelloServer Exiting ...");
    }
}

