package DogHouse;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class javaFXPane extends Application {
	private static MediaPlayer mediaPlayer;
	private static MediaPlayer cuteBarking;
	private static MediaPlayer dogPanting;
	private static MediaPlayer mediaPlayerHeater;
	private static MediaPlayer mediaPlayerHeaterNext;
	private static MediaPlayer tooHotWarning;
	private static MediaPlayer tooColdWarning;
	private TableView<Dog> table = new TableView<Dog>();
	private ObservableList<Dog> data = FXCollections.observableArrayList();
	ArrayList<String> history = new ArrayList<>(20);
	public Button backToUser = new Button("Main Menu");
	int x = 0; //variable to track pet x position
	int y = 0;//variable to track pet's y position
	int a = 250;//pet size
	int b = 250;//pet size
   
	@Override
	public void start(Stage stage) throws Exception {
			//class to register dog position
		
		    
		    DistanceFromHouse dog = new DistanceFromHouse(); 
	        ImageView pet = new ImageView();
	        ImageView dogHouse = new ImageView();
	        ImageView tennisBall = new ImageView();
	        FetchBall TBall = new FetchBall();
	        Button viewInfor = new Button("Database");
	        viewInfor.setStyle("-fx-background-color: Black");
	        viewInfor.setTextFill(Color.WHITE);
	       
	        
	        backToUser.setStyle("-fx-background-color: Black");
	        backToUser.setTextFill(Color.WHITE);
	        backToUser.setOnAction(event->{
	        	UserInputPane us = new UserInputPane();
	        	Stage userStage = new Stage();
	        	try {
	        		us.start(userStage);
	        		stage.close();
	        		mediaPlayer.stop();
	        		cuteBarking.stop();
	        	}catch (Exception e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
	        });
	        viewInfor.setOnAction(e->{
	        	  data.clear();
	   
	        	  String url = "jdbc:mysql://localhost/cs3220stu55?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		          String username = "cs3220stu55";
		          String password = "zWpa0NxR";
		          		         
		          
		              
				  try {
					  Connection c = DriverManager
			                  .getConnection( url, username, password );
			              Statement stmt = c.createStatement();
			          
			            ResultSet rs = stmt.executeQuery( "select * from pet" );
			 
			            while( rs.next() )
			            {
			               LocalDate today = rs.getDate("birthday").toLocalDate();
			               LocalDate tomorrow = today.plusDays(1);
			               data.add(new Dog(rs.getString("name"),rs.getString("breed"),
			            		   rs.getString("gender"),rs.getString("furColor"),rs.getInt("weight"),rs.getInt("height"),
			            		   tomorrow,rs.getBoolean("neutorSpay"),rs.getBoolean("microchipped"),rs.getString("userEmail")));
			                
			            }
			            
			            c.close();
			           
			 
			              				 
			         
			        
			        } catch (Exception exception) {
			            exception.printStackTrace();
			            //System.out.println("Error on Building Data");
			        }
				table.getColumns().clear();
			    
				
				Stage fifthStage = new Stage();

				Scene scene = new Scene(new Group());
				
				final Label label = new Label("Dog Information");
		        label.setFont(new Font("Arial", 20));
		 
		        table.setEditable(true);
		 
		        TableColumn NameCol = new TableColumn("name");
		        NameCol.setMaxWidth(100);
		        NameCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, String>("name"));
		 
		        TableColumn breedCol = new TableColumn("breed");
		        breedCol.setMaxWidth(200);
		        breedCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, String>("breed"));
		 
		        TableColumn genderCol = new TableColumn("gender");
		        genderCol.setMaxWidth(100);
		        genderCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, String>("gender"));
		        
		        TableColumn furColorCol = new TableColumn("furColor");
		        furColorCol.setMaxWidth(100);
		        furColorCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, String>("furColor"));
		        
		        TableColumn weightCol = new TableColumn("weight");
		        weightCol.setMaxWidth(100);
		        weightCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, Integer>("weight"));
		        
		        TableColumn heightCol = new TableColumn("height");
		        heightCol.setMaxWidth(100);
		        heightCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, Integer>("height"));
		        
		        TableColumn birthdayCol = new TableColumn("birthday");
		        birthdayCol.setMaxWidth(100);
		        birthdayCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, String>("birthday"));
		        
		        TableColumn spayCol = new TableColumn("neuterSpay");
		        spayCol.setMaxWidth(100);
		        spayCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, Boolean>("neuterSpay"));
		        
		        TableColumn microchippedCol = new TableColumn("microchip");
		        microchippedCol.setMaxWidth(100);
		        microchippedCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, Boolean>("microchip"));
		        
		        TableColumn userEmailCol = new TableColumn("User Email");
		        userEmailCol.setMaxWidth(100);
		        userEmailCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, String>("userEmail"));
		        
		        
		        
		      //  ObservableList<Dog> list = FXCollections.observableArrayList(dogInformation);
                ObservableList<Dog> list = removeDuplicates(data);
		        table.setItems(list);
		        table.getColumns().addAll(NameCol, breedCol, genderCol,furColorCol, weightCol,
		        		     heightCol, birthdayCol, spayCol, microchippedCol, userEmailCol);
		        
		        
		        Button clear = new Button("Clear");
		        
		        clear.setOnAction(e1->{
		        	UserInputPane.dogInformation.clear();
    				Connection conn = null;
					Statement stmte = null;
						
					try{
						     
						      Class.forName("com.mysql.cj.jdbc.Driver");

						      conn = DriverManager.getConnection(url, username, password);
						     
						      stmte = conn.createStatement();
						      
						      String sql = "DELETE FROM pet;";
						      
						      stmte.executeUpdate(sql);
						 }catch(Exception exc) {
							 exc.printStackTrace();
						 }
					
		       refreshTable();
		        
		        });
		       
		        final VBox vbox = new VBox();
		        vbox.setSpacing(5);
		        vbox.setPadding(new Insets(10, 0, 0, 10));
		        vbox.getChildren().addAll(label, table,clear);
		 
		        ((Group) scene.getRoot()).getChildren().addAll(vbox);
						
				fifthStage.setScene(scene);
				fifthStage.show();
				
				
	        });

	         
	        //pet properties
            pet.setImage(new Image("dog.gif"));
            pet.setFitHeight(a);
            pet.setFitWidth(b);
	       //pet coordinates
            pet.setX(0);
            pet.setY(0);
            pet.setOnMouseDragged(e -> {
                pet.setX(e.getX());
                pet.setY(e.getY());
            });
            //dog house image
            dogHouse.setImage(new Image("dogHouse.png"));
            dogHouse.setFitHeight(280);
            dogHouse.setFitWidth(400);
           
          //tennis ball
    		tennisBall.setImage(new Image("tennisBall.jpg"));
    		TBall.setTennisBallDistance();
    		tennisBall.setX( TBall.getTennisBallDistance() );
    		tennisBall.setFitHeight(40);
    		tennisBall.setFitWidth(40);
            
	        GridPane pane = new GridPane();
	       
	        
	        pane.setHgap(10);
	        pane.setVgap(10);
	        pane.add(dogHouse, 0, 19);
	         Button viewRecord = new Button("ViewRec");
	         viewRecord.setStyle("-fx-background-color: Black");
	         viewRecord.setTextFill(Color.WHITE);
	         
	        //buttons to move pet
	         Button btCheckDistance = new Button("CheckDis");
	         btCheckDistance.setStyle("-fx-background-color: Black");
	         btCheckDistance.setTextFill(Color.WHITE);
	 
	         Button btMoveFordward = new Button("Forward");
	         btMoveFordward.setStyle("-fx-background-color: Black");
	         btMoveFordward.setTextFill(Color.WHITE);
	 
	         Button btMoveBackward = new Button("backward");
	         btMoveBackward.setStyle("-fx-background-color: Black");
	         btMoveBackward.setTextFill(Color.WHITE);
	         
	         Button btUpward = new Button ("Upward");
	         btUpward.setStyle("-fx-background-color: Black");
	         btUpward.setTextFill(Color.WHITE);
	         
	         Button btDownward = new Button("Downward");
	         btDownward.setStyle("-fx-background-color: Black");
	         btDownward.setTextFill(Color.WHITE);
	         
	         Button throwBall = new Button("Throw Tennis Ball");
	         throwBall.setStyle("-fx-background-color: Black");
	         throwBall.setTextFill(Color.WHITE);
	         /*Button btTotalDistance = new Button("Total Distance");*/
	         
	        throwBall.setDisable(true);
	 		throwBall.setOnAction(e->{
	 			TBall.setTennisBallDistance();
	 			tennisBall.setX( TBall.getTennisBallDistance() );
	 			tennisBall.setVisible(true);
	 			throwBall.setDisable(true);
	 		});

	         
	         viewRecord.setOnAction(e->{
	        	
	        	Button button = new Button("Back");
	 			GridPane recordPane = new GridPane();
	 			Stage recordStage = new Stage();
	 			int i = 0;
	 			for(@SuppressWarnings("unused") String str : history){
	 				Text label = new Text(history.get(i));
	 				label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	 				label.setFill(Color.BLACK);
	 				label.setStyle("-fx-background-color: coral;");
	 				recordPane.add(label, 1, i + 1);
	 				i++;
	 			}
	 			button.setOnAction(en->{
	 				recordStage.close();
	 			});
	 			recordPane.setVgap(10);
	 			recordPane.setHgap(10);
	 			recordPane.add(button, 1, 0);
	 			recordStage.setScene(new Scene(recordPane, 600, 400));

	 			recordStage.show();
	        	 
	         });
	        //buttons Action Event
	         
	         //check distance button
	         btCheckDistance.setOnAction( e ->
	         {

	             
	             ////////////////////////////////UPDATE///////////////////////////////////////////////
	             dog.totalDistance();
	  
	             Alert alert = new Alert(AlertType.INFORMATION);
	             alert.setTitle("pet's distance");
	             alert.setHeaderText(null);
	  
	             if(dog.total>0)
	             {
	                 alert.setContentText("The dog is now: "+dog.total +" feet from the dog house.");
	             }
	             else if(dog.total==0&&!(0<pet.getTranslateX()&&pet.getTranslateX()<dogHouse.getFitWidth()))
	             {
	                 alert.setContentText("The dog is now: 0 feet from the dog house.");
	             }
	             else
	             {
	  
	                 double distance = dog.total*(-1);
	                
	                
	                 alert.setContentText("The dog is now: "+  distance  +" feet from the dog house.");
	             }
	  
	  
	  
	             alert.showAndWait();
	             ///////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
	         
	         });
	         //forward button
	         btMoveFordward.setOnAction((e) ->
	         {
	        	 pet.setScaleX(1);
	 			x -= 40;
	 			pet.setTranslateX(x);
	 			dog.moveForward();
	 			if(dog.distance == 0){
	 				btMoveFordward.setDisable(true);
	 				if(TBall.doesDogHasBall()){
	 					TBall.setDogHasBall(false);
	 					TBall.setBallPresent(true);
	 					throwBall.setDisable(false);
	 				}
	 			}
	 			if(dog.distance < 39){
	 				btMoveBackward.setDisable(false);
	 			}
	 			if(dog.distance == TBall.getTennisBallDistance() && dog.distanceV == 0){
	 				tennisBall.setVisible(false);
	 				TBall.setDogHasBall(true);
	 			}}
	        		 );
	         //backward button
	         btMoveBackward.setOnAction(e ->
	         {
	        	 pet.setScaleX(-1);
	        	 x += 40;
	 			pet.setTranslateX(x);
	 			dog.moveBackward();
	 			if(dog.distance > 0){
	 				btMoveFordward.setDisable(false);
	 			}
	 			if(dog.distance > 39){
	 				btMoveBackward.setDisable(true);
	 				history.add("Your dog is unusually far away! at " + new Date());
	 			}
	 			if(dog.distance == TBall.getTennisBallDistance() && dog.distanceV == 0){
	 				tennisBall.setVisible(false);
	 				TBall.setDogHasBall(true);
	 			}});
	 
	         btUpward.setOnAction(e ->
	         {
	             y -= 40;
	             pet.setTranslateY(y);
	             a -= 25;
	             b -= 25;
	             pet.setFitHeight(a);
	             pet.setFitWidth(b);
	             dog.Upward();
	             if(dog.distanceV > 0){btDownward.setDisable(false);}
	             if(dog.distanceV > 20) {btUpward.setDisable(true);}

	         });
	         btDownward.setDisable(true);  
	         btDownward.setOnAction(e ->
	         {
	        	 y += 40;
	             pet.setTranslateY(y);
	             a += 25;
	             b += 25;
	             pet.setFitHeight(a);
	             pet.setFitWidth(b);
	             dog.Downward();
	             if(dog.distanceV == 0) {btDownward.setDisable(true);}   //////////UPDATE/////////////////////////////
	             if(dog.distanceV < 22) {btUpward.setDisable(false);}    ///////////UPDATE///////////////////////////
	         });
	         
	         
	         
	         
	  
	       ////////////////////////////Temperature buttons and property//////////////////////////////////////////////////////////
	      
	        Temperature temp = new Temperature();
	        Text label = new Text("The current temperature: " + temp.GetTemperature());
	   
	        
	        /******           UPDATES           ******/
	        label.setFont(Font.font("Arial", FontWeight.BOLD, 15));
	        label.setFill(Color.BLACK);
	        label.setStyle("-fx-background-color: coral;");
	        
	        /******           UPDATES           ******/
	        Button tempUp = new Button("Up"); 
	        tempUp.setStyle("-fx-background-color: Black");
	        tempUp.setTextFill(Color.WHITE);
	        Button tempDown = new Button("Down");     
	        tempDown.setStyle("-fx-background-color: Black");
	        tempDown.setTextFill(Color.WHITE);
	        Button setOptimalTemperature = new Button("Optimal");
	        setOptimalTemperature.setStyle("-fx-background-color: Black");
	        setOptimalTemperature.setTextFill(Color.WHITE);
	        
	        tempUp.setOnAction(e ->{
	        	temp.SetTemperatureUp();
	        	label.setText("The current temperature: " + temp.GetTemp());
//	       
	        	if(temp.GetTemp() >= temp.GetOptimalTemp() + 10) {	
	        		 final URL toohotAlert = getClass().getResource("TooHotAlert.mp3");
	       	         final Media tooHotAlert = new Media(toohotAlert.toString());
	       	         tooHotWarning = new MediaPlayer(tooHotAlert);
	       	         tooHotWarning.play();
	       	         mediaPlayer.setVolume(mediaPlayer.getVolume()/30);
	        		Alert alert = new Alert(AlertType.INFORMATION);
	        		alert.setTitle("AC is on for your pet!");
	        		alert.setHeaderText(null);
	        		alert.setContentText("The air conditioning is on for your pet!");

	        		alert.showAndWait();
	        	        
	        		    mediaPlayer.setVolume(mediaPlayer.getVolume()*30);
	        		    temp.SetCurrentTemp(temp.GetOptimalTemp());
		        		 Button button = new Button("Back");
		        		 button.setStyle("-fx-background-color: YELLOW");
		        		 GridPane pane1 = new GridPane();
		    		      Stage stage1 = new Stage();
		        		  pane1.setStyle(  "-fx-background-image: url(" +
		        	                "'room.jpg'" +
		        	                "); " +
		        	                "-fx-background-size: cover;");
		        		  if(dog.distance==0) {
		        		   ImageView ima = new ImageView();
		        		   ima.setImage(new Image("dog hot.gif"));
		        		   ima.setFitHeight(350);
		        		   ima.setFitWidth(350);
		        		   pane1.add(ima, 7, 20);}
		        		   ImageView AC = new ImageView();
		        		   AC.setImage(new Image("AC.gif"));
		        		   AC.setFitHeight(100);
		        		   AC.setFitWidth(150);
		                   button.setOnAction(en->{
		                	   stage1.close();
		                	   dogPanting.stop();
		                	   mediaPlayer.play();
		                	   label.setText("The current temperature: " + temp.GetTemp());
		                	   stage.show();
		                	  
		                   });
		        		   pane1.setVgap(10);
		        		   pane1.setHgap(10);
		        		   pane1.add(button, 1, 1);		        		  
		        		   pane1.add(AC, 0, 1);
		        		   
		        		   stage1.setScene(new Scene(pane1,800,800));
		        		   stage1.show();
		        		   mediaPlayer.stop();
		        		   final URL dogPantingResource = getClass().getResource("dogPanting.mp3");
		       	           final Media dogPantingMedia = new Media(dogPantingResource.toString());
		       	           dogPanting = new MediaPlayer(dogPantingMedia);
		       	           dogPanting.setOnEndOfMedia(new Runnable() {
		    	            public void run() {
		    	              dogPanting.seek(Duration.ZERO);
		    	            }
		    	        });
		    	     
		    	           dogPanting.play();
			               stage.close();
	        		}
	 	         	        	
	        	 
	 	       		
	        });
	        tempDown.setOnAction(e->{
	        	temp.SetTemperatureDown();
	        	
	        	label.setText("The current temperature: " + temp.GetTemp());
	
	        	
	        	 if(temp.GetTemp()<= temp.GetOptimalTemp()-10) {
	        		 final URL toocoldAlert = getClass().getResource("TooColdAlert.mp3");
	       	         final Media tooColdAlert = new Media(toocoldAlert.toString());
	       	         tooColdWarning = new MediaPlayer(tooColdAlert);
	       	         tooColdWarning.play();
	       	         mediaPlayer.setVolume(mediaPlayer.getVolume()/30);
	        		 
	        			Alert alert = new Alert(AlertType.INFORMATION);
		        		alert.setTitle("heater is on for your pet!");
		        		alert.setHeaderText(null);
		        		alert.setContentText("The heater is on for your pet!");

		        		alert.showAndWait();
		        		
		        		 mediaPlayer.setVolume(mediaPlayer.getVolume()*30);
		        		 
			        		 Button button1 = new Button("Back");
			        		 GridPane pane2 = new GridPane();
			    		      Stage stage2 = new Stage();
			        		  pane2.setStyle(  "-fx-background-image: url(" +
			        	                "'room.jpg'" +
			        	                "); " +
			        	                "-fx-background-size: cover;");
			        		  
			        		  if(dog.distance==0) {
			        		   ImageView imCold = new ImageView();
			        		   imCold.setImage(new Image("dogHappy.gif"));
			        		   imCold.setFitHeight(350);
			        		   imCold.setFitWidth(350);
			        		   pane2.add(imCold, 7, 30);
			        		  }
			        		   ImageView heater = new ImageView();
			        		   heater.setImage(new Image("heater.gif"));
			        		   heater.setFitHeight(100);
			        		   heater.setFitWidth(150);
			                   button1.setOnAction(en->{
			                	   temp.SetCurrentTemp(temp.GetOptimalTemp());
			                	   stage2.close();
			                	   mediaPlayerHeaterNext.stop();
			                	   label.setText("The current temperature: " + temp.GetTemp());
			                	   stage.show();
			                	 
			                	   mediaPlayer.play();
			                	   cuteBarking.play();
			                	 
			                   });
			        		   pane2.setVgap(10);
			        		   pane2.setHgap(10);
			        		   pane2.add(button1, 1, 1);
			        		   
			        		   pane2.add(heater, 0, 1);
			        		   
			        		   stage2.setScene(new Scene(pane2,800,800));
			        		   stage2.show();
			        		   mediaPlayer.stop();
			        		   final URL resource = getClass().getResource("dogHappySound.mp3");
			       	           final Media media1 = new Media(resource.toString());
			       	           final URL resource1 = getClass().getResource("Calming-harp-music.mp3");
			       	           final Media media2 = new Media(resource1.toString());
			       	           mediaPlayerHeaterNext = new MediaPlayer(media2);
			        	       mediaPlayerHeater = new MediaPlayer(media1); 
			        	       mediaPlayerHeater.play();
			        	       mediaPlayerHeaterNext.play();
				               stage.close();
		        		}
		        	
						
		 	        	
		 	        
		        	        
	        });
	        setOptimalTemperature.setOnAction(e ->{
                TextInputDialog dialog = new TextInputDialog("70");
                dialog.setTitle("Optimal Temperature");
                dialog.setHeaderText("What is your optimal temperature?");
                dialog.setContentText("Enter Optimal Temperature:");
 
                // Traditional way to get the response value.
                Optional<String> temperature = dialog.showAndWait();
                if (temperature.isPresent()){
                	
                    Temperature.SetOptimalTemp(Integer.parseInt(temperature.get()));
                    label.setText("The current temperature: " + temp.GetOptimalTemp());
                 
                }

              
            });
         
	      
	      
	              
	              
	     
	        String dogFood = "dogFood.jpeg";
	        String dogWater = "15-154507_cartoon-dog-water-bowl.png";
	        ImageView imFood = new ImageView();
	        ImageView imWater = new ImageView();
	        DateFormat df = new SimpleDateFormat("EEE,MMM d yyyy - h:mm:ss a");
            Date date = new Date();
            String stringDate = df.format(date);
            Text timing = new Text(10, 60, stringDate);
            
            /******           UPDATES           ******/
            
            timing.setFont(Font.font ("Arial",FontWeight.BOLD, 15));
            timing.setFill(Color.BLACK);
	    	Button timeUp = new Button("Up"); 
	    	timeUp.setStyle("-fx-background-color: Black");
	    	timeUp.setTextFill(Color.WHITE);
	    	Button timeDo = new Button("Down");
	    	timeDo.setStyle("-fx-background-color: Black");
	    	timeDo.setTextFill(Color.WHITE);
	    	Button PrefFeeding = new Button("Set");
	    	PrefFeeding.setStyle("-fx-background-color: Black");
	    	PrefFeeding.setTextFill(Color.WHITE);

	    	 Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
	    		        new EventHandler<ActionEvent>() {

	    		            @Override
	    		            public void handle(ActionEvent actionEvent) {
	    		       
	    		              Date update = new Date();	    	
	    		              String stringNewDate = df.format(update);
	    		              timing.setText(stringNewDate);
	    		           
	    		              
	    		            }
	    		        }

	    		        ), new KeyFrame(Duration.seconds(1)));
	    		              timeline.setCycleCount(Animation.INDEFINITE);
	    		        timeline.play(); 
	    	timeUp.setOnAction(e->{
	    		timeline.stop();
	    		int minutes = date.getMinutes();
	    	     
	    		date.setMinutes(minutes+=1);
	    		DateFormat df1 = new SimpleDateFormat("EEE,MMM d yyyy - h:mm a");
	    		String newDate = df1.format(date);
	    		    			    	
	    		timing.setText(newDate);
	    		
	    		//if the adjusted time equals current time, the timeline will play
	    		
	    		Date curTime = new Date();
	    		if(date.getMinutes()==curTime.getMinutes()) {
	    			timeline.play();
	    		}
	    			

	    	});
	    	timeDo.setOnAction(e->{
	    		timeline.stop();
	    		int minutes = date.getMinutes();
	    	     
	    		date.setMinutes(minutes-=1);
	    		 DateFormat df1 = new SimpleDateFormat("EEE,MMM d yyyy - h:mm a");
	    		String newDate = df1.format(date);
	    		    			    	
	    		timing.setText(newDate);
	    		
	    		Date curTime = new Date();
	    		if(date.getMinutes()==curTime.getMinutes()) {
	    			timeline.play();
	    		}

	    	});
	    	//////////////////////////////objects added to pane//////////////////////////////////////////////////////////
	    	
	    	 /******           UPDATES           ******/
	    	
	    	HBox tem = new HBox();
	    	HBox tim = new HBox();
	    	HBox h = new HBox();
	    	h.setSpacing(5);
	    	VBox tempAll = new VBox();
