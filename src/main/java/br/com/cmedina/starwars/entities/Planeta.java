package br.com.cmedina.starwars.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("planetas")
public class Planeta {


	@Id
	private String id;
	private String nome;
	private String clima;
	private String terreno;
	private int quantAparicoes;
	
	public Planeta() {
		
	}
	
	public Planeta(String id, String nome, String clima, String terreno, int quantAparicoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.quantAparicoes = quantAparicoes;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	public String getTerreno() {
		return terreno;
	}
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	public int getQuantAparicoes() {
		return quantAparicoes;
	}
	public void setQuantAparicoes(int quantAparicoes) {
		this.quantAparicoes = quantAparicoes;
	}
	public String getId() {
		return id;//objectId != null ? objectId.toString() : null;
	}
	public void setId(String id) {
		this.id = id;
	}
	/*
	public ObjectId getObjectId() {
		return objectId;
	}
	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}
	*/
	
	
}
