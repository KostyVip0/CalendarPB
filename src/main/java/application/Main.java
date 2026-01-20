package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;

public class Main extends Application {	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Calendar.fxml"));
			Scene scene = new Scene(root, 2200, 1200, Color.TRANSPARENT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			
			//primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWING, event -> {
			//	
			//});
			
			primaryStage.show();

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	

	public static void main(String[] args) {
		launch(args);
	}	
}
