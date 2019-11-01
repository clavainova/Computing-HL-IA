import java.util.*;

public class student
{
    Scanner input = new Scanner(System.in);

    int ID = 0; //ID to identify their order
    private String name = ""; //name so the user will know whom we refer to
    public String timesfree[] = new String[14]; //13 because 0 is a possibility - 14 spaces for data to be stores

    public student(String StudentName, String[] studentFree, int IDin) //build the "student" object
    {
        timesfree = studentFree.clone();
        //check if the clone worked
        //for(int studNum = 0; studNum < studentFree.length; studNum++)
        //{
        //    System.out.println("cloned: " + timesfree[studNum] + "    clone: " + studentFree[studNum]);	
        //}
        name = StudentName;
        ID = IDin; //is this actually used?
        return;
    }

    public int getID()
    {
        return ID;
    }

    public String getName()
    {
        return name;
    }

    public String[] getTable() //not currently use but useful to leave in
    {
        return timesfree;
    }

    public void printStudent()
    {
        System.out.println();
        System.out.print("ID: " + ID);
        System.out.print("   Name: " + name);
        System.out.println();
    }
}
