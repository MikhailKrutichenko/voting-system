package com.graduation.votingSystem.web;

import com.graduation.votingSystem.model.Vote;
import com.graduation.votingSystem.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    private static final Logger log = LoggerFactory.getLogger(VoteRestController.class);

    @Autowired
    private VoteService service;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/votes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@RequestParam int restId, @RequestParam int userId) {
        log.info("vote for the restaurant id={} from user id={}", restId, userId);
        service.vote(restId, userId);
    }

    @GetMapping("admin/votes")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Vote> getAll() {
        log.info("get all");
        return service.getAll();
    }
}
