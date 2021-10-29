public class Funcionario {
    private String nome;
    private String cpf;
    private double salario;

    public double getBonificacao() {
        return this.salario * 0.10;
    }

    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return String return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return double return the salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Nome: " + this.getNome() + "\nCpf: " + this.getCpf() + "\nSalario: R$" + this.getSalario()
                + "\nBonificação: R$" + this.getBonificacao();
    }

}
