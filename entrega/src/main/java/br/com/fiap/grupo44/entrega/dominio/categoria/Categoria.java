package br.com.fiap.grupo44.entrega.dominio.categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class Categoria {

    @Getter
    @AllArgsConstructor
    public enum Patch {
        LIMPEZA(1L,"Limpeza"),
        HIDRAULICA(2L,"Hidráulica"),
        CONFORTO_TERMICO(3L,"Conforto termico"),
        REFRIGERADOR(4L, "Refrigerador"),
        HIGIENE_PESSOAL(5L, "Higiene pessoal"),
        CULINARIA(6L,"Culinaria"),
        CUCÇÃO(7L,"Cucção"),
        PROCESSADOR_DE_ALIMENTOS(8L,"Processador de alimentos"),
        ENTRETENIMENTO(9L, "Entretenimento"),
        LAVANDERIA(10L,"Lavanderia"),
        ILUMINACAO(11L,"Iluminação"),
        ACESSORIO_DE_AQUARIO(12L,"Acessorio de aquario"),
        TECNOLIGIA(13L,"Tecnologia"),
        COMUNICAOCAO(13L,"Comunicação"),
        COSTURA(14L,"Costura"),
        CONSTRUCAO(15L,"Construção");

        private final Long codigo;
        private final String descricao;

        public static List<Patch> listaPath() {
            return Arrays.asList(Patch.values());
        }

        public static Patch buscarPatch(Long codigo) {
            if (codigo == null || codigo.equals(0L)) {
                return null;
            }
            return Arrays.asList(Patch.values()).stream().filter((cat) -> (cat.getCodigo().equals(codigo))).findFirst().orElse(null);
        }

        public static String buscarDescricaoPatchCategoria(Long codigo) {
            Patch status = buscarPatch(codigo);
            if (status == null) {
                return null;
            }
            return status.getDescricao();
        }


    }

}
