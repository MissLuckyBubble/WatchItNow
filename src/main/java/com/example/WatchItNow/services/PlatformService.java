package com.example.WatchItNow.services;

import com.example.WatchItNow.models.Platform;
import com.example.WatchItNow.repo.PlatformRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PlatformService extends BaseService<Platform>{
    @Autowired
    PlatformRepo platformRepo;

    @Override
    protected JpaRepository<Platform, Long> getRepo() {
        return platformRepo;
    }

}