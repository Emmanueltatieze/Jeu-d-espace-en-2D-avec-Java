import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

//cette classe permet de recuperer le point (coordonnees du joueur ou de l'ennemi) qui est entrer en collision avec une balle

public class Point_animation {
	private float x;
	private float y;
	private int type_balle; //on gere les effets en fonction du type de la balle qui a toucher le point
	private boolean encours;
	
	private int temps=0;
	private int compt=0;
	private Joueur J=null;
	private Ennemi X=null;
	
	public static Image[][] Animation;
	
	public static void init_animations() throws SlickException
	{
		Animation=new Image[10][4];
		Animation[0][0]=new Image("Annimations/A0.0.png");Animation[0][1]=new Image("Annimations/A0.1.png");Animation[0][2]=new Image("Annimations/A0.2.png");Animation[0][3]=new Image("Annimations/A0.3.png");
		Animation[1][0]=new Image("Annimations/A1.0.png");Animation[1][1]=new Image("Annimations/A1.1.png");Animation[1][2]=new Image("Annimations/A1.2.png");Animation[1][3]=new Image("Annimations/A1.3.png");
		Animation[2][0]=new Image("Annimations/A2.0.png");Animation[2][1]=new Image("Annimations/A2.1.png");Animation[2][2]=new Image("Annimations/A2.2.png");Animation[2][3]=new Image("Annimations/A2.3.png");
		Animation[3][0]=new Image("Annimations/A4.0.png");Animation[3][1]=new Image("Annimations/A4.1.png");Animation[3][2]=new Image("Annimations/A4.2.png");Animation[3][3]=new Image("Annimations/A4.3.png");
		Animation[6][0]=new Image("Annimations/A3.0.png");Animation[6][1]=new Image("Annimations/A3.1.png");Animation[6][2]=new Image("Annimations/A3.2.png");Animation[6][3]=new Image("Annimations/A3.3.png");
		Animation[5][0]=new Image("Annimations/b0.0.png");Animation[5][1]=new Image("Annimations/b0.1.png");Animation[5][2]=new Image("Annimations/b0.2.png");Animation[5][3]=new Image("Annimations/b0.3.png");
		Animation[4][0]=new Image("Annimations/c0.0.png");Animation[4][1]=new Image("Annimations/c0.1.png");Animation[4][2]=new Image("Annimations/c0.2.png");Animation[4][3]=new Image("Annimations/c0.3.png");
		Animation[7][0]=new Image("Annimations/d0.0.png");Animation[7][1]=new Image("Annimations/d0.1.png");Animation[7][2]=new Image("Annimations/d0.2.png");Animation[7][3]=new Image("Annimations/d0.3.png");
		Animation[8][0]=new Image("Annimations/e0.0.png");Animation[8][1]=new Image("Annimations/e0.1.png");Animation[8][2]=new Image("Annimations/e0.2.png");Animation[8][3]=new Image("Annimations/e0.3.png");
		Animation[9][0]=new Image("Annimations/f0.0.png");Animation[9][1]=new Image("Annimations/f0.1.png");Animation[9][2]=new Image("Annimations/f0.2.png");Animation[9][3]=new Image("Annimations/f0.3.png");

		
	}
	
	private int type_animation; 
	
	public Point_animation(Joueur J,Ennemi X, int type_balle)
	{
		if(J!=null)
		{
			this.J=J;
			
		}
		else if(X!=null)
		{
			this.X=X;
		}
		
		this.type_balle=type_balle;
		this.encours=true;
		this.compt=0;
	}
	
