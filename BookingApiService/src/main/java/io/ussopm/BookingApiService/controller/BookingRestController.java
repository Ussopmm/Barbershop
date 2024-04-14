package io.ussopm.BookingApiService.controller;

import io.ussopm.BookingApiService.dto.BarberDTO;
import io.ussopm.BookingApiService.dto.ServiceItemDTO;
import io.ussopm.BookingApiService.model.Barber;
import io.ussopm.BookingApiService.model.ServiceItem;
import io.ussopm.BookingApiService.service.BarberService;
import io.ussopm.BookingApiService.service.BookingService;
import io.ussopm.BookingApiService.service.ServiceItemService;
import io.ussopm.BookingApiService.view.BookingView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book/api")
@RequiredArgsConstructor
public class BookingRestController {

    private final ServiceItemService itemService;
    private final BarberService barberService;
    private final BookingService bookingService;
    private final ModelMapper mapper;

    @GetMapping("/service/{id}")
    public ServiceItemDTO serviceById(@PathVariable("id") int id) {
        return convertToDTO(this.itemService.getServiceById(id).get());
    }
    @GetMapping("/service")
    public List<ServiceItemDTO> services() {
        return this.itemService.getAllServices().stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/employee/{id}")
    public BarberDTO barberById(@PathVariable("id") int id) {
        return convertToBarberDTO(this.barberService.getBarberById(id).get());
    }
    @GetMapping("/employee")
    public List<BarberDTO> select_Barber(@RequestParam int serviceId) {
        return this.barberService.getAllBarbersByServiceId(serviceId).stream().map(this::convertToBarberDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/order")
    public List<LocalTime> orderAvailableTime(@RequestParam int serviceId, @RequestParam int barberId,
                                              @RequestParam(required = false) String dateString) {
        if (!barberService.existBarberById(barberId)) {
            throw new NoSuchElementException("Barber not found");
        }else if(!itemService.existsServiceById(serviceId)) {
            throw new NoSuchElementException("Service not found");
        } else {
            return bookingService.getAvailableTimeList(dateString, barberId);
        }
    }
    @PostMapping("/save")
    private ResponseEntity<HttpStatus> bookingCustomerForBarber(@RequestBody @Valid BookingView bookingView, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            bookingService.save(bookingView);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    private ServiceItemDTO convertToDTO(ServiceItem serviceItem) {
        return mapper.map(serviceItem, ServiceItemDTO.class);
    }
    private BarberDTO convertToBarberDTO(Barber barber) {
        return mapper.map(barber, BarberDTO.class);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchElementException(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        exception.getMessage()));
    }
}
