public class Secretario extends Funcionario {

    @Override
    public double getBonificacao() {
        return this.getSalario() * 0.10;
    }

}
