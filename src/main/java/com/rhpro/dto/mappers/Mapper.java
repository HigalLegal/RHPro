package com.rhpro.dto.mappers;

import java.io.IOException;

public interface Mapper<E, I, O> {

    E paraEntidade(I input);
    O paraSaida(E entidade);

}
