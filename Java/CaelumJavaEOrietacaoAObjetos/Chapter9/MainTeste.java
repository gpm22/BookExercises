public class MainTeste {
    public static void main(String[] args) {
        Gerente gerente = new Gerente();

        gerente.setNome("Marquinhos Leandro");
        gerente.setSenha(4330);
        gerente.setSalario(5000);

        System.out.println(gerente);

        ControleDeBonificacoes controle = new ControleDeBonificacoes();

        controle.registra(gerente);

        Gerente funcionario1 = new Gerente();
        funcionario1.setSalario(5000.0);
        controle.registra(funcionario1);

        Funcionario funcionario2 = new Funcionario();
        funcionario2.setSalario(1000.0);
        controle.registra(funcionario2);

        System.out.println(controle.getTotalDeBonificacoes());
    }
}