//	    	 pane.add(viewInfor, 3, 0);
//		        pane.add(throwBall, 3, 1);
//		        pane.add(backToUser, 4, 0);
	    	//tempAll.setSpacing(5);
	    	StackPane stack = new StackPane();
	    	StackPane stackTime = new StackPane();
	    	Rectangle rec = new Rectangle();
	    	Rectangle rec1 = new Rectangle();
	    	rec.setFill(Color.WHITE);
	    	rec.setStroke(Color.BLACK);
	    	rec.setWidth(220);
	    	rec.setHeight(40);
	    	
	    	rec1.setFill(Color.WHITE);
	    	rec1.setStroke(Color.BLACK);
	    	rec1.setWidth(220);
	    	rec1.setHeight(40);
	    	stack.getChildren().add(rec);
	    	stack.getChildren().add(label);
	    	stackTime.getChildren().add(rec1);
	    	stackTime.getChildren().add(timing);
	    	tem.getChildren().addAll(tempUp,tempDown,setOptimalTemperature,stack);
	    	h.getChildren().addAll(viewInfor,throwBall,backToUser);
	     //tem.setPadding(new Insets(10,10,10,10));
	    	tem.setSpacing(5);
	    	tim.setSpacing(5);
	    	tim.getChildren().addAll(timeUp,timeDo,PrefFeeding,stackTime);
	
