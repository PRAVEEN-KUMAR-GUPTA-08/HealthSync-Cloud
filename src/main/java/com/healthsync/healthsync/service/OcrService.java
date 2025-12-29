package com.healthsync.healthsync.service;

import java.io.File;

import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class OcrService {

    public String extractText(File file) throws TesseractException {
        ITesseract tesseract = new Tesseract();

        // Update this path if Tesseract is installed elsewhere
        tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");

        return tesseract.doOCR(file);
    }
}
