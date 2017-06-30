package br.com.dsrh.demo.web.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id_venda", "data", "loja", "pdv", "itens" })
public class Venda implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -474417552226920110L;
	
	@JsonProperty("id_venda")
	private Long id;
	
	private Date data;
	
	private Long loja;
	
	private Long pdv;
	
	@JsonIgnore
	private String status;
	
	private List<ItemVenda> itens;
	
	public Venda() {
		
	}
	
	public Venda(Long id, Date data, Long loja, Long pdv, String status) {
		super();
		this.id = id;
		this.data = data;
		this.loja = loja;
		this.pdv = pdv;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getLoja() {
		return loja;
	}

	public void setLoja(Long loja) {
		this.loja = loja;
	}

	public Long getPdv() {
		return pdv;
	}

	public void setPdv(Long pdv) {
		this.pdv = pdv;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
