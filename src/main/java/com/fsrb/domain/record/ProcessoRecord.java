package com.fsrb.domain.record;

import java.time.LocalDateTime;

public record ProcessoRecord(Long id, String npu, LocalDateTime dataCadastro, String uf) {

}
