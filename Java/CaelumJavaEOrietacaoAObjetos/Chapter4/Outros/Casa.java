package Outros;

public class Casa {
    String cor;
    Porta porta1;
    Porta porta2;
    Porta porta3;

    void pintar(String novaCor) {
        this.cor = novaCor;
    }

    int numeroDePortasAbertas() {
        int portasAbertas = 0;
        Porta[] portas = { this.porta1, this.porta2, this.porta3 };

        for (Porta porta : portas) {
            if (porta.estaAberta()) {
                portasAbertas++;
            }
        }

        return portasAbertas;
    }

    String caracteristicas() {
        return "\nCor: " + this.cor + "\nNúmero de portas abertas: " + this.numeroDePortasAbertas();
    }
}
