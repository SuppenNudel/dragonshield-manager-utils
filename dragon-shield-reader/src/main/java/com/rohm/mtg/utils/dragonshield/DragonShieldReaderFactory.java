package com.rohm.mtg.utils.dragonshield;

import java.io.File;

import com.rohm.mtg.utils.dragonshield.collection.CollectionCard;
import com.rohm.mtg.utils.dragonshield.collection.CollectionHeaders;
import com.rohm.mtg.utils.dragonshield.deck.DeckCard;
import com.rohm.mtg.utils.dragonshield.deck.DeckHeaders;

public class DragonShieldReaderFactory {

	public static CollectionReader collection(File file) {
		return new CollectionReader(file);
	}

	public static DeckReader deck(File file) {
		return new DeckReader(file);
	}

	public static class CollectionReader extends CsvReader<CollectionCard> {
		protected CollectionReader(File file) {
			super(file, CollectionCard.class, CollectionHeaders.HEADERS);
		}
	}

	public static class DeckReader extends CsvReader<DeckCard> {
		protected DeckReader(File file) {
			super(file, DeckCard.class, DeckHeaders.HEADERS);
		}
	}

}
