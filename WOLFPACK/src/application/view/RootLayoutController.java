package application.view;
import java.io.IOException;

import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class RootLayoutController {
	
    private Main mainApp;
    
    
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    
    public void TableView() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("view/TableView.fxml"));
    		Parent root = (Parent) loader.load();
    		Stage stage = new Stage();
    		
    		stage.setTitle("Table View");
    		stage.setScene(new Scene(root));
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void BarChart() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("view/BarChart.fxml"));
    		Parent root = (Parent) loader.load();
    		Stage stage = new Stage();
    		
    		stage.setTitle("Bar Chart");
    		stage.setScene(new Scene(root));
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void AboutUs() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("view/About.fxml"));
    		Parent root1 = (Parent) loader.load();
    		Stage stage = new Stage();

    		stage.setTitle("About Us");
    		stage.setScene(new Scene(root1));
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void UserGuide() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("view/UserGuide.fxml"));
    		Parent root1 = (Parent) loader.load();
    		Stage stage = new Stage();

    		stage.setTitle("User Guide");
    		stage.setScene(new Scene(root1));
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
       
    public void Close() {
        Platform.exit();
    }
}
