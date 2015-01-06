package com.hanxinbank.p2p.core.jpa;

import com.hanxinbank.p2p.core.common.domain.RMB;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;

@Converter(autoApply = true)
public class RMBPersistenceConverter implements AttributeConverter<RMB, BigDecimal> {
    @Override
    public BigDecimal convertToDatabaseColumn(RMB attribute) {
        return attribute.asNumberStripped();
    }

    @Override
    public RMB convertToEntityAttribute(BigDecimal dbData) {
        return RMB.of(dbData);
    }
}
