/*
Alunos:
- Pedro Henrique Carvalho Pereira - 10418861
- Mateus Ribeiro Cerqueira - 10443901



Notas:
Ao procurar no google como armazenar numeros em ordem automaticamente, a IA
no navegador sugeriu utilizar o TreeSet.
Foi usado na solução por facilitar os processamentos dos vértices.

Abaixo o link usado de referencia para compreender o TreeSet>:
- https://docs.oracle.com/javase/8/docs/api/java/util/TreeSet.html 
Para os exemplos de implementação:
- https://codegym.cc/pt/groups/posts/pt.1111.treeset-em-java

*/



package GrafoMatriz;

import java.util.Set;
import java.util.TreeSet; // ordem crescente automaticamente

//definição de uma estrutura Matriz de Adjacência para armezanar um grafo
public class TGrafo {
	// Atributos Privados
	private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	int adj[][]; //matriz de adjacência (0 = sem aresta; valor > 0 = peso da aresta)

	// CTE de infinito
	// ref: https://codegym.cc/pt/groups/posts/pt.669.integer-maxvalue-em-java-com-exemplos 
	private static final int INF = Integer.MAX_VALUE;

	// Métodos Públicos
	public TGrafo( int n) {  // construtor
	    this.n = n;
	    // No início dos tempos não há arestas
	    this.m = 0; 
	    // alocação da matriz do TGrafo
	    this.adj = new int [n][n];

	    // Inicia a matriz com zeros
		for(int i = 0; i< n; i++)
			for(int j = 0; j< n; j++)
				this.adj[i][j]=0;	
	}

	// Insere uma aresta no Grafo tal que
	// v é adjacente a w (grafo não ponderado: peso 1)
	public void insereA(int v, int w) {
	    insereA(v, w, 1);
	}

	/*
	Insere uma aresta v->w com peso. O peso deve ser positivo,
	pois o algoritmo de Dijkstra só trabalha com valores positivos.
	O valor 0 na matriz significa que não existe aresta.
	*/
	public void insereA(int v, int w, int peso) {
	    // testa se nao temos a aresta
	    if(adj[v][w] == 0 && peso > 0){
	        adj[v][w] = peso;
	        m++; // atualiza qtd arestas
	    }
	}
	
	// remove uma aresta v->w do Grafo	
	public void removeA(int v, int w) {
	    // testa se temos a aresta
	    if(adj[v][w] != 0 ){
	        adj[v][w] = 0;
	        m--; // atualiza qtd arestas
	    }
	}
	// Apresenta o Grafo contendo
	// número de vértices, arestas
	// e a matriz de adjacência obtida	
	public void show() {
	    System.out.println("n: " + n );
	    System.out.println("m: " + m );
	    for( int i=0; i < n; i++){
	    	System.out.print("\n");
	        for( int w=0; w < n; w++)
	            System.out.print("Adj[" + i + "," + w + "]= " + adj[i][w] + " ");
	    }
	    System.out.println("\n\nfim da impressao do grafo." );
	}



	// DIJKSTRA


	public void dijkstra(int origem) {
		int[] d = new int[n];
		int[] rot = new int[n];
		
		
		Set<Integer> A = new TreeSet<Integer>(); // vértices abertos
		Set<Integer> F = new TreeSet<Integer>(); // vértices fechados
		Set<Integer> S; // sucessores abertos de r



		// INICIALIZAÇÃO
		for (int i = 0; i < n; i++) {
			d[i] = INF;
			rot[i] = 0;
			A.add(i);
		}
		d[origem] = 0;

		// enquanto (A não estiver vazia) faça
		while (!A.isEmpty()) {

			// r <- v E V tal que d1r = min [d1i], i E A
			int r = -1;
			for (int i : A) {
				if (r == -1 || d[i] < d[r])
					r = i;
			}

			// F <- F U {r}; A <- A - {r}
			F.add(r);
			A.remove(r);



			// S <- A n N+(r): sucessores de r que ainda estão abertos
			S = new TreeSet<Integer>();
			for (int i : A) {
				if (adj[r][i] != 0)
					S.add(i);
			}

			// para (i E S) faça
			for (int i : S) {
				
				
				// se d[r] é infinito não há soma
				if (d[r] != INF) {
					
					
					// p <- min [d1i, (d1r + vri)]
					int p = Math.min(d[i], d[r] + adj[r][i]);
					
					
					// se (p < d1i) então d1i <- p; rot(i) <- r
					if (p < d[i]) {
						d[i] = p;
						rot[i] = r + 1;
					}
				}
			}
		}

		// Results
		String linhaRot = "rot     ";
		String linhaVert = "vertice ";
		
		
		for (int i = 0; i < n; i++) {
			linhaRot += String.format("| %2d ", rot[i]);
			linhaVert += String.format("| %2d ", i + 1);
		}
		
		
		System.out.println(linhaRot + "|");
		System.out.println(linhaVert + "|");
		
		
		System.out.println();


		// caminhos D1i
		System.out.println("caminhos:");
		for (int i = 0; i < n; i++) {
			if (d[i] == INF)
				System.out.println((origem + 1) + " -> " + (i + 1) + ": sem caminho");
			else
				System.out.println(caminho(origem, i, rot));
		}
		System.out.println();
	}

	// monta caminho recursivo
	private String caminho(int origem, int i, int[] rot) {
		if (i == origem)
			return String.valueOf(i + 1);
		return caminho(origem, rot[i] - 1, rot) + " -> " + (i + 1);
	}
}