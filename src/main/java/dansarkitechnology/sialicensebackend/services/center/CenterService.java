package dansarkitechnology.sialicensebackend.services.center;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.data.models.Center;
import dansarkitechnology.sialicensebackend.data.models.TrainingSession;
import dansarkitechnology.sialicensebackend.exceptions.CenterException;

import java.util.Optional;
import java.util.Set;

public interface CenterService {

        Center saveCenter(Center center);

        Center findCenterByEmailAddress(String emailAddress);

        Optional<Center> findCenterById(Long centerId);

        ApiResponse findACenterAllTrainingSessions(String emailAddress) throws CenterException;



}
