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
    	name = StudentName;
        ID = IDin;
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

    public String[] getTable()
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
