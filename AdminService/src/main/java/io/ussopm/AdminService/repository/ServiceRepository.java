package io.ussopm.AdminService.repository;



import io.ussopm.AdminService.model.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceItem, Integer> {

    Optional<ServiceItem> findServiceItemByName(String serviceName);
}
