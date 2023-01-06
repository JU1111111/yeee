package com.yeet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonloaderTest {

	private String simpleTestcaseJson = "{	\"title\": \"Yeet\"	}";
	private DynArray testVokDynArr = new DynArray();


	@Test
	void makeDynArr(){
		testVokDynArr.append(new VokabelWort("Hallo", "Hello"));
		testVokDynArr.append(new VokabelWort("Bohne", "Bean"));
		testVokDynArr.append(new VokabelWort("yes", "yee"));
	}


	@Test
	void testParse() throws IOException {
		JsonNode node = Jsonloader.parse(simpleTestcaseJson);
		assertEquals(node.get("title").asText(), "Yeet");
	}
	@Test
	void testFromJson() throws IOException {
		JsonNode node = Jsonloader.parse(simpleTestcaseJson);
		simpleTestObj obj = Jsonloader.fromJson(node, simpleTestObj.class);

		System.out.println(" TestOBJ title: " + obj.getTitle());
		assertEquals(obj.getTitle(), "Yeet");
	}

	

}
