package br.cefetmg.inf.hosten.proxy;

import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.proxy.util.CallableClient;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;


public class ManterUsuarioProxy implements IManterUsuario {

    @Override
    public boolean inserir(Usuario usuario) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Usuario");
        lista.add("Inserir");
        lista.add(usuario);
        
        return (boolean)operacaoRegistro(lista);
    }

    @Override
    public List<Usuario> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Usuario");
        lista.add("Listar");
        lista.add(dadoBusca);
        lista.add(coluna);
        
        return (List<Usuario>)operacaoRegistro(lista);
    }

    @Override
    public List<Usuario> listarTodos() throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Usuario");
        lista.add("ListarTodos");
        
        return (List<Usuario>)operacaoRegistro(lista);
    }

    @Override
    public boolean alterar(String codRegistro, Usuario usuario) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Usuario");
        lista.add("Alterar");
        lista.add(codRegistro);
        lista.add(usuario);
        
        return (boolean)operacaoRegistro(lista);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Usuario");
        lista.add("Excluir");
        lista.add(codRegistro);
        
        return (boolean)operacaoRegistro(lista);
    }
    
    @Override
    public Usuario usuarioLogin(String email, String senha) {
        ArrayList lista = new ArrayList();
        lista.add("Usuario");
        lista.add("Login");
        lista.add(email);
        lista.add(senha);
        
        return (Usuario)operacaoRegistro(lista);
    }
    
    public Object operacaoRegistro (ArrayList lista) {
        try {
            FutureTask retornoCallableClient = new FutureTask(new CallableClient(lista));
            Thread t = new Thread(retornoCallableClient);
            t.start();
            
            ArrayList listaRecebida = ((ArrayList)retornoCallableClient.get());
            
            String tipoObjeto = (String)listaRecebida.get(0);
            switch (tipoObjeto) {
                case "Boolean":
                    return (boolean)listaRecebida.get(1);
                case "Usuario":
                    return (Usuario)listaRecebida.get(1);
                case "List<Usuario>":
                    return (List<Usuario>)listaRecebida.get(1);
                case "Exception":
                    throw (Exception)listaRecebida.get(1);
            }
        }   catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
