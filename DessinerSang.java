import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class DessinerSang {
	private Joueur J;
	private  Ennemi X;
	
	
	public DessinerSang(Graphics g, Joueur J, Ennemi X)
	{
		
		if(J!=null)
		{
			this.J=J;
			this.dessiner(g,J,null);
			
		}
			
		else if(X!=null)
		{
			this.X=X;
			this.dessiner(g,null,X);
		}
		
		
	}
	
	private void dessiner(Graphics g, Joueur J, Ennemi X)
	{
		if(this.J!=null&&J.getSang_actuel()>0)
		{
			g.setColor(Color.red);
			g.fillRect(J.getX(), J.getY()-4+J.getImj().getHeight()+4, J.getImj().getWidth(), 3);
			g.setColor(Color.green);
			if(J.getSang_actuel()==J.getSang())
				g.fillRect(J.getX(), J.getY()-4+J.getImj().getHeight()+4,J.getImj().getWidth(), 3);
			else
			{
				g.fillRect(J.getX(),J.getY()-4+J.getImj().getHeight()+4, J.getImj().getWidth()-(J.getImj().getWidth()/J.getSang())*(J.getSang()-J.getSang_actuel()), 3);
	           // System.out.println( J.getImj().getWidth()-(J.getImj().getWidth()/J.getSang())*(J.getSang()-J.getSang_actuel()));		
			}
				
			//System.out.println(this.J.getImj().getWidth());
			//g.setColor(new Color(0,0,0,255));
			
			
			
		}
		else if(this.X!=null&&X.getSang_e_actuel()>0)
		{
			g.setColor(Color.red);
			g.fillRect(X.getX(), X.getY()-4, X.getIme().getWidth(), 3);
			if(X.getType_enemi()==0)
				g.setColor(Color.white);
			else if(X.getType_enemi()==1)
				g.setColor(Color.green);
			else if(X.getType_enemi()==2)
				g.setColor(Color.lightGray);
			else if(X.getType_enemi()==3)
				g.setColor(Color.pink);
			else if(X.getType_enemi()==4)
				g.setColor(Color.blue);
			else if(X.getType_enemi()==5)
				g.setColor(Color.yellow);
			else //(X.getType_enemi()==6)
				g.setColor(Color.orange);
			if(X.getSang_e_actuel()==X.getSang_e())
			{
				g.fillRect(X.getX(), X.getY()-4,X.getIme().getWidth(), 3);
				
			}
			else
			{
				g.fillRect(X.getX(), X.getY()-4, X.getIme().getWidth()-(X.getIme().getWidth()/X.getSang_e())*(X.getSang_e()-X.getSang_e_actuel()), 3);
				//System.out.println( X.getSang_e_actuel()-(X.getIme().getWidth()/X.getSang_e())*(X.getSang_e()-X.getSang_e_actuel()));
			}
			
		}
		g.setColor(new Color(255,255,255));
	}
	

}
