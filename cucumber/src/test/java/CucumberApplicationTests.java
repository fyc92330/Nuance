import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "cucumber", plugin = "pretty")
class CucumberApplicationTests {

	@Test
	void contextLoads() {
	}

}
