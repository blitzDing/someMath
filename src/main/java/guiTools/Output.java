package guiTools;

import javafx.scene.control.Alert;

public class Output 
{

	public static void errorAlert(String phrase)
	{
		
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText("Error");
		alert.setContentText(phrase);
		alert.showAndWait();
	}
	
	public static void infoAlert(String phrase)
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(phrase);
		alert.showAndWait();
	}

	public static void viewText(String txt)
	{

	}
}
