import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Avant_jeu extends BasicGameState{
	private  Image bg_acceuil;
	private  Image bg_acceuil2;
	public static boolean entrer_avant_jeu;
	
	
	
	 float   bg_acceuil_x=0;
	 float  bg_acceuil2_x;
	 
	 private Image boutton1;
	 private Image boutton2;
	 
	 private Image acceuil_vaissau;
	 private Image acceuil_vaissau2;
	 public static int comp=0;
	 
	 private final int nbre_boutton=3;
	 private String[] menu= {"AVENTURE","SOLO","COMMANDES"};
	 private int choix;
	 
	 

		private java.awt.Font font=new java.awt.Font("ALGERIAN", 30, 30);
		private TrueTypeFont ttf= new TrueTypeFont(font, true);
		private Input inp;
		
		Music_jeu select=new Music_jeu("Sound/mixkit_.wav");
		Music_jeu clic=new Music_jeu("Sound/clic.wav");
		
		
		
	public Avant_jeu(GameContainer gc) throws SlickException {
		super();
		bg_acceuil=new Image("Avant_jeu/acceuil.png");
		acceuil_vaissau=new Image("Avant_jeu/acceuil_vaissau.png");
		acceuil_vaissau2=new Image("Avant_jeu/acceuil_vaissau2.png");
		 bg_acceuil2=bg_acceuil.getFlippedCopy(true, false);
		 bg_acceuil2_x=bg_acceuil_x+bg_acceuil.getWidth();
		 boutton1=new Image("Avant_jeu/boutton1.png");
		 boutton2=new Image("Avant_jeu/boutton2.png");
		 bg_acceuil_x=0;
		 entrer_avant_jeu=false;
		    comp=0;
		    inp=gc.getInput();
	}
	public void affichages(Graphics g, Input inp, StateBasedGame sbg) throws SlickException
	{
		g.destroy();
		g.drawImage(bg_acceuil, 0, 0);
		g.drawImage(new Image("Avant_jeu/logo.png"), 0, 750,new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		
		if(inp.isKeyPressed(Input.KEY_ENTER)&&!entrer_avant_jeu)
		{
			JeuSHEMP.playSE(select);
			entrer_avant_jeu=true;
			Choix_joueur.entrer_choix_joeur=false;
			Choix_joueur.escape_choix_joueur=false;
			setChoix(comp);
			if(comp==0||comp==1)
			{
				
				Fonctions.Choix_etat(false, false, false, true, false);
				sbg.enterState(1,new FadeOutTransition(Color.black,1000),new FadeInTransition(Color.black,1000));
				
				
			}
			
			if(comp==2)
			{
				
			}
		}
		else if(inp.isKeyPressed(Input.KEY_DOWN))
		{
			JeuSHEMP.playSE(clic);
			
			if(comp>=nbre_boutton-1)
			{
			 comp=0;
			}
			else
				comp++;
		}
		else if(inp.isKeyPressed(Input.KEY_UP))
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
			  g.drawImage(boutton2, 40, (i)*(20+boutton2.getHeight())+140+30);
			  g.drawImage(acceuil_vaissau2, 40+boutton2.getWidth()+5, (i)*(20+boutton2.getHeight())+140+30);
			  ttf.drawString(40+60, (i)*(20+boutton2.getHeight())+140+10+30, menu[i], Color.white);
			  
			}
			else
			{
			  g.drawImage(boutton1, 0, (i)*(20+boutton1.getHeight())+140+30);
			  ttf.drawString(0+60, (i)*(20+boutton1.getHeight())+140+10+30, menu[i], Color.white);
			   
			}
				
			
		}
	
		
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
	public int getChoix() {
		return choix;
	}
	public void setChoix(int choix) {
		if(choix>=0&&choix<this.nbre_boutton)
		this.choix = choix;
	}
	
	
	
	public boolean isEntrer() {
		return entrer_avant_jeu;
	}
	public void setEntrer(boolean entrer) {
		this.entrer_avant_jeu = entrer;
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		    bg_acceuil_x=0;
		    comp=0;
		    inp=gc.getInput();
		
	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		affichages(g, inp,sbg);
		
	}
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
	//id-->0=Avant_Jeu
		//id-->1=Choix_Joueur
		//id-->2=Choix_niveau
		//id-->3=jeuSHEMP
		//id-->4=Menu
	
}
