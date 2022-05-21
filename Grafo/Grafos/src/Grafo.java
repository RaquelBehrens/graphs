import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileReader;

public class Grafo {

    // Vértices
    List<Integer> V = new ArrayList<>();

    //Rotulo
    List<String> R = new ArrayList<>();

    // Arestas
    LinkedList<List<Float>> E = new LinkedList<>();

    // Função que mapeia o peso de cada aresta
    float w(float[] vertice) {
        for (int i = 0; i < E.size(); i++) {
            if (E.get(i).get(0).equals(vertice[0]) && E.get(i).get(1).equals(vertice[1])) {
                return E.get(i).get(2);
            }
        }
        return 2147483647;
    }

    int qtdVertices() {
        int tamanho = V.size();
        return tamanho;
    }

    int qtdArestas() {
        int tamanho = E.size();
        return tamanho;
    }

    int grau(float v) {
        int quantidade = 0;

        for (int i = 0; i < E.size(); i++) {
            for (int j = 0; j < E.get(i).size(); j++)
                if (E.get(i).get(j).equals(v)) {
                    quantidade++;
                }
        }
        return quantidade;
    }

    String rotulo(int v) {
        for (int i = 0; i < V.size(); i++) {
            if (V.get(i).equals(v)) {
                return R.get(i);
            }
        }
        return "";
    }

    List<Float> vizinhos(float v) {
        float vizinho = 0;
        List<Float> lista_vizinhos = new ArrayList<>();

        for (int i = 0; i < E.size(); i++) {
            vizinho = 0;

            if (E.get(i).get(0).equals(v)) {
                vizinho = E.get(i).get(1);
            } else if (E.get(i).get(1).equals(v)) {
                System.out.println("aqui");
                vizinho = E.get(i).get(0);
            }

            if (vizinho != 0) {
                lista_vizinhos.add(vizinho);
            }
        }

        return lista_vizinhos;
    }

    boolean haAresta(float[] aresta) {
        for (int i = 0; i < E.size(); i++) {
            if (E.get(i).get(0).equals(aresta[0]) && E.get(i).get(1).equals(aresta[1])) {
                return true;
            }
        }
        return false;
    }

    float peso(float[] aresta) {
        for (int i = 0; i < E.size(); i++) {
            if (E.get(i).get(0).equals(aresta[0]) && E.get(i).get(1).equals(aresta[1])) {
                return E.get(i).get(2);
            }
        }
        return 2147483647;
    }

    void lerArquivo(String path) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(path));
        boolean lendoVertices = false;

        while (in.hasNextLine()) {
            String line = in.nextLine();

            if (line.contains("vertice")) {
                lendoVertices = true;
                continue;
            } else if (line.contains("edges")) {
                lendoVertices = false;
                continue;
            }

            String[] textoSeparado = line.split(" ");

            if (lendoVertices) {
                int value1 = Integer.parseInt(textoSeparado[0]);

                V.add(value1);
                R.add(textoSeparado[1]);

            } else if (lendoVertices == false) {
                List<Float> listaVertices = new ArrayList<>();

                float value1 = Float.parseFloat(textoSeparado[0]);
                float value2 = Float.parseFloat(textoSeparado[1]);
                float value3 = Float.parseFloat(textoSeparado[2]);

                listaVertices.add(value1);
                listaVertices.add(value2);
                listaVertices.add(value3);

                E.add(listaVertices);
            }
        }
        System.out.println(V);
        System.out.println(E);
    }

    public static void main(String[] args) throws FileNotFoundException {

        Grafo grafo = new Grafo();

        // Lendo arquivo de teste
        grafo.lerArquivo("src\\testes\\arvore_geradora_minima.txt");

        float[] vertice = {1.0f, 2.0f};
        float valor = grafo.w(vertice);
        System.out.println(valor);

        System.out.println(grafo.qtdVertices());

        System.out.println(grafo.qtdArestas());

        System.out.println(grafo.grau(4));

        System.out.println(grafo.rotulo(4));

        System.out.println(grafo.vizinhos(2).toString());

        float[] aresta = {1.0f, 2.0f};
        System.out.println(grafo.haAresta(aresta));

        System.out.println(grafo.peso(aresta));
    }

}
