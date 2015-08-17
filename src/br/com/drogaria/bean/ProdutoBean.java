package br.com.drogaria.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name="MBProduto")
@ViewScoped
public class ProdutoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Produto> items;
	private ArrayList<Produto> itemsFiltrados;
	private Produto produto;
	private ArrayList<Fabricante> comboFabricante;
	
	
	public ProdutoBean(){
		System.out.println("Construtor");
	}
	
	@PostConstruct
	private void inicializacao(){
		carregarListagem("BRuno");
	}
	
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<Fabricante> getComboFabricante() {
		return comboFabricante;
	}

	public void setComboFabricante(ArrayList<Fabricante> comboFabricante) {
		this.comboFabricante = comboFabricante;
	}

	public ArrayList<Produto> getItemsFiltrados() {
		return itemsFiltrados;
	}

	public void setItemsFiltrados(ArrayList<Produto> itemsFiltrados) {
		this.itemsFiltrados = itemsFiltrados;
	}

	public ArrayList<Produto> getItems() {
		return items;
	}

	public void setItems(ArrayList<Produto> items) {
		this.items = items;
	}
	
	public void carregarListagem(String nome){
		System.out.println(nome);
		ProdutoDAO pdao = new ProdutoDAO();
		try {
			items = pdao.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.adicionarMsgError(e.getMessage());
		}
		
	}
	 public void prepararNovo(){
		 try{
		 produto = new Produto();
		 FabricanteDAO dao = new  FabricanteDAO();
		 
		 comboFabricante = dao.listar();
		 }catch(SQLException e){
			 e.printStackTrace();
			 JSFUtil.adicionarMsgError(e.getMessage());
			 
			 
		 }
	 }
	public void novo(){
				
		try {
			ProdutoDAO pdao = new ProdutoDAO();
			pdao.salvar(produto);
			items = pdao.listar();
			JSFUtil.adicionarMsgError("Produto Salvo com Sucesso!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSFUtil.adicionarMsgError(e.getMessage());
		}
		
		
	}
	public void excluir(){
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluir(produto);
			items = dao.listar();
			JSFUtil.adicionarMsgError("Produto excluído com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMsgError(e.getMessage());
		}
			
	}
	public void prepararEditar(){
		try {
			FabricanteDAO dao = new FabricanteDAO();
			comboFabricante = dao.listar();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMsgError(e.getMessage());
		}
	}
	public void editar(){
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.editar(produto);
			items =dao.listar();
			JSFUtil.adicionarMsgError("Editado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMsgError(e.getMessage());
		}
	}

}