import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HeapOutput {

    public static Integer[] readInts(String fileName) throws FileNotFoundException {

        int i = 0;
        int temp = 0;
        
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        Integer[] result = new Integer[100];

        while(scan.hasNextInt()){

            temp = scan.nextInt();
            result[i] = Integer.valueOf(temp);
            i++;
       }

        scan.close();
        return result;

    }
    public static void main(String[] args){
        
    }
    
}
