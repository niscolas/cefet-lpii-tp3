package br.cefetmg.inf.hosten.proxy;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.proxy.util.CallableClient;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;


public class ManterItemConfortoProxy implements IManterItemConforto {

    @Override
    public boolean inserir(ItemConforto itemConforto) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("ItemConforto");
        lista.add("Inserir");
        lista.add(itemConforto);
        
        return (boolean)operacaoRegistro(lista);
    }

    @Override
    public List<ItemConforto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("ItemConforto");
        lista.add("Listar");
        lista.add(dadoBusca);
        lista.add(coluna);
        
        return (List<ItemConforto>)operacaoRegistro(lista);
    }

    @Override
    public List<ItemConforto> listarTodos() throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("ItemConforto");
        lista.add("ListarTodos");
        
        return (List<ItemConforto>)operacaoRegistro(lista);
    }

    @Override
    public boolean alterar(String codRegistro, ItemConforto itemConforto) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("ItemConforto");
        lista.add("Alterar");
        lista.add(codRegistro);
        lista.add(itemConforto);
        
        return (boolean)operacaoRegistro(lista);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("ItemConforto");
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
            } else if (tipoObjeto.equals("List<ItemConforto>")) {
                return (List<ItemConforto>)listaRecebida.get(1);
            } else if (tipoObjeto.equals("Exception")) {
                throw (Exception)listaRecebida.get(1);
            }
        }   catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
