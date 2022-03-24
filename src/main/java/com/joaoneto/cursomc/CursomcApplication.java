package com.joaoneto.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.joaoneto.cursomc.domain.Categoria;
import com.joaoneto.cursomc.domain.Cidade;
import com.joaoneto.cursomc.domain.Cliente;
import com.joaoneto.cursomc.domain.Endereco;
import com.joaoneto.cursomc.domain.Estado;
import com.joaoneto.cursomc.domain.Produto;
import com.joaoneto.cursomc.domain.enums.TipoCliente;
import com.joaoneto.cursomc.repositories.CategoriaRepository;
import com.joaoneto.cursomc.repositories.CidadeRepository;
import com.joaoneto.cursomc.repositories.ClienteRepository;
import com.joaoneto.cursomc.repositories.EnderecoRepository;
import com.joaoneto.cursomc.repositories.EstadoRepository;
import com.joaoneto.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRespository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRespository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Cria (popula o BD) as categorias ao iniciar o programa.
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		//Cria (popula o BD) os produtos ao iniciar o programa.
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		//Faz o relacionamento de produtos nas categorias
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//Faz o relacionamento de categorias nos produtos
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		//Cria (popula o BD) os estados ao iniciar o programa.
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		//Cria (popula o BD) as cidades ao iniciar o programa.
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		//Faz o relacionamento de cidades nos estados
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRespository.saveAll(Arrays.asList(c1, c2, c3));
		
		//Cria (popula o BD) os clientes ao iniciar o programa.
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		//Cria os telefones e faz o relacionamento nos clientes
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		//Cria (popula o BD) os enderecos ao iniciar o programa.
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		//Faz o relacionamento de endereços nos clientes
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRespository.saveAll(Arrays.asList(e1, e2));
		
	}
	
	
	
}
