package model;

import java.util.Objects;

/**
 * This class represents a Car object.
 *
 * @author Avihu Tubi
 * @version 12/11/2022
 */
public class Car
{
    private int _id;
    private char _type;
    private String _brand;
    private boolean _isManual;
    private final int MAX_ID = 9999999;
    private final int MIN_ID = 1000000;
    private final int DEFAULT_ID = 9999999;
    private final char DEFAULT_TYPE = 'A';

    /**
     * Creates a new Car object
     * id should be a 7 digits number, otherwise set it to 9999999
     * type should be 'A','B','C' or 'D', otherwise set it to 'A'
     * @param id - the id of the car (7 digits number)
     * @param type - the type of the car ('A','B','C' or 'D')
     * @param brand - the car's brand
     * @param isManual - flag indicating if the car is manual
     */
    public Car (int id, char type, String brand, boolean isManual)
    {
        if (id >= MIN_ID && id <= MAX_ID)
            _id = id;
        else
            _id = DEFAULT_ID;
        if (type == 'A' || type == 'B' || type == 'C' || type == 'D')
            _type = type;
        else
            _type = DEFAULT_TYPE;

        _brand = brand;
        _isManual = isManual;
    }

    /**
     * Copy constructor
     * @param other - the car to be copied
     */
    public Car(Car other)
    {
        _id = other._id;
        _type = other._type;
        _brand = other._brand;
        _isManual = other._isManual;
    }

    /**
     * returns the id of the car.
     * @return The id of the car.
     */
    public int getId()
    {
        return _id;
    }

    /**
     * returns the type of the car.
     * @return The type of the car.
     */
    public char getType()
    {
        return _type;
    }

    /**
     * returns the brand of the car.
     * @return The brand of the car.
     */
    public String getBrand()
    {
        return _brand;
    }

    /**
     * returns the manual of the car.
     * @return The manual of the car.
     */
    public boolean getIsManual()
    {
        return _isManual;
    }

    /**
     * Sets the id (only if the given id is valid)
     * @param id - the id value to be set
     */
    public void setId(int id)
    {
        if (id >= MIN_ID && id <= MAX_ID)
            _id = id;
    }

    /**
     * Sets the type (only if the given type is valid)
     * @param type - the type value to be set
     */
    public void setType (char type)
    {
        if (type == 'A' || type == 'B' || type == 'C' || type == 'D')
            _type = type;
    }

    /**
     * Sets the brand of the car
     * @param brand - the brand value to be set
     */
    public void setBrand(String brand)
    {
        _brand = brand;
    }

    /**
     * Sets the isManual flag of the car
     * @param isManual - the isManual flag to be set
     */
    public void setIsManual(boolean isManual)
    {
        _isManual = isManual;
    }

    /**
     * Returns a String object that represents this car
     * @return String that represents this car in the following format:id:1234567 type:B brand:Toyota gear:manual (or auto)
     */
    public String toString()
    {
        String st = "";
        if (_isManual)
            st = st + "manual";
        else
            st = st + "auto";
        return "id:" + _id + " type:" + _type + " brand:" + _brand + " gear:" + st;
    }

    /**
     * Check if two cars are the same
     * Cars are considered the same if they have the same type, brand and gear
     * @param other - the car to compare this car to
     * @return true if the cars are the same, otherwise false
     */
    public boolean equals (Car other)
    {
        return _type == other._type && Objects.equals(_brand, other._brand) && _isManual == other._isManual;
    }

    /**
     * Check if this car is better than the other car
     * A car is considered better than another car if its type is higher.
     * If both cars have the same type, an automated car is better than a manual car.
     * @param other - car to compare this car to
     * @return true if this car is better than the other car, otherwise false
     */
    public boolean better (Car other)
    {
        if (other._type <_type)
            return true;
        if (_type == other._type)
        {
            return !_isManual;
        }
        return false;
    }


    /**
     * Check if this car is worse than the other car
     * @param other - car to compare this car to
     * @return true if this car is worse than the other car, otherwise false
     */
    public boolean worse (Car other)
    {
        return other.better(this);
    }
}
