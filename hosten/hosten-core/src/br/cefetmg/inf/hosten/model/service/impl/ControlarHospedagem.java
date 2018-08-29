package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.dao.ICategoriaQuartoDAO;
import br.cefetmg.inf.hosten.model.dao.IHospedagemDAO;
import br.cefetmg.inf.hosten.model.dao.IQuartoDAO;
import br.cefetmg.inf.hosten.model.dao.impl.CategoriaQuartoDAO;
import br.cefetmg.inf.hosten.model.dao.impl.HospedagemDAO;
import br.cefetmg.inf.hosten.model.dao.impl.QuartoDAO;
import br.cefetmg.inf.hosten.model.dao.rel.IQuartoHospedagemDAO;
import br.cefetmg.inf.hosten.model.dao.rel.impl.QuartoHospedagemDAO;
import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ControlarHospedagem implements IControlarHospedagem {    

    @Override
    public boolean efetuarCheckIn(String nroQuarto, String codCPF, int diasEstadia, int nroAdultos, int nroCriancas) {
        // data check-in
        Date dataAtual = new Date();
        Timestamp dataCheckIn = new Timestamp(dataAtual.getTime());
        // data check-out
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.format(dataAtual);
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(dataAtual);
        calendario.add(Calendar.DATE, +diasEstadia);
        Timestamp dataCheckOut = new Timestamp(calendario.getTimeInMillis());
            
        // vlrDiaria
        IQuartoDAO quartoDAO = QuartoDAO.getInstance();
        List<Quarto> listaQuarto;
        try {
            listaQuarto = quartoDAO.buscaQuarto("nroQuarto", nroQuarto);
            String codCategoria = listaQuarto.get(0).getCodCategoria();

            ICategoriaQuartoDAO categoriaDAO = CategoriaQuartoDAO.getInstance();
            List<CategoriaQuarto> categorias = categoriaDAO.buscaCategoriaQuarto("codCategoria", codCategoria);
            Double valorDiaria = categorias.get(0).getVlrDiaria();

            double valorTotal = valorDiaria*diasEstadia;

            // ----------------------------------------------------------------------------------------------------------------------------------------
            // realiza a operação de check-in
            Hospedagem hosp = new Hospedagem(dataCheckIn, dataCheckOut, valorTotal, codCPF);
            IHospedagemDAO hospDAO = HospedagemDAO.getInstance();

            hospDAO.adicionaHospedagem(hosp);
            List<Hospedagem> hospEncontrada = hospDAO.busca(hosp);

            IQuartoHospedagemDAO quartoHosp = QuartoHospedagemDAO.getInstance();
            int seqHospedagem = hospEncontrada.get(0).getSeqHospedagem();
            QuartoHospedagem objAdicionar = new QuartoHospedagem(seqHospedagem, Integer.parseInt(nroQuarto), nroAdultos, nroCriancas, valorDiaria);
            boolean testeAddQuarto = quartoHosp.adiciona(objAdicionar);

            // atualiza o idtOcupado do quarto pra ocupado
            Quarto quartoAtualizado = listaQuarto.get(0);
            quartoAtualizado.setIdtOcupado(true);
            boolean testeAtualizaQuarto = quartoDAO.atualizaQuarto(nroQuarto, quartoAtualizado);
            
            return (testeAddQuarto && testeAtualizaQuarto);
        } catch (SQLException ex) {

        }

        return false;
    }

    @Override
    public boolean efetuarCheckOut(String nroQuarto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
