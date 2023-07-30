package com.example.project.panaroma.Repository;

import com.example.project.panaroma.Entity.BankDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetailsEntity,Long> {

}
