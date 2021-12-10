import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class TestaPerformanceArrayListAndHashMap {

    public static void main(String[] args) {
        System.out.println("Iniciando...");
        Collection<Integer> testeHashSet = new HashSet<>();
        Collection<Integer> testeArrayList = new ArrayList<>();

        int total = 100000;

        long inicioHashSet = System.currentTimeMillis();

        for (int i = 0; i < total; i++) {
            testeHashSet.add(i);
        }

        long meioHashSet = System.currentTimeMillis();
        long tempoMedioHashSet = meioHashSet - inicioHashSet;
        System.out.println("Tempo gasto para inserir no HashSet: " + tempoMedioHashSet);

        for (int i = 0; i < total; i++) {
            testeHashSet.contains(i);
        }

        long fimHashSet = System.currentTimeMillis();
        long tempoPesquisaHashSet = fimHashSet - meioHashSet;

        System.out.println("Tempo gasto para pesquisar no HashSet: " + tempoPesquisaHashSet);

        long tempoFinalHashSet = fimHashSet - inicioHashSet;
        System.out.println("Tempo gasto total no HashSet: " + tempoFinalHashSet);

        long inicioArrayList = System.currentTimeMillis();

        for (int i = 0; i < total; i++) {
            testeArrayList.add(i);
        }

        long meioArrayList = System.currentTimeMillis();
        long tempoMedioArrayList = meioArrayList - inicioArrayList;
        System.out.println("Tempo gasto para inserir no ArrayList: " + tempoMedioArrayList);

        for (int i = 0; i < total; i++) {
            testeArrayList.contains(i);
        }

        long fimArrayList = System.currentTimeMillis();
        long tempoPesquisaArrayList = fimArrayList - meioArrayList;

        System.out.println("Tempo gasto para pesquisar no ArrayList: " + tempoPesquisaArrayList);

        long tempoFinalArrayList = fimArrayList - inicioArrayList;
        System.out.println("Tempo gasto total no ArrayList: " + tempoFinalArrayList);
    }
}