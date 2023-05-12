package controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

import engine.*;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.GameActionException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;
import model.world.Direction;
import model.world.Hero;
import views.BoardPanelListener;
import views.GameView;

import views.StartPanelListener;
import views.boardpanel;
import views.endpanel;


public class Controller implements StartPanelListener,ActionListener,BoardPanelListener {

	
		private Game model ;
		private GameView view ;
		private ArrayList<JButton> champbuttons ;
		private ArrayList<ImageIcon> champicons ;
		private static int currentindex ;
		private static int numofchampschosen ;
		private JButton confirm ;
		private static JButton lastpressed ;
		public  JButton[][] champarr  = new JButton[5][5];
		public ArrayList<ImageIcon> minichampicons ;
		public Clip clip ;
		public Controller() throws FontFormatException, IOException {
			view = new GameView();
			view.getStartpanel().setListener(this);
			
		}
		
	public void chmpbtnsound() {
		AudioInputStream audiostream = null ;
		File file = new File("chclick.wav");
		try {
			 audiostream = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		try {
			clip.open(audiostream);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		clip.start();
	}
	public static void main(String[] args) throws FontFormatException, IOException {
		
		new Controller();
		
		
		

	}


	@Override
	public void onstart(String x, String y) throws IOException  {
		model = new Game(new Player(x), new Player(y));
		Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");
		numofchampschosen = 0 ;
		
		confirm = new JButton();
		confirm.addActionListener(this);
		view.getChoosecharacters().addchoosebtn(confirm);
		champbuttons = new ArrayList<JButton>();
		
		for ( Champion c : model.getAvailableChampions()) {
			
			JButton btn = new JButton();
			
			btn.setText(c.getName());
			btn.setFocusable(false);
			btn.setFont(new Font("MV Boli",Font.BOLD,20));
			btn.setBackground(new Color(26, 50, 62));
			btn.setForeground(new Color(247, 210, 129));
			btn.addActionListener(this);
			btn.setBorder(BorderFactory.createEtchedBorder());
			
			
			view.getChoosecharacters().addChampion(btn);
			view.getChoosecharacters().title.setText("Choose Your Leader "+x);
			view.getChoosecharacters().title.setHorizontalAlignment(JLabel.CENTER);
			
			
			
			
			champbuttons.add(btn);
			
			
		}
		view.getChoosecharacters().getFrame().revalidate();
		view.getChoosecharacters().getFrame().repaint();
		champicons = new ArrayList<>();
		minichampicons = new ArrayList<>();
		loadicons();
		
	}

	
	public void loadicons() {
		champicons.add(new ImageIcon(".//resources//captainamerica.png"));
		champicons.add(new ImageIcon(".//resources//deadpool.png"));
		champicons.add(new ImageIcon(".//resources//drstrange.png"));
		champicons.add(new ImageIcon(".//resources//electro.png"));
		champicons.add(new ImageIcon(".//resources//ghostrider.png"));
		champicons.add(new ImageIcon(".//resources//hela.png"));
		champicons.add(new ImageIcon(".//resources//hulk.png"));
		champicons.add(new ImageIcon(".//resources//iceman.png"));
		champicons.add(new ImageIcon(".//resources//ironman.png"));
		champicons.add(new ImageIcon(".//resources//loki.png"));
		champicons.add(new ImageIcon(".//resources//quicksilver.png"));
		champicons.add(new ImageIcon(".//resources//spiderman.png"));
		champicons.add(new ImageIcon(".//resources//thor.png"));
		champicons.add(new ImageIcon(".//resources//venom.png"));
		champicons.add(new ImageIcon(".//resources//yellowjacket.png"));
		
		minichampicons.add(new ImageIcon(".//resources//minicaptainamerica.png"));
		minichampicons.add(new ImageIcon(".//resources//minideadpool.png"));
		minichampicons.add(new ImageIcon(".//resources//minidrstrange.png"));
		minichampicons.add(new ImageIcon(".//resources//minielectro.png"));
		minichampicons.add(new ImageIcon(".//resources//minighostrider.png"));
		minichampicons.add(new ImageIcon(".//resources//minihela.png"));
		minichampicons.add(new ImageIcon(".//resources//minihulk.png"));
		minichampicons.add(new ImageIcon(".//resources//miniiceman.png"));
		minichampicons.add(new ImageIcon(".//resources//miniironman.png"));
		minichampicons.add(new ImageIcon(".//resources//miniloki.png"));
		minichampicons.add(new ImageIcon(".//resources//miniquicksilver.png"));
		minichampicons.add(new ImageIcon(".//resources//minispiderman.png"));
		minichampicons.add(new ImageIcon(".//resources//minithor.png"));
		minichampicons.add(new ImageIcon(".//resources//minivenom.png"));
		minichampicons.add(new ImageIcon(".//resources//miniyellowjacket.png"));
	}
	

	@Override
	public void actionPerformed(ActionEvent e)  {
		JButton newbtn = (JButton) e.getSource();
		
		if(newbtn== confirm)
			{	
			if(lastpressed.isEnabled())
			onplayerchosen();
			else
				JOptionPane.showMessageDialog(null, "You Cannot Choose the same Champion Twice", null, JOptionPane.ERROR_MESSAGE);
			}
		else if (champbuttons.contains(newbtn) )
		{
			
			chmpbtnsound();
			lastpressed = newbtn;
		int index = champbuttons.indexOf(newbtn);
		currentindex = index ;
		Champion c = model.getAvailableChampions().get(index);
		view.getChoosecharacters().info.setText(c.toString());
		view.getChoosecharacters().sora.setIcon(( champicons.get(index))); }
		
		else if(lastpressed == view.getBoardpanel().ability1Btn ||lastpressed == view.getBoardpanel().ability2Btn || lastpressed == view.getBoardpanel().ability3Btn) {
			chmpbtnsound();
			ArrayList<Integer> a = getIJ(newbtn);
			int i = a.get(0); int j = a.get(1);
			if(lastpressed == view.getBoardpanel().ability1Btn)
				try {
					model.castAbility(model.getCurrentChampion().getAbilities().get(0), i, j);
				} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
						| CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			if(lastpressed == view.getBoardpanel().ability2Btn)
				try {
					model.castAbility(model.getCurrentChampion().getAbilities().get(1), i, j);
				} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
						| CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			if(lastpressed == view.getBoardpanel().ability3Btn)
				try {
					model.castAbility(model.getCurrentChampion().getAbilities().get(2), i, j);
				} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
						| CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, JOptionPane.ERROR_MESSAGE);
				}
			 
				
			updateboard();updatecurrentchampion();
			
			if(model.checkGameOver() != null)
				win();
			lastpressed=null;
		}
		
		else {
			
			chmpbtnsound();
			ArrayList<Integer> a = getIJ(newbtn);
			int i = a.get(0); int j = a.get(1);
			Damageable d =(Damageable) model.board[i][j] ;
			if(d instanceof Cover) {
				view.getBoardpanel().name.setText("");
				view.getBoardpanel().hp.setText(""+d.getCurrentHP());
				view.getBoardpanel().abilityinfo.setText("");
				view.getBoardpanel().mana.setText("");
				view.getBoardpanel().actionpoints.setText("");
				view.getBoardpanel().damage.setText("");
				view.getBoardpanel().range.setText("");
				view.getBoardpanel().type.setText("Type : Cover");
				view.getBoardpanel().Leader.setText("");
				
			//	view.getBoardpanel().ability1Btn.setText("");
			//	view.getBoardpanel().ability2Btn.setText("");
			//	view.getBoardpanel().ability3Btn.setText("");
				
			}
			else if(d instanceof Champion) {
				Champion c = (Champion)d;
				String abilityinf = getabilityinf(c);
				if(model.getFirstPlayer().getLeader().equals(c) ||model.getSecondPlayer().getLeader().equals(c) )
					view.getBoardpanel().Leader.setText("Leader");
				else
					view.getBoardpanel().Leader.setText("Not Leader");
				
				view.getBoardpanel().abilityinfo.setText(abilityinf);
				
			//	view.getBoardpanel().ability1Btn.setText(c.getAbilities().get(0).getName());
			//	view.getBoardpanel().ability2Btn.setText(c.getAbilities().get(1).getName());
			//	view.getBoardpanel().ability3Btn.setText(c.getAbilities().get(2).getName());
				String ns = "Player : ";
				ns += (model.getFirstPlayer().getTeam().contains(c)) ?  model.getFirstPlayer().getName() :  model.getSecondPlayer().getName();
				view.getBoardpanel().name.setText(ns);
				view.getBoardpanel().hp.setText(""+c.getCurrentHP());
				view.getBoardpanel().mana.setText(""+c.getMana());
				view.getBoardpanel().actionpoints.setText("Current Action Points : "+c.getCurrentActionPoints());
				view.getBoardpanel().damage.setText(""+c.getAttackDamage());
				view.getBoardpanel().range.setText(""+c.getAttackRange());
				
				String s ;
				if(c instanceof Hero)
					s = "Hero";
				else if( c instanceof AntiHero)
					s = "Anti-Hero" ;
					else
						s = "Villain" ;
				view.getBoardpanel().type.setText("Type : " +s);
				
			}
			else {
				view.getBoardpanel().name.setText("");
				view.getBoardpanel().hp.setText("");
				view.getBoardpanel().abilityinfo.setText("");
				view.getBoardpanel().mana.setText("");
				view.getBoardpanel().actionpoints.setText("");
				view.getBoardpanel().damage.setText("");
				view.getBoardpanel().range.setText("");
				view.getBoardpanel().type.setText("");
				view.getBoardpanel().Leader.setText("");
			//	view.getBoardpanel().ability1Btn.setText("");
			//	view.getBoardpanel().ability2Btn.setText("");
			//	view.getBoardpanel().ability3Btn.setText("");
				
			}
			
			
			
		}
		
	
	
	}

