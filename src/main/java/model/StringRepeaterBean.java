package model;

import sessions.DatabaseHelper;
import sessions.Repeater;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;

@ManagedBean(name = "stringRepeaterBean")
@SessionScoped
public class StringRepeaterBean {
    private StringRepeater stringRepeater = new StringRepeater();

    public static final int ERROR_MESSAGE = 1;
    public static final int LINK_MESSAGE = 2;
    public static final int REFRESH_MESSAGE = 3;

    public String databaseInfo = "";

    @EJB
    private Repeater repeaterBean;
    @EJB
    private DatabaseHelper dbHelper;

    public String getMessage(int MESSAGE_TYPE) {
        if(stringRepeater.getSourceString() == null || stringRepeater.getSourceString().isEmpty()) {
            switch (MESSAGE_TYPE) {
                case ERROR_MESSAGE : {
                    return "Извините произошла ошибка";
                }
                case LINK_MESSAGE : {
                    return "Вернуться назал";
                }
                case REFRESH_MESSAGE : {
                    return "5;url=index.xhtml";
                }
                default: return "";
            }
        }
        return "";
    }

    public String getSourceString() { return stringRepeater.getSourceString(); }

    public void setSourceString(String sourceString) { stringRepeater.setSourceString(sourceString); }

    public String getRepeatedString() {
        return stringRepeater.getRepeatedString();
    }

    public void setRepeatedString(String repeatedString) { this.stringRepeater.setRepeatedString(repeatedString); }

    public void setRepeatCount(int repeatCount) { stringRepeater.setRepeatCount(repeatCount); }

    public int getRepeatCount() { return stringRepeater.getRepeatCount(); }

    public String repeatString() {
        if(getSourceString() != null && !getSourceString() .equals("") && getRepeatCount() > 0) {
            /*StringRepeater tmp = dbHelper.findData(getSourceString(), getRepeatCount());
            if(tmp == null) {
                stringRepeater.setRepeatedString(repeaterBean.repeat(stringRepeater.getSourceString(), stringRepeater.getRepeatCount()));
                dbHelper.writeData(stringRepeater);
            } else {
                stringRepeater = tmp;
                databaseInfo = "Результат из базы данных";
            }*/
            stringRepeater.setRepeatedString(repeaterBean.repeat(stringRepeater.getSourceString(), stringRepeater.getRepeatCount()));
            return "result";
        }
        return null;
    }

    public String resetData() {
        stringRepeater = new StringRepeater();
        databaseInfo = "";
        return "index";
    }

    public String getDatabaseInfo() {
        return databaseInfo;
    }

    public String getXmlStringRepeater() {
        try {
            JAXBContext context = JAXBContext.newInstance(StringRepeater.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(stringRepeater, writer);
            return writer.toString();
        } catch (JAXBException exc) {
            exc.printStackTrace();
            return "";
        }
    }

    public void redirectToXmlDataServlet() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/JSFapp_war_exploded/getXml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
