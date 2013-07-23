package dto.validators;

/**
 * User: Dmitry
 * Date: 7/22/13
 * Time: 12:06 PM
 */

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator(value = "PhoneValidator")
public class PhoneValidator implements Validator {
    private static final String PHONE_PATTERN = "\\d{5,13}";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String phone = (String) o;

        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);

        if (!matcher.matches()) {
            FacesMessage message = new FacesMessage("Phone can contain from 5 to 13 numbers only");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, message);
            throw new ValidatorException(message);
        }
    }
}