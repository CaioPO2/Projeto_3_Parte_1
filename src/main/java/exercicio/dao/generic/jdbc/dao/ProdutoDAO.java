package exercicio.dao.generic.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exercicio.dao.generic.jdbc.ConnectionFactory;
import exercicio.domain.Produto;

public class ProdutoDAO implements IProdutoDAO {

	@Override
	public Integer cadastrar(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlInsert();
			stm = connection.prepareStatement(sql);
			adicionarParametrosInsert(stm, produto);
			return stm.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
	}

	@Override
	public Integer atualizar(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlUpdate();
			stm = connection.prepareStatement(sql);
			adicionarParametrosUpdate(stm, produto);
			return stm.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
	}

	@Override
	public Produto buscar(String codigo) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Produto produto = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlSelect();
			stm = connection.prepareStatement(sql);
			adicionarParametrosSelect(stm, codigo);
			rs = stm.executeQuery();
			
			if (rs.next()) {
				produto = new Produto();
				Long id = rs.getLong("id");
				String nome = rs.getString("nome");
				String cd = rs.getString("codigo");
				Double preco = rs.getDouble("preco");
				produto.setId(id);
				produto.setNome(nome);
				produto.setCodigo(cd);
				produto.setPreco(preco);
			}
		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
		return produto;
	}

	@Override
	public List<Produto> buscarTodos() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Produto> list = new ArrayList<>();
		Produto produto = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlSelectAll();
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			
			while (rs.next()) {
				produto = new Produto();
				Long id = rs.getLong("id");
				String nome = rs.getString("nome");
				String cd = rs.getString("codigo");
				Double preco = rs.getDouble("preco");
				produto.setId(id);
				produto.setNome(nome);
				produto.setCodigo(cd);
				produto.setPreco(preco);
				list.add(produto);
			}
		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
		return list;
	}

	@Override
	public Integer excluir(Produto produto) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = getSqlDelete();
			stm = connection.prepareStatement(sql);
			adicionarParametrosDelete(stm, produto);
			return stm.executeUpdate();
		} catch(Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
	}
	
	private String getSqlInsert() {
		StringBuilder sb = new StringBuilder();
		sb.append("insert into tb_produto (id, codigo, nome, preco) ");
		sb.append("values (nextval('sq_produto'),?,?,?");
		return sb.toString();
	}

	private void adicionarParametrosInsert(PreparedStatement stm, Produto produto) throws SQLException {
		stm.setString(1, produto.getCodigo());
		stm.setString(2, produto.getNome());
		stm.setDouble(2, produto.getPreco());
	}
	
	private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			if (connection != null & !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	private String getSqlUpdate() {
		StringBuilder sb = new StringBuilder();
		sb.append("update tb_produto");
		sb.append("set nome = ?, set codigo = ?, set preco = ?");
		sb.append("where id = ?");
		return sb.toString();
	}

	private void adicionarParametrosUpdate(PreparedStatement stm, Produto produto) throws SQLException {
		stm.setString(1, produto.getNome());
		stm.setString(2, produto.getCodigo());
		stm.setDouble(3, produto.getPreco());
		stm.setLong(4, produto.getId());
	}
	
	private String getSqlSelect() {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from tb_produto");
		sb.append("where codigo = ?");
		return sb.toString();
	}

	private void adicionarParametrosSelect(PreparedStatement stm, String codigo) throws SQLException {
		stm.setString(1, codigo);	
	}

	private String getSqlSelectAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from tb_produto");
		return sb.toString();
	}
	
	private String getSqlDelete() {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from tb_produto");
		sb.append("where codigo = ?");
		return sb.toString();
	}
	
	private void adicionarParametrosDelete(PreparedStatement stm, Produto produto) throws SQLException {
		stm.setString(1, produto.getCodigo());
	}

}
