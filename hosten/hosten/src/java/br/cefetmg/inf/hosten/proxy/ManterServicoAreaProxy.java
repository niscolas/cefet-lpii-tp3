package br.cefetmg.inf.hosten.proxy;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.proxy.util.CallableClient;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;


public class ManterServicoAreaProxy implements IManterServicoArea {

    public ManterServicoAreaProxy() {
    }

    @Override
    public boolean inserir(ServicoArea servicoArea) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("ServicoArea");
        lista.add("Inserir");
        lista.add(servicoArea);
        
        return (boolean)operacaoRegistro(lista);
    }

    @Override
    public List<ServicoArea> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("ServicoArea");
        lista.add("Listar");
        lista.add(dadoBusca);
        lista.add(coluna);
        
        return (List<ServicoArea>)operacaoRegistro(lista);
    }

    @Override
    public List<ServicoArea> listarTodos() throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("ServicoArea");
        lista.add("ListarTodos");
        
        return (List<ServicoArea>)operacaoRegistro(lista);
    }

    @Override
    public boolean alterar(String codRegistro, ServicoArea servicoArea) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("ServicoArea");
        lista.add("Alterar");
        lista.add(codRegistro);
        lista.add(servicoArea);
        
        return (boolean)operacaoRegistro(lista);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("ServicoArea");
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
            } else if (tipoObjeto.equals("List<ServicoArea>")) {
                return (List<ServicoArea>)listaRecebida.get(1);
            } else if (tipoObjeto.equals("Exception")) {
                throw (Exception)listaRecebida.get(1);
            }
        }   catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
