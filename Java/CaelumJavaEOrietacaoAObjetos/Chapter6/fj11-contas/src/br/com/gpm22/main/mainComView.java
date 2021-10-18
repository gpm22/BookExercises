package br.com.gpm22.main;

import br.com.gpm22.bancomvc.BancoView;

public class mainComView {

	public static void main(String[] args) {

		BancoView bancoView = new BancoView();

		try {
			bancoView.menuInicial();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
