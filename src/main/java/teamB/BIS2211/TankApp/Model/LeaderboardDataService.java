package teamB.BIS2211.TankApp.Model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaderboardDataService 
{
    @Autowired
    LeaderboardDataRepository leaderboardDataRepository;

    public ArrayList<LeaderboardData> getAllData() 
    {
        ArrayList<LeaderboardData> data = new ArrayList<LeaderboardData>();
        leaderboardDataRepository.findAll().forEach(entry -> data.add(entry));
        return data;
    }

    public ArrayList<LeaderboardData> getDataByUser(String user) 
    {
        ArrayList<LeaderboardData> data = new ArrayList<LeaderboardData>();
        leaderboardDataRepository.findByUser(user).forEach(entry -> data.add(entry));
        return data;
    }

    public LeaderboardData getDataByID(int id)
    {
        return leaderboardDataRepository.findById(id);
    }

    public void updateData(LeaderboardData data)
    {
        data.setChecked(true);
        leaderboardDataRepository.deleteById(data.getId());
        leaderboardDataRepository.save(data); 
    }
}