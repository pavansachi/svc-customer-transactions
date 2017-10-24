package org.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.models.domain.CustomerRecord;
import org.utils.AppUtils;

/*
 * enum to define all rules and implement
 */
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

	FAST_SPENDER {
		public boolean apply(List<CustomerRecord> records) {

			boolean satisfies = false;

			for (CustomerRecord r: records) {

				// deposit
				if (r.getAmount() > 0) {

					// find all transactions in this range ( this date and next 7 days)

					//					Calendar cal = Calendar.getInstance();
					//					cal.setTime(r.getDate());
					//					cal.add(Calendar.DAY_OF_YEAR, 7);

					DateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

					DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

					try {
						final Date startDate = targetFormat.parse(originalFormat.format(r.getDate()));

						Calendar cal = Calendar.getInstance();
						cal.setTime(startDate);
						cal.add(Calendar.DAY_OF_YEAR, 7);

						System.out.println(records.size());
						
						double next7DaySum = records.stream().filter(e -> e.getDate().after(startDate) && e.getDate().before(cal.getTime()))
								.mapToDouble(e -> e.getAmount()).sum();

						System.out.println(next7DaySum);
						
						if ( ( ((double)next7DaySum / (double)r.getAmount()) * 100 ) > 75) {

							satisfies = true;
						}

					} catch (ParseException e1) {
						e1.printStackTrace();
					}

				}
			}

			return satisfies;
		}
	},

	//	BIG_SPENDER,
	//	BIG_TICKET_SPENDER,
	//	FAST_SPENDER,
	//	MORNING_PERSON,
	//	POTENTIAL_SAVER;

}
