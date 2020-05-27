package utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Excecoes.ArgumentosInvalidosException;
import gestao.Cliente;
import Excecoes.ArgumentosInvalidosException;

public class TransformadorClientJSON 
{
	public static int jsonParaNIF(String json) throws ArgumentosInvalidosException
	{
		int nif = -1;
		try 
		{
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			nif = ((Long) jsonObj.get("NIF")).intValue();
		} 
		catch(ClassCastException e)
		{
			throw new ArgumentosInvalidosException("Argumentos Inválidos");
		}
		catch(ParseException e)
		{
			throw new ArgumentosInvalidosException("Argumentos Inválidos");
		}
		return nif;
	}
	
	public static String jsonParaNome(String json) throws ArgumentosInvalidosException
	{
		String nome = "";
		try 
		{
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
			nome = (String) jsonObj.get("nome");
		} 
		catch(ClassCastException e)
		{
			throw new ArgumentosInvalidosException("Argumentos Inválidos");
		}
		catch (ParseException e) 
		{
			throw new ArgumentosInvalidosException("Argumentos Inválidos");
		}
		return nome;
	}
	
	public static Cliente jsonParaCliente(String json) throws ArgumentosInvalidosException
	{
		Cliente cliente = new Cliente();
		try 
		{
			JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);

			
			cliente = new Cliente(((Long) jsonObj.get("NIF")).intValue(),
					 				((Long) jsonObj.get("telefone")).intValue(),
									(String) jsonObj.get("nome"),
									(String) jsonObj.get("morada")
									);
		} 
		catch(ClassCastException e)
		{
			throw new ArgumentosInvalidosException("Argumentos Inválidos");
		}
		catch (ParseException e) 
		{
			throw new ArgumentosInvalidosException("Argumentos Inválidos");
		}
		
		return cliente;
	}
	
	public static String listaClientesParaJson(List<Cliente> clientes)
	{
		JSONArray clientesJSON = new JSONArray();
		Map<String,Object> m ;
		for(Cliente c : clientes)
		{
			m = new LinkedHashMap<>(4);
			m.put("NIF", c.getNIF());
			m.put("nome", c.getNome());
			m.put("morada", c.getMorada());
			m.put("telefone", c.getTelefone());
			clientesJSON.add(m);	
		}
		return clientesJSON.toJSONString();
	}
	
}
