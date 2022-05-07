package main.repository;

import main.api.response.StatisticsResponse;
import main.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

    Optional<Car> findCarByNumber(@Param("number") String number);

    String query = "SELECT c FROM Car c WHERE " +
            "(c.type = :type or :type is null) and " +
            "(c.color = :color or :color is null) and " +
            "(c.carBrand = :brand or :brand is null) ";

    @Transactional
    @Modifying
    @Query("delete from Car c where c.number like :number")
    void deleteCarByNumber(@Param("number") String number);

    @Query(query + " and (c.yearOfManufacture = :year or :year is null)")
    List<Car> findCarByParam(@Param("type") String type,
                             @Param("color") String color,
                             @Param("brand") String brand,
                             @Param("year") String year);

    @Query(query + " order by c.yearOfManufacture ASC")
    List<Car> findOlderCarByParam(@Param("type") String type,
                                  @Param("color") String color,
                                  @Param("brand") String brand);

    @Query(query + " order by c.yearOfManufacture DESC")
    List<Car> findYoungerCarByParam(@Param("type") String type,
                                    @Param("color") String color,
                                    @Param("brand") String brand);

    @Query("Select new main.api.response.StatisticsResponse(count(c.id)," +
            "(select min(c1.regTime) from Car c1 )," +
            "(select max(c2.regTime) from Car c2 ))" +
            "from Car c")
    StatisticsResponse findAllStatistics();
}
