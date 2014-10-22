package com.rhcloud.pugmg.cinetheatrosys.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Modelo dos dados dos usuários persistidos no banco
 * @author Daniel Severo Estrázulas
 */
@Entity
public class Usuario {

	@Id
	@GeneratedValue
	@Column(updatable =false)
	private Integer id;
 
	@Enumerated(EnumType.STRING)
	@Column(updatable =false)
	private TipoUsuario tipo;

	@Column(updatable =false,columnDefinition = "BIT",length=1)
	private Boolean ativo;
	
	@Column(updatable = false)
	private String email;
	
	private String telefone;
	
	private String nome;
	
	@Column(updatable=false)
	private String senha;

	@Column(updatable=false)
	private String salt;

	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private List<Estoria> estorias;
	
	private String cpf;
	
	private String cidade;
	

	private String estado;
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Usuario(){
		
	}
	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getSenha() {
		return senha;
	}

	public String getSalt() {
		return salt;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}
	
	public String getNome() {
		return nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public String getEmail() {
		return email;
	}
	

	public boolean isAtivo() {
		return ativo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public boolean naoPossuiSenha() {
		return this.getSenha() == null || this.getSenha().isEmpty();
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
