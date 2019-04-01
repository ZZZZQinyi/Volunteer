

package cw3interfaces;

//**DO NOT CHANGE ANYTHING HERE

import coursework3.Volunteer;

public interface CommunityGroupInterface {
    
    public int howManyVolunteers();
    public int getSkillsTotals();
    public void addVolunteer(Volunteer vol);
    public Volunteer removeVolunteer(String id);
    public void removeAll();
    public int getGroupId();
    public int getA();
    public int getB();
    public int getC();
    public int getD();
    public int getE();
}