	public ArrayList getIJ(JButton btn) {
		ArrayList x = new ArrayList<>();
		
		for(int i = 0 ; i < 5 ; i ++) {
			for(int j = 0 ; j < 5 ; j++) {
				if (btn == champarr[i][j])
					{x.add(i);x.add(j);break;
					
					}
			}
		}
		return x ;
		
	}
	
	public String getabilityinf(Champion c) {
		String s = "     "+c.getName()+"\n";
		for(Ability a : c.getAbilities()) {
			s+= "Abilty Name : "+a.getName()+" \n"
					+ "Mana Cost : "+a.getManaCost()+" \n"
					+ "Base Cooldown : "+a.getBaseCooldown()+" \n"
					+ "Current Cooldown : "+a.getCurrentCooldown()+" \n"
					+ "Cast Range : "+a.getCastRange()+" \n"
					+ "Cast Area : "+a.getCastArea()+" \n"
					+ "Required Action Points : "+a.getRequiredActionPoints()+" \n";
			
			if(a instanceof DamagingAbility)
				s+= "Damaging Amount : "+((DamagingAbility)a).getDamageAmount()+" \n";
			else if(a instanceof HealingAbility)
				s+= "Healing Amount : "+((HealingAbility)a).getHealAmount()+" \n";
			else
				s+=  "Crowd Control Effect : "+((CrowdControlAbility)a).getEffect().getName()+" \n";
			s+="-----------------------------"+"\n";
		}
		s+="Applied Effects : "+"\n";
		
		for(Effect e : c.getAppliedEffects()) {
			s+="Effect Name : "+e.getName()+"                   \n"
					+"Duration Left : "+e.getDuration()+"                \n";
		}
		
		
		s+= "------------------------- \n"+"Turn Order : \n";
		
		
		int counter = 1 ;
		PriorityQueue curr = model.getTurnOrder();
		PriorityQueue temp = new PriorityQueue(curr.size());
		
		while(!curr.isEmpty()) {
			Champion tempc = (Champion) curr.remove();
			s+= ""+(counter++)+" - "+tempc.getName()+"\n" ;
			temp.insert(tempc);
		}
		
		while(!temp.isEmpty()) {
			curr.insert(temp.remove());
		}
		
		return s;
		
		
	}
	
	
	public void onplayerchosen() {
		Champion c = model.getAvailableChampions().get(currentindex);
		numofchampschosen++ ;
		if(numofchampschosen ==1) {
			model.getFirstPlayer().setLeader(c);
			model.getFirstPlayer().getTeam().add(c);
			view.getChoosecharacters().title.setText("Choose Your 2nd Champion "+model.getFirstPlayer().getName());
		}
		if(numofchampschosen ==2) {
			model.getFirstPlayer().getTeam().add(c);
			view.getChoosecharacters().title.setText("Choose Your 3rd Champion "+model.getFirstPlayer().getName());
		}
		if(numofchampschosen == 3) {
			model.getFirstPlayer().getTeam().add(c);
			view.getChoosecharacters().title.setText("Choose Your Leader "+model.getSecondPlayer().getName());}
		if(numofchampschosen ==4) {
			model.getSecondPlayer().setLeader(c);
			model.getSecondPlayer().getTeam().add(c);
			view.getChoosecharacters().title.setText("Choose Your 2nd Champion "+model.getSecondPlayer().getName());}
		if(numofchampschosen ==5 ) {
			model.getSecondPlayer().getTeam().add(c);
			view.getChoosecharacters().title.setText("Choose Your 3rd Champion "+model.getSecondPlayer().getName());}
			
		if(numofchampschosen == 6 ) {
			
			model.getSecondPlayer().getTeam().add(c);
			model.placeChampions();
			model.prepareChampionTurns();
			startrealgame();
			
			
			
			}
		lastpressed.setEnabled(false);
		
		
	}
	
	
	public void startrealgame() {
		
		
		
		AudioInputStream audiostream = null ;
		File file = new File("playerschosen.wav");
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
		
		
		ImageIcon desertimg = new ImageIcon(".//resources//desert.jpg");
		ImageIcon coverimg = new ImageIcon(".//resources//cover.png");
		boardpanel boardpanel = new boardpanel(view.getChoosecharacters().getFrame(), view);
		view.setBoardpanel(boardpanel);
		boardpanel.setListener(this);
		Champion currentchampion = model.getCurrentChampion();
		for(int i = 4 ; i >= 0 ; i --) {
			for(int j = 0 ; j <= 4 ; j++) {
				
				JButton btn = new JButton();
				btn.setContentAreaFilled(false);
				
				Damageable x = 	(Damageable) model.board[i][j];  
				if(x instanceof Champion) {
					Champion c = (Champion)x;
					int cindex = model.getAvailableChampions().indexOf(c);
					btn.setIcon(minichampicons.get(cindex));
					if(model.getFirstPlayer().getTeam().contains(c)) {
						if(c == currentchampion)
							btn.setBorder(BorderFactory.createBevelBorder(0, Color.red, Color.white));
						else
							btn.setBorder(BorderFactory.createLineBorder(Color.red));
					}
					else {
						if(c == currentchampion)
							btn.setBorder(BorderFactory.createBevelBorder(0, Color.blue, Color.white));
						else
							btn.setBorder(BorderFactory.createLineBorder(Color.blue));
					}
					
					
					
				}
				else if (x instanceof Cover)
					btn.setIcon(coverimg);
				else
					btn.setIcon(desertimg);
				
				
				
				
				
				
		//		btn.setText(getobjname(i, j));
				btn.setFocusable(false);
				btn.setFont(new Font("MV Boli",Font.BOLD,20));
				btn.setBackground(new Color(26, 50, 62));
		//		btn.setForeground(new Color(247, 210, 129));
				btn.addActionListener(this);
				
				view.getBoardpanel().addchampbtns(btn);
				champarr[i][j] = btn ;
				view.getChoosecharacters().getFrame().revalidate();
				view.getChoosecharacters().getFrame().repaint();
			}
		}
		
		
		view.getChoosecharacters().getFrame().remove(view.getChoosecharacters());
		view.getChoosecharacters().getFrame().add(boardpanel);
		view.getChoosecharacters().getFrame().revalidate();
		view.getChoosecharacters().getFrame().repaint();
		updatecurrentchampion();
	}

