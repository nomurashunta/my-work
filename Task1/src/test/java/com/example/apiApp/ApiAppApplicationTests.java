package com.example.apiApp;
import com.example.apiApp.domain.batch.BatchComponent;
import com.example.apiApp.domain.repository.ItemEditLogRepository;
import org.junit.jupiter.api.Test;
import com.example.apiApp.domain.model.Item;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
class ApiAppApplicationTests {

	@Test
	void doTest() throws Exception{
		Item item = new Item();
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		item.setId("test");
		Set<ConstraintViolation<Item>> violations = validator.validate(item);
		assertThat("invalid", violations.size(), is(1));
		for (ConstraintViolation<Item> violation : violations){
			System.out.println(violation.getMessage());
			System.out.println(violation.getMessageTemplate());
		}
	}

	@Autowired
	ItemEditLogRepository itemEditLogRepository;

	@Test
	void dosome() throws Exception{

	}

	@Autowired
	BatchComponent batchComponent;

	@Test
	void checkBatch() throws Exception{
		batchComponent.printSummary();
	}

}
