package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.entity.enums.ApartmentType;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

//    @Query("SELECT o FROM Offer AS o WHERE o.apartment.apartmentType = :apartmentType " +
//            "ORDER BY o.apartment.area DESC, o.price")
//    List<Offer> findSearchedOffers(ApartmentType apartmentType);

    List<Offer> findAllByApartment_ApartmentTypeOrderByApartment_AreaDescPriceAsc(ApartmentType apartment_apartmentType);

}
