package com.learning.ChallengeApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

    @Autowired
    ChallengeRepository challengeRepository;
    //private List<Challenge> challenges = new ArrayList<>();
    private Long  nextId = 1L;

    public ChallengeService(){

    }

    public List<Challenge> getAllChallenges(){
        return challengeRepository.findAll();
    }

    public boolean addChallenge(Challenge challenge){
        if(challenge != null){
            challenge.setId(nextId++);
            challengeRepository.save(challenge);
            return true;
        }
        else {
            return false;
        }
    }

    public Challenge getChallenge(String month){
        Optional<Challenge> challenge=challengeRepository.findByMonthIgnoreCase(month);
        return challenge.orElse(null);
    }

    public boolean updateChallenge(Long id,Challenge updatedChallenge){
        Optional<Challenge> challenge=challengeRepository.findById(id);
        if(challenge.isPresent()){
            Challenge challengeToUpdate = challenge.get();
            challengeToUpdate.setMonth(updatedChallenge.getMonth());
            challengeToUpdate.setDescription(updatedChallenge.getDescription());
            challengeRepository.save(challengeToUpdate);
            return true;
        }
        return false;
    }

    public boolean deleteChallenge(Long id) {
        Optional<Challenge> challenge=challengeRepository.findById(id);
        if(challenge.isPresent()){
            challengeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