		public String getobjname(int i , int j) {
		Damageable x = 	(Damageable) model.board[i][j];  
		if(x instanceof Champion)
			return ((Champion) x).getName();
		else
			if(x instanceof Cover)
				return "Cover";
		return "NULL";
			
			
		}

		@Override
		public void onmove(JButton btn) {
			lastpressed = btn ;
			JOptionPane.showMessageDialog(null, "Please Choose the Direction you want to move in", null, JOptionPane.INFORMATION_MESSAGE);
			
		}

		@Override
		public void onattack(JButton btn) {
			lastpressed = btn ;
			JOptionPane.showMessageDialog(null, "Please Choose the Direction you want to Attack", null, JOptionPane.INFORMATION_MESSAGE);
			
		}

		@Override
		public void direction(JButton btn) throws NotEnoughResourcesException, UnallowedMovementException, ChampionDisarmedException, InvalidTargetException, AbilityUseException, CloneNotSupportedException {
			
			model.clipmove.stop();
			model.clipmove.flush();
			model.clipmove.setFramePosition(0);
			model.clipheal.stop();
			model.clipheal.flush();
			model.clipheal.setFramePosition(0);
			model.clipattack.stop();
			model.clipattack.flush();
			model.clipattack.setFramePosition(0);
			model.coverattack.stop();
			model.coverattack.flush();
			model.coverattack.setFramePosition(0);
			
			
			Direction d ;
			if(btn == view.getBoardpanel().rightBtn)
				d = Direction.RIGHT;
			else if(btn == view.getBoardpanel().upBtn)
				d = Direction.UP;
			else if(btn == view.getBoardpanel().downBtn)
				d = Direction.DOWN;
			else 
			   d = Direction.LEFT;
			if(lastpressed == view.getBoardpanel().moveBtn)
				model.move(d);
			else if(lastpressed == view.getBoardpanel().attackBtn)
				model.attack(d);
			else if(lastpressed == view.getBoardpanel().ability1Btn)
				{model.castAbility(model.getCurrentChampion().getAbilities().get(0), d); lastpressed = null ;}
			else if(lastpressed == view.getBoardpanel().ability2Btn)
				{model.castAbility(model.getCurrentChampion().getAbilities().get(1), d); lastpressed = null ;}
			else if(lastpressed == view.getBoardpanel().ability3Btn)
				{model.castAbility(model.getCurrentChampion().getAbilities().get(2), d); lastpressed = null ;}
			
				
				updatecurrentchampion();
				updateboard();
				
				if(model.checkGameOver()!=null)
					win();
		}
		
		
		public void updatecurrentchampion() {
			Champion c = model.getCurrentChampion();
			String abilityinf = getabilityinf(c);
			if(model.getFirstPlayer().getLeader().equals(c) ||model.getSecondPlayer().getLeader().equals(c) )
				view.getBoardpanel().Leader.setText("Leader");
			else
				view.getBoardpanel().Leader.setText("Not Leader");
			
			view.getBoardpanel().abilityinfo.setText(abilityinf);
			
			String ns = "Player : ";
			ns += (model.getFirstPlayer().getTeam().contains(c)) ?  model.getFirstPlayer().getName() :  model.getSecondPlayer().getName();
			view.getBoardpanel().name.setText(ns);
			view.getBoardpanel().hp.setText(""+c.getCurrentHP());
			view.getBoardpanel().mana.setText(""+c.getMana());
			view.getBoardpanel().actionpoints.setText("Current Action Points : "+c.getCurrentActionPoints());
			view.getBoardpanel().damage.setText(""+c.getAttackDamage());
			view.getBoardpanel().range.setText(""+c.getAttackRange());
			
			String s ;
			if(c instanceof Hero)
				s = "Hero";
			else if( c instanceof AntiHero)
				s = "Anti-Hero" ;
				else
					s = "Villain" ;
			view.getBoardpanel().type.setText("Type : " +s);
			
			view.getBoardpanel().ability1Btn.setText(c.getAbilities().get(0).getName());
			view.getBoardpanel().ability2Btn.setText(c.getAbilities().get(1).getName());
			view.getBoardpanel().ability3Btn.setText(c.getAbilities().get(2).getName());
			
			
			
		}
		

