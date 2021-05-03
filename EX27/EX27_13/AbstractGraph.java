//Abstract Graph class

import java.util.*;

public abstract class AbstractGraph implements Graph
{
 protected Object[] vertices;//點陣列
 protected List<Integer>[] neighbors;//相鄰關係陣列

 //contructor
 //輸入陣列
 protected AbstractGraph(int[][] edges, Object[] vertices)
  {
   this.vertices = vertices;
   createAdjacencyLists(edges,vertices.length);//將邊陣列轉為相鄰關係陣列
  }

 //contructor
 //輸入list
 protected AbstractGraph(List<Edge> edges, List vertices)
  {
   this.vertices = vertices.toArray();//將list轉為陣列
   createAdjacencyLists(edges,vertices.size());//將edge list轉為相鄰陣列
  }

 //contructor
 //照順序創出vertex
 protected AbstractGraph(List<Edge> edges,int numberOfVertices)
  {
   //依照順序創建vertex物件
   vertices = new Integer[numberOfVertices];
   for(int i=0;i<numberOfVertices;i++)
    vertices[i] = new Integer(i);

   createAdjacencyLists(edges,numberOfVertices);//將edge list轉為相鄰陣列
  }

 //contructor
 //輸入邊陣列
 //照順序創出vertex
 protected AbstractGraph(int[][] edges, int numberOfVertices)
  {
   //依照順序創建vertex物件
   vertices = new Integer[numberOfVertices];
   for(int i=0;i<numberOfVertices;i++)
    vertices[i] = new Integer(i);

   createAdjacencyLists(edges,numberOfVertices);//將邊陣列轉為相鄰關係陣列
  }

 //創出相鄰關係陣列
 //載入邊陣列
 private void createAdjacencyLists(int[][] edges,int numberOfVertices)
  {
   neighbors = new LinkedList[numberOfVertices];//依vertex數決定陣列長度

   //創建linked list
   for(int i=0;i<numberOfVertices;i++)
    neighbors[i] = new java.util.LinkedList<Integer>();
   
   //依序加入邊關係
   for(int i=0;i<edges.length;i++)
    {
     int u = edges[i][0];
     int v = edges[i][1];
     neighbors[u].add(v);
    }
  }

 //創出相鄰關係陣列
 //載入edge list
 private void createAdjacencyLists(List<Edge> edges,int numberOfVertices)
  {
   neighbors = new LinkedList[numberOfVertices];//依vertex數決定陣列長度

   //創建linked list
   for(int i=0;i<numberOfVertices;i++)
    neighbors[i] = new java.util.LinkedList<Integer>();
   
   //依序加入邊關係
   for(Edge edge: edges)
    neighbors[edge.u].add(edge.v);
  }

 //取得大小(vertex數)
 public int getSize()
  {
   return vertices.length;
  }

 //取得vertex陣列
 public Object[] getVertices()
  {
   return vertices;
  }

 //取得指定index的veertex
 public Object getVertex(int v)
  {
   return vertices[v];
  }

 //取得指定vertex的index
 public int getIndex(Object vertex)
  {
   //迴圈尋訪
   for(int i=0;i<getSize();i++)
    {
     //尋找到相同vertex時回傳index
     if(vertex.equals(vertices[i]))
      return i;
    }

   return -1;//沒找到回傳-1
  }

 //取得指定index的vertex的相鄰關係list
 public java.util.List getNeighbors(int v)
  {
   return neighbors[v];
  }

 //取得指定index的vertex的分支度
 public int getDegree(int v)
  {
   return neighbors[v].size();//回傳相鄰邊數
  }

 //取得相鄰關係陣列
 public int[][] getAdjacencyMatrix()
  {
   int[][] adjacencyMatrix = new int[getSize()][getSize()];//宣告陣列
   
   //迴圈將有相鄰的第i點和第j點的陣列設為1
   for(int i=0;i<neighbors.length;i++)
    for(int j=0;j<neighbors[i].size();j++)
     {
      int v = neighbors[i].get(j);
      adjacencyMatrix[i][v] = 1;
     }

   return adjacencyMatrix;//回傳陣列
  }

