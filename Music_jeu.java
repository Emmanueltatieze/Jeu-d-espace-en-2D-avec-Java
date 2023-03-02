
import java.io.File; 
import java.util.Scanner; 
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.UnsupportedAudioFileException; 


public class Music_jeu {
	Clip clip ;
	URL sound;
	AudioInputStream ais;
	String chemin;
	
	public Music_jeu(String chemin)
	{
		  this.chemin=chemin;
		  this.sound=getClass().getResource(this.chemin);
			
	  
	  
	}
	public void setFile()
	{
		//this.chemin=chemin;
	  try { //this.sound=getClass().getResource(string);
		    this.ais= AudioSystem.getAudioInputStream(sound);
		    this.clip = AudioSystem.getClip();
		    this.clip.open(ais);
		    }catch (Exception e) {
				// TODO: handle exception
		    	System.out.println("Experienced an error while playing sound."); 
				e.printStackTrace(); 
		    	
			}
	 // this.sound=getClass().getResource(this.chemin);
//		
	}
	public void play()
	{
		this.clip.start();
		 
		
	}

	public void loop()
	{
		this.clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	public void stop()
	{
		this.clip.stop();
	}
}

