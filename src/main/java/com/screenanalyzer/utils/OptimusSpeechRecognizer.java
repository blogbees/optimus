package com.screenanalyzer.utils;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

public class OptimusSpeechRecognizer {
	public LiveSpeechRecognizer getSpeechRecognizer() throws IOException {
		Configuration configuration = new Configuration();
		// Load model from the jar
		configuration.setAcousticModelPath("resource:/data/sphinx/models/en-in/en-in");
		configuration.setDictionaryPath("resource:/data/sphinx/models/en-in/en_in.dic");
		configuration.setLanguageModelPath("resource:/data/sphinx/models/en-in/en-us.lm.bin");
		LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
		return recognizer;
	}
}