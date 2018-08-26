package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterItemConforto;
import br.cefetmg.inf.hosten.model.service.impl.ManterItemConforto;
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

            IManterItemConforto manterItemConforto = new ManterItemConforto();

            switch (operacao) {
                case "Inserir": {
                    tipoRetorno = "Boolean";
                    ItemConforto itemInserir = (ItemConforto) listaRecebida.get(2);
                    objEnviado = manterItemConforto.inserir(itemInserir);

                    break;
                }
                case "Listar": {
                    tipoRetorno = "List<ItemConforto>";
                    String dadoBusca = (String) listaRecebida.get(2);
                    String coluna = (String) listaRecebida.get(3);
                    objEnviado = manterItemConforto.listar(dadoBusca, coluna);

                    break;
                }
                case "ListarTodos": {
                    tipoRetorno = "List<ItemConforto>";
                    objEnviado = manterItemConforto.listarTodos();

                    break;
                }
                case "Alterar": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    ItemConforto itemAlterar = (ItemConforto) listaRecebida.get(3);
                    objEnviado = manterItemConforto.alterar(codRegistro, itemAlterar);
                    break;
                }
                case "Excluir": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    objEnviado = manterItemConforto.excluir(codRegistro);
                    break;
                }
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
