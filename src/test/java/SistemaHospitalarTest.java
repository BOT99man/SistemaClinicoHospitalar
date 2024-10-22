
import Exceptions.ExameNaoEncontradoException;
import Exceptions.PacienteNaoEncontradoException;
import Exceptions.ProntuarioNaoEncontradoException;
import SistemaHospital.*;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class SistemaHospitalarTest {

    private SistemaHospitalar sistema;
    private Medico medico;
    private Paciente paciente;
    private Prontuario prontuario;
    private Exame exame;
    private TipoExame tipoExame;

    @Before
    public void setUp() throws ParseException {
        sistema = new SistemaHospitalar();
        medico = new Medico("CRM123", 1, "Dr. João");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        paciente = new Paciente("Maria", "01/02/2004","123456789");
        prontuario = new Prontuario(sdf1.parse("04/02/2004"),medico,paciente,"Alergia");
        tipoExame = TipoExame.CARDIOLOGICOS;
    }

    @Test
    public void testRegistrarExame() {
        Date data = new Date();
        sistema.registrarExame(data, "Exame de sangue", "EX001", medico, paciente, TipoExame.LABORATORIAIS);

        try {
            Exame exame = sistema.consultarExame("EX001");
            assertNotNull(exame); // Verifica se o exame foi registrado
            assertEquals("Exame de sangue", exame.getDescricao());
            assertEquals(TipoExame.LABORATORIAIS, exame.getTipo());
        } catch (ExameNaoEncontradoException e) {
            fail("Exame não deveria lançar exceção");
        }
    }

    @Test
    public void testConsultarExame() throws ExameNaoEncontradoException {
        sistema.registrarExame(new Date(), "Exame de sangue", "EX123", medico, paciente, TipoExame.CARDIOLOGICOS);

        Exame exameConsultado = sistema.consultarExame("EX123");
        assertNotNull(exameConsultado);
        assertEquals("EX123", exameConsultado.getId());
    }

    @Test
    public void testConsultarExameNaoEncontrado() {
        assertThrows(ExameNaoEncontradoException.class, () -> sistema.consultarExame("ID_INEXISTENTE"));
    }


    @Test
    public void testAtualizarExame() {
        Date data = new Date();
        sistema.registrarExame(data, "Exame de imagem", "EX002", medico, paciente, TipoExame.IMAGEM);

        try {
            Exame exame = sistema.consultarExame("EX002");
            assertFalse(exame.isRealizado());

            sistema.atualizarExame("EX002"); // Atualiza para realizado
            exame = sistema.consultarExame("EX002");
            assertTrue(exame.isRealizado()); // Verifica que agora foi realizado
        } catch (ExameNaoEncontradoException e) {
            fail("Exame não deveria lançar exceção");
        }
    }

    @Test
    public void testRemoverExame() {
        Date data = new Date();
        sistema.registrarExame(data, "Exame ortopédico", "EX003", medico, paciente, TipoExame.ORTOPEDICOS);

        try {
            assertTrue(sistema.removerExame("EX003"));
            sistema.consultarExame("EX003"); // Deve lançar exceção
            fail("Exame deveria ter sido removido");
        } catch (ExameNaoEncontradoException e) {
            // Exceção esperada
        }
    }



    @Test
    public void testPesquisarPaciente() throws PacienteNaoEncontradoException {
        sistema.getPacientes().put(paciente.getNome(), paciente);

        // Pesquisa o paciente pelo nome
        Paciente pacienteEncontrado = sistema.pesquisarPaciente(paciente.getNome());

        // Verifica se o paciente foi encontrado
        assertNotNull(pacienteEncontrado);
        assertEquals(paciente, pacienteEncontrado);
    }

    @Test
    public void testPesquisarPacienteNaoEncontrado() {
        assertThrows(PacienteNaoEncontradoException.class, () -> sistema.pesquisarPaciente("Paciente Inexistente"));
    }

    @Test
    public void testProcurarProntuario() throws ProntuarioNaoEncontradoException {
        sistema.getPacientes().put(paciente.getNome(), paciente);
        sistema.getProntuarios().put(paciente.getNome(), prontuario);

        Prontuario prontuarioEncontrado = null;
        try {
            prontuarioEncontrado = sistema.procurarProntuario(paciente);
        } catch (ProntuarioNaoEncontradoException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(prontuarioEncontrado);
        assertEquals(prontuario, prontuarioEncontrado);
    }

    @Test
    public void testProcurarProntuarioNaoEncontrado() {
        assertThrows(ProntuarioNaoEncontradoException.class, () -> sistema.procurarProntuario(paciente));
    }


}
