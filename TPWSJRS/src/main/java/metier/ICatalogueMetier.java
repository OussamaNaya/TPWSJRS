package metier;

import java.util.List;

import metier.entities.Categorie;
import metier.entities.Produit;

public interface ICatalogueMetier {
	public Categorie addCategorie(Categorie c);
	public Produit addProduit(Produit p);
	public List<Categorie> listCategorie();
	public List<Produit> listProduitPArCategorie(long idCat);
	public List<Produit> listproduit();
	public List<Produit> listProduitParMC(String mc);
	public Categorie updateCategorie(Categorie c);
	public Produit updateProduit(Produit p);
	public boolean deleteProduit(Long idProd);
	public Categorie getCategorie(Long idCat);
	public Produit getProduit(Long idProd);
}
