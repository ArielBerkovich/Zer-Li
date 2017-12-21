package user;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import product.ProdcutController;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.event.HyperlinkEvent.EventType;

import client.Client;
import client.ClientInterface;
import prototype.FormController;
import utils.Replay;

public class UserGUIController extends FormController implements ClientInterface  {

	private Replay replay = null;
	
    @FXML
    private Button loginBtn;

    @FXML
    private TextField usernameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private Button exitBtn;
    
    @FXML
    void onExit(ActionEvent event) {
    	client.quit();
    	System.exit(0);
    }

    @FXML
    void onLogin(ActionEvent event) {
    	UserController.requestLogin(usernameTxt.getText(), passwordTxt.getText(), client);
    	try
    	{
    		synchronized(this)
    		{
    			this.wait();
    		}
    	
    		if (replay == null)
    			return;
    		
    	if (replay.getType() == Replay.Type.SUCCESS)
    	{
    		Alert alert = new Alert(AlertType.INFORMATION, "Logged in successfully :)", ButtonType.OK);
    		alert.showAndWait();
    		replay = null;
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR, (String)replay.getMessage(), ButtonType.OK);
    		alert.showAndWait();
    		replay = null;
    	}
    	
    	}catch(InterruptedException e) {}
    }
	    
	@Override
	public void onSwitch(Client newClient) {
		// TODO Auto-generated method stub

	}
	
	public void display(Object message)
	{
    	System.out.println(message.toString());
    	System.out.println(message.getClass().toString());
		
		Replay replay = (Replay)message;
		
		this.replay = replay;
		synchronized(this)
		{
			this.notify();
		}
	}

}
