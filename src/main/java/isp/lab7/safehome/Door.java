package isp.lab7.safehome;

import jdk.internal.module.DefaultRoots;

public class Door {

    boolean isdoorlocked;
    public DoorStatus doorStatus;

    public Door(DoorStatus doorStatus){

            this.doorStatus=doorStatus;
    }

    public void lockDoor(){

        System.out.println("Door is locked ! ");
        isdoorlocked=true;
    }

    public void unlockDoor(){

        System.out.println("Door is unlocked ! ");
        isdoorlocked=false;
    }

}
