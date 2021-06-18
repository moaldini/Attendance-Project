package edu.miu.attendance.domain;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class BarcodeStatusConverter implements AttributeConverter<BarcodeStatus, String> {


    @Override
    public String convertToDatabaseColumn(BarcodeStatus barcodeStatus) {
        if (barcodeStatus == null) {
            return null;
        }
        return barcodeStatus.getStatus();
    }

    @Override
    public BarcodeStatus convertToEntityAttribute(String barcodeStatus) {
        if (barcodeStatus == null) {
            return null;
        }
        return Stream.of(BarcodeStatus.values())
                .filter(c -> c.getStatus().equals(barcodeStatus))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }
}
