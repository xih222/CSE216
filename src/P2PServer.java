import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * <p>
 * Title: P2PProject</p>
 *
 * <p>
 * Description: This is the server for the Project.
 * </p>
 *
 * <p>Copyright: none</p>
 *
 * <p>Company: Lehigh University</p>
 *
 * @author Bill Phillips 
 *
 */
public class P2PServer implements Runnable 
{
   private RunTimeVars rtv = 
                           RunTimeVars.Instance();
   private ConfigFile cf =  ConfigFile.Instance();
   private ServerSocket server;
   private Socket socket;
   private final ServerTransactionLogger stl;
   private ServerServices smservices;
   private ExecutorService Sservice;

   private static final int NUMTHREADS = 40;
   private int port;

   public P2PServer(int p, Socket s) 
   {
      port = p;

      if (rtv.isACS()) port = cf.getACSPORT();
      
      socket = s;
      stl = ServerTransactionLogger.Instance();
      stl.writeToLogger("Server Created. Port: "
                     + String.format("%d", port));
   }

   public void start() 
   {
      
      Sservice = Executors.newFixedThreadPool
                                     (NUMTHREADS);

      stl.writeToLogger        ("Server Started");
      try {
         server = new ServerSocket
                               (port, NUMTHREADS);
      } catch (Exception e) {
         stl.writeToLogger
                 ("Error creating server socket");
         System.exit(-1);
      }

      do 
      {
         //
         // Connect to the client.
         //
         try 
         {
            stl.writeToLogger("Connecting -- " + 
                    "waiting for client on port ",
                    port);

            socket = server.accept();
            stl.writeToLogger("Accepted client " +
                    "on port ", port);

         } 
         catch (Exception e) 
         {
            stl.writeToLogger("Error connecting "+ 
                    "to client");
            System.exit(-1);
         }

         //
         // Create a server thread to process
         // the request.
         //
         Sservice.execute(new P2PServer
                                  (port, socket));
         
         
      } while (true);

   }

   @Override
   public void run() 
   {

      stl.writeToLogger("Server thread started.");
      smservices =     new ServerServices(socket);

      smservices.getStreams();

      //
      // Process the client command.
      //
 
      BuilderFacade bf = BuilderFacade.Instance();
      ProcessingFacade pf = 
                         bf.getProcessingFacade();
      pf.processMsg(smservices);

      smservices.Disconnect();

   }

}
