package common.exceptions;

/**
 * Created by Павел on 28.02.2017.
 */
public class MyException extends Exception {
    private String mess;

    public MyException(String mess) {
        this.mess = mess;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
