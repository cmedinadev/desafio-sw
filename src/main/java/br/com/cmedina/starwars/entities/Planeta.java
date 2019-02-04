package br.com.cmedina.starwars.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity("planetas")
public class Planeta {


	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	private String nome;
	private String clima;
	private String terreno;
	private int quantAparicoes;
	
	public Planeta() {
		
	}
	
	public Planeta(ObjectId id, String nome, String clima, String terreno, int quantAparicoes) {
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
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	
}
