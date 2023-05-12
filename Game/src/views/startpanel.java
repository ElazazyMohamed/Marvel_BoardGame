package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.sound.sampled.*;
public class startpanel extends JPanel implements ActionListener{
	
	private String p1 ;
	private String p2;
	private JButton b1;
	private JTextField text1;
	private JTextField text2;
	private JFrame frame ;
	private GameView view;
	private StartPanelListener listener ;
	public Clip clip ;
	
	public startpanel(JFrame frame1 , GameView view1) {
		
		AudioInputStream audiostream = null ;
		File file = new File("Marvel Opening Theme.wav");
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
		
		
		
		view = view1 ;
		frame = frame1 ;
		
		this.setBounds(0, 0, 1500, 800);
		
		
		
		
		ImageIcon back = new ImageIcon(".//resources//back2.png");
		JLabel background = new JLabel(back,JLabel.CENTER);
		background.setBounds(0,0,1500,800);
		
		
		
		JLabel l1 = new JLabel("Player 1 Name");
		l1.setBounds(380,280,280,50);
		l1.setFont(new Font("MS Gothic",Font.BOLD,30));
		l1.setForeground(Color.white);
		background.add(l1);
		
		
		 text1 = new JTextField("");
		text1.setBounds(620, 280, 400, 70);
		text1.setFont(new Font("MV Boli",Font.BOLD,20));
		
		
		
		
		
		JLabel l2 = new JLabel("Player 2 Name");
		l2.setBounds(380,380,280,50);
		l2.setFont(new Font("MS Gothic",Font.BOLD,30));
		l2.setForeground(Color.white);
		background.add(l2);
		
		 text2 = new JTextField("");
		text2.setBounds(620, 380, 400, 70);
		text2.setFont(new Font("MV Boli",Font.BOLD,20));
		
		
		
		 b1 = new JButton("Submit");
		b1.setFont(new Font("MV Boli",Font.BOLD,20));
		b1.setBounds(680,500,300,50);
		b1.addActionListener(this);
		
		background.add(text2);
		background.add(b1);
		background.add(text1);
	
		this.add(background);
	
		
		
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
		(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"clickButton");
				frame.getRootPane().getActionMap().put("clickButton",new AbstractAction(){
				        public void actionPerformed(ActionEvent ae)
				        {
				    b1.doClick();
				        }
				    }  );
		
		
		
		
		frame.revalidate();
		frame.repaint();
		
		
		
	}



	public StartPanelListener getListener() {
		return listener;
	}



	public void setListener(StartPanelListener listener) {
		this.listener = listener;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			if(text1.getText().equals("") )
				JOptionPane.showMessageDialog(null, "Missing Player 1 name");
			else if(text2.getText().equals("") )
				JOptionPane.showMessageDialog(null, "Missing Player 2 name");
			else {
			
			view.p1name = text1.getText();
			view.setP2name(text2.getText());
			try {
				onstartgame(text1.getText(), text2.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			frame.remove(this);
			frame.add(view.getChoosecharacters());
		    frame.revalidate();
			frame.repaint();
			}
		}
		
	}



	public GameView getView() {
		return view;
	}



	public void setView(GameView view) {
		this.view = view;
	}



	public String getP1() {
		return p1;
	}



	public void setP1(String p1) {
		this.p1 = p1;
	}



	public String getP2() {
		return p2;
	}



	public void setP2(String p2) {
		this.p2 = p2;
	}



	public void onstartgame(String x , String y) throws IOException {
		if(listener != null)
			listener.onstart(x, y);
		
	}







	
	
	
	
	
	

}
