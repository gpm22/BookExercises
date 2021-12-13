package br.com.gpm22.entidades;

import br.com.gpm22.entidades.contas.Conta;
import br.com.gpm22.exceptions.DataInvalidaException;

public class Banco {
    private String nome;
    private int numero;
    private Conta[] contas;

    public Banco(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
        this.contas = new Conta[10];
    }

    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return int return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @return Conta[] return the contas
     */
    public Conta[] getContas() {
        return contas;
    }

    public int adiciona(Conta c) {

        if (this.contas[contas.length - 1] != null) {
            this.contas = this.gerarArrayContasMaior(this.contas);
        }

        for (int i = 0; i < this.contas.length; i++) {
            if (this.contas[i] == null) {
                this.contas[i] = c;
                return i;
            }
        }

        return -1;
    }

    private Conta[] gerarArrayContasMaior(Conta[] arrayAntigo) {
        Conta[] arrayNovo = new Conta[arrayAntigo.length + 1];

        for (int i = 0; i < arrayAntigo.length; i++) {
            arrayNovo[i] = arrayAntigo[i];
        }

        return arrayNovo;
    }

    public void mostraContas() throws DataInvalidaException {
        for (Conta conta : this.contas) {
            if (conta == null) {
                break;
            }
            System.out.println(conta.recuperarDadosParaImpressao());
        }
    }

    public int contem(Conta conta) {

        for (int i = 0; i < this.contas.length; i++) {
            if (contas[i].equals(conta)) {
                return i;
            }
        }

        return -1;
    }

}
