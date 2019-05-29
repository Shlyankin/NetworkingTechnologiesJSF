package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("CustomStringConverter")
public class CustomStringConverter implements Converter {


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        HtmlInputTextarea inputText = null;
        try {
            inputText = (HtmlInputTextarea) uiComponent;
        } catch (IncompatibleClassChangeError error) { }

        if(value.isEmpty()) {
            if(inputText != null)
                inputText.setStyle("border: 4px solid indianred");
            FacesMessage msg = new FacesMessage("Пустое поле",
                    "Заполните поле");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(msg);
        } else {
            if(inputText != null)
                inputText.setStyle("border: 4px solid white");
        }
        return value;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return value != null ? value.toString() : "";
    }
}
