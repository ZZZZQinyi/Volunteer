//DO NOT CHANGE THIS PACKAGE
package coursework3;

import cw3interfaces.VolunteerInterface;
import java.io.Serializable;
import java.util.UUID;

//DO NOT CHANGE THIS NAME
public class Volunteer implements VolunteerInterface, Serializable {
//COMPLETE THIS CLASS 

    private String SkillSet;
    private String Name;
    private String id;

//these public methods need to form the interface 
    public Volunteer(String skill, String name) {
        SkillSet = skill;
        Name = name;
        id = UUID.randomUUID().toString().replace("-", "");
    }
// DO NOT CHANGE ANY OF THESE METHOD NAMES, RETURN VALUES, OR ARGUMENTS   

    @Override
    public String getSkillSet() {
        //COMPLETE CODE HERE
        //returns a String of this volunteers skills, eg BBB, ABC, CDD etc
        return SkillSet;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String getId() {
        return id;
    }

}
