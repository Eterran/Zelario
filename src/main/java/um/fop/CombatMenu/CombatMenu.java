package CombatMenu;

import Entitypack.*;

import java.io.IOException;

public class CombatMenu {

	public static void displayCombatMenu(Entity player, Entity monster) throws IOException {
		
		ASCII a = new ASCII();
		a.YouEnemy();

		System.out.print("+");

		int width = 123;
		for (int i = 0; i < width; i++)
			System.out.print("-");
		System.out.println("+");
		System.out.println();

		System.out.print(" \033[0;33m Level : " + player.getLevel());

		System.out.println();

		HealthBar2 b = new HealthBar2(); // HPbar for hero
		b.HeroHealthBar(player);

		for (int i = 0; i < 11; i++)
			System.out.print(" ");

		System.out.print("|");
		for (int i = 0; i < 11; i++)
			System.out.print(" ");

		b.MonsterHealthBar(monster);

		System.out.println();// HPbar for monster

		ManaBar c = new ManaBar();
		c.HeroManaBar(player);
		for (int i = 0; i < 11; i++)
			System.out.print(" ");

		System.out.print("|");
		for (int i = 0; i < 11; i++)
			System.out.print(" ");

		c.MonsterManaBar(monster);
		System.out.println();
		System.out.println();
		System.out.print("+ ");
		for (int i = 0; i < width / 2; i++)
			System.out.print("- ");
		System.out.println("+");

		System.out.println();
		System.out.println(" 1. Normal Attack");
		System.out.println(" 2. Defend");
		System.out.println(" 3. Escape");
		System.out.println(" 4. Heal");

		if (player.isSkill1Unlocked == true) {
			System.out.println("\033[0m \033[0;32m5. " + player.getSkillOneName() + " \u001B[3m\t\t"
					+ player.getSkill1Description());
			System.out.print("   \t\t\tRequires " + player.getSkill1Mp() + " mana");
			System.out.println("\t\tCooldown: " + player.getCDSkill1() + "/" + player.getMaxCDSkill1() + "\033[0m");
			System.out.println();
		}

		else {
			System.out.println("\033[0m \033[0;90m5. " + player.getSkillOneName() + " \u001B[3m\t*Locked*");
			System.out.println();
		}

		if (player.isSkill2Unlocked == true) {
			System.out.println("\033[0m \033[0;35m6. " + player.getSkillTwoName() + " \u001B[3m\t\t"
					+ player.getSkill2Description());
			System.out.print("   \t\t\tRequires " + player.getSkill2Mp() + " mana");
			System.out.println("\t\tCooldown: " + player.getCDSkill2() + "/" + player.getMaxCDSkill2() + "\033[0m");
			System.out.println();

		}

		else {
			System.out.println("\033[0m \033[0;90m6. " + player.getSkillTwoName() + " \u001B[3m\t*Locked*");
			System.out.println();
		}

		if (player.isSkill3Unlocked == true) {
			System.out.println("\033[0m \033[0;34m7. " + player.getSkillThreeName() + " \u001B[3m\t\t"
					+ player.getSkill3Description());
			System.out.print("   \t\t\tRequires " + player.getSkill3Mp() + " mana");
			System.out.println("\t\tCooldown: " + player.getCDSkill3() + "/" + player.getMaxCDSkill3() + "\033[0m");
			System.out.println();
		} else {
			System.out.println("\033[0m \033[0;90m7. " + player.getSkillThreeName() + " \u001B[3m\t*Locked* \033[0m");
			System.out.println();
		}

		System.out.println();
		System.out.print("+");
		width = 123;
		for (int i = 0; i < width; i++)
			System.out.print("-");
		System.out.println("+");
		System.out.println();
		System.out.print(">");

	}

}
