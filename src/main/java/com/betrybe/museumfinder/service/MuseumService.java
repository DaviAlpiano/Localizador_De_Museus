package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Museum service.
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  private MuseumFakeDatabase database;

  /**
   * Instantiates a new Museum service.
   *
   * @param database the database
   */
  @Autowired
  public MuseumService(MuseumFakeDatabase database) {
    this.database = database;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    Boolean coordinateUtil = CoordinateUtil.isCoordinateValid(coordinate);
    if (!coordinateUtil) {
      throw new InvalidCoordinateException("Invalid coordinate");
    }
    return database.getClosestMuseum(coordinate, maxDistance)
        .orElseThrow(() -> new MuseumNotFoundException("Museum not found"));
  }

  @Override
  public Museum createMuseum(Museum museum) {
    Coordinate coordinate = museum.getCoordinate();
    Boolean coordinateUtil = CoordinateUtil.isCoordinateValid(coordinate);
    if (!coordinateUtil) {
      throw new InvalidCoordinateException("Invalid coordinate");
    }
    return database.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) {
    return null;
  }
}