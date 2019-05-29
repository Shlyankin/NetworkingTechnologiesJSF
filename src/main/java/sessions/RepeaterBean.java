package sessions;

import javax.ejb.Stateless;

@Stateless
public class RepeaterBean implements Repeater {

    public String repeat(String sourceString, int repeatCount) {
        StringBuilder strBuilder = new StringBuilder();
        for(int i = 0; i < repeatCount; i++)
            strBuilder.append(sourceString);
        return strBuilder.toString();
    }
}
