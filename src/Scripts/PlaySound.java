/*Daniel Izadnegahdar S00840086 IT2650 Lab#3 03/04/17*/
/*This class manages the sounds for the game*/
package Scripts;

//Imported libraries
    import javafx.scene.media.AudioClip;

//Main class
public class PlaySound
    {
    //Instances of the audio clips
        AudioClip playButtonSound = new AudioClip(this.getClass().getResource("/Sounds/pressButton.wav").toExternalForm());
        AudioClip playStartSound = new AudioClip(this.getClass().getResource("/Sounds/startGame.wav").toExternalForm());
        AudioClip playDropToken = new AudioClip(this.getClass().getResource("/Sounds/dropToken.wav").toExternalForm());
        AudioClip playMusic = new AudioClip(this.getClass().getResource("/Sounds/music.mp3").toExternalForm());

    //Methods
        public void playButton()
            { playButtonSound.play(); }

        public void playToken()
            { playDropToken.play(); }
               
        public void playStart()
            {
               playStartSound.setVolume(0.1);
               playStartSound.play(); 
            } 
        
        public void playMusic()
            { playMusic.play(); }               
    }
