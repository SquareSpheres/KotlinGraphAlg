package io.github.squarespheres.graph.graphs

import io.github.squarespheres.graph.Edge

/**
 * A simple graph is an undirected graph in which both multiple edges and loops are disallowed.
 *
 * This graph is represented only by a list of edges. This saves a significant amount of memory compared to
 * typical graph representations which uses adjacency lists, or adjacency matrices. This comes at a cost however.
 * You will not be able to find out which vertex another vertex is adjacent to without going through every edge.
 * This type of graph is fast and useful for finding connected components.
 *
 * @see UndirectedGraphAdjList
 */
class SimpleGraphAlt(val numVertices: Int) {

    init {
        require(numVertices > 0) { "Number of vertices cannot be less than 1" }
    }


    private val edges = mutableSetOf<Edge>()
    val numEdges: Int
        get() = edges.size

    /**
     * Add an edge to the graph. Maximum number of edges allowed for a simple graph is n(n-1)/2. If more edges are added they will simply be ignored.
     * @param from from vertex
     * @param to to vertex
     */
    fun addEdge(from: Int, to: Int) {
        require(from < numVertices && to < numVertices) { "Vertex number to high. Max vertex number = $numVertices" }
        require(from >= 0 && to >= 0) { "Vertex number must be larger or equal to 0" }
        require(from != to) { "Loops are not allowed" }

        if (!edges.contains(Edge(from, to)) && !edges.contains(Edge(to, from))) {
            edges.add(Edge(from, to))
        }
    }

    /**
     * Add an edge to the graph. Maximum number of edges allowed for a simple graph is n(n-1)/2. If more edges are added they will simply be ignored.
     * @param edge an edge
     */
    fun addEdge(edge: Edge) {
        require(edge.from < numVertices && edge.to < numVertices) { "Vertex number to high. Max vertex number = $numVertices" }
        require(edge.from >= 0 && edge.to >= 0) { "Vertex number must be larger or equal to 0" }
        require(edge.from != edge.to) { "Loops are not allowed" }

        if (!edges.contains(edge) && !edges.contains(Edge(edge.to, edge.from))) {
            edges.add(edge)
        }
    }

    /**
     * Get a list of all the edges in the graph
     * @return a list of edges
     */
    fun getEdges(): List<Edge> {
        return edges.toList()
    }

    override fun toString(): String {
        return "NumVertices = $numVertices Edges = $edges"
    }

}
