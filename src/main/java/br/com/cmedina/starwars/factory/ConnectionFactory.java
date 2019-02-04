package br.com.cmedina.starwars.factory;


import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;



public class ConnectionFactory {

	private MongoClient mongoClient;
	private Datastore datastore;
	private static ConnectionFactory connection;

	private ConnectionFactory(){}
	
	public void openConnection(){
		mongoClient = new MongoClient();
		this.setDatabase();
	}
	
	private void setDatabase(){
		if (mongoClient == null){
			this.openConnection();
		}
		Morphia morphia = new Morphia();
		morphia.mapPackage("br.com.cmedina.starwars.entity");
		datastore = morphia.createDatastore(mongoClient, "starwars");
		datastore.ensureIndexes();
	}
	
	public Datastore getDatastoreManager(){
		if (this.datastore == null){
			this.setDatabase();
		}
		return this.datastore;
	}
	

	
	public static ConnectionFactory getInstance(){
		if (connection == null){
			connection = new ConnectionFactory();
		}
		return connection;
	}
}
