package structj.frame.rcframe.slab;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MainRcSlabController implements Initializable {

	//
	@FXML
	public TextField rcSlabName;

	@FXML
	private TextField rcSlabCon;

	@FXML
	private ComboBox<String> rebarDia1;

	ObservableList<String> list = FXCollections.observableArrayList("D10", "D13", "D16", "D19", "D22", "D25", "D29",
			"D32");

	@FXML
	private TextField RebarFy1;

	@FXML
	private ComboBox<String> rebarDia2;

	ObservableList<String> list2 = FXCollections.observableArrayList("D10", "D13", "D16", "D19", "D22", "D25", "D29",
			"D32");

	@FXML
	private TextField rebarFy2;

	@FXML
	private TextField rcSlabSpanx;

	@FXML
	private TextField rcSlabSpany;

	@FXML
	private TextField rcSlabThk;

	@FXML
	private TextField rcSlabCover;

	@FXML
	private TextField rcSlabDL;

	@FXML
	private TextField rcSlabLL;

	@FXML
	private ToggleGroup slabgroup;

	@FXML
	private RadioButton rcSlabWay1;

	@FXML
	private RadioButton rcSlabWay2;

	@FXML
	private RadioButton rcSlabWay11;

	@FXML
	private ToggleGroup way1group;

	@FXML
	private RadioButton rcSlabWay12;

	@FXML
	private RadioButton rcSlabWay13;

	@FXML
	private RadioButton rcSlabWay14;

	@FXML
	private RadioButton rcSlabWay21;

	@FXML
	private ToggleGroup way2group;

	@FXML
	private RadioButton rcSlabWay22;

	@FXML
	private RadioButton rcSlabWay23;

	@FXML
	private RadioButton rcSlabWay24;

	@FXML
	private RadioButton rcSlabWay25;

	@FXML
	private RadioButton rcSlabWay26;

	@FXML
	private RadioButton rcSlabWay27;

	@FXML
	private RadioButton rcSlabWay28;

	@FXML
	private RadioButton rcSlabWay29;

	@FXML
	private VBox rcSlabWay1Table;

	@FXML
	private VBox rcSlabWay2Table;

	// 실행시 바로적용되는것들
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		rebarDia1.setItems(list);
		rebarDia2.setItems(list2);

		rcSlabWay1Table.setVisible(false);
		rcSlabWay2Table.setVisible(false);

		// 1. 표 라인 생성
		slabSheet1.setGridLinesVisible(true);
		slabDesignCode.setText("KDS 41 00 00");
		slabUnitSystem.setText("kN, m");
		slabSpanx.setText("4.00m");
		slabSpany.setText("4.00m");
		slabThk.setText("150mm");
		slabFck.setText("24Mpa");
		slabFy.setText("400Mpa");

		// 2.
		slabSheet2.setGridLinesVisible(true);
		slabDeadload.setText("4.0");
		slabLiveload.setText("5.0");
		slabType.setText("1-Way slab");
		slabSupport.setText("type-2");

		// 3.
		slabSheet3.setGridLinesVisible(true);
		slabThickness1.setText("150");
		slabThickness2.setText("180");
		slabThickness3.setText("1.23");

		Platform.runLater(() -> setFasterScroller(rcSlabScroll));
	}

	private static void setFasterScroller(ScrollPane scroll) {
		// TODO Auto-generated method stub
		ScrollBar verticalScrollbar = (ScrollBar) scroll.lookup(".scroll-bar:vertical");
		double defaultUnitIncrement = verticalScrollbar.getUnitIncrement();
		verticalScrollbar.setUnitIncrement(defaultUnitIncrement * 1000);
	}

	@FXML
	void onRcSlabWay1(ActionEvent event) {
		rcSlabWay1Table.setVisible(true);
		rcSlabWay2Table.setVisible(false);

	}

	@FXML
	void onRcSlabWay2(ActionEvent event) {
		rcSlabWay2Table.setVisible(true);
		rcSlabWay1Table.setVisible(false);

	}

	@FXML
	private Button rcSlabDesign;

	@FXML
	private Button rcSlabCheck;

	@FXML
	void onClickSlabCheck(ActionEvent event) {
		System.out.println(rcSlabName);
		String slabname = rcSlabName.getText();
		int slabcon = Integer.parseInt(rcSlabCon.getText()); // gettext는 String Integer를 써서 int로 변환.
		int dia1 = Integer.parseInt((rebarDia1.getValue()).substring(1)); // substring > 문자열 몇번째부터 가져올것인지.
		int dia2 = Integer.parseInt((rebarDia2.getValue()).substring(1));
		int slabrebar1 = Integer.parseInt(RebarFy1.getText());
		String slabrebar2 = rebarFy2.getText();
		String slabspanx = rcSlabSpanx.getText();
		String slabspany = rcSlabSpany.getText();
		String slabthk = rcSlabThk.getText();
		String slabcover = rcSlabCover.getText();

		CalculateSlab nam = new CalculateSlab();

		nam.first(slabname, slabcon);
		System.out.println(dia1);

	}

	@FXML
	void onClickSlabDesign(ActionEvent event) {
		

		String slabname = rcSlabName.getText();
		int slabcon = Integer.parseInt(rcSlabCon.getText()); // gettext는 String Integer를 써서 int로 변환.
		int dia1 = Integer.parseInt((rebarDia1.getValue()).substring(1)); // substring > 문자열 몇번째부터 가져올것인지.
		int dia2 = Integer.parseInt((rebarDia2.getValue()).substring(1));
		int slabrebar1 = Integer.parseInt(RebarFy1.getText());
		String slabrebar2 = rebarFy2.getText();
		double slabspanx = Integer.parseInt(rcSlabSpanx.getText());
		double slabspany = Integer.parseInt(rcSlabSpany.getText());
		String slabthk = rcSlabThk.getText();
		String slabcover = rcSlabCover.getText();
		double slabdl = Integer.parseInt(rcSlabDL.getText());
		double slabll = Integer.parseInt(rcSlabLL.getText());

		Coefficients.moment(slabspanx, slabspany, slabdl, slabll, 2, 1);
		
	}

	// 실행화면

	@FXML
	private ScrollPane rcSlabScroll;

	@FXML
	private GridPane slabSheet1;

	@FXML
	private Label slabDesignCode;

	@FXML
	private Label slabUnitSystem;

	@FXML
	private Label slabSpanx;

	@FXML
	private Label slabSpany;

	@FXML
	private Label slabThk;

	@FXML
	private Label slabFck;

	@FXML
	private Label slabFy;

	@FXML
	private GridPane slabSheet2;

	@FXML
	private Label slabDeadload;

	@FXML
	private Label slabLiveload;

	@FXML
	private Label slabType;

	@FXML
	private Label slabSupport;

	@FXML
	private ImageView slabImage1;

	@FXML
	private ImageView slabImage2;

	@FXML
	private GridPane slabSheet3;

	@FXML
	private Label slabThickness1;

	@FXML
	private Label slabThickness2;

	@FXML
	private Label slabThickness3;

	@FXML
	private Label slabShortMidMu;

	@FXML
	private Label slabShortMidRatio;

	@FXML
	private Label slabShortMidAst;

	@FXML
	private Label slabShortMid1;

	@FXML
	private Label slabShortMid2;

	@FXML
	private Label slabShortMid3;

	@FXML
	private Label slabShortMid4;

	@FXML
	private Label slabShortConMu;

	@FXML
	private Label slabShortConRatio;

	@FXML
	private Label slabShortConAst;

	@FXML
	private Label slabShortCon1;

	@FXML
	private Label slabShortCon2;

	@FXML
	private Label slabShortCon3;

	@FXML
	private Label slabShortCon4;

	@FXML
	private Label slabShortDisconMu;

	@FXML
	private Label slabShortDisconRatio;

	@FXML
	private Label slabShortDisconAst;

	@FXML
	private Label slabShortDiscon1;

	@FXML
	private Label slabShortDiscon2;

	@FXML
	private Label slabShortDiscon3;

	@FXML
	private Label slabShortDiscon4;

	@FXML
	private Label slabLongMidMu;

	@FXML
	private Label slabLongMidRatio;

	@FXML
	private Label slabLongMidAst;

	@FXML
	private Label slabLongMid1;

	@FXML
	private Label slabLongMid2;

	@FXML
	private Label slabLongMid3;

	@FXML
	private Label slabLongMid4;

	@FXML
	private Label slabLongConMu;

	@FXML
	private Label slabLongConRatio;

	@FXML
	private Label slabLongConAst;

	@FXML
	private Label slabLongCon1;

	@FXML
	private Label slabLongCon2;

	@FXML
	private Label slabLongCon3;

	@FXML
	private Label slabLongCon4;

	@FXML
	private Label slabLongDisconMu;

	@FXML
	private Label slabLongDisconRatio;

	@FXML
	private Label slabLongDisconAst;

	@FXML
	private Label slabLongDiscon1;

	@FXML
	private Label slabLongDiscon2;

	@FXML
	private Label slabLongDiscon3;

	@FXML
	private Label slabLongDiscon4;

	@FXML
	private Label slabMinRebarRatio;

	@FXML
	private Label slabMinRebarAst;

	@FXML
	private Label slabMinRebar1;

	@FXML
	private Label slabMinRebar2;

	@FXML
	private Label slabMinRebar3;

	@FXML
	private Label slabMinRebar4;

	@FXML
	private Label rcSlabVu1;

	@FXML
	private Label rcSlabShear1;

	@FXML
	private Label rcSlabVc1;

	@FXML
	private Label rcSlabShearResult1;

	@FXML
	private Label rcSlabVu2;

	@FXML
	private Label rcSlabShear2;

	@FXML
	private Label rcSlabVc2;

	@FXML
	private Label rcSlabShearResult2;

}
