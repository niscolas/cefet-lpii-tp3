package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.model.service.impl.ManterCategoriaQuarto;
import java.util.ArrayList;
import java.util.List;

public class ManterCategoriaQuartoAdapter implements AdapterInterface {

    private final ArrayList listaRecebida;
    private Object objEnviado;
    private String tipoRetorno;

    public ManterCategoriaQuartoAdapter(ArrayList lista) {
        this.listaRecebida = lista;

        operacao();
    }

    private void operacao() {
        System.err.println("Iniciando operação no adapter...");

        String operacao = (String) listaRecebida.get(1);

        try {

            IManterCategoriaQuarto manterCategoriaQuarto = new ManterCategoriaQuarto();

            switch (operacao) {
                case "Inserir": {
                    tipoRetorno = "Boolean";
                    CategoriaQuarto itemInserir = (CategoriaQuarto) listaRecebida.get(2);
                    List<ItemConforto> itensCategoria 
                            = (List<ItemConforto>) listaRecebida.get(3);
                    
                    objEnviado = manterCategoriaQuarto.inserir(
                            itemInserir, itensCategoria);

                    break;
                }
                case "Listar": {
                    tipoRetorno = "List<CategoriaQuarto>";
                    String dadoBusca = (String) listaRecebida.get(2);
                    String coluna = (String) listaRecebida.get(3);
                    objEnviado = manterCategoriaQuarto.listar(dadoBusca, coluna);

                    break;
                }
                case "ListarTodos": {
                    tipoRetorno = "List<CategoriaQuarto>";
                    objEnviado = manterCategoriaQuarto.listarTodos();

                    break;
                }
                case "Alterar": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    CategoriaQuarto itemAlterar = (CategoriaQuarto) listaRecebida.get(3);
                    List<ItemConforto> itensCategoria 
                            = (List<ItemConforto>) listaRecebida.get(4);
                    
                    objEnviado = manterCategoriaQuarto
                            .alterar(codRegistro, itemAlterar, itensCategoria);
                    
                    break;
                }
                case "Excluir": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    objEnviado = manterCategoriaQuarto.excluir(codRegistro);
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
