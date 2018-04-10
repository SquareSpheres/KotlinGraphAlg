package io.github.squarespheres.graph.graphs.directed

import io.github.squarespheres.graph.GraphAdjList
import io.github.squarespheres.graph.IDirectedGraph

/**
 * A directed graph in which both multiple edges and loops are disallowed.
 *
 * This implementation uses adjacency-list
 *
 * @see DirectedGraphAdjList
 */
class SimpleDirectedGraphAdjList(numVertices: Int) : GraphAdjList(numVertices), IDirectedGraph {

    private val inDeg = IntArray(numVertices)

    override fun addEdge(v: Int, w: Int) {

        require(v in 0..(numVertices - 1)) { "Vertex $v is not a member of graph(0 - ${numVertices - 1})" }
        require(w in 0..(numVertices - 1)) { "Vertex $w is not a member of graph(0 - ${numVertices - 1})" }
        require(v != w) { "Self-loops are not allowed" }
        require(!adjacencyList[v].contains(w)) { "Multiple edges are not allowed" }

        adjacencyList[v].add(w)
        inDeg[w]++
        numEdges++

    }

    override fun inDegree(vertex: Int): Int {
        require(vertex in 0..(numVertices - 1)) { "Vertex $vertex is not a member of graph(0 - ${numVertices - 1})" }
        return inDeg[vertex]
    }

    override fun outDegree(vertex: Int): Int {
        require(vertex in 0..(numVertices - 1)) { "Vertex $vertex is not a member of graph(0 - ${numVertices - 1})" }
        return adjacencyList[vertex].size
    }


    override fun reverse(): IDirectedGraph {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}