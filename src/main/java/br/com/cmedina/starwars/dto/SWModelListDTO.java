package br.com.cmedina.starwars.dto;

import java.io.Serializable;
import java.util.List;

public class SWModelListDTO<T> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int count;
    public String next;
    public String previous;
    public List<T> results;

    public boolean hasMore() {
        return (next != null && !next.isEmpty());
    }

}
