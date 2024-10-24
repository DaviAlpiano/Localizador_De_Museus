package com.davi.museumfinder.service;

import com.davi.museumfinder.database.MuseumFakeDatabase;
import com.davi.museumfinder.exception.InvalidCoordinateException;
import com.davi.museumfinder.exception.MuseumNotFoundException;
import com.davi.museumfinder.model.Coordinate;
import com.davi.museumfinder.model.Museum;
import com.davi.museumfinder.util.CoordinateUtil;
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
      throw new InvalidCoordinateException();
    }
    return database.getClosestMuseum(coordinate, maxDistance)
        .orElseThrow(MuseumNotFoundException::new);
  }

  @Override
  public Museum createMuseum(Museum museum) {
    Coordinate coordinate = museum.getCoordinate();
    Boolean coordinateUtil = CoordinateUtil.isCoordinateValid(coordinate);
    if (!coordinateUtil) {
      throw new InvalidCoordinateException();
    }
    return database.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) {
    return database.getMuseum(id).orElseThrow(MuseumNotFoundException::new);
  }
}
