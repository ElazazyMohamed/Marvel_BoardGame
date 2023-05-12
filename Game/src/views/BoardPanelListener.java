package views;

import javax.swing.JButton;

import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;

public interface BoardPanelListener {

	public void onmove(JButton btn);
	public void onattack(JButton btn);
	public void direction(JButton btn) throws NotEnoughResourcesException, UnallowedMovementException, ChampionDisarmedException, InvalidTargetException, AbilityUseException, CloneNotSupportedException;
	public void onendturn();
	public void onleaderability() throws LeaderNotCurrentException, LeaderAbilityAlreadyUsedException;
	public void onab1(JButton btn) throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException;
	public void onab2(JButton btn) throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException;
	public void onab3(JButton btn) throws NotEnoughResourcesException, AbilityUseException, CloneNotSupportedException;
	
	
}
