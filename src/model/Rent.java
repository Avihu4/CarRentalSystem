package model;

/**
 * This class represents a Rent object
 *
 * @author Avihu Tubi
 * @version 26/11/2022
 */
public class Rent {
    private String _name;
    private Car _car;
    private Date _pickDate;
    private Date _returnDate;
    private final int PRICE_A_CLASS = 100;
    private final int PRICE_B_CLASS = 150;
    private final int PRICE_C_CLASS = 180;
    private final int PRICE_D_CLASS = 240;
    private final int DAYS_IN_WEEK = 7;
    private final int PRICE_A_CLASS_FOR_WEEK = 630;
    private final int PRICE_B_CLASS_FOR_WEEK = 945;
    private final int PRICE_C_CLASS_FOR_WEEK = 1134;
    private final int PRICE_D_CLASS_FOR_WEEK = 1512;

    /**
     * Creates a new Rent object
     * The return date must be at least one day after the pickup date, otherwise set it to one day after the pickup date.
     *
     * @param name - the client's name
     * @param car  - the rented car
     * @param pick - the pickup date
     * @param ret  - the return date
     */
    public Rent(String name, Car car, Date pick, Date ret) {
        _name = name;
        _car = new Car(car);
        _pickDate = new Date(pick);
        if (ret.after(pick)) {
            _returnDate = new Date(ret);
        } else
            _returnDate = pick.tomorrow();
    }

    /**
     * Copy constructor
     *
     * @param other - the rent to be copied
     */
    public Rent(Rent other) {
        _name = other._name;
        _car = new Car(other._car);
        _pickDate = new Date(other._pickDate);
        _returnDate = new Date(other._returnDate);
    }

    /**
     * Gets the car
     *
     * @return the car;
     */
    public Car getCar() {
        return new Car(_car);
    }

    /**
     * Gets the name
     *
     * @return the name;
     */
    public String getName() {
        return _name;
    }

    /**
     * Gets the pickup date
     *
     * @return the pickup date;
     */
    public Date getPickDate() {
        return new Date(_pickDate);
    }

    /**
     * Gets the return date
     *
     * @return the return date;
     */
    public Date getReturnDate() {
        return new Date(_returnDate);
    }

    /**
     * Sets the rented car
     *
     * @param car - the rented car (You can assume that car is not null)
     */
    public void setCar(Car car) {
        _car = new Car(car);
    }

    /**
     * Sets the client name
     *
     * @param name - the client name (You can assume that the name is a valid name)
     */
    public void setName(String name) {
        _name = name;
    }

    /**
     * Sets the pickup date
     * The pickup date must be at least one day before the return date, otherwise - don't change the pickup date
     *
     * @param pickDate - the pickup date (You can assume that pick up date is not null)
     */
    public void setPickDate(Date pickDate) {
        if (pickDate.before(_returnDate)) {
            _pickDate = new Date(_pickDate);
        }
    }

    /**
     * Sets the return date
     * The return date must be at least one day after the pickup date, otherwise - don't change the return date
     *
     * @param returnDate - the return date (You can assume that return date is not null)
     */
    public void setReturnDate(Date returnDate) {
        if (returnDate.after(_pickDate)) {
            _returnDate = new Date(returnDate);
        }
    }

    /**
     * Check if 2 rents are the same
     *
     * @param other - the rent to compare this rent to
     * @return true if the rents are the same;
     */
    public boolean equals(Rent other) {
        return _name.equals(other._name) && _pickDate.equals(other._pickDate) && _returnDate.equals(other._returnDate) && _car.equals(other._car);
    }

    /**
     * Returns the number of rent days
     *
     * @return the number of rent days
     */
    public int howManyDays() {
        return _pickDate.difference(_returnDate);
    }

