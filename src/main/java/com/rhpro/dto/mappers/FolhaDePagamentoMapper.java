package com.rhpro.dto.mappers;

import com.rhpro.dto.outputs.FolhaDePagamentoOutput;
import com.rhpro.entities.FolhaDePagamento;

public interface FolhaDePagamentoMapper {

    FolhaDePagamentoOutput paraSaida(FolhaDePagamento entidade);

}
