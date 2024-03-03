package isp.lab7.safehome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DoorLockController implements ControllerInterface {

    public class InvalidPinException extends Exception {
        public InvalidPinException(String message) {
            super(message);
        }
    }
    public class TooManyAttemptsException extends Exception {
        public TooManyAttemptsException(String message) {
            super(message);
        }
    }
    public class TenantAlreadyExistsException extends Exception {
        public TenantAlreadyExistsException(String message) {
            super(message);
        }
    }

    public class TenantNotFoundException extends Exception {
        public TenantNotFoundException(String message) {
            super(message);
        }
    }

    List<AccesLog>AccesLogList=new ArrayList<>();

    DoorStatus doorStatus;
    AccesKey accesKey;

    AccesLog accesLog;

    List<AccesLog> accesLogList;

    Tenant tenant;
    Door door;

    DoorLockController(Door door, AccesKey accesKey,AccesLog accesLog,Tenant tenant){

        this.door=door;
        this.accesKey=accesKey;
        this.accesLog=accesLog;
        this.tenant=tenant;

    }
    private HashMap<String, String> map;

    int consecutiveAttempts=0;


    private void logAccessAttempt(String pin, boolean isSuccess) {
        AccesLog logEntry = new AccesLog(pin, isSuccess);
        AccesLogList.add(logEntry);
    }

    private void resetConsecutiveFailedAttempts() {
        consecutiveAttempts = 0;
    }

    public DoorStatus enterPin(String pin) throws InvalidPinException, TooManyAttemptsException {

        if (!accesKey.getPin().equals(pin)) {
            consecutiveAttempts++;
            if (consecutiveAttempts >= 3) {
                door.lockDoor();
                throw new TooManyAttemptsException("Too many attempts! The door is locked!");
            }
            logAccessAttempt(pin, false);
            throw new InvalidPinException("Invalid pin!");
        }
            else if(accesKey.getPin().equals(MASTER_KEY)){
                resetConsecutiveFailedAttempts();
                return DoorStatus.OPEN;
            }
         else {
            logAccessAttempt(pin, true);
            return DoorStatus.OPEN;
        }
    }

    public void addTenant(String pin, String tenantName) throws TenantAlreadyExistsException{


        if(map.containsKey(tenantName)) throw new TenantAlreadyExistsException("Tenant allready exists !");
        else map.put(pin,tenantName);

    }


    public void removeTenant(String name) throws TenantNotFoundException{

        if(map.containsKey(name)) map.remove(name);
        else throw new TenantNotFoundException("Cannot remove ! Tenant not found !");

    }


    public List<AccesLog> getAccesLogList() {
        return accesLogList;
    }
}
