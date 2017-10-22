package org;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.models.domain.CustomerRecord;
import org.service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main boot application
 * @author pavansachi
 *
 */

@SpringBootApplication
@ComponentScan(basePackages={"org.controllers, org.service"})
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);		
	}

	private static CustomerRecord parse(String line) {

		String[] lineItems = line.split(",");

		long customerId = Long.parseLong(lineItems[0]);
		double amount = Double.parseDouble(lineItems[2]);
		String date = lineItems[1];
		String transaction = lineItems[3];

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

		Date convDate = null;
		try {
			convDate = formatter.parse(date);
		} catch (ParseException e) {

			convDate = null;
			e.printStackTrace();
		}

		return new CustomerRecord(customerId, convDate, amount, transaction);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {

		Path path = null;
		try {
			path = Paths.get(getClass().getClassLoader()
					.getResource("data.txt").toURI());

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		Stream<String> lines = null;

		List<CustomerRecord> customers = new ArrayList<>();

		try {

			lines = Files.lines(path);

			int idx = 0;

			for (String line: lines.collect(Collectors.toList())) {

				if (++idx > 1) {
					
					CustomerRecord customer = parse(line);

					customers.add(customer);
					
				}
			}
			
			repository.save(customers);
			
			List<CustomerRecord> list = repository.findByCustomerIdAndMonthAndYearOrderByDateAscAmountAsc(1, 4, 2016);
			
			list.stream().forEach(System.out::println);
			
			System.out.println(list.size());
			
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");

			lines.close();
			
		} catch (IOException e) {

			e.printStackTrace();
		}

		return (args) -> {

			repository.save(new CustomerRecord());

		};
	}

}