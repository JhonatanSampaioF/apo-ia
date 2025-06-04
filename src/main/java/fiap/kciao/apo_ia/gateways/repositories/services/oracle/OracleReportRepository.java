package fiap.kciao.apo_ia.gateways.repositories.services.oracle;

import fiap.kciao.apo_ia.gateways.dtos.reports.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OracleReportRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<RelatorioDoencaAbrigados> gerarRelatorioDoencaAbrigados() {
        String sql = """
        SELECT
            d.nome AS nome_doenca,
            COUNT(ad.abrigado_id) AS total_abrigados
        FROM
            doenca d
        JOIN
            abrigado_doencas ad ON d.id = ad.doencas_id
        GROUP BY
            d.nome
        ORDER BY
            total_abrigados DESC
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> RelatorioDoencaAbrigados.builder()
                .nomeDoenca(rs.getString("nome_doenca"))
                .totalAbrigados(rs.getLong("total_abrigados"))
                .dataGeracao(LocalDateTime.now())
                .build());
    }

    public List<RelatorioOcupacaoLocal> gerarRelatorioOcupacaoLocais() {
        String sql = """
        SELECT 
            l.nome AS nome_local,
            l.capacidade,
            l.qtd_abrigados,
            ROUND((l.qtd_abrigados / l.capacidade) * 100, 2) AS percentual_ocupacao
        FROM local l
        ORDER BY percentual_ocupacao DESC
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> RelatorioOcupacaoLocal.builder()
                .nomeLocal(rs.getString("nome_local"))
                .capacidade(rs.getInt("capacidade"))
                .qtdAbrigados(rs.getInt("qtd_abrigados"))
                .percentualOcupacao(rs.getDouble("percentual_ocupacao"))
                .dataGeracao(LocalDateTime.now())
                .build()
        );
    }

    public List<RelatorioVoluntarioHabilidade> gerarRelatorioVoluntariosHabilidades() {
        String sql = """
        SELECT
            a.nome AS nome_voluntario,
            h.nome AS habilidade,
            g.nome AS grupo_habilidade,
            l.nome AS nome_local
        FROM voluntario v
        JOIN abrigado a ON v.abrigado_id = a.id
        JOIN voluntario_habilidades vh ON vh.voluntario_id = v.id
        JOIN habilidade h ON vh.habilidades_id = h.id
        JOIN grupo_habilidade g ON h.grupo_habilidade_id = g.id
        JOIN local l ON a.local_id = l.id
        ORDER BY nome_local, nome_voluntario
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> RelatorioVoluntarioHabilidade.builder()
                .nomeVoluntario(rs.getString("nome_voluntario"))
                .habilidade(rs.getString("habilidade"))
                .grupoHabilidade(rs.getString("grupo_habilidade"))
                .nomeLocal(rs.getString("nome_local"))
                .dataGeracao(LocalDateTime.now())
                .build()
        );
    }

    public List<RelatorioIdadeAbrigadosLocal> gerarRelatorioIdadePorLocal() {
        String sql = """
        SELECT
            l.nome AS nome_local,
            COUNT(a.id) AS total_abrigados,
            ROUND(AVG(a.idade), 1) AS idade_media,
            MAX(a.idade) AS idade_maxima,
            MIN(a.idade) AS idade_minima
        FROM local l
        LEFT JOIN abrigado a ON a.local_id = l.id
        GROUP BY l.nome
        ORDER BY idade_media DESC
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> RelatorioIdadeAbrigadosLocal.builder()
                .nomeLocal(rs.getString("nome_local"))
                .totalAbrigados(rs.getLong("total_abrigados"))
                .idadeMedia(rs.getDouble("idade_media"))
                .idadeMaxima(rs.getInt("idade_maxima"))
                .idadeMinima(rs.getInt("idade_minima"))
                .dataGeracao(LocalDateTime.now())
                .build()
        );
    }

    public List<RelatorioOcupacaoComVoluntarios> gerarResumoComVoluntarios() {
        String sql = """
        SELECT
            l.nome AS nome_local,
            COUNT(DISTINCT a.id) AS total_abrigados,
            COUNT(DISTINCT v.id) AS total_voluntarios,
            l.capacidade,
            ROUND((COUNT(DISTINCT a.id) / l.capacidade) * 100, 2) AS ocupacao_percentual
        FROM local l
        LEFT JOIN abrigado a ON a.local_id = l.id
        LEFT JOIN voluntario v ON v.abrigado_id = a.id
        GROUP BY l.nome, l.capacidade
        ORDER BY ocupacao_percentual DESC
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> RelatorioOcupacaoComVoluntarios.builder()
                .nomeLocal(rs.getString("nome_local"))
                .totalAbrigados(rs.getLong("total_abrigados"))
                .totalVoluntarios(rs.getLong("total_voluntarios"))
                .capacidade(rs.getInt("capacidade"))
                .ocupacaoPercentual(rs.getDouble("ocupacao_percentual"))
                .dataGeracao(LocalDateTime.now())
                .build()
        );
    }

}

