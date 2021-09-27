public class Pessoa {
    String nome;
    int idade;

    void fazerAniversario() {
        idade++;
    }

    String quemSou() {
        return "Meu nome é " + this.nome + " e minha idade é: " + this.idade;
    }
}
