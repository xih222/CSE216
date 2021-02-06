import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <
 * p>
 * Title: P2P Project</p>
 *
 * <p>
 * Description: This class handles 
 * processing of the message
 * sent to the CSE216 Subsystem.
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
public class CSE216Facade extends 
                              ProcessingFacadeBase 
{

   private final ServerTransactionLogger stl;

   CSE216Facade() 
   {
      //
      // Instantiate the client in the
      // constructor.
      //
      stl = ServerTransactionLogger.Instance();

      //
      // Only one thread needed for the client.
      //
      ExecutorService CandS
              = Executors.newFixedThreadPool(1);

      //
      // Create the client.
      //
      CSE216Client pcm = new CSE216Client               
               (ConfigFile.Instance().getACSIP());

      //
      // Execute the client as a separate thread.
      //
      CandS.execute(pcm);
   }

   @Override
   synchronized public void processMsg(Object o) 
   {
      try 
      {
         stl.writeToLogger
                    ("Server received a message");
      } catch (Exception e) {
         stl.writeToLogger( "No request received "
                 + "(telnet test?) ");
         return;
      }

        //
      // Process the request. (write to logger)
      //
      CState c = getMsg(o);
      String up = c.getMessage();
      stl.writeToLogger("Received Message = " + up);

   }
   synchronized public void limitMsg(Object o)
   {
         CState c = getMsg(o);
         String lines = c.getMessage();

         while(lines.length()!=0){
            wrapOneLine(lines);
         }
      
   }
	public final static int LINE_MAX = 50;

   /**
	 * wrapOneLine method that fills the new text file
    *  with the desired text formatting
	 * @param inLine: String
	 * @param output: PrintWriter
	 */
   public  void wrapOneLine(String inLine) {
		
	for(int i = 0; i < inLine.length(); i += LINE_MAX) {
		if(inLine.length() >= i + LINE_MAX) {		
			stl.writeToLogger(inLine.substring(i, i + LINE_MAX));	
		}
		else {		
			stl.writeToLogger(inLine.substring(i));		
		}
		stl.writeToLogger("\r\n");	
	}
   }
   
   synchronized public void abbreviateMsg(Object o, int limit)
   {
      
      CState c = getMsg(o);
      String msg = c.getMessage();
      stl.writeToLogger(msg.substring(0, limit));
      
   }
   
}
