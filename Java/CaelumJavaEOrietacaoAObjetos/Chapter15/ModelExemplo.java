public class ModelExemplo implements Comparable<ModelExemplo> {
    private String nome;
    private int nota;
    private double salario;

    public ModelExemplo(String nome, int nota, double salario) {
        this.nome = nome;
        this.nota = nota;
        this.salario = salario;
    }

    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return int return the nota
     */
    public int getNota() {
        return nota;
    }

    /**
     * @return double return the salario
     */
    public double getSalario() {
        return salario;
    }

    @Override
    public int compareTo(ModelExemplo arg0) {

        if (this.nota != arg0.nota) {
            return this.nota - arg0.nota;
        }

        if (this.salario != arg0.salario) {
            return (int) (this.salario - arg0.salario);
        }

        return this.nome.compareTo(arg0.nome);
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + " - Nota: " + this.nota + " - Salário: " + this.salario;
    }

}
