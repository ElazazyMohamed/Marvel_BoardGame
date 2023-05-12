package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class endpanel extends JPanel{

	public JFrame frame ;
	public GameView view ;
	public Clip clip ;
	
	public endpanel(JFrame f , GameView v,String s) {
		
		view = v ;
		frame = f ;
		
		
		JButton btn = new JButton("Quit Game");
		btn.setFocusable(false);
		btn.setFont(new Font("MV Boli",Font.BOLD,20));
		btn.setBackground(new Color(26, 50, 62));
		btn.setForeground(new Color(247, 210, 129));
		btn.setPreferredSize(new Dimension(350,100));
		btn.addActionListener(e -> {
			clip.close();
			frame.setVisible(false);
			frame.dispose();
		});
		btn.setBorder(BorderFactory.createEtchedBorder());
		
		
		
		this.setBounds(0, 0, 1500, 800);
		this.setLayout(null);
		this.setBackground(Color.black);
		ImageIcon back = new ImageIcon(".//resources//R.gif");
		JLabel background = new JLabel(back,JLabel.CENTER);
		background.setText(s);
		background.setFont(new Font("Avengeance Heroic Avenger",Font.BOLD,30));
		background.setVerticalTextPosition(JLabel.BOTTOM);
		background.setHorizontalTextPosition(JLabel.CENTER);
		background.setForeground(Color.white);
		background.setIconTextGap(20);
		background.setBounds(0,0,1500,800);
		
		this.add(background);
		btn.setBounds(580, 650, 350, 100);
		this.add(btn);
		AudioInputStream audiostream = null ;
		File file = new File("win.wav");
		try {
			 audiostream = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			clip.open(audiostream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		clip.start();
		
		
		
		frame.revalidate();
		frame.repaint();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
