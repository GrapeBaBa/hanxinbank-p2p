package com.hanxinbank.p2p.core.common.domain;

import org.javamoney.moneta.Money;

import javax.money.*;
import java.io.Serializable;
import java.math.BigDecimal;

public final class RMB implements MonetaryAmount, Comparable<MonetaryAmount>, Serializable {

    private final Money money;

    private RMB(BigDecimal number) {
        money = Money.of(number, "RMB");
    }

    public static RMB of(BigDecimal number) {
        return new RMB(number);
    }

    public static RMB zero() {
        return new RMB(new BigDecimal("0.00"));
    }

    @Override
    public MonetaryContext getMonetaryContext() {
        return money.getMonetaryContext();
    }

    @Override
    public MonetaryAmountFactory<? extends MonetaryAmount> getFactory() {
        return money.getFactory();
    }

    @Override
    public boolean isGreaterThan(MonetaryAmount amount) {
        return money.isGreaterThan(amount);
    }

    @Override
    public boolean isGreaterThanOrEqualTo(MonetaryAmount amount) {
        return money.isGreaterThanOrEqualTo(amount);
    }

    @Override
    public boolean isLessThan(MonetaryAmount amount) {
        return money.isLessThan(amount);
    }

    @Override
    public boolean isLessThanOrEqualTo(MonetaryAmount amt) {
        return money.isLessThanOrEqualTo(amt);
    }

    @Override
    public boolean isEqualTo(MonetaryAmount amount) {
        return money.isEqualTo(amount);
    }

    @Override
    public int signum() {
        return money.signum();
    }

    @Override
    public MonetaryAmount add(MonetaryAmount amount) {
        return money.add(amount);
    }

    @Override
    public MonetaryAmount subtract(MonetaryAmount amount) {
        return money.subtract(amount);
    }

    @Override
    public MonetaryAmount multiply(long multiplicand) {
        return money.multiply(multiplicand);
    }

    @Override
    public MonetaryAmount multiply(double multiplicand) {
        return money.multiply(multiplicand);
    }

    @Override
    public MonetaryAmount multiply(Number multiplicand) {
        return money.multiply(multiplicand);
    }

    @Override
    public MonetaryAmount divide(long divisor) {
        return money.divide(divisor);
    }

    @Override
    public MonetaryAmount divide(double divisor) {
        return money.divide(divisor);
    }

    @Override
    public MonetaryAmount divide(Number divisor) {
        return money.divide(divisor);
    }

    @Override
    public MonetaryAmount remainder(long divisor) {
        return money.remainder(divisor);
    }

    @Override
    public MonetaryAmount remainder(double divisor) {
        return money.remainder(divisor);
    }

    @Override
    public MonetaryAmount remainder(Number divisor) {
        return money.remainder(divisor);
    }

    @Override
    public MonetaryAmount[] divideAndRemainder(long divisor) {
        return money.divideAndRemainder(divisor);
    }

    @Override
    public MonetaryAmount[] divideAndRemainder(double divisor) {
        return money.divideAndRemainder(divisor);
    }

    @Override
    public MonetaryAmount[] divideAndRemainder(Number divisor) {
        return money.divideAndRemainder(divisor);
    }

    @Override
    public MonetaryAmount divideToIntegralValue(long divisor) {
        return money.divideToIntegralValue(divisor);
    }

    @Override
    public MonetaryAmount divideToIntegralValue(double divisor) {
        return money.divideToIntegralValue(divisor);
    }

    @Override
    public MonetaryAmount divideToIntegralValue(Number divisor) {
        return money.divideToIntegralValue(divisor);
    }

    @Override
    public MonetaryAmount scaleByPowerOfTen(int power) {
        return money.scaleByPowerOfTen(power);
    }

    @Override
    public MonetaryAmount abs() {
        return money.abs();
    }

    @Override
    public MonetaryAmount negate() {
        return money.negate();
    }

    @Override
    public MonetaryAmount plus() {
        return money.plus();
    }

    @Override
    public MonetaryAmount stripTrailingZeros() {
        return money.stripTrailingZeros();
    }

    @Override
    public int compareTo(MonetaryAmount o) {
        return money.compareTo(o);
    }

    @Override
    public CurrencyUnit getCurrency() {
        return money.getCurrency();
    }

    @Override
    public NumberValue getNumber() {
        return money.getNumber();
    }

    public BigDecimal asNumberStripped() {
        return money.getNumberStripped();
    }
}
