package com.app.shopping.ecommerce.repository;

import com.app.shopping.ecommerce.entity.DisplayInTilesPromos;
import com.app.shopping.ecommerce.entity.FullwidthDisplayFeaturedPromos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DisplayInTilesPromosRepository extends JpaRepository<DisplayInTilesPromos,Long> {
    @Query("SELECT promo FROM DisplayInTilesPromos promo " +
            "WHERE :searchDate BETWEEN promo.startingDate AND promo.endingDate")
    List<DisplayInTilesPromos> findPromosByDate1(@Param("searchDate") Date searchDate);
}
