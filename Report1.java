import java.util.Random;
import java.io.*;

/**Report1 creates sorted array, reversed array, and random array (with different seeds) for
  * arrays with size 1000, 10000, 100000, and 1000000 elements. 
  * @author Jiaxin He
  */
public class Report1 {
  
  //creates the arrays and needed fields.
  int[] sortedArr1000;
  int[] sortedArr10000;
  int[] sortedArr100000;
  int[] sortedArr1000000;
  
  int[] reverseArr1000;
  int[] reverseArr10000;
  int[] reverseArr100000;
  int[] reverseArr1000000;
  
  int[] randomArr1000;
  int[] randomArr10000;
  int[] randomArr100000;
  int[] randomArr1000000;
  
  int[] data; 
  int[] sortTime;
  long start;
  long end;
  int length;
  double temp;
  Random random;
  
  PrintStream outputFile1; 
  PrintStream outputFile2;  
  PrintStream outputFile3; 
  
  //constructor initializes necessary arrays and variables.
  public Report1() throws IOException {
    sortedArr1000 = new int[1000];
    sortedArr10000 = new int[10000];
    sortedArr100000 = new int[100000];
    sortedArr1000000 = new int[1000000];
    
    reverseArr1000 = new int[1000];
    reverseArr10000 = new int[10000];
    reverseArr100000 = new int[100000];
    reverseArr1000000 = new int[1000000];
    
    randomArr1000 = new int[1000];
    randomArr10000 = new int[10000];
    randomArr100000 = new int[100000];
    randomArr1000000 = new int[1000000];
    
    data = new int[10];
    random = new Random();
    sortTime = new int[3];
    
    outputFile1 = new PrintStream("Report1HeapSortData.txt");
    outputFile2 = new PrintStream("Report1QuickSortData.txt");
    outputFile3 = new PrintStream("Report1MergeSortData.txt");
    
    //creates the arrays with size 1000.
    random.setSeed(0L);
    for (int i = 0; i <1000; i++) {
      sortedArr1000[i]=i;
      reverseArr1000[i]=1000-i;
      randomArr1000[i]= Math.abs(random.nextInt());
    }
    
    //creates the arrays with size 10000.
    random.setSeed(2L);
    for (int i = 0; i <10000; i++) {
      sortedArr10000[i]=i;
      sortedArr10000[i]=10000-i;
      randomArr10000[i]= Math.abs(random.nextInt());
    }
    
    //creates the arrays with size 100000.
    random.setSeed(1L);
    for (int i = 0; i <100000; i++) {
      sortedArr100000[i]=i;
      sortedArr100000[i]=100000-i;
      randomArr100000[i]= Math.abs(random.nextInt());
    }
    
    //creates the arrays with size 1000000.
    random.setSeed(3L);
    for (int i = 0; i <1000000; i++) {
      sortedArr1000000[i]=i;
      sortedArr1000000[i]=1000000-i;
      randomArr1000000[i]= Math.abs(random.nextInt());
    }
  }
  
  //Heap sort method, calls on heapSort method from Sorts. Calculates how long it takes for
  //the heapsort to sort, and stores into data.
  public void heapSort (int[] arr, int num) {
    
    for (int i = 0; i<num; i++) {
      start = System.nanoTime();
      Sorts.heapSort(arr);
      end = System.nanoTime();
      temp = (double) end-start;
      data[i]=(int) temp;
    }
  }
  
  //quick sort method, calls on quickSort method from Sorts. Calculates how long it takes for
  //the quicksort to sort, and stores into data.
  public void quickSort (int[] arr, int num) {
    
    for (int i = 0; i<num; i++) {
      start = System.nanoTime();
      Sorts.quickSort(arr);
      end = System.nanoTime();
      temp = (double) end-start;
      data[i]=(int) temp;
    }
  }
  
  //merge sort method, calls on mergeSort method from Sorts. Calculates how long it takes for
  //the mergesort to sort, and stores into data.
  public void mergeSort (int[] arr, int num) {
    
    for (int i = 0; i<num; i++) {
      start = System.nanoTime();
      Sorts.mergeSort(arr);
      end = System.nanoTime();
      temp = (double) end-start;
      data[i]=(int) temp;
    }
  }
  
  //calculates the average of a given int[] with the number of elements filled.
  public double meanVal(int[] data, int num) {
    double sum=0;
    for (int i = 0; i <num; i++) {
      sum = sum + data[i];
    }
    return sum/num;
  }
  
  //calculates the variance of a given int[] with the number of elements filled.
  public double varianceVal(int[] data, int num) {
    double mean = meanVal(data, num);
    double summation = 0;
    for (int i = 0; i < num; i++) {
      summation = summation + Math.pow(data[i]-mean, 2);
    }
    return summation/(num-1);
  } 
  
