package io.github.squarespheres.graph

/**
 * An abstract class implementing some core functionality of IGraph
 *
 * @see IGraph
 *
 * This implementation uses adjacency-matrix
 */
abstract class GraphAdjMatrix(val numVertices: Int) : IGraph {

    protected val adjacencyMatrix = mutableListOf<IntArray>()
    var numEdges = 0
        protected set

    init {
        require(numVertices > 0) { "Number of vertices must be a positive integer" }
        for (i in 0 until numVertices) {
            adjacencyMatrix.add(IntArray(numVertices))
        }
    }


    override fun adjacent(vertex: Int): List<Int> {

        val adj = mutableListOf<Int>()
        for (i in 0 until numVertices) {
            for (j in 0 until adjacencyMatrix[vertex][i]) {
                adj.add(i)
            }
        }

        return adj
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
        return adjacencyMatrix[v][w] > 0
    }

    override fun edgeList(): List<Edge> {

        val edges = mutableListOf<Edge>()

        for (from in 0 until numVertices) {
            val adj = adjacent(from)
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
            for (w in adjacent(v)) {
                s.append("$w ")
            }
            s.append("\n")
        }
        return s.toString()
    }
}