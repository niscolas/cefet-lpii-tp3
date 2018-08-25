package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.dao.IItemConfortoDAO;
import br.cefetmg.inf.hosten.model.dao.impl.ItemConfortoDAO;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import java.util.ArrayList;

public class ManterItemConfortoAdapter implements AdapterInterface {
    private final ArrayList listaRecebida;
    private Object objEnviado;
    private String tipoRetorno;

    public ManterItemConfortoAdapter(ArrayList lista) {
        this.listaRecebida = lista;
        
        operacao();
    }

    private void operacao() {
        System.err.println("Iniciando operação no adapter...");
        
        String operacao = (String) listaRecebida.get(1);
        
        try {
        
            IItemConfortoDAO itemConfortoDAO = new ItemConfortoDAO();

            if (operacao.equals("Inserir")) {
                tipoRetorno = "Boolean";
                ItemConforto itemInserir = (ItemConforto)listaRecebida.get(2);
                objEnviado = itemConfortoDAO.adicionaItemConforto(itemInserir);
            } else if (operacao.equals("Listar")) {
                tipoRetorno = "List<ItemConforto>";
                String dadoBusca = (String)listaRecebida.get(2);
                String coluna = (String)listaRecebida.get(3);
                objEnviado = itemConfortoDAO.buscaItemConforto(dadoBusca, coluna);
            } else if (operacao.equals("ListarTodos")) {
                tipoRetorno = "List<ItemConforto>";
                objEnviado = itemConfortoDAO.buscaTodosItemConfortos();
            } else if (operacao.equals("Alterar")) {
                tipoRetorno = "Boolean";
                String codRegistro = (String)listaRecebida.get(2);
                ItemConforto itemAlterar = (ItemConforto)listaRecebida.get(3);
                objEnviado = itemConfortoDAO.atualizaItemConforto(codRegistro, itemAlterar);
            } else if (operacao.equals("Excluir")) {
                tipoRetorno = "Boolean";
                String codRegistro = (String)listaRecebida.get(2);
                objEnviado = itemConfortoDAO.deletaItemConforto(codRegistro);
            }
        } catch (Exception ex) {
            tipoRetorno = "Exception";
            objEnviado = ex;
        }
    }

    @Override
    public String getReturnObjectType() {
        System.out.println("tipo do objeto: " + tipoRetorno);
        return tipoRetorno;
    }

    @Override
    public Object getReturnObject() {
        return objEnviado;
    }
}
