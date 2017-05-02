package com.fitec.tp.clien;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import com.fitec.tp.service.CalculateurImplService;
import com.fitec.tp.service.ICalculateur;

public class WsClientApp {

	public static void main(String[] args) {
		// le code d'appel du ws
		// va s'appuyer sur le resultat de wsimport
		// à lancer via un .bat ou ....
		
		//String sWdlURL = "http://localhost:8080/WsJdk/calculateur?wsdl";
		try {
			//String sWsdlURL = "http://HPG3i3-39:8080/WsJdk/calculateur?wsdl";
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("ws.properties");
			Properties props = new Properties();
			props.load(is);
			String sWsdlURL = props.getProperty("wsCalculateur.wsdlUrl");
			URL wsdlURL = new URL(sWsdlURL);
			

			ICalculateur wsProxy = (new CalculateurImplService(wsdlURL)).getCalculateurImplPort();
			double resTva = wsProxy.tva(200.0, 20);
			String auteur = wsProxy.getAuteur();
			
			System.out.println("tva= "+resTva);
			System.out.println("auteur= "+auteur);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
