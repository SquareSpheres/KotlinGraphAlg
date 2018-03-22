package io.github.squarespheres.graph

/**
 * An interface for a graph in which both multiple edges and loops are disallowed.
 *
 * A simple graph is by definition undirected, but this interface can also apply to directed graphs
 */
interface ISimpleGraph {

    /**
     * Add a edge from v to w
     * @param v from
     * @param w to
     */
    fun addEdge(v: Int, w: Int)

    /**
     * Get the outdegree for a vertex. For undirected graphs indegree==outdegree
     * @param vertex vertex
     * @return outdegree for vertex
     */
    fun outDegree(vertex: Int): Int

    /**
     * Get list of vertices adjacent from vertex
     * @param vertex vertex
     * @return list of vertices
     */
    fun adjacent(vertex: Int): List<Int>

    /**
     * Number of edges in graph
     * @return number of edges
     */
    fun numEdges(): Int

    /**
     * Number of vertices in graph
     * @return number of vertices
     */
    fun numVertices(): Int

}