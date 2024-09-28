import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
import java.awt.Color;
public class Ball extends JComponent
{
	private Color color;
	private int dx;
	private int dy;
	private Ellipse2D.Double ball;
	public Ball(int x, int y)
	{
		dx = (7);
		dy = (4);
		this.color = Color.blue;
		ball = new Ellipse2D.Double(0,0,15,15); 
		this.setBounds(x,y,16,16);
		this.setFocusable(false);
	}
	public void setColor(Color c)
	{
		color = c;
	}
	public void setDx(int dx)
	{
		this.dx = dx;
	}
	public void setDy(int dy)
	{
		this.dy = dy;
	}
	public int getDx()
	{
		return dx;
	}
	public int getDy()
	{
		return dy;
	}
	public void update()
	{
		this.setLocation(this.getX()+ dx, this.getY()+ dy);
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fill(ball);
		
	}
}
