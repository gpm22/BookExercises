package br.com.gpm22.interfaces;

import br.com.gpm22.banco.Cliente;

public interface Tributavel {

    public double getValorImposto();

    public Cliente getTitular();

}