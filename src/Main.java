import java.util.Random;

public class Main {

    public static int[] heroesHealth = {250, 260, 270, 260};
    public static int[] addHealth = {10, 10, 10, 0};
    public static int[] heroesDamage = {20, 15, 10, 0};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic", "Doctor"};
    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";

    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            round();
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
    }


    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0
                && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        bossHit();
        setAddHealth();
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("_______________");
        System.out.println("Boss health: " + bossHealth + " " + bossDefenceType);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Magic health: " + heroesHealth[2]);
        System.out.println("Doctor health: " + heroesHealth[3]);
        System.out.println("_______________");
    }

    public static void setAddHealth () {
        Random l = new Random();
        int zen = l.nextInt(addHealth.length);
        for (int i = 0; i < addHealth.length; i++) {
           heroesHealth[i]=heroesHealth[i]+zen;
            System.out.println(addHealth);
        }
    }
    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType.equals(heroesAttackType[i])) {
                    Random r = new Random();
                    int coef = r.nextInt(8) + 2; // 2,3,4,5,6,7,8,9
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    }
                    else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                    System.out.println(heroesAttackType[i] +
                            " critically hit " + heroesDamage[i] * coef);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                    }
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
}


