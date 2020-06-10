package teamB.BIS2211.TankApp.Model;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "leaderboardData", path = "leaderboardData")
public interface LeaderboardDataRepository extends PagingAndSortingRepository<LeaderboardData, Long> 
{
    List<LeaderboardData> findByUser(@Param("name") String name); 
    LeaderboardData findById(@Param("id") int id); 
}