package com.fiap.FiapMeet.controllers;


import com.fiap.FiapMeet.dtos.DateUpdateRequestDTO;
import com.fiap.FiapMeet.dtos.ReserveRequestDTO;
import com.fiap.FiapMeet.dtos.ReserveResponseDTO;
import com.fiap.FiapMeet.entities.Reserve;
import com.fiap.FiapMeet.mappers.ReserveMapper;
import com.fiap.FiapMeet.services.ReserveService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/reserves", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @GetMapping
    public ResponseEntity<Page<ReserveResponseDTO>> getAllReserves(Pageable pageable){
        Page<ReserveResponseDTO> reserves = reserveService.getAllReserves(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(reserves);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReserveResponseDTO> getReserve(@PathVariable @Valid UUID id){
        Reserve reserve = reserveService.getReserveById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ReserveMapper.reserveToReserveDTO(reserve));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createReserve(@RequestBody @Valid ReserveRequestDTO reserveRequestDTO) throws JSONException {
        Reserve reserve = reserveService.createReserve(reserveRequestDTO);

        JSONObject response = new JSONObject();
        response.put("id", reserve.getId());
        response.put("Start Date", reserve.getStart_date());
        response.put("End Date", reserve.getEnd_date());
        response.put("quantity", reserve.getQuantity());
        response.put("id room", reserve.getId_room());
        response.put("message", "reserve successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }

    @PutMapping("/update-reserve/{id}")
    public ResponseEntity<String> updatePrice(@PathVariable UUID id, @RequestBody @Valid DateUpdateRequestDTO dateUpdateRequestDTO) throws JSONException {
        Reserve reserve = reserveService.updateReserve(id, dateUpdateRequestDTO);

        JSONObject response = new JSONObject();
        response.put("message","Reserve successfully updated");
        return ResponseEntity.status(HttpStatus.OK).body(response.toString());
    }

    @DeleteMapping("/remove-reserve/{id}")
    public ResponseEntity<String> deleteReserve(@PathVariable UUID id) throws JSONException {
        reserveService.deleteReserve(id);

        JSONObject response = new JSONObject();
        response.put("message","Reserve successfully deleted");
        return ResponseEntity.status(HttpStatus.OK).body(response.toString());
    }
}
