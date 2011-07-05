package Windows;

import game.MainCanvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class WindowFrame extends JFrame{
	
	FramePanel fp = new FramePanel();
	MainCanvas maincanvas = new MainCanvas();
	JButton play;
	Dimension d;
	
	public WindowFrame (){
		super("TANK");
		setPreferredSize(new Dimension (800,575));
		setBackground(Color.WHITE);
		play = fp.getPlayButton();
		add(fp, BorderLayout.CENTER);
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				remove(fp);
				addMaincanvas();
				repaint();
			}
		});
		
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	private void addMaincanvas(){
		add(maincanvas, BorderLayout.CENTER);
		maincanvas.setFocusable(true);
		maincanvas.requestFocusInWindow();
	}
	
	private void addFramePanel(){
		Insets i = getInsets();
		d = fp.getPreferredSize();
		fp.setBounds(i.left,i.top,d.width,d.height);
		add(fp);
	}
}
