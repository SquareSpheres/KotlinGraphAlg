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
 * Add random edges to graph
 * @param graph graph to fill
 * @param numEdges number of edges to add
 */
fun randomFillGraph(graph: ISimpleGraph, numEdges: Long) {


    if (graph is IDigraph) {
        require(graph.numEdges() + numEdges <= (graph.numVertices() * (graph.numVertices() - 1))) { "Too many edges" }
        require(numEdges > 0) { "Too few edges" }
    } else {
        require(graph.numEdges() + numEdges <= (graph.numVertices() * (graph.numVertices() - 1)) / 2) { "Too many edges" }
        require(numEdges > 0) { "Too few edges" }
    }

    val numEdgesToReach = graph.numEdges() + numEdges

    val random = Random()
    while (graph.numEdges() < numEdgesToReach) {
        val v = random.nextInt(graph.numVertices())
        val w = random.nextInt(graph.numVertices())
        if (v != w) {
            graph.addEdge(v, w)
        }
    }
}


/**
 * Generate a random simple graph
 * @param numVertices number of vertices
 * @param numEdges number of edges
 * @return a graph SimpleGraphAlt
 * @see SimpleGraph
 */
fun generateRandomSimpleGraph(numVertices: Long, numEdges: Long): SimpleGraph {
    val simpleGraph = SimpleGraph(numVertices.toInt())
    randomFillGraph(simpleGraph, numEdges)
    return simpleGraph
}

/**
 * Generate a random simple directed graph
 * @param numVertices number of vertices
 * @param numEdges number of edges
 * @return a graph SimpleGraphAlt
 * @see SimpleGraph
 */
fun generateRandomSimpleDiGraph(numVertices: Long, numEdges: Long): SimpleDiGraph {
    val simpleDiGraph = SimpleDiGraph(numVertices.toInt())
    randomFillGraph(simpleDiGraph, numEdges)
    return simpleDiGraph
}



