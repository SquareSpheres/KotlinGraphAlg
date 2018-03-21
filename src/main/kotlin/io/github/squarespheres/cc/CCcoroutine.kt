package io.github.squarespheres.cc

import io.github.squarespheres.graph.Edge
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

private var hasGrafted = true
private val numThreads = Runtime.getRuntime().availableProcessors()


private suspend fun graft(edges: List<Edge>, component: IntArray) {

    hasGrafted = false

    val deferred = (0 until numThreads).map { threadId ->
        launch(CommonPool) {

            for (i in threadId until edges.size step numThreads) {
                var fromVertex = edges[i].from
                var toVertex = edges[i].to

                if (fromVertex < toVertex) {
                    hasGrafted = true
                    component[toVertex] = fromVertex
                }

                val tmp = fromVertex
                fromVertex = toVertex
                toVertex = tmp

                if (fromVertex < toVertex) {
                    hasGrafted = true
                    component[toVertex] = fromVertex
                }
            }
        }
    }
    deferred.forEach { it.join() }
}

private suspend fun shortcut(component: IntArray) {

    val deferred = (0 until numThreads).map { threadId ->
        launch(CommonPool) {
            for (i in threadId until component.size step numThreads) {
                while (component[i] != component[component[i]]) {
                    component[i] = component[component[i]]
                }
            }

        }
    }

    deferred.forEach { it.join() }
}

private suspend fun update(edges: List<Edge>, component: IntArray) {

    val deferred = (0 until numThreads).map { threadId ->
        launch(CommonPool) {
            for (i in threadId until edges.size step numThreads) {
                edges[i].from = component[edges[i].from]
                edges[i].to = component[edges[i].to]
            }
        }
    }
    deferred.forEach { it.join() }
}


fun connectedComponents(edges: List<Edge>, numVertices: Int): List<Int> {

    hasGrafted = true
    val components = IntArray(numVertices) { it }

    while (hasGrafted) {
        runBlocking(CommonPool) {
            graft(edges, components)
            shortcut(components)
            update(edges, components)
        }
    }

    return components.toCollection(ArrayList())
}