 //取得最大導出子圖
 public static Graph maxInducedSubgraph(Graph graph,int k)
  {
   Object[] subVerticesArray = graph.getVertices();//子圖的vertex陣列
   List<Edge> subedges = new LinkedList<Edge>();//子圖的edge
   List<Object> subVertices = new LinkedList<Object>();//子圖的vertices list 創建物件用

   //複製原圖的邊關係
   for(int i=0;i<subVerticesArray.length;i++)
    {
     List subNeighbor = graph.getNeighbors(i);
     for(int j=0;j<subNeighbor.size();j++)
      {
       Edge edge = new Edge(i,(Integer)(subNeighbor.get(j)));
       subedges.add(edge);
      }
    }

   //將陣列轉為list
   for(int i=0;i<subVerticesArray.length;i++)
    subVertices.add(subVerticesArray[i]);
    
   Graph subGraph = new UnweightedGraph(subedges,subVertices);//創建子圖

   //迴圈刪除子圖中分支度小於K的vertex
   //直到所有vertex都大於等於k
   while(true)
    {
     Object[] subVertice = subGraph.getVertices();//子圖的vettices
     boolean isSubgraph = true;//判斷是否通過分支度>=k

     //尋訪每一vertex
     for(int i=0;i<subVertice.length;i++)
      {
       //判斷分支度
       if(subGraph.getDegree(i)<k)
        {
         isSubgraph = false;
         subGraph.remove(subVertice[i]);//移除小於的vertex
         break;
        }
      }

     if(subGraph.getSize()==0)//若不存在vertex 回傳null
      return null;

     //若通過檢查 跳出迴圈     
     if(isSubgraph)
      break;
    }

   return subGraph;//回傳
  }

 //加入vertex
 public boolean add(Object vertex)
  {
   //尋訪確定沒有相同的verex
   for(int i=0;i<vertices.length;i++)
    if(vertex.equals(vertices[i]))
     return false;//若有 回傳true

   Object[] newVertices = new Object[vertices.length+1];//設定新vertex陣列

   //複製舊vertex陣列   
   for(int i=0;i<vertices.length;i++)
    newVertices[i] = vertices[i];

   newVertices[vertices.length] = vertex;//加入新vertex

   vertices = new Object[newVertices.length];//重新設定原vertex陣列
   
   //複製新vertex陣列
   for(int i=0;i<vertices.length;i++)
    vertices[i] = newVertices[i];

   List<Integer>[] newNeighbors = new LinkedList[neighbors.length+1];//設定新neighbor陣列

   //複製舊neighbor陣列
   for(int i=0;i<neighbors.length;i++)
    newNeighbors[i] = neighbors[i];

   neighbors = new LinkedList[newNeighbors.length];//重新設定原neighbor陣列

   //複製新neighbor陣列
   for(int i=0;i<neighbors.length;i++)
    neighbors[i] = newNeighbors[i];

   neighbors[neighbors.length-1] = new java.util.LinkedList<Integer>();//創建新list

   return true;//回傳
  }

 //移除vertex
 public boolean remove(Object vertex)
  {
   int findindex = getIndex(vertex);//尋找指定vertex

   //若找不到 回傳false
   if(findindex==-1)
    return false;

   //首先刪除關聯edge
   neighbors[findindex].clear();

   //移除index以後的往前挪
   for(int i=findindex;i<neighbors.length-1;i++)
    neighbors[i] = neighbors[i+1];

   //尋訪所有edge 將有移除vertex的edge刪除
   for(int i=0;i<neighbors.length-1;i++)
    {
     neighbors[i].remove((Integer)findindex);//用list的remove移除
     //因為移除vertex 之後vertex的index要-1
     for(int j=0;j<neighbors[i].size();j++)
      {
       int num = (int)(neighbors[i].get(j));
       if(num>findindex)
        {
         neighbors[i].set(j,(Integer)(num-1));
        }
      }
    }

   //設定新neighbor陣列
   List<Integer>[] newNeighbors = new LinkedList[neighbors.length-1];
   
   //複製新neighbor陣列
   for(int i=0;i<neighbors.length-1;i++)
    newNeighbors[i] = neighbors[i];

   neighbors = new LinkedList[newNeighbors.length];//重新設定原neighbor陣列

   //複製新neighbor陣列
   for(int i=0;i<neighbors.length;i++)
    neighbors[i] = newNeighbors[i];

   
   //再來刪除vertex
   //將陣列中指定vertex後面的index往前移
   for(int i=findindex;i<vertices.length-1;i++)
    vertices[i] = vertices[i+1];

   Object[] newVertices = new Object[vertices.length-1];//設定新vertex陣列
   
   //複製舊vertex陣列
   for(int i=0;i<newVertices.length;i++)
    newVertices[i] = vertices[i];

   vertices = new Object[newVertices.length];//重新設定原vertex陣列
   
   //複製新vertex陣列
   for(int i=0;i<vertices.length;i++)
    vertices[i] = newVertices[i];

   return true;//回傳true
  }

