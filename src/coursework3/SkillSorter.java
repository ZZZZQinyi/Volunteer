//DO NOT CHANGE THIS PACKAGE
package coursework3;

import cw3interfaces.SkillSorterInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Arrays;

//DO NOT CHANGE THIS NAME
public class SkillSorter implements SkillSorterInterface {

    //object of CommunityGroup
    private CommunityGroup[] myGroups;

    public SkillSorter() {
        this.myGroups = new CommunityGroup[5];
        this.myGroups[0] = new CommunityGroup(1);
        this.myGroups[1] = new CommunityGroup(2);
        this.myGroups[2] = new CommunityGroup(3);
        this.myGroups[3] = new CommunityGroup(4);
        this.myGroups[4] = new CommunityGroup(5);
    }

    @Override
    public void doFile(boolean iswrite) {
        if (iswrite) {
            File file = new File("test.dat");
            FileOutputStream out;
            try {
                out = new FileOutputStream(file);
                ObjectOutputStream objOut = new ObjectOutputStream(out);
                objOut.writeObject(this.myGroups);
                objOut.flush();
                objOut.close();
                System.out.println("write object success!");
            } catch (IOException e) {
                System.out.println("write object failed");
                e.printStackTrace();
            }
        } else {
            Object temp = null;
            File file = new File("test.dat");
            FileInputStream in;
            try {
                in = new FileInputStream(file);
                ObjectInputStream objIn = new ObjectInputStream(in);
                temp = objIn.readObject();
                objIn.close();
                System.out.println("read object success!");
                this.myGroups = (CommunityGroup[]) temp;
            } catch (Exception e) {
                System.out.println("Not fount file.");
            }
        }
    }

    //these public methods need to form the interface 
    // DO NOT CHANGE ANY OF THESE METHOD NAMES, RETURN VALUES, OR ARGUMENTS
    @Override
    public boolean addVolunteer(Volunteer vol) {
        //add a volunteer to a Community Group USING YOUR SORTING ALGORITHM
        //COMPLETE CODE HERE
        char[] tempvol = vol.getSkillSet().toCharArray();
        if (tempvol.length != 3) {
            return false;
        }
        int[] countfig = {0, 0, 0, 0, 0};
        for (char i : tempvol) {
            switch (i) {
                case 'A':
                    countfig[0]++;
                    break;
                case 'B':
                    countfig[1]++;
                    break;
                case 'C':
                    countfig[2]++;
                    break;
                case 'D':
                    countfig[3]++;
                    break;
                case 'E':
                    countfig[4]++;
                    break;
                default:
                    System.out.println("You input the wrong number.");
                    return false;
            }
        }
        double[] variance = new double[5];
        for (int i = 0; i < 5; i++) {
            CommunityGroup group = this.myGroups[i];
            int[] addcountfig = new int[5];
            System.arraycopy(countfig, 0, addcountfig, 0, 5);
            addcountfig[0] += group.getA();
            addcountfig[1] += group.getB();
            addcountfig[2] += group.getC();
            addcountfig[3] += group.getD();
            addcountfig[4] += group.getE();
            double[] tempvar = new double[5];
            for (int j = 0; j < 5; j++) {
                tempvar[j] = addcountfig[j];
            }
            double res = this.getVariance(tempvar);
            variance[i] += res;
            tempvar[i] = group.getSkillsTotals() + 3;
            for (int j = 0; j < 5; j++) {
                if (j != i) {
                    tempvar[j] = this.myGroups[j].getSkillsTotals();
                }
            }
            res = this.getVariance(tempvar);
            variance[i] += res;
        }
        double min = variance[0];
        int minkey = 0;
        for (int i = 0; i < 5; i++) {
            if (variance[i] <= min) {
                min = variance[i];
                minkey = i;
            }
        }
        this.myGroups[minkey].addVolunteer(vol);
        this.doFile(true);
        return true;
    }

