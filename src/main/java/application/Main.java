package application;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Main extends Application {

	private ConfigurableApplicationContext springContext;

	@Override
	public void start(Stage primaryStage) throws IOException {

		springContext = new SpringApplicationBuilder(Main.class).headless(false).run();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("Calendar.fxml"));
		loader.setControllerFactory(springContext::getBean);


		AnchorPane root = (AnchorPane) loader.load();
		Scene scene = new Scene(root, 1800, 1050, Color.TRANSPARENT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.TRANSPARENT);

		primaryStage.show();

	}

	@Override
	public void stop() throws Exception {
		springContext.close();
	}



	public static void main(String[] args)	{
		launch(Main.class);
	}	
}
