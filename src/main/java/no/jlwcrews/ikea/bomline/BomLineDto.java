package no.jlwcrews.ikea.bomline;

import no.jlwcrews.ikea.item.ItemSummaryDto;

public record BomLineDto(
        Long id,
        int quantity,
        ItemSummaryDto item
) {}
