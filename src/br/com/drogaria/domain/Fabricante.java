package br.com.drogaria.domain;

public class Fabricante {
	// Long com L maiusculo porque não podemos ter 0 como ddefault
	private Long codigo;
	private String descricao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	//aqui estamos formatando o metodo para escrever os objetos na consulta
	@Override
	public String toString() {
		String saida = " "+ codigo + " - " + descricao;
		return saida;
	}

}
