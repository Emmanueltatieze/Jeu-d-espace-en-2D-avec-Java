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

public class Menu extends BasicGameState{
	
	private float x;
	private float y;
	boolean pause_menu=false;
	private String[] menu= {"REPRENDRE","RECOMMENCER","COMMANDES","QUITTER"};
	
	
	private Image menu_img;
	private int comp;
	private int nbre_boutton;
	private Image boutton2;
	private Image boutton1;
	private int choix;
	
	private java.awt.Font font=new java.awt.Font("ALGERIAN", 30, 30);
	private TrueTypeFont ttf= new TrueTypeFont(font, true);
	private Input inp;
	public static boolean entrer_menu=false;
	Music_jeu clic=new Music_jeu("Sound/clic.wav");
	Music_jeu select=new Music_jeu("Sound/mixkit_.wav");
	
	
	
	
	public Menu(GameContainer gc) throws SlickException
	{
		super();
	 menu_img=new Image("images/menu2.png");
	 boutton1=new Image("Avant_jeu/boutton1.png");
	 boutton2=new Image("Avant_jeu/boutton2.png");
	 pause_menu=false;
	 choix=0;
	 nbre_boutton=4;
	 comp=0;
	 x=1100/2-menu_img.getWidth()/2;
	 y=200;
	 
	}
	
	
	public void afficher_Menu(Graphics g, Input inp,StateBasedGame sbg) throws SlickException
	{
		g.setColor(new Color(10,10,10,200));
		g.fillRect(0, 0, 1100, 960);
		g.drawImage(menu_img, x, y);
		g.drawImage(new Image("Avant_jeu/logo.png"), 0, 750,new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		
		
		if(inp.isKeyPressed(Input.KEY_SPACE))
		{
			this.pause_menu=false;
			
		}
		else if(inp.isKeyPressed(Input.KEY_ENTER))
		{
			JeuSHEMP.playSE(select);
			
			setChoix(comp);
			if(comp==0)
			{
				Fonctions.Choix_etat(false, true, false, false, false);
				this.pause_menu=false;
				//sbg.enterState(3,new FadeOutTransition(Color.black,1000),new FadeInTransition(Color.black,1000));
				
			}
			if(comp==3&&!entrer_menu)
			{
				entrer_menu=true;
				Avant_jeu.entrer_avant_jeu=false;
				Fonctions.Choix_etat(true, false, false, false, false);
				this.pause_menu=false;
				//JeuSHEMP.stage=1;
				Fonctions.setNiveau(JeuSHEMP.stage);
				sbg.enterState(0,new FadeOutTransition(Color.black,1000),new FadeInTransition(Color.black,1000));
				
			}
			
			if(comp==1)
			{
				Fonctions.Choix_etat(false, true, false, false, false);
				this.pause_menu=false;
				//sbg.enterState(3,new FadeOutTransition(Color.black,1000),new FadeInTransition(Color.black,1000));
				
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
			  g.drawImage(boutton2, x+70, (i)*(20+boutton2.getHeight())+y+100);
			  
			}
			else
			{
			  g.drawImage(boutton1, x+70, (i)*(20+boutton1.getHeight())+y+100);
			   
			}
			  ttf.drawString(x+70+60, (i)*(20+boutton2.getHeight())+y+100+10, menu[i], Color.white);

				
			
		}
		
	}

	public int getChoix() {
		return choix;
	}
	public void setChoix(int choix) {
		if(choix>=0&&choix<this.nbre_boutton)
		this.choix = choix;
	}


	public boolean isPause_menu() {
		return pause_menu;
	}


	public void setPause_menu(boolean pause_menu) {
		this.pause_menu = pause_menu;
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
		afficher_Menu(g, inp,sbg);
		
	}


	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int alpha) throws SlickException {
		// TODO Auto-generated method stub
		inp=gc.getInput();
		
	}


	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

		//id-->0=Avant_Jeu
		//id-->1=Choix_Joueur
		//id-->2=Choix_niveau
		//id-->3=jeuSHEMP
		//id-->4=Menu
	
	

}
