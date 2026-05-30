import java.util.*;

public class CO4_Dijkstra_DeliveryRoute {

    int minDistance(int dist[], boolean visited[]){
        int min=Integer.MAX_VALUE, index=-1;
        for(int i=0;i<dist.length;i++){
            if(!visited[i] && dist[i]<min){
                min=dist[i]; index=i;
            }
        }
        return index;
    }

    public static void main(String[] args){

        int graph[][] = {
                {0,4,0,0,8},
                {4,0,5,0,0},
                {0,5,0,7,0},
                {0,0,7,0,9},
                {8,0,0,9,0}
        };

        int n=5, source=0;

        CO4_Dijkstra_DeliveryRoute d = new CO4_Dijkstra_DeliveryRoute();
        int dist[]=new int[n];
        boolean visited[]=new boolean[n];

        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source]=0;

        for(int i=0;i<n-1;i++){
            int u=d.minDistance(dist,visited);
            visited[u]=true;

            for(int v=0;v<n;v++){
                if(!visited[v] && graph[u][v]!=0 && dist[u]!=Integer.MAX_VALUE &&
                        dist[u]+graph[u][v]<dist[v]){
                    dist[v]=dist[u]+graph[u][v];
                }
            }
        }

        System.out.println("------ DIJKSTRA – DELIVERY ROUTE ------");

        for(int i=0;i<n;i++){
            System.out.println("City : "+i+"   Shortest Distance : "+dist[i]);
            System.out.println("------------------------------------");
        }
    }
}