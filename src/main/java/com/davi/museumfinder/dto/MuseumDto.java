package com.davi.museumfinder.dto;

import com.davi.museumfinder.model.Coordinate;

/**
 * The type Museum dto.
 */
public record MuseumDto(
    Long id,
    String name,
    String description,
    String address,
    String collectionType,
    String subject,
    String url,
    Coordinate coordinate
) {

}
