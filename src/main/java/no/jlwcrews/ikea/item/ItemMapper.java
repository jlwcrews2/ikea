package no.jlwcrews.ikea.item;

import no.jlwcrews.ikea.bom.Bom;
import no.jlwcrews.ikea.bom.BomDto;
import no.jlwcrews.ikea.bomline.BomLine;
import no.jlwcrews.ikea.bomline.BomLineDto;

import java.util.List;

public class ItemMapper {

    public static ItemResponseDto toDto(Item item) {

        Bom bom = item.getBom();

        BomDto bomDto = null;

        if (bom != null) {
            List<BomLineDto> bomLines = bom.getBomLines()
                    .stream()
                    .map(ItemMapper::toDto)
                    .toList();

            bomDto = new BomDto(bom.getId(), bomLines);
        }

        return new ItemResponseDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                bomDto
        );
    }

    private static BomLineDto toDto(BomLine bomLine) {

        Item component = bomLine.getItem();

        ItemSummaryDto itemSummary = new ItemSummaryDto(
                component.getId(),
                component.getName(),
                component.getDescription()
        );

        return new BomLineDto(
                bomLine.getId(),
                bomLine.getQuantity(),
                itemSummary
        );
    }
}