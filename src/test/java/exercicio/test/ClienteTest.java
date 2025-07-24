package exercicio.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import exercicio.dao.generic.jdbc.dao.ClienteDAO;
import exercicio.dao.generic.jdbc.dao.IClienteDAO;
import exercicio.domain.Cliente;

public class ClienteTest {
	
	private IClienteDAO clienteDAO;
	
	@Test
	public void cadastrarTest() throws Exception {
		clienteDAO = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("10");
		cliente.setNome("Caio Cesar");
		Integer countCad = clienteDAO.cadastrar(cliente);
		assertTrue(countCad == 1);
		
		Cliente clienteBD = clienteDAO.buscar("10");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer countDel = clienteDAO.excluir(clienteBD);
		assertTrue(countDel == 1);
	}
	
	@Test
	public void atualizarTest() throws Exception {
		clienteDAO = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("10");
		cliente.setNome("Caio Cesar");
		Integer countCad = clienteDAO.cadastrar(cliente);
		assertTrue(countCad == 1);
		
		Cliente clienteBD = clienteDAO.buscar("10");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		clienteBD.setCodigo("20");
		clienteBD.setNome("Caio Oliveira");
		Integer countUpdate = clienteDAO.atualizar(clienteBD);
		assertTrue(countUpdate == 1);
		
		Cliente clienteBD1 = clienteDAO.buscar("10");
		assertNull(clienteBD1);
		
		Cliente clienteBD2 = clienteDAO.buscar("20");
		assertNotNull(clienteBD2);
		assertEquals(clienteBD.getId(), clienteBD2.getId());
		assertEquals(clienteBD.getCodigo(), clienteBD2.getCodigo());
		assertEquals(clienteBD.getNome(), clienteBD2.getNome());
		
		List<Cliente> list = clienteDAO.buscarTodos();
		for (Cliente cli : list) {
			clienteDAO.excluir(cli);
		}
		
	}
	
	@Test
	public void buscarTest() throws Exception {
		clienteDAO = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("10");
		cliente.setNome("Caio Cesar");
		Integer countCad = clienteDAO.cadastrar(cliente);
		assertTrue(countCad == 1);
		
		Cliente clienteBD = clienteDAO.buscar("10");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer countDel = clienteDAO.excluir(clienteBD);
		assertTrue(countDel == 1);
	}
	
	@Test
	public void buscarTodosTest() throws Exception {
		clienteDAO = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("10");
		cliente.setNome("Caio Cesar");
		Integer countCad = clienteDAO.cadastrar(cliente);
		assertTrue(countCad == 1);
		
		Cliente cliente2 = new Cliente();
		cliente.setCodigo("30");
		cliente.setNome("Rodrigo Pires");
		Integer countCad2 = clienteDAO.cadastrar(cliente2);
		assertTrue(countCad2 == 1);
		
		List<Cliente> list = clienteDAO.buscarTodos();
		assertNotNull(list);
		assertEquals(2, list.size());
		
		int countDel = 0;
		for (Cliente cli : list) {
			clienteDAO.excluir(cli);
			countDel++;
		}
		assertEquals(list.size(), countDel);
		
		list = clienteDAO.buscarTodos();
		assertEquals(list.size(), 0);
	}
	
	@Test
	public void excluirTest() throws Exception {
		clienteDAO = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("10");
		cliente.setNome("Caio Cesar");
		Integer countCad = clienteDAO.cadastrar(cliente);
		assertTrue(countCad == 1);
		
		Cliente clienteBD = clienteDAO.buscar("10");
		assertNotNull(clienteBD);
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer countDel = clienteDAO.excluir(clienteBD);
		assertTrue(countDel == 1);
	}
	
	

}
