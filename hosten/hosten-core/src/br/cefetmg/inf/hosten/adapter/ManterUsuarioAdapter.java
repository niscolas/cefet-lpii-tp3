package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.model.service.impl.ManterUsuario;
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

            IManterUsuario manterUsuario = new ManterUsuario();

            switch (operacao) {
                case "Inserir": {
                    tipoRetorno = "Boolean";
                    Usuario itemInserir = (Usuario) listaRecebida.get(2);
                    objEnviado = manterUsuario.inserir(itemInserir);
                    
                    break;
                }
                case "Listar": {
                    tipoRetorno = "List<Usuario>";
                    String dadoBusca = (String) listaRecebida.get(2);
                    String coluna = (String) listaRecebida.get(3);
                    objEnviado = manterUsuario.listar(dadoBusca, coluna);
                    
                    break;
                }
                case "ListarTodos": {
                    tipoRetorno = "List<Usuario>";
                    objEnviado = manterUsuario.listarTodos();
                    
                    break;
                }
                case "Alterar": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    Usuario itemAlterar = (Usuario) listaRecebida.get(3);
                    objEnviado = manterUsuario.alterar(codRegistro, itemAlterar);
                    
                    break;
                }
                case "Excluir": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    objEnviado = manterUsuario.excluir(codRegistro);
                    
                    break;
                }   
                case "Login": {
                    tipoRetorno = "Usuario";
                    String email = (String) listaRecebida.get(2);
                    String senha = (String) listaRecebida.get(3);
                    objEnviado = manterUsuario.usuarioLogin(email, senha);
                    
                    break;
                }
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
