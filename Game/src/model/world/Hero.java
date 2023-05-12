package model.world;

import java.util.ArrayList;

import model.effects.Effect;
import model.effects.EffectType;
import model.effects.Embrace;

public class Hero extends Champion {

	public Hero(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);

	}

	
	
	
public String toString () {
		
		String x = "";
		
		x+= "       "+getName()+"\n" 
		+ "Champion Type : Hero"+"\n"			
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
			int i = 0;
			while (i < c.getAppliedEffects().size()) {
				Effect e = c.getAppliedEffects().get(i);
				if (e.getType() == EffectType.DEBUFF) {
					e.remove(c);
					c.getAppliedEffects().remove(e);

				} else
					i++;
			}
				Embrace em = new Embrace(2);
				
				em.apply(c);
				c.getAppliedEffects().add(em);
				
			}
		}

	}

