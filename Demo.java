import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File; 
 
public class Demo {
 public static HashTable table = new HashTable(16);
    public static void main(String[] args) {
            if(args.length != 2)
            {
                System.out.println("Invalid arguments");
            }
            else
            {
                System.out.println(Demo.WordCounter(args[0], args[1]));
            }
        }
    
    //count the words and print out
    public static String WordCounter(String inputFileName, String outputFileName) {
        File inputFile = new File("toyInput.txt");
        File outputFile = new File("toyOutput.txt");
        boolean success;     
        success = makeTable(inputFile);
        if(!success)
            return "Failed to make HashTable";   
        success = makeOutput(outputFile);
        if(!success)
            return "Failed to make Output File";
        return table.other() + "\n" + table.toString();
    }
     
    
    //create the table while scanning the input file
    private static boolean makeTable(File inputFile) {
     try {
            FileReader reader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
             for(String key : line.split("[\\p{Punct}\\s]+"))
                {
                    if(!key.equals(""))
                        table.addNode(key.toLowerCase(), 1);
                }
            }
            reader.close();
        } catch (IOException e) {
         return false;
        }
        return true;
    }
    
    //print out the information the output file
    public static boolean makeOutput (File outputFile) {
            try {
                FileWriter writer = new FileWriter("toyOutput.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write( table.other() + "\n" + table.toString());
                bufferedWriter.close();
                
            } catch (IOException e) {
             return false;
            }
            return true;
            }
     
    }
