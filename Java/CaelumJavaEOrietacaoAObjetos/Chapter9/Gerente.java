public class Gerente extends Funcionario implements Autenticavel {
    private int senha;
    private int numeroDeFuncionariosGerenciados;

    @Override
    public double getBonificacao() {
        return this.getSalario() * 0.15;
    }

    public boolean autentica(int senha) {
        if (this.senha == senha) {
            System.out.println("Acesso Permitido!");
            return true;
        } else {
            System.out.println("Acesso Negado!");
            return false;
        }
    }

    /**
     * @return int return the senha
     */
    public int getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(int senha) {
        this.senha = senha;
    }

    /**
     * @return int return the numeroDeFuncionariosGerenciados
     */
    public int getNumeroDeFuncionariosGerenciados() {
        return numeroDeFuncionariosGerenciados;
    }

    /**
     * @param numeroDeFuncionariosGerenciados the numeroDeFuncionariosGerenciados to
     *                                        set
     */
    public void setNumeroDeFuncionariosGerenciados(int numeroDeFuncionariosGerenciados) {
        this.numeroDeFuncionariosGerenciados = numeroDeFuncionariosGerenciados;
    }

    @Override
    public String toString() {
        return super.toString() + "\nNumero de Funcionarios Gerenciados: " + this.getNumeroDeFuncionariosGerenciados();
    }
}
