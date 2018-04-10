package io.github.squarespheres.graph

/**
 * An interface for a graph
 */
interface IGraph {

    /**
     * Add a edge from v to w
     * @param v from
     * @param w to
     */
    fun addEdge(v: Int, w: Int)

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

    /**
     * Check if the graph contains an edge v->w
     * @param v from
     * @param w to
     * @return true if edge exist in graph
     */
    fun hasEdge(v: Int, w: Int): Boolean

    /**
     * get list of edges in the graph
     * @return list of edges
     */
    fun edgeList(): List<Edge>


}