package io.github.squarespheres.graph

interface IUndirectedGraph : IGraph {

    /**
     * Get the outdegree for a vertex. For undirected graphs indegree==outdegree
     * @param vertex vertex
     * @return outdegree for vertex
     */
    fun degree(vertex: Int): Int

}