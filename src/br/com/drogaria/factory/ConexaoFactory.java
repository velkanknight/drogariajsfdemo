package br.com.drogaria.factory;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class ConexaoFactory {
	
	//usuario maiusculo porque � uma variavel constante
	private static final String USUARIO = "root";
	private static final String SENHA = "velkan2512";
	//private static final String URL = "jdbc:mysql://127.3.118.130:3306/drogariajsfdemo";
	private static final String URL = "jdbc:mysql://localhost:3306/drogaria";
	
	//esse throws diz que a excess�o ser� tratada por quem chamar o metodo
	public static Connection conectar() throws SQLException{
		//aqui nos fazemos o registro do driver
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//variavel para receber a conexao aberta
		Connection conexao = (Connection) DriverManager.getConnection(URL, USUARIO, SENHA);
		//retornando a conexao aberta
		return conexao;
	}
	
	//public static void main(String[] args) {
		//tratando o erro
	//	try{
		
		//chamando o metodo conectar e armazenando na var conexao
	//	Connection conexao = ConexaoFactory.conectar();
	//	System.out.println("Conex�o realizada com sucesso!");
	//	}catch(SQLException ex){
			//esse comando exibe os erros que causarama  falha de conexao
	//		ex.printStackTrace();
	//		System.out.println("N�o foi poss�vel conectar!");
			
	//	}
	//}

}
