package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.model.service.impl.ManterServicoArea;
import java.util.ArrayList;

public class ManterServicoAreaAdapter implements AdapterInterface {

    private final ArrayList listaRecebida;
    private Object objEnviado;
    private String tipoRetorno;

    public ManterServicoAreaAdapter(ArrayList lista) {
        this.listaRecebida = lista;

        operacao();
    }

    private void operacao() {
        System.err.println("Iniciando operação no adapter...");

        String operacao = (String) listaRecebida.get(1);

        try {

            IManterServicoArea manterServicoArea = new ManterServicoArea();

            switch (operacao) {
                case "Inserir": {
                    tipoRetorno = "Boolean";
                    ServicoArea itemInserir = (ServicoArea) listaRecebida.get(2);
                    objEnviado = manterServicoArea.inserir(itemInserir);

                    break;
                }
                case "Listar": {
                    tipoRetorno = "List<ServicoArea>";
                    String dadoBusca = (String) listaRecebida.get(2);
                    String coluna = (String) listaRecebida.get(3);
                    objEnviado = manterServicoArea.listar(dadoBusca, coluna);

                    break;
                }
                case "ListarTodos": {
                    tipoRetorno = "List<ServicoArea>";
                    objEnviado = manterServicoArea.listarTodos();

                    break;
                }
                case "Alterar": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    ServicoArea itemAlterar = (ServicoArea) listaRecebida.get(3);
                    objEnviado = manterServicoArea.alterar(codRegistro, itemAlterar);
                    break;
                }
                case "Excluir": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    objEnviado = manterServicoArea.excluir(codRegistro);
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