		public void updateboard() {
		
			view.getBoardpanel().main.removeAll();
			view.getBoardpanel().frame.revalidate();
			view.getBoardpanel().frame.repaint();
			Champion ch = model.getCurrentChampion();
			view.getBoardpanel().ability1Btn.setText(ch.getAbilities().get(0).getName());
				view.getBoardpanel().ability2Btn.setText(ch.getAbilities().get(1).getName());
				view.getBoardpanel().ability3Btn.setText(ch.getAbilities().get(2).getName());
			ImageIcon desertimg = new ImageIcon(".//resources//desert.jpg");
			ImageIcon coverimg = new ImageIcon(".//resources//cover.png");
			Champion currentchampion = model.getCurrentChampion();
			for(int i = 4 ; i >= 0 ; i --) {
				for(int j = 0 ; j <= 4 ; j++) {
					
					JButton btn = new JButton();
					btn.setContentAreaFilled(false);
					
					Damageable x = 	(Damageable) model.board[i][j];  
					if(x instanceof Champion) {
						Champion c = (Champion)x;
						int cindex = model.getAvailableChampions().indexOf(c);
						btn.setIcon(minichampicons.get(cindex));
						if(model.getFirstPlayer().getTeam().contains(c)) {
							if(c == currentchampion)
								btn.setBorder(BorderFactory.createBevelBorder(0, Color.red, Color.white));
							else
								btn.setBorder(BorderFactory.createLineBorder(Color.red));
						}
						else {
							if(c == currentchampion)
								btn.setBorder(BorderFactory.createBevelBorder(0, Color.blue, Color.white));
							else
								btn.setBorder(BorderFactory.createLineBorder(Color.blue));
						}
						
					}
					else if (x instanceof Cover)
						btn.setIcon(coverimg);
					else
						btn.setIcon(desertimg);
					
					btn.setFocusable(false);
					
				//	btn.setBackground(new Color(26, 50, 62));
			//		btn.setForeground(new Color(247, 210, 129));
					btn.addActionListener(this);		
					view.getBoardpanel().addchampbtns(btn);
					champarr[i][j] = btn ;
					view.getChoosecharacters().getFrame().revalidate();
					view.getChoosecharacters().getFrame().repaint();
				}
			}
		}

