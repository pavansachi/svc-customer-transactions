package org.service;

import java.util.ArrayList;
import java.util.List;

import org.models.domain.CustomerRecord;

public class CustomerRules {

	public static List<String> applyRules (List<CustomerRecord> records) {

		List<String> classifications = new ArrayList<>();

		for (CustomerRuleEnum e: CustomerRuleEnum.values()) {

			boolean status = false;

			switch (e) {

			case AFTERNOON_PERSON: status = e.apply(records);
			if (status) classifications.add("AFTERNOON_PERSON");
			break;

			case MORNING_PERSON: status = e.apply(records);
			if (status) classifications.add("MORNING_PERSON");
			break;

			case BIG_SPENDER: status = e.apply(records);
			if (status) classifications.add("BIG_SPENDER");
			break;
			
			case BIG_TICKET_SPENDER: status = e.apply(records);
			if (status) classifications.add("BIG_TICKET_SPENDER");
			break;
			
			case POTENTIAL_SAVER: status = e.apply(records);
			if (status) classifications.add("POTENTIAL_SAVER");
			break;
			
			default: break;

			}

		}

		return classifications;
	}

}
