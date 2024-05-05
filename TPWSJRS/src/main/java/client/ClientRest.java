package client;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;


import metier.entities.Categorie;
import metier.entities.Produit;

public class ClientRest {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		Client client=Client.create(new DefaultClientConfig());
		URI uri= UriBuilder.fromUri("http://localhost:8085/TPWSJRS/").build();
		ObjectMapper mapper = new ObjectMapper();
		
		
		//Modifier un produit.
		System.out.println("\n*************Modifier One Produit ******************");
		Produit p_update = new Produit(3L, "QQQQQ", 10000, "q.png", null);
		ClientResponse respUpdate = client.resource(uri)
				.path("catalogue")
				.path("produits")
				.type(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, mapper.writeValueAsString(p_update));
		System.out.println(respUpdate.getEntity(String.class));
		
		
		//Supprision d'un produit.
//		ClientResponse resp3 = client.resource(uri)
//				.path("catalogue")
//				.path("produits")
//				.type(MediaType.TEXT_XML)
//				.delete(ClientResponse.class, "idProd=4");
//		System.out.println(resp3.getEntity(String.class));
		
		
		//Ajouter un produit.
		System.out.println("\n*************Ajouter One Produit ******************");
		Produit p = new Produit(10L, "SSSSssssss", 2000, "s.png", new Categorie(1L, "wwww", "w.png"));
		ClientResponse resp_put_produit = client.resource(uri)
						.path("catalogue")
						.path("produits")
						.type(MediaType.APPLICATION_JSON)
						.post(ClientResponse.class, mapper.writeValueAsString(p));
		System.out.println(resp_put_produit.getEntity(String.class));
		
		//Ajouter une categorie.
		//On peut creer un objet categorie et serialiser a Json.
		//String catSTR = "{\"idCategorie\":5,\"nomCategorie\":\"AAA\",\"photo\":\"xx.jpg\"}";	
		System.out.println("\n*************Ajouter One Categorie ******************");
		Categorie c2 = new Categorie(8L, "wwww", "aa.jpg");
		ClientResponse resp2 = client.resource(uri)
				.path("catalogue")
				.path("categories")
				.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, mapper.writeValueAsString(c2));
		System.out.println(resp2.getEntity(String.class));
		
		
		//Consulter un categorie avec PathParam.
		System.out.println("\n*************Consulter One Categories /categories/{idCat}  ******************");
		Long idCat = 2L;
		ClientResponse response_idCat = 
						client.resource(uri)
						.path("catalogue")
						.path("categories/"+idCat)
						.get(ClientResponse.class);
		String corpsRepHttp_idCat = response_idCat.getEntity(String.class);
		System.out.println(corpsRepHttp_idCat);
		Categorie c1 = mapper.readValue(corpsRepHttp_idCat, Categorie.class);
		System.out.println("La nom du produit est "+c1.getNomCategorie());
		
		
		//Consulter un produit avec PathParam.
		System.out.println("\n*************Consulter One Produits /produits/{idProd} ******************");
		Long idProd = 3L;
		ClientResponse response2 = 
				client.resource(uri)
				.path("catalogue")
				.path("produits/"+idProd)
				.get(ClientResponse.class);
		String corpsRepHttp2 = response2.getEntity(String.class);
		System.out.println(corpsRepHttp2);
		Produit p1 = mapper.readValue(corpsRepHttp2, Produit.class);
		System.out.println("La desifnation du produit est "+p1.getDesignation());
		
		
		//Consulter ALL produit avec MC .
		System.out.println("\n*************Consulter ALL Produits avec MC ******************");
		String MC = "H";
		ClientResponse response_MC = 
				client.resource(uri)
				.path("catalogue")
				.path("produits")
				.queryParam("mc", MC)
				.get(ClientResponse.class);
		String corpsRepHttp2_MC = response_MC.getEntity(String.class);
		System.out.println(corpsRepHttp2_MC);
		Produit[] prods = mapper.readValue(corpsRepHttp2_MC, Produit[].class);
		for(Produit p11 : prods) {
			System.out.println(p11.getDesignation());
		}
		
		
		//Consulter un produit avec PathParam /categories/{idCat}/produits.
				System.out.println("\n*************Consulter One Produits /categories/{idCat}/produits ******************");
				Long idProd_2 = 3L;
				ClientResponse response2_2 = 
						client.resource(uri)
						.path("catalogue")
						.path(idProd_2+"/produits")
						.get(ClientResponse.class);
				String corpsRepHttp2_2 = response2_2.getEntity(String.class);
				System.out.println(corpsRepHttp2_2);
				Produit p_2 = mapper.readValue(corpsRepHttp2_2, Produit.class);
				System.out.println("La desifnation du produit est "+p_2.getDesignation());
		
		
		//Consulter tous les produits
		System.out.println("\n*************Consulter All Produits ******************");
		ClientResponse response_ALLProduits = 
				client.resource(uri)
				.path("catalogue")
				.path("allProduits")
				.get(ClientResponse.class);
		String corpsRepHttp_ALLProduits = response_ALLProduits.getEntity(String.class);
		System.out.println(corpsRepHttp_ALLProduits);
		//desirialisation JSON => Java Object (Jackson Ou Json)
		Produit[] prods_all = mapper.readValue(corpsRepHttp_ALLProduits, Produit[].class);		
		for(Produit p11: prods_all) {
			System.out.println(p11.getDesignation());
		}
		
		
		//Consulter toutes les categories.
		System.out.println("\n***************Consulter All Categories *************");
		ClientResponse response = 
				client.resource(uri)
				.path("catalogue")
				.path("categories")
				.get(ClientResponse.class);
		String corpsRepHttp = response.getEntity(String.class);
		System.out.println(corpsRepHttp);
		//desirialisation JSON => Java Object (Jackson Ou Json)
		Categorie[] cats = mapper.readValue(corpsRepHttp, Categorie[].class);		
		for(Categorie c:cats) {
			System.out.println(c.getNomCategorie());
		}
		
	}

}
