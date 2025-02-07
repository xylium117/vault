import java.util.Arrays;
import java.util.Scanner;

public class PendulumSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a list of integers:");
        String input = sc.nextLine();
        String[] in = input.split(" ");
        int[] arr = new int[in.length];

        for (int i = 0; i < in.length; i++) 
            arr[i] = Integer.parseInt(in[i]);
        
        sort(arr);
    }

    private static void sort(int arr[]) { 
        int n = arr.length;
        Arrays.sort(arr); // or a defined method
       
        int[] res = new int[n]; 
        int mid = (n-1)/2; 
        
        int j = 1, i = 1; 
        res[mid] = arr[0]; 
        for (i = 1; i <= mid; i++) { 
            res[mid + i] = arr[j++]; 
            res[mid - i] = arr[j++]; 
        } 
       
        if (n % 2 == 0) 
            res[mid + i] = arr[j]; 
       
        System.out.println("Pendulum arrangement:"); 
        for (i = 0 ; i < n; i++) 
            System.out.print(res[i] + " "); 
        System.out.println(); 
    } 
}
