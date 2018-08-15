package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.dao.impl.QuartoDAO;
import br.cefetmg.inf.hosten.model.dao.rel.QuartoHospedagemDAO;
import br.cefetmg.inf.hosten.model.dao.rel.impl.QuartoHospedagemDAOImpl;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.hosten.model.service.ManterQuarto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public class ManterQuartoImpl implements ManterQuarto {

    QuartoDAO objetoDAO;

    public ManterQuartoImpl() {
        objetoDAO = QuartoDAO.getInstance();
    }

    @Override
    public boolean inserir(Quarto quarto)
            throws NegocioException, SQLException {
        // confere se já existe algum quarto com aquele número
        List<Quarto> quartosPesquisados
                = listar(quarto.getNroQuarto(), "nroQuarto");

        if (quartosPesquisados.isEmpty()) {
            // pode inserir
            boolean testeRegistro = objetoDAO.adiciona(quarto);
            return testeRegistro;
        } else {
            // tem quarto com o mesmo número
            throw new NegocioException("Número do quarto repetido!");
        }
    }

    @Override
    public boolean alterar(String codRegistro, Quarto quarto)
            throws NegocioException, SQLException {
        // confere se já existe algum quarto com aquele número
        List<Quarto> quartosPesquisados
                = listar(quarto.getNroQuarto(), "nroQuarto");

        if (quartosPesquisados.isEmpty()) {
            // pode atualizar
            boolean testeRegistro = objetoDAO.atualiza(codRegistro, quarto);
            return testeRegistro;
        } else {
            // tem quarto com o mesmo número
            throw new NegocioException("Número do quarto repetido!");
        }
    }

    @Override
    public boolean excluir(String codRegistro)
            throws NegocioException, SQLException {
        //
        // confere se o quarto está ocupado
        List<Quarto> quartosPesquisados
                = listar(Integer.parseInt(codRegistro), "nroQuarto");
        if (quartosPesquisados.isEmpty()) {
            throw new NegocioException("Quarto não encontrado!");
        } else {
            if (quartosPesquisados.get(0).isIdtOcupado()) {
                throw new NegocioException("Não é possível excluir o quarto" + codRegistro + ". Ele está ocupado!");
            } else {
                //
                // testa se o nroQuarto está sendo usado em QuartoHospedagem
                //
                QuartoHospedagemDAO relDAO = QuartoHospedagemDAOImpl.getInstance();
                List<QuartoHospedagem> listaREL = relDAO.busca(codRegistro, "nroQuarto");
                if (!listaREL.isEmpty()) {
                    throw new NegocioException("Não é possível excluir o quarto" + codRegistro + ". Há registros de hospedagem relacionados a ele!");
                } else {
                    return objetoDAO.deleta(codRegistro);
                }
            }
        }
    }

    @Override
    public List<Quarto> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException {
        //
        // confere se foi digitado um dado busca e se a coluna é válida
        //
        if (dadoBusca != null) {
            if (coluna.equals("nroQuarto") || coluna.equals("codCategoria"))
                return objetoDAO.busca(dadoBusca, coluna);
            else {
                throw new NegocioException("Não existe essa informação em quarto! Busque pelo número do quarto ou pela categoria");
            }
        } else {
            throw new NegocioException("Nenhum quarto buscado!");
        }
    }

    @Override
    public List<Quarto> listarTodos()
            throws NegocioException, SQLException {
        return objetoDAO.buscaTodos();
    }
}
