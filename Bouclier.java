import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bouclier {
	private Image imb1;
	private Image imb2;
	private float x;
	private float y;
	private double v=40;
	private int img_h; 
	private int img_l;
	
	public Bouclier(int x, int y) throws SlickException
	{
		this.x=x;
		this.y=y;
		this.imb1=new Image("images/bouclier2.png");

		this.imb2=new Image("images/bouclier.png");
		
		this.img_h=this.imb2.getWidth();
		this.img_l=this.imb2.getHeight();
	}
	
	public void afficherbouclier(Graphics g,Joueur J)
	{
		x= J.getX()-20;
		y=J.getY()-10;
		g.drawImage(imb2, x, y,new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*200)));
		
	}
	public void afficherGainbouclier(Graphics g)
	{
			g.drawImage(imb2,x,y,new Color((int)(Math.random()*200),(int)(Math.random()*200),(int)(Math.random()*200),(int)(Math.random()*200)));

	}
	public void Bas(GameContainer gc,int alpha)
	{
		double b=this.v*alpha/1000f;
		if((this.y+b<gc.getHeight()))
		 {
		  this.y+=b;
		 }
		
	}
	public boolean collision_avec_joueur(Joueur J)
	{

			//if(this.tirer_par=="E"&&(this.getX()>=J.getX()&&this.getX()<=J.getX()+J.getImg_l()&&this.y>=J.getY()&&this.y<=J.getY()+J.getImg_h()))
			if(J!=null&&(Math.abs(this.x-J.getX())<J.getImg_l()-5 ||Math.abs(this.x-J.getX())<this.img_l-5))
			{
				if(Math.abs(this.y+img_h-(J.getY()+J.getImg_h()))<J.getImg_h()-5||Math.abs(this.y+img_h-(J.getY()+J.getImg_h()))<this.img_h-5)
				{
				  return true;
				}
				
			}	
			
				return false;
		
		
	}
	
}
