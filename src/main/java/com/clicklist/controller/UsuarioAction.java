package com.clicklist.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.omnifaces.cdi.ViewScoped;

import com.clicklist.dao.PessoaDAO;
import com.clicklist.dao.UsuarioDAO;
import com.clicklist.model.entity.Pessoa;
import com.clicklist.model.entity.Produto;
import com.clicklist.model.entity.Usuario;
import com.clicklist.util.JSFUtil;
import com.clicklist.util.Session;

@Named
@ViewScoped
public class UsuarioAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Usuario usuario;

	@Inject
	Pessoa pessoa;

	@Inject
	Produto produto;

	@Inject
	UsuarioDAO usuarioDAO;

	@Inject
	PessoaDAO pessoaDAO;

	private static String username;


	@PostConstruct
	public void init() {
		if (usuario.getPessoa() == null) {
			HttpSession session = Session.getSession();
			usuario.setPessoa(pessoa);
			if (session.getAttribute("username") == null) {
				session.setAttribute("username", "default");
			}
		}
	}

	public String getSalvarCliente() {
		int count = 0;
		List<Usuario> userList = usuarioDAO.listar();
		for (Usuario cl : userList) {
			if (cl.getLogin().contains(usuario.getLogin())) {
				count++;
			}
		}
		if (count <= 1) {
			Date dataNew = new Date();
			usuario.setDataCadastro(dataNew);
			usuarioDAO.save(usuario);
			usuario = new Usuario();
			usuario.setPessoa(new Pessoa());
		}
		return null;
	}

	public String logarCliente() {
		if (usuario.getLogin() == null || usuario.getLogin().isEmpty()
				|| usuario.getSenhaCliente() == null
				|| usuario.getSenhaCliente().isEmpty()) {
			JSFUtil.adicionarMensagemErro("Falha no login",
					"Usuário e senha sao necessários!");
		} else {
			Usuario usu = usuarioDAO.getUsuario(usuario.getLogin(),
					usuario.getSenhaCliente());
			if (usu == null) {
				usu = new Usuario();
				JSFUtil.adicionarMensagemErro("Falha no login",
						"Usuário e senha sao necessários!");
				return null;
			} else {
				JSFUtil.adicionarMensagemSucesso("Bem-Vindo(a)!", usuario
						.getPessoa().getNome());

				HttpSession session = Session.getSession();
				session.setAttribute("username", usuario.getLogin());
				return "home";
			}
		}
		return null;

	}

	public void logout() {
		HttpSession session = Session.getSession();
		session.setAttribute("username", "default");
	}

	public static Boolean notLogged() {
		if (Session.getUserName() == "default") {
			return true;
		}

		return false;
	}

	public static Boolean isLogged() {
		if (Session.getUserName() != "default") {
			username = Session.getUserName();
			return true;
		}
		return false;

	}
	
	public String returnHome(){
		return "home";
	}

	public void limparCampos() {
		usuario = new Usuario();
		JSFUtil.adicionarMensagemSucesso("Sucesso", "Campos Limpos!");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUsername() {
		return username;
	}

	@SuppressWarnings("static-access")
	public void setUsername(String username) {
		this.username = username;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}