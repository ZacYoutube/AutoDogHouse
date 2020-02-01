package DogHouse;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class insideDogHouse extends Application {
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane gridpane = new GridPane();
		Image dog = new Image("dog.gif");
		ImageView dogImage = new ImageView();
		dogImage.setImage(dog);
		dogImage.setFitHeight(250);
		dogImage.setFitWidth(250);
		gridpane.setStyle(  "-fx-background-image: url(" +
                "'room.jpg'" +
                "); " +
                "-fx-background-size: cover;");
		gridpane.add(dogImage, 7, 600);
		primaryStage.setScene(new Scene(gridpane,800,800));
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		Application.launch(args);
	}

}
