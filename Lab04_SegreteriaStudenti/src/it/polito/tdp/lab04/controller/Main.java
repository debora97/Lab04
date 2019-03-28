package it.polito.tdp.lab04.controller;
import it.polito.tdp.lab04.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SegreteriaStudenti.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Model model = new Model();

			SegreteriaStudentiController controller = loader.getController();
			controller.setModel(model);

			/*
			 * Create and set the model here!
			 */
			// controller.setModel(); FATTO

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
