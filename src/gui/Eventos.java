package gui;

import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;

import core.EncoderEngine;

public class Eventos {

    private Tela tela;

    public Eventos(Tela tela) {
        this.tela = tela;
    }

    public void botaoEvento() {
        tela.btnSair.addActionListener(e -> System.exit(0));

        tela.btnLimpar.addActionListener(e -> {

            tela.textArea.setText("");
            tela.tfChave.setText("");
            tela.textArea.requestFocus();

        });

        tela.btnCodificar.addActionListener(e -> {
            try {

                tela.textArea.setText(EncoderEngine.encodeToHex(tela.textArea.getText(),
                        tela.tfChave.getText()));

                tela.tfChave.requestFocus();

            }
            catch (NumberFormatException erro) {
                JOptionPane.showMessageDialog(tela, "Chave só aceita números positivos!");
            }
            catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        tela.btnDecodificar.addActionListener(e -> {
            try {

                tela.textArea.setText(EncoderEngine.decodeFromHex(tela.textArea.getText(),
                        tela.tfChave.getText()));

                tela.tfChave.requestFocus();

            }
            catch (NumberFormatException erro) {
                JOptionPane.showMessageDialog(tela, "Chave só aceita números positivos!");

            }
            catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        });
    }

}
