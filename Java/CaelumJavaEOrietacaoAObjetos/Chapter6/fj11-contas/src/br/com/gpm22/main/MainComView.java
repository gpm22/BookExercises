package br.com.gpm22.main;

//import br.com.gpm22.bancomvc.BancoRepositorio;
import br.com.gpm22.bancomvc.BancoView;

public class MainComView {

	public static void main(String[] args) {

		BancoView bancoView = new BancoView();

		try {
			// BancoRepositorio.iniciarContasDeTeste();
			bancoView.menuInicial();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
