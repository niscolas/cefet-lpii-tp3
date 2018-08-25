package br.cefetmg.inf.hosten.adapter;

import java.util.ArrayList;

public class ManterServicoAreaAdapter implements AdapterInterface {
    private ArrayList lista;
    private String tipoRetorno;

    public ManterServicoAreaAdapter(ArrayList lista) {
        this.lista = lista;
        
        operacao();
    }

    private void operacao() {
        
    }

    @Override
    public Object getReturnObject() {
        return lista;
    }

    @Override
    public String getReturnObjectType() {
        return tipoRetorno;
    }

}