		@Override
		public void onendturn() {
			model.endTurn();
			updatecurrentchampion();
			updateboard();
			
			
		}

		@Override
		public void onleaderability() throws LeaderNotCurrentException, LeaderAbilityAlreadyUsedException {
			model.useLeaderAbility();
			if(model.isFirstLeaderAbilityUsed())
				view.getBoardpanel().p1.setIcon((new ImageIcon(".//resources//used.png")));
			if(model.isSecondLeaderAbilityUsed())
				view.getBoardpanel().p2.setIcon((new ImageIcon(".//resources//used.png")));
			
		 
			updatecurrentchampion();
			updateboard();
			
			if(model.checkGameOver() != null)
				win();
		}
		
		public void win() {
			endpanel endpanel = new endpanel(view.getBoardpanel().frame,view.getBoardpanel().view,"Congratulations "+model.checkGameOver().getName()+"  !!");
			view.setEndpanel(endpanel);
			System.out.println("Congratulations " + model.checkGameOver().getName());
			view.getStartpanel().clip.close();
				view.getBoardpanel().frame.remove(view.getBoardpanel());
				view.getBoardpanel().frame.add(endpanel);
				
				
		}

		@Override
		public void onab1(JButton btn) throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException {
			Ability a = model.getCurrentChampion().getAbilities().get(0);
			abilityhelper(a,btn);
				
		}

