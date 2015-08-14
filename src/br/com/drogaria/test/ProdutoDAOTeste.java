package br.com.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTeste {
	//Esse @test � um aviso p/ o Junit que esse metodo � um metodo de teste
	@Test
	@Ignore
	public void salvar() throws SQLException{
		
		Produto p = new Produto();
		p.setDescricao("NOVALGINA 10 COMPRIMIDOS");
		p.setPreco(2.45);
		p.setQuantidade(13L);
		
		Fabricante f = new Fabricante();
		f.setCodigo(1L);
		p.setFabricante(f);
		
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.salvar(p);
		
	}
			
	@Test
	@Ignore
	public void listar() throws SQLException{
		
		ProdutoDAO pdao = new ProdutoDAO();
		ArrayList<Produto>lista = pdao.listar(); 
		
		for(Produto p : lista){
			System.out.println("C�digo " +p.getCodigo());
			System.out.println("Descri��o " +p.getDescricao());
			System.out.println("Pre�o " +p.getPreco());
			System.out.println("Quantidade " +p.getQuantidade());
			System.out.println("C�digo do Fabricante " +p.getFabricante().getCodigo());
			System.out.println("Descri��o do Fabricante " +p.getFabricante().getDescricao());
			System.out.println();
		}
	}
	@Test
	@Ignore
	public void excluir() throws SQLException{
		Produto p = new Produto();
		p.setCodigo(5L);
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.excluir(p);
		
	}
	@Test
	@Ignore
	public void editar() throws SQLException{
		Produto p = new Produto();
		p.setCodigo(4L);
		p.setDescricao("Alcool Hidratado");
		p.setPreco(5.75d);
		p.setQuantidade(5L);
		
		Fabricante f= new Fabricante();
		f.setCodigo(5L);
		p.setFabricante(f);
		
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.editar(p);
				
		
	}
}












