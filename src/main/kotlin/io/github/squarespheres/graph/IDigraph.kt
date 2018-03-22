package io.github.squarespheres.graph

interface IDigraph : ISimpleGraph {

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
    fun reverse(): IDigraph

}