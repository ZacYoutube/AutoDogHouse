package DogHouse;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserInputPane extends Application {

	private static MediaPlayer welcomeMessage;
	private static MediaPlayer welcomeBGM;
	private static MediaPlayer userInputSpeak;
	private static MediaPlayer informationSaved;
	private static MediaPlayer readyed;
	private static MediaPlayer error;
	private static MediaPlayer saved;
	private static MediaPlayer deleted;
	private static MediaPlayer SpecifyBreed;
	
	public static TableView<Dog> table = new TableView<Dog>();
	
	public static TableView<Dog> table1 = new TableView<Dog>();
	
	public static ArrayList<Dog> dogInformation = new ArrayList<>();
	
	public boolean unique = true;
	
	public Dog dog;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/***********Style the first table displaying dog information.*********/
		
		table.setStyle("-fx-selection-bar: black; -fx-selection-bar-non-focused: black;"
				+ "-fx-background-color: black");
		
		/////////////////////First Pane That Has Dog House Logo/////////////////////
	
		BorderPane border = new BorderPane();
		border.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		border.setPadding(new Insets(25, 25, 25, 25));
		
		/////////////////////Buttons for first pane /////////////////////
		
		Button button = new Button("OK");
		Button backToJavaFXPane = new Button("Back");
		
        /////////////////////Buttons needed after entering the information/////////////////////
		
		Button ready = new Button("Ready");
		Button notReady = new Button("Clear");
		Button viewInfor = new Button("View");
		Button AddPet = new Button("Add");
		
	    /////////////////////Buttons for entering the information /////////////////////
		
		Button save = new Button("Save");
		Button proceed = new Button("Proceed");
		Button back = new Button("Back");
		Button backToPrimary = new Button("Back");
		Button viewDatabase = new Button("View");
	
		button.setTextFill(Color.WHITE);
		button.setStyle("-fx-background-color: Black");
		button.setOnAction(e->{
			
			 /////////////////////Entering Dog information  /////////////////////
			
			primaryStage.close();
			welcomeMessage.stop();
			 final URL resource2 = getClass().getResource("userInput.mp3");
		     final Media media2 = new Media(resource2.toString());
		     userInputSpeak = new MediaPlayer(media2);
		     userInputSpeak.play();
			Stage secondaryStage = new Stage();
			GridPane pane = new GridPane();
			
			pane.setAlignment(Pos.CENTER);
			pane.setHgap(5);
			pane.setVgap(5);
			pane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			//The user input fields
			Label name = new Label("name: ");
			name.setFont(new Font("Times New Roman",17));
			pane.add(name, 0, 1);
			TextField nameText = new TextField();
			nameText.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			pane.add(nameText, 1 ,1);
			
			
			Label breed = new Label("breed: ");
			breed.setFont(new Font("Times New Roman",17));
			pane.add(breed, 0, 2);
			ChoiceBox<String> breedChoice = new ChoiceBox<String>(FXCollections.observableArrayList(
				    "Retrievers (Labrador)	", "German Shepherds", "Retrievers (Golden)	","French Bulldogs	"
				    ,"Bulldogs","Beagles","Poodles","Rottweilers","Yorkshire Terriers","Pointers (German Shorthaired)"
				    , "Boxers","Siberian Huskies", "Great Danes","Pomeranians","Pugs","Huskies","Chihuahua"
				    , "Corgis","Doberman Pinschers","Shih Tzu","Boston Terriers","Havanese","Shetland Sheepdogs","Maltese"
				    ,"Weimaraners","Collies","Shiba Inu","Retrievers (Chesapeake Bay)","Akitas","Papillons","Dalmatians"
				    ,"Italian Greyhounds","Mixed","Other")
				);
			breedChoice.setStyle("-fx-background-color: WHITE");
			breedChoice.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			breedChoice.getSelectionModel().select(0);
			pane.add(breedChoice, 1, 2);
			
			
			Label gender = new Label("gender: ");
			gender.setFont(new Font("Times New Roman",17));
			pane.add(gender, 0, 3);
			ChoiceBox<String> genderChoice= new ChoiceBox<>();
			genderChoice.setStyle("-fx-background-color: WHITE");
			genderChoice.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			genderChoice.getItems().addAll("Male", "Female");
			genderChoice.getSelectionModel().select(0);
			pane.add(genderChoice, 1 ,3);
			
			
			Label furColor = new Label("fur color: ");
			furColor.setFont(new Font("Times New Roman",17));
			pane.add(furColor, 0, 4);
			ChoiceBox<String> furColorChoice= new ChoiceBox<>();
			furColorChoice.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			furColorChoice.setStyle("-fx-background-color: WHITE");
			furColorChoice.getItems().addAll("Brown", "Red","Gold","Yellow","Cream","Blue","Gray","Two color striped","Three color striped","Dotted","Beige","Tan","Orange","Light Brown"
					                         ,"Dark Brown","Apricot","Fawn","Chestnut","Black","Rust","Wheaten","White","Other");
			furColorChoice.getSelectionModel().select(0);
			pane.add(furColorChoice, 1, 4);
			
			
			Label weight = new Label("weight(in lb): ");
			weight.setFont(new Font("Times New Roman",17));
			pane.add(weight, 0, 5);
			TextField weightText = new TextField();
			weightText.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			pane.add(weightText, 1 ,5);
			
			
			Label height = new Label("height(in cm): ");
			height.setFont(new Font("Times New Roman",17));
			pane.add(height, 0, 6);
			TextField heightText = new TextField();
			heightText.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			pane.add(heightText, 1, 6);
			
			
			Label birthday = new Label("birthday: ");
			birthday.setFont(new Font("Times New Roman",17));
			pane.add(birthday, 0, 7);
			DatePicker datePicker = new DatePicker();
			datePicker.setStyle("-fx-control-inner-background: WHITE;");
			datePicker.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			pane.add(datePicker, 1, 7);
			
			
			Label neutorSpay= new Label("Is your pet neutored or spayed? ");
			neutorSpay.setFont(new Font("Times New Roman",17));
			pane.add(neutorSpay, 0, 8);
			ChoiceBox<String> neutorSpayChoice = new ChoiceBox<String>(FXCollections.observableArrayList(
				    "Yes", "No")
				);
			neutorSpayChoice.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			neutorSpayChoice.setStyle("-fx-background-color: WHITE");
			neutorSpayChoice.getSelectionModel().select(0);
			pane.add(neutorSpayChoice, 1 ,8);
			
			
			Label microchipped = new Label("Does your pet have microchip? ");
			microchipped.setFont(new Font("Times New Roman",17));
			pane.add(microchipped, 0, 9);
			ChoiceBox<String> microchippedChoice= new ChoiceBox<>();
			microchippedChoice.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			microchippedChoice.setStyle("-fx-background-color: WHITE");
			microchippedChoice.getItems().addAll("Yes","No");
			microchippedChoice.getSelectionModel().select(0);
			pane.add(microchippedChoice, 1, 9);
			
			
			Label userEmail = new Label("Email: ");
			userEmail.setFont(new Font("Times New Roman",17));
			pane.add(userEmail, 0, 10);
			TextField userEmailText = new TextField();
			userEmailText.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
			pane.add(userEmailText, 1 ,10);

           
			save.setOnAction(e3->{
				
				 /////////////////////Save the information  /////////////////////
				
				    String option = genderChoice.getValue().toString();
		            String neutorSpayOption = neutorSpayChoice.getValue().toString();
		            String breedOption = breedChoice.getValue().toString();
		            String furColorOption = furColorChoice.getValue().toString();
		            String microchippedOption = microchippedChoice.getValue().toString();
		            LocalDate value = datePicker.getValue();
				    String breedSpecify = "";
				    String furColorSpecify = "";
				    
				    
		            if(!nameText.getText().trim().isEmpty()&&!option.trim().isEmpty() && 
		            		!neutorSpayOption.trim().isEmpty()&& !breedOption.trim().isEmpty()
		            		&&!furColorOption.trim().isEmpty()&& !microchippedOption.trim().isEmpty()
		            		&&value!=null&&!weightText.getText().trim().isEmpty()&&!heightText.getText().trim().isEmpty()
		                    &&!userEmailText.getText().trim().isEmpty()
		            		) {    
				   
		        ///////////////////// Specify when choose "Other" /////////////////////     	
		            	
			   if(breedOption.equalsIgnoreCase("Other") || breedOption.equalsIgnoreCase("Mixed")) {
				     userInputSpeak.stop();
				     final URL resource = getClass().getResource("SpecifyBreed.mp3");
				     final Media media = new Media(resource.toString());
				     SpecifyBreed = new MediaPlayer(media);
				     SpecifyBreed.play();
				     
				   TextInputDialog dialog = new TextInputDialog("Golden Retriever");
				   dialog.setTitle("Specify breed");
				   dialog.setHeaderText(null);
				   dialog.setContentText("Please specify breed:");
				   Optional<String> result = dialog.showAndWait();
				   if(result.isPresent())
				   breedSpecify = result.get();
				   
			   }else {
				   breedSpecify = breedOption;
			   }
			   
			   if(furColorOption.equalsIgnoreCase("Other")) {
				     userInputSpeak.stop();
				     
				       TextInputDialog dialog = new TextInputDialog("Brown");
					   dialog.setTitle("Specify fur color");
					   dialog.setHeaderText(null);
					   dialog.setContentText("Please specify fur color:");
					   Optional<String> result = dialog.showAndWait();
					   if(result.isPresent())
						   furColorSpecify = result.get();
					   
				   }else {
					   furColorSpecify = furColorOption;
				   
			   }
			   
			   /////////////////////Numerical validation  /////////////////////
			   
			   if(numberOrNot(weightText.getText())==false||numberOrNot(heightText.getText())==false) {
				    userInputSpeak.stop();
				    Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error!");										
					alert.setContentText("Please enter the weight and height as numerics!");
					alert.showAndWait();
					
				
					userInputSpeak.play();
					
					if(numberOrNot(weightText.getText())==false) {
						weightText.setText("");
					}
					if(numberOrNot(heightText.getText())==false) {
						heightText.setText("");
					}
			   }
			  
			   if(isEmailValid(userEmailText.getText())==false) {
				    userInputSpeak.stop();
				    Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error!");										
					alert.setContentText("Please enter the proper email address!");
					alert.showAndWait();
					
				    userEmailText.setText("");
					userInputSpeak.play();
			   }
			   
				    }	
		            
		            
		            
		        if(!weightText.getText().trim().isEmpty()&&!heightText.getText().trim().isEmpty()) {
		           dog = new Dog(nameText.getText(),breedSpecify,option,
		    		          furColorOption,Double.parseDouble(weightText.getText()),Double.parseDouble(heightText.getText()),
					          value,convertToBoolean(neutorSpayOption),convertToBoolean(microchippedOption),userEmailText.getText()); 
		          }
		          
		        
		          
		        if(!weightText.getText().trim().isEmpty()&&!heightText.getText().trim().isEmpty()&&!option.trim().isEmpty()
						&& !breedSpecify.trim().isEmpty() && !furColorSpecify.trim().isEmpty() 
						    && !neutorSpayOption.trim().isEmpty()
						      && !microchippedOption.trim().isEmpty() && !nameText.getText().trim().isEmpty() && !userEmailText.getText().trim().isEmpty()
						        && dogInformation.contains(dog)==false ) {
		        	
		        	
		        	
		        	
		        	  dogInformation.add(dog);
		        	 
		        	  /////////////////////Add the information to MySQL database  /////////////////////
		        	  
			          String url = "jdbc:mysql://localhost/cs3220stu55?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			          String username = "cs3220stu55";
			          String password = "zWpa0NxR";
			           
			          Connection conn;
					
						 try{
							
							 //STEP 2: Register JDBC driver
						      Class.forName("com.mysql.cj.jdbc.Driver");

						      //STEP 3: Open a connection
						    
						      conn = DriverManager.getConnection(url, username, password);
						      
						      Statement statement = conn.createStatement() ;
						      ResultSet resultset = statement.executeQuery("SELECT * FROM pet") ;
						      while(resultset.next()){
						          if(resultset.getString("name").equalsIgnoreCase(dog.getName())
						            &&  resultset.getString("breed").equalsIgnoreCase(dog.getBreed())	
						            &&	resultset.getString("gender").equalsIgnoreCase(dog.getGender())         
						            &&  resultset.getString("furColor").equalsIgnoreCase(dog.getFurColor())	 
						            &&  resultset.getDouble("weight") == dog.getWeight()	
						            &&  resultset.getDouble("height") == dog.getHeight()	
						            &&  resultset.getBoolean("neutorSpay") == dog.getNeuterSpay()
						            &&  resultset.getBoolean("microchipped") == dog.getMicrochip()
						            &&  resultset.getString("userEmail").equalsIgnoreCase(dog.getUserEmail())
						        		   ){
						        	  
						          ///////////////////// for preventing duplicates added to the database  /////////////////////
						        	 unique = false;
						             Alert alert = new Alert(AlertType.ERROR);
						             alert.setTitle("Error!");										
									 alert.setContentText("Entry: " + dog.getName() + " already existed in our database!");
									 alert.showAndWait();
						             weightText.clear();
						             heightText.clear();
						             nameText.clear();
						             userEmailText.clear();
						             breedChoice.getSelectionModel().select(0);
						             furColorChoice.getSelectionModel().select(0);
						             neutorSpayChoice.getSelectionModel().select(0);
						             microchippedChoice.getSelectionModel().select(0);
						             datePicker.setValue(null);
						             unique = true;
						          }
						      }
						      if(unique == true 
						    		  && !weightText.getText().trim().isEmpty()&&!heightText.getText().trim().isEmpty()) {               
						      String query = " insert into pet (name, breed,gender,furColor, weight, height,birthday, neutorSpay, microchipped, userEmail)"
						    	        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						      PreparedStatement preparedStmt = conn.prepareStatement(query);
						      preparedStmt.setString (1, nameText.getText());
						      preparedStmt.setString (2, breedSpecify);
						      preparedStmt.setString (3, option);
						      preparedStmt.setString (4, furColorSpecify);
						      preparedStmt.setDouble (5, Double.parseDouble(weightText.getText()));
						      preparedStmt.setDouble (6, Double.parseDouble(heightText.getText()));
						      preparedStmt.setDate   (7, java.sql.Date.valueOf(value));
						      preparedStmt.setBoolean(8, convertToBoolean(neutorSpayOption));
						      preparedStmt.setBoolean(9, convertToBoolean(microchippedOption));
						      preparedStmt.setString (10, userEmailText.getText());
						      
						      

						      // execute the preparedstatement
						      preparedStmt.execute();
						      
						      conn.close();
						      
						        userInputSpeak.stop();
						        
						        /////////////////////information saved  /////////////////////
						        
								final URL resource3 = getClass().getResource("informationSaved.mp3");
							    final Media media3 = new Media(resource3.toString());
							     informationSaved = new MediaPlayer(media3);
							     informationSaved.play();
								Alert alert1 = new Alert(AlertType.INFORMATION);
								alert1.setTitle("Saved");
								alert1.setContentText(dog.getName() + "' Information saved!");
								alert1.showAndWait();
								informationSaved.stop();
						      
							 
						      } 
					}catch(SQLException se) {
						se.printStackTrace();
					} catch (ClassNotFoundException e2) {
						e2.printStackTrace();
					}
						 
						
						 }
				
				if(nameText.getText()==null ||  nameText.getText().trim().isEmpty()
				   ||breedSpecify==null ||  breedSpecify.trim().isEmpty()
				     ||option==null || option.trim().isEmpty()
				       ||furColorChoice.getValue().toString()==null || furColorChoice.getValue().toString().trim().isEmpty()
				         ||weightText.getText()==null    || weightText.getText().trim().isEmpty() 
				           ||heightText.getText()==null     || heightText.getText().trim().isEmpty() 
				             ||value == null 
				               ||neutorSpayOption==null   || neutorSpayOption.trim().isEmpty() 
			                   	 ||microchippedOption==null  || microchippedOption.trim().isEmpty() || 
			                   	    userEmailText.getText()==null ||
			                   	     userEmailText.getText().trim().isEmpty()) {
					                   
					
					                    userInputSpeak.stop();
					                     final URL resource5 = getClass().getResource("error.mp3");
									     final Media media5 = new Media(resource5.toString());
									     error = new MediaPlayer(media5);
									     error.play();
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("Error!");										
										alert.setContentText("Please fill in all the fields!!");
										alert.showAndWait();
										 error.stop();
										 userInputSpeak.play();
										
										
								
						
					
				}
				else{
				
				
				}
				});
			
					
			backToPrimary.setOnAction(event->{
				secondaryStage.close();
				primaryStage.show();
				userInputSpeak.stop();
				welcomeMessage.play();
			});
		
			
			proceed.setOnAction(e1->{
				
				 /////////////////////proceed only works after saved  /////////////////////
				
				if(nameText.getText()==null ||  nameText.getText().trim().isEmpty()
						   ||breedChoice.getValue()==null ||  breedChoice.getValue().toString().trim().isEmpty()
						     ||genderChoice.getValue().toString()==null ||  genderChoice.getValue().toString().trim().isEmpty()
						       ||furColorChoice.getValue().toString()==null || furColorChoice.getValue().toString().trim().isEmpty()
						         ||weightText.getText()==null    || weightText.getText().trim().isEmpty() 
						           ||heightText.getText()==null     || heightText.getText().trim().isEmpty() 
						             || datePicker.getValue() == null
						               ||neutorSpayChoice.getValue().toString()==null   || neutorSpayChoice.getValue().toString().trim().isEmpty() 
					                   	 ||microchippedChoice.getValue().toString()==null  || microchippedChoice.getValue().toString().trim().isEmpty()
					                   	    ||userEmailText.getText()== null || userEmailText.getText().trim().isEmpty()) {
							                   
							
							                    userInputSpeak.stop();
							                    final URL resource5 = getClass().getResource("error.mp3");
											     final Media media5 = new Media(resource5.toString());
											     error = new MediaPlayer(media5);
											     error.play();
												Alert alert = new Alert(AlertType.ERROR);
												alert.setTitle("Error!");										
												alert.setContentText("Please fill in all the fields!!");
												alert.showAndWait();
												 error.stop();
												 userInputSpeak.play();
												
												
										
								
							
						}
				
				 /////////////////////ArrayList dogInformation only add entries when save button is pressed /////////////////////		
     	
				else if(dogInformation.isEmpty()) {
     		
     		         userInputSpeak.stop();
					 final URL resource6 = getClass().getResource("saveAlert.mp3");
				     final Media media6 = new Media(resource6.toString());
				     saved = new MediaPlayer(media6);
				     saved.play();
				        Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error!");										
						alert.setContentText("Please save the information to proceed!!");
						alert.showAndWait();
				}
		else {
							
				userInputSpeak.stop();
				
				final URL resource4 = getClass().getResource("ready?.mp3");
			     final Media media4 = new Media(resource4.toString());
			     readyed = new MediaPlayer(media4);
			     readyed.play();
				secondaryStage.close();
				Stage tertiaryStage = new Stage();
				GridPane gridPane = new GridPane();
				gridPane.setAlignment(Pos.CENTER);
				gridPane.setHgap(10);
				gridPane.setVgap(10);
				gridPane.setPadding(new Insets(25, 25, 25, 25));
				Label message = new Label("Are you ready for the ADH? ");
				message.setFont(new Font("Times New Roman", 30));
				Image image = new Image("cuteDoggy.gif");
				ImageView imageView = new ImageView();
				imageView.setImage(image);
				imageView.setFitHeight(150);
				imageView.setFitWidth(90);
				message.setGraphic(imageView);
				gridPane.add(message, 0, 1);
				
				
				ready.setOnAction(e2->{
					welcomeBGM.stop();
					tertiaryStage.close();
					javaFXPane jp1 = new javaFXPane();
					Stage stage = new Stage();
					try {
						jp1.start(stage);
					} catch (Exception e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				});
				
				viewInfor.setOnAction(e6->{
					 
			          String url = "jdbc:mysql://localhost/cs3220stu55?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			          String username = "cs3220stu55";
			          String password = "zWpa0NxR";
			           
			          ObservableList<Dog> data = FXCollections.observableArrayList();
			          
			              
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
				            		   tomorrow,rs.getBoolean("neutorSpay"),rs.getBoolean("microchipped"), rs.getString("userEmail")));
				                
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
			        furColorCol.setMaxWidth(200);
			        furColorCol.setCellValueFactory(
			                new PropertyValueFactory<Dog, String>("furColor"));
			        
			        TableColumn weightCol = new TableColumn("weight(in lbs)");
			        weightCol.setMaxWidth(100);
			        weightCol.setCellValueFactory(
			                new PropertyValueFactory<Dog, String>("weight"));
			        
			        TableColumn heightCol = new TableColumn("height(in cms)");
			        heightCol.setMaxWidth(100);
			        heightCol.setCellValueFactory(
			                new PropertyValueFactory<Dog, String>("height"));
			        
			        TableColumn birthdayCol = new TableColumn("birthday");
			        birthdayCol.setMaxWidth(100);
			        birthdayCol.setCellValueFactory(
			                new PropertyValueFactory<Dog, String>("birthday"));
			        
			        TableColumn spayCol = new TableColumn("neuterSpay");
			        spayCol.setMaxWidth(100);
			        spayCol.setCellValueFactory(
			                new PropertyValueFactory<Dog, String>("neuterSpay"));
			        
			        TableColumn microchippedCol = new TableColumn("microchip");
			        microchippedCol.setMaxWidth(100);
			        microchippedCol.setCellValueFactory(
			                new PropertyValueFactory<Dog, String>("microchip"));
			        
			        TableColumn userEmailCol = new TableColumn("User Email");
			        userEmailCol.setMaxWidth(350);
			        userEmailCol.setCellValueFactory(
			                new PropertyValueFactory<Dog, String>("userEmail"));
			        
			      //  ObservableList<Dog> list = FXCollections.observableArrayList(dogInformation);
                    ObservableList<Dog> list = removeDuplicates(data);
			 
			        table.setItems(list);
			        table.getColumns().addAll(NameCol, breedCol, genderCol,furColorCol, weightCol,
			        		     heightCol, birthdayCol, spayCol, microchippedCol,userEmailCol);
			 
			        
			        back.setOnAction(e8->{
			        	fifthStage.close();
			        	readyed.play();
			        	tertiaryStage.show();
			        });
			        notReady.setOnAction(e5->{
						fifthStage.close();
						readyed.stop();
						tertiaryStage.close();
						final URL resource5 = getClass().getResource("deleted.mp3");
					     final Media media5 = new Media(resource5.toString());
					     deleted = new MediaPlayer(media5);
					     deleted.play();
					    
						dogInformation.clear();

						
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
						secondaryStage.show();
						
						nameText.setText(null);
						breedChoice.getSelectionModel().select(0);
						genderChoice.getSelectionModel().select(0);
						weightText.setText(null);
						heightText.setText(null);
						furColorChoice.getSelectionModel().select(0);
					    datePicker.setValue(null);
						neutorSpayChoice.getSelectionModel().select(0);
						microchippedChoice.getSelectionModel().select(0);
						userEmail.setText(null);
						
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information");
						alert.setHeaderText(null);
						alert.setContentText("Please fill in the following!");

						alert.showAndWait();
						deleted.stop();
					    userInputSpeak.play();
						
					});
			        Button delete = new Button("Delete This");
			        delete.setStyle("-fx-background-color: black");
			        delete.setTextFill(Color.WHITE);
			        delete.setOnAction(ee->{
			        	Dog selectedItem = table.getSelectionModel().getSelectedItem();
			            table.getItems().remove(selectedItem);
			            Connection conn = null;
						 try{
							 
						     
						      Class.forName("com.mysql.cj.jdbc.Driver");

						      conn = DriverManager.getConnection(url, username, password);
						     
						     
						      String sql = "DELETE FROM pet where name = ?";
						      
						      PreparedStatement preparedStmt = conn.prepareStatement(sql);
						      preparedStmt.setString(1,selectedItem.getName());
						      preparedStmt.execute();
						      conn.close();
						      
						 }catch(Exception exc) {
							 exc.printStackTrace();
						 }
			        });
			        
			        Button edit = new Button("Edit");
			        edit.setStyle("-fx-background-color: black");
			        edit.setTextFill(Color.WHITE);
			        
			        edit.setOnAction(ee->{
			        	
			        	Dog selectedItem = table.getSelectionModel().getSelectedItem();
			        	Stage editStage = new Stage();
			        	Button editSave = new Button("Save");
			        	editSave.setStyle("-fx-background-color: black");
			        	editSave.setTextFill(Color.WHITE);
						GridPane editPane = new GridPane();
						
						editPane.setAlignment(Pos.CENTER);
						editPane.setHgap(5);
						editPane.setVgap(5);
						editPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
						//The user input fields
						Label editName = new Label("name: ");
						editName.setFont(new Font("Times New Roman",17));
						editPane.add(editName, 0, 1);
						TextField editNameText = new TextField();
						//editNameText.setText(selectedItem.getName());
						editNameText.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
						editPane.add(editNameText, 1 ,1);
						
						
						Label editBreed = new Label("breed: ");
						editBreed.setFont(new Font("Times New Roman",17));
						editPane.add(editBreed, 0, 2);
						ChoiceBox<String> editBreedChoice = new ChoiceBox<String>(FXCollections.observableArrayList(
							    "Retrievers (Labrador)	", "German Shepherds", "Retrievers (Golden)	","French Bulldogs	"
							    ,"Bulldogs","Beagles","Poodles","Rottweilers","Yorkshire Terriers","Pointers (German Shorthaired)"
							    , "Boxers","Siberian Huskies", "Great Danes","Pomeranians","Pugs","Huskies","Chihuahua"
							    , "Pembroke Welsh Corgis","Doberman Pinschers","Shih Tzu","Boston Terriers","Havanese","Shetland Sheepdogs","Maltese"
							    ,"Weimaraners","Collies","Shiba Inu","Retrievers (Chesapeake Bay)","Akitas","Papillons","Dalmatians"
							    ,"Italian Greyhounds","Mixed","Other")
							);
						editBreedChoice.setStyle("-fx-background-color: WHITE");
						editBreedChoice.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
						//editBreedChoice.setValue(selectedItem.getBreed());
						editPane.add(editBreedChoice, 1, 2);
						
						
						Label editGender = new Label("gender: ");
						editGender.setFont(new Font("Times New Roman",17));
						editPane.add(editGender, 0, 3);
						ChoiceBox<String> editGenderChoice= new ChoiceBox<>();
						editGenderChoice.setStyle("-fx-background-color: WHITE");
						editGenderChoice.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
						editGenderChoice.getItems().addAll("Male", "Female");
						//editGenderChoice.setValue(selectedItem.getGender());
						editPane.add(editGenderChoice, 1 ,3);
						
						
						Label editFurColor = new Label("fur color: ");
						editFurColor.setFont(new Font("Times New Roman",17));
						editPane.add(editFurColor, 0, 4);
						ChoiceBox<String> editFurColorChoice= new ChoiceBox<>();
						editFurColorChoice.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
						editFurColorChoice.setStyle("-fx-background-color: WHITE");
						editFurColorChoice.getItems().addAll("Brown", "Red","Gold","Yellow","Cream","Blue","Gray","Two color striped","Three color striped","Dotted","Beige","Tan","Orange","Light Brown"
								                         ,"Dark Brown","Apricot","Fawn","Chestnut","Rust","Wheaten","White","Other");
						//editFurColorChoice.setValue(selectedItem.getFurColor());;
						editPane.add(editFurColorChoice, 1, 4);
						
						
						Label editWeight = new Label("weight(in lb): ");
						editWeight.setFont(new Font("Times New Roman",17));
						editPane.add(editWeight, 0, 5);
						TextField editWeightText = new TextField();
						//editWeightText.setText(String.valueOf(selectedItem.getWeight()));
						editWeightText.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
						editPane.add(editWeightText, 1 ,5);
						
						
						Label editHeight = new Label("height(in cm): ");
						editHeight.setFont(new Font("Times New Roman",17));
						editPane.add(editHeight, 0, 6);
						TextField editHeightText = new TextField();
						//editHeightText.setText(String.valueOf(selectedItem.getHeight()));
						editHeightText.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
						editPane.add(editHeightText, 1, 6);
						
						
						Label editBirthday = new Label("birthday: ");
						editBirthday.setFont(new Font("Times New Roman",17));
						editPane.add(editBirthday, 0, 7);
						DatePicker editDatePicker = new DatePicker();
						editDatePicker.setStyle("-fx-control-inner-background: WHITE;");
						//editDatePicker.setValue(selectedItem.getBirthday());
						editDatePicker.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
						editPane.add(editDatePicker, 1, 7);
						
						
						Label editNeutorSpay= new Label("Is your pet neutored or spayed? ");
						editNeutorSpay.setFont(new Font("Times New Roman",17));
						editPane.add(editNeutorSpay, 0, 8);
						ChoiceBox<String> editNeutorSpayChoice = new ChoiceBox<String>(FXCollections.observableArrayList(
							    "Yes", "No")
							);
						editNeutorSpayChoice.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
						editNeutorSpayChoice.setStyle("-fx-background-color: WHITE");
						//editNeutorSpayChoice.setValue(String.valueOf(selectedItem.getNeuterSpay()));
						editPane.add(editNeutorSpayChoice, 1 ,8);
						
						
						Label editMicrochipped = new Label("Does your pet have microchip? ");
						editMicrochipped.setFont(new Font("Times New Roman",17));
						editPane.add(editMicrochipped, 0, 9);
						ChoiceBox<String> editMicrochippedChoice= new ChoiceBox<>();
						editMicrochippedChoice.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
						editMicrochippedChoice.setStyle("-fx-background-color: WHITE");
						editMicrochippedChoice.getItems().addAll("Yes","No");
						//editMicrochippedChoice.setValue(String.valueOf(selectedItem.getMicrochip()));;
						editPane.add(editMicrochippedChoice, 1, 9);
						
						
						Label editUserEmail = new Label("Email: ");
						editUserEmail.setFont(new Font("Times New Roman",17));
						editPane.add(editUserEmail, 0, 10);
						TextField editUserEmailText = new TextField();
						//editNameText.setText(selectedItem.getName());
						editUserEmailText.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
						editPane.add(editUserEmailText, 1 ,10);
			          
						editPane.add(editSave, 0, 12);
						
						editSave.setOnAction(eee->{
							
							String editSpecifyBreed = "";
							String editSpecifyColor = "";
				            Connection conn = null;
							 try{
								 
								 
								 if(isEmailValid(editUserEmailText.getText())&&numberOrNot(editWeightText.getText())&&numberOrNot(editHeightText.getText())) {
								 
	    					         if(editBreedChoice.getValue().equalsIgnoreCase("Other")) {
	    					        	   TextInputDialog dialog = new TextInputDialog("Golden Retriever");
	    								   dialog.setTitle("Specify breed");
	    								   dialog.setHeaderText(null);
	    								   dialog.setContentText("Please specify breed:");
	    								   Optional<String> result = dialog.showAndWait();
	    								   if(result.isPresent())
	    									   editSpecifyBreed = result.get();
	    					         }else {
	    					        	 editSpecifyBreed = editBreedChoice.getValue();
	    					         }
	    					         if(editFurColorChoice.getValue().equalsIgnoreCase("Other")) {
	    					        	  TextInputDialog dialog = new TextInputDialog("Brown");
	    								   dialog.setTitle("Specify fur color");
	    								   dialog.setHeaderText(null);
	    								   dialog.setContentText("Please specify fur color:");
	    								   Optional<String> result = dialog.showAndWait();
	    								   if(result.isPresent())
	    									   editSpecifyColor = result.get();
	    					         }else {
	    					        	 editSpecifyColor = editFurColorChoice.getValue();
	    					         }
                 
									 
								 
									 
									 
								 Class.forName("com.mysql.cj.jdbc.Driver");
	
							      conn = DriverManager.getConnection(url, username, password);
							     					     
							      String sql = "update pet set breed = ?,"
							      		+ "gender = ?,"
							      		+ "furColor = ?,"
							      		+ "weight = ?,"
							      		+ "height = ?,"
							      		+ "birthday = ?,"
							      		+ "neutorSpay = ?,"
							      		+ "microchipped = ?, "
							      		+ "userEmail = ? where name = ?";
							      
							      PreparedStatement preparedStmt = conn.prepareStatement(sql);
							      preparedStmt.setString(1,editSpecifyBreed);
							      preparedStmt.setString(2,editGenderChoice.getValue());
							      preparedStmt.setString(3,editSpecifyColor);
							      preparedStmt.setDouble(4, Double.parseDouble(editWeightText.getText()));
							      preparedStmt.setDouble(5, Double.parseDouble(editHeightText.getText()));
							      preparedStmt.setDate(6, java.sql.Date.valueOf(editDatePicker.getValue()));
							      preparedStmt.setBoolean(7, convertToBoolean(editNeutorSpayChoice.getValue()));
							      preparedStmt.setBoolean(8, convertToBoolean(editMicrochippedChoice.getValue()));
							      preparedStmt.setString(9, editUserEmailText.getText());
							      preparedStmt.setString(10, selectedItem.getName());
							      
							      
							      preparedStmt.executeUpdate();
							      conn.close();
							      
							      Alert alert = new Alert(AlertType.INFORMATION);
							      alert.setHeaderText(null);
							      alert.setContentText("Edited information has been saved.");
							      alert.showAndWait();
							      
							      editStage.close();
								 }else {
									  Alert alert = new Alert(AlertType.ERROR);
								      alert.setHeaderText(null);
								      alert.setContentText("Please enter proper values!.");
								      alert.showAndWait(); 
								      
								      editWeightText.clear();
								      editHeightText.clear();
								      editUserEmailText.clear();
								 }
								 		
							      
							 }catch(Exception exc) {
								 exc.printStackTrace();
							 }
						});
			            
						  editStage.setScene(new Scene(editPane,500,500));
					      editStage.show();

			        });
			        final VBox vbox = new VBox();
			        vbox.setSpacing(5);
			        vbox.setPadding(new Insets(10, 0, 0, 10));
			        back.setTextFill(Color.WHITE);
					back.setStyle("-fx-background-color: Black");
					notReady.setTextFill(Color.WHITE);					
					notReady.setStyle("-fx-background-color: Black");
			        vbox.getChildren().addAll(label, table, back, notReady, delete,edit);
			 
			        ((Group) scene.getRoot()).getChildren().addAll(vbox);
							
					fifthStage.setScene(scene);
					fifthStage.show();
					readyed.stop();
					tertiaryStage.close();
				});
				AddPet.setOnAction(e7->{
					tertiaryStage.close();
					readyed.stop();
					secondaryStage.show();
					userInputSpeak.play();
					nameText.setText(dog.getName());
					breedChoice.setValue(dog.getBreed());
					genderChoice.setValue(dog.getGender());;
					weightText.setText(String.valueOf(dog.getWeight()));
					heightText.setText(String.valueOf(dog.getHeight()));
					furColorChoice.setValue(dog.getFurColor());
					datePicker.setValue(dog.getBirthday());
					neutorSpayChoice.setValue(String.valueOf(dog.getNeuterSpay()));;
					microchippedChoice.setValue(String.valueOf(dog.getMicrochip()));;
				});
				ready.setTextFill(Color.WHITE);
				ready.setStyle("-fx-background-color: Black");
				viewInfor.setTextFill(Color.WHITE);
				viewInfor.setStyle("-fx-background-color: Black");
				AddPet.setTextFill(Color.WHITE);
				AddPet.setStyle("-fx-background-color: Black");
				gridPane.add(ready, 0, 2);
			//	gridPane.add(notReady, 0, 3);
				gridPane.add(viewInfor, 0, 3);
				gridPane.add(AddPet, 0, 4);
			   
				tertiaryStage.setScene(new Scene(gridPane,550,300));
				tertiaryStage.show();
						}
				});
			
			
			viewDatabase.setStyle("-fx-background-color: Black");
			viewDatabase.setTextFill(Color.WHITE);
			viewDatabase.setOnAction(event1 ->{
				 userInputSpeak.stop();
				 String url = "jdbc:mysql://localhost/cs3220stu55?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		          String username = "cs3220stu55";
		          String password = "zWpa0NxR";
		           
		          ObservableList<Dog> data = FXCollections.observableArrayList();
		          
		              
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
			            		   tomorrow,rs.getBoolean("neutorSpay"),rs.getBoolean("microchipped"), rs.getString("userEmail")));
			                
			            }

			            c.close();
			           
			 
			              				 
			         
			        
			        } catch (Exception exception) {
			            exception.printStackTrace();
			            //System.out.println("Error on Building Data");
			        }
				table1.getColumns().clear();
			    
				
				Stage sixthStage = new Stage();

				Scene sceneSix = new Scene(new Group());
				
				final Label label1 = new Label("Dog Information");
		        label1.setFont(new Font("Arial", 20));
		 
		        table1.setEditable(true);
		 
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
		        furColorCol.setMaxWidth(200);
		        furColorCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, String>("furColor"));
		        
		        TableColumn weightCol = new TableColumn("weight(in lbs)");
		        weightCol.setMaxWidth(100);
		        weightCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, String>("weight"));
		        
		        TableColumn heightCol = new TableColumn("height(in cms)");
		        heightCol.setMaxWidth(100);
		        heightCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, String>("height"));
		        
		        TableColumn birthdayCol = new TableColumn("birthday");
		        birthdayCol.setMaxWidth(100);
		        birthdayCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, String>("birthday"));
		        
		        TableColumn spayCol = new TableColumn("neuterSpay");
		        spayCol.setMaxWidth(100);
		        spayCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, String>("neuterSpay"));
		        
		        TableColumn microchippedCol = new TableColumn("microchip");
		        microchippedCol.setMaxWidth(100);
		        microchippedCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, String>("microchip"));
		        
		        TableColumn userEmailCol = new TableColumn("User Email");
		        userEmailCol.setMaxWidth(350);
		        userEmailCol.setCellValueFactory(
		                new PropertyValueFactory<Dog, String>("userEmail"));
		        
		      //  ObservableList<Dog> list = FXCollections.observableArrayList(dogInformation);
               ObservableList<Dog> list = removeDuplicates(data);
		 
		        table1.setItems(list);
		        table1.getColumns().addAll(NameCol, breedCol, genderCol,furColorCol, weightCol,
		        		     heightCol, birthdayCol, spayCol, microchippedCol,userEmailCol);
		 
		        Button backToInput = new Button("Back");
		        backToInput.setStyle("-fx-background-color: Black");
		        backToInput.setTextFill(Color.WHITE);
		        backToInput.setOnAction(e8->{
		            sixthStage.close();
//		        	readyed.play();
		        	secondaryStage.show();
		        });
		        Button delete = new Button("Delete This");
		        delete.setStyle("-fx-background-color: black");
		        delete.setTextFill(Color.WHITE);
		        delete.setOnAction(ee->{
		        	Dog selectedItem = table1.getSelectionModel().getSelectedItem();
		            table1.getItems().remove(selectedItem);
		            Connection conn = null;
					 try{					     
					      Class.forName("com.mysql.cj.jdbc.Driver");

					      conn = DriverManager.getConnection(url, username, password);
					     					     
					      String sql = "DELETE FROM pet where name = ?";
					      
					      PreparedStatement preparedStmt = conn.prepareStatement(sql);
					      preparedStmt.setString(1,selectedItem.getName());
					      preparedStmt.execute();
					      conn.close();
					      
					 }catch(Exception exc) {
						 exc.printStackTrace();
					 }
		        });
		        Button clearInfor = new Button("Clear");
		        clearInfor.setStyle("-fx-background-color: Black");
		        clearInfor.setTextFill(Color.WHITE);
		        clearInfor.setOnAction(event2->{
		        	
		        	ObservableList<Dog> items = table1.getItems();
	
		        if(!items.isEmpty()) {
		        	Connection conn = null;
					Statement stmte = null;
					 try{				     
					      Class.forName("com.mysql.cj.jdbc.Driver");

					      conn = DriverManager.getConnection(url, username, password);
					     
					      stmte = conn.createStatement();
					      
					      String sql = "DELETE FROM pet;";
					      
					      stmte.executeUpdate(sql);
					      
					        Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle(null);										
							alert.setContentText("Deleted!");
							alert.showAndWait();
							
							sixthStage.close();
							secondaryStage.show();
							userInputSpeak.play();
					      
					 }catch(Exception exc) {
						 exc.printStackTrace();
					 }
					 }else {
						    Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error!");										
							alert.setContentText("Database is empty, nothing to delete!");
							alert.showAndWait();
					 }
		        });
		        
		        VBox box = new VBox();
		        box.setPadding(new Insets(10));
		       
		        box.getChildren().addAll(label1,table1,backToInput,clearInfor, delete);
		        box.setSpacing(5);
		        ((Group) sceneSix.getRoot()).getChildren().addAll(box);
		        sixthStage.setScene(sceneSix);
		        sixthStage.show();
				
			});
			save.setTextFill(Color.WHITE);
			save.setStyle("-fx-background-color: Black");
			proceed.setTextFill(Color.WHITE);
			proceed.setStyle("-fx-background-color: Black");
			backToPrimary.setTextFill(Color.WHITE);
			backToPrimary.setStyle("-fx-background-color: Black");
			pane.add(proceed, 1, 11);
			pane.add(save, 0, 11);
			pane.add(backToPrimary, 0, 12);
			pane.add(viewDatabase, 1, 12);
			//pane.add(viewInfor, 0, 12);
			secondaryStage.setScene(new Scene(pane,500,500));
			secondaryStage.show();
			
		});
		
		Image image = new Image("Logo.png");
		ImageView imageView = new ImageView();
		imageView.setImage(image);
		imageView.setFitHeight(200);
		imageView.setFitWidth(250);
		backToJavaFXPane.setTextFill(Color.WHITE);
		backToJavaFXPane.setStyle("-fx-background-color: Black");
		border.setCenter(imageView);
		border.setLeft(backToJavaFXPane);
		border.setRight(button);
		BorderPane.setAlignment(backToJavaFXPane, Pos.BOTTOM_LEFT);
		BorderPane.setAlignment(button, Pos.BOTTOM_RIGHT);
		
		primaryStage.setScene(new Scene(border,500,500));
		primaryStage.show();
		 final URL resource = getClass().getResource("speak.mp3");
	     final Media media = new Media(resource.toString());
	     final URL resource1 = getClass().getResource("welcomeBGM.mp3");
	     final Media media1 = new Media(resource1.toString());
	     welcomeBGM = new MediaPlayer(media1);
	     welcomeBGM.setOnEndOfMedia(new Runnable() {
	            public void run() {
	            	welcomeBGM.seek(Duration.ZERO);
	            }
	        });      
	     welcomeBGM.play();
	     welcomeMessage = new MediaPlayer(media);
	     welcomeMessage.play();
	}
	public static void main(String[] args) {
		launch(args);
	}
	public ArrayList<Dog> getList() {
	   
	    return dogInformation;
	}
	boolean convertToBoolean(String value) {
	    boolean returnValue = false;
	    if ("yes".equalsIgnoreCase(value) || 
	        "true".equalsIgnoreCase(value))
	        returnValue = true;
	    return returnValue;
	}
	static ObservableList<Dog> removeDuplicates(ObservableList<Dog> list) {

        // Store unique items in result.
		ObservableList<Dog> result = FXCollections.observableArrayList();

        // Record encountered Strings in HashSet.
        HashSet<Dog> set = new HashSet<>();

        // Loop over argument list.
        for (Dog item : list) {

            // If String is not in set, add it to the list and the set.
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }
	//validating whether an input is numeric
	 public static boolean numberOrNot(String input)
	    {
	        try
	        {
	            Double.parseDouble(input);
	        }
	        catch(NumberFormatException ex)
	        {
	            return false;
	        }
	        return true;
	    }
	 
	 //validating whether an input is an email address
	 
	 public static boolean isEmailValid(String email) 
	    { 
	        String emailAddress = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
	                            "[a-zA-Z0-9_+&*-]+)*@" + 
	                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
	                            "A-Z]{2,7}$"; 
	                              
	        Pattern pat = Pattern.compile(emailAddress); 
	        if (email == null) 
	            return false; 
	        return pat.matcher(email).matches(); 
	    } 

}
