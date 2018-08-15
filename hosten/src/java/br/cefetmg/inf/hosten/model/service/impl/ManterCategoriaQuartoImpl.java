package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.dao.impl.CategoriaQuartoDAO;
import br.cefetmg.inf.hosten.model.dao.rel.CategoriaItemConfortoDAO;
import br.cefetmg.inf.hosten.model.dao.rel.impl.CategoriaItemConfortoDAOImpl;
import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.domain.rel.CategoriaItemConforto;
import br.cefetmg.inf.hosten.model.service.ManterCategoriaQuarto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public class ManterCategoriaQuartoImpl implements ManterCategoriaQuarto {
    CategoriaQuartoDAO objetoDAO;

    public ManterCategoriaQuartoImpl() {
        objetoDAO = CategoriaQuartoDAO.getInstance();
    }
    
    @Override
    public boolean inserir(CategoriaQuarto categoriaQuarto, List<ItemConforto> itensCategoria)
            throws NegocioException, SQLException {
        // pesquisa para saber se há alguma categoria já 
        // inserida que possui o mesmo código
        List<CategoriaQuarto> categoriasPesquisadas
                = listar(categoriaQuarto.getCodCategoria(), "codCategoria");

        if (categoriasPesquisadas.isEmpty()) {
            // não tem categoria com o mesmo código

            // busca se tem categoria com oo mesmo nome
            List<CategoriaQuarto> categoriasPesquisadas1
                    = listar(categoriaQuarto.getNomCategoria(), "nomCategoria");
            if (categoriasPesquisadas1.isEmpty()) {
                // não tem categoria com o mesmo nome
                // pode inserir

                // cria os relacionamentos
                CategoriaItemConfortoDAO relDAO = CategoriaItemConfortoDAOImpl.getInstance();
                for (ItemConforto item : itensCategoria) {
                    CategoriaItemConforto rel = new CategoriaItemConforto(categoriaQuarto.getCodCategoria(), item.getCodItem());
                    relDAO.adiciona(rel);
                }

                // adiciona a categoria
                boolean testeRegistro = objetoDAO.adiciona(categoriaQuarto);
                return testeRegistro;
            } else {
                // tem item com a mesma descrição
                throw new NegocioException("Nome da categoria repetido!");
            }
        } else {
            // tem categoria com o mesmo código
            throw new NegocioException("Código da categoria repetido!");
        }
    }

    @Override
    public boolean alterar(String codRegistro, CategoriaQuarto categoriaQuarto, List<ItemConforto> itensCategoria)
            throws NegocioException, SQLException {
        // pesquisa para saber se há alguma categoria já 
        // inserida que possui o mesmo código
        List<CategoriaQuarto> categoriasPesquisadas
                = listar(categoriaQuarto.getCodCategoria(), "codCategoria");

        if (categoriasPesquisadas.isEmpty()) {
            // não tem categoria com o mesmo código

            // busca se tem categoria com oo mesmo nome
            List<CategoriaQuarto> categoriasPesquisadas1
                    = listar(categoriaQuarto.getNomCategoria(), "nomCategoria");
            if (categoriasPesquisadas1.isEmpty()) {
                // não tem categoria com o mesmo nome
                // pode alterar

                // atualiza a categoria
                boolean testeRegistro = objetoDAO.atualiza(codRegistro, categoriaQuarto);
                if (testeRegistro) {
                    // atualiza os relacionamentos
                    CategoriaItemConfortoDAO relDAO = CategoriaItemConfortoDAOImpl.getInstance();
                    // deleta todos os relacionamentos com aquela categoria
                    List<CategoriaItemConforto> listaREL = relDAO.busca(categoriaQuarto.getCodCategoria(), "codCategoria");
                    if (!listaREL.isEmpty()) {
                        relDAO.deletaPorColuna(categoriaQuarto.getCodCategoria(), "codCategoria");
                    }
                    // cria os relacionamentos com os itens passados
                    for (ItemConforto item : itensCategoria) {
                        CategoriaItemConforto rel = new CategoriaItemConforto(categoriaQuarto.getCodCategoria(), item.getCodItem());
                        relDAO.adiciona(rel);
                    }
                    
                    return true;
                } else {
                    return false;
                }
            } else {
                // tem item com a mesma descrição
                throw new NegocioException("Nome da categoria repetido!");
            }
        } else {
            // tem categoria com o mesmo código
            throw new NegocioException("Código da categoria repetido!");
        }
    }

    @Override
    public boolean excluir(String codRegistro)
            throws NegocioException, SQLException {
        List<CategoriaQuarto> categoriasPesquisadas
                = listar(codRegistro, "codCategoria");
        if (categoriasPesquisadas.isEmpty())
            throw new NegocioException("Essa categoria não existe!");

        // deleta todos os relacionamentos com aquela categoria
        CategoriaItemConfortoDAO relDAO = CategoriaItemConfortoDAOImpl.getInstance();
        List<CategoriaItemConforto> listaREL = relDAO.busca(categoriasPesquisadas.get(0).getCodCategoria(), "codCategoria");
        if (!listaREL.isEmpty()) {
            return relDAO.deletaPorColuna(categoriasPesquisadas.get(0).getCodCategoria(), "codCategoria");
        }

        // deleta a categoria
        return objetoDAO.deleta(codRegistro);
    }

    @Override
    public List<CategoriaQuarto> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException {
        //
        // confere se foi digitado um dado busca e se a coluna é válida
        //
        if (dadoBusca != null) {
            if (coluna.equals("codCategoria") || coluna.equals("nomCategoria") || coluna.equals("vlrDiaria"))
                return objetoDAO.busca(dadoBusca, coluna);
            else {
                throw new NegocioException("Não existe essa informação em categoria de quarto! Busque pelo código, pelo nome ou pelo valor");
            }
        } else {
            throw new NegocioException("Nenhuma categoria buscada!");
        }
    }

    @Override
    public List<CategoriaQuarto> listarTodos()
            throws NegocioException, SQLException {
        return objetoDAO.buscaTodos();
    }
}
