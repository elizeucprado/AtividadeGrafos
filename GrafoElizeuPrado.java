
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GrafoElizeuPrado {

    // 1. Representação por Matriz de Adjacência
    static class GrafoMatriz {

        private int vertices;
        private int[][] matriz;
        private boolean direcionado;

        public GrafoMatriz(int vertices, boolean direcionado) {
            this.vertices = vertices;
            this.direcionado = direcionado;
            this.matriz = new int[vertices][vertices];
        }

        public void adicionarAresta(int origem, int destino) {
            matriz[origem][destino] = 1;
            if (!direcionado) {
                matriz[destino][origem] = 1;
            }
        }

        public void imprimir() {
            System.out.println("\n--- Matriz de Adjacência ---");
            System.out.print("   ");
            for (int i = 0; i < vertices; i++) {
                System.out.print(i + " ");
            }
            System.out.println();

            for (int i = 0; i < vertices; i++) {
                System.out.print(i + ": ");
                for (int j = 0; j < vertices; j++) {
                    System.out.print(matriz[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    // 2. Representação por Lista de Adjacência
    static class GrafoLista {

        private int vertices;
        private List<List<Integer>> adjacencia;
        private boolean direcionado;

        public GrafoLista(int vertices, boolean direcionado) {
            this.vertices = vertices;
            this.direcionado = direcionado;
            this.adjacencia = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjacencia.add(new ArrayList<>());
            }
        }

        public void adicionarAresta(int origem, int destino) {
            adjacencia.get(origem).add(destino);
            if (!direcionado) {
                adjacencia.get(destino).add(origem);
            }
        }

        public void imprimir() {
            System.out.println("\n--- Lista de Adjacência ---");
            for (int i = 0; i < vertices; i++) {
                System.out.print(i + " -> ");
                for (int vizinho : adjacencia.get(i)) {
                    System.out.print(vizinho + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número de vértices: ");
        int totalVertices = scanner.nextInt();

        System.out.print("O grafo é direcionado? (s/n): ");
        boolean direcionado = scanner.next().trim().equalsIgnoreCase("s");

        GrafoMatriz grafoMatriz = new GrafoMatriz(totalVertices, direcionado);
        GrafoLista grafoLista = new GrafoLista(totalVertices, direcionado);

        System.out.print("Digite a quantidade de arestas: ");
        int totalArestas = scanner.nextInt();

        System.out.println("Insira as arestas no formato: <origem> <destino> (vértices de 0 a " + (totalVertices - 1) + "):");
        for (int i = 0; i < totalArestas; i++) {
            System.out.print("Aresta " + (i + 1) + ": ");
            int u = scanner.nextInt();
            int v = scanner.nextInt();

            if (u >= 0 && u < totalVertices && v >= 0 && v < totalVertices) {
                grafoMatriz.adicionarAresta(u, v);
                grafoLista.adicionarAresta(u, v);
            } else {
                System.out.println("Vértice inválido! Digite valores entre 0 e " + (totalVertices - 1));
                i--; // repete a iteração
            }
        }

        // Exibição dos resultados
        grafoMatriz.imprimir();
        grafoLista.imprimir();

        scanner.close();
    }

}
