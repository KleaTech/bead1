//@author Bozzay, Ádám
package hu.kleatech.bead1.dao;

import hu.kleatech.bead1.model.PencilCase;
import hu.kleatech.bead1.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PencilCaseRepository extends JpaRepository<PencilCase, Long> {
	List<PencilCase> findByOwner(User owner);
}
