package fiap.kciao.apo_ia.usecases.services.reports;

import fiap.kciao.apo_ia.gateways.dtos.reports.*;
import fiap.kciao.apo_ia.gateways.repositories.mongo.*;
import fiap.kciao.apo_ia.gateways.repositories.services.oracle.OracleReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final OracleReportRepository oracleReportRepository;
    private final RelatorioDoencaAbrigadosRepository doencaRepository;
    private final RelatorioIdadeMediaRepository idadeMediaRepository;
    private final RelatorioOcupacaoRepository ocupacaoRepository;
    private final RelatorioTotaisLocalRepository totaisRepository;
    private final RelatorioVoluntarioHabilidadeRepository voluntarioRepository;

    public List<RelatorioDoencaAbrigados> gerarRelatorioDoencaAbrigados() {
        List<RelatorioDoencaAbrigados> relatorio = oracleReportRepository.gerarRelatorioDoencaAbrigados();
        return doencaRepository.saveAll(relatorio);
    }

    public List<RelatorioIdadeAbrigadosLocal> gerarRelatorioIdadeAbrigadosLocal() {
        List<RelatorioIdadeAbrigadosLocal> relatorio = oracleReportRepository.gerarRelatorioIdadePorLocal();
        return idadeMediaRepository.saveAll(relatorio);
    }

    public List<RelatorioOcupacaoComVoluntarios> gerarRelatorioOcupacaoComVoluntarios() {
        List<RelatorioOcupacaoComVoluntarios> relatorio = oracleReportRepository.gerarResumoComVoluntarios();
        return ocupacaoRepository.saveAll(relatorio);
    }

    public List<RelatorioOcupacaoLocal> gerarRelatorioOcupacaoLocal() {
        List<RelatorioOcupacaoLocal> relatorio = oracleReportRepository.gerarRelatorioOcupacaoLocais();
        return totaisRepository.saveAll(relatorio);
    }

    public List<RelatorioVoluntarioHabilidade> gerarRelatorioVoluntarioHabilidade() {
        List<RelatorioVoluntarioHabilidade> relatorio = oracleReportRepository.gerarRelatorioVoluntariosHabilidades();
        return voluntarioRepository.saveAll(relatorio);
    }

    public List<RelatorioDoencaAbrigados> listarRelatoriosDoencaAbrigados() {
        return doencaRepository.findAll();
    }

    public List<RelatorioIdadeAbrigadosLocal> listarRelatoriosIdadeAbrigadosLocal() {
        return idadeMediaRepository.findAll();
    }

    public List<RelatorioOcupacaoComVoluntarios> listarRelatoriosOcupacaoComVoluntarios() {
        return ocupacaoRepository.findAll();
    }

    public List<RelatorioOcupacaoLocal> listarRelatoriosOcupacaoLocal() {
        return totaisRepository.findAll();
    }

    public List<RelatorioVoluntarioHabilidade> listarRelatoriosVoluntarioHabilidade() {
        return voluntarioRepository.findAll();
    }
}