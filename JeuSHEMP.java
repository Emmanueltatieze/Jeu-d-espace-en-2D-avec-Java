
import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.crypto.spec.GCMParameterSpec;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class JeuSHEMP extends BasicGameState {

	Music_jeu[] music; 
     
	static Image bg0;
	static Image bg0_b;
	Image[] ennemi;
	Image[] boss;
	Image joueur;
	Image block;
	
	static float bg0_y;
	static float bg0_b_y;
	
	int gauche;
	int droite;
	int haut;
	
	public static boolean menu=true;
	public static boolean choix_joueur=false;
	public static  boolean jeu=false;
	public static  boolean choix_niveau=false;
	public static  boolean commandes=false;
	public static  boolean gameOver=false;
	public static int init=0;
	boolean bouclier;
	boolean combat_avec_boss;

	 static int stage=1;
	static Fonctions f;
	static int  compt;
	
	Menu MyMenu;
	static Avant_jeu A;
	static Choix_joueur Cj;
	static Choix_niveau Cn;

	Music openingMenuMusic;
	Bouclier B;

	int T;
	int compt_stage;
	int fin_stage;
	int[] m;
	int[] time1;//temps mis par chaque ennemi avant de changer de mouvement
	int[] time2;//temps mis par chaque ennemi avant de tirer
	static int temps_ecouler;
	int temps_attente;
	int temps_attente_boss;
	int temps;
	int temp_bouclier;
	int temp_bouclier_actif;
	
	java.awt.Font font;
	TrueTypeFont ttf;
	
	Input inp;

	Joueur J;
	Ennemi[] X;
	public  Balles[] balle;
	public Point_animation[] P;//les points (coordonnees du joueur ou de/des ennemi(s) ) qui sont entrer en collision avec une balle
	
	
	private Music tir1;
	private Music tir2;
	Music_jeu GO;

	private int temps_avant_next_stage;

	private int v_bg=500;
	static int score;
	
	

	
	public JeuSHEMP(String title) {
		super();
		
	}
	
	
	
	public void initialiserJeu(GameContainer gc,StateBasedGame sbg) throws SlickException
	{
		init++;
		this.init2(gc,sbg);
		stage=1;
			Fonctions.Choix_etat(false, true, false, false, false);
			
		
	}
	public void recommencerNiveau(GameContainer gc,int stage,StateBasedGame sbg) throws SlickException
	{ 
		init++;
		this.init2(gc,sbg);
		this.stage=stage;
		f.setNiveau(stage);
		sbg.enterState(3,new FadeOutTransition(Color.black,1000),new FadeInTransition(Color.black,1000));
		
	}
	
	
	public static void playMusic(Music_jeu music)
   {
	
	music.setFile();
	music.play();
	music.loop();
	
   }
	public static void stopMusic(Music_jeu music)
	 {
		//music=new Music(chemin);
		music.stop();
	}
	
	public static void playSE(Music_jeu music)
	 {
		//music=new Music(chemin);
		music.setFile();
		music.play();
		
	
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void render(GameContainer gc, Graphics g,StateBasedGame sbg) throws SlickException {

		g.destroy();
		g.clear();
		
		
		if(gameOver)
		{
	
			f.GameOver(g, inp,sbg);
	
		}
		if(f.main)
		{
			
			f.main=false;
			initialiserJeu(gc,sbg);
			MyMenu.setChoix(0);
			f.setChoix(0);
			gameOver=false;
		}
			
		else if(f.rejouer)
		{
			
			recommencerNiveau(gc,stage,sbg);
			f.setChoix(0);
			gameOver=false;
			f.rejouer=false;
		}
		
		if(MyMenu.getChoix()==3)
		{
			//System.out.println(111);
			initialiserJeu(gc,sbg);
			MyMenu.setChoix(0);
			
		}
		if(MyMenu.getChoix()==1)
		{
			recommencerNiveau(gc,stage,sbg);
			MyMenu.setChoix(0);
			//Fonctions.Choix_etat(false, true, false, false, false);
		}
		

		
		if(jeu&&J.getSang_actuel()>0)
		{
			
		
			g.drawImage(bg0_b, 0, bg0_b_y,new Color(200,200,200,255));
			g.drawImage(bg0, 0, bg0_y,new Color(200,200,200,255));
			
//			if(combat_avec_boss&&!MyMenu.isPause_menu())
//			{
//				if((int)(Math.random()*2)==1)
//					g.drawImage(new Image("images/chien.png"), 0, 0,new Color(200,200,200,230));
//				else
//					g.drawImage(new Image("images/chien2.png"), 0, 0,new Color(200,200,200,230));
//					
//				
//			}
			
	     if(f.getStage_y()<600)    
		f.afficherNiveau(stage, g, new TrueTypeFont(new java.awt.Font("Forte", 10, 50), true));
	     
		 
			
			
			
			
			for(int i=0;i<X.length;i++)
			{  
				if(X[i]!=null&&X[i].getSang_e()>0)
				{
					X[i].Afficher_sang(g,null,X[i]);
					X[i].Afficher_E(g);
					
				}
			}
			
			
			if(droite==1)
			{
				J.setImj(Choix_joueur.Joueurs[Cj.getChoix()][3]);
				droite=0;
			}
			else if(gauche==1)
			{
				J.setImj(Choix_joueur.Joueurs[Cj.getChoix()][2]);
				gauche=0;
			}
			else if(haut==1)
			{
				J.setImj(Choix_joueur.Joueurs[Cj.getChoix()][1]);
				haut=0;
			}
			else
			{
				J.setImj(Choix_joueur.Joueurs[Cj.getChoix()][0]);
				droite=0;
				gauche=0;
				haut=0;
			}
			
			J.Afficher_sang(g,J,null);
			J.Afficher_J(g);
			if(temp_bouclier>15000&&!bouclier&&B==null)
			{	
				B=new Bouclier((int)(Math.random()*900+100), -200);
				//temp_bouclier=0;	
				bouclier=true;
				temp_bouclier=0;
			}
			
			if(B!=null&&!B.collision_avec_joueur(J))
			{
				B.afficherGainbouclier(g);
			}
			
			 if(B!=null&&B.collision_avec_joueur(J))
			{
					
				B.afficherbouclier(g, J);
				//bouclier=true;
				if(temp_bouclier_actif>20000)
				{
					bouclier=false;
					B=null;
					temp_bouclier_actif=0;
				}
			}
			
			
			
			
	       ////////affichage de toutes les balles tirées //////////
			for(int i=0;(balle[i]!=null);i++)
			{
				if(balle[i].getTir()==true)
				balle[i].Afficher_balle(g);
			}
			
			/////// affichage de toutes les animations crées /////////
			
			for(int i=0;(P[i]!=null);i++)
			{int t2=0;
			
				if(P[i].isEncours()&&compt<200)
				{
					P[i].afficher_animation(g);
					
					compt++;
				}
				else
				{
					P[i].setEncours(false);					
				}
			}
	
//			for(int i=0;i<=0;i++)
//			{
//				for(int j=0;j<1219/64;j++)
//				{
//					g.drawImage(block, j*64, i*64, new Color(200,200,200,250));
//				}
//			}
			g.drawImage(new Image("Decor/Decor1.png"), 0, 0);
			
			
			
			
			g.drawImage(new Image("images/temps.png"), 1100-160, 960-78);
			  ttf.drawString(1100-160+50/*10+300*/, 960-78+20/*1*/, new Temps(temps_ecouler).toString(), Color.white);
			 // font=new java.awt.Font("ALGERIAN", 40, 40);
			  ttf.drawString(gc.getWidth()/2-300, 1,"Stage "+ stage, Color.orange);
			  ttf.drawString(gc.getWidth()/2-80+100, 1,"score :"+ score, Color.yellow);
			  
			  
			
			  if(MyMenu.isPause_menu())
				{
					MyMenu.afficher_Menu(g, inp,sbg);
					//sbg.enterState(4);
					
				}
			
		 }
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void init2(GameContainer gc,StateBasedGame sbg) throws SlickException {
		
		A =new Avant_jeu(gc);
		Cj=new Choix_joueur(gc);
		Cn=new Choix_niveau(gc);
		gameOver=false;
		
		Choix_joueur.initJoueurs();
		 bouclier=false;
		temp_bouclier=0;
		temp_bouclier_actif=0;
		temps_avant_next_stage=0;
		 bg0_y=0;
		 bg0_b_y=0;
		 
	
		MyMenu=new Menu(gc);
		f=new Fonctions();
		X=new Ennemi[(int)(Math.random()*(8-5)+5)*0+7];	
		balle=new Balles[1000];
		P=new Point_animation[1000];
		joueur=new Image("Images/joueur1.png");
		block = new Image("Images/block.png");
		J=new Joueur(gc,500,500,joueur,joueur.getWidth(),joueur.getHeight());
		J.initialiser(gc);
		f.setNiveau(stage);
		
		openingMenuMusic = new Music("Sound/bg_1.wav");
		openingMenuMusic.play();
	    openingMenuMusic.loop();
	    
	   // openingMenuMusic.setVolume(300);
	   if(init!=1)
	   {
	    ennemi=new Image[7];
		ennemi[0]=new Image("Ennemis/enemi0.png");
		ennemi[1]=new Image("Ennemis/enemi1a.png");
		ennemi[2]=new Image("Ennemis/enemi2a.png");
		ennemi[3]=new Image("Ennemis/enemi3a.png");
		ennemi[4]=new Image("Ennemis/enemi4a.png");
		ennemi[5]=new Image("Ennemis/enemi5a.png");
		ennemi[6]=new Image("Ennemis/enemi6a.png");
		
		boss=new Image[6];
		boss[0]=new Image("Ennemis/enemi1.png");
		boss[1]=new Image("Ennemis/enemi2.png");
		boss[2]=new Image("Ennemis/enemi3.png");
		boss[3]=new Image("Ennemis/enemi4.png");
		boss[4]=new Image("Ennemis/enemi5.png");
		boss[5]=new Image("Ennemis/enemi6.png");
    
		
		music=new Music_jeu[10];
		music[0]= new Music_jeu("Sound/mixkit_bg.wav");
		music[1]= new Music_jeu("Sound/mixkit_.wav");
		music[2]= new Music_jeu("Sound/mixkit_tir0_.wav");
		music[3]= new Music_jeu("Sound/explosion.wav");
		 GO=new Music_jeu("Sound/gameOver.wav");
	   }
		
		
		
		fin_stage=0;
		score=0;
		combat_avec_boss=false;
		Point_animation.init_animations();
		gauche=0;
		droite=0;
		haut=0;
		T=0;
		temps=0;
		temps_ecouler=0;
		temps_attente_boss=0;
		temps_attente=0;
		temp_bouclier=0;
		temp_bouclier_actif=0;
		B=null;
		compt=0;
		inp=gc.getInput();

		font=new java.awt.Font("ALGERIAN", 50, 30);
		ttf = new TrueTypeFont(font, true);
		
		
		
		compt_stage=0;
		for(int i=0;i<X.length;i++)
		{
			if(stage<5)
				X[i] =new Ennemi(gc,ennemi[(int)(Math.random()*(stage+2+1-stage)+stage)], 0, 0,(int)(Math.random()*(stage+2+1-stage)+stage),J);
			else
				X[i] =new Ennemi(gc,ennemi[(int)(Math.random()*(stage+2-(stage-2))+(stage-2))], 0, 0,(int)(Math.random()*(stage+1-(stage-2))+(stage-2)),J);
		  X[i].initialiser(gc,X,i);
		}
	
	
		m=new int[X.length]; //(int)(Math.random()*8); //permet de decrire un mouvement aleatoire initiale de l'ennemi 
		for(int i=0;i<m.length;i++)
		{
			m[i]=(int)(Math.random()*(8));
		}
		time1=new int[8];
		for(int i=0;i<time1.length;i++)
		{
			int a=(int)(Math.random()*8);
			switch(a)
			{
			case 0:
				time1[i]=3500;
				break;
			case 5:
				time1[i]=1200;
				break;
			case 2:
				time1[i]=2300;
				break;
			case 7:
				time1[i]=3900;
				break;
			case 4:
				time1[i]=4800;
				break;
			case 1:
				time1[i]=1500;
				break;
			case 6:
				time1[i]=5100;
				break;
			case 3:
				time1[i]=4004;
				break;
			default :
				break;
			
				
			}
			
			
			
		}
		time2=new int[8];
		for(int i=0;i<time2.length;i++)
		{
			int a=(int)(Math.random()*8);
			switch(a)
			{
			case 0:
				time2[i]=1300;
				break;
			case 5:
				time2[i]=600;
				break;
			case 2:
				time2[i]=2400;
				break;
			case 7:
				time2[i]=1500;
				break;
			case 4:
				time2[i]=2900;
				break;
			case 1:
				time2[i]=900;
				break;
			case 6:
				time2[i]=700;
				break;
			case 3:
				time2[i]=1550;
				break;
			default :
				break;
			
				
			}
			
		}

	 
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void update(GameContainer gc, int alpha,StateBasedGame sbg) throws SlickException {
		
		if(fin_stage==1)
		{
		 temps_avant_next_stage+=alpha;
		 v_bg=900;
		 //J.setV(500);
		 J.Haut2(gc, alpha);
		}
		else {
			temps_avant_next_stage=0;
			 v_bg=700;
			
		}
		if( fin_stage==1&&temps_avant_next_stage>5000)
		{
			
			fin_stage=0;
			stage++;
			recommencerNiveau(gc, stage, sbg);
		}
		//playMusic("/Sound/mixkit_bg.wav");
//		
//		if(menu)
//		{
//			A.modif_image_fond(alpha);
//			//A.modif_image_fond(alpha);
//			
//		}
//		if(choix_joueur)
//		{
//			Cj.modif_image_fond(alpha);
//			
//		}
		
		
		if(jeu&&!MyMenu.isPause_menu()&&!gameOver)
		{
			if(inp.isKeyPressed(Input.KEY_SPACE))
			{
				MyMenu.setPause_menu(true);
				MyMenu.entrer_menu=false;
			}
				
			
			
			f.Stage_Bas(gc, alpha);
			   
			
			
				temps_ecouler+=alpha;
				if(!bouclier||B==null)
				temp_bouclier+=alpha;
				
				if(B!=null&&bouclier&&B.collision_avec_joueur(J))
				{
				temp_bouclier_actif+=alpha;
				}
				if(B!=null&&bouclier&&!B.collision_avec_joueur(J))
				{
					
					B.Bas(gc, alpha);
					
				}
					
				
				//playMusic();
				bg0_y=bg0_y+ v_bg*alpha/1000f;
				
				bg0_b_y=bg0_b_y+ v_bg*alpha/1000f;
				
				if(bg0_y>bg0.getHeight())
				{
					bg0_y=bg0_b_y-bg0.getHeight();
					//bg0=bg0_b;
				}
				if(bg0_b_y>bg0.getHeight())
				{
					bg0_b_y=bg0_y-bg0.getHeight();
					
				}
		
				if(Ennemi.nbre_ennemi_restant(X)<=5 && compt_stage<1&&combat_avec_boss==false&&fin_stage==0)
				{
					
					temps_attente+=alpha;
					if(temps_attente>4000)
					{
						temps_attente=0;
						for(int i=0;i<X.length;i++)
						{
							
							if(X[i]==null)
							{
								if(stage<5)
									X[i] =new Ennemi(gc,ennemi[(int)(Math.random()*(stage+2))], 0, 0,(int)(Math.random()*(stage+2+1-stage)+stage),J);
								else
									X[i] =new Ennemi(gc,ennemi[(int)(Math.random()*(stage+1))], 0, 0,(int)(Math.random()*(stage+1-(stage-2))+(stage-2)),J);
								X[i].initialiser(gc,X,i);
		//						X[i].initialiser_balles();
								
							}
						}
						compt_stage++;
						
					}
					 
				}
				if(compt_stage>=1 && Ennemi.nbre_ennemi_restant(X)==0&&fin_stage==0)
				{	
					
					temps_attente_boss+=alpha;
					if(temps_attente_boss>8000 &&combat_avec_boss==false)
					{
					 X[0]= new Ennemi(gc,boss[stage-1/*(int)(Math.random()*(6))*/],gc.getWidth()/2, -400, 7, J);
					 X[0].initialiser(gc,X,0);
					 compt_stage=0;
		//			 X[0].initialiser_balles();
					 
					 openingMenuMusic.stop();
					 openingMenuMusic = new Music("Sound/bg_2.wav");
						openingMenuMusic.play();
					    openingMenuMusic.loop();
					   // openingMenuMusic.setVolume(1);
					    
					 combat_avec_boss=true;
					 temps_attente_boss=0;
					}
			
				}
				if(combat_avec_boss==true&& X[0]==null)
					{
						openingMenuMusic.stop();
						 openingMenuMusic = new Music("Sound/bg_3.wav");
							openingMenuMusic.play();
						    openingMenuMusic.loop();
						   // openingMenuMusic.setVolume(1);
						    fin_stage=1;
						    combat_avec_boss=false;
						    
			         
					}
				
				if(inp.isKeyDown(Input.KEY_RIGHT)&&fin_stage!=1)
				{
					//if(!J.collision_Droit(X))
					{
						J.Droite(gc, alpha);
						droite=1;
					}
					
						
				}
				
				if(inp.isKeyDown(Input.KEY_LEFT)&&fin_stage!=1)
				{
					//if(!J.collision_Gauche(X))
					{
						J.Gauche(gc, alpha);
						gauche=1;
					}
				}
				 
				if(inp.isKeyDown(Input.KEY_UP)&&fin_stage!=1)
				{	
					//if(!J.collision_Haut(X))		 
						J.Haut(gc, alpha);
						haut=1;
				}
				if(inp.isKeyDown(Input.KEY_DOWN)&&fin_stage!=1)
				{	
					//if(!J.collision_Bas(X))		 
						J.Bas(gc, alpha);
				}
				
				temps+=alpha;
				if(inp.isKeyPressed(Input.KEY_W)&&fin_stage!=1)
				{playSE(music[1]);
				//tir1.play();
				//tir1.setVolume(1);
					
					int c=0;
					for(int i=0;(balle[i]!=null);i++)
					{
						c++;
						if(balle[i].getTir()==false)
						break;
					}
					if(c>0&&balle[c-1].getTir()==false)
					{
					balle[c-1]=new Balles((int)(Math.random()*(/*stage*/1+1)),J.getX()+J.getImg_l()/2,J.getY(),"H","J");
					
					balle[c-1].setTir(true);
				
					//
					}
					else if(c==0||balle[c]==null)
					{
						balle[c]=new Balles((int)(Math.random()*(/*stage*/1+1)),J.getX()+J.getImg_l()/2,J.getY(),"H","J");
						
						balle[c].setTir(true);
						//
					}
					
		
				}
				if(inp.isKeyPressed(Input.KEY_S)&&fin_stage!=1)
				{ playSE(music[1]);
					//tir1.play();
					//tir1.setVolume(10);
					int c=0;
					for(int i=0;(balle[i]!=null);i++)
					{
						c++;
						if(balle[i].getTir()==false)
						break;
					}
					if(c>0&&balle[c-1].getTir()==false)
					{
					balle[c-1]=new Balles(0,J.getX()+J.getImg_l()/2,J.getY()+J.getImg_h(),"B","J");
					balle[c-1].setTir(true);
					}
					else if(c==0||balle[c]==null)
					{
						balle[c]=new Balles(0,J.getX()+J.getImg_l()/2,J.getY()+J.getImg_h(),"B","J");
						balle[c].setTir(true);
					}
					
		
				}
				if(inp.isKeyPressed(Input.KEY_A)&&fin_stage!=1)
				{ playSE(music[1]);
					//tir1.play();
					//tir1.setVolume(10);
					int c=0;
					for(int i=0;(balle[i]!=null);i++)
					{
						c++;
						if(balle[i].getTir()==false)
						break;
					}
					if(c>0&&balle[c-1].getTir()==false)
					{
					balle[c-1]=new Balles(0,J.getX(),J.getY()+J.getImg_h()/2,"G","J");
					balle[c-1].setTir(true);
					}
					else if(c==0||balle[c]==null)
					{
						balle[c]=new Balles(0,J.getX(),J.getY()+J.getImg_h()/2,"G","J");
						balle[c].setTir(true);
					}
					
		
				}
				if(inp.isKeyPressed(Input.KEY_D)&&fin_stage!=1)
				{ playSE(music[1]);
					//tir1.play();
					//tir1.setVolume(10);
					int c=0;
					for(int i=0;(balle[i]!=null);i++)
					{
						c++;
						if(balle[i].getTir()==false)
						break;
					}
					if(c>0&&balle[c-1].getTir()==false)
					{
					balle[c-1]=new Balles(0,J.getX()+J.getImg_l(),J.getY()+J.getImg_h()/2,"D","J");
					balle[c-1].setTir(true);
					}
					else if(c==0||balle[c]==null)
					{
						balle[c]=new Balles(0,J.getX()+J.getImg_l(),J.getY()+J.getImg_h()/2,"D","J");
						balle[c].setTir(true);
					}
					
		
				}
				
					
				//tir1.stop();
				
		/////////////////////////////// ENNEMI ////////////////////////////
					//
				for(int i=0;i<X.length;i++)
				{
					if(X[i]==null)
						continue;
					
					X[i].setTemps1(X[i].getTemps1()+alpha);
				}
				
				for(int i=0;i<X.length;i++)
				{
					if(X[i]==null)
					continue;
					
					int T1=6000;
				   if(X[i].getType_enemi()==7)
				   {
					  T1=1500+time1[(int)(Math.random()*5)]; 
					  X[i].setV((int)(Math.random()*(300-100)*stage)+100);
					  
				   }
				    
					
					if(X[i].getTemps1()>=T1/*time1[i]+6000*/)
					{
					// System.out.println(X[i].getTemps1());
					 m[i]=(int)(Math.random()*8);
					 
					 X[i].setTemps1(0);
					}
		           
				}
				for(int i=0;i<X.length;i++)
				{
					if(X[i]==null)
						continue;
					
					if(/*(X[i].getX()>=0&&X[i].getX()+X[i].getImg_l()<=gc.getWidth())&&*/X[i].getY()<=0)
					{
						int c=(int)(Math.random()*3);
						if(c==0)
							X[i].Bas(gc, alpha);
						if(c==1)
							X[i].Transversale_3(gc, alpha);
						if(c==2)
							X[i].Transversale_1(gc, alpha);
					}
					else if(/*(X[i].getY()>=0&&X[i].getY()<=gc.getWidth())&&*/X[i].getX()<0)
					{
						int c=(int)(Math.random()*2);
						if(c==0)
							X[i].Droite(gc, alpha);
						if(c==1)
							X[i].Transversale_1(gc, alpha);
						
							
					}
					else if(/*(X[i].getY()>=0&&X[i].getY()<=gc.getWidth())&&*/X[i].getX()+X[i].getImg_l()>gc.getWidth())
					{
						int c=(int)(Math.random()*3);
						if(c==0)
							X[i].Gauche(gc, alpha);
						if(c==1)
							X[i].Transversale_3(gc, alpha);
						
					}
				else
				{
		
					switch(m[i])
					{
					case 3:
						if(!X[i].collision_E_D(X,i))
							X[i].Droite( gc, alpha);
						else if(!X[i].collision_E_G(X,i)) 
							X[i].Gauche(gc, alpha);
						
						break;
					case 4:
						if(!X[i].collision_E_G(X,i))
							X[i].Gauche(gc, alpha);
						else if(!X[i].collision_E_D(X,i)) //if(X[i].getType_enemi()==7)
							X[i].Droite(gc, alpha);
						break;
					case 2:
						if(!X[i].collision_E_B(X,i))
							X[i].Bas( gc, alpha);
						else if(X[i].getType_enemi()==7)
							X[i].Haut(gc, alpha);
						break;
					case 7:
		//				
						if(X[i].getType_enemi()==7)
							X[i].Haut( gc, alpha);
						else
							X[i].Bas(gc, alpha);
		//				break;
					case 0:
						//if(!X[i].collision_E_B(X,i)&&!X[i].collision_E_D(X,i))
							X[i].Transversale_1(gc, alpha);//x>=0 et y>=0
						//else
						//	X[i].Transversale_3(gc, alpha);//x<=0 et y>=0
						break;
					case 1:
						//if(!X[i].collision_E_G(X,i)&&!X[i].collision_E_B(X,i))
							X[i].Transversale_3(gc, alpha);//x<=0 et y>=0
						//else
						//	X[i].Transversale_1(gc, alpha);//x>=0 et y>=0
						break;
						
					case 6:
						//if(X[i].getType_enemi()==7)//if(!X[i].collision_E_D(X,i)&&!X[i].collision_E_B(X,i))
							X[i].Transversale_2(gc, alpha);//x>=0 et y<=0
						//else
						//	X[i].Transversale_3(gc, alpha);//x<=0 et y>=0
						break;
						
					case 5:
						//if(X[i].getType_enemi()==7)//if(!X[i].collision_E_G(X,i)&&!X[i].collision_E_B(X,i))
							X[i].Transversale_4(gc, alpha);//x<=0 et y<=0
						//else
						//	X[i].Transversale_1(gc, alpha);//x>=0 et y>=0
						break;
					default :
						break;
					
						
					}
				}
				 
				}
				
				for(int i=0;i<X.length;i++)
				{
					if(X[i]==null)
						continue;
					int T2=time2[i]+2000;
					   if(X[i].getType_enemi()==7)
					   {
					    T2=time2[(int)(Math.random()*5)]+100;
					   }
					      X[i].setTemps2(X[i].getTemps2()+alpha);
					if((X[i].getTemps2()>=T2/*time2[(int)(Math.random()*6)]*/)&&(X[i].getX()>0&&X[i].getX()+X[i].getImg_l()<gc.getWidth()&&X[i].getY()+X[i].getImg_h()<gc.getHeight()&&X[i].getY()>0))
					{
						playSE(music[2]);
						//tir2.play();
						//tir2.setVolume(50);
						
							X[i].Tirer(balle,J,X[i]);
							
		
						
						X[i].setTemps2(0);
					}
				}
		///////////////// MODIFICATION DES COORDONNEES DE TOUTES LES BALLES /////////////////
					
					if(temps>=5)
					{
						for(int i=0;(balle[i]!=null);i++)
						{
							if(balle[i].getTir() )
							{
								float b=balle[i].getV()*alpha/1000f;
							    float a=balle[i].getV()*alpha/1000f;
								balle[i].avancer_balle(gc,alpha);
								
								if(balle[i].getDirection()=="H"&&balle[i].getY()-a<0)
									balle[i].setTir(false);
								else if(balle[i].getDirection()=="B"&&balle[i].getY()+b+balle[i].getImg_h()>gc.getHeight())
									balle[i].setTir(false);
								else if(balle[i].getDirection()=="G"&&balle[i].getX()-a<0)
									balle[i].setTir(false);
								else if(balle[i].getDirection()=="D"&&balle[i].getX()+a+balle[i].getImg_l()>gc.getWidth())
									balle[i].setTir(false);
								else if(balle[i].getDirection()=="T1"&&(balle[i].getX()+a+balle[i].getImg_l()>gc.getWidth()&&balle[i].getY()+b+balle[i].getImg_h()>gc.getHeight()))
									balle[i].setTir(false);
								else if(balle[i].getDirection()=="T2"&&(balle[i].getX()+a+balle[i].getImg_l()>gc.getWidth()&&balle[i].getY()-a<0))
									balle[i].setTir(false);
								else if(balle[i].getDirection()=="T3"&&(balle[i].getX()-a<0&&balle[i].getY()+b+balle[i].getImg_h()>gc.getHeight()))
									balle[i].setTir(false);
								else if(balle[i].getDirection()=="T4"&&(balle[i].getX()-a<0&&balle[i].getY()-a<0))
									balle[i].setTir(false);
								else if(/*(balle[i].collision_avec_Ennemi(X)!=null||balle[i].collision_avec_Joueur(J)!=null)&&*/balle[i].getTir())
								{
									for(int p=0;p<X.length;p++)
									{
									 if(X[p]!=null&&(balle[i].collision_avec_Ennemi(X[p])!=null||balle[i].collision_avec_Joueur(J)!=null))
									  { 
										 
										 
										int c=0;
										for(int k=0;(P[k]!=null);k++)
										{
											c++;
											if(P[k].isEncours()==false)
											break;
										}
										
										if(c>0&&P[c-1].isEncours()==false)
										{compt=0;
											if(balle[i].collision_avec_Ennemi(X[p])!=null)
											{playSE(music[3]);
												score+=X[p].getSang_e();
												
												P[c-1]=new Point_animation(null,X[p], balle[i].getType_balle());
												if(X[p].getX()+X[p].getImg_l()>0&&X[p].getX()<gc.getWidth()&&X[p].getY()>0)
												X[p].setSang_e_actuel(X[p].getSang_e_actuel()-balle[i].getImpact());
												
												//temps_anim[c-1]+=alpha;
											}
											else if(balle[i].collision_avec_Joueur(J)!=null)
											{
												if(bouclier&&B!=null&&B.collision_avec_joueur(J))
													balle[i].setTir(false);
												//System.out.println(i);
												else
												{playSE(music[3]);
												P[c-1]=new Point_animation(J, null, balle[i].getType_balle());
												J.setSang_actuel(J.getSang_actuel()-balle[i].getImpact());
												}
												//temps_anim[c-1]+=alpha;
											}
											
										
										}
										else if(c==0||P[c]==null)
										{compt=0;
										
										 
											if(balle[i].collision_avec_Ennemi(X[p])!=null)
											{playSE(music[3]);
												score+=X[p].getSang_e();
												
												P[c]=new Point_animation(null,X[p], balle[i].getType_balle());
												if(X[p].getX()+X[p].getImg_l()>0&&X[p].getX()<gc.getWidth()&&X[p].getY()>0)
												X[p].setSang_e_actuel(X[p].getSang_e_actuel()-balle[i].getImpact());
												//temps_anim[c]+=alpha;
											}
											else if(balle[i].collision_avec_Joueur(J)!=null)
											{
												if(bouclier&&B!=null&&B.collision_avec_joueur(J))
												balle[i].setTir(false);
												//
												else
												{playSE(music[3]);
												P[c]=new Point_animation(J, null, balle[i].getType_balle());
												J.setSang_actuel(J.getSang_actuel()-balle[i].getImpact());
												}
												
											}
											
										
										}
										balle[i].setTir(false);
									  }
										
									}
								}
									
									
								
							}
						}
						for(int i=0;(P[i]!=null);i++)
						{
							if(P[i].isEncours()==false)
							{
								
								P[i]=null;
							}
						
						}
						temps=0;
					}
					for(int i=0;i<X.length;i++)
					{
						if(X[i]==null)
							continue;
						if(X[i].getSang_e_actuel()<=0||X[i].getY()>gc.getHeight())
						{
							
							X[i]=null;
						}
					
					}
				
					
		
			}
		
		if(J.getSang_actuel()<=0&&!gameOver)
		{
			openingMenuMusic.stop();
			playSE(GO);
		}
		if(J.getSang_actuel()<=0)
		{
			
			gameOver=true;
			f.game_Over=true;
			
		}
		
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.init2(gc,sbg);
		Fonctions.Choix_etat(false, true, false, false, false);
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		this.render(gc, g,sbg);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		
		this.update(gc, alpha,sbg);
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}
	

}