//	    	pane.add(timeUp, 2, 0);
//	    	pane.add(timeDo, 2, 1);
//	    	  pane.add(tempUp, 0, 0);
//		        pane.add(tempDown, 0, 1);
		       // pane.add(setOptimalTemperature, 0, 2);
	    	pane.add(tem, 0, 0);
		   // pane.add(tempAll, 0, 1);
		    pane.add(tim, 0, 1);
		    pane.add(h, 0, 2);
	     //   pane.add(timing, 2, 2);
	        
	       
	        pane.add(btMoveBackward,0, 15);
	        pane.add(btMoveFordward, 0, 16);
            pane.add(btCheckDistance, 0, 17);
            pane.add(viewRecord,0, 18);
            pane.add(btUpward,0,13);
           
            pane.add(tennisBall, 9, 19);
           // pane.setGridLinesVisible(true);
            pane.add(btDownward,0,14);
            /*pane.add(btTotalDistance, 1, 18);*/
            pane.add(pet,2,19);
            
	        /////////////////////////////////dog at pane:default (4,19)/////////////////////////////////////////////////
            pane.setStyle( "-fx-background-image: url(" +
                "'backyard.jpg'" +
            "); " +
            "-fx-background-size: cover;");
	       
            
	        stage.setScene(new Scene(pane, 925, 1500));/////////////////////////default(pane,900,1500)
	        final URL resource = getClass().getResource("FunnyDay.mp3");
	        final Media media = new Media(resource.toString());
	        final URL cuteBarkingResource = getClass().getResource("dogCuteBarking.mp3");
	        final Media cuteBarkingMedia = new Media(cuteBarkingResource.toString());
	        cuteBarking= new MediaPlayer(cuteBarkingMedia);
	        cuteBarking.play();
	        mediaPlayer = new MediaPlayer(media);
	        mediaPlayer.setOnEndOfMedia(new Runnable() {
	            public void run() {
	              mediaPlayer.seek(Duration.ZERO);
	            }
	        });
	        
	        mediaPlayer.play();
    
	        stage.show();
	}
	public static void main(String[] args) {
        launch(args);
    }
	public void refreshTable() {
		data.clear();
		 String url = "jdbc:mysql://localhost/cs3220stu55?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
         String username = "cs3220stu55";
         String password = "zWpa0NxR";
          
        
         
             
		  try {
			  Connection c = DriverManager
	                  .getConnection( url, username, password );
	              Statement stmt = c.createStatement();
	          
	            ResultSet rs = stmt.executeQuery( "select * from pet" );
	 
	            while( rs.next() )
	            {
	               LocalDate today = rs.getDate("birthday").toLocalDate();
	               LocalDate tomorrow = today.plusDays(1);
	               data.add(new Dog(rs.getString("name"),rs.getString("breed"),
	            		   rs.getString("gender"),rs.getString("furColor"),rs.getInt("weight"),rs.getInt("height"),
	            		   tomorrow,rs.getBoolean("neutorSpay"),rs.getBoolean("microchipped"),rs.getString("userEmail")));
	                
	            }

	            c.close();
	           
	 
	              				 
	         
	        
	        } catch (Exception exception) {
	            exception.printStackTrace();
	            //System.out.println("Error on Building Data");
	        }
		  
		  table.setItems(data);
		
	}
	
	
	static ObservableList<Dog> removeDuplicates(ObservableList<Dog> list) {

		    HashSet <Dog> h = new HashSet<Dog>(list);
		    list.clear();
		    list.addAll(h);
		    return list;
		  }

	 }