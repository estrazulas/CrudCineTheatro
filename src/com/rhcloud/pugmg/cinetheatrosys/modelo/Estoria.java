package com.rhcloud.pugmg.cinetheatrosys.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;


@Entity
public class Estoria {
	
	@Id
	@GeneratedValue
	@Column(updatable =false)
	private Integer id;
	
	@OneToOne
	private Usuario usuario;
	
	@Column(nullable= false)
	private String nome;
	
	@Lob
	@Column(nullable= false)
	private String texto; 
	
	@Lob
	private byte[] imagem;
	
	@Lob
	private byte[] audio;	
	
	private String formatoImagem;
	
	private String formatoAudio;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable= false)
	private TipoCategoria categoria;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable= false)
	private TipoPeriodo periodo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public byte[] getAudio() {
		return audio;
	}

	public void setAudio(byte[] audio) {
		this.audio = audio;
	}

	public TipoCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(TipoCategoria categoria) {
		this.categoria = categoria;
	}

	public TipoPeriodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(TipoPeriodo periodo) {
		this.periodo = periodo;
	}

	public String getFormatoImagem() {
		return formatoImagem;
	}

	public void setFormatoImagem(String formatoImagem) {
		this.formatoImagem = formatoImagem;
	}

	public String getFormatoAudio() {
		return formatoAudio;
	}

	public void setFormatoAudio(String formatoAudio) {
		this.formatoAudio = formatoAudio;
	}
	
	
}
