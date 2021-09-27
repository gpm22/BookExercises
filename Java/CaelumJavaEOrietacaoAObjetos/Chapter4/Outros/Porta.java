public class Porta {
    boolean aberta;
    String cor;
    double dimensaoX = 10;
    double dimensaoY = 10;
    double dimensaoZ = 1;

    void abrir() {
        this.aberta = true;
    }

    void fechar() {
        this.aberta = false;
    }

    void pintar(String novaCor) {
        this.cor = novaCor;
    }

    boolean estaAberta() {
        return aberta;
    }

    String caracteristicas() {
        return "\ncor da porta: " + this.cor + "\nvolume da porta:" + (this.dimensaoX * this.dimensaoY * this.dimensaoZ)
                + "\n está berta? " + this.estaAberta();
    }
}
