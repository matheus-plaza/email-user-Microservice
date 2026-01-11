package io.github.matheusplaza.user.template;

import io.github.matheusplaza.user.entity.UserModel;
import org.springframework.stereotype.Component;

@Component
public class EmailTemplateBuilder {

    public String buildWelcomeMessage(UserModel userModel) {
        return String.format("""
                Hello %s,
                
                Welcome to our platform! 🎉
                
                We are happy to have you with us. Your account has been successfully created.
                
                Your access credentials:
                📧 Email: %s
                🔑 Password: %s
                
                For security reasons, we recommend changing your password on your first login.
                
                Enjoy all the available features, and if you need any help, our support team is always available.
                
                Best regards,
                Support Team
                """,
                userModel.getUserName(),
                userModel.getUserMail(),
                userModel.getPassword());
    }

    public String buildWelcomeSubject(UserModel userModel) {
        return "Welcome " + userModel.getUserName();
    }

}

