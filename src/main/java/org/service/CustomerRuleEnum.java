package org.service;

import java.util.List;

import org.models.domain.CustomerRecord;
import org.utils.AppUtils;

public enum CustomerRuleEnum implements ICustomerRule {

	AFTERNOON_PERSON {
		public boolean apply(List<CustomerRecord> records) {

			long tCount = records.stream().filter(r -> AppUtils.isAfterMidday(r.getDate()))
					.count();

			System.out.println(((double)tCount / (double)records.size()) * 100);

			if ((((double)tCount / (double)records.size()) * 100) > 50) {
				return true;
			}

			return false;
		}
	},

	MORNING_PERSON {
		public boolean apply(List<CustomerRecord> records) {

			long tCount = records.stream().filter(r -> AppUtils.isBeforeMidday(r.getDate()))
					.count();

			if ((((double)tCount / (double)records.size()) * 100) > 50) {
				return true;
			}

			return false;
		}
	},
	
	BIG_SPENDER {
		public boolean apply(List<CustomerRecord> records) {

			double deposits = records.stream().filter(e -> e.getAmount() > 0)
			.mapToDouble(e -> e.getAmount()).sum();
			
			double withdrawals = -records.stream().filter(e -> e.getAmount() < 0)
					.mapToDouble(e -> e.getAmount()).sum();

			if ((((double)withdrawals / (double)deposits) * 100) > 80) {
				return true;
			}
			
			return false;
		}
	},
	
	BIG_TICKET_SPENDER {
		public boolean apply(List<CustomerRecord> records) {

			return records.stream()
			.anyMatch(e -> e.getAmount() <= -1000);
			
		}
	},

	POTENTIAL_SAVER {
		public boolean apply(List<CustomerRecord> records) {

			double deposits = records.stream().filter(e -> e.getAmount() > 0)
			.mapToDouble(e -> e.getAmount()).sum();
			
			double withdrawals = -records.stream().filter(e -> e.getAmount() < 0)
					.mapToDouble(e -> e.getAmount()).sum();

			if ((((double)withdrawals / (double)deposits) * 100) < 25) {
				return true;
			}
			
			return false;
		}
	},

	//	BIG_SPENDER,
	//	BIG_TICKET_SPENDER,
	//	FAST_SPENDER,
	//	MORNING_PERSON,
	//	POTENTIAL_SAVER;

}
