package model.world;

import java.util.ArrayList;

public class Villain extends Champion {

	public Villain(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);

	}

	
public String toString () {
		
		String x = "";
		
		x+= "       "+getName()+"\n" 
		+ "Champion Type : Villain"+"\n"			
		+ "HP : "+getMaxHP()+"\n"
		+"Mana : "+getMana()+"\n"
		+"Action Points per Turn : "+getMaxActionPointsPerTurn()+"\n"
		+"Speed : "+getSpeed()+"\n"
		+"Attack Range : "+getAttackRange()+"\n"
		+"Attack Damage : "+getAttackDamage()+"\n"
		+"------------------ \n"
		+" Available Abilitites : \n";
		for(int i = 1 ; i <= getAbilities().size() ; i++) {
			x+= i+" - "+ getAbilities().get(i-1).getName()+"\n";
		}
		
		
		
		return x ;
	}
	
	
	
	@Override
	public void useLeaderAbility(ArrayList<Champion> targets) {
		for (Champion c : targets) {

			c.setCurrentHP(0);

		}

	}

}
