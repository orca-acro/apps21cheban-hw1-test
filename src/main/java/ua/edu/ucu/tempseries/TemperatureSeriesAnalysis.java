package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    public double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[]{};

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double el : temperatureSeries) {
            if (el < -273) {
                throw new InputMismatchException();
            } else {
                this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
            }
        }
    }

    public double average() {
        double aver = 0.0;
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            for (double el : this.temperatureSeries) {
                aver += el;
            }
            aver = aver / this.temperatureSeries.length;
        }
        return aver;
    }

    public double deviation() {
        double dev = 0.0;
        double aver = average();
        for (double el : this.temperatureSeries) {
            dev += Math.pow((el - aver), 2);
        }
        return dev;
    }


    public double min() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            Arrays.sort(temperatureSeries);
        }
        return temperatureSeries[0];
    }

    public double max() {
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            Arrays.sort(temperatureSeries);
        }
        return temperatureSeries[temperatureSeries.length - 1];
    }

    public double findTempClosestToZero() {
        double mood = Math.abs(temperatureSeries[0]);
        double closest = temperatureSeries[0];
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            for (int i = 0; i < temperatureSeries.length; i++) {
                double current = Math.abs(temperatureSeries[i]);
                if (current < mood) {
                    closest = temperatureSeries[i];
                    mood = current;
                }
            }
        }
        return closest;
    }

    public double findTempClosestToValue(double tempValue) {
        double mood = Math.abs(temperatureSeries[0] - tempValue);
        double closest = temperatureSeries[0];
        if (this.temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            for (int i = 0; i < temperatureSeries.length; i++) {
                double current = Math.abs(temperatureSeries[i] - tempValue);
                if (current < mood) {
                    closest = temperatureSeries[i];
                    mood = current;
                }
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        int counter = 0;
        Arrays.sort(temperatureSeries);
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < tempValue) {
                counter += 1;
            }
        }
        double[] result = new double[counter];
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < tempValue) {
                result[i] = temperatureSeries[i];
            }
        }
        return result;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int counter = 0;
        Arrays.sort(temperatureSeries);
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] >= tempValue) {
                counter += 1;
            }
        }
        double[] result = new double[counter];
        int idx = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] >= tempValue) {
                result[idx] = temperatureSeries[i];
                idx += 1;
            }
        }
        return result;
    }

    public TempSummaryStatistics summaryStatistics() {
        return null;
    }

    public double addTemps(double... temps) {
        if (temperatureSeries.length == 0) {
            temperatureSeries = temps;
        } else {
            double[] temporary = new double[temperatureSeries.length + temps.length];
            for (int i = 0; i < temperatureSeries.length; i++) {
                temporary[i] = temperatureSeries[i];
            }
            int counter = 0;
            for (int i = temperatureSeries.length; i < temporary.length; i++) {
                if (counter != temps.length) {
                    temporary[i] = temps[counter];
                    counter += 1;
                }
            }
            temperatureSeries = temporary;
        }
        double suma = 0;
        for (int i = 0; i < temperatureSeries.length; i++) {
            suma += temperatureSeries[i];
        }
        return suma;
    }
}
