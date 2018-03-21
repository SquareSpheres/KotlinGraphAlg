package io.github.squarespheres.graph


/**
 * A simple graph is an undirected graph in which both multiple edges and loops are disallowed.
 *
 * This implementation uses adjacency-list
 *
 * @see SimpleGraphAlt
 */
class SimpleGraph(val numVertices: Int) {

    private val adjacencyList = mutableListOf<MutableSet<Int>>()
    var numEdges = 0
        private set

    init {
        require(numVertices > 0) { "Number of vertices must be a positive integer" }
        for (i in 0 until numVertices) {
            adjacencyList.add(mutableSetOf())
        }
    }

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

    fun addEdge(v: Int, w: Int) {
        require(v in 0..(numVertices - 1)) { "Vertex $v is not a member of graph(0 - ${numVertices - 1})" }
        require(w in 0..(numVertices - 1)) { "Vertex $w is not a member of graph(0 - ${numVertices - 1})" }
        require(v != w) { "Self-loops are not allowed" }
        if (adjacencyList[v].add(w) && adjacencyList[w].add(v)) {
            numEdges++
        }
    }

    fun degree(vertex: Int): Int {
        require(vertex in 0..(numVertices - 1)) { "Vertex $vertex is not a member of graph(0 - ${numVertices - 1})" }
        return adjacencyList[vertex].size
    }

    fun adjacent(vertex: Int): List<Int> {
        require(vertex in 0..(numVertices - 1)) { "Vertex $vertex is not a member of graph(0 - ${numVertices - 1})" }
        return adjacencyList[vertex].toList()
    }

    override fun toString(): String {
        val s = StringBuilder()
        s.append("$numVertices vertices ,  $numEdges  edges\n")
        for (v in 0 until numVertices) {
            s.append("$v  : ")
            for (w in adjacencyList[v]) {
                s.append("$w ")
            }
            s.append("\n")
        }
        return s.toString()
    }
}
