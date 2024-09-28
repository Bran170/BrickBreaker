import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
public class Brick extends JComponent
{
	private Color color;
	private Rectangle2D.Double brick;
	
	public Brick(int x, int y,Color color)
	{
		this.color = color;
		brick = new Rectangle2D.Double(0,0,99,24); 
		this.setBounds(x,y,100,25);
		this.setFocusable(false);
	}
	
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fill(brick);
		
	}

}
