import java.util.Random;
import java.util.Scanner;

public class GoblinTower
{
    private static Character hero = new Character(30, 20, 8, 12, 2);
    private static Scanner in = new Scanner(System.in);

    public static void main (String[] args)
    {
        int goblinsKilled = 0;
        while(hero.currentHP() > 0)
        {
            System.out.println("You were attacked by a goblin!");
            conductCombat();
            if(hero.currentHP() > 0)
            {
                goblinsKilled++;
                System.out.println("You have now defeated " + goblinsKilled + " goblins");

                if(goblinsKilled % 3 == 0)
                {
                    int currentGold = hero.goldInPurse();
                    int potionsToBuy = currentGold / 10;
                    for(int i = 0; i < potionsToBuy; i ++)
                    {
                        hero.buyPotion();
                    }
                    System.out.println("You came across a vendor selling health potions, and decided to buy some.\n" +
                            "You bought " + potionsToBuy + " potions");
                }
            }
        }

        System.out.println("Game over. You killed " + goblinsKilled + " goblins before you died.");
    }

    private static void conductCombat()
    {
        Character goblin = new Character(20, 20, 6, 8, 0);

        boolean goblinAlive = true;
        boolean heroAlive = true;

        while(goblinAlive && heroAlive)
        {
            //hero attacks first
            int heroAttackRoll = hero.attackRoll();
            int heroDamageRoll;
            if(heroAttackRoll == 20)
            {
                heroDamageRoll = hero.damageRoll(true);
                goblin.receiveAttack(heroDamageRoll);
                System.out.println("Critical hit! You did " + heroDamageRoll + " damage!");
            }
            else
            {
                if(goblin.attackHit(heroAttackRoll))
                {
                    heroDamageRoll = hero.damageRoll(false);
                    goblin.receiveAttack(heroDamageRoll);
                    System.out.println("You landed a hit and did " + heroDamageRoll + " damage!");
                }
                else
                {
                    System.out.println("Your attack missed!");
                }
            }
            goblinAlive = goblin.currentHP() > 0;

            if(goblinAlive)
            {
                int goblinAttackRoll = goblin.attackRoll();
                int goblinDamageRoll;
                if(goblinAttackRoll == 20)
                {
                    goblinDamageRoll = goblin.damageRoll(true);
                    hero.receiveAttack(goblinDamageRoll);
                    System.out.println("Critical hit! The goblin did " + goblinDamageRoll + " damage!");
                }
                else
                {
                    if(hero.attackHit(goblinAttackRoll))
                    {
                        goblinDamageRoll = goblin.damageRoll(false);
                        hero.receiveAttack(goblinDamageRoll);
                        System.out.println("The goblin landed a hit and did " + goblinDamageRoll + " damage!");
                    }
                    else
                    {
                        System.out.println("The goblin's attack missed!");
                    }
                }

                heroAlive = hero.currentHP() > 0;
            }
            else
            {
                int goldFound = hero.lootBody();
                System.out.println("You have defeated the goblin, and found " + goldFound + " gold!");
            }

            if(heroAlive)
            {
                System.out.println("You have " + hero.currentHP() + " hp left, and the goblin has " + goblin.currentHP() + " hp left" +
                        "\nWould you like to drink a potion? Submit y if yes");
                String input = in.nextLine();
                if(input.equals("y") && hero.numPotionsLeft() > 0)
                {
                    System.out.println("You drank a potion, and recovered five hp");
                    hero.drinkPotion();
                }
                else if(input.equals("y") && hero.numPotionsLeft() <= 0)
                {
                    System.out.println("You tried to drink a potion, but discovered you were out!");
                }
                else
                {
                    System.out.println("You chose not to drink a potion");
                }
            }
            else
            {
                System.out.println("You have been slain by the goblin!");
            }
        }
    }
}
