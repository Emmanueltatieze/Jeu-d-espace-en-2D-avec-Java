

import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Joueur {
	private float x;
	private float y;
	private float sang; //sang innitial du joueur
	private float sang_actuel;// sang du joueur a un temps t
	private DessinerSang monSang; // variable permettant de dessiner le sang du joueur
	
	private Image imj;
	private float img_h; //hauteur de l'image
	private float img_l; //largeur de l'image
	
	private static float v=350;//vitesse
	//private boolean tir; //indique si une balle a ette tirer ou pas
	
	//private Balles b_j;
	
	
	///////////// CONSTRUCTEURS /////////////////////////////////
	
	public Joueur(GameContainer gc, float x, float y,Image imj,float img_h,float Img_l)
	{
	
	 if(x+Img_l<=gc.getWidth()&&x>=0&&y+img_h<=gc.getHeight()&&y>=0)
	 {
	  this.x=x;
	  this.y=y;
	  
	  this.imj=imj;
	  this.img_h=img_h;
	  this.img_l=Img_l;
	  this.sang=200;
	  this.sang_actuel=200;
	 }

	 
	}
	
	////////////////////////////////////////////////////////////////
	
	public void initialiser(GameContainer gc)
	{
	
		this.x=gc.getWidth()/2;
		this.y=gc.getHeight()-this.imj.getHeight()-200;
	}
	
	/////////////////////////////////////////////////////////////
	
	
	/////////////////// METHODES ////////////////////////////////
	
	public void Droite(GameContainer gc,int alpha)
	{
		float a=this.v*alpha/1000f;
		if(this.x+a+this.img_l<=gc.getWidth())
		 {
		  this.x+=a;
		 }
		
	}
	public void Gauche(GameContainer gc,int alpha)
	{
		float a=this.v*alpha/1000f;
		if(this.x-a>=0)
		 {
		  this.x-=a;
		  
		 }
		
	}
	public void Haut(GameContainer gc,int alpha)
	{
		float b=this.v*alpha/1000f;
		if(this.y-b>=0)
		 {
		  this.y-=b;
		 }
		
	}
	public void Haut2(GameContainer gc,int alpha)//POUR AVANCER LE JOUEUR AUTAUMATIQUEMENT VERS LE PROCHAIN STAGE
	{
		float b=this.v*alpha/1000f;
		
		  this.y-=b;
		 
		
	}
	public void Bas(GameContainer gc,int alpha)
	{
		float b=this.v*alpha/1000f;
		if(this.y+b+this.img_h<=gc.getHeight())
		 {
		  this.y+=b;
		 }
		
	}
	
	public boolean collision_Droit(Ennemi[] X)
	{int c=0;
		for(int i=0;i<X.length;i++)
		{
			if(X[i]!=null&&((X[i].getX()>this.x+this.img_l+1||X[i].getX()+X[i].getImg_l()<this.x)||(X[i].getY()+X[i].getImg_h()<this.y||X[i].getY()>this.y+this.img_h)))
				c++;
			
		}
		if(c==Ennemi.nbre_ennemi_restant(X))
			return false;
		else
			return true;
	}

	public boolean collision_Gauche(Ennemi[] X)
	{
		int c=0;
		for(int i=0;i<X.length;i++)
		{
			if(X[i]!=null&&((X[i].getX()>this.x+this.img_l||X[i].getX()+X[i].getImg_l()+1<this.x)||(X[i].getY()+X[i].getImg_h()<this.y||X[i].getY()>this.y+this.img_h)))
				c++	;
		}
		if(c==Ennemi.nbre_ennemi_restant(X))
			return false;
		else
			return true;
	}
	
	public boolean collision_Haut(Ennemi[] X)
	{

		int c=0;
		for(int i=0;i<X.length;i++)
		{
			if(X[i]!=null&&((X[i].getY()>this.y+this.img_h||X[i].getY()+X[i].getImg_h()+1<this.y)||(X[i].getX()+X[i].getImg_l()<this.x||X[i].getX()>this.x+this.img_l)))
				c++	;
		}
		if(c==Ennemi.nbre_ennemi_restant(X))
			return false;
		else
			return true;
		
	}
	public boolean collision_Bas(Ennemi[] X)
	{

		int c=0;
		for(int i=0;i<X.length;i++)
		{
			if(X[i]!=null&&((X[i].getY()>this.y+this.img_h+1||X[i].getY()+X[i].getImg_h()<this.y)||(X[i].getX()+X[i].getImg_l()<this.x||X[i].getX()>this.x+this.img_l)))
				c++	;
		}
		if(c==Ennemi.nbre_ennemi_restant(X))
			return false;
		else
			return true;
		
	}

	public boolean collision_avec_la_balle(Balles[] balle)
	{
		int c=0;
		for(int i=0;balle[i]!=null;i++)
		{
			c++;
			if(balle[c]==null)
				break;
		}
		
		for(int i=0;i<c;i++)
		{
			if(balle[i].getTirer_par()=="E")
			{
				if(balle[i].getX()>this.x&&balle[i].getX()<this.x+this.img_l&&balle[i].getY()>this.y&&balle[i].getY()<this.y+this.img_h)
					return true;
			}
		}
		return false;
		
		
	}
	
	
	public void Afficher_J(Graphics g)
	{
		g.drawImage(this.imj,this.x+20, this.y+20, new Color(0, 0, 0, 100));
		g.drawImage(this.imj,this.x, this.y);

	}
	public void Afficher_sang(Graphics g ,Joueur J, Ennemi X)
	{
		this.monSang=new DessinerSang(g, this, null);
	}
	
	public boolean collision_avec_Ennemi(Ennemi[] X)//collision d'un enemi avec un autre
	{
		int c=0;
		for(int i=0;i<X.length;i++)
		{
			if(X[i]!=null&&(Math.abs(this.x-X[i].getX())<=X[i].getImg_l()||Math.abs(this.x-X[i].getX())<=this.img_l))
			{
				if(Math.abs(this.y-X[i].getY())<=X[i].getImg_h()||Math.abs(this.y-X[i].getY())<=this.img_h)
				{
					return true;
				}
				
			}
			
		}
		
			return false;
		
	}
	public boolean collision_avec_Ennemi(Ennemi X)//collision d'un enemi avec un autre
	{
		int c=0;
			if(X!=null&&(Math.abs(this.x-X.getX())<=X.getImg_l()||Math.abs(this.x-X.getX())<=this.img_l))
			{
				if(Math.abs(this.y-X.getY())<=X.getImg_h()||Math.abs(this.y-X.getY())<=this.img_h)
				{
					c++;
					
				}
				
			}
			
		
		if(c==0)
			return false;
		else
			return true;
		
	}
	
	
	
	
	
	
	
	
	///////////////////GETTERS AND SETTERS///////////////////////

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public float getV() {
		return v;
	}

	public void setV(float v) {
		this.v = v;
	}

	public float getImg_h() {
		return img_h;
	}

	public void setImg_h(float img_h) {
		this.img_h = img_h;
	}

	public float getImg_l() {
		return img_l;
	}

	public void setImg_l(float img_l) {
		this.img_l = img_l;
	}
	public float getSang() {
		return sang;
	}

	public void setSang(float sang) {
		this.sang = sang;
	}
	public float getSang_actuel() {
		return sang_actuel;
	}

	public void setSang_actuel(float sang_actuel) {
		this.sang_actuel = sang_actuel;
	}

	public Image getImj() {
		return imj;
	}

	public void setImj(Image imj) {
		this.imj = imj;
	}
	
	
	
	
	
	
}

