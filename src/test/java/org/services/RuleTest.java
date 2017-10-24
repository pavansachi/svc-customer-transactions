package org.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.models.domain.CustomerRecord;
import org.service.CustomerRuleEnum;

public class RuleTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testForAfterNoonPersonRule() {
		
		List<CustomerRecord> records = 
				Arrays.asList(
						new CustomerRecord(1, new Date("09/08/2016 1:29:56 AM"), 100, ""),
						new CustomerRecord(1, new Date("10/08/2016 4:29:56 PM"), -10, ""),
						new CustomerRecord(1, new Date("11/08/2016 12:29:56 PM"), -30, "")
						);
		
		boolean result = CustomerRuleEnum.AFTERNOON_PERSON.apply(records);
		
		org.junit.Assert.assertEquals(true, result);
		
	}
	
	@Test
	public void testForNotAfterNoonPersonRule() {
		
		List<CustomerRecord> records = 
				Arrays.asList(
						new CustomerRecord(1, new Date("09/08/2016 1:29:56 AM"), 100, ""),
						new CustomerRecord(1, new Date("10/08/2016 4:29:56 PM"), -10, ""),
						new CustomerRecord(1, new Date("11/08/2016 12:29:56 AM"), -30, "")
						);
		
		boolean result = CustomerRuleEnum.AFTERNOON_PERSON.apply(records);
		
		org.junit.Assert.assertEquals(false, result);
		
	}
	
	@Test
	public void testForMorningPersonRule() {
		
		List<CustomerRecord> records = 
				Arrays.asList(
						new CustomerRecord(1, new Date("09/08/2016 1:29:56 AM"), 100, ""),
						new CustomerRecord(1, new Date("10/08/2016 4:29:56 AM"), -10, ""),
						new CustomerRecord(1, new Date("12/08/2016 8:29:56 AM"), -30, ""),
						new CustomerRecord(1, new Date("13/08/2016 12:29:56 PM"), -30, "")
						);
		
		boolean result = CustomerRuleEnum.MORNING_PERSON.apply(records);
		
		org.junit.Assert.assertEquals(true, result);
		
	}
	
	@Test
	public void testForNotMorningPersonRule() {
		
		List<CustomerRecord> records = 
				Arrays.asList(
						new CustomerRecord(1, new Date("09/08/2016 1:29:56 AM"), 100, ""),
						new CustomerRecord(1, new Date("10/08/2016 4:29:56 PM"), -10, ""),
						new CustomerRecord(1, new Date("11/08/2016 12:29:56 PM"), -30, "")
						);
		
		boolean result = CustomerRuleEnum.MORNING_PERSON.apply(records);
		
		org.junit.Assert.assertEquals(false, result);
		
	}
	
	@Test
	public void testForBigSpenderRule() {
		
		List<CustomerRecord> records = 
				Arrays.asList(
						new CustomerRecord(1, new Date("09/08/2016 1:29:56 AM"), 100, ""),
						new CustomerRecord(1, new Date("10/08/2016 4:29:56 AM"), -10, ""),
						new CustomerRecord(1, new Date("12/08/2016 8:29:56 AM"), -70, ""),
						new CustomerRecord(1, new Date("13/08/2016 12:29:56 PM"), -10, "")
						);
		
		boolean result = CustomerRuleEnum.BIG_SPENDER.apply(records);
		
		org.junit.Assert.assertEquals(true, result);
		
	}
	
	@Test
	public void testForNotBigSpenderRule() {
		
		List<CustomerRecord> records = 
				Arrays.asList(
						new CustomerRecord(1, new Date("09/08/2016 1:29:56 AM"), 100, ""),
						new CustomerRecord(1, new Date("10/08/2016 4:29:56 AM"), -10, ""),
						new CustomerRecord(1, new Date("12/08/2016 8:29:56 AM"), -20, ""),
						new CustomerRecord(1, new Date("13/08/2016 12:29:56 PM"), -10, "")
						);
		
		boolean result = CustomerRuleEnum.BIG_SPENDER.apply(records);
		
		org.junit.Assert.assertEquals(false, result);
		
	}
	
	@Test
	public void testForBigTicketSpenderRule() {
		
		List<CustomerRecord> records = 
				Arrays.asList(
						new CustomerRecord(1, new Date("09/08/2016 1:29:56 AM"), 10000, ""),
						new CustomerRecord(1, new Date("10/08/2016 4:29:56 AM"), -1230, ""),
						new CustomerRecord(1, new Date("12/08/2016 8:29:56 AM"), -70, ""),
						new CustomerRecord(1, new Date("13/08/2016 12:29:56 PM"), -1110, "")
						);
		
		boolean result = CustomerRuleEnum.BIG_TICKET_SPENDER.apply(records);
		
		org.junit.Assert.assertEquals(true, result);
		
	}
	
	@Test
	public void testForNotBigTicketSpenderRule() {
		
		List<CustomerRecord> records = 
				Arrays.asList(
						new CustomerRecord(1, new Date("09/08/2016 1:29:56 AM"), 10000, ""),
						new CustomerRecord(1, new Date("10/08/2016 4:29:56 AM"), -230, ""),
						new CustomerRecord(1, new Date("12/08/2016 8:29:56 AM"), -70, ""),
						new CustomerRecord(1, new Date("13/08/2016 12:29:56 PM"), -110, "")
						);
		
		boolean result = CustomerRuleEnum.BIG_TICKET_SPENDER.apply(records);
		
		org.junit.Assert.assertEquals(false, result);
		
	}
	
	@Test
	public void testForPotentialSaveRule() {
		
		List<CustomerRecord> records = 
				Arrays.asList(
						new CustomerRecord(1, new Date("09/08/2016 1:29:56 AM"), 100, ""),
						new CustomerRecord(1, new Date("10/08/2016 4:29:56 AM"), -10, ""),
						new CustomerRecord(1, new Date("12/08/2016 8:29:56 AM"), 100, ""),
						new CustomerRecord(1, new Date("13/08/2016 12:29:56 PM"), -30, "")
						);
		
		boolean result = CustomerRuleEnum.POTENTIAL_SAVER.apply(records);
		
		org.junit.Assert.assertEquals(true, result);
		
	}
	
