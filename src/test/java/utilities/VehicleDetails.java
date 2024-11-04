package utilities;

import java.util.Objects;

public class VehicleDetails
{
    private String regNo;
    private String make;
    private String model;
    private int year;

    public VehicleDetails(String regNo, String make, String model, int year)
    {
        this.regNo = regNo;
        this.model = model;
        this.make = make;
        this.year = year;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleDetails that = (VehicleDetails) o;
        return year == that.year &&
                Objects.equals(regNo, that.regNo) &&
                Objects.equals(make, that.make) &&
                Objects.equals(model, that.model);
    }


    @Override
    public String toString()
    {
        return "VehicleDetails{"
                +"registration='" + regNo
                + '\''
                +", make='" + make
                + '\''
                +", model='" + model
                + '\''
                +", year=" + year
                +'}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNo, make, model, year);
    }

}