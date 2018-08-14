package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.service.ManterItemConforto;
import br.cefetmg.inf.hosten.model.dao.impl.ItemConfortoDAO;
import java.util.List;

public class ManterItemConfortoImpl implements ManterItemConforto {
    ItemConfortoDAO objetoDAO;

    public ManterItemConfortoImpl() {
        objetoDAO = ItemConfortoDAO.getInstance();
    }

    @Override
    public boolean inserir(ItemConforto itemConforto) throws NegocioException {
        // pesquisa para saber se há algum item já inserido que possui o mesmo código
        List<ItemConforto> itensPesquisados = pesquisar(itemConforto.getCodItem, "codItem");
        
        if (itensPesquisados.isEmpty()) {
            // não tem item com o mesmo código
            
            // busca se tem item com a mesma descrição
            List<ItemConforto> itensPesquisados1 = pesquisar(itemConforto.getDesItem, "desItem");
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
    public boolean alterar(String codRegistro, ItemConforto itemConforto) {
        // pesquisa para saber se há algum item já inserido que possui o mesmo código
        List<ItemConforto> itensPesquisados = pesquisar(itemConforto.getCodItem, "codItem");
        
        if (itensPesquisados.isEmpty()) {
            // não tem item com o mesmo código
            
            // busca se tem item com a mesma descrição
            List<ItemConforto> itensPesquisados1 = pesquisar(itemConforto.getDesItem, "desItem");
            if (itensPesquisados1.isEmpty()) {
                // não tem item com a mesma descrição
                // pode alterar
                boolean testeRegistro = objetoDAO.atualiza(codRegistro, itemConforto);
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
    public boolean excluir(String codRegistro) {
        //
        // pesquisa se o código do item é utilizado em Categoria de Quarto
        //
        objetoDAO.deleta(codRegistro);
    }

    @Override
    public List<ItemConforto> buscar(Object dadoBusca, String coluna) {
        //
        // confere se foi digitado um dado busca e se a coluna é válida
        //
        return objetoDAO.busca(dadoBusca, coluna);
    }

    @Override
    public List<ItemConforto> buscarTodos() {
        return objetoDAO.buscaTodos();
    }
    
}