public void testForPotentialSaveRuleWithLessThan25pc() {
		
		List<CustomerRecord> records = 
				Arrays.asList(
						new CustomerRecord(1, new Date("09/08/2016 1:29:56 AM"), 100, ""),
						new CustomerRecord(1, new Date("10/08/2016 4:29:56 AM"), -19.5, ""),
						new CustomerRecord(1, new Date("12/08/2016 8:29:56 AM"), 100, ""),
						new CustomerRecord(1, new Date("13/08/2016 12:29:56 PM"), -30, "")
						);
		
		boolean result = CustomerRuleEnum.POTENTIAL_SAVER.apply(records);
		
		org.junit.Assert.assertEquals(true, result);
		
	}
	
	@Test
	public void testForNotPotentialSaveRule() {
		
		List<CustomerRecord> records = 
				Arrays.asList(
						new CustomerRecord(1, new Date("09/08/2016 1:29:56 AM"), 100, ""),
						new CustomerRecord(1, new Date("10/08/2016 4:29:56 AM"), -40, ""),
						new CustomerRecord(1, new Date("12/08/2016 8:29:56 AM"), 100, ""),
						new CustomerRecord(1, new Date("13/08/2016 12:29:56 PM"), -10, "")
						);
		
		boolean result = CustomerRuleEnum.POTENTIAL_SAVER.apply(records);
		
		org.junit.Assert.assertEquals(false, result);
		
	}
	
	@Test
	public void testForFastSpenderRule() {
		
		List<CustomerRecord> records = 
				Arrays.asList(
						new CustomerRecord(1, new Date("09/08/2016 1:29:56 AM"), 100, ""),
						new CustomerRecord(1, new Date("10/08/2016 4:29:56 AM"), -20, ""),
						new CustomerRecord(1, new Date("12/08/2016 8:29:56 AM"), -1, ""),
						new CustomerRecord(1, new Date("13/08/2016 8:29:56 AM"), -10, ""),
						new CustomerRecord(1, new Date("16/08/2016 12:29:56 PM"), -10, "")
						);
		
		boolean result = CustomerRuleEnum.FAST_SPENDER.apply(records);
		
		org.junit.Assert.assertEquals(false, result);
		
	}

}
