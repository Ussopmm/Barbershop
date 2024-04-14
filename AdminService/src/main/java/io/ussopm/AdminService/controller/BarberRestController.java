package io.ussopm.AdminService.controller;

import io.ussopm.AdminService.dto.BarberDTO;
import io.ussopm.AdminService.exception.AlreadyExistsException;
import io.ussopm.AdminService.model.Barber;
import io.ussopm.AdminService.service.BarberService;
import io.ussopm.AdminService.util.GeneralExceptionsHandler;
import io.ussopm.AdminService.util.MappingClasses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("admin/api/barbers")
@RequiredArgsConstructor
public class BarberRestController {

    private final BarberService barberService;
    private final MappingClasses mapper;
    private final GeneralExceptionsHandler handler;

    @GetMapping()
    public List<Barber> getAllBarbers() {
        return barberService.getAllBarbers();
    }

    @GetMapping("/{id}")
    public Object getBarberById(@PathVariable("id") int barberId) {
        try {
            return barberService.getBarberById(barberId);
        } catch (NoSuchElementException ex) {
            return handler.handleNoSuchElementException(ex, "Barber");
        }
    }
    @PostMapping("/new")
    public ResponseEntity<? extends Object> creatingNewBarber(@RequestBody @Valid BarberDTO barberDTO, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            try {
                return barberService.save(mapper.convertToModel(barberDTO, Barber.class));
            } catch (AlreadyExistsException ex) {
                return handler.handleAlreadyExistsException(ex, "Barber");
            }
        }
    }
    @PatchMapping("/{id}/update")
    private ResponseEntity<? extends Object> updatingCustomer(@PathVariable("id") int barberId, @RequestBody @Valid BarberDTO barberDTO, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            try {
               return barberService.updateBarberById(barberId, mapper.convertToModel(barberDTO, Barber.class));
           } catch (NoSuchElementException ex) {
               return handler.handleNoSuchElementException(ex, "Barber");
           }
        }
    }

    @DeleteMapping("/{id}/delete")
    private ResponseEntity<? extends Object> deletingCustomer(@PathVariable("id") int customerId) {
        try {
            return barberService.deleteBarberById(customerId);
        } catch (NoSuchElementException ex) {
            return handler.handleNoSuchElementException(ex, "Barber");
        }
    }


}
