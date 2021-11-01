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
	private ComboBox<ImageView> rcSlabWay2box;

	@FXML
	private TextField RebarFy2;

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
	private ToggleGroup way2group;

	@FXML
	private VBox rcSlabWay1Table;

	@FXML
	private VBox rcSlabWay2Table;

	@FXML
	private TextField rcSlabB1b;

	@FXML
	private TextField rcSlabB1h;

	@FXML
	private TextField rcSlabB2b;

	@FXML
	private TextField rcSlabB2h;

	@FXML
	private Button slabup2;

	@FXML
	private TextField rcSlabB3b;

	@FXML
	private TextField rcSlabB3h;

	@FXML
	private Button slabup3;

	@FXML
	private TextField rcSlabB4b;

	@FXML
	private TextField rcSlabB4h;

	@FXML
	private Button slabup4;

	// 실행시 바로적용되는것들
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		rebarDia1.setItems(list);
		rebarDia2.setItems(list2);

		rcSlabWay1Table.setVisible(false);
		rcSlabWay2Table.setVisible(false);

		rebarDia1.setValue("D13");
		rebarDia2.setValue("D16");

		// 1. 표 라인 생성
		slabSheet1.setGridLinesVisible(true);
		slabDesignCode.setText("KDS 41 00 00");
		slabUnitSystem.setText("kN, m");
		slabSpanx.setText("4.00m");
		slabSpany.setText("4.00m");
		slabThk.setText("150mm");
		slabFck.setText("24Mpa");
		slabFy1.setText("400Mpa");

		// 2.
		slabSheet2.setGridLinesVisible(true);
		slabDeadload.setText("4.0");
		slabLiveload.setText("5.0");
		// slabType.setText("1-Way slab");
		// slabSupport.setText("type-2");

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
		// way2group.selectToggle(null);

	}

	@FXML
	void onRcSlabWay2(ActionEvent event) {
		rcSlabWay2Table.setVisible(true);
		rcSlabWay1Table.setVisible(false);
		way1group.selectToggle(null);

	}

	@FXML
	private Button rcSlabDesign;

	@FXML
	private Button rcSlabCheck;

	@FXML
	void onClickSlabCheck(ActionEvent event) {
		String slabname = rcSlabName.getText();
		int slabcon = Integer.parseInt(rcSlabCon.getText()); // gettext는 String Integer를 써서 int로 변환.
		int dia1 = Integer.parseInt((rebarDia1.getValue()).substring(1)); // substring > 문자열 몇번째부터 가져올것인지.
		int dia2 = Integer.parseInt((rebarDia2.getValue()).substring(1));
		int slabrebar1 = Integer.parseInt(RebarFy1.getText());
		int slabrebar2 = Integer.parseInt(RebarFy2.getText());
		double slabspanx = Double.parseDouble(rcSlabSpanx.getText());
		double slabspany = Double.parseDouble(rcSlabSpany.getText());
		int slabthk = Integer.parseInt(rcSlabThk.getText());
		double slabcover = Double.parseDouble(rcSlabCover.getText());
		double slabdl = Double.parseDouble(rcSlabDL.getText());
		double slabll = Double.parseDouble(rcSlabLL.getText());
		int b1 = Integer.parseInt(rcSlabB1b.getText());
		int h1 = Integer.parseInt(rcSlabB1h.getText());
		int b2 = Integer.parseInt(rcSlabB2b.getText());
		int h2 = Integer.parseInt(rcSlabB2h.getText());
		int b3 = Integer.parseInt(rcSlabB3b.getText());
		int h3 = Integer.parseInt(rcSlabB3h.getText());
		int b4 = Integer.parseInt(rcSlabB4b.getText());
		int h4 = Integer.parseInt(rcSlabB4h.getText());
		int way2_int = 0;

		// 실행화면
		slabSpanx.setText(String.format("%.2f", slabspanx) + " m");
		slabSpany.setText(String.format("%.2f", slabspany) + " m");
		slabThk.setText(slabthk + " mm");
		slabFck.setText(slabcon + " MPa");
		slabFck.setText(slabcon + " MPa");
		slabFy1.setText(rebarDia1.getValue() + "이하 : " + slabrebar1 + "MPa");
		slabFy2.setText(rebarDia2.getValue() + "이상 : " + slabrebar2 + "MPa");

		// 2.
		// deadload & Liveload
		slabDeadload.setText(String.format("%.2f", slabdl) + "");
		slabLiveload.setText(String.format("%.2f", slabll) + "");

		// slabway
		if (rcSlabWay1.isSelected()) {
			slabType.setText(rcSlabWay1.getText() + " slab");

			if (rcSlabWay11.isSelected()) {
				slabSupport.setText("Pin-Pin");
			} else if (rcSlabWay12.isSelected()) {
				slabSupport.setText("Pin-Fix");
			} else if (rcSlabWay13.isSelected()) {
				slabSupport.setText("Fix-FIx");
			} else if (rcSlabWay14.isSelected()) {
				slabSupport.setText("Canti");
			}
		} else if (rcSlabWay2.isSelected()) {
			slabType.setText(rcSlabWay2.getText() + " slab");

			// 슬래브2way선택시 int1~9까지로 change
			// System.out.println(rcSlabWay2box.getValue().getId());
			String way2box = rcSlabWay2box.getValue().getId();
			String way2 = way2box.substring(way2box.length() - 1, way2box.length());
			// System.out.println(way2);
			way2_int = Integer.parseInt(way2);

			slabSupport.setText("Case -" + way2_int);
		} else {
			System.out.println("1~2way방식 잘못됨.");
		}
		
		
		//3.
		// 슬래브 두꼐체크

		slabTHK(slabrebar1, slabspanx, slabspany, slabthk, b1, h1, b2, h2, b3, h3, b4, h4, way2_int);
		
		//4.
		// 슬래브 모멘트
		

	}

	@FXML
	void onClickSlabDesign(ActionEvent event) {

		String slabname = rcSlabName.getText();
		int slabcon = Integer.parseInt(rcSlabCon.getText()); // gettext는 String Integer를 써서 int로 변환.
		int dia1 = Integer.parseInt((rebarDia1.getValue()).substring(1)); // substring > 문자열 몇번째부터 가져올것인지.
		int dia2 = Integer.parseInt((rebarDia2.getValue()).substring(1));
		int slabrebar1 = Integer.parseInt(RebarFy1.getText());
		int slabrebar2 = Integer.parseInt(RebarFy2.getText());
		double slabspanx = Double.parseDouble(rcSlabSpanx.getText());
		double slabspany = Double.parseDouble(rcSlabSpany.getText());
		int slabthk = Integer.parseInt(rcSlabThk.getText());
		double slabcover = Double.parseDouble(rcSlabCover.getText());
		double slabdl = Double.parseDouble(rcSlabDL.getText());
		double slabll = Double.parseDouble(rcSlabLL.getText());
		int b1 = Integer.parseInt(rcSlabB1b.getText());
		int h1 = Integer.parseInt(rcSlabB1h.getText());
		int b2 = Integer.parseInt(rcSlabB2b.getText());
		int h2 = Integer.parseInt(rcSlabB2h.getText());
		int b3 = Integer.parseInt(rcSlabB3b.getText());
		int h3 = Integer.parseInt(rcSlabB3h.getText());
		int b4 = Integer.parseInt(rcSlabB4b.getText());
		int h4 = Integer.parseInt(rcSlabB4h.getText());

		Coefficients.moment(slabspanx, slabspany, slabdl, slabll, 2, 1);

	}
	
	
	//모멘트 계산
	public static void momentcal(double negMoX, double negMoY, double posMoDeadX, double posMoDeadY, double posMoLiveX, double posMoLiveY) {
		
		
	}

	// 슬래브 두께 체크계산
	private void slabTHK(int slabrebar1, double slabspanx, double slabspany, int slabthk, int b1, int h1, int b2,
			int h2, int b3, int h3, int b4, int h4, int way2_int) {

		// 슬래브 두께 구하기
		if (rcSlabWay1.isSelected()) {
			// 1방향일때 두께산정식구하기~~~~~~~~~

		} else if (rcSlabWay2.isSelected()) {

			double ratioBH = slabspany / slabspanx;
			int beamB[] = { b1, b2, b3, b4 };
			int beamH[] = { h1, h2, h3, h4 };
			double beamHb[] = { h1 - slabthk, h2 - slabthk, h3 - slabthk, h4 - slabthk };
			double beamSpan1[] = { 0, 0, 0, 0 };
			double beamSpan2[] = { 0, 0, 0, 0 };
			double beamTbercen1[] = { 0, 0, 0, 0 }; // 반tbar중심축
			double beamTbercen2[] = { 0, 0, 0, 0 }; // tbar중심축
			double beamIb1[] = { 0, 0, 0, 0 };
			double beamIb2[] = { 0, 0, 0, 0 };
			double slabIb1[] = { 0, 0, 0, 0 }; // 반t
			double slabIb2[] = { 0, 0, 0, 0 }; // t
			double slabSpan1[] = { b1 / 2 + slabspanx * 1000 / 2, b2 / 2 + slabspanx * 1000 / 2,
					b3 / 2 + slabspany * 1000 / 2, b4 / 2 + slabspany * 1000 / 2 };
			double slabSpan2[] = { slabspanx * 1000, slabspanx * 1000, slabspany * 1000, slabspany * 1000 };
			double beama[] = { 0, 0, 0, 0 };

			for (int i = 0; i < beamH.length; i++) {
				if (beamHb[i] < 8 * slabthk) {
					beamSpan2[i] = beamHb[i];
				} else {
					beamSpan2[i] = 8 * slabthk;
				}

				if (beamHb[i] < 4 * slabthk) {
					beamSpan1[i] = beamHb[i];
				} else {
					beamSpan1[i] = 4 * slabthk;
				}

				// 중심축구하기
				// System.out.println(beamSpan1[i]);
				beamTbercen1[i] = ((beamB[i] * beamH[i] * beamH[i] / 2)
						+ (beamSpan1[i] * slabthk * (beamH[i] - (slabthk / 2))))
						/ ((beamB[i] * beamH[i]) + (beamSpan1[i] * slabthk));
				// System.out.println(beamTbercen1[i]);
				beamTbercen2[i] = ((beamB[i] * beamH[i] * beamH[i] / 2)
						+ (2 * beamSpan2[i] * slabthk * (beamH[i] - (slabthk / 2))))
						/ ((beamB[i] * beamH[i]) + (2 * beamSpan2[i] * slabthk));
				beamIb1[i] = ((beamB[i] * Math.pow(beamH[i], 3) / 12)
						+ (beamB[i] * beamH[i] * Math.pow(beamTbercen1[i] - (beamH[i] / 2), 2)))
						+ (beamSpan1[i] * Math.pow(slabthk, 3) / 12
								+ (beamSpan1[i] * slabthk * Math.pow(beamH[i] - slabthk / 2 - beamTbercen1[i], 2)));
				beamIb2[i] = ((beamB[i] * Math.pow(beamH[i], 3) / 12)
						+ (beamB[i] * beamH[i] * Math.pow(beamTbercen2[i] - (beamH[i] / 2), 2)))
						+ (2 * beamSpan2[i] * Math.pow(slabthk, 3) / 12
								+ (2 * beamSpan2[i] * slabthk * Math.pow(beamH[i] - slabthk / 2 - beamTbercen2[i], 2)));
				slabIb1[i] = slabSpan1[i] * Math.pow(slabthk, 3) / 12;
				slabIb2[i] = slabSpan2[i] * Math.pow(slabthk, 3) / 12;

				System.out.println(Math.pow(beamH[i] - slabthk - beamTbercen1[i], 2));
				System.out.println(Math.pow(slabthk, 3));
				System.out.println(Math.pow(beamH[i], 3));
				System.out.println("반t span : " + beamSpan1[i]);
				System.out.println("t span : " + beamSpan2[i]);
				System.out.println("반t 중심값 : " + beamTbercen1[i]);
				System.out.println("t 중심값 : " + beamTbercen2[i]);
				System.out.println("반t beam 2차 : " + beamIb1[i]);
				System.out.println("t beam 2차 : " + beamIb2[i]);
				System.out.println("반t slab 2차 : " + slabIb1[i]);
				System.out.println("t slab 2차 : " + slabIb2[i]);
			}

			switch (way2_int) {
			case 0:
				System.out.println("잘못됨.");
				break;
			case 1:
				beama[0] = beamIb1[0] / slabIb1[0];
				beama[1] = beamIb1[1] / slabIb1[1];
				beama[2] = beamIb1[2] / slabIb1[2];
				beama[3] = beamIb1[3] / slabIb1[3];
				break;
			case 2:
				beama[0] = beamIb2[0] / slabIb2[0];
				beama[1] = beamIb2[1] / slabIb2[1];
				beama[2] = beamIb2[2] / slabIb2[2];
				beama[3] = beamIb2[3] / slabIb2[3];
				break;
			case 3:
				beama[0] = beamIb1[0] / slabIb1[0];
				beama[1] = beamIb1[1] / slabIb1[1];
				beama[2] = beamIb2[2] / slabIb2[2];
				beama[3] = beamIb2[3] / slabIb2[3];
				break;
			case 4:
				beama[0] = beamIb1[0] / slabIb1[0];
				beama[1] = beamIb2[1] / slabIb2[1];
				beama[2] = beamIb2[2] / slabIb2[2];
				beama[3] = beamIb1[3] / slabIb1[3];
				break;
			case 5:
				beama[0] = beamIb2[0] / slabIb2[0];
				beama[1] = beamIb2[1] / slabIb2[1];
				beama[2] = beamIb1[2] / slabIb1[2];
				beama[3] = beamIb1[3] / slabIb1[3];
				break;
			case 6:
				beama[0] = beamIb2[0] / slabIb2[0];
				beama[1] = beamIb1[1] / slabIb1[1];
				beama[2] = beamIb1[2] / slabIb1[2];
				beama[3] = beamIb1[3] / slabIb1[3];
				break;
			case 7:
				beama[0] = beamIb1[0] / slabIb1[0];
				beama[1] = beamIb1[1] / slabIb1[1];
				beama[2] = beamIb1[2] / slabIb1[2];
				beama[3] = beamIb2[3] / slabIb2[3];
				break;
			case 8:
				beama[0] = beamIb2[0] / slabIb2[0];
				beama[1] = beamIb1[1] / slabIb1[1];
				beama[2] = beamIb2[2] / slabIb2[2];
				beama[3] = beamIb2[3] / slabIb2[3];
				break;
			case 9:
				beama[0] = beamIb2[0] / slabIb2[0];
				beama[1] = beamIb2[1] / slabIb2[1];
				beama[2] = beamIb1[2] / slabIb1[2];
				beama[3] = beamIb2[3] / slabIb2[3];
				break;
			default:
				break;
			}

			double anga = (beama[0] + beama[1] + beama[2] + beama[3]) / 4;
			System.out.println(beama[0]);
			System.out.println(beama[1]);
			System.out.println(beama[2]);
			System.out.println(beama[3]);
			// System.out.println("a평균값" + anga);

			if (anga > 2) {
				double minH = ((1000 * slabspany) - (b3 / 2) - (b4 / 2)) * (800 + (slabrebar1 / 1.4))
						/ (36000 + 9000 * ratioBH);
				slabThickness1.setText("" + slabthk);
				slabThickness2.setText("" + Math.round(minH * 10) / 10.0);
				double ratioThk = minH / slabthk;
				slabThickness3.setText("" + Math.round(ratioThk * 100) / 100.0);
			}
		} else {
			System.out.println("1~2way방식 잘못됨.");
		}
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
	private Label slabFy1;

	@FXML
	private Label slabFy2;

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
