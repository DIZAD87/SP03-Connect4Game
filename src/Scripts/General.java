/*Daniel Izadnegahdar S00840086 IT2650 Lab#3 03/04/17*/
/*This class is used for general game methods*/
package Scripts;

//Imported libraries
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javax.swing.ImageIcon;
    import javax.swing.JOptionPane;

//Main class
public class General
    {
    //Properties
        //Instances of other objects
            PlaySound playSound = new PlaySound();

    //Methods
        //General game methods
            //Winning
                public void youWin()
                    {
                        playSound.playStart();
                        ImageIcon icon = new ImageIcon(Home.class.getResource("/Images/kwanChiIsUnhappy.png")); //Add image to popup
                        JOptionPane.showMessageDialog( null, "YOU WIN!! \n\n Master Kwan Chi \n IS\n NOT\n HAPPY!!!!!", "", JOptionPane.INFORMATION_MESSAGE, icon); 
                    }
            //Losing
                public void youLose()
                    {
                        playSound.playStart();
                        ImageIcon icon = new ImageIcon(Home.class.getResource("/Images/kwanChiIsHappy.png")); //Add image to popup
                        JOptionPane.showMessageDialog( null, "YOU LOSE!!\n\n Ha Ha Ha,\n nobody defeats\n Master Kwan Chi,\n mortal!!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                    }
            //Tying
                public void youTie()
                    {
                        playSound.playStart();
                        JOptionPane.showMessageDialog(null, "Game is a tie!");
                    }    
            //Introduce pop-up
                public void introduceKwanChi()
                    {
                        ImageIcon icon = new ImageIcon(Home.class.getResource("/Images/kwanChi.png"));
                        JOptionPane.showMessageDialog( null, "Excellent choice\n mortal! \n\n Today, you'll be\n competing\n against me:\n\n Master Kwan Chi!", "", JOptionPane.INFORMATION_MESSAGE, icon);
                    }
                
        //Graphical methods
            //Swap banner status to ON
                public void swapBannerToON(Label labelEntry)
                    {
                        labelEntry.getStyleClass().remove("labelPlayerStatusOff");
                        labelEntry.getStyleClass().add("labelPlayerStatusOn");    
                    }
            //Swap banner status to OFF
                public void swapBannerToOFF(Label labelEntry)
                    {
                        labelEntry.getStyleClass().remove("labelPlayerStatusOn");
                        labelEntry.getStyleClass().add("labelPlayerStatusOff");       
                    }
            //Swap token to blue
                public void swapTokenToBlue(Button buttonEntry)
                    {
                        buttonEntry.getStyleClass().remove("buttonEmptyTokenStyle"); 
                        buttonEntry.getStyleClass().add("buttonBlueTokenStyle"); 
                    }
            //Swap token to red
                public void swapTokenToRed(Button buttonEntry)
                    {
                        buttonEntry.getStyleClass().remove("buttonEmptyTokenStyle");
                        buttonEntry.getStyleClass().add("buttonRedTokenStyle");
                    }
            //Swap token to light
                public void swapTokenToLight(Button buttonEntry)
                    {
                        buttonEntry.getStyleClass().remove("buttonEmptyTokenStyle");
                        buttonEntry.getStyleClass().add("buttonLightTokenStyle");
                    }  
    }
