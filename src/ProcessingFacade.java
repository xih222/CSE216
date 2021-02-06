/** <p>Title: P2P Project</p>
 *
 * <p>Description: This class sets the subsystem
 *    into the facade and routes the message
 *    to the correct subsystem.
 * </p>
 *
 * <p>Copyright: none</p>
 *
 * <p>Company: Lehigh University</p>
 *
 * @author Bill Phillips 
 *
 */
public class ProcessingFacade 
{

   private ProcessingFacadeBase acsF = null;
   private ProcessingFacadeBase tmgF = null;
   private ProcessingFacadeBase cs6f = null;
   private final RunTimeVars rtv     = 
                     RunTimeVars.Instance();


   public void setACSFacade(ACSFacade a) 
   {
      acsF = a;
   }

   public void setTestMessageFacade
                             (TestMsgFacade t) 
   {
      tmgF = t;
   }

   public void setCSE216Facade(CSE216Facade c) 
   {
      cs6f = c;
   }

   synchronized public void processMsg(Object o) 
   {
      if (rtv.isACS()) 
      {
         acsF.processMsg(o);
      } 
      else if(rtv.isCSE())
      {
         cs6f.processMsg(o);
      }
      else if(rtv.isTMG())
      {
         tmgF.processMsg(o);
      }

   }

}
