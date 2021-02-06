/** <p>Title: P2P Project</p>
 *
 * <p>Description: This class retrieves is the
 *    Builder (design pattern) for the P2PProject.       
 * </p>
 *
 * <p>Copyright: none</p>
 *
 * <p>Company: Lehigh University</p>
 *
 * @author Bill Phillips 
 *
 */

public class P2PBuilder 
{
   protected ProcessingFacade pfb;

   protected void build()
   {
      System.out.print("This method (build)");
      System.out.print  ("should have been ");
      System.out.println       ("overridden");
      System.exit(0);
   }
   
   protected void buildTestMsg()
   {
      //
      // Instantiating a new
      // Facade should
      // build it but we can do
      // pre or post processing
      // here.
      //
      TestMsgFacade t = 
                          new TestMsgFacade();
      pfb.setTestMessageFacade(t);
   }
    
   protected void buildACS()
   {
      ACSFacade a = new ACSFacade();
      pfb.setACSFacade(a);
   }  
   
   protected void buildCSE216()
   {
      CSE216Facade c = new CSE216Facade();
      pfb.setCSE216Facade(c);
      
   }
   
   public ProcessingFacade getResult()
   {
      return pfb;
   }
   
   public P2PBuilder()
   {
      pfb = new ProcessingFacade();
   }
   
}
