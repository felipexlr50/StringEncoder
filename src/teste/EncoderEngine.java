package teste;



public class EncoderEngine {

	public long startTime,endTime,runTime;



	// metodo de codificação da String, onde o int key é adicionado ao int representante de cada char da string e recolocado e uma nova string
	public String encode(String text,int key) throws InterruptedException{
		startTime = System.currentTimeMillis();
		String result = "";
		int tempKey;

		for(int i = 0;i<text.length();i++){

			tempKey = text.charAt(i)+key;

			result += (char)tempKey;


		}
		endTime = System.currentTimeMillis();
		runTime = endTime - startTime;

		System.out.println("operação feita em: "+(float)runTime/1000+" s");
		return result;


	}

	//metodo de decodificação da String, faz exatamente o mesmo que o codificador, unica diferença esta na subtração do int key
	public String decode(String text, int key) throws InterruptedException{
		startTime = System.currentTimeMillis();

		String result = "";
		int tempKey;
		for(int i = 0;i<text.length();i++){

			tempKey = text.charAt(i)-key;

			result += (char)tempKey;


		}
		endTime = System.currentTimeMillis();
		runTime = endTime - startTime;

		System.out.println("operação feita em: "+(float)(runTime)/1000+" s");
		return result;

	}



}
