package com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.services;


import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.Session;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.entities.UserEntity;
import com.Suzel.SpringSecurity.SpringAttack.week5.Spring.Attack.week5.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private  final SessionRepository sessionRepository;
    private  final int SESSION_LIMIT = 2;

     public  void genrateNewSession(UserEntity user , String refeshToken){
         List<Session> useSessions = sessionRepository.findByUser(user);

          if(useSessions.size() == SESSION_LIMIT){
              useSessions.sort(Comparator.comparing(Session::getLastUsedAt));

              Session leastReacentlyusedSession = useSessions.getFirst();
              sessionRepository.delete(leastReacentlyusedSession);
          }

          Session newSession = Session.builder().user(user).refreshToken(refeshToken).build();
          sessionRepository.save(newSession);
     }

     public  void validateSession(String refreshToken){
      Session session =  sessionRepository.findByRefreshToken(refreshToken)
              .orElseThrow(() -> new SessionAuthenticationException("Session is not Found for refreshToken " + refreshToken));
      session.setLastUsedAt(LocalDateTime.now());
      sessionRepository.save(session);
     }
}
