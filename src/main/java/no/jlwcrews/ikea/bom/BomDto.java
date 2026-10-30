package no.jlwcrews.ikea.bom;

import no.jlwcrews.ikea.bomline.BomLineDto;

import java.util.List;

public record BomDto(
        Long id,
        List<BomLineDto> bomLines
) {}