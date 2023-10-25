package com.app.shopping.ecommerce.service.impl;

import com.app.shopping.ecommerce.entity.PasswordResetToken;
import com.app.shopping.ecommerce.entity.User;
import com.app.shopping.ecommerce.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    public void createPasswordResetTokenForUser(User user,String passwordToken)
    {
        PasswordResetToken passwordResetToken=new PasswordResetToken(passwordToken, user);
        passwordResetTokenRepository.save(passwordResetToken);
    }
 public String validatePasswordResetToken(String token)
 {
     PasswordResetToken passwordResetToken=passwordResetTokenRepository.findByToken(token);
     if(passwordResetToken==null)
     {
         return "Invalid verification Token";
     }
     User user=passwordResetToken.getUser();
     Calendar calendar=Calendar.getInstance();
     if ((passwordResetToken.getExpirationTime().getTime()-calendar.getTime().getTime())<=0)
     {
         return "Verification Link Already  Expired, resend Verification link";
     }
     return "Valid";
 }
 public Optional<User> findUserByPasswordToken(String token)
 {
     return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
 }
}
