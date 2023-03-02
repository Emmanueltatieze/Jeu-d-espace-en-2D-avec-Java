import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;

public class Fonctions {
	private float stage_x=500;
	private float stage_y=-50;
	private float stage_v=100;
	
	private float x;
	private float y;
	boolean game_Over=false;
	boolean main=false;
	boolean rejouer=false;
	public boolean isMain() {
		return main;
	}
	public void setMain(boolean main) {
		this.main = main;
	}
	public boolean isRejouer() {
		return rejouer;
	}
	public void setRejouer(boolean rejouer) {
		this.rejouer = rejouer;
	}
	private String[] menu= {"RECOMMENCER","QUITTER"};
	
	
	private Image menu_img;
	 private int choix=0;
	 private int nbre_boutton=2;
	 private int comp=0;
	 private Image boutton2;
	private Image boutton1;
	
	Music_jeu clic=new Music_jeu("Sound/clic.wav");
	Music_jeu select=new Music_jeu("Sound/mixkit_.wav");
	
	
	 	
	  private  String[] Stage={"STAGE 1","STAGE 2","STAGE 3","STAGE 4","STAGE 5","STAGE 6"};

	public static void Choix_etat(boolean avant_jeu,boolean jeu,boolean choix_niveau,boolean choix_joueur,boolean commandes)
	{
		JeuSHEMP.menu=avant_jeu;
		JeuSHEMP.choix_niveau=choix_niveau;
		JeuSHEMP.jeu=jeu;
		JeuSHEMP.commandes=commandes;
		JeuSHEMP.choix_joueur=choix_joueur;
	}
	public static void setNiveau(int stage) throws SlickException
	{
		if(stage==1)
			JeuSHEMP.bg0=new Image("Images/bg0.png");
		if(stage==2)
			JeuSHEMP.bg0=new Image("Images/bg2.png");
		if(stage==3)
			JeuSHEMP.bg0=new Image("Images/bg3.png");
		if(stage==4)
			JeuSHEMP.bg0=new Image("Images/bg4.png");
		if(stage==5)
			JeuSHEMP.bg0=new Image("Images/pngegg (31).png");
		if(stage==6)
			JeuSHEMP.bg0=new Image("Images/pngegg__23.png");
		
		JeuSHEMP.bg0_b=JeuSHEMP.bg0.getFlippedCopy(false, true);
		JeuSHEMP.bg0_y=0;
		JeuSHEMP.bg0_b_y=JeuSHEMP.bg0_y-JeuSHEMP.bg0.getHeight()+3;
	}
	public  void afficherNiveau(int stage, Graphics g,TrueTypeFont ttf)
	{
		g.destroy();

		 
		
		ttf.drawString(stage_x, stage_y,Stage[stage-1],Color.orange);
		
		
	}
	public void Stage_Bas(GameContainer gc,int alpha)
	{
		
		float b=stage_v*alpha/1000f;
		if(stage_y+b<=gc.getHeight()-100)
		 {
		  stage_y+=b;
		 }
		
		
		
	}
	
	public void GameOver(Graphics g,Input inp,StateBasedGame sbg) throws SlickException
	{
		  java.awt.Font font=new java.awt.Font("ALGERIAN", 1, 35);
		  TrueTypeFont ttf= new TrueTypeFont(font, true);
		     menu_img=new Image("images/menu2.png");
			 boutton1=new Image("Avant_jeu/boutton1.png");
			 boutton2=new Image("Avant_jeu/boutton2.png");
			 

		g.setColor(new Color(10,10,10,200));
		g.fillRect(0, 0, 1100, 960);
		x=1100/2-menu_img.getWidth()/2;
		y=200;
		g.drawImage(menu_img, x, y);
		g.drawImage(new Image("Avant_jeu/logo.png"), 0, 750,new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		
		
		
		 if(inp.isKeyPressed(Input.KEY_ENTER)&&game_Over)
		{
			JeuSHEMP.playSE(select);
			
			setChoix(comp);
			if(comp==0)
			{
				Fonctions.Choix_etat(false, true, false, false, false);
				rejouer=true;
				this.game_Over=false;
				//sbg.enterState(3,new FadeOutTransition(Color.black,1000),new FadeInTransition(Color.black,1000));
				
			}
			
			if(comp==1)
			{
			main=true;
			Avant_jeu.entrer_avant_jeu=false;
			Fonctions.Choix_etat(true, false, false, false, false);
			JeuSHEMP.stage=1;
			Fonctions.setNiveau(JeuSHEMP.stage);
			this.game_Over=false;
			sbg.enterState(0,null,new FadeInTransition(Color.black,1000));
				
								
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
			 ttf.drawString(x+150, y+10, "GAME OVER", Color.white);
			 // ttf.drawString(x+70+60, (4)*(20+boutton2.getHeight())+y+100+10, "SCORE :"+JeuSHEMP.score, Color.white);
			 // ttf.drawString(x+70+60, (5)*(20+boutton2.getHeight())+y+100+10, "TEMPS :"+new Temps(JeuSHEMP.temps_ecouler).toString(), Color.white);
		
		
	}
	
	
	
	
	
	public int getChoix() {
		return choix;
	}
	public void setChoix(int choix) {
		this.choix = choix;
	}
	public float getStage_x() {
		return stage_x;
	}
	public void setStage_x(float stage_x) {
		this.stage_x = stage_x;
	}
	public float getStage_y() {
		return stage_y;
	}
	public void setStage_y(float stage_y) {
		this.stage_y = stage_y;
	}
	public String[] getStage() {
		return Stage;
	}
	public void setStage(String[] stage) {
		Stage = stage;
	}
	
	
	
	
	
	
	
}
