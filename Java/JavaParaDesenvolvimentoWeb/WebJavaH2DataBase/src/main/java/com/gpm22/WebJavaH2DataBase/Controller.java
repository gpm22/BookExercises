package com.gpm22.WebJavaH2DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gpm22.WebJavaH2DataBase.models.Contato;
import com.gpm22.WebJavaH2DataBase.daos.ContatoDAO;

@RestController
public class Controller {

	@GetMapping("/")
	public String index() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		System.out.print("Conexão aberta!");
		connection.close();
		return "deu tudo certo!";
	}

	@GetMapping("/insere-contato")
	public String insereContato(@RequestParam String nome,
			@RequestParam String email,
			@RequestParam String endereco,
			@RequestParam String data) throws ParseException {

		ContatoDAO dao = new ContatoDAO();
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setDataNascimento(getCalendarByString(data));

		// método elegante
		Contato adicionado = dao.adiciona(contato);

		System.out.println("Gravado!");
		return adicionado.toString();
	}

	@GetMapping("/lista-de-contatos")
	public String getContatos() {
		ContatoDAO dao = new ContatoDAO();

		List<Contato> contatos = dao.getTodosOsContatos();

		contatos.forEach(System.out::println);

		return contatos
				.stream()
				.map(Contato::toString)
				.reduce("", (res, element) -> res + "\n" + element + "\n");
	}

	@GetMapping("/contatos-por-nome")
	public String getContatosPorNome(@RequestParam String nome) {
		ContatoDAO dao = new ContatoDAO();

		List<Contato> contatos = dao.getContatosPorNome(nome);

		contatos.forEach(System.out::println);

		return contatos
				.stream()
				.map(Contato::toString)
				.reduce("", (res, element) -> res + "\n" + element + "\n");
	}

	@GetMapping("/contato-por-id")
	public String getContatosPorId(@RequestParam Long id) {
		ContatoDAO dao = new ContatoDAO();

		Contato contato = dao.getContatoPorId(id);
		String str = contato.toString();
		System.out.println(str);
		return str;
	}

	@GetMapping("/remove-por-id")
	public String removeContato(@RequestParam Long id) {
		ContatoDAO dao = new ContatoDAO();

		Contato contato = new Contato();
		contato.setId(id);

		dao.remove(contato);
		return "removido!";
	}

	@GetMapping("/atualiza-por-id")
	public String atualizaContato(@RequestParam String nome,
			@RequestParam String email,
			@RequestParam String endereco,
			@RequestParam String data,
			@RequestParam Long id) throws ParseException {

		ContatoDAO dao = new ContatoDAO();

		Contato contato = new Contato();

		contato.setId(id);
		contato.setNome(nome);
		contato.setEndereco(data);

		contato.setDataNascimento(getCalendarByString(data));

		dao.altera(contato);
		return dao.getContatoPorId(id).toString();
	}

	private Calendar getCalendarByString(String data) throws ParseException{

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatter.parse(data));
		return calendar;
	}
}
