package pack;
import Excecoes.ArgumentosInvalidosException;
import Excecoes.ClienteJaExisteException;
import Excecoes.ClienteNaoExisteException;
import Excecoes.ListaDeClientesVaziaException;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import gestao.Cliente;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import static utils.TransformadorClientJSON.jsonParaCliente;
import static utils.TransformadorClientJSON.jsonParaNIF;
import static utils.TransformadorClientJSON.jsonParaNome;
import static utils.TransformadorClientJSON.listaClientesParaJson;
import static dataBase.ClienteDAO.adicionaCliente;
import static dataBase.ClienteDAO.apagaCliente;
import static dataBase.ClienteDAO.listaClientes;
import static dataBase.ClienteDAO.infoClienteAtravesDoNIF;
import static dataBase.ClienteDAO.listaClientesAtravesDeNome;

@Path("webservice")
public class WebService {

    public WebService() { }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("adicionarCliente")
    public String adicionarCliente(String json)
    {
    	String resultado ="";
    	try
    	{
    		Cliente cliente = jsonParaCliente(json);
        	adicionaCliente(cliente);
        	resultado = "cliente adicionado com sucesso";
    	}
    	catch(ArgumentosInvalidosException e)
    	{
    		resultado = e.getMessage();
    	}
    	catch(ClienteJaExisteException e)
    	{
    		resultado = e.getMessage();
    	}
    	return resultado;
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("apagarCliente")
    public String apagarCliente(String json)
    {
    	String resultado ="";
    	try
    	{
    		int nif = jsonParaNIF(json);
        	apagaCliente(nif);
        	resultado = "cliente removido com sucesso";
    	}
    	catch(ArgumentosInvalidosException e)
    	{
    		resultado = e.getMessage();
    	}
    	catch(ClienteNaoExisteException e)
    	{
    		resultado = e.getMessage();
    	}
    	return resultado;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("todosClientes")
    public String listaTodosOsClientes()
    {
    	String resultado;
    	try 
    	{
    		resultado = listaClientesParaJson(listaClientes());
		} catch (ListaDeClientesVaziaException e) {
			resultado = e.getMessage();
		}
    	
    	return resultado;
    }
    
    //@GET --> implementação correta, no entanto, tem de ser um post devido às limitações do javascript
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("infoNIF")
    public String infoNIF(String json)
    {
    	String resultado ="";
    	try
    	{
    		List<Cliente> clienteLista = new ArrayList<>();
        	Cliente cliente = infoClienteAtravesDoNIF(
        							jsonParaNIF(json));
        	
        	clienteLista.add(cliente);
        	resultado = listaClientesParaJson(clienteLista);
    	}
    	catch(ArgumentosInvalidosException e)
    	{
    		resultado = e.getMessage();
    	}
    	catch(ClienteNaoExisteException e)
    	{
    		resultado = e.getMessage();
    	}
    	
    	return resultado;
    }
    
    
    //@GET --> implementação correta, no entanto, tem de ser um post devido às limitações do javascript
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("clientesNome")
    public String listaClientesComNome(String json)
    {
    	String resultado;
    	try 
    	{
    		resultado = listaClientesParaJson(
    						listaClientesAtravesDeNome(
    								jsonParaNome(json)));
		} 	
    	catch(ArgumentosInvalidosException e)
    	{
    		resultado = e.getMessage();
    	}
    	catch (ListaDeClientesVaziaException e) {
			resultado = e.getMessage();
		}
    	
    	return resultado;
    }
    
 

}
