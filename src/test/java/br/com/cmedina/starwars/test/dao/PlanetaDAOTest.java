package br.com.cmedina.starwars.test.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoDatabase;

import br.com.cmedina.starwars.dao.PlanetaDAO;
import br.com.cmedina.starwars.entities.Planeta;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlanetaDAOTest {

	private static PlanetaDAO dao;
    private static Planeta planeta;
    
    @BeforeClass
    public static void setUpClass() {
		System.out.println("setup");
	    try {
	    	MongoClient mongoClient = new MongoClient();
	        mongoClient.dropDatabase("morphia_test");
	        Morphia morphia = new Morphia();
	        MongoDatabase db = mongoClient.getDatabase("morphia_test");
	        Datastore ds = morphia.createDatastore(mongoClient, db.getName());
	        dao = new PlanetaDAO(ds);
	    } catch (final Exception e) {
	        throw new RuntimeException(e);
	    }		
    }
    

	@Test
	public void aaInserirPlaneta() {
		planeta = new Planeta(null, "Nome", "Clima", "Terreno", 1);
		dao.save(planeta);
		assertNotNull("Planeta não foi inserido.", planeta.getId());
	}


	@Test
	public void bbRecuperarPlanetas() throws Exception {
		List<Planeta> list = dao.find().asList();
		assertNotNull("A lista é nula.", list);
		assertFalse("A lista está vazia.", list.isEmpty());
	}

	@Test
	public void ccAlterarPlaneta() {
		planeta.setNome("Marte");
		dao.save(planeta);
		Planeta p = dao.findOne("id", planeta.getId());
		assertTrue("O nome não foi alterado.", "Marte".equals(p.getNome()));
	}
	
	
	@Test
	public void zzExcluirPlaneta() throws Exception {
		Planeta p = dao.findOne("id", planeta.getId());
		WriteResult r = dao.delete(p);
		assertTrue("Planeta não foi excluído", r.getN() > 0);
	}
	
	
	
}
