package edu.miu.attendance.service.implementation;

import edu.miu.attendance.domain.Location;
import edu.miu.attendance.repository.LocationRepository;
import edu.miu.attendance.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationDAO;



    @Override
    public Location addLocation(Location location) {
        return locationDAO.save(location);
    }

    @Override
    public Location findByLocationById(long id) {
        return locationDAO.findById(id).get();
    }

    @Override
    public void deleteLocation(long id) {
        locationDAO.deleteById(id);
    }
}
