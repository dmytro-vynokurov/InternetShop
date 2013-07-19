package dto.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Dmitry
 * Date: 7/19/13
 * Time: 8:06 AM
 */
public class EmailValidator implements Validator {
    private static final String EMAIL_PATTERN=".+@.+";
    private static final String MESSAGE_SUMMARY = "E-mail validation failed.";
    private static final String MESSAGE_DETAILS = "Invalid E-mail format.";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (o == null) return;
        String field = (String) o;

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(field);

        if(!matcher.matches()){
            FacesMessage msg = new FacesMessage(MESSAGE_SUMMARY, MESSAGE_DETAILS);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
