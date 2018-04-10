package io.github.squarespheres.graph.graphs.undirected

import io.github.squarespheres.graph.GraphAdjList
import io.github.squarespheres.graph.IUndirectedGraph

/**
 * An undirected graph in which both multiple edges and loops are disallowed.
 *
 * This implementation uses adjacency-list
 *
 * @see UndirectedGraphAdjList
 */
open class SimpleUndirectedGraphAdjList(numVertices: Int) : GraphAdjList(numVertices), IUndirectedGraph {

    override fun addEdge(v: Int, w: Int) {

        require(v in 0..(numVertices - 1)) { "Vertex $v is not a member of graph(0 - ${numVertices - 1})" }
        require(w in 0..(numVertices - 1)) { "Vertex $w is not a member of graph(0 - ${numVertices - 1})" }
        require(v != w) { "Self-loops are not allowed" }
        require(!adjacencyList[v].contains(w)) { "Multiple edges are not allowed" }

        adjacencyList[v].add(w)
        adjacencyList[w].add(v)
        numEdges++

    }

    override fun degree(vertex: Int): Int {
        return adjacencyList[vertex].size
    }
}