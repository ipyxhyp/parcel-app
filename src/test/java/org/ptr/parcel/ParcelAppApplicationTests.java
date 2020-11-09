package org.ptr.parcel;

import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.ptr.parcel.service.ParcelContent;
import org.ptr.parcel.service.ParcelReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParcelAppApplicationTests {

	private Logger LOG  = LoggerFactory.getLogger(ParcelAppApplicationTests.class);

	@Autowired
	private ParcelReader parcelReader;

	@Autowired
	private ParcelContent parcelContent;


	@Test
	public void testPattern() {

		String regexPattern = "^\\d{1,3}\\.\\d{1,3}\\s\\d{5}$";
		Pattern pattern = Pattern.compile(regexPattern);

		String weightAndPostalCode1 = "1.09 12345";
		String weightAndPostalCode2 = "22.5 12345";
		String weightAndPostalCode3 = "11.4 12345";
		String weightAndPostalCode4 = "22.4 12345";

		assertNotNull("pattern not null ", pattern);
		Boolean result = pattern.matcher(weightAndPostalCode1).matches();
		LOG.info(" result matches : {} ", result);
		result = pattern.matcher(weightAndPostalCode2).matches();
		LOG.info(" result matches : {} ", result);
		result = pattern.matcher(weightAndPostalCode3).matches();
		LOG.info(" result matches : {} ", result);
		result = pattern.matcher(weightAndPostalCode4).matches();
		LOG.info(" result matches : {} ", result);
		assertTrue(" result is matching ", result );

	}

	@Test
	public void testParcelReader() {
		// TODO finish retest
		assertNotNull("parcel reader should be not null ", parcelReader);
		assertNotNull("parcel content should be not null ", parcelContent);

	}

}
