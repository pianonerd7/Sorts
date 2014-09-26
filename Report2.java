import java.io.*;

/**Report2 takes a text file, scans and read all numbers, sorts the numbers, and creates 3 
  * new txt files and prints the sorted numbers from small to greatest line by line, with the 
  * time it took to sort the numbers in nanoseconds on the bottom.
  * @author Jiaxin He
  */
public class Report2 {
  
  //creates new printstreams to print data to the text files.
  PrintStream outputFile1;
  PrintStream outputFile2;
  PrintStream outputFile3;
  
  //initialize the printstream.
  public Report2() throws IOException {
    outputFile1 = new PrintStream("jxh604HS.txt");
    outputFile2 = new PrintStream("jxh604QS.txt");
    outputFile3 = new PrintStream("jxh604MS.txt");  
  }
  
  //Scans the file and saves it into a string.
  public String scanner (String fileName) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    try {
      StringBuilder builder = new StringBuilder();
      String everyLine = reader.readLine();
      
      while (everyLine != null) {
        builder.append(everyLine + "\n");
        everyLine = reader.readLine();
      }
      return builder.toString();
    }
    finally {
      reader.close();
    }
  }
  
  //Split the word at delimiters and saves it into a string array.
  public String[] splitWords(String text) {
    text = text.toLowerCase();
    return text.split("\\W");
  }
  
  //Converts the string array to an int array.
  public int[] stringToInt(String[] arr) {
    int[] data = new int[arr.length];
    
    for (int i = 0; i < arr.length; i++) {
      data[i] = Integer.parseInt(arr[i]);
    }
    return data;
  }
  
  //prints the sorted array out to the text files, one integer per line.
  public int[] getData(int[] arr) throws IOException {
    int[] heapSort = new int[3];
    int[] quickSort = new int[3];
    int[] mergeSort = new int[3];
    int[] finalData = new int[3];
    int[] hs = arr;
    int[] qs = arr;
    int[] ms = arr;
    long start;
    long end;
    double temp;
    
    //measures time of the sort, saves it into corresponding array.
    for (int i = 0; i<3; i++) {
      start = System.nanoTime();
      Sorts.heapSort(arr);
      end = System.nanoTime();
      temp = (double) end-start;
      heapSort[i]=(int) temp;
    }
    
    //prints out the sorted array in order from least to greatest line by line.
    for(int i = 0; i<hs.length; i++) {
      outputFile1.print(hs[i] + "\n");
    }
    outputFile1.print("\n");
    
    //measures time of the sort, saves it into corresponding array.
    for (int i = 0; i<3; i++) {
      start = System.nanoTime();
      Sorts.quickSort(arr);
      end = System.nanoTime();
      temp = (double) end-start;
      quickSort[i]=(int) temp;
    }
    
    //prints out the sorted array in order from least to greatest line by line.
    for(int i = 0; i<qs.length; i++) {
      outputFile2.print(qs[i] + "\n");
    }
    outputFile2.print("\n");
    
    //measures time of the sort, saves it into corresponding array.
    for (int i = 0; i<3; i++) {
      start = System.nanoTime();
      Sorts.mergeSort(arr);
      end = System.nanoTime();
      temp = (double) end-start;
      mergeSort[i]=(int) temp;
    }
    
    //prints out the sorted array in order from least to greatest line by line.
    for(int i = 0; i<ms.length; i++) {
      outputFile3.print(ms[i] + "\n");
    }
    outputFile3.print("\n");
    
    Sorts.quickSort(heapSort);
    Sorts.quickSort(quickSort);
    Sorts.quickSort(mergeSort);
    
    //saves the median of the sorts and saves it into final data.
    finalData[0]=heapSort[1];
    finalData[1]=quickSort[1];
    finalData[2]=mergeSort[1];
    
    return finalData;
  }
  
  //prints final data out to the text files.
  public void print(String inputFileName) throws IOException {
    
    String text = scanner(inputFileName);
    String[] arr = splitWords(text);
    int[] data = stringToInt(arr);
    int[] finalData = getData(data);
    
    //final data from getData method stores the data to print out.
    outputFile1.print("HSjxh604 = " + finalData[0]);
    outputFile2.print("QSjxh604 = " + finalData[1]);
    outputFile3.print("MSjxh604 = " + finalData[2]);
  }
  
  //main method to run program. Need to input a file that is saved in the same folder.
  public static void main(String[] args) throws IOException {
    Report2 r = new Report2();
    String inputFileName = args[0];
    r.print(inputFileName);
  }
}