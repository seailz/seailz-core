package com.seailz.seailzcore.profile.expections;

/**
 * Thrown if there is no profile for the given value
 * @author Seailz
 */
public class NoProfileExistsException extends Exception {

    public NoProfileExistsException(String message) {
        super(message);
    }

}
