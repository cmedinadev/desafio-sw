package br.com.cmedina.starwars.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import br.com.cmedina.starwars.entities.Planeta;
import br.com.cmedina.starwars.factory.ConnectionFactory;

public class PlanetaDAO extends BasicDAO<Planeta, String> {

	
	public PlanetaDAO() {
		super(ConnectionFactory.getInstance().getDatastoreManager());
	}
	
	public PlanetaDAO(Datastore ds) {
		super(ds);
	}

	public Planeta get(ObjectId objectId) {
		return getDatastore().get(Planeta.class, objectId);
	}

}
