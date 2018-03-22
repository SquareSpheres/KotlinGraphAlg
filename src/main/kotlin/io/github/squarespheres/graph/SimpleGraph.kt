package io.github.squarespheres.graph


/**
 * A simple graph is an undirected graph in which both multiple edges and loops are disallowed.
 *
 * This implementation uses adjacency-list
 *
 * @see SimpleGraphAlt
 */
class SimpleGraph(numVertices: Int) : AbstractSimpleGraph(numVertices) {

    constructor(graph: SimpleGraph) : this(graph.numVertices) {

        for (v in 0 until graph.numVertices) {
            for (w in graph.adjacent(v)) {
                addEdge(v, w)
            }
        }
    }

    constructor(graph: SimpleGraphAlt) : this(graph.numVertices) {

        for (edge in graph.getEdges()) {
            addEdge(edge.from, edge.to)
        }
    }

    override fun addEdge(v: Int, w: Int) {
        require(v in 0..(numVertices - 1)) { "Vertex $v is not a member of graph(0 - ${numVertices - 1})" }
        require(w in 0..(numVertices - 1)) { "Vertex $w is not a member of graph(0 - ${numVertices - 1})" }
        require(v != w) { "Self-loops are not allowed" }
        if (adjacencyList[v].add(w) && adjacencyList[w].add(v)) {
            numEdges++
        }

    }
}
