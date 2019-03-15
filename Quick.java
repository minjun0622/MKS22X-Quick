import java.util.* ;
import java.io.* ;

public class Quick {
  /*return the value that is the kth smallest value of the array.
  Use Dutch partition sort.
 */

 public static int quickselect(int [] data, int k){

   int pivot = data.length;
   int start = 0;
   int end = data.length - 1;

   while(pivot != k){
       pivot = partition(data, start, end);
       if(pivot > k){
           end = pivot - 1;
       }else if(pivot < k){
           start = pivot + 1;
       }
   }

   return data[pivot];
 }

 public static int partition (int [] data, int start, int end){
    Random random = new Random();
    int index = Math.abs(random.nextInt()) % (end - start + 1) + start;
    int value = data[index];
    int placeholder =0;
    int beg = start;

    data[index] = data[start];
    data[start] = value;
    start += 1;
    while (start < end){

      if (data[start] < value){
        start +=1;
      }
      if (data[start] > value){
        placeholder = data[end];
        data[end] = data[start];
        data[start] = placeholder;
        end -= 1;
      }
      if (data[start] == value){
        int rand = Math.abs(random.nextInt()) % 2;
        if(rand == 0){
          start +=1;
        }
        if(rand ==1){
          placeholder = data[end];
          data[end] = data[start];
          data[start] = placeholder;
          end -=1;
        }
      }
    }

    if(data[start] < value){
      data[beg] = data[start];
      data[start] = value;
      return start;
    }
    if(data[start] > value){
      data[beg] = data[start -1];
      data[start-1] = value;
      return start -1;
    }
    return start;
  }

/*Modify the array to be in increasing order.
 */
 public static void quicksort(int[] data) {
   if (data.length > 0){
     quicksortH(data, 0, data.length - 1);
   }
 }

 /*Modify the array such that:
  *1. Only the indices from start to end inclusive are considered in range
  *2. A random index from start to end inclusive is chosen, the corresponding
  *   element is designated the pivot element.
  *3. all elements in range that are smaller than the pivot element are placed before the pivot element.
  *4. all elements in range that are larger than the pivot element are placed after the pivot element.
  *@return the index of the final position of the pivot element.
  */
 private static void quicksortH(int[] data, int lo, int hi) {
   //The case when you don't want anything.
   if (lo >= hi) {
     return;
   }
   int pivot = partition(data, lo, hi);
   quicksortH(data, lo, pivot - 1);
   quicksortH(data, pivot + 1, hi);
 }

 public static void main(String[]args){
  System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
  int[]MAX_LIST = {1000000000,500,10};
  for(int MAX : MAX_LIST){
    for(int size = 31250; size < 2000001; size*=2){
      long qtime=0;
      long btime=0;
      //average of 5 sorts.
      for(int trial = 0 ; trial <=5; trial++){
        int []data1 = new int[size];
        int []data2 = new int[size];
        for(int i = 0; i < data1.length; i++){
          data1[i] = (int)(Math.random()*MAX);
          data2[i] = data1[i];
        }
        long t1,t2;
        t1 = System.currentTimeMillis();
        Quick.quicksort(data2);
        t2 = System.currentTimeMillis();
        qtime += t2 - t1;
        t1 = System.currentTimeMillis();
        Arrays.sort(data1);
        t2 = System.currentTimeMillis();
        btime+= t2 - t1;
        if(!Arrays.equals(data1,data2)){
          System.out.println("FAIL TO SORT!");
          System.exit(0);
        }
      }
      System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
    }
    System.out.println();
  }
}
}
