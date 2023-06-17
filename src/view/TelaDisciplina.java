package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import model.dao.DisciplinaDAO;
import model.dao.FactoryDAO;
import model.entities.Aluno;
import model.entities.Disciplina;

public class TelaDisciplina {
	static DisciplinaDAO disciplinaDao = FactoryDAO.createDisciplinaDAO();

	@SuppressWarnings("resource")
	public static Scanner menuDisciplina(Scanner console) throws InterruptedException, ParseException {

		int opcao = 0;
		do {
			System.out.println("\n\n");
			System.out.println("    ###   Tela: Disciplina     ###");
			System.out.println("    =========================");
			System.out.println("    |     1 - Cadastrar     |");
			System.out.println("    |     2 - Listar        |");
			System.out.println("    |     3 - Alterar       |");
			System.out.println("    |     4 - Excluir       |");
			System.out.println("    |     0 - Retornar      |");
			System.out.println("    =========================");
			System.out.print("    Opcao -> ");
			opcao = console.nextInt();
			console.nextLine();

			switch (opcao) {
			case 1:
				console = cadastrar(console);
				break;
			case 2:
				console = listar(console);
				break;
			case 3:
				console = alterar(console);
				break;
			case 4:
				console = excluir(console);
				break;
			case 0:
				console = TelaPrincipal.menuPrincipal(console);
				break;
			default:
				System.out.println("Opcao invalida!");
				TimeUnit.SECONDS.sleep(1);
			}
		} while (opcao != 0);
		return console;
	}

	private static Scanner cadastrar(Scanner console) {

		Disciplina d = new Disciplina();

		System.out.println("\n\n");
		System.out.println("    ###   Disciplina-Cadastrar ###");
		System.out.println("    =========================");

		System.out.print("    |     Nome: ");
		d.setNomedisciplina(console.nextLine());
		System.out.print("    |     Carga Horaria: ");
		d.setCargahoraria(console.nextInt());

		System.out.println("    =========================");

		disciplinaDao.insert(d);

		console.nextLine();
		return console;
	}

	private static Scanner listar(Scanner console) {
		List<Disciplina> disciplinas = disciplinaDao.findAll();

		System.out.println("\n\n");
		System.out.println("    ###   Disciplina-Listar    ###");
		System.out.println("    =========================");
		System.out.println("    |     Iddisciplina\tNome\tCarga Horaria");
		for (Disciplina d : disciplinas) {
			System.out.println(
					"    |     " + d.getIddisciplina() + "\t" + d.getNomedisciplina() + "\t" + d.getCargahoraria());
		}
		System.out.println("    =========================");
		console.nextLine();
		return console;
	}

	private static Scanner alterar(Scanner console) throws ParseException {
		Disciplina d = new Disciplina();

		System.out.println("\n\n");
		System.out.println("    ###   Disciplina-Alterar ###");
		System.out.println("    =========================");
		
		System.out.print("    |     Id: "); 
		d.setIddisciplina(console.nextInt());
		console.nextLine();

		System.out.print("    |     Nome: ");
		d.setNomedisciplina(console.nextLine());

		System.out.print("    |     Carga Horaria: ");
		d.setCargahoraria(console.nextInt());

		System.out.println("    =========================");

		disciplinaDao.update(d);

		console.nextLine();
		return console;
	}

	private static Scanner excluir(Scanner console) throws ParseException {

		System.out.println("\n\n");
		System.out.println("    ###   Disciplina-Excluir   ###");
		System.out.println("    =========================");
		System.out.print("    |     Digite o Id: ");
		int id = console.nextInt();
		console.nextLine();
		System.out.println("    =========================");

		disciplinaDao.deleteById(id);

		console.nextLine();
		return console;
	}
}
