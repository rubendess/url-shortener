package com.rubendess.urlshortener;

import com.rubendess.urlshortener.dto.URLDto;
import com.rubendess.urlshortener.services.URLService;
import com.rubendess.urlshortener.util.ShortenerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlshortenerApplicationTests {

	@Autowired
	ShortenerUtil shortenerUtil;

	@Test
	public void encodeTest() {
		final String hash = shortenerUtil.encode(5000L);
		assertTrue(hash.equals("1iE"));
	}

	@Test
	public void decodeTest() {
		final Long id = shortenerUtil.decode("1iE");
		assertTrue(id.equals(5000L));
	}

}
