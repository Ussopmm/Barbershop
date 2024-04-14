package io.ussopm.AdminService.service.impl;

import io.ussopm.AdminService.exception.AlreadyExistsException;
import io.ussopm.AdminService.model.Barber;
import io.ussopm.AdminService.repository.BarberRepository;
import io.ussopm.AdminService.service.BarberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BarberServiceImpl implements BarberService {
    private final BarberRepository barberRepository;
    @Override
    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }

    @Override
    public Barber getBarberById(int barberId) {
        return barberRepository.findById(barberId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public ResponseEntity<HttpStatus> save(Barber barber) {
        if (barberRepository.findBarberByName(barber.getName()).isPresent()) {
            throw new AlreadyExistsException();
        } else {
            barberRepository.save(barber);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<HttpStatus> updateBarberById(int customerId, Barber barberForUpdate) {
        this.barberRepository.findById(customerId).ifPresentOrElse(barber -> {
            barber.setName(barberForUpdate.getName());
            barber.setEmail(barberForUpdate.getEmail());
            barber.setPhoneNumber(barberForUpdate.getPhoneNumber());
        },() -> {
            throw new NoSuchElementException();
        } );

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<HttpStatus> deleteBarberById(int barberId) {
        if (!this.barberRepository.findById(barberId).isPresent()) {
            throw new NoSuchElementException();
        } else {
            this.barberRepository.deleteById(barberId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }
}
