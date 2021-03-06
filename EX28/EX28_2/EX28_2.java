//implementing Prim's algorithm using adjacency matrix

public class EX28_2
{
 public static void main(String[] args)
  {
   //測試用vertices 1
   String[] vertices = {"Seattle","San Francisco","Los Angeles",
                    "Denver","Kansas City","Chicago","Boston","New York",
                   "Atlanta","Miami","Dallas","Houston"};
   
   //測試用edge 1
   int[][] edges = {
       {0,1,807},{0,3,1331},{0,5,2097},
       {1,0,807},{1,2,381},{1,3,1267},
       {2,1,381},{2,3,1015},{2,4,1663},{2,10,1435},
       {3,0,1331},{3,2,1015},{3,4,599},{3,5,1003},
       {4,2,1663},{4,3,599},{4,5,533},{4,7,1260},{4,8,864},{4,10,496},
       {5,0,2097},{5,3,1003},{5,4,533},{5,6,983},{5,7,787},
       {6,5,983},{6,7,214},
       {7,4,1260},{7,5,787},{7,6,214},
       {8,4,864},{8,7,888},{8,9,661},{8,10,781},{8,11,810},
       {9,8,661},{9,11,1187},
       {10,2,1435},{10,4,496},{10,8,781},{10,11,239},
       {11,8,810},{11,9,1187},{11,10,239}
      };
    
   WeightedGraph graph1 = new WeightedGraph(edges,vertices);//創建graph1
   WeightedGraph.MST tree1 = graph1.getMinimumSpanningTree();//MST
   
   
   //顯示MST
   System.out.println("Total weight is "+tree1.getTotalWeight());   
   tree1.printTree();

   System.out.println();
    
   //測試用edge 2
   edges = new int[][]{
       {0,1,7},{0,3,9},
       {1,0,7},{1,2,9},{1,3,7},
       {2,1,9},{2,3,7},{2,4,7},
       {3,0,9},{3,1,7},{3,2,7},{3,4,9},
       {4,2,7},{4,3,9}
      }; 

   WeightedGraph graph2 = new WeightedGraph(edges,5);//創建graph2
   WeightedGraph.MST tree2 = graph2.getMinimumSpanningTree(1);//MST

   //顯示MST
   System.out.println("Tree2:Total weight is "+tree2.getTotalWeight());
   tree2.printTree();
  }
}
