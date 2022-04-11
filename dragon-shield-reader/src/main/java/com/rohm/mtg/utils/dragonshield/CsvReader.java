package com.rohm.mtg.utils.dragonshield;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * DragonShield CollectionManager
 * <br>
 * CSV Reader
 * @author cedri
 *
 */
public class CsvReader<T extends CsvEntity> {

	private static final Charset UTF16LE = Charset.forName("UTF-16LE");

	private File file;

	private List<T> cards;
	private Class<T> clazz;
	private String[] headers;

	protected CsvReader(File file, Class<T> clazz, String[] headers) {
		this.clazz = clazz;
		this.file = file;
		this.headers = headers;
	}

	public List<T> getCards(boolean forceLoad) throws IOException {
		if(cards == null || forceLoad) {
			cards = load();
		}
		return cards;
	}

	public File getFile() {
		return file;
	}

	protected List<T> load() throws IOException {
		cards = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), UTF16LE))) {
			String sep = reader.readLine(); // skip "sep=,"
			Pattern pattern = Pattern.compile("\"sep=(.)\"");
			Matcher matcher = pattern.matcher(sep);
			String delimiter = null;
			if(matcher.find()) {
				delimiter = matcher.group(1);
			}

			CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
					.setSkipHeaderRecord(true)
					.setNullString("")
					.setDelimiter(delimiter)
					.setHeader(headers)
					.build();

			CSVParser csvParser = csvFormat.parse(reader);
			this.cards.addAll(readCsv(csvParser));
		}
		return cards;
	}

	private List<T> readCsv(CSVParser csvParser) {
		List<T> cards = new ArrayList<>();
		for(CSVRecord csvRecord : csvParser) {
			try {
				T card = clazz.getDeclaredConstructor().newInstance();
				card.fillFromCsv(csvRecord);
				cards.add(card);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				System.out.println(csvRecord);
				e.printStackTrace();
			}
		}
		return cards;
	}

}
