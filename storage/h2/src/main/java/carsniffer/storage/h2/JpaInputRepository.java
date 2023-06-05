package carsniffer.storage.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaInputRepository extends JpaRepository<JpaInput, Long> {

}
