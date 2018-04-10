package io.github.squarespheres.algorithms.cc

import io.github.squarespheres.graph.IUndirectedGraph
import java.util.*

/**
 * Sequential algorithms for finding connected components in undirected graphs
 */
class CC(private val graph: IUndirectedGraph, algorithm: ALGORITHM = ALGORITHM.DFS) {


    enum class ALGORITHM {
        /**
         * Recursive depth-first search
         */
        DFS,
        /**
         * Iterative depth-first search (be careful with large graphs, it might cause stack overflow)
         */
        ITERATIVE_DFS,
        /**
         * breadth-first search
         */
        BFS
    }


    private val marked = BooleanArray(graph.numVertices())
    private val components = IntArray(graph.numVertices())
    private val size = IntArray(graph.numVertices())
    private var componentCount: Int = 0


    init {
        when (algorithm) {
            ALGORITHM.DFS -> connectedComponents { someGraph, startNode -> dfs(someGraph, startNode) }
            ALGORITHM.ITERATIVE_DFS -> connectedComponents { someGraph, startNode -> itDfs(someGraph, startNode) }
            ALGORITHM.BFS -> connectedComponents { someGraph, startNode -> bfs(someGraph, startNode) }
        }
    }


    private fun connectedComponents(func: (IUndirectedGraph, Int) -> Unit) {
        for (i in 0 until graph.numVertices()) {
            if (!marked[i]) {
                func.invoke(graph, i)
            }
            componentCount++
        }
    }


    private fun dfs(graph: IUndirectedGraph, v: Int) {

        marked[v] = true
        components[v] = componentCount
        size[componentCount]++
        for (w in graph.adjacent(v)) {
            if (!marked[w]) {
                dfs(graph, w)
            }
        }
    }

    private fun itDfs(graph: IUndirectedGraph, v: Int) {

        val stack = Stack<Int>()
        stack.add(v)
        while (!stack.isEmpty()) {
            val next = stack.pop()
            marked[next] = true
            components[next] = componentCount
            size[componentCount]++
            for (w in graph.adjacent(next)) {
                if (!marked[w]) {
                    stack.add(w)
                }
            }
        }
    }

    private fun bfs(graph: IUndirectedGraph, v: Int) {

        val queue = LinkedList<Int>()
        queue.push(v)
        while (!queue.isEmpty()) {
            val next = queue.pop()
            marked[next] = true
            components[next] = componentCount
            size[componentCount]++

            for (w in graph.adjacent(next)) {
                if (!marked[w]) {
                    queue.add(w)
                }
            }
        }
    }

    fun getComponents(): List<Int> {
        return components.toList()
    }

    fun sizeOfComponent(component: Int): Int {
        return size[component]
    }

}

