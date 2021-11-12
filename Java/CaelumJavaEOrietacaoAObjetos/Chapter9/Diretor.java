public class Diretor extends Gerente {

    @Override
    public double getBonificacao() {
        return this.getSalario() * 0.5 + 1000;
    }

}
