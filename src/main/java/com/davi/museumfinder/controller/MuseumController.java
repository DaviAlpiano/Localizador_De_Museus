package com.davi.museumfinder.controller;

import com.davi.museumfinder.dto.MuseumDto;
import com.davi.museumfinder.model.Coordinate;
import com.davi.museumfinder.model.Museum;
import com.davi.museumfinder.service.MuseumServiceInterface;
import com.davi.museumfinder.util.ModelDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Museum controller.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {
  private MuseumServiceInterface museumService;

  /**
   * Instantiates a new Museum controller.
   *
   * @param museumService the museum service
   */
  @Autowired
  public MuseumController(MuseumServiceInterface museumService) {
    this.museumService = museumService;
  }

  /**
   * Create museum response entity.
   *
   * @param newMuseum the new museum
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody Museum newMuseum) {
    Museum museum = museumService.createMuseum(newMuseum);
    return ResponseEntity.status(HttpStatus.CREATED).body(museum);
  }

  /**
   * Closest museum response entity.
   *
   * @param lat         the lat
   * @param lng         the lng
   * @param maxDistance the max dist km
   * @return the response entity
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> closestMuseum(@RequestParam
      String lat, String lng, @RequestParam(name = "max_dist_km") double maxDistance
  ) {
    Coordinate coordinate = new Coordinate(Double.parseDouble(lat), Double.parseDouble(lng));
    Museum museum = museumService.getClosestMuseum(coordinate, maxDistance);
    MuseumDto museumDto = ModelDtoConverter.modelToDto(museum);
    return ResponseEntity.status(HttpStatus.OK).body(museumDto);
  }

  /**
   * Gets museum.
   *
   * @param id the id
   * @return the museum
   */
  @GetMapping("/museums/{id}")
  public ResponseEntity<MuseumDto> getMuseum(@PathVariable Long id) {
    Museum museum = museumService.getMuseum(id);
    MuseumDto museumDto = ModelDtoConverter.modelToDto(museum);
    return ResponseEntity.status(HttpStatus.OK).body(museumDto);
  }
}
