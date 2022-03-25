package lowk.learning.moneyMana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("lowk.learning.moneyMana.repository")
public class MoneyManaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyManaApplication.class, args);
	}

}
