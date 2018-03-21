import io.github.squarespheres.cc.CCSolver_Threads
import io.github.squarespheres.cc.connectedComponents
import io.github.squarespheres.graph.Edge
import io.github.squarespheres.graph.generateAlternativeSimpleGraph


fun main(args: Array<String>) {



    var timestart = System.currentTimeMillis()
    val graph = generateAlternativeSimpleGraph(1000000, 1000000)
    var timeEnd = System.currentTimeMillis() - timestart



    val jEdges = mutableListOf<Edge>()
    val kEdges = mutableListOf<Edge>()
    for (edge in graph.getEdges()) {
        jEdges.add(edge.copy())
        kEdges.add(edge.copy())
    }


    println("Generating graph = $timeEnd")


    timestart = System.currentTimeMillis()
    val componentsJ = CCSolver_Threads.connectedComponents(jEdges, graph.numVertices)
    timeEnd = System.currentTimeMillis() - timestart
    println("CCJ = $timeEnd")


    timestart = System.currentTimeMillis()
    val componentsK = connectedComponents(kEdges, graph.numVertices)
    timeEnd = System.currentTimeMillis() - timestart
    println("CCK = $timeEnd")





}


