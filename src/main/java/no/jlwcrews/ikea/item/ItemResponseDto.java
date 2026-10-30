package no.jlwcrews.ikea.item;

import no.jlwcrews.ikea.bom.BomDto;

public record ItemResponseDto(
        Long id,
        String name,
        String description,
        BomDto bom
) {}
