package test;
/**
 * Богдан Кисличенко
 * группа 23501/3
 * Тесты для метода bfs
 */
import static org.junit.jupiter.api.Assertions.*;
import BFS.BfsGraph;
import BFS.GraphBuilder;
import BFS.Graph;

class BfsGraphTest {

    @org.junit.jupiter.api.Test
    void bfs() {
        GraphBuilder gk=new GraphBuilder();

        //установка вершин
        gk.addVertex("a");
        gk.addVertex("b");
        gk.addVertex("c");
        gk.addVertex("d");
        gk.addVertex("e");
        gk.addVertex("f");

        /*установка дугам вес равный 1 в соответсвии с рисуноком графа
        на слайде 5 к презентации третьей лекции
         */
        setConnect(gk,"a","b");
        setConnect(gk,"a","c");
        setConnect(gk,"b","c");
        setConnect(gk,"b","d");
        setConnect(gk,"c","e");
        setConnect(gk,"d","e");
        setConnect(gk,"d","f");

        Graph g=gk.build();
        BfsGraph tmp=new BfsGraph();

        assertEquals(3, tmp.bfs(g,new GraphBuilder.VertexImpl("a"),new GraphBuilder.VertexImpl("f")));
        assertEquals(1, tmp.bfs(g,new GraphBuilder.VertexImpl("b"),new GraphBuilder.VertexImpl("c")));
        assertEquals(2, tmp.bfs(g,new GraphBuilder.VertexImpl("b"),new GraphBuilder.VertexImpl("f")));
    }

    private void setConnect(GraphBuilder g, String first, String second){
        g.addConnection(new GraphBuilder.VertexImpl(first),new GraphBuilder.VertexImpl(second),1);

    }

}