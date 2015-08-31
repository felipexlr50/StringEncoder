package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import teste.EncoderEngine;

public class Eventos{

	private Tela tela;
	private EncoderEngine encode = new EncoderEngine();



	public Eventos(Tela tela) {
		this.tela = tela;



	}

	public void botaoEvento(){
		tela.btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		});

		tela.btnLimpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tela.textArea.setText("");
				tela.tfChave.setText("");
				tela.textArea.requestFocus();

			}
		});

		tela.btnCodificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try{

					tela.textArea.setText(encode.encode(tela.textArea.getText(),
							Integer.parseInt(tela.tfChave.getText())));

					tela.tfChave.requestFocus();


				}
				catch(NumberFormatException erro){
					JOptionPane.showMessageDialog(tela, "Chave só aceita números positivos!");
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}



		});

		tela.btnDecodificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try{


					tela.textArea.setText(encode.decode(tela.textArea.getText(),
							Integer.parseInt(tela.tfChave.getText())));



					tela.tfChave.requestFocus();


				}
				catch(NumberFormatException erro){
					JOptionPane.showMessageDialog(tela, "Chave só aceita números positivos!");

				} 
				catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}
		});

	}


}
