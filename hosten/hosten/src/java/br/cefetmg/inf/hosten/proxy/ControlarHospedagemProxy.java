package br.cefetmg.inf.hosten.proxy;

import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;

public class ControlarHospedagemProxy implements IControlarHospedagem {    

    @Override
    public boolean efetuarCheckIn(String nroQuarto, String codCPF, int diasEstadia, int nroAdultos, int nroCriancas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int efetuarCheckOut(String nroQuarto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
