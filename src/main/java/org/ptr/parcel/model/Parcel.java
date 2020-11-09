package org.ptr.parcel.model;

import java.util.Objects;

/**
 * Parcel POJO
 *
 * */
public class Parcel {

    private String postalCode;
    private Double weight;

    public Parcel() {
    }

    public Parcel(String postalCode, Double weight) {
        this.postalCode = postalCode;
        this.weight = weight;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Parcel parcel = (Parcel) o;
        return Objects.equals(postalCode, parcel.postalCode) &&
            Objects.equals(weight, parcel.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postalCode, weight);
    }

    @Override
    public String toString() {
        return "Parcel{" +
            "postalCode='" + postalCode + '\'' +
            ", weight=" + weight +
            '}';
    }
}
