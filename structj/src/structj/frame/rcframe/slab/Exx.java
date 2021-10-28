package structj.frame.rcframe.slab;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Exx extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(final Stage primaryStage) {
		primaryStage.setTitle("");
		Group root = new Group();
		Scene scene = new Scene(root, 400, 300, Color.WHITE);

		GridPane gridpane = new GridPane();

		ComboBox<Color> cmb = new ComboBox<Color>();

		cmb.getItems().addAll(Color.RED, Color.GREEN, Color.BLUE);

		cmb.setCellFactory(new Callback<ListView<Color>, ListCell<Color>>() {
			@Override
			public ListCell<Color> call(ListView<Color> p) {
				return new ListCell<Color>() {
					private final Rectangle rectangle;
					{
						setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						rectangle = new Rectangle(20, 10);
						Text t = new Text();
						t.setFont(new Font(20));
						t.setWrappingWidth(200);
						t.setText("123");
					}

					@Override
					protected void updateItem(Color item, boolean empty) {
						super.updateItem(item, empty);

						if (item == null || empty) {
							setGraphic(null);
						} else {
							rectangle.setFill(item);
							setGraphic(rectangle);
							setText("123");
						}
					}
				};
			}
		});

		gridpane.add(cmb, 3, 0);

		root.getChildren().add(gridpane);
		primaryStage.setScene(scene);

		primaryStage.show();
	}

}
