package br.com.drogaria.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {
	// o thorws manda o erro de sql exception para quem chamar
	public void salvar(Fabricante f) throws SQLException {
		// AQUI USAMOS O STRING BUILD POR SER MAIS SIMPLES PARA CONCANETAR
		StringBuilder sql = new StringBuilder();
		// gerando o sql para submeter
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES(?)");

		// CONECTANDO COM O BANCO
		Connection conexao = ConexaoFactory.conectar();
		// essa classe permite voce executar comando sql
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		// mapei a descricao do fabricante para a primeira "?" do VALUES (?)
		comando.setString(1, f.getDescricao());
		// executar o comando
		comando.executeUpdate();
	}

	public void excluir(Fabricante f) throws SQLException {
		// AQUI USAMOS O STRING BUILD POR SER MAIS SIMPLES PARA CONCANETAR
		StringBuilder sql = new StringBuilder();
		// gerando o sql para submeter
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo_fab = ? ");

		// CONECTANDO COM O BANCO
		Connection conexao = ConexaoFactory.conectar();
		// essa classe permite voce executar comando sql, usei o tostrinf para
		// aceitar o stringbuilder
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		// mapiei o ? e adicionei o codigo no lugar do ?
		comando.setLong(1, f.getCodigo());
		// executar o comando
		comando.executeUpdate();
	}

	public void editar(Fabricante f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo_fab = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();
	}

	public Fabricante buscarPorCod(Fabricante f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo_fab, descricao ");
		sql.append("FROM Fabricante ");
		sql.append("WHERE codigo_fab = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		// aqui é diferente porque aqui existe um retorno da consulta
		comando.executeQuery();
		// armazendo o resultado na var resultado atraves do metodo executequery
		// do obj comando
		ResultSet resultado = comando.executeQuery();
		// aqui é null pq se ele n achar nd na consulta, ele fica nulo
		Fabricante retorno = null;
		// aqui verificamos se houve retorno, se n o fabricante vale null
		// so usano if porque essa consulta so gera 1 retorno
		if (resultado.next()) {
			// se encontrar algo, instancia o obj do tipo fab
			retorno = new Fabricante();
			// popula as informações
			retorno.setCodigo(resultado.getLong("codigo_fab"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		return retorno;
	}

	public ArrayList<Fabricante> listar() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo_fab, descricao ");
		sql.append("FROM Fabricante ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.executeQuery();
		// armazendo o resultado na var resultado atraves do metodo executequery
		// do obj comando
		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		while (resultado.next()) {
			Fabricante f = new Fabricante();
			f.setCodigo(resultado.getLong("codigo_fab"));
			f.setDescricao(resultado.getString("descricao"));

			lista.add(f);
		}

		return lista;
	}

	public ArrayList<Fabricante> listarPorDescricao(Fabricante f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo_fab, descricao ");
		sql.append("FROM Fabricante ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		// aqui colocamos esse %, para ele ignorar na busca caracteres antes e
		// depois do que foi escrito para encontrar
		// isso possibilita encontrar palavras msm q estiverem dentro de outras
		// ex:maria r:anamaria
		comando.setString(1, "%" + f.getDescricao() + "%");
		// armazendo o resultado na var resultado atraves do metodo executequery
		// do obj comando
		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		while (resultado.next()) {
			Fabricante item = new Fabricante();
			item.setCodigo(resultado.getLong("codigo_fab"));
			item.setDescricao(resultado.getString("descricao"));

			lista.add(item);
		}

		return lista;
	}

	// teste
	public static void main(String[] args) {
		// TESTAR O INSERT
		/*
		 * Fabricante f1 = new Fabricante(); f1.setDescricao("Fabricante 3");
		 * Fabricante f2 = new Fabricante(); f2.setDescricao("Fabricante 4");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO(); try { fdao.salvar(f1);
		 * fdao.salvar(f2); System.out.println("Fabricantes Salvos!"); } catch
		 * (SQLException e) { System.out.println("Erro ao salvar!");
		 * e.printStackTrace(); }
		 */
		// TESTAR O EXCLUIR
		/*
		 * Fabricante f1 = new Fabricante(); //Esse L é porque é long, se n pro
		 * ele da erro f1.setCodigo(2L); FabricanteDAO fdao = new
		 * FabricanteDAO(); try { fdao.excuir(f1); System.out.println(
		 * "Registro deletado!"); } catch (SQLException e) { System.out.println(
		 * "Erro ao tentar deletar registro!"); e.printStackTrace(); }
		 */

		// testar editar
		/*
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(1L);
		 * f1.setDescricao("Fabricante5"); FabricanteDAO fdao= new
		 * FabricanteDAO(); try { fdao.editar(f1); System.out.println(
		 * "Editado com Sucesso!"); } catch (SQLException e) {
		 * System.out.println("Error ao Editar!"); e.printStackTrace(); }
		 */

		// testar consultar
		/*
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(1L); FabricanteDAO
		 * fdao= new FabricanteDAO();
		 * 
		 * try { Fabricante f3 = fdao.buscarPorCod(f1); System.out.println(
		 * "Resultado 1:"+ f3); } catch (SQLException e) { System.out.println(
		 * "Erro na Consulta"); e.printStackTrace(); }
		 */
		// testar listar

		/*FabricanteDAO fdao = new FabricanteDAO();

		try {
			ArrayList<Fabricante> lista = fdao.listar();

			for (Fabricante f : lista) {
				System.out.println("Resultado: " + f);
			}

		} catch (SQLException e) {
			System.out.println("Erro na Consulta de lista");
			e.printStackTrace();
		}
		*/
		Fabricante f1 = new Fabricante();
		f1.setDescricao("2");
		FabricanteDAO fdao = new FabricanteDAO();
		
		try {
			ArrayList<Fabricante> lista = fdao.listarPorDescricao(f1);

			for (Fabricante f : lista) {
				System.out.println("Resultado: " + f);
			}

		} catch (SQLException e) {
			System.out.println("Erro na Consulta de lista por descricao");
			e.printStackTrace();
		}

	}

}
