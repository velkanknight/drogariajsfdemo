package br.com.drogaria.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.factory.ConexaoFactory;

public class ProdutoDAO {
	
	public void salvar(Produto p) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produto ");
		sql.append("(descricao, quantidade, preco, fabricante_codigo_fab) ");
		sql.append("VALUES(?,?,?,?) ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setLong(2, p.getQuantidade());
		comando.setDouble(3, p.getPreco());
//Note que aqui como o fabricante_cod é um objeto, nos passamos o fabricante  e o codigo dele
		comando.setLong(4, p.getFabricante().getCodigo());
		comando.executeUpdate();
	}
	
	public ArrayList<Produto> listar() throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo_prod, p.descricao, p.preco, p.quantidade, f.codigo_fab, f.descricao ");
		sql.append("FROM produto p ");
		sql.append("INNER JOIN fabricante f ON f.codigo_fab = p.fabricante_codigo_fab ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		ResultSet resultado = comando.executeQuery();
		ArrayList<Produto> items = new ArrayList<Produto>();
		while (resultado.next()){
			
			Fabricante f = new Fabricante();
			f.setCodigo(resultado.getLong("f.codigo_fab"));
			f.setDescricao(resultado.getString("f.descricao"));
			
			Produto p = new Produto();
			p.setCodigo(resultado.getLong("p.codigo_prod"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setPreco(resultado.getDouble("p.preco"));
			p.setQuantidade(resultado.getLong("p.quantidade"));
			p.setFabricante(f);
			items.add(p);
		}
		
		return items;
	}
	
	public void excluir(Produto p) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE codigo_prod = ? ");
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());
		comando.executeUpdate();
		
	}
	
	public void editar(Produto p) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produto ");
		sql.append("SET descricao = ?, preco = ?, quantidade = ?, fabricante_codigo_fab = ? ");
		sql.append("WHERE codigo_prod = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFabricante().getCodigo());
		comando.setLong(5, p.getCodigo());
		
		comando.executeUpdate();
	}

}

















