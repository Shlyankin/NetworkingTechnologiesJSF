package sessions;

import model.StringRepeater;

import javax.ejb.Remote;

@Remote
public interface DatabaseHelper {
    public void writeData(StringRepeater stringRepeater);

    public StringRepeater findData(String sourceString, int repeatCount);
}
