package br.com.gpm22.services;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import br.com.gpm22.banco.Cliente;
import br.com.gpm22.bancomvc.BancoRepositorio;

public class ClienteServico {

    public static boolean validarCPF(String cpf) {
        return validarCPFFormato(cpf) && validarCPFCalculo(cpf);
    }

    private static boolean validarCPFFormato(String cpf) {
        String cpfValidation = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}";

        return Pattern.compile(cpfValidation).matcher(cpf).matches();
    }

    private static boolean validarCPFCalculo(String cpf) {
        int[] digitosCpf = Stream.of(cpf.replaceAll("[^0-9]", "").split("")).mapToInt(Integer::parseInt).toArray();
        return validarDigitoDoCpf(digitosCpf, 1) && validarDigitoDoCpf(digitosCpf, 2)
                && verificarCPFInvalidoConhecido(digitosCpf);
    }

    private static boolean verificarCPFInvalidoConhecido(int[] cpf) {

        for (int i = 1; i < cpf.length; i++) {
            if (cpf[i - 1] != cpf[i]) {
                return true;
            }
        }

        return false;
    }

    private static boolean validarDigitoDoCpf(int[] cpf, int digito) {

        int digitoPosicao = 0;

        int valorComparacao;

        if (digito == 1) {
            digitoPosicao = 9;
        }

        if (digito == 2) {
            digitoPosicao = 10;
        }

        int soma = 0;

        for (int i = 0; i < digitoPosicao; i++) {
            soma += cpf[i] * (digitoPosicao + 1 - i);
        }

        valorComparacao = ((soma * 10) % 11);

        if (valorComparacao == 10) {
            valorComparacao = 0;
        }

        return cpf[digitoPosicao] == valorComparacao;
    }

    public static boolean validarCPFUnico(String cpf) {
        List<Cliente> clientes = BancoRepositorio.retornarClientes();
        for (Cliente cliente : clientes) {
            if (cpf.equals(cliente.getCpf())) {
                return false;
            }
        }
        return true;
    }
}
