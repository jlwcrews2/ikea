package no.jlwcrews.ikea.bomline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BomLineRepo extends JpaRepository<BomLine,Long> {
}
