package io.github.squarespheres.graph

class SimpleDiGraph(numVertices: Int) : AbstractSimpleGraph(numVertices), IDigraph {

    private val inDeg = IntArray(numVertices)

    override fun addEdge(v: Int, w: Int) {
        require(v in 0..(numVertices - 1)) { "Vertex $v is not a member of graph(0 - ${numVertices - 1})" }
        require(w in 0..(numVertices - 1)) { "Vertex $w is not a member of graph(0 - ${numVertices - 1})" }
        require(v != w) { "Self-loops are not allowed" }

        if (adjacencyList[v].add(w)) {
            inDeg[w]++
            numEdges++
        }
    }

    override fun inDegree(vertex: Int): Int {
        require(vertex in 0..(numVertices - 1)) { "Vertex $vertex is not a member of graph(0 - ${numVertices - 1})" }
        return inDeg[vertex]
    }

    override fun reverse(): IDigraph {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}