package io.github.squarespheres.graph

/**
 * An abstract class implementing some core functionality of IGraph
 *
 * @see IGraph
 *
 * This implementation uses adjacency-list
 */
abstract class GraphAdjList(val numVertices: Int) : IGraph {

    protected val adjacencyList = mutableListOf<MutableList<Int>>()
    var numEdges = 0
        protected set

    init {
        require(numVertices > 0) { "Number of vertices must be a positive integer" }
        for (i in 0 until numVertices) {
            adjacencyList.add(mutableListOf())
        }
    }

    override fun adjacent(vertex: Int): List<Int> {
        require(vertex in 0..(numVertices - 1)) { "Vertex $vertex is not a member of graph(0 - ${numVertices - 1})" }
        return adjacencyList[vertex].toList()
    }

    override fun numEdges(): Int {
        return numEdges
    }

    override fun numVertices(): Int {
        return numVertices
    }

    override fun hasEdge(v: Int, w: Int): Boolean {
        require(v in 0..(numVertices - 1)) { "v $v is not a member of graph(0 - ${numVertices - 1})" }
        require(w in 0..(numVertices - 1)) { "w $w is not a member of graph(0 - ${numVertices - 1})" }
        return adjacencyList[v].contains(w)
    }

    override fun edgeList(): List<Edge> {

        val edges = mutableListOf<Edge>()

        for (from in 0 until adjacencyList.size) {
            val adj = adjacencyList[from]
            for (to in adj) {
                edges.add(Edge(from, to))
            }
        }
        return edges.toList()
    }

    override fun toString(): String {
        val s = StringBuilder()
        s.append("$numVertices vertices ,  $numEdges  edges\n")
        for (v in 0 until numVertices) {
            s.append("$v  : ")
            for (w in adjacencyList[v]) {
                s.append("$w ")
            }
            s.append("\n")
        }
        return s.toString()
    }
}