	public void afficher_animation(Graphics g)
	{

				if(this.J!=null)
				{
					if(this.type_balle==0)
					g.drawImage(Animation[(int)(Math.random()*5)][(int)(Math.random()*4)], this.J.getX(), this.J.getY(), null);
					if(this.type_balle==1||this.type_balle==6)
						g.drawImage(Animation[5][(int)(Math.random()*4)], this.J.getX()-this.J.getImg_l()/2, this.J.getY()-this.J.getImg_h()/2, null);
					if(this.type_balle==2||this.type_balle==3)
						g.drawImage(Animation[4][(int)(Math.random()*4)], this.J.getX(), this.J.getY(), null);
					if(this.type_balle==5)
						g.drawImage(Animation[7][(int)(Math.random()*4)], this.J.getX()+this.J.getImg_l()/2, this.J.getY()+this.J.getImg_h()/2, null);
					if(this.type_balle==8)
						g.drawImage(Animation[8][(int)(Math.random()*4)], this.J.getX()-this.J.getImg_l()/2-20, this.J.getY()-this.J.getImg_h()/2-20, null);
					g.drawImage(J.getImj(), J.getX()/*+this.J.getImg_l()/2*/,J.getY()/*+this.J.getImg_h()/2*/, new Color(255,0,0,100));
					
					if(this.type_balle==4)
						g.drawImage(Animation[6][(int)(Math.random()*4)], this.J.getX()+this.J.getImg_l()/2, this.J.getY()-this.J.getImg_h()/2, null);
					g.drawImage(J.getImj(), J.getX()/*+this.J.getImg_l()/2*/,J.getY()/*+this.J.getImg_h()/2*/, new Color(255,0,0,100));
					if(this.type_balle==7)
						g.drawImage(Animation[9][(int)(Math.random()*4)], this.J.getX(), this.J.getY()+this.J.getImg_h()/2, null);
					
					g.drawImage(J.getImj(), J.getX()/*+this.J.getImg_l()/2*/,J.getY()/*+this.J.getImg_h()/2*/, new Color(255,0,0,100));
					

				}
				else if(this.X!=null)
				{
					float y2=this.X.getImg_h()/2+50;
					if(X.getType_enemi()==7)
						 y2=0;
					
					if(this.type_balle==0)
					g.drawImage(Animation[(int)(Math.random()*5)][(int)(Math.random()*4)], this.X.getX(), this.X.getY(), null);
					//if(this.type_balle==1)
						//g.drawImage(Animation[(int)(Math.random()*5)][(int)(Math.random()*4)], this.X.getX()+this.X.getImg_l()/2, this.X.getY()+this.X.getImg_h()/2, null);
						
					if(this.type_balle==1||this.type_balle==6)
						g.drawImage(Animation[5][(int)(Math.random()*4)], this.X.getX()-this.X.getImg_l()/2, this.X.getY()-this.X.getImg_h()/2, null);
						
					if(this.type_balle==2||this.type_balle==3)
						g.drawImage(Animation[4][(int)(Math.random()*4)], this.X.getX(), this.X.getY(), null);
						
					if(this.type_balle==5)
						g.drawImage(Animation[7][(int)(Math.random()*4)], this.X.getX()+this.X.getImg_l()/2, this.X.getY()+this.X.getImg_h()/2, null);
					if(this.type_balle==8)
						g.drawImage(Animation[8][(int)(Math.random()*4)], this.X.getX()-this.X.getImg_l()/2-20, this.X.getY()-this.X.getImg_h()/2-20, null);
					if(this.type_balle==4)
						g.drawImage(Animation[6][(int)(Math.random()*4)], this.X.getX()+this.X.getImg_l()/2, this.X.getY()-y2, null);
					if(this.type_balle==7)
						g.drawImage(Animation[9][(int)(Math.random()*4)], this.X.getX()/*+this.X.getImg_l()/2*/, this.X.getY()+this.X.getImg_h()/2, null);
					
					
					g.drawImage(X.getIme(), X.getX()/*+this.J.getImg_l()/2*/,X.getY()/*+this.J.getImg_h()/2*/, new Color(255,0,0,100));

				}
	}
				
				
			

	/////////////// GETTERS AND SETTERS /////////////////
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getType_balle() {
		return type_balle;
	}

	public void setType_balle(int type_balle) {
		this.type_balle = type_balle;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public boolean isEncours() {
		return encours;
	}

	public void setEncours(boolean encours) {
		this.encours = encours;
	}

	public int getCompt() {
		return compt;
	}

	public void setCompt(int compt) {
		this.compt = compt;
	}

	public int getType_animation() {
		return type_animation;
	}

	public void setType_animation(int type_animation) {
		this.type_animation = type_animation;
	}
	
	

	

}
