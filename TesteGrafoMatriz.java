/*
Alunos:
- Pedro Henrique Carvalho Pereira - 10418861
- Mateus Ribeiro Cerqueira - 10443901

Compilação e exec:
javac GrafoMatriz/*.java
java GrafoMatriz.TesteGrafoMatriz

*/

package GrafoMatriz;

public class TesteGrafoMatriz {
	public static void main(String args[]) {

		// g1 
		System.out.println("g1: Grafo da Atividade Anterior");
		TGrafo g1 = new TGrafo(4);
		g1.insereA(0, 1, 20); g1.insereA(1, 0, 20); // 1-2
		g1.insereA(0, 2, 30); g1.insereA(2, 0, 30); // 1-3
		g1.insereA(0, 3, 50); g1.insereA(3, 0, 50); // 1-4
		g1.insereA(1, 2, 40); g1.insereA(2, 1, 40); // 2-3
		g1.insereA(1, 3, 15); g1.insereA(3, 1, 15); // 2-4
		g1.insereA(2, 3, 15); g1.insereA(3, 2, 15); // 3-4

		g1.show();
		System.out.println();
		g1.dijkstra(0); // origem v 1

		System.out.println();
		System.out.println();




		// g2
		System.out.println("g2: Grafo do Material da Aula");
		TGrafo g2 = new TGrafo(5);
		g2.insereA(0, 1, 1); // 1->2
		g2.insereA(0, 4, 1); // 1->5
		g2.insereA(1, 2, 1); // 2->3
		g2.insereA(1, 3, 2); // 2->4
		g2.insereA(2, 3, 4); // 3->4
		g2.insereA(2, 4, 2); // 3->5
		g2.insereA(3, 0, 3); // 4->1
		g2.insereA(4, 0, 2); // 5->1
		g2.insereA(4, 3, 1); // 5->4

		g2.show();
		System.out.println();
		g2.dijkstra(0); // origem v 1
	}
}