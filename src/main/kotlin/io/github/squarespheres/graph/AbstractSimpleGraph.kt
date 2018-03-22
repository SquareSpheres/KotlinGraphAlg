package io.github.squarespheres.graph

/**
 * An abstract class implementing some core functionality of ISimpleGraph
 * @see ISimpleGraph
 *
 * This implementation uses adjacency-list
 */
abstract class AbstractSimpleGraph(val numVertices: Int) : ISimpleGraph {

    protected val adjacencyList = mutableListOf<MutableSet<Int>>()
    var numEdges = 0
        protected set

    init {
        require(numVertices > 0) { "Number of vertices must be a positive integer" }
        for (i in 0 until numVertices) {
            adjacencyList.add(mutableSetOf())
        }
    }

    override fun outDegree(vertex: Int): Int {
        require(vertex in 0..(numVertices - 1)) { "Vertex $vertex is not a member of graph(0 - ${numVertices - 1})" }
        return adjacencyList[vertex].size
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