package model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;

@Entity
@Table(name = "STRING_REPEATER")
@XmlRootElement(name = "repeater")
@XmlAccessorType(XmlAccessType.NONE)
public class StringRepeater {
    @Id @Column(name = "ID")
    private int id = -1;
    @Column(name = "SOURCE_STRING")
    @XmlElement(name = "sourceString")
    private String sourceString;
    @Column(name = "REPEAT_COUNT")
    @XmlElement(name = "repeatCount")
    private int repeatCount;
    @Column(name = "RESULT_STRING")
    @XmlElement(name = "resultString")
    private String repeatedString = null;

    public void setSourceString(String sourceString) {
        this.sourceString = sourceString;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public void setRepeatedString(String repeatedString) {
        this.repeatedString = repeatedString;
    }

    public String getSourceString() {
        return sourceString;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public String getRepeatedString() {
        return repeatedString;
    }

    public StringRepeater() {
        sourceString = "";
        repeatCount = 1;
    }

    public StringRepeater(String sourceString, int repeatCount) {
        this.sourceString = sourceString;
        this.repeatCount = repeatCount;
    }

    public StringRepeater(int id, int repeatCount, String sourceString, String repeatedString) {
        this.id = id;
        this.repeatedString = repeatedString;
        this.sourceString = sourceString;
        this.repeatCount = repeatCount;
    }
}
