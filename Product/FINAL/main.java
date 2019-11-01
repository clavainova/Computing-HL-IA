import java.util.*;
public class main
{
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.println("(¯`·._.·(¯`·._.· Timetabling Program ·._.·´¯)·._.·´¯)");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println();

        while(true) //loop
        {
            System.out.println("Press 0 to read the students in from the file");
            System.out.println("Press 1 to see instructions for editing the file.");
            System.out.println("Press 2 to sort the students into pairs");
            System.out.println("Press 3 to see the list of students");
            System.out.println("Press 4 to search for a particular student");
            System.out.println("Press 5 to quit");
            System.out.println();
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            //they can also type easter egg or ghost to see easter eggs

            System.out.println("You pick option: ");
            String answer = input.nextLine();
            System.out.println();

            switch(answer)
            {
                case "0":
                //Read in the file of students and free times
                String[] studentFileLines = timetable.readfromfile(); 

                //Read in line by line and split into pieces
                String[] studentData = new String[16] ;
                String studentName = "";
                String[] studentFree = new String[14]; //timetable
                try
                {
                    for(int studentLine = 0; studentLine < studentFileLines.length; studentLine++)
                    {
                        //Split each file line into pieces
                        if  (studentFileLines[studentLine] != null)
                        {
                            studentData = studentFileLines[studentLine].split(","); //split by commas
                            studentName = studentData[0]; //find name
                            //System.out.println("student name is: " + studentName); //for testing
                            for(int count = 1; count < 15; count++)
                            {
                                studentFree[count - 1] = studentData[count];
                            }
                            student tempStudent = new student(studentName, studentFree,studentLine);
                            //System.out.println("adding student name " + studentName +  ", stud line " + studentLine); //for testing - to see who you're adding
                            studentList.add(tempStudent);
                        }
                        else
                        {
                            studentData[0] = null;  
                        }
                    }
                    break;
                }
                catch(NullPointerException e)
                {
                    System.out.println("Null pointer exception! (╯°□°）╯︵ ┻━┻");
                    break;
                }

                case "1": //see instructions
                main.instructions();
                break;

                case "2": //sort into pairs
                ArrayList<int[]> GroupsList = new ArrayList<int[]>();
                GroupsList = studentList.counting();

                //System.out.println("");
                //System.out.println("These are the groups...    the group list has " + GroupsList.size() + " items.");

                //for(int i = 0; i < GroupsList.size(); i++)
                //{
                //    int[] temporary = GroupsList.get(i);
                //    System.out.println("group " + i + " has x = " + temporary[0] + ", and y = " + temporary[1]);
                //}                                                                                  //for testing           

                ArrayList<student> STUDENT = studentList.getList();
                System.out.println("Here are the pairs: ");
                int count = 0;
                for(int i = 0; i < GroupsList.size(); i++) //print the sorted groups
                {
                    int[] temporary = GroupsList.get(i);
                    student MemberA = STUDENT.get(temporary[0]);
                    student MemberB = STUDENT.get(temporary[1]);

                    System.out.println("Group " + i + " contains: ");
                    MemberA.printStudent();
                    MemberB.printStudent();
                    System.out.println("");
                }
                System.out.println("Any students not mentioned could not be paired.");
                break;

                case "3": //print students
                studentList.printStudents();
                break;

                case "4": //search for a student
                if(studentList.empty() == true)
                {
                    System.out.println("There are no students being stored to search through.");
                    break;
                }
                System.out.println("What is the name of the student you would like to find?");
                String wordentered = input.nextLine();
                studentList.findStudent(wordentered);
                break;

                case "5": //terminate program
                System.out.println("Thanks for using this program, and farewell ( ͡° ͜ʖ ͡°) ( ͡° ͜ʖ ͡°) ");
                return;

                case "easter egg":
                System.out.println("  ()()");
                System.out.println(" ('x' )");
                System.out.println(" (U U )O" );
                System.out.println("  \"¯\"");
                break;

                case "ghost":
                System.out.println("    ~*it is a mystery*~ ");
                System.out.println("");
                System.out.println("        .-\"\"\"\"-.");
                System.out.println("       / -   -  \\");
                System.out.println("      |  .-. .- |");
                System.out.println("      |  \\o| |o (");
                System.out.println("      \\     ^    \\");
                System.out.println("      |'.  )--'  /|");
                System.out.println("     / / '-. .-'`\\ \\");
                System.out.println("    / /'---` `---'\\ \\");
                System.out.println("    '.__.       .__.'");
                System.out.println("        `|     |`");
                System.out.println("         |     \\");
                System.out.println("         \\      '--.");
                System.out.println("          '.        `\\");
                System.out.println("            `'---.   |");
                System.out.println("               ,__) /");
                System.out.println("                `..'");
                break;

                default:
                System.out.println(answer + " is not a valid response.");
                break;
            }

            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*"); //for aesthetics
        }
    }

    public static void instructions()
    {

        System.out.println("Since manually typing in every time the student is free would be");
        System.out.println("time-consuming it is advised that you edit the file which has headings for usability.");
        System.out.println("Press I for instructions and anything else to return to main menu.");
        String ans = input.nextLine();
        if(ans.equals("i") || ans.equals("I"))
        {
            System.out.println("INSTRUCTIONS FOR ENTERING TIMETABLES");
            System.out.println("1) Open 'common_time.csv' in Excel");
            System.out.println("2) Enter the student name and free periods in the headings, delete any");
            System.out.println("   student names and data you do not wish to use");
            System.out.println("3) KEY");
            System.out.println("   For weekdays:");
            System.out.println("     1 = period 1");
            System.out.println("     2 = period 2");
            System.out.println("     3 = peiord 3");
            System.out.println("     4 = period 4");
            System.out.println("     5 = lunch");
            System.out.println("     6 = period 5");
            System.out.println("     7 = after school");
            System.out.println("   For weekends:");
            System.out.println("     1 = morning");
            System.out.println("     2 = lunch");
            System.out.println("     3 = afternoon");
            System.out.println("     4 = night");
            System.out.println("     5 = whole day");

            System.out.println("4) Save the file - be sure it's saved as a CSV");
            System.out.println("   in the same place and the name isn't changed");
            System.out.println("   or if it is saved elsewhere manually enter the");
            System.out.println("   file path when asked later.");
        }
    }   
}