package metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import metier.entities.Categorie;
import metier.entities.Produit;

public class CatalogueMetierImpl implements ICatalogueMetier{
	
	private Map<Long, Categorie> categories = new HashMap<Long, Categorie>();
	private Map<Long, Produit> produits = new HashMap<Long, Produit>();
	
	@Override
	public Categorie addCategorie(Categorie c) {
		categories.put(c.getIdCategorie(), c);
		return c;
	}

	@Override
	public Produit addProduit(Produit p) {
		p.setCategorie(getCategorie(p.getCategorie().getIdCategorie()));
		produits.put(p.getIdProduit(), p);
		return p;
	}

	@Override
	public List<Categorie> listCategorie() {
		return new ArrayList<Categorie>(categories.values());
	}

	@Override
	public List<Produit> listProduitPArCategorie(long idCat) {
		List<Produit> prods = new ArrayList<Produit>();
		for(Produit p : produits.values())
		{
			if(p.getCategorie().getIdCategorie() == idCat){
				{
					prods.add(p);
				}
			}
		}
		return prods;
	}

	@Override
	public List<Produit> listproduit() {
		// TODO Auto-generated method stub
		return new ArrayList<Produit>(produits.values());
	}

	@Override
	public List<Produit> listProduitParMC(String mc) {
		List<Produit> prods = new ArrayList<Produit>();
		for(Produit p : produits.values())
		{
			if(p.getDesignation().contains(mc)){
				{
					prods.add(p);
				}
			}
		}
		return prods;
	}

	@Override
	public Categorie updateCategorie(Categorie c) {
		categories.put(c.getIdCategorie(), c);
		return c;
	}

	@Override
	public Produit updateProduit(Produit p) {
		produits.put(p.getIdProduit(), p);
		return p;
	}

	@Override
	public boolean deleteProduit(Long idProd) {
		if(produits.get(idProd) != null)
		{
			produits.remove(idProd);
			return true;
		}else {
		  throw new RuntimeException("Produit introuvable");
		}
		
	}

	@Override
	public Categorie getCategorie(Long idCat) {
		// TODO Auto-generated method stub
		return categories.get(idCat);
	}

	@Override
	public Produit getProduit(Long idProd) {
		// TODO Auto-generated method stub
		return produits.get(idProd);
	}
	
	public void initialiserCatalogue(){
		addCategorie(new Categorie(1L, "Ordinateur", "Ordinateur.jpg"));
		addCategorie(new Categorie(2L, "Imprimantes", "Imprimantes.jpg"));
		addCategorie(new Categorie(3L, "Televiseurs", "Televiseurs.jpg"));
		
		addCategorie(new Categorie(4L, "Telephone", "Telephone.jpg"));
		addCategorie(new Categorie(5L, "Haut", "Haut.jpg"));
		addCategorie(new Categorie(6L, "Machin", "MAchin.jpg"));
		
		produits.put(1L, new Produit(1L, "HP Pavilion", 6500, "ord1.jpg", getCategorie(1L)));
		produits.put(2L, new Produit(2L, "Asus PC Portable", 4500, "ord2.jpg", getCategorie(1L)));
		produits.put(3L, new Produit(3L, "Lenovo Notebook", 3500, "ord3.jpg", getCategorie(1L)));
		produits.put(4L, new Produit(4L, "HP Imprimante", 1500, "imp1.jpg", getCategorie(2L)));
		produits.put(5L, new Produit(5L, "CAnon Pixma MG", 1000, "imp2.jpg", getCategorie(2L)));
		produits.put(6L, new Produit(6L, "HP L3 Pro", 2200, "imp3.jpg", getCategorie(2L)));
	}
}
