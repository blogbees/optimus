package com.screenanalyzer.utils;


import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import net.sourceforge.tess4j.TesseractException;

public class OptimusOperation {
	public static void main(String[] args) throws AWTException, IOException, TesseractException {

		OptimusOperation operation = new OptimusOperation();
		OptimusSpeechRecognizer speechRecognizer = new OptimusSpeechRecognizer();
		LiveSpeechRecognizer liveSpeechRecognizer = speechRecognizer.getSpeechRecognizer();
		// Start recognition process pruning previously cached data.
		liveSpeechRecognizer.startRecognition(true);
        /* The VoiceManager manages all the voices for FreeTTS.
         */
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice helloVoice = voiceManager.getVoice(voiceName);

		SpeechResult result;
		File outputImageFile = null;

		while ((result = liveSpeechRecognizer.getResult()) != null) {
			String speechText = result.getHypothesis();
			System.out.format("Hypothesis: %s\n", speechText);
			if (speechText.contains("capture")) {
				
				outputImageFile = new File("E:/desktop_output.png");
				operation.grabScreenShot(outputImageFile);
			} else if (speechText.contains("convert")) {
				operation.extractTextFromImage(outputImageFile);
			} else if (speechText.contains("shutdown")) {
				liveSpeechRecognizer.stopRecognition();
			}
		}
	}

	private void grabScreenShot(File outputImageFile) throws IOException, AWTException {
		OptimusScreenGrabber grabber = new OptimusScreenGrabber();

		System.out.println("***Grabbing Screenshot of the desktop***");
		// STEP 1: capture screen and write to an image file
		BufferedImage image = grabber.grabScreen();
		ImageIO.write(image, "png", outputImageFile);
		System.out.println("***Grabbed Screenshot***");
	}

	private void extractTextFromImage(File outputImageFile) throws TesseractException, IOException {
		OptimusTextSynthesizer textSynthesizer = new OptimusTextSynthesizer();
		System.out.println("***Extracting Text from the Image***");
		// STEP 2: extract text from the image
		String extractedText = textSynthesizer.synthesizeTextFromFile(outputImageFile);
		System.out.println("Extracted Text: " + extractedText);
	}
}