 //加入edge
 public boolean add(Edge edge)
  {
   int u = edge.u;//起始vertex
   int v = edge.v;//終點vertex

   //若其中一點不在graph中
   if(u>=vertices.length||v>=vertices.length)
    return false;//回傳false

   //確認edge不存在
   if(neighbors[u].indexOf((Integer)v)!=-1 || neighbors[v].indexOf((Integer)u)!=-1)
    return false;//若有 回傳false

   //兩個vertex加入edge
   neighbors[u].add(v);
   neighbors[v].add(u);

   return true;
  }

 //移除edge
 public boolean remove(Edge edge)
  {
   int u = edge.u;//起始vertex
   int v = edge.v;//終點vertex

   //若至少一點index超出範圍
   if(u>=vertices.length||v>=vertices.length)
    return false;//回傳false

   //確認edge是否存在
   if(neighbors[u].indexOf((Integer)v)==-1 || neighbors[v].indexOf((Integer)u)==-1)
    return false;//回傳false

   //移除edge
   neighbors[u].remove((Integer)v);
   neighbors[v].remove((Integer)u);

   return true;
  }

 //顯示相鄰關係陣列
 public void printAdjacencyMatrix()
  {
   int[][] adjacencyMatrix = getAdjacencyMatrix();//呼叫method取得陣列
   
   //顯示
   for(int i=0;i<adjacencyMatrix.length;i++)
    {
     for(int j=0;j<adjacencyMatrix[0].length;j++)
      System.out.print(adjacencyMatrix[i][j]+" ");
     
     System.out.println();
    } 
  }

 //顯示連接vertex的邊
 public void printEdges()
  {
   //相鄰陣列迴圈
   for(int u=0;u<neighbors.length;u++)
    {
     //起點
     System.out.print("Vertex "+u+": ");
     for(int j=0;j<neighbors[u].size();j++)
      {
       System.out.print("("+u+", "+neighbors[u].get(j)+") ");//終點
      }
     
     System.out.println();
    }
  }

 //depth first search
 public Tree dfs(int v)
  {
   List<Integer> searchOrders = new ArrayList<Integer>();//走訪順序list
   int[] parent = new int[vertices.length];//每個vertex的parent
   
   //parent預設為-1
   for(int i=0;i<parent.length;i++)
    parent[i] = -1;

   boolean[] isVisited = new boolean[vertices.length];//判斷vertex是否走訪過
   
   dfs(v,parent,searchOrders,isVisited);//呼叫recursion method

   return new Tree(v,parent,searchOrders);//創建tree並回傳
  }

 //dfs helper method
 public void dfs(int v,int[] parent,List<Integer> searchOrders,boolean[] isVisited)
  {
   searchOrders.add(v);//將v加入走訪list
   isVisited[v] = true;//走訪的index設為true
   
   //將相連vertex中 未走訪過之vertex做遞迴
   for(int i:neighbors[v])
    if(!isVisited[i])
     {
      parent[i] = v;
      dfs(i,parent,searchOrders,isVisited);
     }
  }

 //breadth first search 
 public Tree bfs(int v)
  {
   List<Integer> searchOrders = new ArrayList<Integer>();//走訪順序list
   int[] parent = new int[vertices.length];//每個vertex的parent

   //parent預設為-1
   for(int i=0;i<parent.length;i++)
    parent[i] = -1;

   java.util.LinkedList<Integer> queue = new java.util.LinkedList<Integer>();//儲存vertex佇列
   boolean[] isVisited = new boolean[vertices.length];//判斷是否已走訪
   queue.offer(v);//放入指定index作為root

   isVisited[v] = true;
   
   //以queue先進先出特性 可走訪同一level的子vertex
   //而不會有將兄弟vertex設為parent的情形
   while(!queue.isEmpty())
    {
     //取出index並放入list
     int u = queue.poll();
     searchOrders.add(u);
     
     //將相連vertex放入queue
     //同一level會先走訪   
     for(int w:neighbors[u])
      if(!isVisited[w])
       {
        queue.offer(w);
        parent[w] = u;
        isVisited[w] = true;
       }
    }

   return new Tree(v,parent,searchOrders);//回傳spinner tree
  }

 //取得Hamiltonian Path(每一點都通過且只通過一次的路徑)
 public List<Integer> getHamiltonianPath(Object vertex)
  {
   return getHamiltonianPath(getIndex(vertex));//呼叫helper method
  }

 //Hamiltonian Path helper method
 public List<Integer> getHamiltonianPath(int v)
  {
   int[] next = new int[getSize()];//第i vertex的下一個vertex

   //next全部預設為-1
   for(int i=0;i<next.length;i++)
    next[i] = -1;

   boolean[] isVisited = new boolean[getSize()];//判斷是否已走訪

   List<Integer> result = null;//回傳list 代表路徑

   //根據分支度重新排序相鄰關係順序
   for(int i=0;i<getSize();i++)
    reorderNeighborsBasedOnDegree(neighbors[i]);

   //判斷是否存在 Hamiltonian Path  
   if(getHamiltonianPath(v,next,isVisited))
    {
     //若存在則利用next陣列將vertex依序放入list
     result = new ArrayList<Integer>();
     int vertex = v;
     while(vertex != -1)
      {
       result.add(vertex);
       vertex = next[vertex];
      }
    }
   
   return result;//回傳
  }

