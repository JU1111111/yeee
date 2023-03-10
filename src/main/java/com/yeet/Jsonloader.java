package com.yeet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Jsonloader {
	
	private static ObjectMapper objectMapper = getDefaulObjectMapper();

	private static ObjectMapper getDefaulObjectMapper(){
		ObjectMapper defaultObjectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		return(defaultObjectMapper);
	}

	public static JsonNode parse(String src) throws IOException{
		return objectMapper.readTree(src);
	}

	public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException, IllegalArgumentException{
		return objectMapper.treeToValue(node, clazz);
	}

	public static void writeToFile(String filename, ArrayList<VokabelWort> vokabeln) throws StreamWriteException, DatabindException, IOException{
		objectMapper.writeValue(Paths.get(filename).toFile(), vokabeln);
	}

	public static List<VokabelWort>  fromJsonFile(String filePath) throws StreamReadException, DatabindException, IOException {
		return objectMapper.readValue(new File(filePath), new TypeReference<List<VokabelWort>>(){});
	}
}