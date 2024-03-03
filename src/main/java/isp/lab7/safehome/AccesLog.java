package isp.lab7.safehome;

import java.time.LocalDateTime;

public class AccesLog {

    AccesKey accesKey;

    private boolean isSucces;
    private String pin;
    private String tenantName;
    private LocalDateTime dateTime;
    private String operation;
    private DoorStatus doorStatus;
    private String errorMessage;
    AccesLog(String pin, boolean isSucces){

        this.pin=pin;
        this.isSucces=isSucces;
    }


}
