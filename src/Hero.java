import java.util.Random;
public class Hero {
    // Private variables
    private String heroName;
    private int hitPoints;
    private int lightAttackDamage;
    private int heavyAttackDamage;
    private int thisTimeAttack;

    //Default constructor
    public Hero(String s, int i, int parseInt, int anInt, int i1) {
        this.heroName = "That's an error, wrong contructor";
        this.hitPoints = 1000;
        this.lightAttackDamage = 100;
        this.heavyAttackDamage = 200;
    }

    // Constructor with parameter
    public Hero (String heroName, int hitPoints, int lightAttackDamage, int heavyAttackDamage ) {
        this.heroName = heroName;
        this.hitPoints = hitPoints;
        this.lightAttackDamage = lightAttackDamage;
        this.heavyAttackDamage = heavyAttackDamage;
    }

    //Mutators
    public void setHeroName (String heroName) {
        this.heroName = heroName;
    }
    public void sethitPoints (int hitPoints) {
        this.hitPoints = hitPoints;
    }
    public void setlightAttackDamage (int lightAttackDamage) {
        this.lightAttackDamage = lightAttackDamage;
    }
    public void setheavyAttackDamage (int heavyAttackDamage) {
        this.heavyAttackDamage = heavyAttackDamage;
    }
    public void setThisTimeAttack (int thisTimeAttack) {
        this.thisTimeAttack = thisTimeAttack;
    }

    //Accessor
    public String getHeroName () {
        return heroName;
    }
    public int gethitPoints () {
        return hitPoints;
    }
    public int getlightAttackDamage () {
        return lightAttackDamage;
    }
    public int getheavyAttackDamage() {
        return heavyAttackDamage;
    }
    public int getThisTimeAttack() {
        return thisTimeAttack;
    }

    //isAlive method
    public boolean isLive () {
        if (hitPoints > 0) {
            return true;
        } else { return false; }
    }

    //Attack method
    public int attack () {
        Random ran = new Random();
        int prob = ran.nextInt(4) + 1;
        if (prob == 1 || prob == 2 || prob == 3) {
            return lightAttackDamage;
        } else { return heavyAttackDamage; }
    }

    public void displayAttack (int attackAmount) {
        if (attackAmount == lightAttackDamage) {
            System.out.println(heroName + " performs a light Attack with Damage: " + lightAttackDamage);
            thisTimeAttack = lightAttackDamage;
        } if (attackAmount == heavyAttackDamage) {
            System.out.println(heroName + " performs a heavy Attack with Damage: " + heavyAttackDamage);
            thisTimeAttack = heavyAttackDamage;
        }
    }

    public void takeDamage (int damageTaken) {
        hitPoints = hitPoints - damageTaken;
    }

    public void displayHealth () {
        if (hitPoints > 0) {
            System.out.println(heroName + " has " + hitPoints + " HP left");
        } if (hitPoints <= 0 ) {
            System.out.println(heroName + " has " + "fainted");
        }
    }





}
