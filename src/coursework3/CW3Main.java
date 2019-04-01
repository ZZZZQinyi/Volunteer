package coursework3;

import java.util.*;

/*READ ME: Use this class as your main class, and create your menu here
 Your menu should then call the appropriate methods in the SkillSorter class
 You need to complete the other classes, including the empty methods.
 /*
 */
public class CW3Main {

    ArrayList<CommunityGroup> myGroups = new ArrayList<>();

    public static void main(String[] args) {
        CW3Main cw = new CW3Main();
    }

    //Construct and run your menu here.
    //You MUST call methods in SkillSorter from your menu
    //and complete the methods in SkillSorter 
    //DO NOT write the methods, eg addVolunteer, in THIS class.
    //Call and use the ones in SkillSorter.   
    public CW3Main() {
        runMenu();
    }

    private void runMenu() {
        SkillSorter mySkillSorter = new SkillSorter();
        System.out.println("Class List System: ");
        System.out.println("1 = Add volunteer ; 2 = Move volunteer ; 3 = Delete volunteer; "
                + "4 = Delete all volunteer; 5 = Display groups.; 6 = Exit program.");
        Scanner s = new Scanner(System.in);
        String in;
        int choice;
        boolean exit = false;
        boolean res;
        // read obejct from file
        mySkillSorter.doFile(false);
        while (!exit) {
            System.out.println("Please choose an option:");
            in = s.nextLine();
            choice = Integer.parseInt(in);
            switch (choice) {
                case 1:
                    String name = getString("Please input volunteer name");
                    String skill1 = getString("Please input volunteer skills");
                    Volunteer vol = new Volunteer(skill1, name);
                    res = mySkillSorter.addVolunteer(vol);
                    if (!res) {
                        System.out.println("You input the wrong format.");
                    }
                    break;
                case 2:
                    String id = getString("Please input volunteer ID");
                    String fromgroupid = getString("Please input fromgroup id range (1~5)");
                    String togroupid = getString("Please input togroup id range (1~5)");
                    res = mySkillSorter.moveVolunteer(id, fromgroupid, togroupid);
                    if (!res) {
                        System.out.println("You input the wrong format.");
                    } else {
                        System.out.println("move success!");
                    }
                    break;
                case 3:
                    String idd = getString("Please input volunteer id");
                    String groupid = getString("Please input groupid id range (1~5)");
                    res = mySkillSorter.deleteVolunteer(idd, groupid);
                    if (!res) {
                        System.out.println("You input the wrong format.");
                    }
                    break;
                case 4:
                    res = mySkillSorter.deleteAllVolunteers();
                    if (res) {
                        System.out.println("remove all success!.");
                    }
                    break;
                case 5:
                    CommunityGroup[] resgroup = mySkillSorter.getCommunityGroups();
                    for (CommunityGroup i : resgroup) {
                        System.out.println("Group id: " + i.getGroupId());
                        System.out.println("Group members: " + i.howManyVolunteers());
                        System.out.println("Group skilltotoals: " + i.getSkillsTotals());
                        System.out.println("A: " + i.getA() + " " + " B: " + i.getB() + " C: " + i.getC() + " D: " + i.getD() + " E: " + i.getE());
                    }
                    break;
                case 6:
                    System.out.println("exit success!");
                    exit = true;
                    break;
                default:
                    System.out.println("You input the wrong number.");
                    break;

            }
        }
    }

    public String getString(String question) {
        Scanner s = new Scanner(System.in);

        System.out.println(question);
        String input = s.nextLine();

        return input;
    }

}
