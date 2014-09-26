Jiaxin He
Jxh604
April 22, 2014

*******************************************************************************
My report includes Jxh604 Report.pdf, Sorts Data.xlsx, Report1HeapSortData.txt, Report1MergeSortData.txt, Report1QuickSortData.txt, as well as Report1.java.
*******************************************************************************

This program contains sorting algorithms. Sorts.java includes the sorting methods for heap sort, quick sort, and merge sort. Sorting an integer array from smallest to greatest value. Report1.java creates the sorted, reversed, and random array for 1000, 10000, 100000 , and 1000000 elements and sorts and print out the time it took to sort them into the files Report1HeapSortData.txt, Report1MergeSortData.txt, Report1QuickSortData.txt. I then manually processed those data into excel and analyzed them. The excel includes the average and variance for every set of data, and the graphs are used in the Jxh604 Report.pdf. 

NOTE: ALL OF THE DATA USED IN THIS REPORT IS COLLECTED FROM Report1HeapSortData.txt, Report1MergeSortData.txt, Report1QuickSortData.txt, and Sorts Data.xlsx. I know that the data is reliable because the trends for the curves used in the analysis below has an R^2 value of 0.9999 which is very close to 1, meaning that the curve is reliable. Furthermore, in the excel file, below every set of collected data, I have included the average and the variance, to show that my data is reliable. 

To run Report1.java, simply hit run. 

To run Report2.java, make sure you give an input file as argument and hit run. For example: 

run Report2 Test.txt

this will create 3 files: jxh604HS.txt, jxh604MS.txt, and jxh604QS.txt. In the files, it will print out the sorted integers line by line, and the last line will print HSjxh604 =, MSjxh604 =, and QSjxh604 =, with the amount of time it took to run the sort.

The "toy" file that I tested is called "Test.txt", and the "toy" output is jxh604HS.txt, jxh604MS.txt, and jxh604QS.txt. These wills will simply be overwritten when the TA's test it.

The Jxh604 Report.pdf is the analysis the of data collected in Report1.java, and analyzes the different sorting algorithms, namely the heap sort, quick sort, and merge sort.

This is how I measured the time:

 start = System.nanoTime();
 Sorts.mergeSort(arr);
 end = System.nanoTime();
