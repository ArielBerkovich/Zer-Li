package survey;

import java.util.ArrayList;
import client.Client;
import client.ClientInterface;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import networkGUI.CustomerServiceGUI;
import networkGUI.StoreWorkerGUI;
import prototype.FormController;
import serverAPI.Response;
import user.User;

public class ResultInputGUI extends FormController implements ClientInterface {
	private Response response = null;

	//Current user's name
	private User user;
	
    @FXML // fx:id="answerTxtFld1"
    private TextField answerTxtFld1; // Value injected by FXMLLoader

    @FXML // fx:id="surveyComboBox"
    private ComboBox<?> surveyComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="answerTxtFld3"
    private TextField answerTxtFld3; // Value injected by FXMLLoader

    @FXML // fx:id="answerTxtFld2"
    private TextField answerTxtFld2; // Value injected by FXMLLoader

    @FXML // fx:id="cancelBtn"
    private Button cancelBtn; // Value injected by FXMLLoader

    @FXML // fx:id="sendBtn"
    private Button sendBtn; // Value injected by FXMLLoader

    @FXML // fx:id="answerTxtFld5"
    private TextField answerTxtFld5; // Value injected by FXMLLoader

    @FXML // fx:id="answerTxtFld4"
    private TextField answerTxtFld4; // Value injected by FXMLLoader

    @FXML // fx:id="answerTxtFld6"
    private TextField answerTxtFld6; // Value injected by FXMLLoader
  //===============================================================================================================
    @FXML
    //Will be called by FXMLLoader
    public void initialize(){
    	
    	answerTxtFld1.textProperty().addListener(new ChangeListener<String>() {
	    @Override
	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
	        String newValue) 
	    {
	    	if(newValue.matches("\\d*") && newValue.length() < 3)
	    		answerTxtFld1.setText(newValue);
	    	else
	    		answerTxtFld1.setText(oldValue);
	    }
	});

    	answerTxtFld2.textProperty().addListener(new ChangeListener<String>() {
	    @Override
	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
	        String newValue) 
	    {
	    	if(newValue.matches("\\d*") && newValue.length() < 3)
	    		answerTxtFld2.setText(newValue);
	    	else
	    		answerTxtFld2.setText(oldValue);
	    }
	});
    	answerTxtFld3.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) 
    	    {
    	    	if(newValue.matches("\\d*") && newValue.length() < 3)
    	    		answerTxtFld3.setText(newValue);
    	    	else
    	    		answerTxtFld3.setText(oldValue);
    	    }
    	});
    	answerTxtFld4.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) 
    	    {
    	    	if(newValue.matches("\\d*") && newValue.length() < 3)
    	    		answerTxtFld4.setText(newValue);
    	    	else
    	    		answerTxtFld4.setText(oldValue);
    	    }
    	});
    	answerTxtFld5.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) 
    	    {
    	    	if(newValue.matches("\\d*") && newValue.length() < 3)
    	    		answerTxtFld5.setText(newValue);
    	    	else
    	    		answerTxtFld5.setText(oldValue);
    	    }
    	});
    	answerTxtFld6.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) 
    	    {
    	    	if(newValue.matches("\\d*") && newValue.length() < 3)
    	    		answerTxtFld6.setText(newValue);
    	    	else
    	    		answerTxtFld6.setText(oldValue);
    	    }
    	});
    	
    }
  //===============================================================================================================
    @FXML
    void onShowSurveys(ActionEvent event) {

    }
  //===============================================================================================================
    /**
     * collecting the data and creating a new result for a spesific survey
     * @param event
     */
    @FXML
    void onSendBtn(ActionEvent event) {
    	int[] answers = new int[6];//===========================================CHANGE SURVEY NAME TO BE GOTTEN FROM THE COMBO BOX!!!!=======
    	answers[0]=Integer.parseInt(answerTxtFld1.getText());
    	answers[1]=Integer.parseInt(answerTxtFld2.getText());
    	answers[2]=Integer.parseInt(answerTxtFld3.getText());
    	answers[3]=Integer.parseInt(answerTxtFld4.getText());
    	answers[4]=Integer.parseInt(answerTxtFld5.getText());
    	answers[5]=Integer.parseInt(answerTxtFld6.getText());
    	if(answers[0]<=10 && answers[1]<=10 && answers[2]<=10 && answers[3]<=10 && answers[4]<=10 && answers[5]<=10 
    			&& answers[0]>0 && answers[1]>0 && answers[2]>0 && answers[3]>0 && answers[4]>0 && answers[5]>0)
    	{
    		CustomerSatisfactionSurveyResultsController.addResults(/*(String)surveyComboBox.getSelectionModel().getSelectedItem()*/"new survey", answers, client);
    		try
        	{
        		synchronized(this)
        		{
        			// wait for server response
        			this.wait();
        		}
        	
        		if (response == null)
        			return;
	        		
	        	// show success 
	        	if (response.getType() == Response.Type.SUCCESS)
	        	{
    				Alert alert = new Alert(AlertType.CONFIRMATION, "Result input was successfull", ButtonType.OK);
	        		alert.showAndWait();
	        		// clear replay
	        		response = null;
	        	}
	        	else
	        	{
    				// show failure  
    	    		Alert alert = new Alert(AlertType.ERROR, (String)response.getMessage(), ButtonType.OK);
    	    		alert.showAndWait();
    	    		// clear replay
    	    		response = null;
	        	}
        	}
    		catch(InterruptedException e) {}
    		
    	}
    	else
    	{
    		// show failure  
    		Alert alert = new Alert(AlertType.ERROR, "Inputs must be 1-10.", ButtonType.OK);
    		alert.showAndWait();
    	}

    }
  //===============================================================================================================
    @FXML
    void onCancel(ActionEvent event) {
    	StoreWorkerGUI storeWorkerGUI = (StoreWorkerGUI)parent;
    	client.setUI(storeWorkerGUI);
    	FormController.primaryStage.setScene(parent.getScene());
    }
  //===============================================================================================================
	public void display(Object message) {
		
    	System.out.println(message.toString());
    	System.out.println(message.getClass().toString());
		
		Response response = (Response)message;
		this.response = response;
		
		synchronized(this)
		{
			this.notify();
		}
		
		//.............................................combo
	}
	//===============================================================================================================
	@Override
	public void onSwitch(Client newClient) {
		// TODO Auto-generated method stub
		
	}
	//===============================================================================================================
	public void setUser(User user)
	{
		this.user = user;
	}
	public void initComboBox()
	{
		CustomerSatisfactionSurveyController.requestSurveys(client);
    	ArrayList<String> surveyNames = new ArrayList<String>();
    	CustomerSatisfactionSurveyController.requestSurveys(client);
    	try
    	{
    		synchronized(this)
    		{
    			// wait for server response
    			this.wait();
    		}
    	
    		if (response == null)
    			return;
        		
        	// show success 
        	if (response.getType() == Response.Type.SUCCESS)
        	{
        		
        		response.getMessage().toString();
        		
        	}
        	else
        	{
        		Alert alert = new Alert(AlertType.ERROR, "Could not load surveys", ButtonType.OK);
        		alert.showAndWait();
        		// clear response
        		response = null;
        	}
    	}
        catch(InterruptedException e) {}
	}
}
