import edu.duke.*;

/**
 * examBabyName
 * 
 * @author  Furukawa Atsushi
 * @version 1.0.0
 * @date    2022-02-07
 */
public class examBabyName
{ 
    /**
     * 定数宣言
     */
    final int MALE   = 1;
    final int FEMALE = 2;
    final int TOTAL  = 0;
    
    /**
     * for examination 
     *
     */
    public void examBabyName() {
        System.out.println("\n  examination executor, q.1-8");
        babyNames baby = new babyNames();
    
        //
        String basedir = "../datas/us_babynames_by_year/";
        DirectoryResource dr;
        FileResource fr;
        
        // q.1
        String csvfile;
        int[]  total;
        
        csvfile = "yob1900.csv";
        fr = new FileResource(basedir + csvfile);
        total = baby.totalBirths(fr);
        System.out.println("q.1\n  File:"+ csvfile + " in Male:" + total[MALE] + " Female:" + total[FEMALE]);
        
        // q.2
        csvfile = "yob1905.csv";
        fr = new FileResource(basedir + csvfile);
        total = baby.totalBirths(fr);
        System.out.println("q.2\n  File:"+ csvfile + " in Male:" + total[MALE] + " Female:" + total[FEMALE]);
        
        // q.3
        int year = 1960;
        String name = "Emily";
        String gender = "F";
        int rank = baby.getRank(year, name, gender);
        System.out.println("q.3\n  Year:" + year + "'s Name:" + name +"("+gender+") rank:"+rank);
        
        // q.4
        year = 1971;
        name = "Frank";
        gender = "M";
        rank = baby.getRank(year, name, gender);
        System.out.println("q.4\n  Year:" + year + "'s Name:" + name +"("+gender+") rank:"+rank);
        
        // q.5
        year = 1980; gender="F"; rank = 350;
        name = baby.getName(year, rank, gender);
        System.out.println("q.5\n  Name:" + name + " is in (year:" + year + ", rank:" + rank + ", gender:" + gender + ")");
        
        // q.6
        year = 1982; gender="M"; rank = 450;
        name = baby.getName(year, rank, gender);
        System.out.println("q.5\n  Name:" + name + " is in (year:" + year + ", rank:" + rank + ", gender:" + gender + ")");
        
        // q.7
        name = "Susan"; year=1972; gender="F";
        int newYear = 2014;
        String newName = baby.whatIsNameInYear(name, year, gender, newYear);
        System.out.println("q.7\n  Name:"+name + " in " + year + " ⇒ New Name:" + newName + " in " + newYear + ".");
     
        // q.8
        name = "Owen"; year=1974; gender="M";
        newYear = 2014;
        newName = baby.whatIsNameInYear(name, year, gender, newYear);
        System.out.println("q.8\n  Name:"+name + " in " + year + " ⇒ New Name:" + newName + " in " + newYear + ".");
    }
    
    /**
     * for examination 
     *
     */
    public void examBabyName2() {
        System.out.println("\n  examination executor, q.9 - 14.");
        babyNames baby = new babyNames();
        DirectoryResource dr;
        String name;
        String gender;
        
        // q.9
        name="Genevieve"; gender = "F";
        dr = new DirectoryResource();
        int year = baby.yearOfHighestRank(name, gender, dr);
        System.out.println("q.9\n  Name:"+name+"("+gender+")  highest year is " + year);
    
        // q.10
        name="Mich"; gender = "M";
        dr = new DirectoryResource();
        year = baby.yearOfHighestRank(name, gender, dr);
        System.out.println("q.10\n  Name:"+name+"("+gender+")  highest year is " + year);
        
        // q.11
        name = "Susan"; gender="F";
        dr = new DirectoryResource();
        double avg = baby.getAverageRank(name, gender, dr);
        System.out.println("q.11\n  Name:"+name+"("+gender+") average rank is " +avg);
        
        // q.12
        name = "Robert"; gender="M";
        dr = new DirectoryResource();
        avg = baby.getAverageRank(name, gender, dr);
        System.out.println("q.12\n  Name:"+name+"("+gender+") average rank is " +avg);
        
        // q.13
        name = "Emily"; gender = "F";
        year = 1990;
        int total = baby.getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("q.13\n  Name:"+name+"("+gender+") higher ranks birth: " + total); // expect 15
    
        // q.14
        name = "Drew"; gender = "M";
        year = 1990;
        total = baby.getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("q.14\n  Name:"+name+"("+gender+") higher ranks birth: " + total); // expect 15
    }
}
