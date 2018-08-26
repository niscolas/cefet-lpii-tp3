package br.cefetmg.inf.hosten.adapter;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.model.service.impl.ManterCargo;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.server.ServerUtils;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ManterCargoAdapter implements Runnable {

    private DatagramSocket socket;
    private DatagramPacket pacoteRecebido;


    private final ArrayList listaRecebida;
    private Object objEnviado;
    private String tipoRetorno;

    public ManterCargoAdapter(DatagramSocket socket, DatagramPacket pacoteRecebido) throws IOException, ClassNotFoundException {
        this.socket = socket;
        this.pacoteRecebido = pacoteRecebido;
        listaRecebida = (ArrayList) ServerUtils.toObject(pacoteRecebido.getData());
    }

    private void operacao() {
        System.err.println("Iniciando operação no adapter...");

        String operacao = (String) listaRecebida.get(1);

        try {

            IManterCargo manterCargo = new ManterCargo();

            switch (operacao) {
                case "Inserir": {
                    tipoRetorno = "Boolean";
                    Cargo itemInserir = (Cargo) listaRecebida.get(2);
                    List<String> listaProgramas 
                            = (List<String>)listaRecebida.get(3);
                    
                    objEnviado = manterCargo.inserir(
                            itemInserir, 
                            listaProgramas);
                    
                    break;
                }
                case "Listar": {
                    tipoRetorno = "List<Cargo>";
                    String dadoBusca = (String) listaRecebida.get(2);
                    String coluna = (String) listaRecebida.get(3);
                    objEnviado = manterCargo.listar(dadoBusca, coluna);
                    
                    break;
                }
                case "ListarTodos": {
                    tipoRetorno = "List<Cargo>";
                    objEnviado = manterCargo.listarTodos();
                    
                    break;
                }
                case "Alterar": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    Cargo itemAlterar = (Cargo) listaRecebida.get(3);
                    List<String> listaProgramas 
                            = (List<String>) listaRecebida.get(4);
                    
                    objEnviado = manterCargo.alterar(
                            codRegistro, 
                            itemAlterar, 
                            listaProgramas);
                    
                    break;
                }
                case "Excluir": {
                    tipoRetorno = "Boolean";
                    String codRegistro = (String) listaRecebida.get(2);
                    objEnviado = manterCargo.excluir(codRegistro);
                    
                    break;
                }
            }
        } catch (Exception ex) {
            tipoRetorno = "Exception";
            objEnviado = ex;
        }
    }

    @Override
    public void run() {
        operacao();

        try {
            ArrayList listaEnviada = new ArrayList();
            listaEnviada.add(tipoRetorno);
            listaEnviada.add(objEnviado);

            byte[] out = new byte[ServerUtils.TAMANHO];
            out = ServerUtils.toByteArray(listaEnviada);

            System.out.println("Pacote de retorno montado!");

            InetAddress IPAddress = pacoteRecebido.getAddress();
            int port = pacoteRecebido.getPort();
            DatagramPacket sendPacket = new DatagramPacket(out, out.length, IPAddress, port);

            System.out.println("pacote de retorno enviado!!");
            socket.send(sendPacket);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
