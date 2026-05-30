import java.util.*;

class Job {
    int start, end, profit;
    Job(int s,int e,int p){ start=s; end=e; profit=p; }
}

public class CO6_WeightedScheduling_DP {

    int lastNonConflict(Job jobs[], int i){
        for(int j=i-1;j>=0;j--)
            if(jobs[j].end <= jobs[i].start)
                return j;
        return -1;
    }

    public static void main(String[] args){

        Job jobs[] = {
                new Job(1,3,50),
                new Job(2,5,20),
                new Job(4,6,70),
                new Job(6,7,60)
        };

        Arrays.sort(jobs, Comparator.comparingInt(j->j.end));

        CO6_WeightedScheduling_DP ws = new CO6_WeightedScheduling_DP();
        int n=jobs.length;
        int dp[]=new int[n];

        dp[0]=jobs[0].profit;

        for(int i=1;i<n;i++){
            int include=jobs[i].profit;
            int l=ws.lastNonConflict(jobs,i);
            if(l!=-1) include+=dp[l];
            dp[i]=Math.max(include,dp[i-1]);
        }

        System.out.println("------ WEIGHTED JOB SCHEDULING ------");

        for(int i=0;i<n;i++){
            System.out.println("Job "+(i+1)+" : ("+jobs[i].start+" - "+jobs[i].end+")  Profit : "+jobs[i].profit);
            System.out.println("---------------------------------------");
        }

        System.out.println("Maximum Profit : "+dp[n-1]);
    }
}