package com.app.travelplan;

import com.app.travelplan.model.entity.Category;
import com.app.travelplan.model.form.AddressForm;
import com.app.travelplan.model.form.LinkForm;
import com.app.travelplan.model.form.PlacesForm;
import com.app.travelplan.repository.CategoryRepository;
import com.app.travelplan.service.AddressService;
import com.app.travelplan.service.LinkService;
import com.app.travelplan.service.PlacesService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class TravelPlanApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelPlanApplication.class, args);

	}
}
