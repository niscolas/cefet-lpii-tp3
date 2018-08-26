package br.cefetmg.inf.hosten.proxy;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.proxy.util.CallableClient;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;


public class ManterServicoProxy implements IManterServico {

    public ManterServicoProxy() {
    }

    @Override
    public boolean inserir(Servico servico) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Servico");
        lista.add("Inserir");
        lista.add(servico);
        
        return (boolean)operacaoRegistro(lista);
    }

    @Override
    public List<Servico> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Servico");
        lista.add("Listar");
        lista.add(dadoBusca);
        lista.add(coluna);
        
        return (List<Servico>)operacaoRegistro(lista);
    }

    @Override
    public List<Servico> listarTodos() throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Servico");
        lista.add("ListarTodos");
        
        return (List<Servico>)operacaoRegistro(lista);
    }

    @Override
    public boolean alterar(String codRegistro, Servico servico) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Servico");
        lista.add("Alterar");
        lista.add(codRegistro);
        lista.add(servico);
        
        return (boolean)operacaoRegistro(lista);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Servico");
        lista.add("Excluir");
        lista.add(codRegistro);
        
        return (boolean)operacaoRegistro(lista);
    }
    
    public Object operacaoRegistro (ArrayList lista) {
        try {
            FutureTask retornoCallableClient = new FutureTask(new CallableClient(lista));
            Thread t = new Thread(retornoCallableClient);
            t.start();
            
            ArrayList listaRecebida = ((ArrayList)retornoCallableClient.get());
            
            String tipoObjeto = (String)listaRecebida.get(0);
            if (tipoObjeto.equals("Boolean")) {
                return (boolean)listaRecebida.get(1);
            } else if (tipoObjeto.equals("List<Servico>")) {
                return (List<Servico>)listaRecebida.get(1);
            } else if (tipoObjeto.equals("Exception")) {
                throw (Exception)listaRecebida.get(1);
            }
        }   catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
