package io.github.squarespheres.graph.graphs.directed

import io.github.squarespheres.graph.GraphAdjMatrix
import io.github.squarespheres.graph.IDirectedGraph

/**
 * A directed graph in which both multiple edges and loops are allowed.
 *
 * This implementation uses adjacency-matrix
 *
 * @see SimpleDirectedGraphAdjMatrix
 */
class DirectedGraphAdjMatrix(numVertices: Int) : GraphAdjMatrix(numVertices), IDirectedGraph {

    private val inDeg = IntArray(numVertices)

    override fun addEdge(v: Int, w: Int) {
        require(v in 0..(numVertices - 1)) { "Vertex $v is not a member of graph(0 - ${numVertices - 1})" }
        require(w in 0..(numVertices - 1)) { "Vertex $w is not a member of graph(0 - ${numVertices - 1})" }

        adjacencyMatrix[v][w]++
        inDeg[w]++
        numEdges++

    }

    override fun outDegree(vertex: Int): Int {
        require(vertex in 0..(numVertices - 1)) { "Vertex $vertex is not a member of graph(0 - ${numVertices - 1})" }
        return adjacencyMatrix[vertex].sum()
    }

    override fun inDegree(vertex: Int): Int {
        require(vertex in 0..(numVertices - 1)) { "Vertex $vertex is not a member of graph(0 - ${numVertices - 1})" }
        return inDeg[vertex]
    }

    override fun reverse(): IDirectedGraph {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}