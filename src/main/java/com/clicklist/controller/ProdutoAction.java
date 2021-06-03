package com.clicklist.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.omnifaces.cdi.ViewScoped;

import com.clicklist.dao.ItensDaListaDAO;
import com.clicklist.dao.MinhaListaDAO;
import com.clicklist.dao.ProdutoDAO;
import com.clicklist.dao.UsuarioDAO;
import com.clicklist.model.entity.ItensDaLista;
import com.clicklist.model.entity.MinhaLista;
import com.clicklist.model.entity.Produto;
import com.clicklist.model.entity.Usuario;
import com.clicklist.util.JSFUtil;
import com.clicklist.util.Session;
import com.clicklist.util.UtilRelatorio;

@Named
@ViewScoped
public class ProdutoAction implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Produto> produtos;
	
	
	private Produto produto;

	private List<Produto> produtoSelecionado = new ArrayList<Produto>();

	Double soma;

	@Inject
	ProdutoDAO produtoDAO;

	@Inject
	MinhaLista minhaLista;

	@Inject
	MinhaListaDAO minhaListaDAO;

	@Inject
	UsuarioDAO usuarioDAO;
	
	@Inject
	ItensDaLista itensDaLista;
	
	@Inject
	ItensDaListaDAO itensDaListaDAO;
	
	List<ItensDaLista> itensDaListaTransient = new ArrayList<ItensDaLista>();


	@PostConstruct
	public void init() {
		HttpSession session = Session.getSession();
		produtos = produtoDAO.listar();
		if (session.getAttribute("username") == null) {
			session.setAttribute("username", "default");
		}
	}

	public void adicionaLista(Produto prod) {
		prod.setTotal(prod.getPreco() * prod.getQuantidade());
		produtoSelecionado.add(prod);
		prod.setQuantidade(0);
		JSFUtil.adicionarMensagemSucesso(prod.getNome()+ " adicionado a sua lista!", null);

	}

	public void finalizarLista() {
		if (produtoSelecionado.size() > 0) {
			if (UsuarioAction.isLogged()) {
				Usuario usuario = usuarioDAO.listByUsername(Session.getUserName());
				UUID id = UUID.randomUUID();
				minhaLista.setIdMinhaLista(id.toString());
				minhaLista.setUsuario(usuario);
				minhaLista.setValorTotal(soma);
				minhaListaDAO.save(minhaLista);
				for(Produto produto : produtoSelecionado){
					itensDaLista.setMinhaLista(id.toString());
					itensDaLista.setProdutos(produto);
					itensDaLista.setTotal(produto.getTotal());
					itensDaLista.setQtd(produto.getTotal() / produto.getPreco());
					itensDaListaDAO.save(itensDaLista);
				}
				minhaLista = new MinhaLista();
				produtoSelecionado = new ArrayList<Produto>();
				JSFUtil.adicionarMensagemSucesso("Lista Salva!", null);
			} else {
				JSFUtil.adicionarMensagemErro("Necessario que esteja logado para salvar uma lista.",null);
			}
		} else if (minhaLista.getNome() == null) {
			JSFUtil.adicionarMensagemErro("Necessario que a lista tenha um nome!", null);
		} else {
			JSFUtil.adicionarMensagemErro("Necessario itens na lista para finalizar.", null);

		}
	}
	
	public void removeProdutoLista(Produto prod){
		for (Produto p : produtoSelecionado){
			if(p.getNome() == prod.getNome()){
				produtoSelecionado.remove(p);
				break;
			}
		}
	}
	
	public List<Produto> getListarProdutos() {
		return new ProdutoDAO().listar();
	}
	
	public void relatorioProdutos() {
		Map<String, Object> parametros = new HashMap<>();
		UtilRelatorio.imprimirRelatorio("relatorioProdutos", parametros, produtoDAO.listar());
	}
	
	public void prepararNovo () {
		produto = new Produto();
	}
	
	public void salvarProduto() {
		try {
			produtoDAO.save(produto);
			JSFUtil.adicionarMensagemSucesso("Sucesso", "Salvo com Sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro", "Erro ao realizar a Inclusão!");
		}
	}
	

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public MinhaLista getMinhaLista() {
		return minhaLista;
	}

	public void setMinhaLista(MinhaLista minhaLista) {
		this.minhaLista = minhaLista;
	}

	public List<Produto> getProdutoSelecionado() {
		soma = new Double(0);
		for (Produto prod : produtoSelecionado) {
			soma = soma + prod.getTotal();
		}
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(List<Produto> produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public double getSoma() {
		return soma;
	}

	public void setSoma(double soma) {
		this.soma = soma;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}