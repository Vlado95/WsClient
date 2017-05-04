package com.fitec.tp.clien;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fitec.tp.entity.Auteur;

import javafx.scene.media.Media;

public class TestAppelRest {

	
	public static void main(String[] args) {
		Client jaxrscleint =ClientBuilder.newClient().register(JacksonJsonProvider.class);
		
		String wsUrl ="http://localhost:8080/wsSpringCxfWeb/services/rest/auteurs";
		WebTarget target = jaxrscleint.target(wsUrl).path("all");
		String resAsJsonString = target.request(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		System.out.println(resAsJsonString);
		List<Auteur> auteurlist = target.request(MediaType.APPLICATION_JSON).get().readEntity(new GenericType< List<Auteur> >(){});
		
		
		for(Auteur auteur : auteurlist){
			System.out.println(auteur.toString());
			Auteur auteur1 = jaxrscleint.target(wsUrl).path("1").request().get().readEntity(Auteur.class);
			System.out.println(auteur1.toString());
		}
		
		
		Auteur auteurajout = new Auteur();
		auteurajout.setNom("TESTJAVA");
		auteurajout.setPrenom("testJava");
		WebTarget targetPost =  jaxrscleint.target(wsUrl);
		
		Response response = targetPost.request(MediaType.APPLICATION_JSON).post(Entity.entity(auteurajout,MediaType.APPLICATION_JSON));
		if (response.getStatus()==200) {
			Auteur auteurAj = response.readEntity(Auteur.class);
			System.out.println("auteur ajouté" +auteurAj.toString());
			
		}
		
		
	
	}
}
