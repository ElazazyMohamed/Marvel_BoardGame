package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;

public class boardpanel extends JPanel implements ActionListener {

	public JFrame frame ;
	public GameView view ;
	public JLabel name ;
	public JLabel type ;
	public JLabel hp ;
	public JLabel mana ;
	public JLabel actionpoints ;
	public JLabel damage ;
	public JLabel range ;
	public JLabel Leader ;
	public JPanel champdetails;
	public JPanel rightpanel ;
	public JPanel  actions ;
	public JPanel directions;
	public JButton upBtn;
	public JButton downBtn;
	public JButton rightBtn;
	public JButton leftBtn;
	public JButton moveBtn;
	public JButton attackBtn;
	public JButton ability1Btn;
	public JButton ability2Btn;
	public JButton ability3Btn;
	public JButton leaderAbilityBtn;
	public JButton endBtn;
	public JPanel main ;
	public JPanel abilitydetails ;
	public JPanel playerdetails ;
	public JLabel p1 ;
	public JLabel p2 ;
	public JTextArea abilityinfo ;
	public JPanel actionscontainer ;
	private BoardPanelListener listener;
	public JScrollPane scrollpanel ;
	
	
	public boardpanel(JFrame frame1, GameView view1) {
		
		frame = frame1 ;
		view = view1 ;
		frame.revalidate();
		frame.repaint();
		this.setLayout(new BorderLayout());
		this.setBounds(0,0,1500,800);
		
		 champdetails = new JPanel();
		champdetails.setPreferredSize(new Dimension(this.getWidth(), 80));
		champdetails.setBackground(Color.black);
		
		rightpanel = new JPanel();
		 rightpanel.setPreferredSize(new Dimension(200,this.getHeight()));
		 rightpanel.setBackground(Color.blue);
		 rightpanel.setLayout(new BorderLayout());
	
		 	
		 abilityinfo = new JTextArea() ;
		 	abilityinfo.setBackground(Color.black);
		 	abilityinfo.setEditable(false);
		//	abilityinfo.setPreferredSize(new Dimension(200, 510));
			abilityinfo.setForeground(Color.white);
			abilityinfo.setFont(new Font("MV Boli",Font.PLAIN, 15));
			 
		 scrollpanel = new JScrollPane(abilityinfo,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				//	 scrollpanel.setLayout(new BorderLayout());
				//	 scrollpanel.setBackground(Color.red);
		 scrollpanel.setPreferredSize(new Dimension(200,510));
					 scrollpanel.setBackground(Color.black);
					 
		 
		 
		 
		 playerdetails = new JPanel(new BorderLayout());
		
		 playerdetails.setPreferredSize(new Dimension(200,110));
		 playerdetails.setBackground(Color.BLACK);
		 p1 = new JLabel("Player 1");
		 p1.setVerticalTextPosition(JLabel.TOP);
		
		 p1.setHorizontalTextPosition(JLabel.CENTER);
		 p1.setIcon(new ImageIcon(".//resources//notused.png"));

		 p1.setForeground(Color.white);
		 p1.setFont(new Font("Avengeance Heroic Avenger",Font.PLAIN, 15));
		 p1.setIconTextGap(0);
		 playerdetails.add(p1,BorderLayout.WEST);
		 
		 p2 = new JLabel("Player 2");
		 p2.setIcon(new ImageIcon(".//resources//notused.png"));
		 p2.setVerticalTextPosition(JLabel.TOP);
		 p2.setHorizontalTextPosition(JLabel.CENTER);
		 p2.setForeground(Color.white);
		 p2.setFont(new Font("Avengeance Heroic Avenger",Font.PLAIN, 15));
		 p2.setIconTextGap(0);
		 
		 playerdetails.add(p2,BorderLayout.EAST);
		 
	   rightpanel.add(scrollpanel,BorderLayout.NORTH);
	   rightpanel.add(playerdetails,BorderLayout.SOUTH);
		 
		actions = new JPanel(new BorderLayout());
		actions.setPreferredSize(new Dimension(this.getWidth(), 100));
		actions.setBackground(Color.LIGHT_GRAY);
		
		
		
		
		directions = new JPanel(new BorderLayout());
		directions.setPreferredSize(new Dimension(200,100));
		directions.setBackground(Color.black);
		actions.add(directions , BorderLayout.EAST);
		 
		
		actionscontainer = new JPanel();
		actionscontainer.setBackground(Color.black);
		actionscontainer.setPreferredSize(new Dimension(1300, 100));
		actionscontainer.setLayout(new GridLayout(1,0,8,8));
		actions.add(actionscontainer);
		
		
		 
		
		main = new JPanel();
		main.setLayout(new GridLayout(5,5,8,8));
		main.setBackground(Color.black);
		
		createlabels();
		createactions();
		
		this.setBackground(Color.magenta);
		this.add(main);
		this.add(actions,BorderLayout.SOUTH);
		this.add(champdetails,BorderLayout.NORTH);
		this.add(rightpanel, BorderLayout.EAST);
		
		
		
		frame.getRootPane().getInputMap().put(KeyStroke.getKeyStroke("UP"),"clickButtonup");
				frame.getRootPane().getActionMap().put("clickButtonup",new AbstractAction(){
				        public void actionPerformed(ActionEvent ae)
				        {
				    upBtn.doClick();
				        }
				    }  );
			
				frame.getRootPane().getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"clickButtondown");
				frame.getRootPane().getActionMap().put("clickButtondown",new AbstractAction(){
				        public void actionPerformed(ActionEvent ae)
				        {
				    downBtn.doClick();
				        }
				    }  );		
						
				frame.getRootPane().getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"clickButtonright");
				frame.getRootPane().getActionMap().put("clickButtonright",new AbstractAction(){
				        public void actionPerformed(ActionEvent ae)
				        {
				    rightBtn.doClick();
				        }
				    }  );
								
