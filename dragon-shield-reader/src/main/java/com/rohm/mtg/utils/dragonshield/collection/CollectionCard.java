package com.rohm.mtg.utils.dragonshield.collection;

import java.time.LocalDate;

import org.apache.commons.csv.CSVRecord;

import com.rohm.mtg.utils.dragonshield.CsvEntity;

public class CollectionCard implements CsvEntity {

	private String folderName;
	private Integer quantity;
	private Integer tradeQuantity;
	private String cardName;
	private String setCode;
	private String setName;
	private String cardNumber;
	private String condition;
	private String printing;
	private String language;
	private Double priceBought;
	private LocalDate dateBought;
	private Double avg;
	private Double low;
	private Double trend;

	@Override
	public String toString() {
		return String.format("%sx %s/%s in %s", quantity, cardName, setName, folderName);
	}

	public Integer getQuantity() {
		return quantity;
	}
	public String getCardName() {
		return cardName;
	}
	public Double getAvg() {
		return avg;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public String getCondition() {
		return condition;
	}
	public LocalDate getDateBought() {
		return dateBought;
	}
	public String getFolderName() {
		return folderName;
	}
	public String getLanguage() {
		return language;
	}
	public Double getLow() {
		return low;
	}
	public Double getPriceBought() {
		return priceBought;
	}
	public String getPrinting() {
		return printing;
	}
	public String getSetCode() {
		return setCode;
	}
	public String getSetName() {
		return setName;
	}
	public Integer getTradeQuantity() {
		return tradeQuantity;
	}
	public Double getTrend() {
		return trend;
	}

	@Override
	public void fillFromCsv(CSVRecord record) {
		folderName = record.get(CollectionHeaders.FOLDER_NAME);
		quantity = Integer.parseInt(record.get(CollectionHeaders.QUANTITY));
		tradeQuantity = Integer.parseInt(record.get(CollectionHeaders.TRADE_QUANTITY));
		cardName = record.get(CollectionHeaders.CARD_NAME);
		setCode = record.get(CollectionHeaders.SET_CODE);
		setName = record.get(CollectionHeaders.SET_NAME);
		cardNumber = record.get(CollectionHeaders.CARD_NUMBER);
		condition = record.get(CollectionHeaders.CONDITION);
		printing = record.get(CollectionHeaders.PRINTING);
		language = record.get(CollectionHeaders.LANGUAGE);
		priceBought = parseDouble(record, CollectionHeaders.PRICE_BOUGHT);
		dateBought = LocalDate.parse(record.get(CollectionHeaders.DATE_BOUGHT));
		avg = parseDouble(record, CollectionHeaders.AVG);
		low = parseDouble(record, CollectionHeaders.LOW);
		trend = parseDouble(record, CollectionHeaders.TREND);
	}

}
