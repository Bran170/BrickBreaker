import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
public class BrickMain extends JFrame implements ActionListener
{
	private Ball ball;
	private Timer timer;
	private Paddle paddle;
	private ArrayList<Brick> bricks;
	private int lives;                                                  
	private JLabel restart;
	private boolean isWon;
	private boolean isStarted;
	public BrickMain()
	{
		this.setTitle("BrickBreaker");
		this.setBounds(100,50,615,700);
		this.setLayout(null);
		this.setTitle("Brick Breaker");
		ball = new Ball(200,200);
		this.add(ball);
		paddle = new Paddle(200,600);
		this.add(paddle);
		timer = new Timer(25,this);
		JLabel rules = new JLabel("<html>Welcome to BrickBreaker!<br>" +"You "
				+ "have 3 Lives to Destroy all the Bricks <br>"
				+"Press SPACE to Pause/Unpause<br>"+ "Press SHIFT to Start<br>" + 
				"Use Mouse to Move<br>" + "Good Luck!</html>");
		rules.setBounds(175,150,300,300);
		this.add(rules);
		restart = new JLabel("<html>No Lives Left! <br>" + "Press Enter to Restart</html>");
		restart.setBounds(200,200,300,300);
		lives = 3;
		isWon = false;
		isStarted = false;
		bricks = new ArrayList<Brick>();
		for(int i = 0; i < 50; i+=25)
		{
			for(int j = 0; j < 6; j++)
			{
				bricks.add(new Brick(j*100,i,Color.RED));
			}
		}
		for(int i = 50; i < 100; i+=25)
		{
			for(int j = 0; j < 6; j++)
			{
				bricks.add(new Brick(j*100,i,Color.YELLOW));
			}
		}
		for(int i = 100; i < 150; i+=25)
		{
			for(int j = 0; j < 6; j++)
			{
				bricks.add(new Brick(j*100,i,Color.GREEN));
			}
		}
		for(int k=0; k < bricks.size(); k++)
		{
			this.add(bricks.get(k));
		}
		this.addKeyListener(new KeyListener()
		{	
			Insets insets = getInsets();
			@Override
			public void keyTyped(KeyEvent e) 
			{
				
				
			}
			@Override
			public void keyPressed(KeyEvent e) 
			{
				
				int code = e.getKeyCode();
				switch(code)
				{
					case  KeyEvent.VK_LEFT:
					if(paddle.getX()>0)
					{
						paddle.setDx(-10);
					}
					if(paddle.getX()<0)
					{
						paddle.setDx((0));
						paddle.setLocation(0,paddle.getY());
					}
					break;
				case  KeyEvent.VK_RIGHT:
					if(paddle.getX()+ paddle.getWidth()<getWidth()-insets.right-insets.left)
					{
						paddle.setDx(10);
					}
					if(paddle.getX()+ paddle.getWidth()> getWidth()-insets.right-insets.left)
					{
						paddle.setDx((0));
						paddle.setLocation(getWidth()-paddle.getWidth(),paddle.getY());
					}
					break; 
				case  KeyEvent.VK_SHIFT:
					timer.start();
					isStarted= true;
					remove(rules);
					repaint();
					revalidate();
					break;
				case  KeyEvent.VK_ENTER:
					if(!timer.isRunning() && !isWon && isStarted)
					{
						lives = 3;
						ball.setLocation(200,200);
						for(int i = 0; i<bricks.size(); i++)
						{
							remove(bricks.get(i));
							bricks.remove(i);
							i--;
						}
						for(int i = 0; i < 50; i+=25)
						{
							for(int j = 0; j < 6; j++)
							{
								bricks.add(new Brick(j*100,i,Color.RED));
							}
						}
						for(int i = 50; i < 100; i+=25)
						{
							for(int j = 0; j < 6; j++)
							{
								bricks.add(new Brick(j*100,i,Color.YELLOW));
							}
						}
						for(int i = 100; i < 150; i+=25)
						{
							for(int j = 0; j < 6; j++)
							{
								bricks.add(new Brick(j*100,i,Color.GREEN));
							}
						}
						for(int k=0; k < bricks.size(); k++)
						{
							add(bricks.get(k));
						}
						repaint();
						revalidate();
						remove(restart);
						timer.start();
					}
					break;
				case  KeyEvent.VK_SPACE:
					if(timer.isRunning() && !isWon && isStarted)
					{
						timer.stop();
					}
					else if (!timer.isRunning() && !isWon && isStarted)
					{
						timer.start();
					}
					break;
				}	
			}
			@Override
			public void keyReleased(KeyEvent e) 
			{
				int code = e.getKeyCode();
				switch(code)
				{
				case  KeyEvent.VK_LEFT:
					paddle.setDx(0);
					break;
				case KeyEvent.VK_RIGHT:
					paddle.setDx(0);
					break;
				}
				
			}
		}); 
		this.addMouseMotionListener(new MouseMotionListener() 
		{
			Insets insets = getInsets();
          

			@Override
			public void mouseDragged(MouseEvent e) 
			{
				
				
			}
			private int oldX = 0;

			@Override
			public void mouseMoved(MouseEvent e) 
			{
				if(e.getX()> oldX)
				{
					paddle.setLocation(paddle.getX()+5,paddle.getY());
				}
				if(paddle.getX()+paddle.getWidth()>getWidth()-insets.right-insets.left)
				{
					paddle.setLocation(paddle.getX()-5,paddle.getY());
				}
				if(e.getX()< oldX)
				{
					paddle.setLocation(paddle.getX()-5,paddle.getY());
				}
				if(paddle.getX()<0)
				{
					paddle.setLocation(0,paddle.getY());
				}
				oldX = e.getX();
				
			} 
		});
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	
	}
			

	public static void main(String[] args) 
	{
		new BrickMain();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		ball.update();
		paddle.update();
		if(ball.getBounds().intersects(paddle.getBounds()))
		{
			if(ball.getY()+ball.getHeight()> paddle.getY()) 
			{	
				ball.setDy(ball.getDy()*-1);
				ball.setLocation(ball.getX(),paddle.getY()-ball.getHeight());
			}
		}
		for(int i=0; i < bricks.size(); i++)
		{
			if(ball.getBounds().intersects(bricks.get(i).getBounds()))
			{
				ball.setDy(ball.getDy()*-1);
				remove(bricks.get(i));
				bricks.remove(i);
				this.repaint();
				this.revalidate();
			}
		}
		Insets insets = this.getInsets();
		if(ball.getX()<0)
		{
			ball.setDx(ball.getDx()*-1);

		}
		else if(ball.getX() + ball.getWidth() > this.getWidth()- insets.left - insets.right)
		{
			ball.setDx(ball.getDx()*-1);
		}
		if(ball.getY()<0)
		{
			ball.setDy(ball.getDy()*-1);
		}
		else if(ball.getY() + ball.getHeight() > this.getHeight()- insets.top - insets.bottom)
		{
			ball.setLocation(200,200);
			lives--;
		}
		if(lives==0)
		{
			timer.stop();
			this.add(restart);
			this.repaint();
			this.revalidate();
		}
		if(bricks.size()==0)
		{
			timer.stop();
			isWon = true;
			JLabel rules = new JLabel("You Win!");
			rules.setBounds(175,150,100,30);
			this.add(rules);
			this.repaint();
			this.revalidate();
			
		}
		
	}

}