  //prints out the data to txt files in the same folder.
  public void getData(int[] arr, int num) throws IOException {    
    double variance;
    int[] hs = arr;
    int[] qs = arr;
    int[] ms = arr;
    
    //performs heapsort in this class, and prints out the time to the corresponding txt files.
    heapSort(hs,num);
    variance = varianceVal(data, num);
    for (int i = 0; i <num; i++) {
      outputFile1.print(data[i]);
      outputFile1.print("\n");
    }
    outputFile1.print("THE AVERAGE IS " + meanVal(data, num) + "\n");
    outputFile1.print("THE VARIANCE IS " + variance);
    
    //performs quicksort in this class, and prints out the time to the corresponding txt files.
    quickSort(qs,num);
    variance = varianceVal(data, num);
    for (int i = 0; i <num; i++) {
      outputFile2.print(data[i]);
      outputFile2.print("\n");
    }
    outputFile2.print("THE AVERAGE IS " + meanVal(data, num) + "\n");
    outputFile2.print("THE VARIANCE IS " + variance);
    
    //performs mergesort in this class, and prints out the time to the corresponding txt files.
    mergeSort(ms,num);
    variance = varianceVal(data, num);
    for (int i = 0; i <num; i++) {
      outputFile3.print(data[i]);
      outputFile3.print("\n");
    }
    outputFile3.print("THE AVERAGE IS " + meanVal(data, num) + "\n");
    outputFile3.print("THE VARIANCE IS " + variance);
  } 
  
  //prints out all of the data to the necessary txt files. calls on the getData method.
  public void allData() throws IOException {
    
    //SORTED ARR
    outputFile1.print("DATA FOR SortedArr1000" + "\n");
    outputFile2.print("DATA FOR SortedArr1000" + "\n");
    outputFile3.print("DATA FOR SortedArr1000" + "\n");
    getData(sortedArr1000, 3);
    
    outputFile1.print("\n" + "\n" + "DATA FOR SortedArr10000" + "\n");
    outputFile2.print("\n" + "\n" + "DATA FOR SortedArr10000" + "\n");
    outputFile3.print("\n" + "\n" + "DATA FOR SortedArr10000" + "\n");
    getData(sortedArr10000, 3);
    
    outputFile1.print("\n" + "\n" + "DATA FOR SortedArr100000" + "\n");
    outputFile2.print("\n" + "\n" + "DATA FOR SortedArr100000" + "\n");
    outputFile3.print("\n" + "\n" + "DATA FOR SortedArr100000" + "\n");
    getData(sortedArr100000, 3);
    
    outputFile1.print("\n" + "\n" + "DATA FOR SortedArr1000000" + "\n");
    outputFile2.print("\n" + "\n" + "DATA FOR SortedArr1000000" + "\n");
    outputFile3.print("\n" + "\n" + "DATA FOR SortedArr1000000" + "\n");
    getData(sortedArr1000000, 3);
    
    //REVERSE ARR
    outputFile1.print("\n" + "\n" + "DATA FOR ReverseArr1000" + "\n");
    outputFile2.print("\n" + "\n" + "DATA FOR ReverseArr1000" + "\n");
    outputFile3.print("\n" + "\n" + "DATA FOR ReverseArr1000" + "\n");
    getData(reverseArr1000, 3);
    
    outputFile1.print("\n" + "\n" + "DATA FOR ReverseArr10000" + "\n");
    outputFile2.print("\n" + "\n" + "DATA FOR ReverseArr10000" + "\n");
    outputFile3.print("\n" + "\n" + "DATA FOR ReverseArr10000" + "\n");
    getData(reverseArr10000, 3);
    
    outputFile1.print("\n" + "\n" + "DATA FOR ReverseArr100000" + "\n");
    outputFile2.print("\n" + "\n" + "DATA FOR ReverseArr100000" + "\n");
    outputFile3.print("\n" + "\n" + "DATA FOR ReverseArr100000" + "\n");
    getData(reverseArr100000, 3);
    
    outputFile1.print("\n" + "\n" + "DATA FOR ReverseArr1000000" + "\n");
    outputFile2.print("\n" + "\n" + "DATA FOR ReverseArr1000000" + "\n");
    outputFile3.print("\n" + "\n" + "DATA FOR ReverseArr1000000" + "\n");
    getData(reverseArr1000000, 3);
    
    //RANDOM ARR
    outputFile1.print("\n" + "\n" + "DATA FOR RandomArr1000" + "\n");
    outputFile2.print("\n" + "\n" + "DATA FOR RandomArr1000" + "\n");
    outputFile3.print("\n" + "\n" + "DATA FOR RandomArr1000" + "\n");
    getData(randomArr1000, 10);
    
    outputFile1.print("\n" + "\n" + "DATA FOR RandomArr10000" + "\n");
    outputFile2.print("\n" + "\n" + "DATA FOR RandomArr10000" + "\n");
    outputFile3.print("\n" + "\n" + "DATA FOR RandomArr10000" + "\n");
    getData(randomArr10000, 10);
    
    outputFile1.print("\n" + "\n" + "DATA FOR RandomArr100000" + "\n");
    outputFile2.print("\n" + "\n" + "DATA FOR RandomArr100000" + "\n");
    outputFile3.print("\n" + "\n" + "DATA FOR RandomArr100000" + "\n");
    getData(randomArr100000, 10);
    
    outputFile1.print("\n" + "\n" + "DATA FOR RandomArr1000000" + "\n");
    outputFile2.print("\n" + "\n" + "DATA FOR RandomArr1000000" + "\n");
    outputFile3.print("\n" + "\n" + "DATA FOR RandomArr1000000" + "\n");
    getData(randomArr1000000, 10);
  }
  
  //Runs the class. does not need args.
  public static void main(String args[]) throws IOException { 
    Report1 r = new Report1();
    r.allData();
  }
}