				frame.getRootPane().getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"clickButtonleft");
				frame.getRootPane().getActionMap().put("clickButtonleft",new AbstractAction(){
				        public void actionPerformed(ActionEvent ae)
				        {
				    leftBtn.doClick();
				        }
				    }  );
				frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
				(KeyStroke.getKeyStroke(KeyEvent.VK_E,0),"end");
						frame.getRootPane().getActionMap().put("end",new AbstractAction(){
						        public void actionPerformed(ActionEvent ae)
						        {
						    endBtn.doClick();
						        }
						    }  );
						frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
						(KeyStroke.getKeyStroke(KeyEvent.VK_M,0),"move");
								frame.getRootPane().getActionMap().put("move",new AbstractAction(){
								        public void actionPerformed(ActionEvent ae)
								        {
								    moveBtn.doClick();
								        }
								    }  );
								frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
								(KeyStroke.getKeyStroke(KeyEvent.VK_A,0),"attack");
										frame.getRootPane().getActionMap().put("attack",new AbstractAction(){
										        public void actionPerformed(ActionEvent ae)
										        {
										    attackBtn.doClick();
										        }
										    }  );
										frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
										(KeyStroke.getKeyStroke(KeyEvent.VK_1,0),"a1");
												frame.getRootPane().getActionMap().put("a1",new AbstractAction(){
												        public void actionPerformed(ActionEvent ae)
												        {
												    ability1Btn.doClick();
												        }
												    }  );
												frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
												(KeyStroke.getKeyStroke(KeyEvent.VK_2,0),"a2");
														frame.getRootPane().getActionMap().put("a2",new AbstractAction(){
														        public void actionPerformed(ActionEvent ae)
														        {
														    ability2Btn.doClick();
														        }
														    }  );
														frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put
														(KeyStroke.getKeyStroke(KeyEvent.VK_3,0),"a3");
																frame.getRootPane().getActionMap().put("a3",new AbstractAction(){
																        public void actionPerformed(ActionEvent ae)
																        {
																    ability3Btn.doClick();
																        }
																    }  );
	frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_L,0),"leader");
	frame.getRootPane().getActionMap().put("leader",new AbstractAction(){
																		        public void actionPerformed(ActionEvent ae)
			     {
		  leaderAbilityBtn.doClick();
			    }
																		    }  );														
		
		
		frame.revalidate();
		frame.repaint();
	//	frame.add(this);
	}
	public void createactions() {
		
		
		
		
		
		moveBtn = new JButton("Move");
		moveBtn.setFocusable(false);
		moveBtn.setFont(new Font("MV Boli",Font.BOLD,20));
		moveBtn.setBackground(new Color(26, 50, 62));
		moveBtn.setForeground(new Color(247, 210, 129));
		actionscontainer.add(moveBtn);
		moveBtn.addActionListener(this);
		
		attackBtn = new JButton("Attack");
		attackBtn.setFocusable(false);
		attackBtn.setFont(new Font("MV Boli",Font.BOLD,20));
		attackBtn.setBackground(new Color(26, 50, 62));
		attackBtn.setForeground(new Color(247, 210, 129));
		actionscontainer.add(attackBtn);
		attackBtn.addActionListener(this);
		
		ability1Btn= new JButton("Ability1");
		ability1Btn.setFocusable(false);
		ability1Btn.setFont(new Font("MV Boli",Font.BOLD,15));
		ability1Btn.setBackground(new Color(26, 50, 62));
		ability1Btn.setForeground(new Color(247, 210, 129));
		actionscontainer.add(ability1Btn);
		ability1Btn.addActionListener(this);
		
		ability2Btn= new JButton("Ability2");
		ability2Btn.setFocusable(false);
		ability2Btn.setFont(new Font("MV Boli",Font.BOLD,15));
		ability2Btn.setBackground(new Color(26, 50, 62));
		ability2Btn.setForeground(new Color(247, 210, 129));
		actionscontainer.add(ability2Btn);
		ability2Btn.addActionListener(this);
		
		ability3Btn= new JButton("Ability3");
		ability3Btn.setFocusable(false);
		ability3Btn.setFont(new Font("MV Boli",Font.BOLD,15));
		ability3Btn.setBackground(new Color(26, 50, 62));
		ability3Btn.setForeground(new Color(247, 210, 129));
		actionscontainer.add(ability3Btn);
		ability3Btn.addActionListener(this);
		
		leaderAbilityBtn= new JButton("LeaderAbility");
		leaderAbilityBtn.setFocusable(false);
		leaderAbilityBtn.setFont(new Font("MV Boli",Font.BOLD,20));
		leaderAbilityBtn.setBackground(new Color(26, 50, 62));
		leaderAbilityBtn.setForeground(new Color(247, 210, 129));
		actionscontainer.add(leaderAbilityBtn);
		leaderAbilityBtn.addActionListener(this);
		
		endBtn= new JButton("End Turn");
		endBtn.setFocusable(false);
		endBtn.setFont(new Font("MV Boli",Font.BOLD,20));
		endBtn.setBackground(new Color(26, 50, 62));
		endBtn.setForeground(new Color(247, 210, 129));
		actionscontainer.add(endBtn);
		endBtn.addActionListener(this);
		
		upBtn= new JButton("UP");
		upBtn.setFocusable(false);
		upBtn.setFont(new Font("MV Boli",Font.BOLD,20));
		upBtn.setBackground(new Color(26, 50, 62));
		upBtn.setForeground(new Color(247, 210, 129));
		directions.add(upBtn, BorderLayout.NORTH);
		upBtn.setPreferredSize(new Dimension(60, 30));
		upBtn.addActionListener(this);
		
		downBtn= new JButton("DOWN");
		downBtn.setFocusable(false);
		downBtn.setFont(new Font("MV Boli",Font.BOLD,20));
		downBtn.setBackground(new Color(26, 50, 62));
		downBtn.setForeground(new Color(247, 210, 129));
		directions.add(downBtn, BorderLayout.SOUTH);
		downBtn.setPreferredSize(new Dimension(60, 30));
		downBtn.addActionListener(this);
		
		rightBtn= new JButton("RIGHT");
		rightBtn.setFocusable(false);
		rightBtn.setFont(new Font("MV Boli",Font.BOLD,20));
		rightBtn.setBackground(new Color(26, 50, 62));
		rightBtn.setForeground(new Color(247, 210, 129));
		directions.add(rightBtn, BorderLayout.EAST);
		rightBtn.setPreferredSize(new Dimension(110, 30));
		rightBtn.addActionListener(this);
		
		leftBtn= new JButton("LEFT");
		leftBtn.setFocusable(false);
		leftBtn.setFont(new Font("MV Boli",Font.BOLD,20));
		leftBtn.setBackground(new Color(26, 50, 62));
		leftBtn.setForeground(new Color(247, 210, 129));
		directions.add(leftBtn, BorderLayout.WEST);
		leftBtn.setPreferredSize(new Dimension(90, 30));
		leftBtn.addActionListener(this);
		
		
		
		
	}
	public void createlabels() {
		name = new JLabel("") ;
		name.setPreferredSize(new Dimension(180, 60));
		name.setForeground(Color.white);
		name.setFont(new Font("Avengeance Heroic Avenger",Font.BOLD, 20));
		
		
		
		type = new JLabel("") ;
		type.setPreferredSize(new Dimension(180, 60));
		type.setForeground(Color.white);
		type.setFont(new Font("Avengeance Heroic Avenger",Font.BOLD, 20));
		
		hp = new JLabel() ;
		hp.setPreferredSize(new Dimension(180, 60));
		hp.setForeground(Color.white);
		hp.setFont(new Font("Avengeance Heroic Avenger",Font.BOLD, 20));
		hp.setIcon(new ImageIcon(".//resources//health.png"));
		
		mana = new JLabel() ;
		mana.setPreferredSize(new Dimension(180, 60));
		mana.setForeground(Color.white);
		mana.setFont(new Font("Avengeance Heroic Avenger",Font.BOLD, 20));
		mana.setIcon(new ImageIcon(".//resources//stamina.png"));
		
		actionpoints = new JLabel("") ;
		actionpoints.setPreferredSize(new Dimension(260, 60));
		actionpoints.setForeground(Color.white);
		actionpoints.setFont(new Font("Avengeance Heroic Avenger",Font.BOLD, 20));
		
		damage = new JLabel() ;
		damage.setPreferredSize(new Dimension(180, 60));
		damage.setForeground(Color.white);
		damage.setFont(new Font("Avengeance Heroic Avenger",Font.BOLD, 20));
		damage.setIcon(new ImageIcon(".//resources//damage.png"));
		
		range = new JLabel() ;
		range.setPreferredSize(new Dimension(180, 60));
		range.setForeground(Color.white);
		range.setFont(new Font("Avengeance Heroic Avenger",Font.BOLD, 20));
		range.setIcon(new ImageIcon(".//resources//range.png"));
		
		Leader = new JLabel() ;
		Leader.setPreferredSize(new Dimension(100, 60));
		Leader.setForeground(Color.white);
		Leader.setFont(new Font("Avengeance Heroic Avenger",Font.BOLD, 20));
		
		
		champdetails.add(name);champdetails.add(type);champdetails.add(hp);champdetails.add(mana);champdetails.add(actionpoints);champdetails.add(damage);champdetails.add(range);
		champdetails.add(Leader);
		
		
		
		
		
	}
	
	public void addchampbtns(JButton btn) {
		main.add(btn);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton currentBtn = (JButton) e.getSource();
		if(currentBtn == moveBtn) 
			listener.onmove(currentBtn);
		if(currentBtn == endBtn)
			listener.onendturn();
		if(currentBtn == attackBtn) {
			listener.onattack(currentBtn);
		}
		if(currentBtn == leaderAbilityBtn)
			try {
				listener.onleaderability();
			} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
		if (currentBtn == rightBtn || currentBtn == upBtn ||currentBtn == downBtn ||currentBtn == leftBtn  )
			try {
				listener.direction(currentBtn);
			} catch (NotEnoughResourcesException | UnallowedMovementException | ChampionDisarmedException
					| InvalidTargetException | AbilityUseException | CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			} 
			
		if(currentBtn == ability1Btn)
			try {
				listener.onab1(ability1Btn);
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
		if(currentBtn == ability2Btn)
			try {
				listener.onab2(ability2Btn);
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
		if(currentBtn == ability3Btn)
			try {
				listener.onab3(ability3Btn);
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
			}
		 
			
		
	}
	public BoardPanelListener getListener() {
		return listener;
	}
	public void setListener(BoardPanelListener listener) {
		this.listener = listener;
	}
	
}
