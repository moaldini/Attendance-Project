package edu.miu.attendance.model;

import edu.miu.attendance.domain.BarcodeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarcodeStatusRequest {

    BarcodeStatus status;
}
