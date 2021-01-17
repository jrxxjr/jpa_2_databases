package br.com.tidicas.main;

import java.util.Date;
import java.util.logging.Logger;
import org.junit.Test;
import br.com.tidicas.dao.BlogDao;
import br.com.tidicas.dao.CategoriaDao;
import br.com.tidicas.model.Blog;
import br.com.tidicas.model.Categoria;
import br.com.tidicas.tipos.BancoDeDados;

/**
 * Classe para geração das tabelas
 * 
 * @author Evaldo Junior
 *
 */
public class TesteCrudJpa {
	private static final Logger LOGGER = Logger.getLogger(TesteCrudJpa.class.getName());

	@Test
	public void execute() throws Exception {
		
		LOGGER.info("inicio - banco de dados mysql");
		fillData(BancoDeDados.MYSQL);
		LOGGER.info("fim - banco de dados mysql");
		
		LOGGER.info("inicio - banco de dados postgres");
		fillData(BancoDeDados.POSTGRES);
		LOGGER.info("fim - banco de dados postgres");
		
	}

	private void fillData(BancoDeDados bancoDeDados) {
		CategoriaDao daoCategoria = new CategoriaDao(bancoDeDados);
		BlogDao daoBlog = new BlogDao(bancoDeDados);

		// 1 Entidade Categoria
		Categoria categoria1 = new Categoria();
		categoria1.setDescricao("categoria new");
		
		Categoria categoria2 = new Categoria();
		categoria2.setDescricao("categoria 2");

		daoCategoria.adiciona(categoria1);
		LOGGER.info("categoria - inclusao:" + categoria1.getDescricao());
		
		daoCategoria.adiciona(categoria2);
		LOGGER.info("categoria - inclusao:" + categoria2.getDescricao());
		
		categoria1 = daoCategoria.busca(categoria1.getCodigo());
		LOGGER.info("categoria - consulta:" + categoria1.getDescricao());

		categoria1.setDescricao("categoria update");		
		categoria1=daoCategoria.atualiza(categoria1);
		LOGGER.info("categoria - atualizacao:" + categoria1.getDescricao());
		
		categoria2.setDescricao("categoria2 update");
		categoria2 = daoCategoria.atualiza(categoria2);
		LOGGER.info("categoria - atualizacao:" + categoria2.getDescricao());
		
		// 2 Entidade Blog
		Blog blog1 = new Blog();
		blog1.setCategoria(categoria1);
		blog1.setConteudo("conteúdo teste");
		blog1.setDtevento(new Date());
		blog1.setPublicar(1);
		blog1.setTitulo("título");

		daoBlog.adiciona(blog1);
		LOGGER.info("blog - inclusao:" + blog1.getTitulo());
		
		blog1 = daoBlog.busca(blog1.getCodigo());
		LOGGER.info("blog - consulta:" + blog1.getTitulo());

		blog1.setConteudo("conteúdo teste update");
		blog1.setDtevento(new Date());
		blog1.setPublicar(0);
		blog1.setTitulo("título update");

		blog1=daoBlog.atualiza(blog1);
		LOGGER.info("blog - atualizacao:" + blog1.getTitulo());

		blog1 = daoBlog.busca(blog1.getCodigo());
		LOGGER.info("blog - consulta:" + blog1.getTitulo());

		daoBlog.remove(blog1);
		LOGGER.info("blog - exclusao:" + blog1.getTitulo());
		
		daoCategoria.remove(categoria1);
		LOGGER.info("categoria - exclusao:" + categoria1.getDescricao());
		
		daoCategoria.remove(categoria2);
		LOGGER.info("categoria - exclusao:" + categoria2.getDescricao());
		
	}

}