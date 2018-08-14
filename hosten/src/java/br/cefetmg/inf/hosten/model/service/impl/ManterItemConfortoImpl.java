package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.service.ManterItemConforto;
import br.cefetmg.inf.hosten.model.dao.impl.ItemConfortoDAO;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public class ManterItemConfortoImpl implements ManterItemConforto {

    ItemConfortoDAO objetoDAO;

    public ManterItemConfortoImpl() {
        objetoDAO = ItemConfortoDAO.getInstance();
    }

    @Override
    public boolean inserir(ItemConforto itemConforto)
            throws NegocioException, SQLException {
        // pesquisa para saber se há algum item já 
        // inserido que possui o mesmo código
        List<ItemConforto> itensPesquisados
                = listar(itemConforto.getCodItem(), "codItem");

        if (itensPesquisados.isEmpty()) {
            // não tem item com o mesmo código

            // busca se tem item com a mesma descrição
            List<ItemConforto> itensPesquisados1
                    = listar(itemConforto.getDesItem(), "desItem");
            if (itensPesquisados1.isEmpty()) {
                // não tem item com a mesma descrição
                // pode inserir
                boolean testeRegistro = objetoDAO.adiciona(itemConforto);
                return testeRegistro;
            } else {
                // tem item com a mesma descrição
                throw new NegocioException("Descrição do item repetida!");
            }
        } else {
            // tem item com o mesmo código
            throw new NegocioException("Código do item repetido!");
        }
    }

    @Override
    public boolean alterar(String codRegistro, ItemConforto itemConforto)
            throws SQLException, NegocioException {
        // pesquisa para saber se há algum item já inserido que possui o mesmo código
        List<ItemConforto> itensPesquisados
                = listar(itemConforto.getCodItem(), "codItem");

        if (itensPesquisados.isEmpty()) {
            // não tem item com o mesmo código

            // busca se tem item com a mesma descrição
            List<ItemConforto> itensPesquisados1
                    = listar(itemConforto.getDesItem(), "desItem");
            if (itensPesquisados1.isEmpty()) {
                // não tem item com a mesma descrição
                // pode alterar
                boolean testeRegistro
                        = objetoDAO.atualiza(codRegistro, itemConforto);
                return testeRegistro;
            } else {
                // tem item com a mesma descrição
                throw new NegocioException("Descrição do item repetida!");
            }
        } else {
            // tem item com o mesmo código
            throw new NegocioException("Código do item repetido!");
        }
    }

    @Override
    public boolean excluir(String codRegistro)
            throws NegocioException, SQLException {
        //
        // pesquisa se o código do item é utilizado em Categoria de Quarto
        //
        return objetoDAO.deleta(codRegistro);
    }

    @Override
    public List<ItemConforto> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException {
        //
        // confere se foi digitado um dado busca e se a coluna é válida
        //
        return objetoDAO.busca(dadoBusca, coluna);
    }

    @Override
    public List<ItemConforto> listarTodos()
            throws NegocioException, SQLException {
        return objetoDAO.buscaTodos();
    }
}
