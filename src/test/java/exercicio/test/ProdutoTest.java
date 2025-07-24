package exercicio.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import exercicio.dao.generic.jdbc.dao.IProdutoDAO;
import exercicio.dao.generic.jdbc.dao.ProdutoDAO;
import exercicio.domain.Produto;

public class ProdutoTest {
	
	private IProdutoDAO produtoDAO;
	
	@Test
	public void cadastrarTest() throws Exception {
		produtoDAO = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("10");
		produto.setNome("Civic");
		produto.setPreco(100000.00);
		Integer countCad = produtoDAO.cadastrar(produto);
		assertTrue(countCad == 1);
		
		Produto produtoBD = produtoDAO.buscar("10");
		assertNotNull(produtoBD);
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		assertEquals(produto.getPreco(), produtoBD.getPreco());
		
		Integer countDel = produtoDAO.excluir(produtoBD);
		assertTrue(countDel == 1);
	}
	
	@Test
	public void atualizarTest() throws Exception {
		produtoDAO = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("10");
		produto.setNome("Civic");
		produto.setPreco(100000.00);
		Integer countCad = produtoDAO.cadastrar(produto);
		assertTrue(countCad == 1);
		
		Produto produtoBD = produtoDAO.buscar("10");
		assertNotNull(produtoBD);
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		assertEquals(produto.getPreco(), produtoBD.getPreco());
		
		produtoBD.setCodigo("20");
		produtoBD.setNome("Corsa");
		produtoBD.setPreco(20000.00);
		Integer countUpdate = produtoDAO.atualizar(produtoBD);
		assertTrue(countUpdate == 1);
		
		Produto produtoBD1 = produtoDAO.buscar("10");
		assertNull(produtoBD1);
		
		Produto produtoBD2 = produtoDAO.buscar("20");
		assertNotNull(produtoBD2);
		assertEquals(produtoBD.getId(), produtoBD2.getId());
		assertEquals(produtoBD.getCodigo(), produtoBD2.getCodigo());
		assertEquals(produtoBD.getNome(), produtoBD2.getNome());
		
		List<Produto> list = produtoDAO.buscarTodos();
		for (Produto pro : list) {
			produtoDAO.excluir(pro);
		}
	}
	
	@Test
	public void buscarTest() throws Exception {
		produtoDAO = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("10");
		produto.setNome("Civic");
		produto.setPreco(100000.00);
		Integer countCad = produtoDAO.cadastrar(produto);
		assertTrue(countCad == 1);
		
		Produto produtoBD = produtoDAO.buscar("10");
		assertNotNull(produtoBD);
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		assertEquals(produto.getPreco(), produtoBD.getPreco());
		
		Integer countDel = produtoDAO.excluir(produtoBD);
		assertTrue(countDel == 1);
	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		produtoDAO = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("10");
		produto.setNome("Caio Cesar");
		Integer countCad = produtoDAO.cadastrar(produto);
		assertTrue(countCad == 1);
		
		Produto produto2 = new Produto();
		produto.setCodigo("30");
		produto.setNome("Rodrigo Pires");
		Integer countCad2 = produtoDAO.cadastrar(produto2);
		assertTrue(countCad2 == 1);
		
		List<Produto> list = produtoDAO.buscarTodos();
		assertNotNull(list);
		assertEquals(2, list.size());
		
		int countDel = 0;
		for (Produto pro : list) {
			produtoDAO.excluir(pro);
			countDel++;
		}
		assertEquals(list.size(), countDel);
		
		list = produtoDAO.buscarTodos();
		assertEquals(list.size(), 0);
	}
	
	@Test
	public void excluirTest() throws Exception {
		produtoDAO = new ProdutoDAO();
		
		Produto produto = new Produto();
		produto.setCodigo("10");
		produto.setNome("Civic");
		produto.setPreco(100000.00);
		Integer countCad = produtoDAO.cadastrar(produto);
		assertTrue(countCad == 1);
		
		Produto produtoBD = produtoDAO.buscar("10");
		assertNotNull(produtoBD);
		assertEquals(produto.getCodigo(), produtoBD.getCodigo());
		assertEquals(produto.getNome(), produtoBD.getNome());
		assertEquals(produto.getPreco(), produtoBD.getPreco());
		
		Integer countDel = produtoDAO.excluir(produtoBD);
		assertTrue(countDel == 1);
	}

}
