package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.dao.impl.HospedeDAO;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.service.ManterHospede;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public class ManterHospedeImpl implements ManterHospede {

    HospedeDAO objetoDAO;

    public ManterHospedeImpl() {
        objetoDAO = HospedeDAO.getInstance();
    }

    @Override
    public boolean inserir(Hospede hospede)
            throws NegocioException, SQLException {
        // pesquisa para saber se há algum hospede já 
        // inserido que possui o mesmo cpf
        List<Hospede> hospedesPesquisados
                = listar(hospede.getCodCPF(), "codCPF");

        if (hospedesPesquisados.isEmpty()) {
            // não tem hóspede com o mesmo cpf

            // busca se tem hóspede com o mesmo email
            List<Hospede> hospedesPesquisados1
                    = listar(hospede.getDesEmail(), "desEmail");
            if (hospedesPesquisados1.isEmpty()) {
                // não tem hóspede com o mesmo email
                
                // busca se tem hóspede com o mesmo telefone
                List<Hospede> hospedesPesquisados2
                        = listar(hospede.getDesTelefone(), "desTelefone");
                if (hospedesPesquisados2.isEmpty()) {
                    // não tem hóspede com o mesmo telefone
                    // pode inserir
                    boolean testeRegistro = objetoDAO.adiciona(hospede);
                    return testeRegistro;
                } else {
                    // tem hóspede com o mesmo telefone
                    throw new NegocioException("Telefone repetido!");
                }
                
            } else {
                // tem item com o mesmo email
                throw new NegocioException("Email repetido!");
            }
        } else {
            // tem hóspede com o mesmo cpf
            throw new NegocioException("CPF repetido!");
        }
    }

    @Override
    public boolean alterar(String codRegistro, Hospede hospede)
            throws NegocioException, SQLException {
        // pesquisa para saber se há algum hospede já 
        // inserido que possui o mesmo cpf
        List<Hospede> hospedesPesquisados
                = listar(hospede.getCodCPF(), "codCPF");

        if (hospedesPesquisados.isEmpty()) {
            // não tem hóspede com o mesmo cpf

            // busca se tem hóspede com o mesmo email
            List<Hospede> hospedesPesquisados1
                    = listar(hospede.getDesEmail(), "desEmail");
            if (hospedesPesquisados1.isEmpty()) {
                // não tem hóspede com o mesmo email
                
                // busca se tem hóspede com o mesmo telefone
                List<Hospede> hospedesPesquisados2
                        = listar(hospede.getDesTelefone(), "desTelefone");
                if (hospedesPesquisados2.isEmpty()) {
                    // não tem hóspede com o mesmo telefone
                    // pode alterar
                    boolean testeRegistro = objetoDAO.atualiza(codRegistro, hospede);
                    return testeRegistro;
                } else {
                    // tem hóspede com o mesmo telefone
                    throw new NegocioException("Telefone repetido!");
                }
                
            } else {
                // tem item com o mesmo email
                throw new NegocioException("Email repetido!");
            }
        } else {
            // tem hóspede com o mesmo cpf
            throw new NegocioException("CPF repetido!");
        }
    }

//    @Override
//    public boolean excluir(String codRegistro)
//            throws NegocioException, SQLException {
//        //TODO
//        return false;
//    }

    @Override
    public List<Hospede> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException {
        //
        // confere se foi digitado um dado busca e se a coluna é válida
        //
        if (dadoBusca != null) {
            if (coluna.equals("codCPF") || coluna.equals("desEmail") || coluna.equals("desTelefone") || coluna.equals("nomHospede"))
                return objetoDAO.busca(dadoBusca, coluna);
            else {
                throw new NegocioException("Não existe essa informação sobre o hóspede! Busque pelo CPF, pelo nome, pelo email ou pelo telefone");
            }
        } else {
            throw new NegocioException("Nenhum hóspede buscado!");
        }
    }

    @Override
    public List<Hospede> listarTodos()
            throws NegocioException, SQLException {
        return objetoDAO.buscaTodos();
    }
}
