package BFS;
/*
 * Богдан Кисличенко
 * группа 23501/3
 * Алгоритм поиска в ширину (bfs)
 */

import java.util.*;

public class BfsGraph {

    //данная функция реализована в соответсвии с интерфейсом Graph из презентации третьей лекции
    public int bfs(Graph g, Graph.Vertex start, Graph.Vertex end){
        Set<Graph.Vertex> newGraph=g.getVertices();

     /*
     хэш-таблица hashMap используется для хранения информации о прохождении вершины
     и для удобного поиска по строковому ключу
      */
        Map<String,Boolean> hashMap=new HashMap<String,Boolean>();

        //хэш-таблица hashdistance используется для хранения информации о расстоянии вершины от начальной (start)
        Map<String,Integer> hashDistance=new HashMap<String,Integer>();

        //vert используется для перебора всех вершин (от первой до последней)
        Graph.Vertex vert=new GraphBuilder.VertexImpl("");

        //поиск заданной в аргументах метода начальной вершины
        Iterator<Graph.Vertex> it=newGraph.iterator();

        while(it.hasNext()){
            vert=it.next();

            //вершина пройдена
            hashMap.put(vert.getName(),true);

            //у всех вершин до необходимой вершины start расстояние 0
            hashDistance.put(vert.getName(),0);

            //дошли до вершины start
            if(vert.getName().compareTo(start.getName())==0) break;
        }

        while(it.hasNext()&&vert.getName().compareTo(end.getName())!=0){

            //просмотр всех соседей вершины
            for(Graph.Vertex tmp: g.getNeighbors(vert)){

                //если одна из соседних вершин уже пройдена
                if(hashMap.containsKey(tmp.getName())) continue;

                hashMap.put(tmp.getName(),true);
                hashDistance.put(tmp.getName(),hashDistance.get(vert.getName())+1);
            }
            vert=it.next();
        }

        return hashDistance.get(end.getName());
    }

}
