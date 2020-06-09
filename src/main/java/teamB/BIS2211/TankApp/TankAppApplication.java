package teamB.BIS2211.TankApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TankAppApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(TankAppApplication.class, args);
	}
}
