package structj.frame.rcframe;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import structj.frame.Main;
import structj.frame.rcframe.slab.MainRcSlab;

public class MainRcController {
	
	public void closeStageRcSlab() {
		Stage stage11 = (Stage) btnRcBack.getScene().getWindow();
		Platform.runLater(() -> {
			stage11.close();
		});
	}

    @FXML
    private Button btnRcFrame;

    @FXML
    private Button btnRcSlab;

    @FXML
    private Button btnRcBeam;

    @FXML
    private Button btnRcColumn;

    @FXML
    private Button btnRcWall;

    @FXML
    private Button btnRcFooting;

    @FXML
    private Button btnRcOther;

    @FXML
    private Button btnRcBack;

    @FXML
    void onClickRcBack(ActionEvent event) {
		Stage primaryStage = new Stage();
		Main mainRc = new Main();
		mainRc.start(primaryStage);
		closeStageRcSlab();
    }

    @FXML
    void onClickRcBeam(ActionEvent event) {

    }

    @FXML
    void onClickRcColumn(ActionEvent event) {

    }

    @FXML
    void onClickRcFooting(ActionEvent event) {

    }

    @FXML
    void onClickRcFrame(ActionEvent event) {

    }

    @FXML
    void onClickRcOther(ActionEvent event) {

    }

    @FXML
    void onClickRcSlab(ActionEvent event) throws Exception {
		Stage primaryStage = new Stage();
		MainRcSlab mainRc = new MainRcSlab();
		mainRc.start(primaryStage);
//		closeStageRcSlab();

    }

    @FXML
    void onClickRcWall(ActionEvent event) {

    }

}
