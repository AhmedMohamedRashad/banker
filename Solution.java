package com.company;
import java.util.Scanner;

public class Solution
{

    public static void main(String[] args)
    {

        int n,m;
        Scanner input=new Scanner(System.in);
        System.out.println("Enter number of processes ");
        n=input.nextInt();
        System.out.println("Enter number of resources ");
        m=input.nextInt();
        int [] available=new int[m];	//the available amount of each resource
        int [][] maximum=new int[n][m];	//the maximum demand of each process
        int [][] allocation=new int[n][m];;	//the amount currently allocated to each process
        int [][] need=new int[n][m];	//the remaining needs of each process

        System.out.println("Enter the available amount of each resource");
        for (int i=0; i<m ; i++)
        {
            available[i]=input.nextInt();
        }



        System.out.println("Enter the amount currently allocated to each process");
        for(int i=0; i<n ;i++)
        {
            for(int j=0; j<m; j++)
            {
                allocation[i][j]=input.nextInt();
            }
            input.nextLine();
        }


        System.out.println("Enter the maximum demand of each process");
        for(int i=0; i<n ;i++)
        {
            for(int j=0; j<m; j++)
            {
                maximum[i][j]=input.nextInt();
            }
            input.nextLine();
        }

        for(int i=0; i<n ;i++)
        {
            for(int j=0; j<m; j++)
            {
                need[i][j]=maximum[i][j]-allocation[i][j];
            }
        }
        deadlock(n,m,available,allocation,need);
        while (true) {
            boolean flag=true;
            System.out.println("\n1-request\n2-release\n3-exit");
            int x = input.nextInt();
            if (x == 1) {
                int[] request = new int[m];
                System.out.println("Enter the number of process");
                int p = input.nextInt();
                System.out.println("Enter the request of each resource");
                for (int i = 0; i < m; i++) {
                    request[i] = input.nextInt();
                    if(request[i]>available[i] || request[i]>need[p][i])    // invalid input
                        flag=false;
                }
                if(flag)
                {
                    for(int i=0;i<m;i++)
                    {
                        available[i] -= request[i];
                        allocation[p][i] += request[i];
                        need[p][i] -= request[i];
                    }
                    deadlock(n, m, available, allocation, need);

                }
                else
                    System.out.println("!!! INVALID REQUEST");

            } else if (x == 2) {
                int[] release = new int[m];
                System.out.println("Enter the number of process");
                int p = input.nextInt();
                System.out.println("Enter the release of each resource");
                for (int i = 0; i < m; i++) {
                    release[i] = input.nextInt();
                    if(release[i]>allocation[p][i])
                        flag=false;
                    available[i] += release[i];
                    allocation[p][i] -= release[i];
                    need[p][i] += release[i];
                }
                if(flag)
                {
                    for (int i = 0; i < m; i++) {
                        available[i] += release[i];
                        allocation[p][i] -= release[i];
                        need[p][i] += release[i];
                    }
                    deadlock(n, m, available, allocation, need);
                }
                else
                    System.out.println("!!! INVALID RELEASE");

            } else
                System.exit(0);
        }



    }

public static void deadlock(int n, int m, int[] available, int[][] allocation, int[][] need)
{
    System.out.println("process  |  need");   // print matrix need
    for(int i=0;i<n;i++) {
        System.out.print("\nP" + i + "\t\t\t");
        for (int j = 0; j < m; j++)
            System.out.print( need[i][j]+" ");

    }
    boolean [] finish=new boolean[n];
    int [] work=new int [m];
    for(int i=0;i<m;i++)
        work[i]=available[i];
    String report="\n\nSAFE STATE\nprocess  |  work\n";
    boolean flag=true;

    while(flag) {
        flag=false;
        for (int i = 0; i < n; i++) {
            if (finish[i])
                continue;
            int k = 0;
            while (k < m && need[i][k] <= work[k]) {
                k++;
            }
            if (k == m)     //process execute
            {
                finish[i] = true;
                flag = true;
                report+="P" + i + "\t\t\t";
                for (int x = 0; x < m; x++) {
                    report+=work[x] + " ";
                    work[x] += allocation[i][x];
                }

                report+="\n";
            }

        }
        if(!flag)
            break;
    }
    for (int i=0;i<n;i++)
    {
        if(!finish[i])
        {
            System.out.println("\nUNSAFE STATE");
            return;
        }

    }
    System.out.print(report+"  \t\t\t");
    for(int x=0;x<m;x++)
    {
        System.out.print(work[x]+" ");

    }


}
}


/*
5
3
3 3 2

alloc
0 1 0
2 0 0
3 0 2
2 1 1
0 0 2

max
7 5 3
3 2 2
9 0 2
2 2 2
4 3 3
 */