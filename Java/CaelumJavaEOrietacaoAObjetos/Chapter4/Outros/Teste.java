public class Teste {
    public static void main(String[] args) {
        atividade3();
    }

    public static void atividade1() {
        Pessoa pessoa = new Pessoa();

        pessoa.nome = "Laetitia Casta";

        for (int i = 0; i < 30; i++) {
            pessoa.fazerAniversario();
            System.out.println(pessoa.quemSou());
        }

    }

    public static void atividade2() {

        Porta porta = new Porta();

        porta.abrir();
        porta.pintar("verde");

        System.out.println(porta.caracteristicas());

        porta.fechar();
        porta.pintar("marrom");

        System.out.println(porta.caracteristicas());

    }

    public static void atividade3() {
        Casa casa = new Casa();
        casa.porta1 = new Porta();
        casa.porta2 = new Porta();
        casa.porta3 = new Porta();

        casa.pintar("amarelo");
        casa.porta1.abrir();

        System.out.println(casa.caracteristicas());

        casa.pintar("verde");
        casa.porta2.abrir();

        System.out.println(casa.caracteristicas());

        casa.pintar("roxo");
        casa.porta3.abrir();

        System.out.println(casa.caracteristicas());
    }
}
