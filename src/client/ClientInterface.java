package client;

//*************************************************************************************************
/**
 * This interface implements the abstract method used to display
 * objects onto the client or server UIs.
 */
//*************************************************************************************************
public interface ClientInterface {
	
	public static int TIMEOUT = 5000;
	//*************************************************************************************************
	/**
	* Method that when overridden is used to display objects onto
	* a UI.
	* @param message the message to transfer to this ClientInterface
	*/
	//*************************************************************************************************
	public abstract void display(Object message);
}
