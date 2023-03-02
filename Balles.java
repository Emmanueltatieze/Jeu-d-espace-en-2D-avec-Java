
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Balles {
	private float x;
	private float y;
	
	private int type_balle; //type de la balle
	private boolean tir=false; //indique si une balle a ete tirer (et n'est pas encore en collision) ou pas
	private String direction;// "G"=gauche, "D"=droite; "H"=haut; "B"=bas; "T1"= ;"T2"= ; "T3"= ; "T4"= ;
	private String tirer_par; // "J"=Joueur; "E"=Ennemi
	private int impact; //represente le domage que la balle peut causer a ce qui est viser 
	
	static Image b1;
	static Image b2;
	static Image b3;
	static Image balle1;
	static Image balle2;
	static Image balle3;
	static Image balle4;
	static Image balle5;
	static Image balle6;
	static Image balle7;
	static Image balle8;
	
	private Image imb;
	private int img_h; //hauteur de l'image
	private int img_l; //largeur de l'image
	
	private  float v;//vitesse
	
	public static void init_Balles() throws SlickException
	{
		 b1=new Image("Balles/b1.png");
		 b2=new Image("Balles/b2.png");
		 b3=new Image("Balles/b3.png");
		 balle1=new Image("Balles/balle1.png");
		 balle2=new Image("Balles/balle2.png");
		 balle3=new Image("Balles/balle3.png");
		 balle4=new Image("Balles/balle4.png");
		 balle5=new Image("Balles/balle5.png");
		 balle6=new Image("Balles/balle6.png");
		 balle7=new Image("Balles/balle7.png");
		 balle8=new Image("Balles/balle8.png");
	}
	
	///////////// CONSTRUCTEURS //////////////////
	public Balles(int type_balle, float x, float y, String direction, String tirer_par) throws SlickException
	{
		init_Balles();
		
		this.type_balle=type_balle;
		this.x=x-25;
		this.y=y+10;
		this.tir=false;
		this.direction=direction;
		this.tirer_par=tirer_par;
		
		
		
		if(tirer_par=="J")
			this.v=3000;
		else if(tirer_par=="E")
			this.v=1000;
		
		
		if(type_balle==0)
		{
			if(tirer_par=="J"||JeuSHEMP.stage>3)
				this.imb=b2;
			else if(tirer_par=="E")
				this.imb=b1;
		
		this.impact=2;
		this.img_h=b2.getHeight();
		this.img_l=b2.getWidth();
		}
		if(type_balle==1)
		{
			this.x=x;
			this.y=y;
		this.imb=balle1;
		this.impact=1;
		this.img_h=balle1.getHeight();
		this.img_l=balle1.getWidth();
		}
		if(type_balle==2)
		{
			this.x=x;
			this.y=y;
		this.imb=balle2;
		this.impact=3;
		this.img_h=balle2.getHeight();
		this.img_l=balle2.getWidth();
		}
		if(type_balle==3)
		{
			this.x=x;
			this.y=y;
		this.imb=balle3;
		this.impact=5;
		this.img_h=balle3.getHeight();
		this.img_l=balle3.getWidth();
		}
		if(type_balle==4)
		{
			this.x=x;
			this.y=y;
		this.imb=balle4;
		this.impact=5;
		this.img_h=balle4.getHeight();
		this.img_l=balle4.getWidth();
		}
		if(type_balle==5)
		{
			this.x=x-50;
			this.y=y;
		this.imb=balle5;
		this.impact=8;
		this.img_h=balle5.getHeight();
		this.img_l=balle5.getWidth();
		}
		if(type_balle==6)
		{
			this.x=x;
			this.y=y;
		this.imb=balle6;
		this.impact=10;
		this.img_h=balle6.getHeight();
		this.img_l=balle6.getWidth();
		}
		if(type_balle==7)
		{
			this.x=x;
			this.y=y;
		this.imb=balle7;
		this.impact=12;
		this.img_h=balle7.getHeight();
		this.img_l=balle7.getWidth();
		}
		if(type_balle==8)
		{
			this.x=x;
			this.y=y;
			this.v=1100;
			
			if(this.tirer_par=="J")
				this.imb=balle8.getFlippedCopy(false, true);
			if(this.tirer_par=="E")
				this.imb=balle8;
		this.impact=14;
		this.img_h=balle8.getHeight();
		this.img_l=balle8.getWidth();
		}
		
	
	}
	
	public int getImpact() {
		return impact;
	}

	public void setImpact(int impact) {
		this.impact = impact;
	}

	///////////// METHODES /////////////////////
	public void avancer_balle(GameContainer gc, int alpha)
	{
		float b=this.v*alpha/1000f;
		float a=this.v*alpha/1000f;
		
		if(direction=="H")
		{
			if(this.y-b>=0)
			 {
			  this.y-=b;
			 }
		}
		
		if(direction=="B")
		{
			if(this.y+b+this.img_h<=gc.getHeight())
			 {
			  this.y+=b;
			 }
		}
	
		if(direction=="D")
		{
			if(this.x+a+this.img_l<=gc.getWidth())
			 {
			  this.x+=a;
			 }
		}
		
		if(direction=="G")
		{
			if(this.x-a>=0)
			 {
			  this.x-=a;
			  
			 }
			
		}
		if(direction=="T1")// x>=0 && y>=0
		{
			if(this.x+a+this.img_l<=gc.getWidth()||this.y+b+this.img_h<=gc.getHeight())
			 {
			  this.x+=a;
//			 }
//			if(this.y+b+this.img_h<=gc.getHeight())
//			 {
			  this.y+=b;
			 }
		}
		if(direction=="T2")// x>=0 && y<=0
		{
			if(this.x+a+this.img_l<=gc.getWidth()||this.y-b>=0)
			 {
			  this.x+=a;
//			 }
//			if(this.y-b>=0)
//			 {
			  this.y-=b;
			 }
		}
		if(direction=="T3")// x<=0 && y>=0
		{
			if(this.x-a>=0||this.y+b+this.img_h<=gc.getHeight())
			 {
			  this.x-=a;
			  
//			 }
//			if(this.y+b+this.img_h<=gc.getHeight())
//			 {
			  this.y+=b;
			 }
		}
		if(direction=="T4")// x<=0 && y<=0
		{
			if(this.x-a>=0||this.y-b>0)
			 {
			  this.x-=a;
			  
//			 }
//		if(this.y-b>0)
//		 {
		  this.y-=b;
		 }
		}
		
		
	}
	
	
	public Joueur collision_avec_Joueur(Joueur J)
	{
		//if(this.tirer_par=="E"&&(this.getX()>=J.getX()&&this.getX()<=J.getX()+J.getImg_l()&&this.y>=J.getY()&&this.y<=J.getY()+J.getImg_h()))
		if(this.tirer_par=="E"&&J!=null&&(Math.abs(this.x-J.getX())<J.getImg_l()-5 ||Math.abs(this.x-J.getX())<this.img_l-5))
		{
			if(Math.abs(this.y+getImg_h()-(J.getY()+J.getImg_h()))<J.getImg_h()-5||Math.abs(this.y+getImg_h()-(J.getY()+J.getImg_h()))<this.img_h-5)
			{
			  return J;
			}
			
		}	
		
			return null;
	
	}
	public Ennemi collision_avec_Ennemi(Ennemi X)
	{
		//if(this.tirer_par=="J"&&(this.getX()>=X.getX()-2&&this.getX()<=X.getX()+X.getImg_l()&&this.y>=X.getY()&&this.y<=X.getY()+X.getImg_h()))
		
			if(this.tirer_par=="J"&&X!=null&&(estDans_intervalle(X.getX(),X.getX()+X.getImg_l(),this.x)||(Math.abs(this.x-X.getX())<this.getImg_l()||Math.abs(X.getX()+X.getImg_l()-this.x)<this.getImg_l())))
			{
				if(Math.abs(this.y-X.getY())<=X.getImg_h() )
				{
					return X;
				}
				
			}
			
			return null;
	
	}
	private boolean estDans_intervalle(float a, float b, float x)
	{
		if(x>a&&x<b)
			return true;
		else
			return false;
	}
	
	/*public Point_animation effet_impact_balle(Graphics g,Joueur J, Ennemi X)
	{
		Point_animation P=null;
		if(this.collision_avec_Ennemi(X)!=null)
		{
		  if(this.type_balle==0)
		  {
			  //g.drawImage(imb, X.getX()+X.getImg_l()/2, X.getY()+X.getImg_h()/2, null);
			   P=new Point_animation(null, X, 0);
		  }
		}
		else if(this.collision_avec_Joueur(J)!=null)
		{
			if(this.type_balle==0)
			  {
				 // g.drawImage(imb, X.getX()+X.getImg_l()/2, X.getY()+X.getImg_h()/2, null);
				   P=new Point_animation(J, null, 0);
			  }
		}
		return P;
		
	}*/
	
	
	public void Afficher_balle(Graphics g)
	{
		g.drawImage(this.imb, this.x, this.y, null);
	}
	
	////////// GETTERS AND SETTERS /////////////
	public float getX() {
		return x;
	}
	public Image getImb() {
		return imb;
	}

	public void setImb(Image imb) {
		this.imb = imb;
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
	public float getV() {
		return v;
	}

	public void setV(float v) {
		this.v = v;
	}
	public boolean getTir() {
		return tir;
	}

	public void setTir(boolean tir) {
		this.tir = tir;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getImg_h() {
		return img_h;
	}

	public void setImg_h(int img_h) {
		this.img_h = img_h;
	}

	public int getImg_l() {
		return img_l;
	}

	public void setImg_l(int img_l) {
		this.img_l = img_l;
	}

	public String getTirer_par() {
		return tirer_par;
	}

	public void setTirer_par(String tirer_par) {
		this.tirer_par = tirer_par;
	}

	
	
	
	

}
