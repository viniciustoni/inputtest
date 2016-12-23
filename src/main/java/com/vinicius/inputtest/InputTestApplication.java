package com.vinicius.inputtest;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vinicius.inputtest.exception.ProcessFileException;
import com.vinicius.inputtest.services.ProcessFileService;

/**
 * Start application
 * 
 * @author Vinicius Antonio Gai
 *
 */
public class InputTestApplication {

    private static final Logger logger = LoggerFactory.getLogger(InputTestApplication.class);

    public static void main(final String[] args) {
        logger.info("Starting application");

        try {

            String fileName = null;
            if(args != null && args.length > 0) {
                fileName = args[0];
            }
            
            logger.info(MessageFormat.format("File name {0}.", fileName));
            
            final ProcessFileService processFileService = new ProcessFileService();
            final String resultContent = processFileService.processFile(fileName);
            
            logger.info(resultContent);
        } catch (ProcessFileException e) {
            logger.error("Error to read file", e);
        }
    }
}
