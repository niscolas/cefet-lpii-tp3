package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.service.IManterHospede;
import br.cefetmg.inf.hosten.model.service.impl.ManterHospede;
import java.util.ArrayList;

public class ManterHospedeAdapter implements AdapterInterface {

    private final ArrayList listaRecebida;
    private Object objEnviado;
    private String tipoRetorno;

    public ManterHospedeAdapter(ArrayList lista) {
        this.listaRecebida = lista;

        operacao();
    }

    private void operacao() {
        System.err.println("Iniciando operação no adapter...");

        String operacao = (String) listaRecebida.get(1);

        try {

            IManterHospede manterHospede = new ManterHospede();

            switch (operacao) {
                case "Inserir": {
                    tipoRetorno = "Boolean";
                    Hospede itemInserir = (Hospede) listaRecebida.get(2);
                    objEnviado = manterHospede.inserir(itemInserir);

                    break;
                }
                case "Listar": {
                    tipoRetorno = "List<Hospede>";
                    String dadoBusca = (String) listaRecebida.get(2);
                    String coluna = (String) listaRecebida.get(3);
                    objEnviado = manterHospede.listar(dadoBusca, coluna);

                    break;
                }
                case "ListarTodos": {
                    tipoRetorno = "List<Hospede>";
                    objEnviado = manterHospede.listarTodos();

                    break;
                }
                case "Alterar": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    Hospede itemAlterar = (Hospede) listaRecebida.get(3);
                    objEnviado = manterHospede.alterar(codRegistro, itemAlterar);
                    break;
                }
                /*
                case "Excluir": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    objEnviado = manterHospede.excluir(codRegistro);
                    break;
                }
                */
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
