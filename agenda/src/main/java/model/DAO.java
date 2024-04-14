package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	/* Módulo de Conexão */

	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://localhost:3306/bancoagendajava?useTimeZone=true&serverTimeZone=UTC";
	
	/** The usuario. */
	private String usuario = "root";
	
	/** The senha. */
	private String senha = "MacielAraujo";
	// user=root&password=MacielAraujo"

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection conector = null;
		try {
			Class.forName(driver);
			conector = DriverManager.getConnection(url, usuario, senha);
			return conector;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * Teste conector.
	 */
	public void testeConector() {
		try {
			Connection con = conectar();
			System.out.println("Conexão Estabelecida: ");
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Teste de Conexão falhou:");
			System.out.println(e);
		}
	}

	//CRUD Create
	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	public void inserirContato(javaBeans contato) {
		String createSql = "insert into contatos(nome, fone, email) values(?,?,?)";
		try {
			// iniciar conexão com banco de dados
			Connection con = conectar();
			// preparar query para inserir dados
			PreparedStatement pst = con.prepareStatement(createSql);
			// substituir os termos (?) pelos valores do contato
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// executar query efetivando o envio dos dados ao banco
			pst.executeUpdate();
			// encerrar conexão por questões de segurança
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	public ArrayList<javaBeans> listarContatos() {
		ArrayList<javaBeans> contatos = new ArrayList<>();
		String readSql = "select * from contatos order by nome";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(readSql);
			// ResultSet guarda o retorno do banco em rs temporariamente, já que rs executa
			// a query
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				contatos.add(new javaBeans(id, nome, fone, email));
			}
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return contatos;
	}
	
	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	public void selecionarContato(javaBeans contato) {
		String seleciona = "select * from contatos where id=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(seleciona);
			pst.setString(1, contato.getId());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				contato.setId(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	public void alterarContato(javaBeans contato) {
		String atualiza = "update contatos set nome=?, fone=?, email=? where id=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(atualiza);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Excluir contato.
	 *
	 * @param contato the contato
	 */
	public void excluirContato(javaBeans contato) {
		//System.out.println(contato.getId());
		String deletar = "delete from contatos where id=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(deletar);
			pst.setString(1, contato.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
