/*Daniel Izadnegahdar S00840086 IT2650 Lab#3 03/04/17*/
/*This class manages all the tray-related methods*/
package Scripts;

//Main class
public class Tray
    {
    //Properties
        //Variable properties
            int col;
            int row;
            int countFilledTokens;
        //Variables for win coordinates
            //Columns
                private int winC1;
                private int winC2;
                private int winC3;
                private int winC4;
            //Rows
                private int winR1;
                private int winR2;
                private int winR3;
                private int winR4;
        //Object properties
            General general = new General();
            Token[][] tokenArrayInMemory = new Token[7][6]; //Create a 7 X 6 array with token objects inside   

    //Constructor
        //Initiate the tray object by adding a blank token inside each array entry
            Tray()
                {
                    for (col = 0; col < 7; col++)//Loop through the columns
                      { for (row = 0; row < 6; row++)//Loop through the rows
                          { tokenArrayInMemory[col][row] = new Token(); } 
                      }
                }
    //Methods
        //Reset all tokens to false
            public void resetTokens()
                {for (col = 0; col < 7; col++)//Loop through the columns
                      { for (row = 0; row < 6; row++)//Loop through the rows
                          { 
                            tokenArrayInMemory[col][row].setTokenPresence(false, "blue");
                            tokenArrayInMemory[col][row].setTokenPresence(false, "red");
                          }  
                      }
                }
            
        //Check for token presence
            public boolean isXTokenPresent(int colEntry, int rowEntry, String tokenTypeEntry)
                {
                    if (tokenTypeEntry == "blue")
                        { return tokenArrayInMemory[colEntry][rowEntry].isTokenPresent("blue");}
                    else if (tokenTypeEntry == "red")
                        { return tokenArrayInMemory[colEntry][rowEntry].isTokenPresent("red");}
                    else {return false;}
                }
        
        //Check for full stack
            public boolean isStackFull(int colEntry)
                    {
                        if (tokenArrayInMemory[colEntry][0].isTokenPresent("blueOrRed") == true)
                            { return true; } //The stack is full
                        else
                            { return false; } //the stack is not full
                    }

        //Gravity insert
            public int gravityInsert(int colEntry, String tokenTypeEntry)
                {
                    int gravityRow;
                    for (gravityRow = 0; gravityRow < 6; gravityRow++)
                        {
                        //Scan for the highest token in the column and insert 1 row up
                            if (tokenArrayInMemory[colEntry][gravityRow].isTokenPresent("blueOrRed") == true && gravityRow > 0)
                                { 
                                    switch(tokenTypeEntry)
                                        {
                                            case "blue": tokenArrayInMemory[colEntry][gravityRow - 1].setTokenPresence(true, "blue"); //Update the token presence in memory
                                                         return (gravityRow - 1); //Return the coordinate so can be used to access the display settings in the home class
                                            case "red": tokenArrayInMemory[colEntry][gravityRow - 1].setTokenPresence(true, "red"); //Update the token presence in memory
                                                         return (gravityRow - 1); //Return the coordinate so can be used to access the display settings in the home class
                                            default: break;
                                        } 
                                }
                        //If it reached the bottom of the column with no token, insert token at bottom
                            else if (tokenArrayInMemory[colEntry][gravityRow].isTokenPresent("blueOrRed") == false && gravityRow == 5)
                                { 
                                    switch(tokenTypeEntry)
                                        {
                                            case "blue": tokenArrayInMemory[colEntry][5].setTokenPresence(true, "blue"); //Update the token presence in memory
                                                         return 5; //Update the token presence in memory
                                            case "red": tokenArrayInMemory[colEntry][5].setTokenPresence(true, "red"); //Update the token presence in memory
                                                         return 5; //Update the token presence in memory
                                            default: break;
                                        }
                                }
                        } 
                    //Else...
                        return 0;
                }
            
        //Check for win
            public boolean winCheck(String tokenTypeEntry)
                {
                //Check all directions
                    //NOTE: Reason a nested if statement was not used is because outofbound catch was exiting after each routine
                    if (
                            winCheckDirection(0 , -1 , -2 , -3 , 0 , 0 , 0 , 0 , tokenTypeEntry) == true || //Check Left
                            winCheckDirection(0 , -1 , -2 , -3 , 0 , 1 , 2 , 3, tokenTypeEntry) == true || //Check Left Down
                            winCheckDirection(0 , 0 , 0 , 0 , 0 , 1 , 2 , 3, tokenTypeEntry) == true || //Check Down
                            winCheckDirection(0 , 1 , 2 , 3 , 0 , 1 , 2 , 3, tokenTypeEntry) == true || //Check Down Right
                            winCheckDirection(0 , 1 , 2 , 3 , 0 , 0 , 0 , 0, tokenTypeEntry) == true || //Check Right
                            winCheckDirection(0 , 1 , 2 , 3 , 0 , -1 , -2 , -3, tokenTypeEntry) == true || //Check Right Up
                            winCheckDirection(0 , 0 , 0 , 0 , 0 , -1 , -2 , -3, tokenTypeEntry) == true || //Check Up
                            winCheckDirection(0 , -1 , -2 , -3 , 0 , -1 , -2 , -3, tokenTypeEntry) == true //Check Up Left
                        )
                            {return true;}
                        else
                            {return false;}
                }
            
        //Check direction method   
            public boolean winCheckDirection(int c1, int c2, int c3, int c4, int r1, int r2, int r3, int r4, String tokenTypeEntry)
                {
                        try
                            {//This try is for catching the out of bound indexes when searching, so it doesnt clog the console with red lines
                                for (col = 0; col < 7; col++)//Loop through the columns
                                  { for (row = 0; row < 6; row++)//Loop through the rows
                                      { 
                                        if (//If it finds a connect 4...
                                            tokenArrayInMemory[col + c1][row + r1].isTokenPresent(tokenTypeEntry) == true &&
                                            tokenArrayInMemory[col + c2][row + r2].isTokenPresent(tokenTypeEntry) == true &&
                                            tokenArrayInMemory[col + c3][row + r3].isTokenPresent(tokenTypeEntry) == true &&
                                            tokenArrayInMemory[col + c4][row + r4].isTokenPresent(tokenTypeEntry) == true 
                                            )             
                                            {
                                            //Record the win coordinates
                                                winC1 = col + c1; winR1 = row + r1;
                                                winC2 = col + c2; winR2 = row + r2;
                                                winC3 = col + c3; winR3 = row + r3;
                                                winC4 = col + c4; winR4 = row + r4;
                                                if (tokenTypeEntry.equals("blue") || tokenTypeEntry.equals("red"))
                                                    { return true; }       
                                            } 
                                        } 
                                  }
                            }
                        catch(ArrayIndexOutOfBoundsException e)
                        {System.out.println("WinCheck tried to assess an index outside of boundary...");}
                        //Else...
                        return false;
                }
            
        //Check for tie
            public boolean tieCheck()
                {
                 //Count the number of filled tokens and if it is the max - (4 - 1), return true, else false
                    countFilledTokens = 0; 
                    for (col = 0; col < 7; col++)//Loop through the columns
                          { for (row = 0; row < 6; row++)//Loop through the rows
                              { 
                                if (
                                    tokenArrayInMemory[col][row].isTokenPresent("blue") == true ||
                                    tokenArrayInMemory[col][row].isTokenPresent("red") == true
                                    )             
                        //If a connect 4 was found, finish game
                                    { countFilledTokens++;}    
                                } 
                            }
                    if (countFilledTokens > (42 - (4 - 1)))
                        { return true; }
                    else
                        { return false; }
                }
        
        //Get the win coordinates
            //Columns
                public int getWinC1()
                    {return winC1;}
                public int getWinC2()
                    {return winC2;}
                public int getWinC3()
                    {return winC3;}
                public int getWinC4()
                    {return winC4;}
            //Rows
                public int getWinR1()
                    {return winR1;}
                public int getWinR2()
                    {return winR2;}
                public int getWinR3()
                    {return winR3;}
                public int getWinR4()
                    {return winR4;}  
    }