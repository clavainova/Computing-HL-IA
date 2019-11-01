import java.util.*;
import java.io.*;
public class timetable
{
    public static String[] readfromfile()
    {
        BufferedReader reader = null;
        Scanner input = new Scanner(System.in);
        String[] studentarray = new String[36]; //35 is a realistic maximum class size

        try
        {
            String fileName = "";

            System.out.println("Type in the file path or D to choose the default.");
            System.out.println("Press I to get instructions on how to write the file path.");
            String answer = input.nextLine();

            if(answer.equals("i") || answer.equals("I")) //print instructions if they ask for them
            {
                System.out.println("");
                System.out.println("1) It must be a .csv file. You can save a file in this format using MS Excel.");
                System.out.println("2) The path should look like this - using forward slashes:");
                System.out.println("   D:/Computing IA/DB/common_time.csv");
                System.out.println("3) Be careful about forward slashes as Windows uses backslashes by default.");
                System.out.println("4) Make sure you have specified the disc location, e.g. C:/ or H:/");
                System.out.println("");
                System.out.println("What is the file's path?");
                answer = input.nextLine();
            }

            System.out.println();
            if((answer.indexOf(".csv") == -1) && !(answer.equals("d") || answer.equals("D"))) 
            //check that they have submitted a .csv file by checking the string contains ".csv"
            //except "d" and "D" because those are valid answers for choosing the default
            //important to catch if it's not a .csv because reading in another file format could cause unusual errors
            {
                System.out.println("|.---------------.|");
                System.out.println("||  NICE  TRY    ||");
                System.out.println("||   THAT IS     ||");
                System.out.println("||    NOT A      ||");
                System.out.println("||    .CSV       ||");
                System.out.println("|.---------------.|");
                System.out.println("That answer did not contain the characters .csv and is therefore not a valid file.");
                return null;
            }

            if(answer.equals("d")||answer.equals("D")) //choose the default - alter this depending on where it's running
            {
                fileName = "D:/Computing IA/FINAL/common_time.csv";
            }
            else
            {
                fileName = answer;
            }

            try
            {
                File file = new File(fileName);
                reader = new BufferedReader(new FileReader(file));

                String line = " ";
                int count = 0;
                boolean fileEnd = true;
            	reader.readLine(); //skip line 1
                
            	while(line != null)
                {
                    line = reader.readLine();
                    if(line != null) 
                    {
                        studentarray[count] = line; //storing the info, but it hasn't been parsed yet
                    }
                    count++;
                }
            }
            catch(java.io.FileNotFoundException e)
            {
                System.out.println(answer + " is not a valid file path, file not found.");
                System.out.println("Press I next time for instructions on how the path should be written.");
            }
            //return count;
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally 
        {
            try 
            {
                reader.close(); //closer the reader
                return studentarray;
            } 
            catch (IOException e)
            {
                e.printStackTrace(); //check for I/O errors - input/output errors
            }
            catch(java.lang.NullPointerException e)
            {
                System.out.println("Returning to main menu.");
                return null;
            }
        }
        return null;
    }
}