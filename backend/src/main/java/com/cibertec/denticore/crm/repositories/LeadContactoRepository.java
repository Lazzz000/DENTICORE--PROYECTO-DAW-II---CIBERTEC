package com.cibertec.denticore.crm.repositories;

import com.cibertec.denticore.crm.entities.LeadContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadContactoRepository extends JpaRepository<LeadContacto, Integer> {
}