    @Override
    public boolean moveVolunteer(String id, String from, String to) {
        //move a volunteer with this skillset (eg AAA, BCD) from one CommunityGroup to another
        //COMPLETE CODE HERE
//        ListIterator<Volunteer> lit = myVols.listIterator();
//        Volunteer vol;
//        while (lit.hasNext()) {
//            vol = lit.next();
//            if (vol.getSkillSet().equalsIgnoreCase(skillSet)) {
//                lit.remove();
//                System.out.println(skillSet + " form " + from + "was removed to " + to);
//            } else {
//                System.out.println("No such volunteer to remove: " + skillSet);
//            }
//        }
        int fromgroupid;
        int togroupid;
        try {
            fromgroupid = Integer.parseInt(from);
            togroupid = Integer.parseInt(to);
        } catch (Exception e) {
            return false;
        }
        Volunteer res;
        if ((fromgroupid >= 1 && fromgroupid <= 5) && (togroupid >= 1 && togroupid <= 5)) {
            res = this.myGroups[fromgroupid - 1].removeVolunteer(id);
            if (res != null) {
                this.myGroups[togroupid - 1].addVolunteer(res);
                this.doFile(true);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteVolunteer(String id, String groupid) {
        //delete a volunteer with this skillset from this CommunityGroup
        //COMPLETE CODE HERE
        int fromgroupid;
        try {
            fromgroupid = Integer.parseInt(groupid);
        } catch (Exception e) {
            return false;
        }
        Volunteer res;
        res = this.myGroups[fromgroupid - 1].removeVolunteer(id);
        if (res != null) {
            this.doFile(true);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAllVolunteers() {
        // delete all volunteers from all CommunityGroups
        //COMPLETE CODE HERE
        for (CommunityGroup i : this.myGroups) {
            i.removeAll();
        }
        this.doFile(true);
        return true;
    }

    @Override
    public CommunityGroup[] getCommunityGroups() {
        //return an ArrayList of all this application's CommunityGroups
        return myGroups;
    }

    /**
     * 求给定双精度数组中值的和
     *
     * @param inputData 输入数据数组
     * @return 运算结果
     */
    public double getSum(double[] inputData) {
        if (inputData == null || inputData.length == 0) {
            return -1;
        }
        int len = inputData.length;
        double sum = 0;
        for (int i = 0; i < len; i++) {
            sum = sum + inputData[i];
        }
        return sum;
    }

    /**
     * 求给定双精度数组中值的数目
     *
     * @param input Data 输入数据数组
     * @return 运算结果
     */
    public int getCount(double[] inputData) {
        if (inputData == null) {
            return -1;
        }
        return inputData.length;
    }

    /**
     * 求给定双精度数组中值的平均值
     *
     * @param inputData 输入数据数组
     * @return 运算结果
     */
    public double getAverage(double[] inputData) {
        if (inputData == null || inputData.length == 0) {
            return -1;
        }
        int len = inputData.length;
        double result;
        result = getSum(inputData) / len;

        return result;
    }

    /**
     * 求给定双精度数组中值的平方和
     *
     * @param inputData 输入数据数组
     * @return 运算结果
     */
    public double getSquareSum(double[] inputData) {
        if (inputData == null || inputData.length == 0) {
            return -1;
        }
        int len = inputData.length;
        double sqrsum = 0.0;
        for (int i = 0; i < len; i++) {
            sqrsum = sqrsum + inputData[i] * inputData[i];
        }

        return sqrsum;
    }

    /**
     * 求给定双精度数组中值的方差
     *
     * @param inputData 输入数据数组
     * @return 运算结果
     */
    public double getVariance(double[] inputData) {
        int count = getCount(inputData);
        double sqrsum = getSquareSum(inputData);
        double average = getAverage(inputData);
        double result;
        result = (sqrsum - count * average * average) / count;
        return result;
    }

}
