package com.davi.museumfinder.service;

import com.davi.museumfinder.model.Coordinate;
import com.davi.museumfinder.model.Museum;

/**
 * Interface for Museum service class.
 */
public interface MuseumServiceInterface {

  /**
   * Gets closest museum.
   *
   * @param coordinate  the coordinate
   * @param maxDistance the max distance
   * @return the closest museum
   */
  Museum getClosestMuseum(Coordinate coordinate, Double maxDistance);

  /**
   * Create museum museum.
   *
   * @param museum the museum
   * @return the museum
   */
  Museum createMuseum(Museum museum);

  /**
   * Gets museum.
   *
   * @param id the id
   * @return the museum
   */
  Museum getMuseum(Long id);
}
