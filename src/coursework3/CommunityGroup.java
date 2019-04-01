//DO NOT CHANGE THIS PACKAGE
package coursework3;

import cw3interfaces.CommunityGroupInterface;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

//DO NOT CHANGE THIS NAME
public class CommunityGroup implements CommunityGroupInterface, Serializable {

    //COMPLETE THIS CLASS    
    private int groupid;
    private int members;
    private int A;
    private int B;
    private int C;
    private int D;
    private int E;
    ArrayList<Volunteer> myVols = new ArrayList<>();

    public CommunityGroup(int id) {
        this.groupid = id;
        this.members = 0;
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.E = 0;
    }

    //these public methods need to form the interface 
// DO NOT CHANGE ANY OF THESE METHOD NAMES, RETURN VALUES, OR ARGUMENTS   
    @Override
    public int howManyVolunteers() {
        //return the total number of volunteers in this community group
        //COMPLETE CODE HERE

        return members;
    }

    @Override
    public int getSkillsTotals() {
        return A + B + C + D + E;
    }

    @Override
    public int getGroupId() {
        return this.groupid;
    }

    @Override
    public void removeAll() {
        ListIterator<Volunteer> lit = myVols.listIterator();
        Volunteer vol;
        while (lit.hasNext()) {
            vol = lit.next();
            lit.remove();
            this.members -= 1;
            char[] tempvol = vol.getSkillSet().toCharArray();
            for (int i = 0; i < 3; i++) {
                if (tempvol[i] == 'A') {
                    this.A--;
                } else if (tempvol[i] == 'B') {
                    this.B--;
                } else if (tempvol[i] == 'C') {
                    this.C--;
                } else if (tempvol[i] == 'D') {
                    this.D--;
                } else if (tempvol[i] == 'E') {
                    this.E--;
                }
            }
        }
        System.out.println("remove all vol from Group " + this.groupid + " success!");
    }

    @Override
    public void addVolunteer(Volunteer vol) {
        char[] tempvol = vol.getSkillSet().toCharArray();
        for (int i = 0; i < 3; i++) {
            if (tempvol[i] == 'A') {
                this.A++;
            } else if (tempvol[i] == 'B') {
                this.B++;
            } else if (tempvol[i] == 'C') {
                this.C++;
            } else if (tempvol[i] == 'D') {
                this.D++;
            } else if (tempvol[i] == 'E') {
                this.E++;
            }
        }
        this.members++;
        this.myVols.add(vol);
        System.out.println("add success!");
        System.out.print("vol info:" + " ");
        System.out.println(vol.getId() + " " + vol.getName() + " " + vol.getSkillSet());
    }

    @Override
    public Volunteer removeVolunteer(String id) {
        ListIterator<Volunteer> lit = myVols.listIterator();
        Volunteer vol;
        while (lit.hasNext()) {
            vol = lit.next();
            if (vol.getId().equals(id)) {
                lit.remove();
                this.members -= 1;
                char[] tempvol = vol.getSkillSet().toCharArray();
                for (int i = 0; i < 3; i++) {
                    if (tempvol[i] == 'A') {
                        this.A--;
                    } else if (tempvol[i] == 'B') {
                        this.B--;
                    } else if (tempvol[i] == 'C') {
                        this.C--;
                    } else if (tempvol[i] == 'D') {
                        this.D--;
                    } else if (tempvol[i] == 'E') {
                        this.E--;
                    }
                }
                System.out.println("remove success!");
                System.out.println(id + " form Group " + this.groupid + " was removed");
                return vol;
            }
        }
        System.out.println("No such volunteer to remove: " + id);
        return null;
    }

    @Override
    public int getA() {
        return this.A;
    }

    @Override
    public int getB() {
        return this.B;
    }

    @Override
    public int getC() {
        return this.C;
    }

    @Override
    public int getD() {
        return this.D;
    }

    @Override
    public int getE() {
        return this.E;
    }

}
