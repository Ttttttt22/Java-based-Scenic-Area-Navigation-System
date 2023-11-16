package com.example.t; /**
 * Created with IntelliJ IDEA.
 *
 * @Author: Kuang Wentao
 * @Date: 2022/11/29/23:45
 * @Description:
 */

import java.util.Arrays;
import java.util.Vector;

public class adjMatrix {
    protected int verNum;       //图的顶点数
    protected int edgeNum;      //图的边数
    protected boolean [] visited;        //访问标志数组?
    private String [] vertices;        //记录顶点的数组
    private int [][] edges;     //邻接矩阵
    private int noEdge;     // 无边标志

    public int getVerNum() {
        return verNum;
    }

    public int getEdgeNum() {
        return edgeNum;
    }

    public int getNoEdge() {
        return noEdge;
    }

    public String getVertices(int n) {
        return vertices[n];
    }

    public Vector<TriTuple> getTri(){
        Vector result = new Vector<TriTuple>();
        for(int i = 0; i < verNum; i ++)
            for(int j = 0; j < verNum; j ++)
                if(edges[i][j] != noEdge)
                    result.add((new TriTuple(i, j, edges[i][j])));
        return result;
    }

    /*private void dfs(int start) {

    }       //从start顶点出发深度遍历图*/

    private void dfs(int pos, int [][] map, int [] stack, int [] v, int top, int end, int n, int m, Vector<Vector> result){
        int i;
        if(pos == end){
            Vector temp = new Vector();
            for(i = 0; i < top; i ++){
                //System.out.print(stack[i] + "->");
                temp.add(stack[i]);
            }       //end之前所有的输出与入vector

            int distance = 0;
            for(i  = 0; i + 1 < top;i ++)
                distance += edges[stack[i]][stack[i + 1]];
            distance += edges[stack[top - 1]][end];

            //System.out.println(end + " distance: " + distance);
            temp.add(end);
            temp.add(0, distance);      //end和distance的输出与入vector

            /*//--------
            System.out.print("tempvector: ");
            for( i = 0; i < temp.size(); i ++)
                System.out.print(temp.get(i) + " ");
            System.out.println();
            //--------调试代码*/
            result.add(temp);
            return;
        }
        v[pos] = 1;     //标记访问过
        stack[top ++] = pos;        //经过的路径加入队列

        for(i = 0; i < n; i ++){
            if (v[i] != 1 && map[pos][i] != noEdge)      //注意
                dfs(i, map, stack, v, top ,end, n, m, result);
        }
        v[pos] = 0;     //删除标记
        top --;     //队列里面删除b
    }

    /*基于dfs输出从from到to的所有路径以Vector<Vector>(二维动态数组)做容器返回
    *
    *   Vector<Vector> result 为从from到to的所有路径的集合
    *   result[x][0] 设定为第x种可行路径的总距离
    *   result[x][y] 意为第x种可行路径中, 第y - 1个景点的编号
    *
    *
    * */
    public Vector<Vector> all(int from, int to){
        Vector<Vector> result = new Vector<Vector>();
        int distance = 0;
        int [] stack = new int[(int) (verNum + 0.2 * verNum)];
        int [] v = new int[verNum];
        int top = 0;
        dfs(from, edges, stack, v, top, to, verNum, edgeNum, result);
        return result;
    }

    public Vector<Vector> shortestByDfs(int from, int to){
        Vector<Vector> temp;
        temp = all(from, to);
        int min = Integer.parseInt(temp.get(0).get(0).toString());
        int minId = 0;

        Vector<Vector> result = new Vector<Vector>();

        for(int i = 0; i < temp.size(); i ++)
            if(Integer.parseInt(temp.get(i).get(0).toString()) < min){
                min = Integer.parseInt(temp.get(i).get(0).toString());
                minId = i;
            }

        for(int i = 0; i < temp .size(); i ++)
            if(Integer.parseInt(temp.get(i).get(0).toString()) == min){
                result.add(new Vector(temp.get(i)));
            }


        /*//--------

        //--------调试代码*/
        return result;
    }


    public static void main(String[] args) {
        ReadTxt rt = new ReadTxt();
        adjMatrix sdj = rt.readTxtAdjMatrix("dfsTestData.txt");
        //adjMatrix sdj = rt.readTxtAdjMatrix("result1.txt");
        sdj.printGraph();
        sdj.all(0, 2);
        sdj.shortestByDfs(0, 2);
    }

    public adjMatrix(int size, int noEdgeFlag){
        verNum = size;
        edgeNum = 0;
        noEdge = noEdgeFlag;
        vertices =new String[verNum];      //顶点向量
        edges = new int[verNum][verNum];        //邻接矩阵

        for(int i = 0; i < verNum; i ++)
            for(int j = 0; j < verNum; j++)
                edges[i][j] = noEdgeFlag;       //领接矩阵初始化为noEdgeFlag

        visited = new boolean[verNum];      //访问标志数组的初始化
    }       //构造函数

    //创建图
    public void createGraph(String[] V, Vector<TriTuple>E){
        int i;
        for(i = 0; i < verNum; i++)
            vertices[i] = V[i];
        i = 0;
        while(!(0 == E.get(i).from && 0 == E.get(i).to && 0 == E.get(i).length))//      注意三元组要以 0 0 0结尾否则数组超限
        {
            insertEdge(E.get(i).from, E.get(i).to, E.get(i).length);
            //insertEdge(E.get(i).to, E.get(i).from, E.get(i).length);//直接构造无向图注意！！！！！！！！！！！！！！！！
            i ++;
        }
        //System.out.println("插入函数运行了" + i + "次");      //调试代码
    }

