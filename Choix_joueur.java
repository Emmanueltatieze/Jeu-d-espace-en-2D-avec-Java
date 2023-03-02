import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Choix_joueur extends BasicGameState{
	Input inp;
	
	private  Image bg_acceuil;
	private  Image bg_acceuil2;
	
	 float   bg_acceuil_x=0;
	 float  bg_acceuil2_x;
	 
	     private  Image bg;
	     private  Image img;
		
		 private static int comp=0;
		 
		 private int nbre_boutton;
		 private String[] menu= {"CHOISISSEZ VOTRE VAISSEAU","SOLO","COMMANDES"};
		 private static int choix;
		 
		 private Image[] imj;
		 
		 public static Image[][] Joueurs=new Image[5][4];
		 private java.awt.Font font=new java.awt.Font("ALGERIAN", 1, 35);
		 private TrueTypeFont ttf= new TrueTypeFont(font, true);

		public static boolean entrer_choix_joeur=false;
		public static boolean escape_choix_joueur=false;
		

		Music_jeu clic=new Music_jeu("Sound/clic.wav");
		Music_jeu select=new Music_jeu("Sound/mixkit_.wav");		
		
		 
		 public static void initJoueurs() throws SlickException
		 {
			Joueurs[0][0]=new Image("Joueurs/J0.png");Joueurs[0][1]=new Image("Joueurs/J0_h.png");Joueurs[0][2]=new Image("Joueurs/J0_g.png");Joueurs[0][3]=new Image("Joueurs/J0_d.png");
			Joueurs[1][0]=new Image("Joueurs/J1.png");Joueurs[1][1]=new Image("Joueurs/J1_h.png");Joueurs[1][2]=new Image("Joueurs/J1_g.png");Joueurs[1][3]=new Image("Joueurs/J1_d.png");
			Joueurs[2][0]=new Image("Joueurs/J2.png");Joueurs[2][1]=new Image("Joueurs/J2_h.png");Joueurs[2][2]=new Image("Joueurs/J2_g.png");Joueurs[2][3]=new Image("Joueurs/J2_d.png");
			Joueurs[3][0]=new Image("Joueurs/J3.png");Joueurs[3][1]=new Image("Joueurs/J3_h.png");Joueurs[3][2]=new Image("Joueurs/J3_g.png");Joueurs[3][3]=new Image("Joueurs/J3_d.png");
			Joueurs[4][0]=new Image("Joueurs/J4.png");Joueurs[4][1]=new Image("Joueurs/J4_h.png");Joueurs[4][2]=new Image("Joueurs/J4_g.png");Joueurs[4][3]=new Image("Joueurs/J4_d.png");

			
			
		 }
	    
	 

		 
			
		public Choix_joueur(GameContainer gc) throws SlickException {
			
			super();
			
			bg_acceuil=new Image("Choix_joueur/bg_Choix_joueur.png");
			 bg_acceuil2=bg_acceuil.getFlippedCopy(true, false);
			 bg_acceuil2_x=bg_acceuil_x+bg_acceuil.getWidth();

			
			bg=new Image("Choix_joueur/bg_Choix_joueur.png");
			img=new Image("Choix_joueur/bg_Choix_joueur2.png");
			
			imj=new Image[5];
			
			
			
			
			nbre_boutton=imj.length;
			comp=nbre_boutton/2;
			
				imj[0]=new Image("images/joueur.png");
				imj[1]=new Image("images/joueur2.png");
				imj[2]=new Image("images/joueur3.png");
				imj[3]=new Image("images/joueur4.png");
				imj[4]=new Image("images/joueur5.png");
			
			
			
			
			
		}
		public void affichages(Graphics g, Input inp, StateBasedGame sbg) throws SlickException
		{
			g.destroy();
			g.drawImage(bg_acceuil, bg_acceuil_x, 0);
			g.drawImage(bg_acceuil2, bg_acceuil2_x, 0);
			
			
			//g.drawImage(bg, 0, 0);
			g.drawImage(img, 1100/2-img.getWidth()/2, 960/2);
			g.drawImage(new Image("Avant_jeu/logo.png"), 0, 750,new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			g.drawImage(new Image("images/boutton_rouge.png"), 0, 0);
			if(Dans_le_bouton(inp.getMouseX(), inp.getMouseY()))
			ttf.drawString(50, 10,"RETOUR",Color.blue);
			else
				ttf.drawString(50, 10,"RETOUR",Color.white);
			
			if(comp!=nbre_boutton-1)
			g.drawImage(new Image("Choix_joueur/left2.png"), 1100/2-img.getWidth()/2-200, 960/2-100);
			if(comp!=0)
			g.drawImage(new Image("Choix_joueur/right2.png"), 1100/2-img.getWidth()/2+200*2, 960/2-100);
			ttf.drawString(1100/2-250, 150,menu[0],Color.yellow);
			
			//System.out.println(entrer_choix_joeur);
			if(inp.isKeyPressed(Input.KEY_ENTER)&&!entrer_choix_joeur)
			{
				JeuSHEMP.playSE(select);
				entrer_choix_joeur=true;
				Choix_niveau.entrer_choix_niveau=false;
				Choix_niveau.escape_choix_niveau=false;
				setChoix(comp);
				
					if(Avant_jeu.comp==1)
					{
						Fonctions.Choix_etat(false, false, true, false, false);
						sbg.enterState(2,new FadeOutTransition(Color.black,1000),new FadeInTransition(Color.black,1000));
						
					}
					System.out.println(Avant_jeu.comp);
					if(Avant_jeu.comp==0)
					{
						Fonctions.Choix_etat(false, true, false, false, false);
						JeuSHEMP.stage=1;
						sbg.enterState(3,new FadeOutTransition(Color.black,1000),new FadeInTransition(Color.black,1000));
						
					}
				
			}
			else if(inp.isKeyPressed(Input.KEY_ESCAPE)&&!escape_choix_joueur)
			{
				escape_choix_joueur=true;
				Avant_jeu.entrer_avant_jeu=false;
				//entrer_choix_joeur=false;
				Fonctions.Choix_etat(true, false, false, false, false);
				sbg.enterState(0,new FadeOutTransition(Color.black,1000),new FadeInTransition(Color.black,1000));
				
			}
			else if(inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)&&Dans_le_bouton(inp.getMouseX(), inp.getMouseY()))
			{
				escape_choix_joueur=true;
				Avant_jeu.entrer_avant_jeu=false;
				Fonctions.Choix_etat(true, false, false, false, false);
				sbg.enterState(0,new FadeOutTransition(Color.black,1000),new FadeInTransition(Color.black,1000));
				
			}
			
			
			else if(inp.isKeyPressed(Input.KEY_LEFT)&&comp!=nbre_boutton-1)
			{
				JeuSHEMP.playSE(clic);
				if(comp>=nbre_boutton-1)
				{
				 comp=0;
				}
				else
					comp++;
				
			}
			else if(inp.isKeyPressed(Input.KEY_RIGHT)&&comp!=0)
			{
				JeuSHEMP.playSE(clic);
				if(comp<=0)
				{
				 comp=nbre_boutton-1;
				}
				else
					comp--;

			}

			
			for(int i=0;i<nbre_boutton;i++)
			{
				if(comp==i)
				{

					g.drawImage(imj[i], 1100/2-imj[i].getWidth()/2, 960/2-imj[i].getHeight()-10);
				  
				}
				else
				{
				  	
				}
					
				
			}
		   
			
		}
		private boolean Dans_le_bouton(int x, int y) throws SlickException {
			if(x>10&&x<new Image("images/boutton_rouge.png").getWidth()&&y>10&&y<new Image("images/boutton_rouge.png").getHeight())
				return true;
			return false;

			}
		public int getChoix() {
			return choix;
		}
		public void setChoix(int choix) {
			if(choix>=0&&choix<this.nbre_boutton)
			this.choix = choix;
		}

		
	
		public void modif_image_fond(int alpha) throws SlickException
		{
			bg_acceuil_x-= 150*alpha/1000f;
			
			bg_acceuil2_x-= 150*alpha/1000f;
			
			if(bg_acceuil_x+bg_acceuil.getWidth()<=0)
			{
				bg_acceuil_x=bg_acceuil2_x+bg_acceuil.getWidth();
				//bg0=bg0_b;
			}
			if(bg_acceuil2_x+bg_acceuil.getWidth()<=0)
			{
				bg_acceuil2_x=bg_acceuil_x+bg_acceuil.getWidth();
				
			}
		
			
		}
		
		public boolean isEntrer() {
			return entrer_choix_joeur;
		}
		public void setEntrer(boolean entrer) {
			this.entrer_choix_joeur = entrer;
		}
		@Override
		public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
			// TODO Auto-generated method stub

			    bg_acceuil_x=0;
			    comp=0;
			inp=gc.getInput();
			initJoueurs();
			
		}
		@Override
		public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
			// TODO Auto-generated method stub
			inp=gc.getInput();
			affichages(g, inp,sbg);
			
		}
		@Override
		public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
			// TODO Auto-generated method stub
			inp=gc.getInput();
			modif_image_fond(alpha);
		}
		@Override
		public int getID() {
			// TODO Auto-generated method stub
			return 1;
		}
		
		//id-->0=Avant_Jeu
		//id-->1=Choix_Joueur
		//id-->2=Choix_niveau
		//id-->3=jeuSHEMP
		//id-->4=Menu
		

}
