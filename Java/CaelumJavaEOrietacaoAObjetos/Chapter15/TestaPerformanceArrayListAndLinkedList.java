import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestaPerformanceArrayListAndLinkedList {

    public static void main(String[] args) {
        System.out.println("Iniciando...");

        int total = 100000;

        long inicioLinkedList = System.currentTimeMillis();

        LinkedList<Integer> testeLinkedList = new LinkedList<>();

        for (int i = 0; i < total; i++) {
            testeLinkedList.add(0, i);
        }

        long meioLinkedList = System.currentTimeMillis();
        double tempoMeioLinkedList = (meioLinkedList - inicioLinkedList);
        System.out.println("Tempo gasto na inserção da posição inicial da LinkedList: " + tempoMeioLinkedList);

        for (int i = 0; i < total; i++) {
            testeLinkedList.get(i);
        }

        long meio2LinkedList = System.currentTimeMillis();
        double tempoMeio2LinkedList = (meio2LinkedList - meioLinkedList);
        System.out.println("Tempo gasto no get da LinkedList: " + tempoMeio2LinkedList);

        for (int num : testeLinkedList) {

        }

        long meio3LinkedList = System.currentTimeMillis();
        double tempoMeio3LinkedList = (meio3LinkedList - meio2LinkedList);
        System.out.println("Tempo gasto no forEach da LinkedList: " + tempoMeio3LinkedList);

        long fimLinkedList = System.currentTimeMillis();
        double tempoLinkedList = (fimLinkedList - inicioLinkedList);
        System.out.println("Tempo total gasto na LinkedList: " + tempoLinkedList);

        long inicioArrayList = System.currentTimeMillis();

        List<Integer> testeArrayList = new ArrayList<>();

        for (int i = 0; i < total; i++) {
            testeArrayList.add(0, i);
        }

        long meioArrayList = System.currentTimeMillis();
        double tempoMeioArrayList = (meioArrayList - inicioArrayList);
        System.out.println("Tempo gasto na inserção da posição inicial da ArrayList: " + tempoMeioArrayList);

        for (int i = 0; i < total; i++) {
            testeArrayList.get(i);
        }

        long meio2ArrayList = System.currentTimeMillis();
        double tempoMeio2ArrayList = (meio2ArrayList - meioArrayList);
        System.out.println("Tempo gasto no get da ArrayList: " + tempoMeio2ArrayList);

        for (int num : testeArrayList) {

        }
        long meio3ArrayList = System.currentTimeMillis();
        double tempoMeio3ArrayList = (meio3ArrayList - meio2ArrayList);
        System.out.println("Tempo gasto no forEach da ArrayList: " + tempoMeio3ArrayList);

        long fimArrayList = System.currentTimeMillis();
        double tempoArrayList = (fimArrayList - inicioArrayList);
        System.out.println("Tempo total gasto na ArrayList: " + tempoArrayList);
    }

}
