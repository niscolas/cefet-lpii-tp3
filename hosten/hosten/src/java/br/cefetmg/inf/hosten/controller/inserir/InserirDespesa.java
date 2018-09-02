package br.cefetmg.inf.hosten.controller.inserir;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.proxy.ControlarDespesasProxy;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class InserirDespesa {
    public static String execute(HttpServletRequest request) {
        String jsp = "";
        try {
            int seqHospedagem = Integer.parseInt(request.getParameter("seqHospedagem"));
            int nroQuarto = Integer.parseInt(request.getParameter("nroQuarto"));
            Timestamp datConsumo = new Timestamp(System.currentTimeMillis());
            int qtdConsumo = Integer.parseInt(request.getParameter("qtdConsumo"));
            int seqServico = Integer.parseInt(request.getParameter("seqServico"));
            
            HttpSession sessao = request.getSession();
            String codUsuario = (String) sessao.getAttribute("codUsuario");
            
            QuartoConsumo despesa = new QuartoConsumo(seqHospedagem, nroQuarto, datConsumo, qtdConsumo, seqServico, codUsuario);
            
            IControlarDespesas controlarDespesas = new ControlarDespesasProxy();
            boolean testeRegistro = controlarDespesas.inserir(despesa);
            
            if (testeRegistro)
                request.setAttribute("mensagem", "Despesa inserida com sucesso");
            else
                request.setAttribute("mensagem", "Não foi possível inserir o registro do consumo!");
            
            jsp = "/servletweb?acao=ListarDetalhesConta";
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        return jsp;
    }
    
}
