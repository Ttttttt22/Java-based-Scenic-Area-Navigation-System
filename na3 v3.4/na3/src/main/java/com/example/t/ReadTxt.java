package com.example.t;





import com.example.my_v_7.Point;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Vector;

public class ReadTxt {
    /**传入txt路径读取txt文件
     * @param txtPath
     * @return 返回读取到的内容
     */
    public static adjMatrix readTxtAdjMatrix(String txtPath) {
        File file = new File(txtPath);

        if(file.isFile() && file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


                StringBuffer sb = new StringBuffer();
                String text = null;

                int n;
                text = bufferedReader.readLine();
                n = Integer.parseInt(text);     //读数量
                System.out.println("读入n: " + n);

                String[] vertices = new String[n];
                Vector edges = new Vector<TriTuple>();

                int noEdge;
                text = bufferedReader.readLine();
                noEdge = Integer.parseInt(text);     //读无边标志
                System.out.println("读入noEdge: " + noEdge);

                text = bufferedReader.readLine();
                vertices = text.split(" ");
                for(int i = 0; i < n; i ++)
                    System.out.print(vertices[i] + " ");
                System.out.println();//读顶点


                while ((text = bufferedReader.readLine()) != null){
                    String [] tri = text.split(" ");
                    edges.add(new TriTuple(Integer.parseInt(tri[0]), Integer.parseInt(tri[1]), Integer.parseInt(tri[2])));
                    System.out.println("读入：" + Integer.parseInt(tri[0]) + " " +Integer.parseInt(tri[1]) + " "+ Integer.parseInt(tri[2]));
                }

                adjMatrix result = new adjMatrix(n,noEdge);
                result.createGraph(vertices, edges);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new adjMatrix(0, 0);//空图
    }


    /**使用FileOutputStream来写入txt文件
     * @param txtPath txt文件路径
     *
     */
    public static boolean writeTxtAdjMatrix(String txtPath, adjMatrix adj){
        FileOutputStream fileOutputStream = null;
        File file = new File(txtPath);
        try {
            if(file.exists()){
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }

            fileOutputStream = new FileOutputStream(file);

            //fileOutputStream.write(adj.getVerNum().getBytes());

            fileOutputStream.write((String.valueOf(adj.getVerNum())+"\n").getBytes() );
            fileOutputStream.write((String.valueOf(adj.getNoEdge())+"\n").getBytes() );

            for(int i = 0; i < adj.getVerNum(); i ++)
                fileOutputStream.write((adj.getVertices(i) + " ").getBytes());
            fileOutputStream.write("\n".getBytes());

            Vector<TriTuple> tritemp = adj.getTri();
            for(int i = 0; i < tritemp.size(); i ++)
            {
                fileOutputStream.write((String.valueOf(tritemp.get(i).getFrom()) + " ").getBytes());
                fileOutputStream.write((String.valueOf(tritemp.get(i).getTo()) + " ").getBytes());
                fileOutputStream.write((String.valueOf(tritemp.get(i).getLength()) + " ").getBytes());
                fileOutputStream.write("\n".getBytes());
            }
            fileOutputStream.write("0 0 0 ".getBytes());



            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch err");
            return false;
        }
        return true;
    }

    public static HashMap<String, Point> readHash(String txtPath){
        File file = new File(txtPath);
        HashMap<String, Point> result = new HashMap<String, Point>();

        if(file.isFile() && file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


                StringBuffer sb = new StringBuffer();
                String text = null;
                while((text = bufferedReader.readLine()) != null){
                    String [] temp = text.split(" ");
                    result.put(temp[0], new Point(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), temp[0]));
                    System.out.println(temp[0] + " " + Integer.parseInt(temp[1]) + " " + Integer.parseInt(temp[2]) + " " + temp[0]);
                }

                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return result;

    }

    public static boolean writeHash(String txtPath, HashMap<String, Point> pp){
        FileOutputStream fileOutputStream = null;
        File file = new File(txtPath);
        try {
            if(file.exists()){
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }

            fileOutputStream = new FileOutputStream(file);

            for(String i: pp.keySet()){
                fileOutputStream.write((i + " " + pp.get(i).getX() + " " + pp.get(i).getX() + " " + "\n").getBytes());
            }

            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch err");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        String [] vertices;
        Vector<TriTuple> edges;
        //adjMatrix sdj = readTxtAdjMatrix("result2.txt");
        adjMatrix sdj = readTxtAdjMatrix("dfsTestData.txt");
        sdj.printGraph();
        //writeTxt("result1.txt", sdj);
        writeTxtAdjMatrix("dfsTestDataresult.txt", sdj);
    }
}