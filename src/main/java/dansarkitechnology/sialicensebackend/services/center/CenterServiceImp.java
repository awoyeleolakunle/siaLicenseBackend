package dansarkitechnology.sialicensebackend.services.center;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.Utils.GenerateApiResponse;
import dansarkitechnology.sialicensebackend.data.models.Center;
import dansarkitechnology.sialicensebackend.data.models.TrainingSession;
import dansarkitechnology.sialicensebackend.data.repositories.CenterRepository;
import dansarkitechnology.sialicensebackend.dtos.request.CenterRequest;
import dansarkitechnology.sialicensebackend.exceptions.CenterException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor

public class CenterServiceImp implements CenterService{

    private final CenterRepository centerRepository;

    @Override
    public Center saveCenter(Center center) {
        return centerRepository.save(center) ;
    }

    @Override
    public Center findCenterByEmailAddress(String emailAddress) {
        return centerRepository.findByUser_EmailAddress(emailAddress);
    }

    @Override
    public Optional<Center> findCenterById(Long centerId) {
        return centerRepository.findById(centerId);
    }

    @Override
    public ApiResponse findACenterAllTrainingSessions(String emailAddress) throws CenterException {
        Center center = findCenterByEmailAddress(emailAddress);
        if(center==null) throw new CenterException(GenerateApiResponse.CENTER_NOT_FOUND);
        return GenerateApiResponse.found(center.getSetOfTrainingSession());
    }

}
