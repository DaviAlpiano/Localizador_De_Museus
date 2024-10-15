package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
