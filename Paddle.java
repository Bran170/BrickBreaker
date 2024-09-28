import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
public class Paddle extends JComponent
{
	private int dx;
	private int dy;
	private Color color;
	private Rectangle2D.Double paddle;
	public Paddle(int x, int y)
	{
		this.color = Color.GRAY;
		paddle = new Rectangle2D.Double(0,0,50,5); 
		this.setBounds(x,y,51,6);
		this.setFocusable(false);
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
		g2.fill(paddle);
		g2.setColor(Color.BLACK);
		g2.draw(paddle);
	}

}
