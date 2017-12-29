package networkGUI;

import client.Client;
import client.ClientInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import prototype.FormController;
import survey.SurveyCreationGUI;
import systemManager.SystemManagerGUI;
import user.LoginGUI;
import user.NewUserCreationGUI;

public class CustomerServiceGUI extends FormController implements ClientInterface{

	SurveyCreationGUI surveyCreationGUI;
	
    @FXML // fx:id="surveylistBtn"
    private Button surveylistBtn; // Value injected by FXMLLoader

    @FXML // fx:id="newSurveyBtn"
    private Button newSurveyBtn; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML
    //Will be called by FXMLLoader
    public void initialize(){
    	surveyCreationGUI = FormController.<SurveyCreationGUI, AnchorPane>loadFXML(getClass().getResource("/survey/SurveyCreationGUI.fxml"), this);
    }
    @FXML
    void onNewSurvey(ActionEvent event) {
    	if ( surveyCreationGUI != null)
		{
    		surveyCreationGUI.setClinet(client);
			FormController.primaryStage.setScene(surveyCreationGUI.getScene());
		}
    }

    @FXML
    void onBack(ActionEvent event) {
    	
    	LoginGUI loginGUi = (LoginGUI)parent;
    	client.setUI(loginGUi);
    	FormController.primaryStage.setScene(parent.getScene());
    }

    @FXML
    void onSurveyList(ActionEvent event) {

    }

	@Override
	public void display(Object message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSwitch(Client newClient) {
		// TODO Auto-generated method stub
		
	}

}
