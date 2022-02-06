import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
 * babyNames
 *   babyNames.vsv
 *   
 * @author  Atsushi Furukawa
 * @version 1.0.0
 * @date    2022-02-06
 */
public class babyNames
{
    /**
     * 定数宣言
     */
    final int MALE   = 1;
    final int FEMALE = 2;
    final int TOTAL  = 0;
    boolean IS_TEST = true;
    
    // utility function
    private String babynameCSV(int year, boolean is_test) {
        String basedir = "../datas/" + (is_test ? "us_babynames_test" : "us_babynames_by_year");
        String csvfile = "yob" + year + (is_test ? "short" : "" ) + ".csv";
        return basedir + "/" + csvfile;
    }
    
    /**
     * get birth year from F. filename
     */
    private int yearfromFile(File f) {
        String yearStr = f.getName().substring(3, 7);
        int year = Integer.parseInt(yearStr);
        
        return year;
    }
    
    
    // exercise function
    /**
     *  一覧表示
     *  
     */
    void printBabys(FileResource fr) {
        // System.out.println("\n  Baby names & numbers");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            System.out.println("Baby Name:"+rec.get(0) + " 性別:" + rec.get(1) + " Number:" + rec.get(2));
        }
    }
    
    /**
     *  出生数算出
     *  
     */
    int[] totalBirths(FileResource fr) {
        int[]  total = new int[3];
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String gender = rec.get(1);
            int num = Integer.parseInt(rec.get(2));
            
            if (gender.equals("M")) {
                total[MALE] += num;
            } else {
                total[FEMALE] += num;
            }
        }
        total[TOTAL] = total[MALE] + total[FEMALE];
        return total;
    }
    
    /**
     * get rank from name, gender (File from FileResource)
     */
    private int getRankfromFile(String babyName, String babyGender, FileResource fr) {
        final int NOT_FOUND = -1;
        
        if (!babyGender.equals("M") && !babyGender.equals("F")) {
            return NOT_FOUND;
        }
    
        // for get rank
        int[] rank = new int[3];
        rank[MALE] = 1;
        rank[FEMALE] = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String name = rec.get(0);
            String gender = rec.get(1);
            int gi = gender.equals("M") ? MALE : FEMALE;
            
            if (name.equals(babyName) && gender.equals(babyGender)) {
                return rank[gi];
            }
            rank[gi]++;
        }
        return NOT_FOUND;
    }
    
    /**
     * getRank
     *   get baby names rank by birth number
     */
    int getRank(int year, String babyName, String babyGender) {        
        FileResource fr = new FileResource(babynameCSV(year, IS_TEST));
        int rank = getRankfromFile(babyName, babyGender, fr);
        return rank; // not found
    }
    
    /**
     * getName
     *   get baby names from year, rank, gender
     */
    String getName(int year, int babyRank, String babyGender) {
        final String NOT_FOUND = "NO NAME";
        
        if (!babyGender.equals("M") && !babyGender.equals("F")) {
            return NOT_FOUND;
        }
        if (babyRank <= 0) {
            return NOT_FOUND;
        }
        
        int[] rank = new int[3];
        rank[MALE] = 1;
        rank[FEMALE] = 1;
        
        FileResource fr = new FileResource(babynameCSV(year, IS_TEST));
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String name   = rec.get(0);
            String gender = rec.get(1);
            int gi = gender.equals("M") ? MALE : FEMALE;
             
            if (gender.equals(babyGender) && rank[gi]==babyRank) {
                return name;
            }
            rank[gi]++;
        }
        return NOT_FOUND;
    }
    
    /**
     * whatIsNameInYear
     * 
     */
    String whatIsNameInYear(String name, int year, String gender, int newYear) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        return newName;
    }
    
    /**
     * int year = yearfromFile(f);
            
     */
    int yearOfHighestRank(String name, String gender, DirectoryResource dr) {
        int highestYear = -1;
        int highestRank = -1;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int year = yearfromFile(f);
            int rank = getRankfromFile(name, gender, fr);
            
            // System.out.println("Name:" + name + " year:" + year + " rank:"+rank);
            if (rank>0) { // rankあり
                if ((highestRank<=0)||(rank < highestRank)) {
                    highestRank = rank;
                    highestYear = year;
                }
            }
        }
        return highestYear;
    }
    
    /**
     * getAverageRank
     *   calc average rank from selected files
     */
    double getAverageRank(String name, String gender, DirectoryResource dr) {
        final int NO_RANK = -1;
        int rankNum = 0;
        double totalRank = 0;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int rank = getRankfromFile(name, gender, fr);
            
            System.out.println("Name:"+ name + "(" + gender + ") rank is " + rank);
            if (rank > 0) {
                totalRank += rank;
                rankNum++;
            }
        }
        if (rankNum <= 0) {
            return NO_RANK;
        }
        double avg = totalRank / rankNum;
        return avg;
    }
    
    /**
     * getTotalBirthsRankedHigher
     */
    int getTotalBirthsRankedHigher(int year, String babyName, String babyGender) {
        final int NOT_FOUND = -1;
        int[] totalBirth = new int[3];                
        
        FileResource fr = new FileResource(babynameCSV(year, IS_TEST));
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String name   = rec.get(0);
            String gender = rec.get(1);
            int gi = gender.equals("M") ? MALE : FEMALE;
            
            if (name.equals(babyName) && gender.equals(babyGender)) {
                return totalBirth[gi];
            }
            
            int birthNum  = Integer.parseInt(rec.get(2));
            totalBirth[gi] += birthNum;
        }
        return NOT_FOUND;
    }
    
    // test function
    // 出生数表示テスト
    public void testTotalBirths() {
        System.out.println("\n  Baby birth numbers");
    
        FileResource fr = new FileResource();
        
        int[] total = totalBirths(fr);
        System.out.println("男性:" + total[MALE] + " 女性:" + total[FEMALE] + " 合計:" + total[TOTAL]);
    }
    
    // 出生数ランク（性別)
    public void testGetRank() {
        int rank;
        int year;
        String name;
        String gender;

        System.out.println("\n  test get Rank by birth number.");
        // test main
        year = 2013; name = "Jacob"; gender="M";
        rank = getRank(year, name, gender); // expect 3 (test data)
        System.out.println("Year:" + year + " Name:" + name +"("+gender+") rank:"+rank);
        
        year = 2014; name = "Ava"; gender="F";
        rank = getRank(year, name, gender); // expect 5 (test data)
        System.out.println("Year:" + year + " Name:" + name +"("+gender+") rank:"+rank);
        
        year = 2014; name = "Greg"; gender="F";
        rank = getRank(year, name, gender); // expect -1: not found:
        System.out.println("Year:" + year + " Name:" + name +"("+gender+") rank:"+rank);
    }
    
    
    // Baby Name 取得テスト
    public void testGetName() {
        String name;
        int year;
        int rank;
        String gender;

        System.out.println("\n  test get name from year & gender.");
        // test main
        
        year = 2014; gender="F"; rank = 4;
        name = getName(year, rank, gender);
        System.out.println("Name:" + name + " is in (year:" + year + ", rank:" + rank + ", gender:" + gender + ")");
    
        year = 2012; gender="M"; rank = 3;
        name = getName(year, rank, gender);
        System.out.println("Name:" + name + " is in (year:" + year + ", rank:" + rank + ", gender:" + gender + ")");
        
        year = 2014; gender="l"; rank = 3;
        name = getName(year, rank, gender);
        System.out.println("Name:" + name + " is in (year:" + year + ", rank:" + rank + ", gender:" + gender + ")");
        
        year = 2014; gender="M"; rank = 12;
        name = getName(year, rank, gender);
        System.out.println("Name:" + name + " is in (year:" + year + ", rank:" + rank + ", gender:" + gender + ")");
    }
    
    // deferent year
    public void testWhatIsNameInYear() {
        String name;
        int year;
        int newYear;
        String gender;
        String newName;
        
        System.out.println("\n  test newYear's name from old year, name, gender.");
            
        name = "Isabella"; year=2012; gender="F";
        newYear = 2014;
        newName = whatIsNameInYear(name, year, gender, newYear);
        System.out.println(name + " born in " + year + " ⇒ " + newName + " born in " + newYear + ".");
    
        name = "Jacob"; year=2012; gender="M";
        newYear = 2014;
        newName = whatIsNameInYear(name, year, gender, newYear);
        System.out.println(name + " born in " + year + " ⇒ " + newName + " born in " + newYear + ".");

        name = "Anderson"; year=2014; gender="M";
        newYear = 2012;
        newName = whatIsNameInYear(name, year, gender, newYear);
        System.out.println(name + " born in " + year + " ⇒ " + newName + " born in " + newYear + ".");
    }
    
    public void testYearofHighestRank() {
        System.out.println("\n  year of Highest Rank");
        DirectoryResource dr;
        String name;
        String gender;
        int year;
        
        name="Sophia";
        gender = "F";
        dr = new DirectoryResource();
        year = yearOfHighestRank(name, gender, dr);
        System.out.println("Name:"+name+"("+gender+") highest year is " + year); // expect 2012
    
        name="Noah";
        gender = "M";
        dr = new DirectoryResource();
        year = yearOfHighestRank(name, gender, dr);
        System.out.println("Name:"+name+"("+gender+") highest year is " + year); // expect 2012
    
        name="JohnSmith";
        gender = "M";
        dr = new DirectoryResource();
        year = yearOfHighestRank(name, gender, dr);
        System.out.println("Name:"+name+"("+gender+") highest year is " + year); // expect 2012
    }
    
    /**
     * test Average Rank
     *   selected files average rank
     */
    public void testGetAverageRank() {
        System.out.println("\n  test average rank");
        String name;
        String gender;
        double avg;
        DirectoryResource dr = null;
        
        gender = "M"; name = "Mason";
        dr = new DirectoryResource();
        avg = getAverageRank(name, gender, dr);
        System.out.println("Name:"+name+"("+gender+") average rank is " +avg); // expect 3.0
        
        name = "Jacob"; gender = "M"; 
        dr = new DirectoryResource();
        avg = getAverageRank(name, gender, dr);
        System.out.println("Name:"+name+"("+gender+") average rank is " +avg); // expect 2.66
        
        name = "Bluesky";
        gender = "F"; 
        dr = new DirectoryResource();
        avg = getAverageRank(name, gender, dr);
        System.out.println("Name:"+name+"("+gender+") average rank is " +avg); // expect -1 (not found)
    }
    
    /**
     * testGetTotalBirthsRankedHigher 
     *
     */
    public void testGetTotalBirthsRankedHigher() {
        String name;
        String gender;
        int year;
        int total;
        
        System.out.println("\n  test get birth number when rank is higher");
           
        name = "Ethan";
        gender = "M";
        year = 2012;
        total = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("Name:"+name+"("+gender+") higher ranks birth: " + total); // expect 15
    
        name = "Ava";
        gender = "F";
        year = 2014;
        total = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("Name:"+name+"("+gender+") higher ranks birth: " + total); // expect 15
    
        name = "Ikuzoh";
        gender = "M";
        year = 2014;
        total = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("Name:"+name+"("+gender+") higher ranks birth: " + total); // expect 15
    }
    
}
