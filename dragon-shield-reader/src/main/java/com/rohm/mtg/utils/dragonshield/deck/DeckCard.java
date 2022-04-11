package com.rohm.mtg.utils.dragonshield.deck;

import org.apache.commons.csv.CSVRecord;

import com.rohm.mtg.utils.dragonshield.CsvEntity;

public class DeckCard implements CsvEntity {

	private CardType cardType;
	private Integer quantity;
	private String cardName;
	private String manaCost;
	private Double minPriceTrend;

	public Integer getQuantity() {
		return quantity;
	}
	public String getCardName() {
		return cardName;
	}
	public CardType getCardType() {
		return cardType;
	}
	public String getManaCost() {
		return manaCost;
	}
	public Double getMinPriceTrend() {
		return minPriceTrend;
	}

	@Override
	public void fillFromCsv(CSVRecord record) {
		cardType = CardType.valueOf(record.get(DeckHeaders.CARD_TYPE));
		quantity = Integer.parseInt(record.get(DeckHeaders.QUANTITY));
		cardName = record.get(DeckHeaders.CARD_NAME);
		manaCost = record.get(DeckHeaders.MANA_COST);
		minPriceTrend = parseDouble(record, DeckHeaders.MIN_PRICE_TREND);
	}

}
