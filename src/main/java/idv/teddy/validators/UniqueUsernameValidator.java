package idv.teddy.validators;

import idv.teddy.constraints.UniqueUsername;
import idv.teddy.repository.UserDetailsRepository;
import lombok.Data;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Data
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserDetailsRepository repository;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return repository.findByUsername(username) == null;
    }
}
