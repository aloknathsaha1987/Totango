package com.aloknath.totango.Objects;

/**
 * Created by ALOKNATH on 3/9/2015.
 */
public class Hits {

    private int missing;
    private Health health;
    private int total_hits;
    private ContractValue contract_value;

    public int getMissing() {
        return missing;
    }

    public void setMissing(int missing) {
        this.missing = missing;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public int getTotal_hits() {
        return total_hits;
    }

    public void setTotal_hits(int total_hits) {
        this.total_hits = total_hits;
    }

    public ContractValue getContract_value() {
        return contract_value;
    }

    public void setContract_value(ContractValue contract_value) {
        this.contract_value = contract_value;
    }

    @Override
    public String toString() {
        return "Hits{" +
                "missing=" + missing +
                ", health=" + health +
                ", total_hits=" + total_hits +
                ", contract_value=" + contract_value +
                '}';
    }
}