    /**
     * Returns the rent total price
     *
     * @return the rent total price;
     */
    public int getPrice() {
        if (_car.getType() == 'A') {
            if (howManyDays() < DAYS_IN_WEEK) {
                return howManyDays() * PRICE_A_CLASS;
            }
            return (howManyDays() / DAYS_IN_WEEK * PRICE_A_CLASS_FOR_WEEK) + (howManyDays() % DAYS_IN_WEEK * PRICE_A_CLASS);
        }
        if (_car.getType() == 'B') {
            if (howManyDays() < DAYS_IN_WEEK) {
                return howManyDays() * PRICE_B_CLASS;
            }
            return (howManyDays() / DAYS_IN_WEEK * PRICE_B_CLASS_FOR_WEEK) + (howManyDays() % DAYS_IN_WEEK * PRICE_B_CLASS);
        }
        if (_car.getType() == 'C') {
            if (howManyDays() < DAYS_IN_WEEK) {
                return howManyDays() * PRICE_C_CLASS;
            }
            return (howManyDays() / DAYS_IN_WEEK * PRICE_C_CLASS_FOR_WEEK) + (howManyDays() % DAYS_IN_WEEK * PRICE_C_CLASS);
        }
        if (_car.getType() == 'D') {
            if (howManyDays() < DAYS_IN_WEEK) {
                return howManyDays() * PRICE_D_CLASS;
            }
            return (howManyDays() / DAYS_IN_WEEK * PRICE_D_CLASS_FOR_WEEK) + (howManyDays() % DAYS_IN_WEEK * PRICE_D_CLASS);
        }
        return 0;
    }

    /**
     * Try to upgrade the car to a better car
     * If the given car is better than the current car of the rent, upgrade it and return the upgrade additional cost, otherwise - don't upgrade
     *
     * @param newCar - the car to upgrade to
     * @return the upgrade cost
     */
    public int upgrade(Car newCar) {

        if (newCar.better(_car)) {
            int price = this.getPrice();
            _car = new Car(newCar);
            int newPrice = this.getPrice() - price;
            return newPrice;
        }
        return 0;
    }

    /**
     * Check if there is a double listing of a rent for the same person and car with an overlap in the rental days
     * If there is - return a new rent object with the unified dates, otherwise - return null.
     *
     * @param other - the other rent
     * @return the unified rent or null
     */
    public Rent overlap(Rent other) {

        if (!_name.equals(other._name) || !_car.equals(other._car)) {
            return null;
        }
        if (_pickDate.before(other._pickDate) && other._returnDate.after(_pickDate) && other._returnDate.before(_returnDate)) {
            _pickDate = _pickDate;
            _returnDate = _returnDate;
        }
        if (other._pickDate.before(_pickDate) && _returnDate.after(other._pickDate) && _returnDate.before(other._returnDate)) {
            _pickDate = other._pickDate;
            _returnDate = other._returnDate;
        }
        if (_pickDate.before(other._pickDate) && _returnDate.before(other._pickDate)) {
            return null;
        }
        if (other._pickDate.before(_pickDate) && other._returnDate.before(_pickDate)) {
            return null;
        }
        if (other._pickDate.before(_pickDate) && _pickDate.before(other._returnDate) && _returnDate.after(other._returnDate)) {
            _pickDate = other._pickDate;
            _returnDate = _returnDate;
        }
        if (_pickDate.before(other._pickDate) && other._pickDate.before(_returnDate) && other._returnDate.after(_returnDate)) {
            _pickDate = _pickDate;
            _returnDate = other._returnDate;
        }
        if (other._pickDate.before(_pickDate) && other._returnDate.equals(_pickDate) && _returnDate.after(other._returnDate)) {
            _pickDate = other._pickDate;
            _returnDate = _returnDate;
        }
        if (_pickDate.before(other._pickDate) && _returnDate.equals(other._pickDate) && other._returnDate.after(_returnDate)) {
            _pickDate = _pickDate;
            _returnDate = other._returnDate;
        }
        return new Rent(_name, _car, _pickDate, _returnDate);
    }

    /**
     * Returns a String that represents this rent
     *
     * @return String that represents this rent in the following format:
     * Name:Rama From:30/10/2022 To:12/11/2022 Type:B Days:13 Price:1845
     */
    public String toString() {
        return "Name:" + _name + " From:" + new Date(_pickDate) + " To:" + new Date(_returnDate) + " Type:" + _car.getType() + " Days:" + howManyDays() + " Price:" + getPrice();
    }

}
