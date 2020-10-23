package test.boot.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.boot.spring.model.Employee;
import test.boot.spring.model.Town;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {
    Town findByName(String name);
}
