import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class adj{
    public int x;
    public int y;

    public adj(int f, int g){
        this.x=f;
        this.y=g;
    }
    @Override
    public String toString(){
        return x+" "+y;
    }
}

public class Solution {
    static int find(int x, int y, List<adj> que){
        for(int i=0;i<que.size();i++){
            if(x==que.get(i).x || x==que.get(i).y || y==que.get(i).x || x==que.get(i).x)
                return i;
        }
        return 0;
    }

    public static void main(String[] args)  {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int m = in.nextInt();
            ArrayList<Integer> q=new ArrayList<>();//q[i] contains the nuber of queries for i-th group
            int[] group=new int [n];//group[i]contains i-th person(i.e. i-th person is a member of group[i])
            List<adj> que=new ArrayList<>();
            for(int a1 = 0; a1 < m; a1++) {
                int x = in.nextInt();
                int y = in.nextInt();
                que.add(new adj(Math.min(x, y),Math.max(x, y)));
            }
            Collections.sort(que, new Comparator<adj>() {
                @Override
                public int compare(adj o1, adj o2) {
                    if(o1.x > o2.x )
                        return 1;
                    else if(o1.x < o2.x )
                        return -1;
                    else {
                        if(o1.y > o2.y )
                            return 1;
                        else
                            return -1;
                    }
                }
            });
           // que.sort((o1,o2) -> o1.x > o2.x ? 1: -1);
//            for(int i=0;i<que.size();i++){
            int x=que.get(0).x;
            int y=que.get(0).y;
            q.add(1);
            group[x-1]=q.size();
            group[y-1]=q.size();
            que.remove(0);
            while(!que.isEmpty()){
                int i=find(x,y,que);
                x=que.get(i).x;
                y=que.get(i).y;
                if(group[x-1]!=0){
                    group[y-1]=group[x-1];
                    q.set(group[y-1]-1,q.get( group[y-1]-1)+1);
                }
                else if(group[y-1]!=0){
                    group[x-1]=group[y-1];
                    q.set( group[y-1]-1,q.get( group[y-1]-1)+1);
                }
                else{
                    q.add(1);
                    group[x-1]=q.size();
                    group[y-1]=q.size();
                }
                que.remove(i);
            }
            //*************************************************
           int peop_gr[]=new int[q.size()];//peop_gr[i] contains the number of people in i-th group
            for(int i=0;i<group.length;i++){
                if(group[i]!=0)
                     peop_gr[group[i]-1]++;
            }
            long created_sum=0;
            long total=0, otherQ=0;
//            BigInteger created_sum=BigInteger.ZERO;
//            BigInteger total=BigInteger.ZERO;
//            BigInteger otherQ=BigInteger.ZERO;
            for(int i=0;i<q.size();i++){
                if(q.get(i)>peop_gr[i]-1)
                   otherQ+=q.get(i)-peop_gr[i]+1;//now we have the queries for each group which don`t add new people
               // otherQ=otherQ.add(BigInteger.valueOf(q.get(i)-peop_gr[i]+1));
            }
            Arrays.sort(peop_gr);
            for(int i=peop_gr.length-1;i>=0;i--){
                for(int j=1;j<peop_gr[i];j++){
                    total+=(j*(j+1)+created_sum);
//                    total=total.add(BigInteger.valueOf(j*(j+1)));
//                    total=total.add(created_sum);
                }
               created_sum+=(peop_gr[i]*(peop_gr[i]-1));
                //created_sum=created_sum.add(BigInteger.valueOf(peop_gr[i]*(peop_gr[i]-1)));
                //System.out.println(total);
            }
            if(otherQ!=0)
                 total+=created_sum*otherQ;
                   // total=total.add(created_sum.multiply(otherQ));
          //  for(int i:peop_gr)
              System.out.println(total);
            q.clear();
            //que.clear();
       // que.stream().forEach(System.out::println);
       //    Arrays.stream(peop_gr).forEach(System.out::println);

        }
    }
}