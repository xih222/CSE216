/**
 * <p>
 * Title: P2PProject</p>
 *
 * <p>
 * Description: This is main for the program. 
 * It creates the server as a separate runnable 
 * and executes it.
 * </p>
 *
 * <p>
 * Copyright: none</p>
 *
 * <p>
 * Company: Lehigh University</p>
 *
 * @author Bill Phillips
 *
 */

public class P2PProject 
{

   public static void main(String[] args) 
   {

      final int BASEPORT =                  11212;
      final boolean AUTH =                   false;
      
      RunTimeVars rtv =    RunTimeVars.Instance();
      ConfigFile cf   =     ConfigFile.Instance();
      
      rtv.setServerPort                (BASEPORT);
      


      //
      // Anything on the command line means
      // display output windows.
      //
      rtv.setIsGUIInterface(args.length > 0);

      //
      // Set the System ID.
      //
      String SID = cf.getSysID();
      boolean isacs
              = (SID.equalsIgnoreCase("ACCESS"));
      rtv.setACS(isacs);
      boolean istmg
              = (SID.equalsIgnoreCase("TSTMSG"));
      rtv.setTMG(istmg);
      boolean iscse
              = (SID.equalsIgnoreCase("CSE216"));
      rtv.setCSE(iscse);
      
      //
      // Erase the current client
      // directory.
      // and server directory 
      // if not ACS.
      //
      new       FileIOServices(true).CleanFiles();
      if (!isacs)
           new FileIOServices(false).CleanFiles();    
      
      //
      // ACS does not authorize at this time.
      //
      boolean authorize =          AUTH && !isacs;
      rtv.setAUTH                     (authorize);

      //
      // Create the 
      // builder and
      // build the subsystem.
      //
      BuilderFacade.Instance();

      P2PServer psm
                  = new P2PServer(BASEPORT, null);

       //
      // Start the server.
      //
      psm.start();

   }

}
