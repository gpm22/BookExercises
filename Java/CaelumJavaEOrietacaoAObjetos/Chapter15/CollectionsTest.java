import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CollectionsTest {
    public static void main(String[] args) {
        List<String> lista = new LinkedList<>();
        lista.add("Sérgio");
        lista.add("Paulo");
        lista.add("Guilherme");

        // Repare que o toString de ArrayList foi sobrescrito:
        System.out.println(lista);

        Collections.sort(lista);

        System.out.println(lista);

        List<ModelExemplo> listaModel = new ArrayList<>();

        listaModel.add(new ModelExemplo("Gabriel", 10, 20000));
        listaModel.add(new ModelExemplo("Guilherme", 10, 20000));
        listaModel.add(new ModelExemplo("Lucas", 8, 1212));
        listaModel.add(new ModelExemplo("Giovanni", 6, 4533));

        System.out.println(listaModel);

        Collections.sort(listaModel);

        System.out.println(listaModel);

        Collections.sort(listaModel, (s1, s2) -> (int) (s1.getSalario() - s2.getSalario()));

        System.out.println(listaModel);
    }
}