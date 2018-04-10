package io.github.squarespheres.algorithms.cc;

import io.github.squarespheres.graph.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CCSolver_Threads {


    public static List<Integer> connectedComponentsAsList(List<Edge> edges, int numVertices) {

        int[] components = connectedComponents(edges, numVertices);
        List<Integer> result = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            result.add(components[i]);
        }

        return result;
    }

    public static int[] connectedComponents(List<Edge> edges, int numVertices) {
        CCSolver_Threads solver = new CCSolver_Threads(edges, numVertices);
        return solver.connectedComponents();
    }


    private boolean hasGrafted = true;

    private final int numberOfThreads;
    private final int numVertices;
    private final ExecutorService executorService;
    private final List<Edge> edges;


    private final int[] component;

    private CCSolver_Threads(List<Edge> edges, int numVertices) {
        this.numVertices = numVertices;
        this.edges = edges;
        this.numberOfThreads = Runtime.getRuntime().availableProcessors();
        this.executorService = Executors.newFixedThreadPool(numberOfThreads);
        this.component = new int[numVertices];
    }

    private int[] connectedComponents() {

        //create all tasks
        List<Callable<Void>> initTaks = new ArrayList<>();
        List<Callable<Void>> graftingTasks = new ArrayList<>();
        List<Callable<Void>> shortcutTasks = new ArrayList<>();
        List<Callable<Void>> updateTasks = new ArrayList<>();

        for (int threadId = 0; threadId < numberOfThreads; threadId++) {
            initTaks.add(new Initializer(threadId));
            graftingTasks.add(new Grafter(threadId));
            shortcutTasks.add(new Shortcutter(threadId));
            updateTasks.add(new Updater(threadId));
        }

        //Initialize components
        try {
            executorService.invokeAll(initTaks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Run the algorithm
        while (hasGrafted) {

            hasGrafted = false;

            try {
                executorService.invokeAll(graftingTasks);
                executorService.invokeAll(shortcutTasks);
                executorService.invokeAll(updateTasks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        return component;
    }


    private class Initializer implements Callable<Void> {

        private final int threadId;

        private Initializer(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public Void call() {

            for (int i = threadId; i < component.length; i += numberOfThreads) {
                component[i] = i;
            }
            return null;
        }
    }

    private class Grafter implements Callable<Void> {

        private final int threadId;

        private Grafter(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public Void call() {

            for (int i = threadId; i < edges.size(); i += numberOfThreads) {

                int fromVertex = edges.get(i).getFrom();
                int toVertex = edges.get(i).getTo();

                if (fromVertex < toVertex) {
                    hasGrafted = true;
                    component[toVertex] = fromVertex;
                }

                int tmp = fromVertex;
                fromVertex = toVertex;
                toVertex = tmp;

                if (fromVertex < toVertex) {
                    hasGrafted = true;
                    component[toVertex] = fromVertex;
                }

            }
            return null;
        }
    }

    private class Shortcutter implements Callable<Void> {

        private final int threadId;

        private Shortcutter(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public Void call() {

            for (int i = threadId; i < numVertices; i += numberOfThreads) {

                while (component[i] != component[component[i]]) {
                    component[i] = component[component[i]];
                }
            }
            return null;
        }
    }

    private class Updater implements Callable<Void> {

        private final int threadId;

        private Updater(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public Void call() {

            for (int i = threadId; i < edges.size(); i += numberOfThreads) {
                edges.get(i).setFrom(component[edges.get(i).getFrom()]);
                edges.get(i).setTo(component[edges.get(i).getTo()]);
            }
            return null;
        }
    }


}
