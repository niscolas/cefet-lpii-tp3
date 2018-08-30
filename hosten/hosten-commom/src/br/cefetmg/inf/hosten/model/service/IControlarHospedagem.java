package br.cefetmg.inf.hosten.model.service;

public interface IControlarHospedagem {
    public boolean efetuarCheckIn(String nroQuarto, String codCPF, int diasEstadia, int nroAdultos, int nroCriancas);
    public int efetuarCheckOut(String nroQuarto);
}
