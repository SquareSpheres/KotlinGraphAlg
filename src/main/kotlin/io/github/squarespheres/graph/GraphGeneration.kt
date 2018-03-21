package io.github.squarespheres.graph

import java.util.*

/**
 * Generate a random alternative simple graph.
 * @param numVertices number of vertices
 * @param numEdges number of edges
 * @return a graph SimpleGraphAlt
 * @see SimpleGraphAlt
 */
fun generateAlternativeSimpleGraph(numVertices: Long, numEdges: Long): SimpleGraphAlt {

    require(numEdges <= (numVertices * (numVertices - 1)) / 2) { "Too many edges" }
    require(numEdges > 0) { "Too few edges" }

    val random = Random()
    val simpleGraph = SimpleGraphAlt(numVertices.toInt())

    while (simpleGraph.numEdges < numEdges) {
        val edge = Edge(random.nextInt(numVertices.toInt()), random.nextInt(numVertices.toInt()))
        if (edge.from != edge.to) {
            simpleGraph.addEdge(edge)
        }
    }

    return simpleGraph
}

/**
 * Generate a random simple graph
 * @param numVertices number of vertices
 * @param numEdges number of edges
 * @return a graph SimpleGraphAlt
 * @see SimpleGraph
 */
fun generateSimpleGraph(numVertices: Long, numEdges: Long): SimpleGraph {

    require(numEdges <= (numVertices * (numVertices - 1)) / 2) { "Too many edges" }
    require(numEdges > 0) { "Too few edges" }

    val random = Random()
    val simpleGraph = SimpleGraph(numVertices.toInt())

    while (simpleGraph.numEdges < numEdges) {
        val v = random.nextInt(numVertices.toInt())
        val w = random.nextInt(numVertices.toInt())
        if (v != w) {
            simpleGraph.addEdge(v, w)
        }
    }
    return simpleGraph
}

