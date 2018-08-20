package br.cefetmg.inf.hosten.server;

import br.cefetmg.inf.hosten.adapter.ManterItemConfortoAdapter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HostenServidor {
    private static final int PORTA = 1234;
    private static final int TAMANHO = 65535;

    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(PORTA);
            byte[] vetorByteRecebido = new byte[TAMANHO];

            DatagramPacket datagramPacketReceive = null;
            while (true){

                datagramPacketReceive = new DatagramPacket(vetorByteRecebido, vetorByteRecebido.length);
                datagramSocket.receive(datagramPacketReceive);
                
                ArrayList registro = (ArrayList)toObject(vetorByteRecebido);

                if (registro.get(0).equals("ItemConforto")) {
                    ManterItemConfortoAdapter adapter = new ManterItemConfortoAdapter();
                }
            }

        } catch (SocketException ex) {
            Logger.getLogger(HostenServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HostenServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HostenServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Object toObject (byte [] bytes) throws IOException,
            ClassNotFoundException {
        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (ois != null) {
                ois.close();
            }
        }
        return obj;
    }

}