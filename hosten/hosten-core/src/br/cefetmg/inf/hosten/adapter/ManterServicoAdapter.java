package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.model.service.impl.ManterServico;
import java.util.ArrayList;

public class ManterServicoAdapter implements AdapterInterface {

    private final ArrayList listaRecebida;
    private Object objEnviado;
    private String tipoRetorno;

    public ManterServicoAdapter(ArrayList lista) {
        this.listaRecebida = lista;

        operacao();
    }

    private void operacao() {
        System.err.println("Iniciando operação no adapter...");

        String operacao = (String) listaRecebida.get(1);

        try {

            IManterServico manterServico = new ManterServico();

            switch (operacao) {
                case "Inserir": {
                    tipoRetorno = "Boolean";
                    Servico itemInserir = (Servico) listaRecebida.get(2);
                    objEnviado = manterServico.inserir(itemInserir);

                    break;
                }
                case "Listar": {
                    tipoRetorno = "List<Servico>";
                    String dadoBusca = (String) listaRecebida.get(2);
                    String coluna = (String) listaRecebida.get(3);
                    objEnviado = manterServico.listar(dadoBusca, coluna);

                    break;
                }
                case "ListarTodos": {
                    tipoRetorno = "List<Servico>";
                    objEnviado = manterServico.listarTodos();

                    break;
                }
                case "Alterar": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    Servico itemAlterar = (Servico) listaRecebida.get(3);
                    objEnviado = manterServico.alterar(codRegistro, itemAlterar);
                    break;
                }
                case "Excluir": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    objEnviado = manterServico.excluir(codRegistro);
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
