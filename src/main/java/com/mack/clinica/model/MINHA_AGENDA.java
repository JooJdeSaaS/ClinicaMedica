import com.mack.clinica.model.AgendarConsultaDAO;
import com.mack.clinica.model.Consulta;
import java.util.List;
import java.util.Scanner;

public class MINHA_AGENDA {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do paciente: ");
        int pacienteId = scanner.nextInt();

        AgendarConsultaDAO dao = new AgendarConsultaDAO("/caminho/real/do/projeto"); // ajuste aqui se necessário
        List<Consulta> consultas = dao.listarConsultasDoPaciente(pacienteId);

        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta agendada para o paciente ID " + pacienteId + ".");
        } else {
            System.out.println("Consultas agendadas:");
            for (Consulta consulta : consultas) {
                System.out.println(consulta);
            }
        }

        scanner.close();
    }
}