    //输出图
    public void printGraph(){
        int i, j;
        System.out.println("printEdges:");
        for(i = 0; i < verNum; i ++){
            System.out.print(vertices[i] + ": ");
            for(j = 0; j < verNum; j ++)
                System.out.print(edges[i][j] + " ");
            System.out.println();
        }
        System.out.println();

    }

    //插入一条边
    public boolean insertEdge(int from, int to, int length){
        if(from < 0 || from >verNum -1 || to < 0 || to > verNum - 1)///////
            return false;       //下标越界
        if(edges[from][to] == length)
            return false;       //已经存在from到to且权值为length的边
        if(edges[from][to] != noEdge)
            return false;
        if(edges[from][to] == noEdge)       //从from到to原来没有边
            ++edgeNum;      //边数增大
        edges[from][to] = length;       //设权值为length
        System.out.println("成功插入：" + from + " " + to + " " + length);     //调试代码
        return true;
    }

    //删除一条边
    public boolean removeEdge(int from, int to){
        if(from < 0 || from >verNum -1 || to < 0 || to > verNum - 1)
            return false;       //下标越界
        if(edges[from][to] == noEdge)
            return false;       //边不存在
        edges[from][to] =noEdge;        //重设无边条件
        --edgeNum;      //边数减少
        return true;
    }

    //查找图中是否存在from到to的边
    public boolean searchEdge(int from, int to){
        if(from < 0 || from >verNum -1 || to < 0 || to > verNum - 1)
        return false;       //下标越界
        if(edges[from][to] == noEdge) return false;
        else return true;
    }

    //增加一个点
    public boolean addVertex(String V){
        for(int i = 0; i < verNum; i ++)
            if(V.equals(vertices[i]))
                return false;       //已存在该点
        int[][] edgesBackup =  new int[verNum][verNum];
        for(int i = 0; i < verNum; i ++)
            edgesBackup[i] = Arrays.copyOf(edges[i], verNum);

        String[] verticesBackup = new String[verNum];
        for(int i = 0; i < verNum; i ++)
            verticesBackup[i] = vertices[i];        //数据备份

        verNum++;       //图的顶点数增加

        edges = new int[verNum][verNum];
        vertices = new String[verNum];

        for(int i = 0; i < verNum; i ++){
            edges[i][verNum - 1] = noEdge;
            edges[verNum - 1][i] = noEdge;
        }       //设新的权值

        for(int i = 0; i < verNum - 1; i ++)
            edges[i] = Arrays.copyOf(edgesBackup[i], verNum );

        for(int i = 0; i < verNum - 1; i ++)
            vertices[i] = verticesBackup[i];
        vertices[verNum - 1] = new String(V);       //搬buckup
            return true;
    }

    //删除一个点
    public boolean removeVertex(int x){
        if(x < 0 || x > verNum -1)
            return false;       //顶点不存在

        int[][] edgesBackup =  new int[verNum][verNum];
        for(int i = 0; i < verNum; i ++)
            edgesBackup[i] = Arrays.copyOf(edges[i], verNum);

        String[] verticesBackup = new String[verNum];
        for(int i = 0; i < verNum; i ++)
            verticesBackup[i] = vertices[i];        //数据备份

        verNum--;       //图的顶点数减少

        edges = new int[verNum][verNum];
        vertices = new String[verNum];

        int flagi = 0;
        for(int i = 0; i <verNum; i ++){
            if(i == x) flagi = 1;
            int flagj = 0;
            for(int j = 0; j < verNum; j++){
                if(j == x) flagj = 1;

                edges[i][j] = edgesBackup[i + flagi][j + flagj];
            }
        }

        flagi = 0;
        for(int i = 0; i < verNum; i ++){
            if(i == x) flagi = 1;
            vertices[i] = verticesBackup[i + flagi];
        }
        return true;
    }

    //逻辑编号转实际名称
    public String getrealvertices(int num){
        return vertices[num];
    }

    public int getrealnum(String V){
        for(int i = 0; i < verNum; i ++)
            if(V.equals(vertices[i]))
                return i;
            return -1;
    }

    public boolean judgevertices(String V){
        for(int i = 0; i < verNum; i ++)
            if(V.equals(vertices[i]))
                return true;
        return false;
    }

    /*public static void main(String[] args) {
        adjMatrix test = new adjMatrix(6, 0);
        //test.printGraph();
        Vector<TriTuple> edges = new Vector<TriTuple>();
        edges.add(new TriTuple(0,1,1));
        edges.add(new TriTuple(0,2,1));
        edges.add(new TriTuple(0,4,1));
        edges.add(new TriTuple(1,4,1));
        edges.add(new TriTuple(3,4,1));
        edges.add(new TriTuple(3,5,1));
        edges.add(new TriTuple(4,5,1));

        edges.add(new TriTuple(0,0,0));
        String a[] = new String[225];
        a[0] = "a";
        a[1] = "b";
        a[2] = "c";
        a[3] = "d";
        a[4] = "e";
        a[5] = "f";
        test.createGraph(a,edges);
        test.printGraph();
        test.addVertex("a");
        //test.removeVertex(0);
        test.printGraph();

    }*/
}
