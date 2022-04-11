package com.rohm.mtg.utils.dragonshield;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.rohm.mtg.utils.dragonshield.collection.CollectionCard;
import com.rohm.mtg.utils.dragonshield.collection.CollectionHeaders;

public class CsvReaderTest {

	private static final File singleFolderFile = new File("src/test/resources/White ⚪.csv");
	private static final File allFoldersFile = new File("src/test/resources/all-folders.csv");

	@Test
	public void loadTestFolder() throws IOException {
		assertTrue(singleFolderFile.exists(), "File does not exist");
		CsvReader<CollectionCard> fileHandler = new CsvReader<>(singleFolderFile, CollectionCard.class, CollectionHeaders.HEADERS);
		List<CollectionCard> cards = fileHandler.load();
		assertEquals(1119, cards.stream().map(CollectionCard::getQuantity).collect(Collectors.summingInt(Integer::intValue)));
	}

	@Test
	public void notExist() throws IOException {
		CsvReader<CollectionCard> csvReader = new CsvReader<>(new File("test.csv"), CollectionCard.class, CollectionHeaders.HEADERS);
		assertThrows(FileNotFoundException.class, () ->	csvReader.load());
	}

	@Test
	public void loadAllFolders() throws IOException {
		assertTrue(allFoldersFile.exists(), "File does not exist");
		CsvReader<CollectionCard> fileHandler = new CsvReader<>(allFoldersFile, CollectionCard.class, CollectionHeaders.HEADERS);
		List<CollectionCard> cards = fileHandler.load();
		Integer totalCards = cards.stream().map(CollectionCard::getQuantity).collect(Collectors.summingInt(Integer::intValue));
		assertEquals(30941, totalCards);
	}

}
