package structj.frame.rcframe.slab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainRcSlab extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainRcSlabfx.fxml"));
			Scene scene = new Scene(root, 1300, 900);
			scene.getStylesheets().add(getClass().getResource("rcapplication.css").toExternalForm());
			primaryStage.setScene(scene);
			//ADD
//			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("logo.png")));		//로고이미지삽입
//			primaryStage.setResizable(false); 			//크기변경불가
			//
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

