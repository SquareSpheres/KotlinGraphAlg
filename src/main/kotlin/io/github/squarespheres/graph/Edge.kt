package io.github.squarespheres.graph

/**
 * An ordered pair of nodes representing a line joining the two nodes.
 * The class can be used for undirected graphs as well, but remember that
 * (u,v)!=(v,u), so this must be handled by the user.
 */
data class Edge(var from: Int, var to: Int)