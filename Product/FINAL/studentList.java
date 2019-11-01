
//don't forget to declare that 35 is the maximum - as realistic maximum class size

import java.util.*;
public class studentList 
{
    private static ArrayList<student> studentlist = new ArrayList<student>(); //create new arraylist
    private static int[][] commontime;

    public static void add(student X)
    {
        studentlist.add(X);
    }

    public static ArrayList<student> getList()
    {
        return studentlist;
    }

    public static boolean empty()
    {
        if(studentlist.size() != 0)
        {
            return false;
        }
        return true;
    }

    public static void setTheArray(int[][] commonTime)
    {
        commontime = commonTime;
        return;
    }   //because this array is updated when rows are blanked for students already grouped and it has to be passed between methods, this is necessary

    public static int[][] getCommonTime()
    {
        return commontime;
    }

    public static void findStudent(String answer)
    {
        for(int i = 0; i < studentlist.size(); i++)
        {
            student temporary = studentlist.get(i);
            if(temporary.getName().equals(answer))
            {
                System.out.println("Student " + answer + " is in the list.");
                return;
            }
        }
        System.out.println("Student " + answer + " could not be found! (╯°□°）╯︵ ┻━┻");
        System.out.println(" Did you enter the full name correctly? It requires the full name and is case sensitive.");
        return;
    }

    public static void printStudents()
    {
        if(studentlist.size() == 0)
        {
            System.out.println("There are currently no students being stored.");
        }
        else
        {
            System.out.println("The number of students is " + studentlist.size()); //list the size
            System.out.println("Here they are: ");
            for(int i = 0; i < studentlist.size(); i++)
            {
                studentlist.get(i).printStudent();
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            }
        }
    }

    public static ArrayList<int[]> counting()
    {
        //initialise array
        commontime = new int[studentlist.size()][studentlist.size()];

        // Fill whole table with -1 values, default/empty value
        for(int x = 0; x < studentlist.size(); x++)
        {
            for(int y = 0; y < studentlist.size(); y++)
            {
                commontime[x][y] = -1;
            }
        }

        //test
        //System.out.println(" ");
        //for(int y = 0; y < 4; y++)
        //{
        //    System.out.println("after filling -1  y :" + y 
        //        + ", " + commontime[0][y]
        //        + ", " + commontime[1][y]
        //        + ", " + commontime[2][y]
        //        + ", " + commontime[3][y]
        //    );
        //    System.out.println(" ");
        //}
        //test

        //count total shared periods for every combination of students in the table
        for(int x = 0; x < studentlist.size(); x++)
        {
            for(int y = 0; y < studentlist.size(); y++) //count through all the students
            {
                if ((x != y)  && !( x < y))
                {
                    commontime[x][y] = studentList.checkPair(x,y); //put the number of shared frees into the space
                    //System.out.println("filling in counts X = " + x + "  &  Y = " + y + "  &  commontime = " + commontime[x][y]); //for testing to see what's being stored
                }
            }
        }

        //test
        System.out.println(" ");         
        //for(int y = 0; y < 4; y++)
        //{
        //    System.out.println("after filling in counts  y :" + y 
        //        + ", " + commontime[0][y]
        //        + ", " + commontime[1][y]
        //        + ", " + commontime[2][y]
        //        + ", " + commontime[3][y]
        //    );
        //    System.out.println(" ");
        //}
        //test

        // Find the pairs with the highest number of shares 
        ArrayList<int[]> grouplist = new ArrayList<int[]>(); //create new arraylist

        for(int loopcount = studentlist.size()/2; loopcount > 0; loopcount--)
        {
            //start find groups
            //System.out.println("  ");
            //System.out.println("starting findGroups()  ---------- with " + studentlist.size() + " students."); //for testing
            int biggest = -1;
            int[] coords = new int[2];

            for(int x = 0; x < studentlist.size(); x++)     
            {
                for(int y = 0; y < studentlist.size(); y++) 
                {
                    if(commontime[x][y] > biggest)
                    {
                        biggest = commontime[x][y];
                        coords[0] = x;
                        coords[1] = y;
                    }
                }
            }

            //System.out.println("ending findGroups()  found biggest value of x= " + coords[0] + ", y = " + coords[1] );
            //System.out.println("biggest value found was: " + biggest);

            //test
            //System.out.println(" ");         
            //for(int y = 0; y < 4; y++)
            //{
            //    System.out.println("just found biggest about to blank  y :" + y 
            //        + ", " + commontime[0][y]
            //        + ", " + commontime[1][y]
            //        + ", " + commontime[2][y]
            //        + ", " + commontime[3][y]
            //    );
            //    System.out.println(" ");
            //}
            //test

            //blank out / remove the two students in the biggest pair from the table so we don't choose them again
            for(int i = 0; i < studentlist.size(); i++) 
            {
                commontime[i][coords[1]] = -1;
                commontime[coords[0]][i] = -1;
                commontime[coords[1]][i] = -1;
                commontime[i][coords[0]] = -1;
            }

            //test
            //System.out.println(" ");         
            //for(int y = 0; y < 4; y++)
            //{
            //    System.out.println("after blank k  y :" + y 
            //        + ", " + commontime[0][y]
            //        + ", " + commontime[1][y]
            //        + ", " + commontime[2][y]
            //        + ", " + commontime[3][y]
            //    );
            //    System.out.println(" ");
            //}
            //test

            studentList.setTheArray(commontime);
            if (biggest != -1)
            {
                grouplist.add(coords);
            }
            else
            {
                int[] minusone = new int[2];
                minusone[0] = -1;
                minusone[1] = -1;
                System.out.println("returning after finding no biggest, all are -1");
                grouplist.add(minusone);
            }
        }
        return grouplist;
    }

    public static int checkPair(int x, int y) //calculate how many shared frees they have
    {
        student studentA = studentlist.get(x);
        student studentB = studentlist.get(y); //get the students being compared

        String[] studentAlist = studentA.getTable(); //get their timetables
        String[] studentBlist = studentB.getTable();

        int sharedFrees = 0;

        //test
        //System.out.println("student " + x + " timetable : "
        //    +  studentAlist[0] + "  " 
        //    +  studentAlist[1] + "  " 
        //    +  studentAlist[2] + "  " 
        //    +  studentAlist[3] + "  " 
        //    +  studentAlist[4] + "  " 
        //    +  studentAlist[5] + "  " 
        //);
        //System.out.println("student " + y + " timetable : "
        //    +  studentBlist[0] + "  " 
        //    +  studentBlist[1] + "  " 
        //    +  studentBlist[2] + "  " 
        //    +  studentBlist[3] + "  " 
        //    +  studentBlist[4] + "  " 
        //    +  studentBlist[5] + "  " 
        //);
        //test

        for(int i = 0; i <= 13; i++) //go through each day of the week and check if they are the same
        {
            if(studentAlist[i].equals(studentBlist[i])) //if they are the same add one to count of shared frees
            {
                sharedFrees++;
            }
        }

        return sharedFrees; //return the number of shared frees they have
    }
}