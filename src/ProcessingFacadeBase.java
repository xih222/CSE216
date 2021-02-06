/** <p>Title: P2P Project</p>
 *
 * <p>Description: This base class provides
 *    the getMsg and setMsg functions.
 *    processMsg is overriden by the base
 *    class to receive the Cstate object
 *    containing the message.
 * </p>
 *
 * <p>Copyright: none</p>
 *
 * <p>Company: Lehigh University</p>
 *
 * @author Bill Phillips 
 *
 */
class ProcessingFacadeBase 
{
   CState getMsg(Object o)
   {
      ServerServices s = (ServerServices) o;
      CState c = (CState)s.get();
      return c;
   }
   
   synchronized public void processMsg(Object o)
   {
      System.out.print           ("This method "); 
      System.out.print          ("(processMsg) ");
      System.out.print      ("should have been ");
      System.out.println           ("overridden");
      System.exit(0); 
   };
   
   void sendMsg(CState c, Object o)
   {
      ServerServices s = (ServerServices) o; 
      s.send(c);
   }
   
}
