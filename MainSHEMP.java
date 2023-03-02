import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainSHEMP extends StateBasedGame{

	public MainSHEMP(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SlickException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		JeuSHEMP J =new JeuSHEMP("SHOOT'EM UP");
		   AppGameContainer app = new AppGameContainer(new MainSHEMP("SHOOT'EM UP"));
		  //app.setFullscreen(true);
		   app.setDisplayMode(1100, 960, false);
		   app.setShowFPS(false);
		   app.start();
		   
		   
		   
   
		   
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		this.addState(new Avant_jeu(gc));
		this.addState(new Choix_joueur(gc));
		this.addState(new Choix_niveau(gc));
		this.addState(new JeuSHEMP("SHOOT'EM UP"));
		this.addState(new Menu(gc));
	}
	//id-->0=Avant_Jeu
	//id-->1=Choix_Joueur
	//id-->2=Choix_niveau
	//id-->3=jeuSHEMP
	//id-->4=Menu
	
	

}
