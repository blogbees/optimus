package com.screenanalyzer.utils;

import java.io.File;
import java.io.IOException;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OptimusTextSynthesizer {
	public String synthesizeTextFromFile(File imageFile) throws TesseractException, IOException {
		ITesseract instance = new Tesseract(); // JNA Interface Mapping
		instance.setLanguage("english");
		instance.setDatapath("E:/satish.janakiraman/Projects/outofbox/optimus/src/main/resources/data");
		return instance.doOCR(imageFile);
	}
}
