import java.util.*;

class Edge {
    int src, dest, weight;
    Edge(int s, int d, int w){ src=s; dest=d; weight=w; }
}

public class CO3_KruskalMST_RoadNetwork {

    int find(int parent[], int i){
        if(parent[i]==i) return i;
        return parent[i]=find(parent,parent[i]);
    }

    void union(int parent[], int rank[], int x, int y){
        int xroot=find(parent,x);
        int yroot=find(parent,y);
        if(rank[xroot]<rank[yroot]) parent[xroot]=yroot;
        else if(rank[xroot]>rank[yroot]) parent[yroot]=xroot;
        else { parent[yroot]=xroot; rank[xroot]++; }
    }

    public static void main(String[] args){
        CO3_KruskalMST_RoadNetwork k = new CO3_KruskalMST_RoadNetwork();

        ArrayList<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0,1,4));
        edges.add(new Edge(0,2,3));
        edges.add(new Edge(1,2,1));
        edges.add(new Edge(1,3,2));
        edges.add(new Edge(2,3,4));

        Collections.sort(edges, Comparator.comparingInt(e->e.weight));

        int parent[] = new int[4];
        int rank[] = new int[4];
        for(int i=0;i<4;i++){ parent[i]=i; rank[i]=0; }

        System.out.println("------ KRUSKAL MST – ROAD NETWORK ------");

        int mstCost=0;
        for(Edge e : edges){
            int x=k.find(parent,e.src);
            int y=k.find(parent,e.dest);

            if(x!=y){
                System.out.println("Road Added: " + e.src + " → " + e.dest + "  | Distance: " + e.weight);
                System.out.println("---------------------------------------------");
                mstCost+=e.weight;
                k.union(parent,rank,x,y);
            }
        }

        System.out.println("Total Minimum Road Cost : "+mstCost);
    }
}