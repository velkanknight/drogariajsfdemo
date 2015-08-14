package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

//isso (@managedbean) configura minha classe para dizer que é um
//managed bean, ou seja q estara por tras de uma interface
//esse nome é usado para o xhtml achar o .java
@ManagedBean(name = "MBFabricante")

// esse comando diz q esse bean só estara na memoria
// enquanto o usuario estiver na tela referente a essa ui
@ViewScoped
public class FabricanteBean {
	// bean são varivaveis de tela
	private ArrayList<Fabricante> items;
	// essa variavel ira guardar quando precisar salvar, edita rou excluir um
	// fabricante
	private Fabricante fabricante;
	private ArrayList<Fabricante> itemsFiltrado;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ArrayList<Fabricante> getItems() {
		return items;
	}

	public void setItems(ArrayList<Fabricante> items) {
		this.items = items;
	}
	public ArrayList<Fabricante> getItemsFiltrado() {
		return itemsFiltrado;
	}
	public void setItemsFiltrado(ArrayList<Fabricante> itemsFiltrado) {
		this.itemsFiltrado = itemsFiltrado;
	}

	// programando as ações
	// esse codigo diz que o metodo será chamado antes da tela ser desenhada
	// @postconstruction
	@PostConstruct
	public void prepararPesquisa() {
		// adicionando uma msg no catch da classe JSF que criamos
		try {
			FabricanteDAO fdao = new FabricanteDAO();
			// Aqui nós populamos o vetor lista com a consulta do fdao
			items = fdao.listar();
		
			} catch (SQLException ex) {
			// esse codigo imprime a pilha de execução do erro do console
			ex.printStackTrace();
			JSFUtil.adicionarMsgError(ex.getMessage());
		}

	}
	
	//public void prepararExcluir() {
		//esse getrowdata ele retorna o registro que vc clica na tela
		//fabricante = items.getRowData();
	//}

	public void prepararNovo() {
		fabricante = new Fabricante();
	}
	public void Excluir() {
		
		try {
			FabricanteDAO fdao = new FabricanteDAO();
			fdao.excluir(fabricante);
			items = fdao.listar();
			
			JSFUtil.adicionarMsgSuccess("Removido com Sucesso!");
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			JSFUtil.adicionarMsgError(ex.getMessage());
		}
	}
public void Editar() {
		
		try {
			FabricanteDAO fdao = new FabricanteDAO();
			fdao.editar(fabricante);
			items = fdao.listar();
			
			JSFUtil.adicionarMsgSuccess("Editado com Sucesso!");
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			JSFUtil.adicionarMsgError(ex.getMessage());
		}
	}



	public void gravarFabricante() {
		FabricanteDAO fdao = new FabricanteDAO();

		try {
			fdao.salvar(fabricante);
			// Aqui, depois que nos gravamos um registro, criamos um vetor
			// lista, que recebe o conteudo do listar do dao
			// e passa pro items da tela a listdatamodel atualizar a tela assim
			// que vc gravar.
			items = fdao.listar();
			

			JSFUtil.adicionarMsgSuccess("Salvo com Sucesso!");
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			JSFUtil.adicionarMsgError(ex.getMessage());
		}
	}

}
