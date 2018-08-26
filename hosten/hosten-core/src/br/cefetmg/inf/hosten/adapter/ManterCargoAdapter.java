package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.model.service.impl.ManterCargo;
import java.util.ArrayList;
import java.util.List;

public class ManterCargoAdapter implements AdapterInterface {

    private final ArrayList listaRecebida;
    private Object objEnviado;
    private String tipoRetorno;

    public ManterCargoAdapter(ArrayList lista) {
        this.listaRecebida = lista;

        operacao();
    }

    private void operacao() {
        System.err.println("Iniciando operação no adapter...");

        String operacao = (String) listaRecebida.get(1);

        try {

            IManterCargo manterCargo = new ManterCargo();

            switch (operacao) {
                case "Inserir": {
                    tipoRetorno = "Boolean";
                    Cargo itemInserir = (Cargo) listaRecebida.get(2);
                    List<String> listaProgramas 
                            = (List<String>)listaRecebida.get(3);
                    
                    objEnviado = manterCargo.inserir(
                            itemInserir, 
                            listaProgramas);
                    
                    break;
                }
                case "Listar": {
                    tipoRetorno = "List<Cargo>";
                    String dadoBusca = (String) listaRecebida.get(2);
                    String coluna = (String) listaRecebida.get(3);
                    objEnviado = manterCargo.listar(dadoBusca, coluna);
                    
                    break;
                }
                case "ListarTodos": {
                    tipoRetorno = "List<Cargo>";
                    objEnviado = manterCargo.listarTodos();
                    
                    break;
                }
                case "Alterar": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    Cargo itemAlterar = (Cargo) listaRecebida.get(3);
                    List<String> listaProgramas 
                            = (List<String>) listaRecebida.get(4);
                    
                    objEnviado = manterCargo.alterar(
                            codRegistro, 
                            itemAlterar, 
                            listaProgramas);
                    
                    break;
                }
                case "Excluir": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    objEnviado = manterCargo.excluir(codRegistro);
                    
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
