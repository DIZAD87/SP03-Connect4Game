//Daniel Izadnegahdar S00840086 IT2650 Lab#3 03/04/17
//This is the start class where all the central invokes are made
    package Scripts;

//Imported libraries
    import java.util.Random;
    import javafx.stage.Stage;
    import javax.swing.JOptionPane;
    import javafx.application.Application;
    import javafx.concurrent.Task;
    import javafx.concurrent.WorkerStateEvent;
    import javafx.event.ActionEvent;
    import javafx.event.EventHandler;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.layout.AnchorPane;
    import javafx.scene.layout.GridPane;
    
//Main program******************************************************************  
public class Home extends Application
    {//Global items
        //Global swapStage
            Stage swapStage;
          
        //Global instances
            General general = new General();
            PlaySound playSound = new PlaySound();
            Tray tray = new Tray();
            Random random = new Random();
           
        //Global variables
            int swapCount = 0;
 //SCENE#1: start()*************************************************************            
    //Main menu        
        @Override
        public void start(Stage primaryStage)
            {         
               swapStage = primaryStage; 
               playSound.playMusic();
               mainScene();
            }
              
    //Game scene  
        public void mainScene()
            {
            //PART#1: Instantiate the GUI objects
                //Panes
                    AnchorPane root = new AnchorPane();
                //Buttons
                    Button buttonNewGame = new Button();
                    Button buttonQuit = new Button();

            //PART#2: Attach the objects to their pane
                //Buttons
                    root.getChildren().add(buttonNewGame);
                    root.getChildren().add(buttonQuit);

            //PART#3: Define the location/style attributes
                //Panes	
                    root.getStyleClass().add("titleBackgroundStyle");
                //Buttons
                    //buttonNewGame
                        buttonNewGame.setText("New Game"); 
                        buttonNewGame.getStyleClass().add("buttonDefault");
                        AnchorPane.setTopAnchor(buttonNewGame, 325.0);
                        AnchorPane.setLeftAnchor(buttonNewGame, 125.0);
                        AnchorPane.setRightAnchor(buttonNewGame, 125.0);
                    //buttonQuit
                        buttonQuit.setText("Quit");
                        buttonQuit.getStyleClass().add("buttonDefault");
                        AnchorPane.setTopAnchor(buttonQuit, 400.0);
                        AnchorPane.setLeftAnchor(buttonQuit, 125.0);
                        AnchorPane.setRightAnchor(buttonQuit, 125.0);
                        
                //Button Events
                    //Press the buttonNewGame
                        buttonNewGame.setOnAction(new EventHandler<ActionEvent>()
                            {
                                @Override
                                public void handle(ActionEvent event)
                                    {
                                        playSound.playStart();
                                        swapCount++;
                                        gameScene();
                                    } 
                            });
                    //Press the buttonQuit
                        buttonQuit.setOnAction(new EventHandler<ActionEvent>()
                            {                              
                                @Override
                                public void handle(ActionEvent event)
                                    { 
                                        playSound.playButton();
                                        System.exit(0); 
                                    }       
                            });             
                                 
            //Scene properties
                //PATCH: For some reason it is oversizing by 10px on start...
                    //Start of patch
                        int windowSize;
                         if (swapCount == 0)
                         { windowSize = 480; }
                         else 
                         { windowSize = 490; }
                    //End of patch
               //Standard scene settings
                    Scene scene = new Scene(root, windowSize, windowSize);
                    swapStage.setScene(scene);
                    swapStage.setTitle("LAB#3: Connect4 (DIZAD 03/03/17)");
                    swapStage.setResizable(false);
                    swapStage.show();
                //Attach stylesheet    
                    String css = Home.class.getResource("mainCSS.css").toExternalForm();
                    scene.getStylesheets().add(css); 
            }

//SCENE#2: gameScene()**********************************************************          
        //Game scene  
            public void gameScene()
                {
                //PART#1: Instantiate the GUI objects
                    //Panes
                        GridPane root = new GridPane();
                    //Buttons
                        //Standard buttons
                            Button buttonReturnToMenu = new Button();
                            Button buttonInstructions = new Button();
                        //Labels
                            Label labelPlayerStatus = new Label();

                //PART#2: Attach the objects to their pane
                    //Labels
                        root.add(labelPlayerStatus, 0, 6, 6, 6);
                    //Buttons                          
                        root.add(buttonReturnToMenu, 6, 6, 6, 6); //Column, row, column span?, row span?
                        root.add(buttonInstructions, 5, 6, 6, 6); //Column, row

                //PART#3: Define the location/style attributes
                    //Panes
                        //Add CSS classses
                            root.getStyleClass().add("gameBackgroundStyle");
                    //Labels
                        //Add CSS classses
                            labelPlayerStatus.getStyleClass().add("labelPlayerStatusOn"); 
                    //Buttons
                        //Add CSS classes
                            buttonReturnToMenu.getStyleClass().add("buttonDefault");
                            buttonReturnToMenu.setId("buttonReturnToMenuStyle");
                            buttonInstructions.getStyleClass().add("buttonDefault");
                            buttonInstructions.setId("buttonInstructionsStyle");
                                
                //Events
                    //Press the buttonReturnToMenu
                        buttonReturnToMenu.setOnAction(new EventHandler<ActionEvent>()
                            {
                                @Override
                                public void handle(ActionEvent event)
                                    {
                                        swapCount++;
                                        playSound.playButton();
                                        mainScene();
                                    } 
                            });

                    //Press the buttonInstructions
                        buttonInstructions.setOnAction(new EventHandler<ActionEvent>()
                            {
                                @Override
                                public void handle(ActionEvent event)
                                    {
                                       playSound.playButton();
                                       JOptionPane.showMessageDialog(null, "Drop a token into any slot and align 4 to win!"); 
                                    } 
                            });
                                                        
                //Other     
                    //The button tokens are not part of the PART1-3 instances above,...
                    //...they are bundled below so that we can capitalize on the double FOR loop
                        //Create a 7 X 6 array with button objects inside
                            Button[][] buttonTokenArray = new Button[7][6]; 
                        //Reset the tokens for a new game
                            tray.resetTokens();
                            for (int col = 0; col < 7; col++)//Loop through the columns
                                {
                                    for (int row = 0; row < 6; row++)//Loop through the rows
                                        {
                                        //PART#1-3 for the token buttons
                                            buttonTokenArray[col][row] = new Button(); //Add a unique button for each 2D array entry                                               
                                            root.add(buttonTokenArray[col][row], col, row); //Add each button to the root pane
                                            buttonTokenArray[col][row].getStyleClass().add("buttonEmptyTokenStyle"); //Add the stylesheet
                                        //Copies of row/col variables so we can use them inside the EventHandler below
                                            int rowCopy = row;
                                            int colCopy = col;
                                        //Events
                                            buttonTokenArray[col][row].setOnAction(new EventHandler<ActionEvent>()
                                               {//Player#1**********************
                                                @Override
                                                public void handle(ActionEvent event)
                                                    {   //Player#1 start commands
                                                            general.swapBannerToOFF(labelPlayerStatus); //Update the lower status banner display 
                                                            int udpatedGravityRow; //Used for locating the win positions
                                                            udpatedGravityRow = tray.gravityInsert(colCopy, "blue"); //Insert on gravity only
                                                            general.swapTokenToBlue(buttonTokenArray[colCopy][udpatedGravityRow]); //Change the token color
                                                        //Check for win
                                                            if (tray.winCheck("blue") == true)//If there's a win...
                                                                {//Lighten the tokens of the connect4
                                                                    general.swapTokenToLight(buttonTokenArray[tray.getWinC1()][tray.getWinR1()]);
                                                                    general.swapTokenToLight(buttonTokenArray[tray.getWinC2()][tray.getWinR2()]);
                                                                    general.swapTokenToLight(buttonTokenArray[tray.getWinC3()][tray.getWinR3()]);                
                                                                    general.swapTokenToLight(buttonTokenArray[tray.getWinC4()][tray.getWinR4()]);
                                                                //This code is just to invoke the win after the tokens are lite up, the 2 '!important' below are the key lines of interest in this bundle
                                                                    Task<Void> sleeper = new Task<Void>() 
                                                                    { @Override protected Void call() throws Exception { try { Thread.sleep(1000); }catch ( InterruptedException e ){ }return null;} };
                                                                        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() { @Override public void handle(WorkerStateEvent event)
                                                                            { 
                                                                               general.youWin(); //!important
                                                                               mainScene();  //!important
                                                                            }  
                                                                    });
                                                                    new Thread(sleeper).start();
                                                                }
                                                        //Check for tie
                                                            if (tray.tieCheck() == true)
                                                                {
                                                                    general.youTie();
                                                                    mainScene();
                                                                }
            
                                                //Player#2**********************
                                                    //Player#2 commands required for computer
                                                        //Generate a random duration for the computer to make a move
                                                            int  randTime = random.nextInt(1000) + 250; //randTime between 250 and 1000
                                                            try {Thread.sleep(randTime);} catch(InterruptedException ex) {Thread.currentThread().interrupt();}//Wait that randTime

                                                        //Choose a column for the computer to drop a token:
                                                            int  randCol = random.nextInt(7) + 0; //randCol between 0 and 6;
                                                        //If the stack is full, pick another number(this error-proof becomes more relevant when the tray is nearly full)
                                                            while (tray.isStackFull(randCol) == true)
                                                              { randCol = random.nextInt(7) + 0; }

                                                    //Player#2 start commands
                                                      int udpatedGravityRow2 = tray.gravityInsert(randCol, "red"); //Insert on gravity only
                                                      general.swapTokenToRed(buttonTokenArray[randCol][udpatedGravityRow2]); //Change the token color
                                                        //Check for lose
                                                           if (tray.winCheck("red") == true && tray.winCheck("blue") != true) //controlling blue token to error proof
                                                                {
                                                                    general.swapTokenToLight(buttonTokenArray[tray.getWinC1()][tray.getWinR1()]);
                                                                    general.swapTokenToLight(buttonTokenArray[tray.getWinC2()][tray.getWinR2()]);
                                                                    general.swapTokenToLight(buttonTokenArray[tray.getWinC3()][tray.getWinR3()]);                
                                                                    general.swapTokenToLight(buttonTokenArray[tray.getWinC4()][tray.getWinR4()]);
                                                                   //This code is just to invoke the win after the tokens are lite up, the 2 '!important' below are the key lines of interest in this bundle
                                                                        Task<Void> sleeper = new Task<Void>() 
                                                                                { @Override protected Void call() throws Exception { try { Thread.sleep(1000); }catch ( InterruptedException e ){ }return null;} };
                                                                        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() { @Override public void handle(WorkerStateEvent event)
                                                                                { 
                                                                                    general.youLose(); //!important
                                                                                    mainScene(); } //!important
                                                                                });
                                                                    new Thread(sleeper).start();
                                                                } 

                                                    //This just updates the lowerbanner display
                                                        Task<Void> sleeper = new Task<Void>() 
                                                                { @Override protected Void call() throws Exception { try { Thread.sleep(100); }catch ( InterruptedException e ){ }return null;} };
                                                        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() { @Override public void handle(WorkerStateEvent event)
                                                                {    
                                                                    general.swapBannerToON(labelPlayerStatus); //!important
                                                                }  
                                                        });
                                                        new Thread(sleeper).start();
                                                            }   
                                                    });
                                        }
                                }

                    //Popup introducing Master Kwan Chi:
                        general.introduceKwanChi();
                         
                    //Scene properties 
                       //Standard scene settings
                            Scene scene = new Scene(root, 490, 490);
                            swapStage.setTitle("LAB#3: Connect4 (DIZAD 03/03/17)");
                            swapStage.setScene(scene);
                            swapStage.setResizable(false);
                            swapStage.show();
                      //Adding the stylesheet
                            String css = Home.class.getResource("mainCSS.css").toExternalForm();
                            scene.getStylesheets().add(css); 
                }
//Main launch method************************************************************ 
        public static void main(String[] args)
            { launch(args); }
    }