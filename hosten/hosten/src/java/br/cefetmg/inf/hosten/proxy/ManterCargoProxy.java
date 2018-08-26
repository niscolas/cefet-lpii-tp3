package br.cefetmg.inf.hosten.proxy;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.proxy.util.CallableClient;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;


public class ManterCargoProxy implements IManterCargo {

    public ManterCargoProxy() {
    }

    @Override
    public boolean inserir(Cargo cargo, List<String> listaProgramas) 
            throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Cargo");
        lista.add("Inserir");
        lista.add(cargo);
        lista.add(listaProgramas);
        
        return (boolean)operacaoRegistro(lista);
    }

    @Override
    public List<Cargo> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Cargo");
        lista.add("Listar");
        lista.add(dadoBusca);
        lista.add(coluna);
        
        return (List<Cargo>)operacaoRegistro(lista);
    }

    @Override
    public List<Cargo> listarTodos() throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Cargo");
        lista.add("ListarTodos");
        
        return (List<Cargo>)operacaoRegistro(lista);
    }

    @Override
    public boolean alterar(String codRegistro, 
            Cargo cargo, 
            List<String> listaProgramas) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Cargo");
        lista.add("Alterar");
        lista.add(codRegistro);
        lista.add(cargo);
        lista.add(listaProgramas);
        
        return (boolean)operacaoRegistro(lista);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        ArrayList lista = new ArrayList();
        lista.add("Cargo");
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
            switch (tipoObjeto) {
                case "Boolean":
                    return (boolean)listaRecebida.get(1);
                case "List<Cargo>":
                    return (List<Cargo>)listaRecebida.get(1);
                case "Exception":
                    throw (Exception)listaRecebida.get(1);
            }
        }   catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
