package fiap.kciao.apo_ia.usecases.services.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlocacaoIAService {

    private final ChatClient chatClient;
    private final DataTools dataTools;

    public String obterResposta(String pergunta) {
        return chatClient.prompt()
                .system("""
                    Você é um assistente de alocação de voluntários em centros humanitários.\s
                    Sua função é analisar os dados disponíveis e sugerir a melhor distribuição de voluntários com base em:
                    - Gravidade das doenças e ferimentos dos abrigados
                    - Habilidades dos voluntários
                    - Capacidade e ocupação atual do local
                   \s
                    Responda com clareza, de forma objetiva e, se possível, em formato de lista.
                   \s""")
                .user("""
                    %s
                    
                    ---
                    
                    Pergunta do usuário: %s
                    """.formatted(dataTools.getContext(), pergunta))
                .call()
                .content();
    }
}