		@Override
		public void onab2(JButton btn) throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException {
			Ability a = model.getCurrentChampion().getAbilities().get(1);
			abilityhelper(a,btn);
			
		}

		@Override
		public void onab3(JButton btn) throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException {
			Ability a = model.getCurrentChampion().getAbilities().get(2);
			abilityhelper(a,btn);
			
		}
		
		public void abilityhelper(Ability a , JButton btn) throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException {
			model.clipheal.stop();
			model.clipheal.flush();
			model.clipheal.setFramePosition(0);
			model.clipattack.stop();
			model.clipattack.flush();
			model.clipattack.setFramePosition(0);
			
			
			if(a.getCastArea().equals(AreaOfEffect.SELFTARGET) || a.getCastArea().equals(AreaOfEffect.TEAMTARGET) || a.getCastArea().equals(AreaOfEffect.SURROUND))
			{	model.castAbility(a);   }
			if(a.getCastArea().equals(AreaOfEffect.DIRECTIONAL)) {
				lastpressed = btn ;
				JOptionPane.showMessageDialog(null, "Please Choose the Direction you which to perform "+a.getName()+" in", null, JOptionPane.INFORMATION_MESSAGE);
			}
			if(a.getCastArea().equals(AreaOfEffect.SINGLETARGET)) {
				lastpressed = btn ;
				JOptionPane.showMessageDialog(null, "Please Click on the cell you which to perform "+a.getName()+" on", null, JOptionPane.INFORMATION_MESSAGE);
				
			}
			
			 
				updatecurrentchampion();
				updateboard();
				
				if(model.checkGameOver() != null)
					win();
		}
		
		

}
