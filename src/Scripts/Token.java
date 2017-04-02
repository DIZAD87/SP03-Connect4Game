/*Daniel Izadnegahdar S00840086 IT2650 Lab#3 03/04/17*/
/*This class relates to all the methods relating to the individual tokens inside the tray*/
package Scripts;

//Main class
public class Token
    {
    //Properties
        //Variables
            private boolean isBlueTokenPresent;
            private boolean isRedTokenPresent;
        //Instances of other objects
            PlaySound playSound = new PlaySound();

    //Constructor
        Token()
            {
                this.isBlueTokenPresent = false;
                this.isRedTokenPresent = false;
            }
        
    //Methods
        //Get methods
            public boolean isTokenPresent(String tokenTypeEntry)
                { 
                    switch(tokenTypeEntry)
                        {
                            case "blue": return isBlueTokenPresent; 
                            case "red": return isRedTokenPresent;
                            case "blueOrRed":  if (isRedTokenPresent == true || isBlueTokenPresent == true) { return true; };
                            default: break;
                        }
                    //Else...
                        return false;
                }              
            
        //Set method
            public void setTokenPresence(boolean isTokenPresentEntry, String tokenTypeEntry)
                {  playSound.playToken(); //Play sound effect
                    switch(tokenTypeEntry)
                    {
                        case "blue": isBlueTokenPresent = isTokenPresentEntry; break; //Udpate status
                        case "red": isRedTokenPresent = isTokenPresentEntry; break; //Udpate status
                        default: break;
                    }  
                }            
    }
