package br.com.cmedina.starwars.dto;

import java.io.Serializable;
import java.util.List;

import javax.json.bind.annotation.JsonbProperty;

public class PlanetDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "PlanetDTO [name=" + name + ", diameter=" + diameter + ", gravity=" + gravity + ", population="
				+ population + ", climate=" + climate + ", terrain=" + terrain + ", created=" + created + ", edited="
				+ edited + ", url=" + url + ", rotationPeriod=" + rotationPeriod + ", orbitalPeriod=" + orbitalPeriod
				+ ", surfaceWater=" + surfaceWater + ", residentsUrls=" + residentsUrls + ", filmsUrls=" + filmsUrls
				+ "]";
	}

	public String name;
    public String diameter;
    public String gravity;
    public String population;
    public String climate;
    public String terrain;
    public String created;
    public String edited;
    public String url;

    @JsonbProperty("rotation_period")
    public String rotationPeriod;

    @JsonbProperty("orbital_period")
    public String orbitalPeriod;

    @JsonbProperty("surface_water")
    public String surfaceWater;

    @JsonbProperty("residents")
    public List<String> residentsUrls;

    @JsonbProperty("films")
    public List<String> filmsUrls;
   
}