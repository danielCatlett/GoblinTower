import java.util.Random;

public class Character
{
    private int hp;
    private final int maxHP;
    private final int attack;
    private final int damage;
    private final int armorClass;
    private int gold;
    private int numPotions;

    private Random rngesus = new Random();

    public Character(int hpStat, int attackStat, int damageStat, int armorClassStat, int numPotionsStat)
    {
        hp = hpStat;
        maxHP = hpStat;
        attack = attackStat;
        damage = damageStat;
        armorClass = armorClassStat;
        gold = 0;
        numPotions = numPotionsStat;
    }

    public boolean attackHit(int attackRoll)
    {
        return attackRoll > armorClass;
    }

    public void receiveAttack(int damageRoll)
    {
        hp -= damageRoll;
    }

    public int lootBody()
    {
        int goldOnBody = rngesus.nextInt(3) + 1;
        gold += goldOnBody;
        return goldOnBody;
    }

    public int goldInPurse()
    {
        return gold;
    }

    public int numPotionsLeft()
    {
        return numPotions;
    }

    public void buyPotion()
    {
        if(gold > 9)
        {
            gold -= 10;
            numPotions++;
        }
    }

    public void drinkPotion()
    {
        if(numPotions < 1)
        {
            return;
        }

        hp += 5;
        if(hp > maxHP)
        {
            hp = maxHP;
        }
        numPotions--;
    }

    public int currentHP()
    {
        return hp;
    }

    public int attackRoll()
    {
        return rngesus.nextInt(attack) + 1;
    }

    public int damageRoll(boolean crit)
    {
        if(crit)
        {
            return damage;
        }

        return rngesus.nextInt(damage) + 1;
    }
}
