import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AlgoritmoDeHierholz {

    boolean ehCiclo;
    List<Integer> caminho = new ArrayList<>();
    List<Boolean> C = new ArrayList<>(); //arestas visitadas

    ReturnWithDifferentTypes buscaCiclo(Grafo grafo, float vertice) {

        List<Integer> ciclo = new ArrayList<>();
        float t = vertice; //t é onde termina o ciclo

        LinkedList<List<Float>> arestasNaoVisitadas = new LinkedList<>();
        arestasNaoVisitadas = grafo.E;

        while (true) {
            List<Float> arestaNaoVisitada = null;
            for (int i = 0; i < arestasNaoVisitadas.size(); i++) {

                if ((arestasNaoVisitadas.get(i).get(0).equals(vertice) || (arestasNaoVisitadas.get(i).get(1).equals(vertice)))
                        && this.C.get(i).equals(false)) {

                    arestaNaoVisitada = arestasNaoVisitadas.get(i); // O(1)
                    this.C.set(i, true);
                    break;
                }
            }

            if (arestaNaoVisitada == null) {
                this.ehCiclo = false;
                this.caminho = null;
                return new ReturnWithDifferentTypes(false, null).getTypes();
            } else {
                if (arestaNaoVisitada.get(0) == vertice) {
                    vertice = arestaNaoVisitada.get(1);
                } else {
                    vertice = arestaNaoVisitada.get(0);
                }

                ciclo.add((int)vertice);
            }

            if (vertice == t) {
                break;
            }
        }

        boolean loopEnd = false;
        while (true) {
            float verticeComArestaNaoVisitada = 0.0f;

            for (int i = 0; i < this.C.size(); i++) {
                if (this.C.get(i).equals(false)) {
                    verticeComArestaNaoVisitada = grafo.E.get(i).get(0);
                    loopEnd = false;
                    break;
                }
                loopEnd = true;
            }

            if (loopEnd) {
                break;
            }

            ReturnWithDifferentTypes retornoFuncao;
            retornoFuncao = buscaCiclo(grafo, verticeComArestaNaoVisitada);

            if (!retornoFuncao.getEhCiclo()) {
                return retornoFuncao;
            }
        }

        return new ReturnWithDifferentTypes(true, ciclo);
    }

    ReturnWithDifferentTypes algoritmoDeHierholz(Grafo grafo) {
        int quantidadeArestas = grafo.qtdArestas();

        for (int i = 0; i < quantidadeArestas; i++) {
            this.C.add(false);
        }

        int v = 1; //vertice arbitrario

        ReturnWithDifferentTypes retornoFuncao = buscaCiclo(grafo, v); //return ;

        if (!retornoFuncao.getEhCiclo()) {
            return retornoFuncao;
        } else {
            for (int i = 0; i < this.C.size(); i++) {
                if (!this.C.get(i)) {
                    return new ReturnWithDifferentTypes(false, null);
                }
            }
            return retornoFuncao;
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Grafo grafo = new Grafo();
        grafo.lerArquivo("C:\\Users\\raque\\OneDrive\\Área de Trabalho\\github\\grafos\\Grafo\\Grafos\\src\\testes\\ContemCicloEuleriano.txt");

        AlgoritmoDeHierholz algoritmo = new AlgoritmoDeHierholz();
        ReturnWithDifferentTypes ehEuleriano = algoritmo.algoritmoDeHierholz(grafo);
        System.out.println(ehEuleriano.getEhCiclo());
    }
}
