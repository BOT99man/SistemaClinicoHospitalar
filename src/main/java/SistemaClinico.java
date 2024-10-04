public interface SistemaClinico {
    public void agendarConsulta(Paciente paciente, Medico medico, String data);
    public void registrarExame(Paciente paciente, TipoExame tipoExame, String resultado);
    public void atualizarEstoqueRemedios(String remedio, int quantidade);
    public int consultarEstoqueRemedios(String remedio);
    public Consulta consultarConsulta(Paciente paciente);
    public Exame consultarExame(Paciente paciente);
}
