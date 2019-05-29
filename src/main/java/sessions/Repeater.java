package sessions;

import javax.ejb.Remote;

@Remote
public interface Repeater {
    String repeat(String sourceString, int repeatCount);
}
