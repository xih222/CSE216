/**
 * <p>Title: CS656 Project</p>
 *
 * <p>Description: Interface class used to define the
 * object returned by the factory method in ClientInputPanelFactory.
 * </p>
 *
 * <p>Copyright: none</p>
 *
 * <p>Company: NJIT Fall 2009</p>
 *
 * @author Bill Phillips (214-36-930)
 * @version 1.0
 */

abstract public interface IOInterface
{

    abstract public String                         getFileName();
    abstract public String                         getFileType();
    abstract public String                         getHostName();
    abstract public int                                getPort();
    abstract public boolean                          isPublish();
    abstract public boolean                           isSearch();
    abstract public boolean                         isRetrieve();
    abstract public boolean                            isLeave();
    abstract public boolean                  waitingForCommand();
    abstract public void                           enablePanel();
    abstract public void                            OnRdisplay();
    abstract public void        WriteFilename( final CState cs );
    abstract public void                           OffRdisplay();
    abstract public void                              ClearAll();
    abstract public void    writeStatusMessage( final String S );
}
