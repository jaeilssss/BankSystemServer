package com.example.bankserversystem.domain.repository.card;

import com.example.bankserversystem.entity.card.DebitCardDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitCardDetailRepository extends JpaRepository<DebitCardDetail, Long>  {
}
