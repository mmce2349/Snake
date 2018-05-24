package SnakeController;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Snake extends JFrame
{
	public Snake()
	{
		initUI();//have some more problems i need to deal with
	}
	private void initUI()
	{
		add(new Board());
		
		setResizable(false);
		pack();
		
		setTitle("Class project: Snake");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main (String [] args)
	{
		EventQueue.invokeLater(() -> 
		{
			JFrame ex = new Snake();
			ex.setVisible(true);
		});
	}
}
