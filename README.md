# Car Rental System 🚗

A comprehensive Java-based car rental management system that handles car inventory, date management, and rental operations with robust validation and business logic.

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Class Structure](#class-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)
- [API Documentation](#api-documentation)
- [Business Rules](#business-rules)
- [Contributing](#contributing)
- [License](#license)

## 🔍 Overview

This Car Rental System is designed to manage car rentals with three main components:
- **Car Management**: Handle car inventory with different types and specifications
- **Date Management**: Robust date handling with validation and calculations
- **Rental Management**: Complete rental operations including pricing, upgrades, and overlap detection

## ✨ Features

### Car Management
- ✅ Car categorization (Types A, B, C, D)
- ✅ Manual/Automatic transmission support
- ✅ Brand management
- ✅ Unique 7-digit ID system
- ✅ Car comparison and ranking

### Date Management
- ✅ Comprehensive date validation
- ✅ Leap year calculations
- ✅ Date arithmetic operations
- ✅ Tomorrow calculation with month/year rollover

### Rental Operations
- ✅ Dynamic pricing based on car type
- ✅ Weekly discount calculations
- ✅ Car upgrade functionality
- ✅ Rental overlap detection and merging
- ✅ Rental validation and date constraints

## 🏗️ Class Structure

```
model/
├── Car.java        # Car entity with validation and comparison
├── Date.java       # Date utilities with comprehensive validation
└── Rent.java       # Rental management with pricing logic

test/
└── CarRentalSystemTest.java  # Comprehensive test suite
```

### Car Class
- **ID Validation**: 7-digit numbers (1,000,000 - 9,999,999)
- **Type System**: A, B, C, D (with D being premium)
- **Comparison Logic**: Type-based with transmission preference

### Date Class
- **Range**: Years 1000-9999
- **Validation**: Month-specific day limits, leap year support
- **Operations**: Before/after comparison, difference calculation

### Rent Class
- **Pricing Tiers**: A($100), B($150), C($180), D($240) per day
- **Weekly Discounts**: Automatic application for 7+ day rentals
- **Smart Validation**: Automatic date correction for invalid ranges

## 🔧 Prerequisites

- **Java**: JDK 8 or higher
- **JUnit**: 5.x for testing
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code with Java extensions

## 📦 Installation

### 1. Clone the Repository
```bash
git clone https://github.com/Avihu4/CarRentalSystem.git
cd CarRentalSystem
```

### 2. Project Structure Setup
```
CarRentalSystem
├── src/
│   ├── model/
│   │   ├── Car.java
│   │   ├── Date.java
│   │   └── Rent.java
│   └── test/
│       └── CarRentalSystemTest.java
├── lib/ (optional - for JUnit JAR)
│   └── junit-platform-console-standalone-1.9.2.jar
└── README.md
```

### 3. Compile the Project
```bash
# Compile main classes
javac -cp . src/model/*.java -d build/

# Compile tests (with JUnit)
javac -cp "build/:lib/junit-platform-console-standalone-1.9.2.jar" src/test/*.java -d build/
```

## 🚀 Usage

### Basic Car Operations
```java
// Create a car
Car car = new Car(1234567, 'B', "Toyota", true);

// Validate and get properties
System.out.println(car.getId());    // 1234567
System.out.println(car.getType());  // B
System.out.println(car.getBrand()); // Toyota
System.out.println(car.getIsManual()); // true

// Compare cars
Car betterCar = new Car(7654321, 'D', "BMW", false);
System.out.println(betterCar.better(car)); // true
```

### Date Operations
```java
// Create dates with validation
Date validDate = new Date(15, 6, 2023);    // Valid
Date invalidDate = new Date(32, 13, 2023); // Uses default: 1/1/2000

// Date calculations
Date tomorrow = validDate.tomorrow();
int daysBetween = validDate.difference(tomorrow); // 1

// Date comparisons
Date laterDate = new Date(20, 6, 2023);
System.out.println(validDate.before(laterDate)); // true
```

### Rental Management
```java
// Create a rental
Car car = new Car(1234567, 'B', "Toyota", true);
Date pickUpDate = new Date(15, 6, 2023);
Date returnDate = new Date(20, 6, 2023);
Rent rental = new Rent("John Doe", car, pickUpDate, returnDate);

// Calculate rental details
System.out.println(rental.howManyDays()); // 5
System.out.println(rental.getPrice());    // 750 (5 days * 150)

// Upgrade car
Car premiumCar = new Car(7654321, 'D', "BMW", false);
int upgradeCost = rental.upgrade(premiumCar);
System.out.println("Upgrade cost: $" + upgradeCost);

// Display rental summary
System.out.println(rental.toString());
// Output: Name:John Doe From:15/06/2023 To:20/06/2023 Type:D Days:5 Price:1200
```

### Advanced Features
```java
// Rental overlap detection
Rent rental1 = new Rent("Alice", car, new Date(1, 7, 2023), new Date(10, 7, 2023));
Rent rental2 = new Rent("Alice", car, new Date(5, 7, 2023), new Date(15, 7, 2023));

Rent mergedRental = rental1.overlap(rental2);
if (mergedRental != null) {
    System.out.println("Overlapping rentals merged: " + mergedRental.toString());
}

// Weekly pricing example
Date longPickUp = new Date(1, 8, 2023);
Date longReturn = new Date(12, 8, 2023); // 11 days
Rent longRental = new Rent("Bob", car, longPickUp, longReturn);
System.out.println("11-day rental price: $" + longRental.getPrice());
// Type B: (1 week * 945) + (4 days * 150) = 1545
```

## 🧪 Testing

### Run All Tests
```bash
# Using JUnit Platform Console
java -cp "build/:lib/junit-platform-console-standalone-1.9.2.jar" \
     org.junit.platform.console.ConsoleLauncher \
     --class-path build \
     --scan-class-path

# Or with Maven (if using Maven)
mvn test

# Or with Gradle (if using Gradle)
./gradlew test
```

### Test Coverage
The test suite includes:
- **120+ test cases** covering all classes
- **Edge cases**: Boundary values, invalid inputs, leap years
- **Integration tests**: Complete rental scenarios
- **Validation tests**: All business rule enforcement
- **Error handling**: Invalid data scenarios

### Key Test Categories
```java
// Constructor validation tests
@Test void testCarConstructorInvalidId()
@Test void testDateLeapYear()
@Test void testRentConstructorInvalidReturnDate()

// Business logic tests  
@Test void testCarBetter()
@Test void testRentGetPriceAllTypes()
@Test void testRentUpgradeSuccessful()

// Edge case tests
@Test void testCarIdBoundaries()
@Test void testDateMonthDaysValidation()
@Test void testRentSingleDay()
```

## 📚 API Documentation

### Car Class Methods

| Method | Parameters | Returns | Description |
|--------|------------|---------|-------------|
| `Car(int, char, String, boolean)` | id, type, brand, isManual | - | Constructor with validation |
| `Car(Car)` | other | - | Copy constructor |
| `getId()` | - | int | Get car ID |
| `getType()` | - | char | Get car type (A-D) |
| `getBrand()` | - | String | Get car brand |
| `getIsManual()` | - | boolean | Check if manual transmission |
| `setId(int)` | id | void | Set ID (if valid) |
| `setType(char)` | type | void | Set type (if valid A-D) |
| `setBrand(String)` | brand | void | Set brand |
| `setIsManual(boolean)` | isManual | void | Set transmission type |
| `equals(Car)` | other | boolean | Compare cars (type, brand, transmission) |
| `better(Car)` | other | boolean | Check if this car is better |
| `worse(Car)` | other | boolean | Check if this car is worse |
| `toString()` | - | String | String representation |

### Date Class Methods

| Method | Parameters | Returns | Description |
|--------|------------|---------|-------------|
| `Date(int, int, int)` | day, month, year | - | Constructor with validation |
| `Date(Date)` | other | - | Copy constructor |
| `getDay()` | - | int | Get day |
| `getMonth()` | - | int | Get month |
| `getYear()` | - | int | Get year |
| `setDay(int)` | day | void | Set day (if valid) |
| `setMonth(int)` | month | void | Set month (if valid) |
| `setYear(int)` | year | void | Set year (if valid) |
| `equals(Date)` | other | boolean | Compare dates |
| `before(Date)` | other | boolean | Check if before other date |
| `after(Date)` | other | boolean | Check if after other date |
| `difference(Date)` | other | int | Days between dates |
| `tomorrow()` | - | Date | Get tomorrow's date |
| `toString()` | - | String | Format: DD/MM/YYYY |

### Rent Class Methods

| Method | Parameters | Returns | Description |
|--------|------------|---------|-------------|
| `Rent(String, Car, Date, Date)` | name, car, pick, return | - | Constructor with validation |
| `Rent(Rent)` | other | - | Copy constructor |
| `getName()` | - | String | Get customer name |
| `getCar()` | - | Car | Get rental car (copy) |
| `getPickDate()` | - | Date | Get pickup date (copy) |
| `getReturnDate()` | - | Date | Get return date (copy) |
| `setName(String)` | name | void | Set customer name |
| `setCar(Car)` | car | void | Set rental car |
| `setPickDate(Date)` | date | void | Set pickup date (if valid) |
| `setReturnDate(Date)` | date | void | Set return date (if valid) |
| `equals(Rent)` | other | boolean | Compare rentals |
| `howManyDays()` | - | int | Get rental duration |
| `getPrice()` | - | int | Calculate total price |
| `upgrade(Car)` | newCar | int | Upgrade car, return cost difference |
| `overlap(Rent)` | other | Rent | Merge overlapping rentals |
| `toString()` | - | String | Rental summary |

## 📊 Business Rules

### Car Classification
- **Type A**: Economy cars - $100/day, $630/week
- **Type B**: Compact cars - $150/day, $945/week
- **Type C**: Mid-size cars - $180/day, $1134/week
- **Type D**: Premium cars - $240/day, $1512/week

### Pricing Logic
```
Daily Rate: 
- Less than 7 days: days × daily_rate
- 7+ days: (weeks × weekly_rate) + (remaining_days × daily_rate)

Weekly Discounts:
- Type A: 10% off (630 vs 700)
- Type B: 10% off (945 vs 1050)
- Type C: 10% off (1134 vs 1260)
- Type D: 37% off (1512 vs 1680)
```

### Car Ranking System
1. **Primary**: Type (D > C > B > A)
2. **Secondary**: Transmission (Automatic > Manual)

### Validation Rules
- **Car ID**: Must be 7 digits (1,000,000 - 9,999,999)
- **Car Type**: Must be A, B, C, or D
- **Dates**: Year 1000-9999, valid month/day combinations
- **Rental Period**: Return date must be after pickup date

## 🔄 Contributing

### Development Setup
1. Fork the repository
2. Create a feature branch: `git checkout -b feature/new-feature`
3. Follow Java coding conventions
4. Add comprehensive tests for new features
5. Update documentation as needed

### Code Style Guidelines
- Use camelCase for variables and methods
- Use PascalCase for class names
- Private fields prefixed with underscore: `_fieldName`
- Comprehensive JavaDoc for all public methods
- Meaningful variable names

### Testing Standards
- Minimum 90% code coverage
- Test all edge cases and boundary conditions
- Include both positive and negative test scenarios
- Use descriptive test method names with `@DisplayName`

### Pull Request Process
1. Ensure all tests pass
2. Update README if API changes
3. Add changelog entry
4. Request review from maintainers

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Support

- **Issues**: Report bugs via [GitHub Issues](https://github.com/YOUR_USERNAME/YOUR_REPOSITORY_NAME/issues)
- **Discussions**: Ask questions in [GitHub Discussions](https://github.com/YOUR_USERNAME/YOUR_REPOSITORY_NAME/discussions)
- **Documentation**: Full API docs available in code comments

## 🔮 Future Enhancements

- [ ] Database integration (JPA/Hibernate)
- [ ] REST API endpoints (Spring Boot)
- [ ] Customer management system
- [ ] Payment processing integration
- [ ] Rental history and analytics
- [ ] Multi-location support
- [ ] Advanced reporting features
- [ ] Mobile app compatibility

---

**Created by**: Avihu Tubi  
**QA Testing**: Comprehensive test suite included (created with AI assistance)  
**Last Updated**: June 2025

> 💡 **Tip**: Start with the test file to understand expected behavior, then explore the main classes!