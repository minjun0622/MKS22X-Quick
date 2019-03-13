public class Quick {
  /*return the value that is the kth smallest value of the array.
  Use Dutch partition sort.
 */

  public static int quickSelect(int[] data, int k) {
    int[][] dataH = partition(data, 0, data.length-1);
    int max = data.length-1;
    int min = 0;
    while (dataH[1][0] != k) {
      if (k < dataH[1][0]) {
        max = dataH[1][0] - 1;
        dataH = partition(dataH[0], min, dataH[1][0]-1);
      }
      else {
        min = dataH[1][0]+1;
        dataH = partition(dataH[0], dataH[1][0]+1, max);
      }
    }
    return dataH[0][k];
  }
}

/*return the value that is the kth smallest value of the array. k=0 is the smallest
 */
 public static int quickselect(int[] data, int k) {
   if (k = 0) {
     return data[0];
   }
   return quickselectH(data, int k);
 }

 public static int quickselectH(int[] data, int k) {
   return partition(data, k);

 }

/*Modify the array to be in increasing order.
 */
 public static void quicksort(int[] data) {
   return quicksortH(data, 0, data.length);
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
   return quicksortH(data, lo, hi - 1); //hold up, which one am I returning?
   return quicksortH(data, pivot + 1, hi);
 }
}
