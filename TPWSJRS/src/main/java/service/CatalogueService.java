package service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.spi.resource.Singleton;

import metier.CatalogueMetierImpl;
import metier.entities.Categorie;
import metier.entities.Produit;

@Singleton
@Path("/catalogue")
@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
public class CatalogueService {
	private CatalogueMetierImpl metier;
	
	public CatalogueService(){
		metier = new CatalogueMetierImpl();
		metier.initialiserCatalogue();
	}
	
	//http://localhost:8085/TPWSJRS/catalogue/categories
	@GET
	@Path("/categories")
	//@Produces(MediaType.APPLICATION_JSON)
	public List<Categorie> consulterCategories(){	
		return metier.listCategorie();	
	}
	
	//http://localhost:8085/TPWSJRS/catalogue/allProduits
	@GET
	@Path("/allProduits") 
	public List<Produit> produits(){
		return metier.listproduit();
	}
	
	//http://localhost:8085/TPWSJRS/catalogue/categories/1/produits
	@GET
	@Path("/categories/{idCat}/produits") 
	//@Produces(MediaType.APPLICATION_JSON)
	public List<Produit> produitsParCat(@PathParam(value="idCat")Long idCat){
		return metier.listProduitPArCategorie(idCat);
	}
	
	//http://localhost:8085/TPWSJRS/catalogue/produits?mc=H
	@GET
	@Path("/produits") 
	//@Produces(MediaType.APPLICATION_JSON)
	public List<Produit> produitsParMC(@QueryParam(value="mc")String mc){
		return metier.listProduitParMC(mc);
	}
	
	//http://localhost:8085/TPWSJRS/catalogue/categories/1
	@GET
	@Path("/categories/{idCat}") 
	//@Produces(MediaType.APPLICATION_JSON)
	public Categorie getCategorie(@PathParam(value="idCat")Long idCat) {
		return metier.getCategorie(idCat);
	}
	
	//http://localhost:8085/TPWSJRS/catalogue/produits/1
	@GET
	@Path("/produits/{idProd}") 
	//@Produces(MediaType.APPLICATION_JSON)
	public Produit getProduit(@PathParam(value="idProd")Long idProd) {
		return metier.getProduit(idProd);
	}
	
	////http://localhost:8085/TPWSJRS/catalogue/categories
	@POST
	@Path("/categories")
	@Consumes(MediaType.APPLICATION_JSON) //envoyer inserer les donnes sous forme JSON
	public Categorie saveCategorie(Categorie c) {
		return metier.addCategorie(c);
	}
	
	////http://localhost:8085/TPWSJRS/catalogue/categories
	@POST
	@Path("/produits")
	@Consumes(MediaType.APPLICATION_JSON) //envoyer les donnes sous forme JSON
	public Produit saveProduit(Produit p) {
		return metier.addProduit(p);
	}
	
    ////http://localhost:8085/TPWSJRS/catalogue/produits
	//Donner sous la forme : urlencoded (Le cors)
	@DELETE
	@Path("/produits")
	@Consumes(MediaType.TEXT_XML)
	public boolean deleteProduit(@FormParam(value="idProd")Long idProd) {
		return metier.deleteProduit(idProd);
	}
	
	////http://localhost:8085/TPWSJRS/catalogue/produits
	//par defaut l'objet envoyer sous la forme JSON.
	@PUT
	@Path("/produits")
	public Produit updateProduit(Produit p) {
		return metier.updateProduit(p);
	}
}
