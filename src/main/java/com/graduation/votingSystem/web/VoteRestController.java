package com.graduation.votingSystem.web;

import com.graduation.votingSystem.model.Vote;
import com.graduation.votingSystem.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.VOTE_URI, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    private static final Logger log = LoggerFactory.getLogger(VoteRestController.class);

    public static final String VOTE_URI = "/votes";

    @Autowired
    private VoteRepository repository;

    @GetMapping
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@RequestParam int restId, @RequestParam int userId) {
        log.info("vote for the restaurant id={} from user id={}", restId, userId);
        repository.vote(restId, userId);
    }
}
