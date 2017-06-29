package br.com.dsrh.demo.batch.model;

import java.io.Serializable;
import java.util.Date;

public class Processamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 840833226303752425L;

	private Long id;
	
	private Date data;
	
	private Long loja;
	
	private Long pdv;
	
	private String nomeArquivo;
	
	private String status;
	
	public Processamento() {
		
	}
	
	public Processamento(Long id, Date data, Long loja, Long pdv, String nomeArquivo, String status) {
		super();
		this.id = id;
		this.data = data;
		this.loja = loja;
		this.pdv = pdv;
		this.nomeArquivo = nomeArquivo;
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

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
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
		Processamento other = (Processamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
