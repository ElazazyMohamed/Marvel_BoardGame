package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class charpanel extends JPanel {

	
	
	private JFrame frame ;
	private GameView view;
	private JPanel champs = new JPanel() ;
	private JPanel champinf = new JPanel();
	public JLabel sora ;
	public JTextArea info;
	public JLabel title ;
	public JButton subm ;
	

	public charpanel(JFrame frame1, GameView view1) {
		frame = frame1 ;
		view = view1 ;
		this.setBounds(0,0,1500,800);
		this.setBackground(new Color(139,0,0));
		frame.revalidate();
		frame.repaint();
		
		
		
			this.setLayout(new BorderLayout());
		  champs.setLayout(new GridLayout(5, 3,8,8));
		  champs.setBackground(Color.black);
		  
			
			champinf.setPreferredSize(new Dimension(300, getHeight()));
			champinf.setLayout(new BorderLayout());
			 info = new JTextArea();
			info.setFont(new Font("MV Boli",Font.BOLD,15));
			info.setBackground(new Color(247, 210, 129));
			info.setEditable(false);
			
			
			
			
			
			
			 sora = new JLabel();
			 sora.setBackground(new Color(0,0,63));
			 
			
			sora.setPreferredSize(new Dimension(300,250));
			
			champinf.add(sora, BorderLayout.NORTH);
			champinf.add(info,BorderLayout.CENTER);
			frame.revalidate();
			frame.repaint();
			
			
			title = new JLabel();
			title.setLayout(new BorderLayout());
			title.setPreferredSize(new Dimension(1300, 80));
			title.setFont(new Font("Snap ITC",Font.BOLD,20));
			title.setForeground(Color.white);
			title.setHorizontalTextPosition(JLabel.CENTER);
			
			
			
			add(title,BorderLayout.NORTH);
			add(champinf, BorderLayout.EAST);
			add(champs,BorderLayout.CENTER);
			frame.revalidate();
			frame.repaint();
		
		
	}
	
	public GameView getView() {
		return view;
	}

	public void setView(GameView view) {
		this.view = view;
	}

	public void addChampion(JButton b) {
		champs.add(b);
		frame.revalidate();
		frame.repaint();
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	
	public void addchoosebtn(JButton subm) {
		subm.setText("CHOOSE");
		subm.setPreferredSize(new Dimension(300,120));
		champinf.add(subm,BorderLayout.SOUTH);
		subm.setFocusable(false);
		subm.setBackground(new Color(	27, 114, 159));
		subm.setFont(new Font("Avengeance Heroic Avenger",Font.BOLD, 20));
		subm.setForeground(new Color(247, 210, 129));
	}
	
	
		
	}

	
	

