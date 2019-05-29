package converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesConverter("NaturalNumberConverter")
public class NaturalNumberConverter implements Converter {
    private static final String NUMBER_PATTERN = "[1-9][0-9]*";
    private Pattern pattern;

    public NaturalNumberConverter() {
        pattern = Pattern.compile(NUMBER_PATTERN);
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        HtmlInputText inputText = null;
        try {
            inputText = (HtmlInputText) uiComponent;
        } catch (IncompatibleClassChangeError error) { }

        Matcher matcher = pattern.matcher(value.toString());
        if (!matcher.matches()) {
            if(inputText != null)
                inputText.setStyle("border: 4px solid indianred");
            FacesMessage msg = new FacesMessage("Не является натуральным числом",
                    "Введите положительное целое число");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(msg);
        } else {
            if(inputText != null)
                inputText.setStyle("border: 4px solid white");
        }
        return Integer.parseInt(value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return value.toString();
    }
}
