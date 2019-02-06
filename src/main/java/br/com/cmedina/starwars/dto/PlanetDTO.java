package br.com.cmedina.starwars.dto;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanetDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public String name;
    public String diameter;
    public String gravity;
    public String population;
    public String climate;
    public String terrain;
    public String created;
    public String edited;
    public String url;

    @JsonProperty("rotation_period")
    public String rotationPeriod;

    @JsonProperty("orbital_period")
    public String orbitalPeriod;

    @JsonProperty("surface_water")
    public String surfaceWater;

    @JsonProperty("residents")
    public List<String> residentsUrls;

    @JsonProperty("films")
    public List<String> filmsUrls;
 
	@Override
	public String toString() {
		return "PlanetDTO [name=" + name + ", diameter=" + diameter + ", gravity=" + gravity + ", population="
				+ population + ", climate=" + climate + ", terrain=" + terrain + ", created=" + created + ", edited="
				+ edited + ", url=" + url + ", rotationPeriod=" + rotationPeriod + ", orbitalPeriod=" + orbitalPeriod
				+ ", surfaceWater=" + surfaceWater + ", residentsUrls=" + residentsUrls + ", filmsUrls=" + filmsUrls
				+ "]";
	}    
    
}