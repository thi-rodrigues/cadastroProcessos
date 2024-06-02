package com.fsrb.domain.record;

import java.time.LocalDate;

public record ProcessoRecord(String npu, LocalDate dataVisualizacao, String municipio, String uf) {

}
