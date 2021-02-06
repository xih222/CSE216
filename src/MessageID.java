
import java.io.*;

/**
 * <p>
 * Title: P2P Project</p>
 *
 * <p>
 * Description: The MessageID enum defines the message id as
 * well as the messages to be displayed on displays and
 * written into logs.
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
public enum MessageID implements Serializable 
{

   MSG("Message");

   private final Object message;

   private MessageID(final Object msg) 
   {
      message = msg;
   }

   public Object getMessage() 
   {
      return message;
   }
}
