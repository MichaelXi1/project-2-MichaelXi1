import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab2 {
    public static void fightComplexVersion (ArrayList<Hero> team1, ArrayList<Hero> team2, int round) {
        System.out.println(team1.get(round).getHeroName() + " VS. " + team2.get(round).getHeroName());
        System.out.println("============================================");
        while (team1.get(round).isLive() && team2.get(round).isLive()) {
            //Display health
            team1.get(round).displayHealth();
            team2.get(round).displayHealth();
            System.out.println("============================================");
            //Perform attack
            team1.get(round).displayAttack(team1.get(round).attack()); team2.get(round).takeDamage(team1.get(round).getThisTimeAttack());
            if (team2.get(round).gethitPoints()<=0) {
                System.out.println("============================================"); continue;}
            team2.get(round).displayAttack(team2.get(round).attack()); team1.get(round).takeDamage(team2.get(round).getThisTimeAttack());
            System.out.println("============================================");
        }
        if (team1.get(round).gethitPoints() <= 0) {
            team1.get(round).displayHealth();
            team2.get(round).displayHealth();
            System.out.println("============================================");
            System.out.println(team2.get(round).getHeroName() + " VANQUISHES " + team1.get(round).getHeroName() + " with " + team2.get(round).gethitPoints() + " HP to spare");
        } else {
            team2.get(round).displayHealth();
            team1.get(round).displayHealth();
            System.out.println("============================================");
            System.out.println(team1.get(round).getHeroName() + " VANQUISHES " + team2.get(round).getHeroName() + " with " + team1.get(round).gethitPoints() + " HP to spare");
        }
    }

    public static void fightSimpleVersion (ArrayList<Hero> team1, ArrayList<Hero> team2, int round) {
        System.out.println(team1.get(round).getHeroName()+" " + " VS. " + team2.get(round).getHeroName());
        while (team1.get(round).gethitPoints()>0 && team2.get(round).gethitPoints()>0) {
            team2.get(round).takeDamage(team1.get(round).attack());
            if (team2.get(round).gethitPoints()<0) {break;}
            team1.get(round).takeDamage(team2.get(round).attack());
        }
        if (team1.get(round).isLive()) {
            System.out.println(team1.get(round).getHeroName() + " VANQUISHES " + team2.get(round).getHeroName() + " with " + team1.get(round).gethitPoints() + " HP to spare");
        } if (team2.get(round).isLive()) {
            System.out.println(team2.get(round).getHeroName() + " VANQUISHES " + team1.get(round).getHeroName() + " with " + team2.get(round).gethitPoints() + " HP to spare");
        }
    }

    public static void fightBetweenTeamsPrint (ArrayList<Hero> team1, ArrayList<Hero> team2, boolean display, int round) {
        if (!display) { fightSimpleVersion(team1, team2, round); }
        if (display) { fightComplexVersion(team1, team2, round); }
    }

    public static void doBattle (ArrayList<Hero> team1, ArrayList<Hero> team2, boolean flag) {
        //System.out.println(flag[0]);
        for (int i=0; i< team1.size(); i++) {
            fightBetweenTeamsPrint(team1, team2, flag, i);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //read flag boolean and check
        try {
            if (!args[0].equals("false") && !(args[0].equals("true"))) {
                throw new Exception ("the print flag is incorrectly set");}
        } catch (Exception excpt) {
            System.out.println(excpt.getMessage());
            System.exit(1);
        }
        boolean display = Boolean.parseBoolean(args[0]);

        //log in the file content
        Scanner scan = new Scanner(System.in);
        boolean getCorrectInput = false;
        FileInputStream heroFile = null;
        String fileName = null;
        while (!getCorrectInput) {
            try {
                System.out.println("Enter file name:");
                fileName = scan.nextLine();
                heroFile = new FileInputStream("src/" + fileName);
                getCorrectInput = true;
            } catch (FileNotFoundException e) {
                System.out.println("Could not open input file. Enter another file name:");
                continue;
            }
        }
        //Read the file and create two teams
        Scanner fileReader = new Scanner(heroFile);
        ArrayList <Hero> team1 = new ArrayList<Hero>();
        ArrayList <Hero> team2 = new ArrayList<Hero>();
        int count = 0;
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine(); //reading lines in file
            String[] eachHeroInfo = data.split(",");
            count++;
            //Put eachHeroInfo into Arraylist
            try {
                if (Integer.parseInt(eachHeroInfo[4]) == 1) {
                    team1.add(new Hero((eachHeroInfo[0]), Integer.parseInt(eachHeroInfo[1]), Integer.parseInt(eachHeroInfo[2]), Integer.parseInt(eachHeroInfo[3])));}
                if (Integer.parseInt(eachHeroInfo[4]) == 2) {
                    team2.add(new Hero((eachHeroInfo[0]), Integer.parseInt(eachHeroInfo[1]), Integer.parseInt(eachHeroInfo[2]), Integer.parseInt(eachHeroInfo[3])));}
            } catch (NumberFormatException e) {continue;}
        }
        fileReader.close();

        doBattle(team1, team2, display);
    }
}
