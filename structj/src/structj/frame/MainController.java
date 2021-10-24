package structj.frame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import structj.frame.rcframe.MainRc;

public class MainController {

	public void closeStage() {
		Stage stage11 = (Stage) btnRC.getScene().getWindow();
		Platform.runLater(() -> {
			stage11.close();
		});
	}

	@FXML
	private Button btnRC;

	@FXML
	private Button btnSTEEL;

	@FXML
	private Button btnSR;

	@FXML
	private Button btnJOIN;

	@FXML
	private Button btnPER;

	@FXML
	private Button btnOth;

	@FXML
	void onClickJOIN(ActionEvent event) {

	}

	@FXML
	void onClickOth(ActionEvent event) {

	}

	@FXML
	void onClickPER(ActionEvent event) {

	}

	@FXML
	void onClickRC(ActionEvent event) throws Exception {

//		Parent root1 = FXMLLoader.load(getClass().getResource("rcframe/MainRcfx.fxml"));
//		Scene scene = new Scene(root1, 600, 300);
		Stage primaryStage = new Stage();
//		primaryStage.setScene(scene);
//		primaryStage.show();
		MainRc mainRc = new MainRc();
		mainRc.start(primaryStage);
		closeStage();

	}

	@FXML
	void onClickSR(ActionEvent event) {

	}

	@FXML
	void onClickSTEEL(ActionEvent event) {

	}

}
