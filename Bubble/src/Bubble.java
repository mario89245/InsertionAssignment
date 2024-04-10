import java.time.Duration;
import java.time.Instant;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Bubble
{
    public static long comparisons = 0;
    public static long swaps = 0;

    public static void main(String[] args)
    {
        //int size = 10;
        int maxSize = 1000000;

        for (int x = 0; x < 10; x++)
        {
            int[] numlist = new int[maxSize];
            String inputFile = "sort"+x+".txt";

            numlist = initialize(inputFile,maxSize);

            System.out.print("File"+x+",");
            //printlist(numlist,x);
            numlist = initialize(inputFile,maxSize);
            Instant start = Instant.now();
            bubblesort(numlist,maxSize);
            Instant finish = Instant.now();
            long elapsed = (long)Duration.between(start, finish).toMillis();
            numlist = initialize(inputFile,maxSize);

            System.out.printf("%d,",elapsed);

            start = Instant.now();
            insertionSort(numlist,maxSize);
            finish = Instant.now();
            elapsed = (long)Duration.between(start, finish).toMillis();

            System.out.printf("%d,",elapsed);
            numlist = initialize(inputFile,maxSize);

            start = Instant.now();
            selectionSort(numlist,maxSize);
            finish = Instant.now();
            elapsed = (long)Duration.between(start, finish).toMillis();

            System.out.printf("%d,",elapsed);

            //selectionSort(numlist,x);
            //insertionSort(numlist,x);
//			mergeSort(numlist,x);
//
//			finish = Instant.now();
//			elapsed = (long)Duration.between(start, finish).toMillis();
//			System.out.printf("FINAL LIST: %d Milliseconds\n",elapsed);

            //printlist(numlist,x);
            System.out.println();
        }
        // TODO Auto-generated method stub

    }

    public static int[] initialize(String infile, int maxSize)
    {
        int[] numbers = new int[maxSize];

        try
        {
            FileReader fr = new FileReader(infile);
            BufferedReader br = new BufferedReader(fr);

            for (int x = 0; x < maxSize; x++)
            {
                numbers[x] = Integer.parseInt(br.readLine());
            }
        }
        catch (IOException e)
        {
            System.out.println("File Error: " + infile);
        }

        return numbers;
    }

    private static void insertionSort(int[] array, int count)
    {
        long comparisons = 0;
        long swaps = 0;

        for(int i = 1; i < count; i++) {
            int temp = array[i];
            int j = i - 1;

            while(j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                swaps++;
                j--;
                comparisons++;
            }
            swaps++;
            array[j + 1] = temp;
        }
        System.out.print(comparisons + "," + swaps +",");

    }

    private static void selectionSort(int[] array, int count) {

        long comparisons = 0;
        long swaps = 0;

        for(int i = 0; i < count - 1; i++) {
            int min = i;
            for(int j = i + 1; j < count; j++) {
                comparisons++;
                if(array[min] > array[j]) {
                    min = j;
                }
            }

            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
            swaps++;
        }
        System.out.print(comparisons + "," + swaps +",");


    }

    public static int[] bubblesort(int[] numlist, int count)
    {
        long comparisons = 0;
        long swaps = 0;

        int temp = 0;
        for (int i = 0; i < count - 1;i++)
        {
            for (int j = 0; j < ((count - 1) - i);j++)
            {
                comparisons++;
                if (numlist[j] > numlist[j+1])
                {
                    swaps++;
                    temp = numlist[j+1];
                    numlist[j+1] = numlist[j];
                    numlist[j] = temp;
                }
            }

        }
        System.out.print(comparisons + "," + swaps +",");

        return numlist;
    }

    public static void printlist(int[] numlist, int count)
    {
        for (int i = 0; i < count; i++)
        {
            System.out.print(numlist[i] + " ");
        }
        System.out.println("");
    }

    private static void mergeSort(int[] array, int count)
    {
        int[] choppedArray = new int[count];
        for (int i = 0; i < count; i++)
        {
            choppedArray[i] = array[i];
        }
        swaps = 0;
        comparisons = 0;
        mergeSort(choppedArray);
        System.out.println("\nComparisons: " + comparisons + "\nSwaps: " + swaps);

    }

    private static void mergeSort(int[] array) {

        int length = array.length;
        if (length <= 1) return; //base case

        int middle = length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[length - middle];

        int i = 0; //left array
        int j = 0; //right array

        for(; i < length; i++) {
            if(i < middle) {
                leftArray[i] = array[i];
            }
            else {
                rightArray[j] = array[i];
                j++;
            }
        }
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, array);
    }

    private static void merge(int[] leftArray, int[] rightArray, int[] array) {

        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0, l = 0, r = 0; //indices

        //check the conditions for merging
        while(l < leftSize && r < rightSize) {
            comparisons++;
            if(leftArray[l] < rightArray[r]) {
                array[i] = leftArray[l];
                i++;
                l++;
            }
            else {
                array[i] = rightArray[r];
                swaps++;
                i++;
                r++;
            }
        }
        while(l < leftSize) {
            array[i] = leftArray[l];
            i++;
            l++;
        }
        while(r < rightSize) {
            array[i] = rightArray[r];
            i++;
            r++;
        }
    }

}
