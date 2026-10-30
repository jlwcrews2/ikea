package no.jlwcrews.ikea.bom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BomRepo extends JpaRepository<Bom, Long>{}
