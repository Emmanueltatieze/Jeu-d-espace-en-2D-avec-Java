import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ennemi {
	private float x;
	private float y;
	private float sang_e;//sang initial de l'enemi
	private float sang_e_actuel;//sang de l'ennemi a un temps t
	private DessinerSang monSang;// variable permettant de dessiner le sang de l'ennemi
	private int type_enemi;
	
	private int temps1;// temps avant de faire un mouvement
	private int temps2;//temps avant de tirer
	
	private Image ime;
	private float img_h; //hauteur de l'image
	private float img_l; //largeur de l'image
	private Joueur J; //por definir quel est le joueur que les ennemis chassent
	
	Image[] ennemi=new Image[7];
	
	
	private static float v;//vitesse
	
///////////// CONSTRUCTEURS /////////////////////////////////

	public Ennemi(GameContainer gc,Image ime, float x, float y,int type_ennemi ,Joueur J) throws SlickException
	{
		
		
		this.ime=ime;
	
		this.type_enemi=type_ennemi;
		 if(x+img_l<=gc.getWidth()&&x>=0&&y+img_h<=gc.getHeight()&&y>=0)
		 {
		  this.x=x;
		  this.y=y;
 
		 }
		 
		 this.temps1=0;
		 this.temps2=0;
		 this.J=J;
		 if(type_ennemi==0)
		 {
			 
			 //this.ime=new Image("images/enemi0.png");
			
			 this.img_h=this.ime.getHeight();
			 this.img_l=this.ime.getWidth();
			 this.v=40;
			 this.sang_e=5;
			 this.sang_e_actuel=5;
		 }
		 if(type_ennemi==1)
		 {
			 
			 //this.ime=new Image("images/enemi1.png");
			
			 this.img_h=this.ime.getHeight();
			 this.img_l=this.ime.getWidth();
			 this.v=40;
			 this.sang_e=10;
			 this.sang_e_actuel=10;
		 }
		 if(type_ennemi==2)
		 {
			 
			 //this.ime=new Image("images/enemi2.png");
			 
			 this.img_h=this.ime.getHeight();
			 this.img_l=this.ime.getWidth();
			 this.v=40;
			 this.sang_e=15;
			 this.sang_e_actuel=15;
		 }
		 if(type_ennemi==3)
		 {
			 
			 //this.ime=new Image("images/enemi3.png");
			 
			 this.img_h=this.ime.getHeight();
			 this.img_l=this.ime.getWidth();
			 this.v=70;
			 this.sang_e=20;
			 this.sang_e_actuel=20;
		 }
		 if(type_ennemi==4)
		 {
			 
			 //this.ime=new Image("images/enemi4.png");
			 
			 this.img_h=this.ime.getHeight();
			 this.img_l=this.ime.getWidth();
			 this.v=70;
			 this.sang_e=50;
			 this.sang_e_actuel=50;
		 }
		 if(type_ennemi==5)
		 {
			 
			 //this.ime=new Image("images/enemi5.png");
			 this.img_h=this.ime.getHeight();
			 this.img_l=this.ime.getWidth();
			 this.v=80;
			 this.sang_e=70;
			 this.sang_e_actuel=70;
		 }
		 if(type_ennemi==6)
		 {
			 
			 //this.ime=new Image("images/enemi6.png");
			 this.img_h=this.ime.getHeight();
			 this.img_l=this.ime.getWidth();
			 this.v=80;
			 this.sang_e=90;
			 this.sang_e_actuel=90;
		 }
		 if(type_ennemi==7)
		 {
			 this.img_h=this.ime.getHeight();
			 this.img_l=this.ime.getWidth();
			 this.v=200+JeuSHEMP.stage*30;
			 this.sang_e=300;
			 this.sang_e_actuel=300;
		 }
  
	}
	/////////////////////////////////////////////////////////
	
	///////////////// METHODES ////////////////////////////
	public void initialiser(GameContainer gc, Ennemi X[], int ind)
	{
		do
		{
		int c=(int)(Math.random()*3);
		if(c==0)
		{
			this.x=0-300;
			this.y=(float)Math.random()*((gc.getHeight()-this.img_h-700+300)-300);
			
			
			
		}
		if(c==2)
		{
			this.x=gc.getWidth()-this.img_l+300;
			this.y=(float)(Math.random()*(gc.getHeight()-this.img_h-700+300)-300);
			
		}
		if(c==1)
		{
			this.x=(float)(Math.random()*(gc.getWidth()+300+300)-300);
			this.y=0-300;
			
		}
		}while(this.collision_E_G(X, ind)||this.collision_E_D(X, ind)||this.collision_E_B(X, ind)||this.collision_E_H(X, ind));
		
		
	}
	
	public void initialiser_balles() throws SlickException
	{
		Balles.init_Balles();
	}
	
	public void Afficher_E(Graphics g)
	{
		g.drawImage(this.ime,this.x+20, this.y+20, new Color(0, 0, 0, 100));
		g.drawImage(this.ime,this.x, this.y);
		
		
	}
	public void Afficher_sang(Graphics g,Joueur J, Ennemi X)
	{
		this.monSang=new DessinerSang(g, null, this);
	}
	
	public void Droite(GameContainer gc,int alpha)
	{
		float a=this.v*alpha/1000f;
		if(this.x+a+this.img_l<gc.getWidth())
		 {
		  this.x+=a;
		 }
		
	}
	public void Gauche(GameContainer gc,int alpha)
	{
		float a=this.v*alpha/1000f;
		if(this.x-a>0)
		 {
		  this.x-=a;
		  
		 }
		
	}
	public void Haut(GameContainer gc,int alpha)
	{
		float b=this.v*alpha/1000f;
		if(this.y-b>0)
		 {
		  this.y-=b;
		 }
		
	}
	public void Bas(GameContainer gc,int alpha)
	{
		int y2=700;
		if(this.type_enemi==7)
			y2=0;
		float b=this.v*alpha/1000f;
		if((this.y+b+this.img_h<gc.getHeight()+y2))
		 {
		  this.y+=b;
		 }
		
	}
	public void Transversale_1(GameContainer gc,int alpha)//x>=0 et y>=0
	{
		int y2=700;
		if(this.type_enemi==7)
		y2=0;
		float b=this.v*alpha/1000f;
		if(this.y+b+this.img_h<gc.getHeight()+y2)
		 {
		  this.y+=b;
		 }
		float a=this.v*alpha/1000f;
		if(this.x+a+this.img_l<gc.getWidth())
		 {
		  this.x+=a;
		 }
		
	}
	public void Transversale_2(GameContainer gc,int alpha)//x>=0 et y<=0
	{
		float b=this.v*alpha/1000f;
		if(this.y-b>0)
		 {
		  this.y-=b;
		 }
		float a=this.v*alpha/1000f;
		if(this.x+a+this.img_l<gc.getWidth())
		 {
		  this.x+=a;
		 }
		
	}
	public void Transversale_3(GameContainer gc,int alpha)//x<=0 et y>=0
	{
		int y2=700;
		if(this.type_enemi==7)
		y2=0;
		float b=this.v*alpha/1000f;
		if(this.y+b+this.img_h<=gc.getHeight()+y2)
		 {
		  this.y+=b;
		 }
		float a=this.v*alpha/1000f;
		if(this.x-a>0)
		 {
		  this.x-=a;
		  
		 }
		
	}
	public void Transversale_4(GameContainer gc,int alpha)//x<=0 et y<=0
	{
		float b=this.v*alpha/1000f;
		if(this.y-b>0)
		 {
		  this.y-=b;
		 }
		float a=this.v*alpha/1000f;
		if(this.x-a>0)
		 {
		  this.x-=a;
		  
		 }
	}
	

	public void Tirer(Balles[] balle,Joueur J,Ennemi X) throws SlickException
	{
		initialiser_balles();
		int type=(int)(Math.random()*(2));
		if(X.type_enemi==7)
			type=(int)(Math.random()*(JeuSHEMP.stage+3));
//		else
//			type=(int)(Math.random()*3);

		
		int c=0;
		for(int i=0;(balle[i]!=null);i++)
		{
			c++;
			if(balle[i].getTir()==false)
			break;
		}
		if(c>0&&balle[c-1].getTir()==false)
		{
			
		if(x>=J.getX())
		 {
			if(y<=J.getY()+J.getImg_h()&&y>=J.getY()-3)
			 balle[c-1]=new Balles(type,this.x,this.y+this.img_h/2,"G","E");
			else if(y>=J.getY()+J.getImg_h())
				balle[c-1]=new Balles(type,this.x,this.y+this.img_h/2,"T3","E");
			else if(y<=J.getY()-3)
				balle[c-1]=new Balles(type,this.x,this.y+this.img_h/2,"T4","E");
				
				
			 balle[c-1].setTir(true);
		 }
		
		
		
		
		    
		if(x<=J.getX())
		 {
			if(y<=J.getY()+J.getImg_h()&&y>=J.getY()-3)
			balle[c-1]=new Balles(type,this.x+this.img_l,this.y+this.img_h/2,"D","E");
			else if(y<=J.getY()-3)
				balle[c-1]=new Balles(type,this.x+this.img_l,this.y+this.img_h/2,"T1","E");
			else if(y>=J.getY()+J.getImg_h())
				balle[c-1]=new Balles(type,this.x+this.img_l,this.y+this.img_h/2,"T2","E");
				
			balle[c-1].setTir(true);
		 }
		 if(J.getY()+J.getImg_h()<getY())
		 {
			 if(J.getX()+J.getImg_l()>x-20&&J.getX()<x+getImg_l()+20)
			balle[c-1]=new Balles(type,this.x+this.img_l/2,this.y,"H","E");
			 else if(J.getX()+J.getImg_l()<=x-20)
				 balle[c-1]=new Balles(type,this.x+this.img_l/2,this.y,"T4","E");
			 else if(J.getX()>=x+getImg_l()+20)
				 balle[c-1]=new Balles(type,this.x+this.img_l/2,this.y,"T2","E");
				 
				 
			balle[c-1].setTir(true);
		 }
		 if(getY()+getImg_h()<J.getY())
		 {
			 if(J.getX()+J.getImg_l()>x-20&&J.getX()<x+getImg_l()+20)
				balle[c-1]=new Balles(type,this.x+this.img_l/2,this.y,"B","E");
			else if(J.getX()+J.getImg_l()<=x-20)
				balle[c-1]=new Balles(type,this.x+this.img_l/2,this.y,"T3","E");
			else if(J.getX()>=x+getImg_l()+20)
				 balle[c-1]=new Balles(type,this.x+this.img_l/2,this.y,"T1","E");
						 
			balle[c-1].setTir(true);
		 }
			
		
		}
		else if(c==0||balle[c]==null)
		{
			
				
			if(x>=J.getX())
			 {
				if(y<=J.getY()+J.getImg_h()&&y>=J.getY()-3)
				 balle[c]=new Balles(type,this.x,this.y+this.img_h/2,"G","E");
				else if(y>=J.getY()+J.getImg_h())
					balle[c]=new Balles(type,this.x,this.y+this.img_h/2,"T3","E");
				else if(y<=J.getY()-3)
					balle[c]=new Balles(type,this.x,this.y+this.img_h/2,"T4","E");
					
					
				 balle[c].setTir(true);
			 }
			
			if(x<=J.getX())
			 {
				if(y<=J.getY()+J.getImg_h()&&y>=J.getY()-3)
				balle[c]=new Balles(0,this.x+this.img_l,this.y+this.img_h/2,"D","E");
				else if(y<=J.getY()-3)
					balle[c]=new Balles(type,this.x+this.img_l,this.y+this.img_h/2,"T1","E");
				else if(y>=J.getY()+J.getImg_h())
					balle[c]=new Balles(type,this.x+this.img_l,this.y+this.img_h/2,"T2","E");
					
				balle[c].setTir(true);
			 }
			if(J.getY()+J.getImg_h()<getY())
			 {
				 if(J.getX()+J.getImg_l()>x-20&&J.getX()<x+getImg_l()+20)
				balle[c]=new Balles(type,this.x+this.img_l/2,this.y,"H","E");
				 else if(J.getX()+J.getImg_l()<=x-20)
					 balle[c]=new Balles(type,this.x+this.img_l/2,this.y,"T4","E");
				 else if(J.getX()>=x+getImg_l()+20)
					 balle[c]=new Balles(type,this.x+this.img_l/2,this.y,"T2","E");
					 
					 
				balle[c].setTir(true);
			 }
			if(y+getImg_h()<=J.getY())
			 {
				if(J.getX()+J.getImg_l()>x-20&&J.getX()<x+getImg_l()+20)
					balle[c]=new Balles(type,this.x+this.img_l/2,this.y,"B","E");
				else if(J.getX()+J.getImg_l()<=x-20)
					balle[c]=new Balles(type,this.x+this.img_l/2,this.y,"T3","E");
				else if(J.getX()>=x+getImg_l()+20)
					 balle[c]=new Balles(type,this.x+this.img_l/2,this.y,"T1","E");
							 
				balle[c].setTir(true);
			 }
			
			
		}
	}
	
	public boolean collision_E_G(Ennemi[] X2,int ind)//collision d'un enemi avec un autre
	{
		int c=0;
		
		for(int i=0;i<X2.length;i++)
		{
			if((i!=ind&&X2[i]!=null)&&((X2[i].getX()>this.x+this.img_l||X2[i].getX()+X2[i].getImg_l()+1<this.x)||(X2[i].getY()+X2[i].getImg_h()<this.y||X2[i].getY()>this.y+this.img_h)))
				c++	;
		}
		if(X2[ind]!=null&&((J.getX()>this.x+this.img_l||J.getX()+J.getImg_l()+1<this.x)||(J.getY()+J.getImg_h()<this.y||J.getY()>this.y+this.img_h)))
		c++;
		if(c==nbre_ennemi_restant(X2))
			return false;
		else
			return true;
		
		
	}
	
	public boolean collision_E_D(Ennemi[] X2,int ind)//collision d'un enemi avec un autre
	{
		int c=0;
		for(int i=0;i<X2.length;i++)
		{
			if((i!=ind&&X2[i]!=null)&&((X2[i].getX()>this.x+this.img_l+1||X2[i].getX()+X2[i].getImg_l()<this.x)||(X2[i].getY()+X2[i].getImg_h()<this.y||X2[i].getY()>this.y+this.img_h)))
				c++;
			
		}
		if(X2[ind]!=null&&((J.getX()>this.x+this.img_l+1||J.getX()+J.getImg_l()<this.x)||(J.getY()+J.getImg_h()<this.y||J.getY()>this.y+this.img_h)))
			c++;
		if(c==nbre_ennemi_restant(X2))
			return false;
		else
			return true;
		
	}
	public boolean collision_E_H(Ennemi[] X2,int ind)//collision d'un enemi avec un autre
	{
		int c=0;
		for(int i=0;i<X2.length;i++)
		{
			if((i!=ind&&X2[i]!=null)&&((X2[i].getY()>this.y+this.img_h||X2[i].getY()+X2[i].getImg_h()+1<this.y)||(X2[i].getX()+X2[i].getImg_l()<this.x||X2[i].getX()>this.x+this.img_l)))
				c++	;
		}
		if(X2[ind]!=null&&((J.getY()>this.y+this.img_h||J.getY()+J.getImg_h()+1<this.y)||(J.getX()+J.getImg_l()<this.x||J.getX()>this.x+this.img_l)))
			c++;
		if(c==nbre_ennemi_restant(X2))
			return false;
		else
			return true;
		
		
	}
	public boolean collision_E_B(Ennemi[] X2,int ind)//collision d'un enemi avec un autre
	{

		int c=0;
		for(int i=0;i<X2.length;i++)
		{
			if((i!=ind&&X2[i]!=null)&&((X2[i].getY()>this.y+this.img_h+1||X2[i].getY()+X2[i].getImg_h()<this.y)||(X2[i].getX()+X2[i].getImg_l()<this.x||X2[i].getX()>this.x+this.img_l)))
				c++;
		}
		if(X2[ind]!=null&&((J.getY()>this.y+this.img_h+1||J.getY()+J.getImg_h()<this.y)||(J.getX()+J.getImg_l()<this.x||J.getX()>this.x+this.img_l)))
			c++;
		if(c==nbre_ennemi_restant(X2))
			return false;
		else
			return true;
		
		
	}
	public static int nbre_ennemi_restant(Ennemi[] X)
	{
		int c=0;
		for(int i=0;i<X.length;i++)
		{
			
			if(X[i]==null)
				continue;
			else
				c++;
		}
		return c;
	}
	
	
	
	
	
	public boolean collision_J(Joueur J)//collision d'un enemi avec le joueur
	{
		if(Math.abs(this.x-J.getX())<=J.getImg_l()||Math.abs(this.x-J.getX())<=this.img_l)
		{
			if(Math.abs(this.y-J.getY())<=J.getImg_h()||Math.abs(this.y-J.getY())<=this.img_h)
			{
				return true;
			}
			return false;
		}
		else
			return false;
		
	}
	
///////////////////GETTERS AND SETTERS///////////////////////
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

	public int getTemps1() {
		return temps1;
	}

	public void setTemps1(int temps1) {
		this.temps1 = temps1;
	}

	public int getTemps2() {
		return temps2;
	}

	public void setTemps2(int temps2) {
		this.temps2 = temps2;
	}

	public Image getIme() {
		return ime;
	}

	public void setIme(Image ime) {
		this.ime = ime;
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

	public float getSang_e() {
		return sang_e;
	}

	public void setSang_e(float sang_e) {
		this.sang_e = sang_e;
	}
	
	public float getSang_e_actuel() {
		return sang_e_actuel;
	}

	public void setSang_e_actuel(float sang_e_actuel) {
		this.sang_e_actuel = sang_e_actuel;
	}

	public int getType_enemi() {
		return type_enemi;
	}

	public void setType_enemi(int type_enemi) {
		this.type_enemi = type_enemi;
	}

	public static float getV() {
		return v;
	}

	public static void setV(float v) {
		Ennemi.v = v;
	}
	
	
	
	
	

}
