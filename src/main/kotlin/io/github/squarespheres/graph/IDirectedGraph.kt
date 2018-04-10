package io.github.squarespheres.graph

interface IDirectedGraph : IGraph {

    /**
     * Get the outdegree for a vertex. For undirected graphs indegree==outdegree
     * @param vertex vertex
     * @return outdegree for vertex
     */
    fun outDegree(vertex: Int): Int

    /**
     * Get the indegree for a vertex.
     * @param vertex vertex
     * @return outdegree for vertex
     */
    fun inDegree(vertex: Int): Int

    /**
     * Returns the reverse of the digraph.
     * @return reverse of the graph
     */
    fun reverse(): IDirectedGraph

}