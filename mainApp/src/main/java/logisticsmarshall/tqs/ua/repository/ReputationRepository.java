package logisticsmarshall.tqs.ua.repository;

import logisticsmarshall.tqs.ua.model.DriverAdminView;
import logisticsmarshall.tqs.ua.model.Reputation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import logisticsmarshall.tqs.ua.model.Delivery;

import java.util.List;

@Repository
public interface ReputationRepository extends JpaRepository<Reputation, Long> {
    @Query("SELECT new logisticsmarshall.tqs.ua.model.DriverAdminView(x.driver.id,x.driver.user.name,x.driver.user.email,x.driver.vehicle,x.driver.phoneNo,avg(x.rating),count(x.rating)) from Reputation x " +
            "group by x.driver.id,x.driver.user.name,x.driver.user.email,x.driver.phoneNo,x.driver.vehicle" +
            " having avg(x.rating)<= :ratingMax and count(x.rating) >= :reviewMin")
    List<DriverAdminView> findAllByRatingMaximum(@Param("ratingMax") double ratingMax,@Param("reviewMin") Long minReviews);
  
    Reputation findReputationById(long id);

    Reputation findReputationByDelivery(Delivery delivery);
}
