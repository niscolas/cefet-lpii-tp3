package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.dao.IUsuarioDAO;
import br.cefetmg.inf.hosten.model.dao.impl.UsuarioDAO;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import java.util.ArrayList;

public class ManterUsuarioAdapter implements AdapterInterface {
    private final ArrayList listaRecebida;
    private Object objEnviado;
    private String tipoRetorno;

    public ManterUsuarioAdapter(ArrayList lista) {
        this.listaRecebida = lista;
        
        operacao();
    }

    private void operacao() {
        System.err.println("Iniciando operação no adapter...");
        
        String operacao = (String) listaRecebida.get(1);
        
        try {
        
            IUsuarioDAO usuarioDAO = new UsuarioDAO();

            if (operacao.equals("Inserir")) {
                tipoRetorno = "Boolean";
                Usuario itemInserir = (Usuario)listaRecebida.get(2);
                objEnviado = usuarioDAO.adicionaUsuario(itemInserir);
            } else if (operacao.equals("Listar")) {
                tipoRetorno = "List<Usuario>";
                String dadoBusca = (String)listaRecebida.get(2);
                String coluna = (String)listaRecebida.get(3);
                objEnviado = usuarioDAO.buscaUsuario(dadoBusca, coluna);
            } else if (operacao.equals("ListarTodos")) {
                tipoRetorno = "List<Usuario>";
                objEnviado = usuarioDAO.buscaTodosUsuarios();
            } else if (operacao.equals("Alterar")) {
                tipoRetorno = "Boolean";
                String codRegistro = (String)listaRecebida.get(2);
                Usuario itemAlterar = (Usuario)listaRecebida.get(3);
                objEnviado = usuarioDAO.atualizaUsuario(codRegistro, itemAlterar);
            } else if (operacao.equals("Excluir")) {
                tipoRetorno = "Boolean";
                String codRegistro = (String)listaRecebida.get(2);
                objEnviado = usuarioDAO.deletaUsuario(codRegistro);
            } else if (operacao.equals("Login")) {
                tipoRetorno = "Usuario";
                String email = (String)listaRecebida.get(2);
                String senha = (String)listaRecebida.get(3);
                objEnviado = usuarioDAO.usuarioLogin(email, senha);
            }
        } catch (Exception ex) {
            tipoRetorno = "Exception";
            objEnviado = ex;
        }
    }

    @Override
    public String getReturnObjectType() {
        System.out.println("tipo do objeto: " + tipoRetorno);
        return tipoRetorno;
    }

    @Override
    public Object getReturnObject() {
        return objEnviado;
    }
}
