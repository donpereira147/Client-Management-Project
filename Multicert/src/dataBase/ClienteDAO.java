package dataBase;

import gestao.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import Excecoes.ClienteNaoExisteException;
import Excecoes.ListaDeClientesVaziaException;
import Excecoes.ClienteJaExisteException;

public class ClienteDAO 
{
	private static Connection conexao;
	
	public static void adicionaCliente(Cliente c) throws ClienteJaExisteException
	{
		try
		{
			conexao = Connect.connect();
			PreparedStatement ps = 
					conexao.prepareStatement( "insert into Cliente values(?,?,?,?)");
			ps.setInt(1, c.getNIF());
			ps.setString(2, c.getNome());
			ps.setString(3, c.getMorada());
			ps.setInt(4, c.getTelefone());
			
			ps.executeUpdate();
					
		}
		catch(MySQLIntegrityConstraintViolationException e)
		{
			throw new ClienteJaExisteException("Cliente já existe");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally { Connect.close(conexao);}	
	}
	
	//synchronized por causa da conta dos registos antes e depois. Esta conta determina se o cliente existe ou não
	public synchronized static void apagaCliente(int nif) throws ClienteNaoExisteException
	{
		int contaAntes = 0, contaDepois = 0;
		try
		{
			conexao = Connect.connect();
			
			PreparedStatement contaAntesPS = conexao.prepareStatement("select count(*) from cliente");
			ResultSet rs = contaAntesPS.executeQuery();
			if(rs.next())
				contaAntes = rs.getInt(1);
			
			PreparedStatement ps =
					conexao.prepareStatement("delete from Cliente where nif=?");
			ps.setInt(1, nif);
			
			ps.executeUpdate();
			
			PreparedStatement contaDepoisPS = conexao.prepareStatement("select count(*) from cliente");
			ResultSet rs1 = contaDepoisPS.executeQuery();
			if(rs1.next())
				contaDepois = rs1.getInt(1);
			
			if(contaAntes == contaDepois)
				throw new ClienteNaoExisteException("O cliente com o NIF" + nif + " não existe.\n");
				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally { Connect.close(conexao);}
	}
	
	
	
	public static List<Cliente> listaClientes() throws ListaDeClientesVaziaException
	{
		//acc servirá para determinar se a lista está ou não vazia
		int acc = 0;
		List<Cliente> clientes = new ArrayList<>();
		
		try 
		{
			conexao = Connect.connect();
			PreparedStatement ps = conexao.prepareStatement("select * from cliente");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				acc++;
				 clientes.add( new Cliente(rs.getInt(1),
						 			rs.getInt(4),
						 			rs.getString(2),
						 			rs.getString(3)));
			}
			if(acc == 0)
				throw new ListaDeClientesVaziaException("A lista de clientes encontra-se vazia.\n");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			//throw new Exception(e);
		}
		finally
		{
			Connect.close(conexao);
		}
		return clientes;
		
	}
	
	public static Cliente infoClienteAtravesDoNIF(int nif) throws ClienteNaoExisteException
	{
		Cliente cliente = new Cliente();
		try 
		{
			conexao = Connect.connect();
			PreparedStatement ps = 
					conexao.prepareStatement("select * from cliente where nif = ?");
			ps.setInt(1, nif);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				cliente = new Cliente(nif, rs.getInt(4), rs.getString(2), rs.getString(3));
			else
				throw new ClienteNaoExisteException("O cliente não existe, logo não há informação a obter.\n");
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {Connect.close(conexao);}
		
		return cliente;
	}
	
	public static List<Cliente> listaClientesAtravesDeNome(String nome) throws ListaDeClientesVaziaException
	{
		//acc servirá para determinar se a lista está ou não vazia
		int acc = 0;
		List<Cliente> clientes = new ArrayList<>();
		
		try 
		{
			conexao = Connect.connect();
			
			PreparedStatement ps =
					conexao.prepareStatement("select * from cliente where nome = ?");
			ps.setString(1, nome);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				acc++;
				 clientes.add( new Cliente(rs.getInt(1),
				 			rs.getInt(4),
				 			rs.getString(2),
				 			rs.getString(3)));
			}
			if(acc == 0)
				throw new ListaDeClientesVaziaException("Lista de clientes vazia.\n");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {Connect.close(conexao);}
		
		return clientes;
	}
}
