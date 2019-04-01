package cw3interfaces;

import coursework3.CommunityGroup;
import coursework3.Volunteer;
import java.util.ArrayList;

//**DO NOT CHANGE ANYTHING HERE
public interface SkillSorterInterface {

    public boolean addVolunteer(Volunteer vol);

    public boolean moveVolunteer(String skillSet, String from, String to);

    public boolean deleteVolunteer(String id, String groupid);

    public boolean deleteAllVolunteers();

    public void doFile(boolean iswrite);

    public CommunityGroup[] getCommunityGroups();

}
