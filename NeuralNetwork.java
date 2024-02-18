package mlneuralnetwork;

import java.util.*;

class NeuralNetwork {
    private List<List<Double>> weights; // 2D list to store weights between nodes
    
    // Constructor to initialize the neural network with specified number of layers and nodes
    public NeuralNetwork(int[] nodesInLayer) {
        weights = new ArrayList<>();
        for (int i = 0; i < nodesInLayer.length - 1; i++) {
            List<Double> layerWeights = new ArrayList<>();
            for (int j = 0; j < nodesInLayer[i] * nodesInLayer[i + 1]; j++) {
                layerWeights.add(0.0); // Initialize all weights to 0 initially
            }
            weights.add(layerWeights);
        }
    }
    
    // Method to set weight of an edge
    public void setWeight(int layerIndex, int nodeIndex1, int nodeIndex2, double weight) {
        int index = nodeIndex1 * getNodesInLayer(layerIndex + 1) + nodeIndex2;
        weights.get(layerIndex).set(index, weight);
    }
    
    // Method to get weight of an edge
    public double getWeight(int layerIndex, int nodeIndex1, int nodeIndex2) {
        int index = nodeIndex1 * getNodesInLayer(layerIndex + 1) + nodeIndex2;
        return weights.get(layerIndex).get(index);
    }
    
    // Helper method to get number of nodes in a layer
    private int getNodesInLayer(int layerIndex) {
        return weights.get(layerIndex - 1).size() / weights.get(layerIndex).size();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input the number of layers
        System.out.print("Enter the number of layers: ");
        int numLayers = scanner.nextInt();
        
        // Input the number of nodes in each layer
        int[] nodesInLayer = new int[numLayers];
        for (int i = 0; i < numLayers; i++) {
            System.out.print("Enter the number of nodes in layer " + (i + 1) + ": ");
            nodesInLayer[i] = scanner.nextInt();
        }
        
        // Create neural network object
        NeuralNetwork neuralNetwork = new NeuralNetwork(nodesInLayer);
        
        // Set weights for each edge
        for (int i = 0; i < numLayers - 1; i++) {
            for (int j = 0; j < nodesInLayer[i]; j++) {
                for (int k = 0; k < nodesInLayer[i + 1]; k++) {
                    System.out.print("Enter weight for edge from node " + (j + 1) + " in layer " + (i + 1) +
                                     " to node " + (k + 1) + " in layer " + (i + 2) + ": ");
                    double weight = scanner.nextDouble();
                    neuralNetwork.setWeight(i, j, k, weight);
                }
            }
        }
        
        // Query weight of an edge
        System.out.print("Enter the layer index: ");
        int layerIndex = scanner.nextInt() - 1;
        System.out.print("Enter the source node index: ");
        int sourceNodeIndex = scanner.nextInt() - 1;
        System.out.print("Enter the destination node index: ");
        int destNodeIndex = scanner.nextInt() - 1;
        double weight = neuralNetwork.getWeight(layerIndex, sourceNodeIndex, destNodeIndex);
        System.out.println("Weight of edge from node " + (sourceNodeIndex + 1) + " to node " + (destNodeIndex + 1) +
                           " in layer " + (layerIndex + 1) + " is: " + weight);
        
        scanner.close();
    }
}
