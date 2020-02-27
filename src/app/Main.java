package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	@Override
	public void start(Stage primaryStage)throws Exception{
		  Parent root = FXMLLoader.load(getClass().getResource("firstPage.fxml"));
    	  Scene scene = new Scene(root); 
    	  primaryStage.setTitle("JavaFX"); 
    	  primaryStage.setScene(scene); 
    	  primaryStage.setWidth(1000);
    	  primaryStage.setHeight(850);
    	  primaryStage.setResizable(false);
    	  primaryStage.show();
      }
	public static void main(String args[]){
		launch(args);
	}
	
	
}
