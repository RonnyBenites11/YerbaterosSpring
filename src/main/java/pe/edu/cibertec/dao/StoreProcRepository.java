package pe.edu.cibertec.dao;

import pe.edu.cibertec.entity.StoreProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Repository
public interface StoreProcRepository {
	@Query(value = "exec dbo.SetProccessBaseVirgenToInconcert :CampaingId,:FechaCargaBase",nativeQuery = true)
    Set<?> SetProccessBaseVirgenToInconcert(@Param("CampaingId") String campaingId,@Param("FechaCargaBase") String fechaCargaBase);

    
}
