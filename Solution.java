import java.io.*;
import java.util.*;

public class Solution {
    public static int find(int[] arr, int k, int t){
        for(int i=k+1;i<k+3 && i<arr.length;i++){
            if(arr[i]==t)
                return i;
        }
            return -1;
    }

    public static void swap(int[] arr, int i, int j){
        int t=arr[i];
        arr[i]=arr[j];
        arr[j]=t;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int test=sc.nextInt();
        for (int i = 0; i <test ; i++) {
            int n=sc.nextInt();
            int arr[]=new int[n];
            int work[]=new int[n];
            int ans=0;
            for (int j=0;j<n;j++) {
                arr[j] = sc.nextInt();
                work[j]=j+1;
            }
            for (int j=0;j<n;j++) {
                if(arr[j]!=work[j]){
                    int index=find(work,j,arr[j]);
                    if(index==-1) {
                        ans = -1;
                        break;
                    }
                    else if(index-j>0){
                       // ans+=(index-j);
                        while (index>j){
                            ans++;
                            swap(work,index,index-1);
                            index--;
                        }
                    }
                }
            }
            if(ans==-1)
                System.out.println("Too chaotic ");
            else
                System.out.println(ans);
        }
    }
}