 //根據分支度重新排序相鄰關係順序 由小到大
 public void reorderNeighborsBasedOnDegree(List<Integer> list)
  {
   //按照分支度大小以select sort排序
   for(int i=list.size()-1;i>=1;i--)
    {
     //預設index 0為最大
     int currentMaxDegree = getDegree(list.get(0));//預設最大分支度
     int currentMaxIndex = 0;
     
     //到i為止找出最大index 
     for(int j=1;j<=i;j++)
      {
       if(currentMaxDegree < getDegree(list.get(j)))
        {
         currentMaxDegree = getDegree(list.get(j));
         currentMaxIndex = j;
        }
      }
     
     //若最大的index不為i 交換
     if(currentMaxIndex != i)
      {
       int temp = list.get(currentMaxIndex);
       list.set(currentMaxIndex,list.get(i));
       list.set(i,temp);
      }
    }
  }

 //判斷是否全部走訪
 private boolean allVisited(boolean[] isVisited)
  {
   boolean result = true;//判斷結果

   for(int i=0;i<getSize();i++)
    result = result && isVisited[i];//若有至少一個false 必為false 

   return result;//回傳
  }

 //判斷是否存在Hamiltonian Path
 private boolean getHamiltonianPath(int v,int[] next,boolean[] isVisited)
  {
   isVisited[v] = true;//目前vertex走訪設為true
   
   //若全部已走訪 回傳true
   if(allVisited(isVisited))
    return true;

   //將鄰居vertex做下一次遞迴
   for(int i=0;i<neighbors[v].size();i++)
    {
     int u = neighbors[v].get(i);

     //若遞迴結果為true 回傳true
     if(!isVisited[u] && getHamiltonianPath(u,next,isVisited))
      {
       next[v] = u;
       return true;
      }
    }

   //若全部鄰居無結果 將目前vertex走訪設回false
   isVisited[v] = false;
   return false;//回傳false
  }

 //spinner tree
 public class Tree
  {
   private int root;//根vertex
   private int[] parent;//每個vertex的parent
   private List<Integer> searchOrders;//走訪順序

   //contructor
   //載入走訪順序list
   public Tree(int root,int[] parent,List<Integer> searchOrders)
    {
     this.root = root;
     this.parent = parent;
     this.searchOrders = searchOrders;
    }

   //contructor
   public Tree(int root,int[] parent)
    {
     this.root = root;
     this.parent = parent;
    }

   //取得root vertex
   public int getRoot()
    {
     return root;
    }

   //取得指定vertex的parent
   public int getParent(int v)
    {
     return parent[v];
    }

   //回傳走訪順序
   public List<Integer> getSearchOrders()
    {
     return searchOrders;
    }

   //走訪的vertex數量
   public int getNumberOfVerticesFound()
    {
     return searchOrders.size();
    }

   //回傳 Iterator
   public java.util.Iterator pathIterator(int v)
    {
     return new PathIterator(v);
    }

   //Iterator class
   public class PathIterator implements java.util.Iterator
    {
     private Stack<Integer> stack;//儲存stack
     
     //contructor
     public PathIterator(int v)
      {
       stack = new Stack<Integer>();//創建stack
       do{
        stack.add(v);//放入vertex
        v = parent[v];//新vertex設為parent
       }
       while(v != -1);
      }

     //判斷是否有下一個vertex
     public boolean hasNext()
      {
       return !stack.isEmpty();
      }

     //回傳目前vertex
     public Object next()
      {
       return vertices[stack.pop()];
      }

     //移除 不實作
     public void remove()
      {}
    }

   //顯示路徑 從root到v
   public void printPath(int v)
    {
     Iterator iterator = pathIterator(v);

     //root to v
     System.out.print("A path from "+vertices[root]+" to "+vertices[v]+": ");
     
     //迴圈顯示
     while(iterator.hasNext())
      {
       System.out.print(iterator.next()+" ");
      }
    }

   //顯示tree整體
   public void printTree()
    {
     System.out.println("Root is: "+vertices[root]);
     System.out.print("Edges: ");
     
     //依序顯示連接邊
     for(int i=0;i<parent.length;i++)
      {
       if(parent[i] !=-1)
        System.out.print("("+vertices[parent[i]]+", "+vertices[i]+") ");
      }
     
     System.out.println();
    }
  }   
}