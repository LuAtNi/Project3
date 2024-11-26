import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HeapOutput {

    /**
    * Reads integers from a file (data_sorted.txt) and puts them into an array
    * @param fileName Name of the file where the integers will be read from
    * @return Returns an array of integers
    * @throws FileNotFoundException
    */
    public static Integer[] readInts(String fileName) throws FileNotFoundException 
    {

        int i = 0;
        int temp = 0;
        
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        Integer[] result = new Integer[100];

        while(scan.hasNextInt())
        {

            temp = scan.nextInt();
            result[i] = Integer.valueOf(temp);
            i++;
       }

        scan.close();
        return result;

    }

    /**
    * Prints the number of swaps that occur when changing a heap
    * @param heap The heap the swaps are being calculated from
    * @param fileName The file that the output will be printed into
    * @throws IOException
    */
    public static void printSwaps(MaxHeap<Integer> heap, String fileName) throws IOException 
    {
        
        File file = new File (fileName);

        if (heap != null && file.exists())
        {
            int numSwaps = heap.getSwaps();

            FileWriter appendFile = new FileWriter(fileName, true);
            PrintWriter outputFile = new PrintWriter(appendFile);

            outputFile.println("Number of swaps in the heap creation: " + numSwaps);

            outputFile.close();
        }
    }

    /**
    * Peforms the removal of 10 inetgers from the heap
    * @param heap The max heap that the integers are being removed from
    */
    public static void remove10(MaxHeap<Integer> heap) 
    {
        for (int i = 0; i < 10;i++) 
        {
            heap.removeMax();
        }
    }

    /**
    * Prints the resulting heap created with the sequential insertion method into an output file
    * @param heap The heap that is being printed
    * @param fileName The file that this heap will be printed into
    * @throws IOException
    */
    public static void printSequential(MaxHeap<Integer> heap, String fileName) throws IOException 
    {
        
        File file = new File(fileName);
    

        if(heap != null && file.exists())
        {

            FileWriter appendFile = new FileWriter(fileName, true);
            PrintWriter outputFile = new PrintWriter(appendFile);

            outputFile.print("Heap built using sequential insertions: ");
            
            for (int i = 1; i < 11; i++)
            {

                outputFile.print(heap.getter(i) + ", ");
            }

            outputFile.print("...");
            outputFile.println();
            outputFile.close();
        }    
        
    }

    /**
    * Prints the resulting heap created with the optimal insertion method into an output file
    * @param heap The heap that is being printed 
    * @param fileName The file that this heap will be printed into
    * @throws IOException
    */
    public static void printOptimal(MaxHeap<Integer> heap, String fileName) throws IOException 
    {
        
        File optimalFile = new File(fileName);

        if(heap != null && optimalFile.exists())
        {
            FileWriter appendFile = new FileWriter(fileName, true);
            PrintWriter outputFile = new PrintWriter(appendFile);

            outputFile.print("Heap built using optimal method: ");
            
            for(int i = 1; i < 11; i++)
            {
                outputFile.print(heap.getter(i) + ", ");
            }
            outputFile.print("...");
            outputFile.println();
            outputFile.close();
        }
    }

    /**
    * Prints the resulting heap after removing 10 integers (after performing remove10() method)
    * @param heap The heap that is being printed 
    * @param fileName The file that this heap will be printed into
    * @throws IOException
    */
    public static void printRemove10(MaxHeap<Integer> heap, String fileName) throws IOException 
    {
        File file = new File (fileName);

        remove10(heap);

        if (heap != null && file.exists())
        {
            FileWriter appendFile = new FileWriter(fileName, true);
            PrintWriter outputFile = new PrintWriter(appendFile);

            outputFile.print("Heap after 10 removals: ");
            for(int i = 1; i < 11; i++) 
            {
                outputFile.print(heap.getter(i) + ", ");
            }
            outputFile.println("...");
            outputFile.println("");
            outputFile.close();
        }
    }  
    
    /**
    * Prints a line of 69 equals signs which are used as borders
    * @param fileName The file that the border will be printed into
    * @throws IOException
    */
    public static void printEquals(String fileName) throws IOException 
    {
        
        FileWriter appendFile = new FileWriter(fileName, true);
        PrintWriter outputFile = new PrintWriter(appendFile);

        for (int i = 0; i < 69; i++) 
        {
            outputFile.print("="); 
        }
        outputFile.println("");
        outputFile.println("");
        
        outputFile.close();
    }

    public static void main(String[] args) throws IOException 
    {

    
        PrintWriter outputFile = new PrintWriter("outputFile.txt");
        //File name should be updated according to input file on computer
        Integer[] heapArray = readInts("C:\\Users\\prize\\.vscode\\Project3\\Project3\\data_sorted.txt");
    

        MaxHeap<Integer> sequentialHeap = new MaxHeap<>();
        sequentialHeap.sequentialInsertion(heapArray);

        printEquals("outputFile.txt");

        printSequential(sequentialHeap, "outputFile.txt");
        printSwaps(sequentialHeap, "outputFile.txt");
        printRemove10(sequentialHeap, "outputFile.txt");


        MaxHeap<Integer> optimalHeap = new MaxHeap<>();
        optimalHeap.optimalInsertion(heapArray);

        printOptimal(optimalHeap,"outputFile.txt");
        printSwaps(optimalHeap, "outputFile.txt");
        printRemove10(optimalHeap, "outputFile.txt");

        printEquals("outputFile.txt");


        outputFile.close();
        
    }
    
}
