package edu.miu.attendance.service;

import edu.miu.attendance.domain.Location;

public interface LocationService {

    Location addLocation(Location location);

    Location findByLocationById(long id);

    void deleteLocation(long id);


}
