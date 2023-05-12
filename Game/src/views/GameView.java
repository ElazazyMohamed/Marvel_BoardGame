package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GameView {

	private JFrame frame = new JFrame() ;
	private  startpanel  startpanel ;
	public String p1name ;
	private String p2name ;
	private charpanel choosecharacters;
	private boardpanel boardpanel ;
	private endpanel endpanel ;
	
	public endpanel getEndpanel() {
		return endpanel;
	}



	public void setEndpanel(endpanel endpanel) {
		this.endpanel = endpanel;
	}



	public boardpanel getBoardpanel() {
		return boardpanel;
	}



	public void setBoardpanel(boardpanel boardpanel) {
		this.boardpanel = boardpanel;
	}



	public GameView() throws FontFormatException, IOException {
	
	frame.setTitle("Marvel");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	frame.setSize(1500, 800);
	ImageIcon gameicon = new ImageIcon(".//resources//sora.png");
	frame.setIconImage(gameicon.getImage());
	frame.setLayout(null);
	frame.setVisible(true);
	frame.revalidate();
	frame.repaint();
	
	
	startpanel = new startpanel(frame,this);
	choosecharacters = new charpanel(frame,this);	
	
	
	frame.add(startpanel);
	
	
	}
	
	
	
	public startpanel getStartpanel() {
		return startpanel;
	}



	public void setStartpanel(startpanel startpanel) {
		this.startpanel = startpanel;
	}



	public charpanel getChoosecharacters() {
		return choosecharacters;
	}



	public void setChoosecharacters(charpanel choosecharacters) {
		this.choosecharacters = choosecharacters;
	}



	public String getP1name() {
		return p1name;
	}



	public void setP1name(String p1name) {
		this.p1name = p1name;
	}



	public String getP2name() {
		return p2name;
	}



	public void setP2name(String p2name) {
		this.p2name = p2name;
	}



	

	
}
