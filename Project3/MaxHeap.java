/**
   A class that implements the ADT maxheap by using an array.
 
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/

public final class MaxHeap<T extends Comparable<? super T>>
             implements MaxHeapInterface<T>
{
   private T[] heap;      // Array of heap entries; ignore heap[0]
   private int lastIndex; // Index of last entry and number of entries
   private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 100;
	private static final int MAX_CAPACITY = 10000;
   private int swaps = 0;
   
   //Default constructor
   public MaxHeap()
   {
      this(DEFAULT_CAPACITY); // Call next constructor
   } // end default constructor
   
   public MaxHeap(int initialCapacity)
   {
      // Is initialCapacity too small?
      if (initialCapacity < DEFAULT_CAPACITY)
         initialCapacity = DEFAULT_CAPACITY;
      else // Is initialCapacity too big?
         checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempHeap = (T[])new Comparable[initialCapacity + 1];
      heap = tempHeap;
      lastIndex = 0;
      integrityOK = true;
   } // end constructor

    /**
    * Builds a max heap using sequential insertions
    * @param newEntry The array that will hold the heap
    */
   public void sequentialInsertion(T[] newEntry)
   {

      int tempSwaps = 0; 
   
      for (int i = 0; i < newEntry.length; i++)
      {
         tempSwaps = this.add(newEntry[i]) + tempSwaps;
      }
      swaps = tempSwaps;
   }

   /**
    * Builds a max heap using optimal insertions
    * @param newEntry The array that will hold the heap
    */
   public void optimalInsertion(T[] newEntry)
   {

      lastIndex = newEntry.length;
      int tempSwaps = 0; 

      for (int index = 0; index < lastIndex; index++)
         heap[index + 1] = newEntry[index];

      for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
         tempSwaps = reheap(rootIndex) + tempSwaps;
      
      swaps = tempSwaps;
   }

   /**
    * Adds a new elements to a heap
    * @param newEntry The new element that will be added to the heap
    */
   public int add(T newEntry)
   {
         int tempSwaps = 0;
         int newIndex = lastIndex + 1;
         int parentIndex = newIndex/2;
         while ((parentIndex > 0) && newEntry.compareTo(heap[parentIndex])>0)
         {
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex/2;
            tempSwaps++;
         }
         heap[newIndex] = newEntry;
      lastIndex++;
      checkCapacity(lastIndex);
      
      return tempSwaps;
   } // end add

   /**
    * Removes the highest root of the heap and adjusts the rest of the heap using the reheap method
    * @return The new root after readjusting the heap
    */
   public T removeMax()
   {
      T root = null;
      if (!isEmpty())
      {
         root = heap[1];
         heap[1] = heap[lastIndex];
         lastIndex--;
         reheap(1);
      }
      return root;
   } // end removeMax

   /**
    * Gives the value of the current root of a heap
    * @return The value of the highest root
    */
   public T getMax()
   {
		checkIntegrity();
      T root = null;
      if (!isEmpty())
         root = heap[1];
      return root;
   } // end getMax

   
   public boolean isEmpty()
   {
      return lastIndex < 1;
   } // end isEmpty

   
   public int getSize()
   {
      return lastIndex;
   } // end getSize

   
   public void clear()
   {
		checkIntegrity();
      while (lastIndex > -1)
      {
         heap[lastIndex] = null;
         lastIndex--;
      } // end while
      lastIndex = 0;
   } // end clear

   public int getSwaps()
   {
      return swaps;
   }

   /**
    * Gets the value of a specific index
    * @param index The index we are getting the value for
    * @return The value identified
    */
   public T getter(int index){
      if (index > 0 && index <= lastIndex)
      {
         return heap[index];
      }
      else 
         return null;
   }

   
// Private methods

   /**
    * Checks that the integrity is maintained, throws exception if is it not
    */
   private void checkIntegrity()
   {
      if(!integrityOK)
      {
         throw new SecurityException("MaxHeap object is corrupt");
      }
   }

   /**
    * Checks to see if the maxiumum capacity is reached, throws exception if it is
    * @param capacity Integer that represents the capacity
    */
   private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
      {
         throw new IllegalStateException("Heap exceeds maximum capacity");
      }
   }

   /**
    * Adjusts the subtress after its root is removed
    * @param rootIndex The root whos subtree is being adjusted
    * @return Integer that represents the number of swaps that occured when adjusting the heap
    */
   private int reheap(int rootIndex)
   {

      int tempSwaps = 0;
      boolean done = false;
      T orphan = heap[rootIndex];
      int leftChildIndex = 2 * rootIndex;

      while (!done && (leftChildIndex <= lastIndex))
      {
         int largerChildIndex = leftChildIndex; //Assume larger
         int rightChildIndex = leftChildIndex + 1;
         if ((rightChildIndex <= lastIndex) &&
               heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
         {
            largerChildIndex = rightChildIndex;
         }
         if (orphan.compareTo(heap[largerChildIndex]) < 0)
         {
            heap[rootIndex] = heap[largerChildIndex];
            rootIndex = largerChildIndex;
            leftChildIndex = 2 * rootIndex;
            tempSwaps++;
         }
         else
            done = true;
      } // end while
      heap[rootIndex] = orphan;
      return tempSwaps;
   } // end reheap

} // end MaxHeap
