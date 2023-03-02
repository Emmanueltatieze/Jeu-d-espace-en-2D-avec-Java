
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import net.java.games.input.Mouse;

public class Choix_niveau extends BasicGameState {
	     private  Image bg;
	     private  Image img;

		 private int comp;
		 private int nbre_boutton;
		 private String[] menu2= {"STAGE1","STAGE2","STAGE3","STAGE4","STAGE5","STAGE6"};
		 private int choix;
		 

		 private java.awt.Font font=new java.awt.Font("ALGERIAN", 1, 35);
		 private TrueTypeFont ttf= new TrueTypeFont(font, true);
		 private Image boutton2=new Image("Avant_jeu/boutton2.png");
		private Input inp;
		public static boolean entrer_choix_niveau=false;
		public static boolean escape_choix_niveau=false;
		
		Music_jeu clic=new Music_jeu("Sound/clic.wav");
		Music_jeu select=new Music_jeu("Sound/mixkit_.wav");
			
		public Choix_niveau(GameContainer gc) throws SlickException {
			super();
			bg=new Image("Choix_joueur/bg_Choix_joueur.png");
			img=new Image("Choix_joueur/bg_Choix_joueur2.png");
			nbre_boutton=menu2.length;
			comp=0;
			
			
	
		}
		public void affichages(Graphics g, Input inp, StateBasedGame sbg) throws SlickException
		{
			g.destroy();
			
			
			
			//g.drawImage(bg, 0, 0);
			Fonctions.setNiveau(comp+1);
			g.drawImage(JeuSHEMP.bg0, 0, 0,new Color(100,100,100,200));
			
			g.drawImage(img, 1100/2-img.getWidth()/2, 960/2+100);
			g.drawImage(new Image("Avant_jeu/logo.png"), 0, 750,new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			g.drawImage(new Image("images/boutton_rouge.png"), 0, 0);
			if(Dans_le_bouton(inp.getMouseX(), inp.getMouseY()))
			ttf.drawString(50, 10,"RETOUR",Color.yellow);
			else
				ttf.drawString(50, 10,"RETOUR",Color.white);
				
			
			
			if(comp!=0)
			g.drawImage(new Image("Choix_joueur/left2.png"), 1100/2-img.getWidth()/2-200, 960/2-100+100);
			if(comp!=nbre_boutton-1)
			g.drawImage(new Image("Choix_joueur/right2.png"), 1100/2-img.getWidth()/2+200*2, 960/2-100+100);
			ttf.drawString(1100/2-250+50, 150+200,"CHOISISSEZ UN NIVEAU",Color.yellow);
			
			
			if(inp.isKeyPressed(Input.KEY_ENTER)&&!entrer_choix_niveau)
			{
				JeuSHEMP.playSE(select);
				entrer_choix_niveau=true;
				setChoix(comp);
				JeuSHEMP.stage=comp+1;
				Fonctions.Choix_etat(false, true, false, false, false);
				Fonctions.setNiveau(comp+1);
				sbg.enterState(3,null,new FadeInTransition(Color.black,1000));
					
				
			}
			else if(inp.isKeyPressed(Input.KEY_ESCAPE)&&!escape_choix_niveau)
			{
				escape_choix_niveau=true;
				//entrer_choix_niveau=false;
				Choix_joueur.entrer_choix_joeur=false;
				 Fonctions.Choix_etat(false, false, false, true, false);
				 sbg.enterState(1,new FadeOutTransition(Color.black,1000),new FadeInTransition(Color.black,1000));
				
			}
			else if(inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)&&Dans_le_bouton(inp.getMouseX(), inp.getMouseY()))
			{
				escape_choix_niveau=true;
				//entrer_choix_niveau=false;
				Choix_joueur.entrer_choix_joeur=false;
				Fonctions.Choix_etat(false, false, false, true, false);
				 sbg.enterState(1,new FadeOutTransition(Color.black,1000),new FadeInTransition(Color.black,1000));
				
			}
			
			
			else if((inp.isKeyPressed(Input.KEY_RIGHT))&&comp!=nbre_boutton-1)
			{
				JeuSHEMP.playSE(clic);
				
				if(comp>=nbre_boutton-1)
				{
				 comp=0;
				}
				else
					comp++;
				
				
			}
			else if((inp.isKeyPressed(Input.KEY_LEFT))&&comp!=0)
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
					g.drawImage(boutton2, 1100/2-100-100,  960/2-50+100-25);
					ttf.drawString(1100/2-100, 960/2-50+100-15,menu2[i],Color.white);
  
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
		@Override
		public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
			// TODO Auto-generated method stub
			inp=gc.getInput();
			
		}
		@Override
		public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
			// TODO Auto-generated method stub
			inp=gc.getInput();
			affichages(g,inp,sbg);
			
		}
		@Override
		public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
			// TODO Auto-generated method stub
			inp=gc.getInput();
			
		}
		@Override
		public int getID() {
			// TODO Auto-generated method stub
			return 2;
		}

		
		//id-->0=Avant_Jeu
		//id-->1=Choix_Joueur
		//id-->2=Choix_niveau
		//id-->3=jeuSHEMP
		//id-->4=Menu


}

