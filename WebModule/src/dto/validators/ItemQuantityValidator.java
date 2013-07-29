package dto.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * User: Dmitry
 * Date: 7/28/13
 * Time: 7:50 PM
 */
@FacesValidator(value="ItemQuantityValidator")
public class ItemQuantityValidator implements Validator {
    private static final String NEGATIVE_QUANTITY_MESSAGE = "Item quantity cannot be negative";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Number number=(Number)o;
        int quantity=number.intValue();

        if(quantity<0){
            FacesMessage message = new FacesMessage(NEGATIVE_QUANTITY_MESSAGE);
            throw new ValidatorException(message);
        }
    }
}
