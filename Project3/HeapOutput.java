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

    public static void printSwaps(MaxHeap<Integer> heap, String fileName) throws IOException {
        
        File file = new File (fileName);

        if (heap != null && file.exists()){
            int numSwaps = heap.getSwaps();

            FileWriter appendFile = new FileWriter(fileName, true);
            PrintWriter outputFile = new PrintWriter(appendFile);

            outputFile.println("Number of swaps in the heap creation: " + numSwaps);

            outputFile.close();
        }
    }


    //Athena: print remove 10
    //Nikhita: print sequential heap
    //Lucia: print optimal heap

    public static void remove10(MaxHeap<Integer> heap) {
        for (int i = 0; i < 10;i++) {
            heap.removeMax();
        }
    }

    public static void printSequential(MaxHeap<Integer> heap, String fileName) throws IOException {
        
        File file = new File(fileName);
        Integer[] sequentialFileArr = readInts(fileName);

        if(heap != null && file.exists()){

            FileWriter appendFile = new FileWriter(fileName, true);
            PrintWriter outputFile = new PrintWriter(appendFile);

            outputFile.print("Heap built using sequential insertions: ");
            heap.sequentialInsertion(sequentialFileArr);
            
            for (int i = 0; i < heap.getSize(); i++){

                outputFile.print(heap.getter(i) + ", ");
            }

            outputFile.println();
            outputFile.close();
        }
            
        

    }

    public static void printOptimal(MaxHeap<Integer> heap, String fileName) throws IOException {
        
        File optimalFile = new File(fileName);
        Integer[] optimalFileArr = readInts(fileName);

        if(heap != null && optimalFile.exists()){
            FileWriter appendFile = new FileWriter(fileName, true);
            PrintWriter outputFile = new PrintWriter(appendFile);

            outputFile.print("Heap built using optimal method: ");
            heap.optimalInsertion(optimalFileArr);
            
            for(int i = 1; i < heap.getSize(); i++){
                outputFile.print(heap.getter(i) + ", ");
            }

            outputFile.println();
            outputFile.close();
        }
    }

    public static void printRemove10(MaxHeap<Integer> heap, String fileName) throws IOException {
        File file = new File (fileName);

        remove10(heap);

        if (heap != null && file.exists())
        {
            FileWriter appendFile = new FileWriter(fileName, true);
            PrintWriter outputFile = new PrintWriter(appendFile);

            outputFile.println("Heap after 10 removals: ");
            for(int i = 1; i < heap.getSize() + 1; i++) {
                outputFile.print(heap.getter(i) + ",");
                if (i == 25) {
                    outputFile.println();
                } 
                else if (i == 50) 
                {
                    outputFile.println();
                }
                else if (i == 75)
                {
                        outputFile.println();
                }
            }
            outputFile.close();
        }
    }  
    

    public static void main(String[] args) throws IOException {

        try {
            PrintWriter outputFile = new PrintWriter("outputFile.txt");

            for (int i = 0; i < 69; i++) {
                outputFile.print("="); 
            }
            outputFile.println();

            outputFile.close();
            System.out.println("Successfully wrote to outputFile.txt");

        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not create output file. " + e.getMessage());
        }
    }
    
}
