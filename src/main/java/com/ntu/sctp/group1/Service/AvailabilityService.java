package com.ntu.sctp.group1.Service;

import com.ntu.sctp.group1.Exceptions.NoVolunteerFoundExceptions;
import com.ntu.sctp.group1.entity.Availability;
import com.ntu.sctp.group1.entity.Volunteer;
import com.ntu.sctp.group1.repository.AvailabilityRepository;
import com.ntu.sctp.group1.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepo;

    @Autowired
    VolunteerRepository volunteerRepository;

    // create availability of a volunteer
    public Availability setAvailability(Integer volunteerId, String date) throws NoVolunteerFoundExceptions {
        Optional<Volunteer> findVolunteer = volunteerRepository.findById(volunteerId);
        if(findVolunteer.isEmpty()) {
            throw new NoVolunteerFoundExceptions("No volunteer id founds");
        }
        Availability availDate = new Availability();
        availDate.setAvail(true);
        availDate.setVolunteer(findVolunteer.get());
        availDate.setDate(LocalDate.parse(date));

        return availabilityRepo.save(availDate);
    }

}