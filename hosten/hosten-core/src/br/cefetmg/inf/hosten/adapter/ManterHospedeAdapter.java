package br.cefetmg.inf.hosten.adapter;

import java.util.ArrayList;

public class ManterHospedeAdapter implements AdapterInterface {
    private ArrayList lista;
    private String tipoRetorno;

    public ManterHospedeAdapter(ArrayList lista) {
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
