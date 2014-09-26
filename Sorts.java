import java.util.Arrays;

/**
 * sorting class that sorts an integer array from smallest to greatest element. 
 * class includes heap sort, quick sort, and merge sort.
 * @author Jiaxin He
 */
public class Sorts {
  
  //Performs heap sort. uses inner class.
  public static void heapSort(int[] arr) {
    Sorts sort = new Sorts();
    Sorts.Heap heap = sort.new Heap(arr);
    int[] sorted = heap.heapSort(arr);
    
    for (int i = 0; i <sorted.length; i++) {
      arr[i]=sorted[i];
    }
  }
  
  //Quick sort class. mainly uses myQuickSort.
  public static void quickSort(int[] arr){
    if (arr.length == 0 || arr == null) {
      return;
    }
    else 
      myQuickSort(arr, 0, arr.length-1);
  }
  
  //quick sort that sorts in place. have two pointers, one on left and one on right, going 
  //at the same time towards the middle, and swap at any time when the left is greater than right,
  //or when right is less than the right.
  private static void myQuickSort(int[] arr, int first, int last) {
    
    //the piviot for the algorithm.
    int pivot = arr[(first+last)/2];
    //left pointer.
    int i = first;
    //right pointer.
    int j = last;
    
    while (i<=j) {
      
      while (arr[i] < pivot) {
        i++;
      }
      
      while (arr[j] > pivot) {
        j--;
      }
      
      //swap
      if (i<=j){
        int save = arr[i];
        arr[i]=arr[j];
        arr[j] = save; 
        
        i++;
        j--;
      }
    }
    //as long as the left is less than the right, then continue to swap.
    if (first <j){
      myQuickSort(arr, first, j);
    }
    //as long as the right is greater than the left, then continue to swap.
    if (i<last) {
      myQuickSort(arr, i, last);
    }
  }
  
  //performs merge sort. calls on my merge sort and merge array.
  public static void mergeSort(int[] arr){
    int length = arr.length;
    int[] temp = new int[length];
    
    if (arr == null || length == 0) {
      return;
    }
    else
      myMergeSort(arr, temp, 0, length-1);
  }
  
  //splits the array into smaller arrays and sort them, then merge.
  public static void myMergeSort(int[] arr, int[] temp, int first, int last) {
    
    if (first < last) {
      int middle = (first +last)/2;
      myMergeSort(arr, temp, first, middle);
      myMergeSort(arr, temp, middle+1, last);
      mergeArray(arr, temp, first, middle, last);       
    }
  }
  
  //splits array to smaller size until size of 1, then start merging them back.
  public static void mergeArray(int[] arr, int[] temp, int first, int middle, int last) {
    
    for (int i = first; i<=last; i++) {
      temp[i]=arr[i];
    }
    
    int a = first;
    int b = first;
    int c = middle+1;
    while (a<=middle && c<=last) {
      if (temp[a]<=temp[c]) {
        arr[b]=temp[a];
        a++;
      }
      else {
        arr[b]=temp[c];
        c++;
      }
      b++;
    }
    
    while (a <=middle) {
      arr[b]=temp[a];
      a++;
      b++;
    }
    
    while (c<=last) {
      arr[b] = temp[c];
      b++;
      c++;
    }
  }
  
  //inner class used by the heap sort.
  private class Heap {
    
    private int[] items;
    private int numItems;
    public int[] sorted;
    
    //constructor initialize the array and variables.
    private Heap(int[] arr){
      items = arr;
      numItems = items.length;
    }
    
    //builds the heap.
    private void buildHeap() {
      for (int i = numItems/2-1; i>=0; i--){
        siftDown(i);
      }
    }
    
    //removes the max element in the heap.
    private int removeMax() {
      
      int toRemove = items[0];
      items[0] = items[numItems-1];
      numItems--;
      siftDown(0);
      return toRemove; 
    }
    
    //inputs the node to sift
    private void siftDown(int i) {
      
      int toSift = items[i];
      int parent = i;
      //start with the left child. the child to compare with.
      int child = 2*parent +1;
      while (child < numItems) {
        //in the event that the right child is greater than the left, compare with right.
        //and if left child exists and is greater than left,
        if (child + 1 < numItems && (items[child]-items[child+1]) <0)
          //grab right child
          child = child + 1;
        if (toSift-items[child] >= 0)
          break;
        items[parent]=items[child];
        items[child] = toSift;
        parent = child;
        child = 2*parent + 1;
      }
      items [parent] = toSift;
    }
    
    //Sums up the inner class so that the heapSort class only need to call this method.
    public int[] heapSort(int[] toSort) {
      Heap heap = new Heap(toSort);
      sorted = new int[toSort.length];
      heap.buildHeap();
      
      for (int i = toSort.length-1; i>-1; i--) {
        sorted[i] = heap.removeMax();
      }
      return sorted;
    }
  }
}
