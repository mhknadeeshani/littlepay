package com.littlepay.transport.pricing.util;

import com.littlepay.transport.pricing.exceptions.InvalidDataException;
import com.littlepay.transport.pricing.exceptions.InvalidFileNameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import static com.littlepay.transport.pricing.util.Constant.INVALID_DATA;
import static com.littlepay.transport.pricing.util.Constant.INVALID_FILE;

@Slf4j
@Component
public class CSVWriter {

    public void createCSVFile(List<String> data, String outputFilePath){

        if(CollectionUtils.isEmpty(data)){
            throw new InvalidDataException(INVALID_DATA);
        }

        if(StringUtils.isEmpty(outputFilePath)){
            throw new InvalidFileNameException(INVALID_FILE);
        }

        File csvOutFile = new File(outputFilePath);
        try (PrintWriter pw = new PrintWriter(csvOutFile)) {
            data.forEach(pw::println);
        } catch (Exception e) {
            log.error("Exception occurred ", e);
        }

    }
}
