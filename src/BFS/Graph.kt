package BFS

import BFS.Graph.Edge
import BFS.Graph
import BFS.Graph.Vertex

class GraphBuilder {

    data class VertexImpl(private val nameField: String) : Vertex {
        override fun getName() = nameField
    }

    data class EdgeImpl(private val weightField: Int,
                        val begin: Vertex,
                        val end: Vertex) : Edge {
        override fun getWeight() = weightField
    }

    private val vertices = mutableSetOf<Vertex>()

    private val connections = mutableMapOf<Vertex, Set<EdgeImpl>>()

    private  fun getSet():Set<Vertex>{
        return vertices.toSet()
    }

    fun addVertex(v: Vertex) {
        vertices.add(v)
    }

    fun addVertex(name: String): Vertex {
        return VertexImpl(name).apply {
            addVertex(this)
        }
    }

    fun addConnection(begin: Vertex, end: Vertex, weight: Int) {
        val edge = EdgeImpl(weight, begin, end)
        connections[begin] = connections[begin]?.let { it + edge } ?: setOf(edge)
        connections[end] = connections[end]?.let { it + edge } ?: setOf(edge)
    }

    fun build(): Graph = object : Graph {

        override fun getVertices(): Set<Vertex>{
            return getSet()
        }

        override fun getConnections(v: Vertex): Map<Vertex, Edge> {
            val edges = connections[v] ?: emptySet()
            val result = mutableMapOf<Vertex, Edge>()
            for (edge in edges) {
                when (v) {
                    edge.begin -> result[edge.end] = edge
                    edge.end -> result[edge.begin] = edge
                }
            }
            return result
        }


    }
}