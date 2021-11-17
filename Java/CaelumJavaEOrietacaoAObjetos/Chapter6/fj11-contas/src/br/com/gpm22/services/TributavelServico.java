package br.com.gpm22.services;

import java.util.List;

import br.com.gpm22.interfaces.Tributavel;

public class TributavelServico {

    public static double calculaImpostos(List<Tributavel> tributaveis) {
        int total = 0;

        for (Tributavel tributavel : tributaveis) {

            if (tributavel == null) {
                continue;
            }

            total += tributavel.getValorImposto();
        }

        return total;
    }

}
