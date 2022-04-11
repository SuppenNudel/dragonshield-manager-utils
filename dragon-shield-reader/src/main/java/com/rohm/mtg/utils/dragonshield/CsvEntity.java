package com.rohm.mtg.utils.dragonshield;

import org.apache.commons.csv.CSVRecord;

public interface CsvEntity {

	void fillFromCsv(CSVRecord record);

	default Double parseDouble(CSVRecord record, String key) {
		String value = record.get(key);
		if(value == null) {
//			System.out.println(String.format("%s is null - %s", key, record));
			return null;
		} else if(value.isEmpty()) {
			System.out.println(String.format("%s is empty - %s", key, record));
			return null;
		} else {
			return Double.parseDouble(value);
		}
	}

	default Integer parseInteger(CSVRecord record, String key) {
		String value = record.get(key);
		if(value == null) {
//			System.out.println(String.format("%s is null - %s", key, record));
			return null;
		} else if(value.isEmpty()) {
			System.out.println(String.format("%s is empty - %s", key, record));
			return null;
		} else {
			return Integer.parseInt(value);
		}
	}

}
